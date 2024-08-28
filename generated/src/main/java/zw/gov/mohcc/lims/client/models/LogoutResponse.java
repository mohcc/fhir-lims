package zw.gov.mohcc.lims.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class LogoutResponse {
  @JsonProperty("_runtime")
  private String runtime;

  @JsonProperty("success")
  private Boolean success;

  @JsonProperty("url")
  private String url;

  @JsonProperty("_runtime")
  public String getRuntime() {
    return this.runtime;
  }

  @JsonProperty("_runtime")
  public void setRuntime(String runtime) {
    this.runtime = runtime;
  }

  public LogoutResponse withRuntime(String runtime) {
    this.runtime = runtime;
    return this;
  }

  @JsonProperty("success")
  public Boolean getSuccess() {
    return this.success;
  }

  @JsonProperty("success")
  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public LogoutResponse withSuccess(Boolean success) {
    this.success = success;
    return this;
  }

  @JsonProperty("url")
  public String getUrl() {
    return this.url;
  }

  @JsonProperty("url")
  public void setUrl(String url) {
    this.url = url;
  }

  public LogoutResponse withUrl(String url) {
    this.url = url;
    return this;
  }

  @Override
  public boolean equals(Object rhs) {
    if (rhs == this) return true;
    if (!(rhs instanceof LogoutResponse o)) return false;
    return Objects.equals(runtime, o.runtime)
        && Objects.equals(success, o.success)
        && Objects.equals(url, o.url);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = result * 31 + Objects.hashCode(runtime);
    result = result * 31 + Objects.hashCode(success);
    result = result * 31 + Objects.hashCode(url);
    return result;
  }

  @Override
  public String toString() {
    return "LogoutResponse" + "{" +
            " " + "runtime" + "=" + "'" + runtime + "'" +
            "," + " " + "success" + "=" + "'" + success + "'" +
            "," + " " + "url" + "=" + "'" + url + "'" + "}";
  }
}
