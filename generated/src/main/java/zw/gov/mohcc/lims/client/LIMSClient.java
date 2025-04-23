package zw.gov.mohcc.lims.client;

import java.util.Collections;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import zw.gov.mohcc.lims.client.models.CreatePortalTypeRequest;
import zw.gov.mohcc.lims.client.models.DeletePortalTypeRequest;
import zw.gov.mohcc.lims.client.models.GetPortalTypeRequest;
import zw.gov.mohcc.lims.client.models.LoginRequest;
import zw.gov.mohcc.lims.client.models.LogoutRequest;
import zw.gov.mohcc.lims.client.models.LogoutResponse;
import zw.gov.mohcc.lims.client.models.PortalTypePage;
import zw.gov.mohcc.lims.client.models.UpdatePortalTypeRequest;
import zw.gov.mohcc.lims.client.models.UserPage;

public interface LIMSClient {
    /**
     * Summary: Get portal type
     * Description: The API provides a simple way to authenticate a user with SENAITE.
     * The response will set the __ac cookie for further cookie authenticated requests.
     * Path: /{portalType}
     * Method: GET
     */
    ResponseEntity<PortalTypePage> getPortalType(GetPortalTypeRequest getPortalTypeRequest);

    /**
     * Summary: Get portal type
     * Description: The API provides a simple way to authenticate a user with SENAITE.
     * The response will set the __ac cookie for further cookie authenticated requests.
     * Path: /{portalType}
     * Method: POST
     */
    ResponseEntity<PortalTypePage> createPortalType(
            CreatePortalTypeRequest createPortalTypeRequest);

    /**
     * Summary: Get portal type
     * Description: The API provides a simple way to authenticate a user with SENAITE.
     * The response will set the __ac cookie for further cookie authenticated requests.
     * Path: /{portalType}/{resourceId}
     * Method: POST
     */
    ResponseEntity<PortalTypePage> updatePortalType(
            UpdatePortalTypeRequest updatePortalTypeRequest);

    /**
     * Summary: Get portal type
     * Description: The API provides a simple way to authenticate a user with SENAITE.
     * The response will set the __ac cookie for further cookie authenticated requests.
     * Path: /{portalType}/{resourceId}
     * Method: DELETE
     */
    ResponseEntity<PortalTypePage> deletePortalType(
            DeletePortalTypeRequest deletePortalTypeRequest);

    /**
     * Summary: Login
     * Description: The API provides a simple way to authenticate a user with SENAITE.
     * The response will set the __ac cookie for further cookie authenticated requests.
     * Path: /login
     * Method: POST
     */
    ResponseEntity<UserPage> login(LoginRequest loginRequest);

    /**
     * Summary: Logout
     * Description: The response will expire the __ac cookie for further requests.
     * Path: /users/logout
     * Method: POST
     */
    ResponseEntity<LogoutResponse> logout(LogoutRequest logoutRequest);

    default LIMSClient refreshAuthenticationInfo() {
        return this;
    }

    static LIMSClient newClient(String url, RestTemplate restTemplate,
            Map<String, String> defaultHeaders) {
        return new LIMSClientImpl(url, restTemplate, defaultHeaders);
    }

    static LIMSClient newClient(String url, RestTemplate restTemplate) {
        return newClient(url, restTemplate, Collections.emptyMap());
    }
}
