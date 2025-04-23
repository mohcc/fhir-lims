package zw.gov.mohcc.lims.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class DeletePortalTypeRequest {
  @JsonProperty("portalType")
  private String portalType;

  @JsonProperty("resourceId")
  private String resourceId;

  @JsonProperty("portalType")
  public String getPortalType() {
    return this.portalType;
  }

  @JsonProperty("portalType")
  public void setPortalType(String portalType) {
    this.portalType = portalType;
  }

  public DeletePortalTypeRequest withPortalType(String portalType) {
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

  public DeletePortalTypeRequest withResourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  @Override
  public boolean equals(Object rhs) {
    if (rhs == this) return true;
    if (!(rhs instanceof DeletePortalTypeRequest o)) return false;
    return Objects.equals(portalType, o.portalType)
        && Objects.equals(resourceId, o.resourceId);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = result * 31 + Objects.hashCode(portalType);
    result = result * 31 + Objects.hashCode(resourceId);
    return result;
  }

  @Override
  public String toString() {
    return "DeletePortalTypeRequest" + "{" +
            " " + "portalType" + "=" + "'" + portalType + "'" +
            "," + " " + "resourceId" + "=" + "'" + resourceId + "'" + "}";
  }
}
