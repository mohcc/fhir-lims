package zw.gov.mohcc.lims.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class Client {
  @JsonProperty("Analyst")
  private String analyst;

  @JsonProperty("allowedRolesAndUsers")
  private String[] allowedRolesAndUsers;

  @JsonProperty("analysisRequestTemplates")
  private String analysisRequestTemplates;

  @JsonProperty("api_url")
  private String apiUrl;

  @JsonProperty("author")
  private String author;

  @JsonProperty("created")
  private String created;

  @JsonProperty("description")
  private String description;

  @JsonProperty("effective")
  private String effective;

  @JsonProperty("end")
  private String end;

  @JsonProperty("exclude_from_nav")
  private Boolean excludeFromNav;

  @JsonProperty("expires")
  private String expires;

  @JsonProperty("getClientID")
  private String clientId;

  @JsonProperty("getCountry")
  private String country;

  @JsonProperty("getDistrict")
  private String district;

  @JsonProperty("getDoctorID")
  private String doctorId;

  @JsonProperty("getDoctorUID")
  private String getDoctorUID;

  @JsonProperty("getProvince")
  private String province;

  @JsonProperty("id")
  private String id;

  @JsonProperty("is_folderish")
  private Boolean isFolderish;

  @JsonProperty("location")
  private String location;

  @JsonProperty("modified")
  private String modified;

  @JsonProperty("parent_id")
  private String parentId;

  @JsonProperty("parent_path")
  private String parentPath;

  @JsonProperty("parent_uid")
  private String parentUid;

  @JsonProperty("parent_url")
  private String parentUrl;

  @JsonProperty("path")
  private String path;

  @JsonProperty("portal_type")
  private String portalType;

  @JsonProperty("review_state")
  private String reviewState;

  @JsonProperty("start")
  private String start;

  @JsonProperty("tags")
  private List<String> tags;

  @JsonProperty("title")
  private String title;

  @JsonProperty("uid")
  private String uid;

  @JsonProperty("url")
  private String url;

  @JsonProperty("Analyst")
  public String getAnalyst() {
    return this.analyst;
  }

  @JsonProperty("Analyst")
  public void setAnalyst(String analyst) {
    this.analyst = analyst;
  }

  public Client withAnalyst(String analyst) {
    this.analyst = analyst;
    return this;
  }

  @JsonProperty("allowedRolesAndUsers")
  public String[] getAllowedRolesAndUsers() {
    return this.allowedRolesAndUsers;
  }

  @JsonProperty("allowedRolesAndUsers")
  public void setAllowedRolesAndUsers(String[] allowedRolesAndUsers) {
    this.allowedRolesAndUsers = allowedRolesAndUsers;
  }

  public Client withAllowedRolesAndUsers(String[] allowedRolesAndUsers) {
    this.allowedRolesAndUsers = allowedRolesAndUsers;
    return this;
  }

  @JsonProperty("analysisRequestTemplates")
  public String getAnalysisRequestTemplates() {
    return this.analysisRequestTemplates;
  }

  @JsonProperty("analysisRequestTemplates")
  public void setAnalysisRequestTemplates(String analysisRequestTemplates) {
    this.analysisRequestTemplates = analysisRequestTemplates;
  }

  public Client withAnalysisRequestTemplates(String analysisRequestTemplates) {
    this.analysisRequestTemplates = analysisRequestTemplates;
    return this;
  }

  @JsonProperty("api_url")
  public String getApiUrl() {
    return this.apiUrl;
  }

  @JsonProperty("api_url")
  public void setApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
  }

  public Client withApiUrl(String apiUrl) {
    this.apiUrl = apiUrl;
    return this;
  }

  @JsonProperty("author")
  public String getAuthor() {
    return this.author;
  }

  @JsonProperty("author")
  public void setAuthor(String author) {
    this.author = author;
  }

  public Client withAuthor(String author) {
    this.author = author;
    return this;
  }

  @JsonProperty("created")
  public String getCreated() {
    return this.created;
  }

  @JsonProperty("created")
  public void setCreated(String created) {
    this.created = created;
  }

  public Client withCreated(String created) {
    this.created = created;
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

  public Client withDescription(String description) {
    this.description = description;
    return this;
  }

  @JsonProperty("effective")
  public String getEffective() {
    return this.effective;
  }

  @JsonProperty("effective")
  public void setEffective(String effective) {
    this.effective = effective;
  }

  public Client withEffective(String effective) {
    this.effective = effective;
    return this;
  }

  @JsonProperty("end")
  public String getEnd() {
    return this.end;
  }

  @JsonProperty("end")
  public void setEnd(String end) {
    this.end = end;
  }

  public Client withEnd(String end) {
    this.end = end;
    return this;
  }

  @JsonProperty("exclude_from_nav")
  public Boolean getExcludeFromNav() {
    return this.excludeFromNav;
  }

  @JsonProperty("exclude_from_nav")
  public void setExcludeFromNav(Boolean excludeFromNav) {
    this.excludeFromNav = excludeFromNav;
  }

  public Client withExcludeFromNav(Boolean excludeFromNav) {
    this.excludeFromNav = excludeFromNav;
    return this;
  }

  @JsonProperty("expires")
  public String getExpires() {
    return this.expires;
  }

  @JsonProperty("expires")
  public void setExpires(String expires) {
    this.expires = expires;
  }

  public Client withExpires(String expires) {
    this.expires = expires;
    return this;
  }

  @JsonProperty("getClientID")
  public String getClientId() {
    return this.clientId;
  }

  @JsonProperty("getClientID")
  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public Client withClientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  @JsonProperty("getCountry")
  public String getCountry() {
    return this.country;
  }

  @JsonProperty("getCountry")
  public void setCountry(String country) {
    this.country = country;
  }

  public Client withCountry(String country) {
    this.country = country;
    return this;
  }

  @JsonProperty("getDistrict")
  public String getDistrict() {
    return this.district;
  }

  @JsonProperty("getDistrict")
  public void setDistrict(String district) {
    this.district = district;
  }

  public Client withDistrict(String district) {
    this.district = district;
    return this;
  }

  @JsonProperty("getDoctorID")
  public String getDoctorId() {
    return this.doctorId;
  }

  @JsonProperty("getDoctorID")
  public void setDoctorId(String doctorId) {
    this.doctorId = doctorId;
  }

  public Client withDoctorId(String doctorId) {
    this.doctorId = doctorId;
    return this;
  }

  @JsonProperty("getDoctorUID")
  public String getGetDoctorUID() {
    return this.getDoctorUID;
  }

  @JsonProperty("getDoctorUID")
  public void setGetDoctorUID(String getDoctorUID) {
    this.getDoctorUID = getDoctorUID;
  }

  public Client withGetDoctorUID(String getDoctorUID) {
    this.getDoctorUID = getDoctorUID;
    return this;
  }

  @JsonProperty("getProvince")
  public String getProvince() {
    return this.province;
  }

  @JsonProperty("getProvince")
  public void setProvince(String province) {
    this.province = province;
  }

  public Client withProvince(String province) {
    this.province = province;
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

  public Client withId(String id) {
    this.id = id;
    return this;
  }

  @JsonProperty("is_folderish")
  public Boolean getIsFolderish() {
    return this.isFolderish;
  }

  @JsonProperty("is_folderish")
  public void setIsFolderish(Boolean isFolderish) {
    this.isFolderish = isFolderish;
  }

  public Client withIsFolderish(Boolean isFolderish) {
    this.isFolderish = isFolderish;
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

  public Client withLocation(String location) {
    this.location = location;
    return this;
  }

  @JsonProperty("modified")
  public String getModified() {
    return this.modified;
  }

  @JsonProperty("modified")
  public void setModified(String modified) {
    this.modified = modified;
  }

  public Client withModified(String modified) {
    this.modified = modified;
    return this;
  }

  @JsonProperty("parent_id")
  public String getParentId() {
    return this.parentId;
  }

  @JsonProperty("parent_id")
  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public Client withParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  @JsonProperty("parent_path")
  public String getParentPath() {
    return this.parentPath;
  }

  @JsonProperty("parent_path")
  public void setParentPath(String parentPath) {
    this.parentPath = parentPath;
  }

  public Client withParentPath(String parentPath) {
    this.parentPath = parentPath;
    return this;
  }

  @JsonProperty("parent_uid")
  public String getParentUid() {
    return this.parentUid;
  }

  @JsonProperty("parent_uid")
  public void setParentUid(String parentUid) {
    this.parentUid = parentUid;
  }

  public Client withParentUid(String parentUid) {
    this.parentUid = parentUid;
    return this;
  }

  @JsonProperty("parent_url")
  public String getParentUrl() {
    return this.parentUrl;
  }

  @JsonProperty("parent_url")
  public void setParentUrl(String parentUrl) {
    this.parentUrl = parentUrl;
  }

  public Client withParentUrl(String parentUrl) {
    this.parentUrl = parentUrl;
    return this;
  }

  @JsonProperty("path")
  public String getPath() {
    return this.path;
  }

  @JsonProperty("path")
  public void setPath(String path) {
    this.path = path;
  }

  public Client withPath(String path) {
    this.path = path;
    return this;
  }

  @JsonProperty("portal_type")
  public String getPortalType() {
    return this.portalType;
  }

  @JsonProperty("portal_type")
  public void setPortalType(String portalType) {
    this.portalType = portalType;
  }

  public Client withPortalType(String portalType) {
    this.portalType = portalType;
    return this;
  }

  @JsonProperty("review_state")
  public String getReviewState() {
    return this.reviewState;
  }

  @JsonProperty("review_state")
  public void setReviewState(String reviewState) {
    this.reviewState = reviewState;
  }

  public Client withReviewState(String reviewState) {
    this.reviewState = reviewState;
    return this;
  }

  @JsonProperty("start")
  public String getStart() {
    return this.start;
  }

  @JsonProperty("start")
  public void setStart(String start) {
    this.start = start;
  }

  public Client withStart(String start) {
    this.start = start;
    return this;
  }

  @JsonProperty("tags")
  public List<String> getTags() {
    return this.tags;
  }

  @JsonProperty("tags")
  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public Client withTags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  @JsonProperty("title")
  public String getTitle() {
    return this.title;
  }

  @JsonProperty("title")
  public void setTitle(String title) {
    this.title = title;
  }

  public Client withTitle(String title) {
    this.title = title;
    return this;
  }

  @JsonProperty("uid")
  public String getUid() {
    return this.uid;
  }

  @JsonProperty("uid")
  public void setUid(String uid) {
    this.uid = uid;
  }

  public Client withUid(String uid) {
    this.uid = uid;
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

  public Client withUrl(String url) {
    this.url = url;
    return this;
  }

  @Override
  public boolean equals(Object rhs) {
    if (rhs == this) return true;
    if (!(rhs instanceof Client o)) return false;
    return Objects.equals(analyst, o.analyst)
        && Arrays.equals(allowedRolesAndUsers, o.allowedRolesAndUsers)
        && Objects.equals(analysisRequestTemplates, o.analysisRequestTemplates)
        && Objects.equals(apiUrl, o.apiUrl)
        && Objects.equals(author, o.author)
        && Objects.equals(created, o.created)
        && Objects.equals(description, o.description)
        && Objects.equals(effective, o.effective)
        && Objects.equals(end, o.end)
        && Objects.equals(excludeFromNav, o.excludeFromNav)
        && Objects.equals(expires, o.expires)
        && Objects.equals(clientId, o.clientId)
        && Objects.equals(country, o.country)
        && Objects.equals(district, o.district)
        && Objects.equals(doctorId, o.doctorId)
        && Objects.equals(getDoctorUID, o.getDoctorUID)
        && Objects.equals(province, o.province)
        && Objects.equals(id, o.id)
        && Objects.equals(isFolderish, o.isFolderish)
        && Objects.equals(location, o.location)
        && Objects.equals(modified, o.modified)
        && Objects.equals(parentId, o.parentId)
        && Objects.equals(parentPath, o.parentPath)
        && Objects.equals(parentUid, o.parentUid)
        && Objects.equals(parentUrl, o.parentUrl)
        && Objects.equals(path, o.path)
        && Objects.equals(portalType, o.portalType)
        && Objects.equals(reviewState, o.reviewState)
        && Objects.equals(start, o.start)
        && Objects.equals(tags, o.tags)
        && Objects.equals(title, o.title)
        && Objects.equals(uid, o.uid)
        && Objects.equals(url, o.url);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = result * 31 + Objects.hashCode(analyst);
    result = result * 31 + Arrays.hashCode(allowedRolesAndUsers);
    result = result * 31 + Objects.hashCode(analysisRequestTemplates);
    result = result * 31 + Objects.hashCode(apiUrl);
    result = result * 31 + Objects.hashCode(author);
    result = result * 31 + Objects.hashCode(created);
    result = result * 31 + Objects.hashCode(description);
    result = result * 31 + Objects.hashCode(effective);
    result = result * 31 + Objects.hashCode(end);
    result = result * 31 + Objects.hashCode(excludeFromNav);
    result = result * 31 + Objects.hashCode(expires);
    result = result * 31 + Objects.hashCode(clientId);
    result = result * 31 + Objects.hashCode(country);
    result = result * 31 + Objects.hashCode(district);
    result = result * 31 + Objects.hashCode(doctorId);
    result = result * 31 + Objects.hashCode(getDoctorUID);
    result = result * 31 + Objects.hashCode(province);
    result = result * 31 + Objects.hashCode(id);
    result = result * 31 + Objects.hashCode(isFolderish);
    result = result * 31 + Objects.hashCode(location);
    result = result * 31 + Objects.hashCode(modified);
    result = result * 31 + Objects.hashCode(parentId);
    result = result * 31 + Objects.hashCode(parentPath);
    result = result * 31 + Objects.hashCode(parentUid);
    result = result * 31 + Objects.hashCode(parentUrl);
    result = result * 31 + Objects.hashCode(path);
    result = result * 31 + Objects.hashCode(portalType);
    result = result * 31 + Objects.hashCode(reviewState);
    result = result * 31 + Objects.hashCode(start);
    result = result * 31 + Objects.hashCode(tags);
    result = result * 31 + Objects.hashCode(title);
    result = result * 31 + Objects.hashCode(uid);
    result = result * 31 + Objects.hashCode(url);
    return result;
  }

  @Override
  public String toString() {
    return "Client" + "{" +
            " " + "analyst" + "=" + "'" + analyst + "'" +
            "," + " " + "allowedRolesAndUsers" + "=" + "'" + Arrays.toString(allowedRolesAndUsers) + "'" +
            "," + " " + "analysisRequestTemplates" + "=" + "'" + analysisRequestTemplates + "'" +
            "," + " " + "apiUrl" + "=" + "'" + apiUrl + "'" +
            "," + " " + "author" + "=" + "'" + author + "'" +
            "," + " " + "created" + "=" + "'" + created + "'" +
            "," + " " + "description" + "=" + "'" + description + "'" +
            "," + " " + "effective" + "=" + "'" + effective + "'" +
            "," + " " + "end" + "=" + "'" + end + "'" +
            "," + " " + "excludeFromNav" + "=" + "'" + excludeFromNav + "'" +
            "," + " " + "expires" + "=" + "'" + expires + "'" +
            "," + " " + "clientId" + "=" + "'" + clientId + "'" +
            "," + " " + "country" + "=" + "'" + country + "'" +
            "," + " " + "district" + "=" + "'" + district + "'" +
            "," + " " + "doctorId" + "=" + "'" + doctorId + "'" +
            "," + " " + "getDoctorUID" + "=" + "'" + getDoctorUID + "'" +
            "," + " " + "province" + "=" + "'" + province + "'" +
            "," + " " + "id" + "=" + "'" + id + "'" +
            "," + " " + "isFolderish" + "=" + "'" + isFolderish + "'" +
            "," + " " + "location" + "=" + "'" + location + "'" +
            "," + " " + "modified" + "=" + "'" + modified + "'" +
            "," + " " + "parentId" + "=" + "'" + parentId + "'" +
            "," + " " + "parentPath" + "=" + "'" + parentPath + "'" +
            "," + " " + "parentUid" + "=" + "'" + parentUid + "'" +
            "," + " " + "parentUrl" + "=" + "'" + parentUrl + "'" +
            "," + " " + "path" + "=" + "'" + path + "'" +
            "," + " " + "portalType" + "=" + "'" + portalType + "'" +
            "," + " " + "reviewState" + "=" + "'" + reviewState + "'" +
            "," + " " + "start" + "=" + "'" + start + "'" +
            "," + " " + "tags" + "=" + "'" + tags + "'" +
            "," + " " + "title" + "=" + "'" + title + "'" +
            "," + " " + "uid" + "=" + "'" + uid + "'" +
            "," + " " + "url" + "=" + "'" + url + "'" + "}";
  }
}
