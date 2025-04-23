package zw.gov.mohcc.lims.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;

public interface Page<T> {
  @JsonProperty("_runtime")
  BigDecimal getRuntime();

  @JsonProperty("_runtime")
  void setRuntime(BigDecimal runtime);

  Page<T> withRuntime(BigDecimal runtime);

  @JsonProperty("count")
  Integer getCount();

  @JsonProperty("count")
  void setCount(Integer count);

  Page<T> withCount(Integer count);

  @JsonProperty("items")
  List<T> getItems();

  @JsonProperty("items")
  void setItems(List<T> items);

  Page<T> withItems(List<T> items);

  @JsonProperty("next")
  String getNext();

  @JsonProperty("next")
  void setNext(String next);

  Page<T> withNext(String next);

  @JsonProperty("page")
  Integer getPage();

  @JsonProperty("page")
  void setPage(Integer page);

  Page<T> withPage(Integer page);

  @JsonProperty("pages")
  Integer getPages();

  @JsonProperty("pages")
  void setPages(Integer pages);

  Page<T> withPages(Integer pages);

  @JsonProperty("pagesize")
  Integer getPagesize();

  @JsonProperty("pagesize")
  void setPagesize(Integer pagesize);

  Page<T> withPagesize(Integer pagesize);

  @JsonProperty("previous")
  String getPrevious();

  @JsonProperty("previous")
  void setPrevious(String previous);

  Page<T> withPrevious(String previous);

  @JsonProperty("url")
  String getUrl();

  @JsonProperty("url")
  void setUrl(String url);

  Page<T> withUrl(String url);
}
