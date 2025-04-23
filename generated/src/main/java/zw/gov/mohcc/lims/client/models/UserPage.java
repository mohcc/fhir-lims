package zw.gov.mohcc.lims.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class UserPage implements Page<User> {
  @JsonProperty("_runtime")
  private BigDecimal runtime;

  @JsonProperty("count")
  private Integer count;

  @JsonProperty("items")
  private List<User> items;

  @JsonProperty("next")
  private String next;

  @JsonProperty("page")
  private Integer page;

  @JsonProperty("pages")
  private Integer pages;

  @JsonProperty("pagesize")
  private Integer pagesize;

  @JsonProperty("previous")
  private String previous;

  @JsonProperty("url")
  private String url;

  @JsonProperty("_runtime")
  public BigDecimal getRuntime() {
    return this.runtime;
  }

  @JsonProperty("_runtime")
  public void setRuntime(BigDecimal runtime) {
    this.runtime = runtime;
  }

  public UserPage withRuntime(BigDecimal runtime) {
    this.runtime = runtime;
    return this;
  }

  @JsonProperty("count")
  public Integer getCount() {
    return this.count;
  }

  @JsonProperty("count")
  public void setCount(Integer count) {
    this.count = count;
  }

  public UserPage withCount(Integer count) {
    this.count = count;
    return this;
  }

  @JsonProperty("items")
  public List<User> getItems() {
    return this.items;
  }

  @JsonProperty("items")
  public void setItems(List<User> items) {
    this.items = items;
  }

  public UserPage withItems(List<User> items) {
    this.items = items;
    return this;
  }

  @JsonProperty("next")
  public String getNext() {
    return this.next;
  }

  @JsonProperty("next")
  public void setNext(String next) {
    this.next = next;
  }

  public UserPage withNext(String next) {
    this.next = next;
    return this;
  }

  @JsonProperty("page")
  public Integer getPage() {
    return this.page;
  }

  @JsonProperty("page")
  public void setPage(Integer page) {
    this.page = page;
  }

  public UserPage withPage(Integer page) {
    this.page = page;
    return this;
  }

  @JsonProperty("pages")
  public Integer getPages() {
    return this.pages;
  }

  @JsonProperty("pages")
  public void setPages(Integer pages) {
    this.pages = pages;
  }

  public UserPage withPages(Integer pages) {
    this.pages = pages;
    return this;
  }

  @JsonProperty("pagesize")
  public Integer getPagesize() {
    return this.pagesize;
  }

  @JsonProperty("pagesize")
  public void setPagesize(Integer pagesize) {
    this.pagesize = pagesize;
  }

  public UserPage withPagesize(Integer pagesize) {
    this.pagesize = pagesize;
    return this;
  }

  @JsonProperty("previous")
  public String getPrevious() {
    return this.previous;
  }

  @JsonProperty("previous")
  public void setPrevious(String previous) {
    this.previous = previous;
  }

  public UserPage withPrevious(String previous) {
    this.previous = previous;
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

  public UserPage withUrl(String url) {
    this.url = url;
    return this;
  }

  @Override
  public boolean equals(Object rhs) {
    if (rhs == this) return true;
    if (!(rhs instanceof UserPage o)) return false;
    return Objects.equals(runtime, o.runtime)
        && Objects.equals(count, o.count)
        && Objects.equals(items, o.items)
        && Objects.equals(next, o.next)
        && Objects.equals(page, o.page)
        && Objects.equals(pages, o.pages)
        && Objects.equals(pagesize, o.pagesize)
        && Objects.equals(previous, o.previous)
        && Objects.equals(url, o.url);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = result * 31 + Objects.hashCode(runtime);
    result = result * 31 + Objects.hashCode(count);
    result = result * 31 + Objects.hashCode(items);
    result = result * 31 + Objects.hashCode(next);
    result = result * 31 + Objects.hashCode(page);
    result = result * 31 + Objects.hashCode(pages);
    result = result * 31 + Objects.hashCode(pagesize);
    result = result * 31 + Objects.hashCode(previous);
    result = result * 31 + Objects.hashCode(url);
    return result;
  }

  @Override
  public String toString() {
    return "UserPage" + "{" +
            " " + "runtime" + "=" + "'" + runtime + "'" +
            "," + " " + "count" + "=" + "'" + count + "'" +
            "," + " " + "items" + "=" + "'" + items + "'" +
            "," + " " + "next" + "=" + "'" + next + "'" +
            "," + " " + "page" + "=" + "'" + page + "'" +
            "," + " " + "pages" + "=" + "'" + pages + "'" +
            "," + " " + "pagesize" + "=" + "'" + pagesize + "'" +
            "," + " " + "previous" + "=" + "'" + previous + "'" +
            "," + " " + "url" + "=" + "'" + url + "'" + "}";
  }
}
