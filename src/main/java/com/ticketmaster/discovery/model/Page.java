package com.ticketmaster.discovery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@NoArgsConstructor
public class Page<T> {

  @JsonProperty("_embedded")
  private T embedded;

  @JsonProperty("_links")
  private PageLinks links;

  @JsonProperty("page")
  private PageInfo info;

  // 202205 GP >>>>
  public T getEmbedded() {
    return embedded;
  }
  public PageLinks getLinks() {
    return links;
  }
  public PageInfo getInfo() {
    return info;
  }
  // 202205 GP <<<<

  @Getter
  @NoArgsConstructor
  public static class PageLinks {

    private Link self;
    private Link next;
    @JsonProperty("prev")
    private Link previous;

    // 202205 GP

    public Link getNext() {
      return next;
    }

    public Link getPrevious() {
      return previous;
    }
  }

  @Getter
  @NoArgsConstructor
  public static class PageInfo {

    @JsonProperty("size")
    private Integer pageSize;
    private Integer totalElements;
    private Integer totalPages;
    @JsonProperty("number")
    private Integer currentPage;
  }

  @Getter
  @NoArgsConstructor
  public static class Link {

    private static final String TEMPLATE_PATTERN = "\\{(.*?)\\}";

    private String href;
    private String rel;
    private Boolean templated;

    public String getRelativeHref() {
      if (templated != null && templated) {
        return href.replaceAll(TEMPLATE_PATTERN, "");
      } else {
        return href;
      }
    }
  }

}
