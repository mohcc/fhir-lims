package zw.gov.mohcc.mrs.fhir.lims.tools;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.TrustSelfSignedStrategy;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.util.Timeout;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.DefaultStrategy;
import org.zalando.logbook.core.ResponseFilters;
import org.zalando.logbook.httpclient5.LogbookHttpExecHandler;
import zw.gov.mohcc.lims.client.LIMSClient;
import zw.gov.mohcc.lims.client.models.GetPortalTypeRequest;
import zw.gov.mohcc.lims.client.models.LoginRequest;
import zw.gov.mohcc.mrs.fhir.lims.log.LogstashLogbackSink;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.io.IOUtils.resourceToURL;
import static org.springframework.http.MediaType.APPLICATION_PDF;
import static org.zalando.logbook.BodyReplacer.composite;
import static org.zalando.logbook.core.BodyFilters.defaultValue;
import static org.zalando.logbook.core.BodyReplacers.*;
import static org.zalando.logbook.core.HeaderFilters.authorization;
import static org.zalando.logbook.core.QueryFilters.accessToken;
import static org.zalando.logbook.core.QueryFilters.replaceQuery;

@Slf4j
public class SenaiteApp {
    public static void configureLoggerContext(String resource) {
        final var loggerFactory = LoggerFactory.getILoggerFactory();
        if (loggerFactory instanceof LoggerContext loggerContext) {
            loggerContext.reset();
            final var configurator = new JoranConfigurator();
            try (final var stream = resourceToURL(resource).openStream()) {
                configurator.setContext(loggerContext);
                configurator.doConfigure(stream);
            } catch (IOException | JoranException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }


    public static RestTemplate createRestTemplate(HttpClient httpClient, ObjectMapper mapper, ConversionService conversionService) {
        final var requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        final var restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(requestFactory));
        final var messageConverter = new ByteArrayHttpMessageConverter();
        final var mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(APPLICATION_PDF);
        messageConverter.setSupportedMediaTypes(mediaTypes);
        List<HttpMessageConverter<?>> messageConverters = restTemplate
                .getMessageConverters();
        messageConverters
                .add(messageConverter);
        for (final var httpMessageConverter : messageConverters) {
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter converter) {
                var supportedMediaTypes = new ArrayList<>(converter.getSupportedMediaTypes());
                supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
                converter.setSupportedMediaTypes(supportedMediaTypes);
                converter.setObjectMapper(mapper);
            }
            if (httpMessageConverter instanceof FormHttpMessageConverter converter) {
                converter.addPartConverter(new ObjectToStringHttpMessageConverter(conversionService));
            }
        }
        return restTemplate;
    }

    public static Logbook createLogbook(ObjectMapper mapper) {
        return Logbook.builder()
                .bodyFilters(Collections.singletonList(
                        defaultValue()
                ))
                .headerFilters(List.of(authorization()))
                .strategy(new DefaultStrategy())
                .responseFilter(ResponseFilters
                        .replaceBody(composite(
                                binary(),
                                multipart(),
                                stream()
                        )))
                .sink(new LogstashLogbackSink(mapper))
                .queryFilters(List.of(
                        accessToken(),
                        replaceQuery("secret", "<secret>"),
                        replaceQuery("remoteip", "<ip>"),
                        replaceQuery("__ac_password", "<password>"),
                        replaceQuery("__ac_name", "<name>")
                ))
                .build();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        configureLoggerContext("/logback-console.xml");
        final var baseURL = "https://mhealth.nmrl.org.zw:10043/senaite/@@API/senaite/v1";
        final var username = "";
        final var password = "";


//        final var baseURL = "http://localhost:8081/senaite/@@API/senaite/v1";
//        final var username = "admin";
//        final var password = "admin";

        final PoolingHttpClientConnectionManager connectionManager =
                PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(new SSLConnectionSocketFactory(
                        SSLContexts.custom().loadTrustMaterial(null, TrustSelfSignedStrategy.INSTANCE).build(),
                        NoopHostnameVerifier.INSTANCE))
                .build();
        final var mapper = new ObjectMapper().findAndRegisterModules();
        final var logbook = createLogbook(mapper);
        final var httpClient = HttpClientBuilder.create()
                .disableContentCompression()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(RequestConfig
                        .custom()
                        .setConnectTimeout(Timeout.of(30, TimeUnit.SECONDS))
                        .setResponseTimeout(Timeout.of(30, TimeUnit.SECONDS))
                        .build())
                .addExecInterceptorFirst("Logbook", new LogbookHttpExecHandler(logbook))
                .build();
        final var restTemplate = createRestTemplate(httpClient, mapper, new DefaultConversionService());
        final var headers = new HashMap<String, String>();
        final var client = LIMSClient.newClient(baseURL, restTemplate, Collections.emptyMap());
        final var userPageResponseEntity = client.login(new LoginRequest().withUsername(username).withPassword(password));
        final var cookie = Objects.requireNonNull(userPageResponseEntity.getHeaders().get("Set-Cookie")).get(0);
        headers.put("Cookie", cookie);

        // Batch
        // InboundSampleShipment
        // Method
        // Patient
        // DispatchCenter
        // WorkSheet

        final var portalTypes = List.of( "WorkSheet");
        for (final var portalType : portalTypes) {
            final var clients = Objects.requireNonNull(client.getPortalType(new GetPortalTypeRequest().withPortalType(portalType)).getBody()).getItems();
            int count = 0;
            for (JsonNode jsonNode : clients) {
                count++;
                if(count < 4){
                    try {
                        mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get("fixtures").resolve(portalType + count +".json").toFile(), jsonNode);
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            }
        }




    }
}
