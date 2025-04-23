package zw.gov.mohcc.lims.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class LoginRequest {
  /**
   * The username
   */
  @JsonProperty("__ac_name")
  private String username;

  /**
   * The password
   */
  @JsonProperty("__ac_password")
  private String password;

  @JsonProperty("__ac_name")
  public String getUsername() {
    return this.username;
  }

  @JsonProperty("__ac_name")
  public void setUsername(String username) {
    this.username = username;
  }

  public LoginRequest withUsername(String username) {
    this.username = username;
    return this;
  }

  @JsonProperty("__ac_password")
  public String getPassword() {
    return this.password;
  }

  @JsonProperty("__ac_password")
  public void setPassword(String password) {
    this.password = password;
  }

  public LoginRequest withPassword(String password) {
    this.password = password;
    return this;
  }

  @Override
  public boolean equals(Object rhs) {
    if (rhs == this) return true;
    if (!(rhs instanceof LoginRequest o)) return false;
    return Objects.equals(username, o.username)
        && Objects.equals(password, o.password);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = result * 31 + Objects.hashCode(username);
    result = result * 31 + Objects.hashCode(password);
    return result;
  }

  @Override
  public String toString() {
    return "LoginRequest" + "{" +
            " " + "username" + "=" + "'" + username + "'" +
            "," + " " + "password" + "=" + "'" + password + "'" + "}";
  }
}
