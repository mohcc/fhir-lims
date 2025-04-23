package zw.gov.mohcc.lims.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class User {
  @JsonProperty("api_url")
  private String apiUrl;

  @JsonProperty("authenticated")
  private Boolean authenticated;

  @JsonProperty("description")
  private String description;

  @JsonProperty("email")
  private String email;

  @JsonProperty("error_log_update")
  private Integer errorLogUpdate;

  @JsonProperty("ext_editor")
  private Boolean extEditor;

  @JsonProperty("fullname")
  private String fullname;

  @JsonProperty("groups")
  private List<String> groups;

  @JsonProperty("home_page")
  private String homePage;

  @JsonProperty("id")
  private String id;

  @JsonProperty("language")
  private String language;

  @JsonProperty("last_login_time")
  private String lastLoginTime;

  @JsonProperty("linked_contact_uid")
  private String linkedContactUid;

  @JsonProperty("listed")
  private Boolean listed;

  @JsonProperty("location")
  private String location;

  @JsonProperty("login_time")
  private String loginTime;

  @JsonProperty("portal_skin")
  private String portalSkin;

  @JsonProperty("roles")
  private List<String> roles;

  @JsonProperty("url")
  private String url;

  @JsonProperty("username")
  private String username;

  @JsonProperty("visible_ids")
  private Boolean visibleIds;

  @JsonProperty("wysiwyg_editor")
  private String wysiwygEditor;

  @JsonProperty("api_url")
  public String getApiUrl() {
    return this.apiUrl;
  }

  @JsonProperty("api_url")
  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }

  public User withApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
    return this;
  }

  @JsonProperty("authenticated")
  public Boolean getAuthenticated() {
    return this.authenticated;
  }

  @JsonProperty("authenticated")
  public void setAuthenticated(Boolean authenticated) {
    this.authenticated = authenticated;
  }

  public User withAuthenticated(Boolean authenticated) {
    this.authenticated = authenticated;
    return this;
  }

  @JsonProperty("description")
  public String getDescription() {
    return this.description;
  }

  @JsonProperty("description")
  public void setDescription(String description) {
    this.description = description;
  }

  public User withDescription(String description) {
    this.description = description;
    return this;
  }

  @JsonProperty("email")
  public String getEmail() {
    return this.email;
  }

  @JsonProperty("email")
  public void setEmail(String email) {
    this.email = email;
  }

  public User withEmail(String email) {
    this.email = email;
    return this;
  }

  @JsonProperty("error_log_update")
  public Integer getErrorLogUpdate() {
    return this.errorLogUpdate;
  }

  @JsonProperty("error_log_update")
  public void setErrorLogUpdate(Integer errorLogUpdate) {
    this.errorLogUpdate = errorLogUpdate;
  }

  public User withErrorLogUpdate(Integer errorLogUpdate) {
    this.errorLogUpdate = errorLogUpdate;
    return this;
  }

  @JsonProperty("ext_editor")
  public Boolean getExtEditor() {
    return this.extEditor;
  }

  @JsonProperty("ext_editor")
  public void setExtEditor(Boolean extEditor) {
    this.extEditor = extEditor;
  }

  public User withExtEditor(Boolean extEditor) {
    this.extEditor = extEditor;
    return this;
  }

  @JsonProperty("fullname")
  public String getFullname() {
    return this.fullname;
  }

  @JsonProperty("fullname")
  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public User withFullname(String fullname) {
    this.fullname = fullname;
    return this;
  }

  @JsonProperty("groups")
  public List<String> getGroups() {
    return this.groups;
  }

  @JsonProperty("groups")
  public void setGroups(List<String> groups) {
    this.groups = groups;
  }

  public User withGroups(List<String> groups) {
    this.groups = groups;
    return this;
  }

  @JsonProperty("home_page")
  public String getHomePage() {
    return this.homePage;
  }

  @JsonProperty("home_page")
  public void setHomePage(String homePage) {
    this.homePage = homePage;
  }

  public User withHomePage(String homePage) {
    this.homePage = homePage;
    return this;
  }

  @JsonProperty("id")
  public String getId() {
    return this.id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  public User withId(String id) {
    this.id = id;
    return this;
  }

  @JsonProperty("language")
  public String getLanguage() {
    return this.language;
  }

  @JsonProperty("language")
  public void setLanguage(String language) {
    this.language = language;
  }

  public User withLanguage(String language) {
    this.language = language;
    return this;
  }

  @JsonProperty("last_login_time")
  public String getLastLoginTime() {
    return this.lastLoginTime;
  }

  @JsonProperty("last_login_time")
  public void setLastLoginTime(String lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public User withLastLoginTime(String lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
    return this;
  }

  @JsonProperty("linked_contact_uid")
  public String getLinkedContactUid() {
    return this.linkedContactUid;
  }

  @JsonProperty("linked_contact_uid")
  public void setLinkedContactUid(String linkedContactUid) {
    this.linkedContactUid = linkedContactUid;
  }

  public User withLinkedContactUid(String linkedContactUid) {
    this.linkedContactUid = linkedContactUid;
    return this;
  }

  @JsonProperty("listed")
  public Boolean getListed() {
    return this.listed;
  }

  @JsonProperty("listed")
  public void setListed(Boolean listed) {
    this.listed = listed;
  }

  public User withListed(Boolean listed) {
    this.listed = listed;
    return this;
  }

  @JsonProperty("location")
  public String getLocation() {
    return this.location;
  }

  @JsonProperty("location")
  public void setLocation(String location) {
    this.location = location;
  }

  public User withLocation(String location) {
    this.location = location;
    return this;
  }

  @JsonProperty("login_time")
  public String getLoginTime() {
    return this.loginTime;
  }

  @JsonProperty("login_time")
  public void setLoginTime(String loginTime) {
    this.loginTime = loginTime;
  }

  public User withLoginTime(String loginTime) {
    this.loginTime = loginTime;
    return this;
  }

  @JsonProperty("portal_skin")
  public String getPortalSkin() {
    return this.portalSkin;
  }

  @JsonProperty("portal_skin")
  public void setPortalSkin(String portalSkin) {
    this.portalSkin = portalSkin;
  }

  public User withPortalSkin(String portalSkin) {
    this.portalSkin = portalSkin;
    return this;
  }

  @JsonProperty("roles")
  public List<String> getRoles() {
    return this.roles;
  }

  @JsonProperty("roles")
  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public User withRoles(List<String> roles) {
    this.roles = roles;
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

  public User withUrl(String url) {
    this.url = url;
    return this;
  }

  @JsonProperty("username")
  public String getUsername() {
    return this.username;
  }

  @JsonProperty("username")
  public void setUsername(String username) {
    this.username = username;
  }

  public User withUsername(String username) {
    this.username = username;
    return this;
  }

  @JsonProperty("visible_ids")
  public Boolean getVisibleIds() {
    return this.visibleIds;
  }

  @JsonProperty("visible_ids")
  public void setVisibleIds(Boolean visibleIds) {
    this.visibleIds = visibleIds;
  }

  public User withVisibleIds(Boolean visibleIds) {
    this.visibleIds = visibleIds;
    return this;
  }

  @JsonProperty("wysiwyg_editor")
  public String getWysiwygEditor() {
    return this.wysiwygEditor;
  }

  @JsonProperty("wysiwyg_editor")
  public void setWysiwygEditor(String wysiwygEditor) {
    this.wysiwygEditor = wysiwygEditor;
  }

  public User withWysiwygEditor(String wysiwygEditor) {
    this.wysiwygEditor = wysiwygEditor;
    return this;
  }

  @Override
  public boolean equals(Object rhs) {
    if (rhs == this) return true;
    if (!(rhs instanceof User o)) return false;
    return Objects.equals(apiUrl, o.apiUrl)
        && Objects.equals(authenticated, o.authenticated)
        && Objects.equals(description, o.description)
        && Objects.equals(email, o.email)
        && Objects.equals(errorLogUpdate, o.errorLogUpdate)
        && Objects.equals(extEditor, o.extEditor)
        && Objects.equals(fullname, o.fullname)
        && Objects.equals(groups, o.groups)
        && Objects.equals(homePage, o.homePage)
        && Objects.equals(id, o.id)
        && Objects.equals(language, o.language)
        && Objects.equals(lastLoginTime, o.lastLoginTime)
        && Objects.equals(linkedContactUid, o.linkedContactUid)
        && Objects.equals(listed, o.listed)
        && Objects.equals(location, o.location)
        && Objects.equals(loginTime, o.loginTime)
        && Objects.equals(portalSkin, o.portalSkin)
        && Objects.equals(roles, o.roles)
        && Objects.equals(url, o.url)
        && Objects.equals(username, o.username)
        && Objects.equals(visibleIds, o.visibleIds)
        && Objects.equals(wysiwygEditor, o.wysiwygEditor);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = result * 31 + Objects.hashCode(apiUrl);
    result = result * 31 + Objects.hashCode(authenticated);
    result = result * 31 + Objects.hashCode(description);
    result = result * 31 + Objects.hashCode(email);
    result = result * 31 + Objects.hashCode(errorLogUpdate);
    result = result * 31 + Objects.hashCode(extEditor);
    result = result * 31 + Objects.hashCode(fullname);
    result = result * 31 + Objects.hashCode(groups);
    result = result * 31 + Objects.hashCode(homePage);
    result = result * 31 + Objects.hashCode(id);
    result = result * 31 + Objects.hashCode(language);
    result = result * 31 + Objects.hashCode(lastLoginTime);
    result = result * 31 + Objects.hashCode(linkedContactUid);
    result = result * 31 + Objects.hashCode(listed);
    result = result * 31 + Objects.hashCode(location);
    result = result * 31 + Objects.hashCode(loginTime);
    result = result * 31 + Objects.hashCode(portalSkin);
    result = result * 31 + Objects.hashCode(roles);
    result = result * 31 + Objects.hashCode(url);
    result = result * 31 + Objects.hashCode(username);
    result = result * 31 + Objects.hashCode(visibleIds);
    result = result * 31 + Objects.hashCode(wysiwygEditor);
    return result;
  }

  @Override
  public String toString() {
    return "User" + "{" +
            " " + "apiUrl" + "=" + "'" + apiUrl + "'" +
            "," + " " + "authenticated" + "=" + "'" + authenticated + "'" +
            "," + " " + "description" + "=" + "'" + description + "'" +
            "," + " " + "email" + "=" + "'" + email + "'" +
            "," + " " + "errorLogUpdate" + "=" + "'" + errorLogUpdate + "'" +
            "," + " " + "extEditor" + "=" + "'" + extEditor + "'" +
            "," + " " + "fullname" + "=" + "'" + fullname + "'" +
            "," + " " + "groups" + "=" + "'" + groups + "'" +
            "," + " " + "homePage" + "=" + "'" + homePage + "'" +
            "," + " " + "id" + "=" + "'" + id + "'" +
            "," + " " + "language" + "=" + "'" + language + "'" +
            "," + " " + "lastLoginTime" + "=" + "'" + lastLoginTime + "'" +
            "," + " " + "linkedContactUid" + "=" + "'" + linkedContactUid + "'" +
            "," + " " + "listed" + "=" + "'" + listed + "'" +
            "," + " " + "location" + "=" + "'" + location + "'" +
            "," + " " + "loginTime" + "=" + "'" + loginTime + "'" +
            "," + " " + "portalSkin" + "=" + "'" + portalSkin + "'" +
            "," + " " + "roles" + "=" + "'" + roles + "'" +
            "," + " " + "url" + "=" + "'" + url + "'" +
            "," + " " + "username" + "=" + "'" + username + "'" +
            "," + " " + "visibleIds" + "=" + "'" + visibleIds + "'" +
            "," + " " + "wysiwygEditor" + "=" + "'" + wysiwygEditor + "'" + "}";
  }
}
