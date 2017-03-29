package com.mwb.util;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public class Article {
    private String id;//id
    private String title;//标题
    private String content;//内容
    private String url;//当前章节url
    private String nextUrl;//下一章url
    /**
     *省略getter、setter方法
     */
    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", content="
                + content + ", url=" + url + ", nextUrl=" + nextUrl + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }
}
