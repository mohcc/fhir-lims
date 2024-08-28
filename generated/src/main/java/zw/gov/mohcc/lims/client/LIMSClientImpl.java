package zw.gov.mohcc.lims.client;

import java.util.Map;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import zw.gov.mohcc.lims.client.models.GetPortalTypeRequest;
import zw.gov.mohcc.lims.client.models.LoginRequest;
import zw.gov.mohcc.lims.client.models.LogoutRequest;
import zw.gov.mohcc.lims.client.models.LogoutResponse;
import zw.gov.mohcc.lims.client.models.PortalTypePage;
import zw.gov.mohcc.lims.client.models.UserPage;

@SuppressWarnings({
        "unused",
        "SameParameterValue",
        "Duplicates",
        "squid:S3776",
        "squid:S1192"
})
class LIMSClientImpl implements LIMSClient {
    private final Map<String, String> defaultHeaders;

    private final String url;

    private final RestTemplate restTemplate;

    LIMSClientImpl(String url, RestTemplate restTemplate, Map<String, String> defaultHeaders) {
        this.url = url;
        this.restTemplate = restTemplate;
        this.defaultHeaders = defaultHeaders;
    }

    private LinkedMultiValueMap<String, String> contentType(String value) {
        final var headers  = new LinkedMultiValueMap<String, String>();
        headers.set(HttpHeaders.CONTENT_TYPE, value);
        return headers;
    }

    private LinkedMultiValueMap<String, String> contentDisposition(String property, String filename,
            String contentType) {
        final var headers  = new LinkedMultiValueMap<String, String>();
        final var  content = ContentDisposition
                                .formData()
                                .name(property)
                                .filename(filename)
                                .build().toString();
        headers.set(HttpHeaders.CONTENT_TYPE, contentType);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, content);
        return headers;
    }

    public ResponseEntity<PortalTypePage> getPortalType(GetPortalTypeRequest getPortalTypeRequest) {
        final var endpoint  = url + "/" + getPortalTypeRequest.getPortalType();
        final var headers  = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        defaultHeaders.forEach(headers::set);
        return restTemplate.exchange(endpoint, HttpMethod.GET,
                    new HttpEntity<>(headers),
                        PortalTypePage.class);
    }

    public ResponseEntity<UserPage> login(LoginRequest loginRequest) {
        final var builder  = UriComponentsBuilder.fromUriString(url + "/login");
        if(loginRequest.getUsername() != null) {
            builder.queryParam("__ac_name", loginRequest.getUsername());
        }
        if(loginRequest.getPassword() != null) {
            builder.queryParam("__ac_password", loginRequest.getPassword());
        }
        final var endpoint  = builder.build().toString();
        final var headers  = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        defaultHeaders.forEach(headers::set);
        return restTemplate.exchange(endpoint, HttpMethod.POST,
                    new HttpEntity<>(headers),
                        UserPage.class);
    }

    public ResponseEntity<LogoutResponse> logout(LogoutRequest logoutRequest) {
        final var endpoint  = url + "/users/logout";
        final var headers  = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        defaultHeaders.forEach(headers::set);
        return restTemplate.exchange(endpoint, HttpMethod.POST,
                    new HttpEntity<>(headers),
                        LogoutResponse.class);
    }
}
