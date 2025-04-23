package zw.gov.mohcc.mrs.fhir.lims.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.marker.RawJsonAppendingMarker;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.zalando.logbook.*;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Locale.ROOT;
import static org.slf4j.LoggerFactory.getLogger;

public final class LogstashLogbackSink implements Sink {

    private static final Logger logger = getLogger(LogstashLogbackSink.class);

    private final ObjectMapper mapper;

    public LogstashLogbackSink(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void write(final @NotNull Precorrelation precorrelation, final @NotNull HttpRequest request) throws IOException {

        // call so can be cached otherwise won't be able to write it in
        if(logger.isTraceEnabled()){
            request.getBody();
        }
    }

    @Nullable
    public String preparePort(final HttpRequest request){
        return request.getPort()
                .map(Object::toString)
                .orElse(null);
    }

    public Optional<Object> prepareHeaders(final HttpMessage message) {
        final var headers = message.getHeaders();
        return Optional.ofNullable(headers.isEmpty() ? null : headers);
    }

    public Optional<Object> prepareBody(final HttpMessage message) throws IOException {
        final var body = message.getBodyAsString();
        return Optional.ofNullable(body.isEmpty() ? null : body);
    }

    private Object response(final Correlation correlation, final HttpResponse response) throws IOException {

        final var content = new LinkedHashMap<String, Object>();

        content.put("origin", response.getOrigin().name().toLowerCase(ROOT));
        content.put("duration", correlation.getDuration().toMillis());
        content.put("protocol", response.getProtocolVersion());
        content.put("status", response.getStatus());

        prepareHeaders(response).ifPresent(headers -> content.put("headers", headers));
        prepareBody(response).ifPresent(body -> content.put("body", body));

        return content;
    }
    private Object request(final HttpRequest request)
            throws IOException {
        final var content = new LinkedHashMap<String, Object>();

        content.put("origin", request.getOrigin().name().toLowerCase(ROOT));
        content.put("protocol", request.getProtocolVersion());
        content.put("remote", request.getRemote());
        content.put("method", request.getMethod());
        content.put("uri", request.getRequestUri());
        content.put("host", request.getHost());
        content.put("path", request.getPath());
        content.put("scheme", request.getScheme());
        content.put("port", preparePort(request));

        prepareHeaders(request).ifPresent(headers -> content.put("headers", headers));
        prepareBody(request).ifPresent(body -> content.put("body", body));

        return content;
    }

    private String requestMessage(final HttpRequest request) {
        return request.getMethod() + " " + request.getRequestUri();
    }


    @Override
    public void write(final @NotNull Correlation correlation, final @NotNull HttpRequest request,
                      final @NotNull HttpResponse response) throws IOException {
        var httpMarker =  new RawJsonAppendingMarker(
                "http", mapper.writeValueAsString(
                        Map.of(    "correlation", correlation.getId(),
                                "request", this.request(request),
                                "response", this.response(correlation, response))));
        logger.info(httpMarker, requestMessage(request));

    }


}
