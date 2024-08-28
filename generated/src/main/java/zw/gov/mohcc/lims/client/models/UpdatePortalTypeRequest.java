package zw.gov.mohcc.lims.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class UpdatePortalTypeRequest {
  @JsonProperty("portalType")
  private String portalType;

  @JsonProperty("resourceId")
  private String resourceId;

  @JsonProperty("request")
  private JsonNode request;

  @JsonProperty("portalType")
  public String getPortalType() {
    return this.portalType;
  }

  @JsonProperty("portalType")
  public void setPortalType(String portalType) {
    this.portalType = portalType;
  }

  public UpdatePortalTypeRequest withPortalType(String portalType) {
    this.portalType = portalType;
    return this;
  }

  @JsonProperty("resourceId")
  public String getResourceId() {
    return this.resourceId;
  }

  @JsonProperty("resourceId")
  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public UpdatePortalTypeRequest withResourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  @JsonProperty("request")
  public JsonNode getRequest() {
    return this.request;
  }

  @JsonProperty("request")
  public void setRequest(JsonNode request) {
    this.request = request;
  }

  public UpdatePortalTypeRequest withRequest(JsonNode request) {
    this.request = request;
    return this;
  }

  @Override
  public boolean equals(Object rhs) {
    if (rhs == this) return true;
    if (!(rhs instanceof UpdatePortalTypeRequest o)) return false;
    return Objects.equals(portalType, o.portalType)
        && Objects.equals(resourceId, o.resourceId)
        && Objects.equals(request, o.request);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = result * 31 + Objects.hashCode(portalType);
    result = result * 31 + Objects.hashCode(resourceId);
    result = result * 31 + Objects.hashCode(request);
    return result;
  }

  @Override
  public String toString() {
    return "UpdatePortalTypeRequest" + "{" +
            " " + "portalType" + "=" + "'" + portalType + "'" +
            "," + " " + "resourceId" + "=" + "'" + resourceId + "'" +
            "," + " " + "request" + "=" + "'" + request + "'" + "}";
  }
}
