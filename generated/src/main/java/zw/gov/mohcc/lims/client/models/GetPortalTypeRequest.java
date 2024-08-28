package zw.gov.mohcc.lims.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class GetPortalTypeRequest {
  @JsonProperty("portalType")
  private String portalType;

  @JsonProperty("portalType")
  public String getPortalType() {
    return this.portalType;
  }

  @JsonProperty("portalType")
  public void setPortalType(String portalType) {
    this.portalType = portalType;
  }

  public GetPortalTypeRequest withPortalType(String portalType) {
    this.portalType = portalType;
    return this;
  }

  @Override
  public boolean equals(Object rhs) {
    if (rhs == this) return true;
    if (!(rhs instanceof GetPortalTypeRequest o)) return false;
    return Objects.equals(portalType, o.portalType);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = result * 31 + Objects.hashCode(portalType);
    return result;
  }

  @Override
  public String toString() {
    return "GetPortalTypeRequest" + "{" +
            " " + "portalType" + "=" + "'" + portalType + "'" + "}";
  }
}
