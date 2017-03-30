package com.mwb.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public class UtilMethod {

    /**
     * 根据url获取Document对象
     * @param url 小说章节url
     * @return Document对象
     */
    public static Document getDocument(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(5000).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return doc;
    }
    /**
     * 根据获取的Document对象找到章节标题
     * @param doc
     * @return 标题
     */
    public static String getTitle(Document doc){
        return doc.getElementById("J_Title").text();
    }

    /**
     * 根据获取的Document对象找到小说内容
     * @param doc
     * @return 内容
     */
    public static String getContent(Document doc){
        if(doc.getElementById("content") != null){
            return doc.getElementById("content").text();
        }else{
            return null;
        }

    }
    /**
     * 根据获取的Document对象找到下一章的Url地址
     * @param doc
     * @return 下一章Url
     */
    public static String getNextUrl(Document doc){
        Element ul = doc.select("ul").first();
        String regex = "<li><a href=\"(.*?)\">下一页<\\/a><\\/li>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ul.toString());
        Document nextDoc = null;
        if (matcher.find()) {
            nextDoc = Jsoup.parse(matcher.group());
            Element href = nextDoc.select("a").first();
            return "http://www.bxwx.org/b/5/5131/" + href.attr("href");
        }else{
            return null;
        }


    }

    /**
     * 根据url获取id
     * @param url
     * @return id
     */
    public static String getId(String url){
        String urlSpilts[] = url.split("/");
        return (urlSpilts[urlSpilts.length - 1]).split("\\.")[0];
    }

    /**
     * 根据小说的Url获取一个Article对象
     * @param url
     * @return
     */
    public static Article getArticle(String url){
        Article article = new Article();
        article.setUrl(url);
        Document doc = getDocument(url);
//        article.setId(getId(url));
        article.setTitle(getTitle(doc));
//        article.setNextUrl(getNextUrl(doc));
//        article.setContent(getContent(doc));
        return article;
    }

    public static void main(String[] args) {
        String uul = "https://detail.tmall.com/item.htm?spm=a3211.0-7235607.commonSinglePic_1490172710949_24.3.cZNCa7&id=529213042920&rn=ba7b37c6cc996baa465ea750f4acb665&abbucket=3&gccpm=8062126.300.2.subject-1008&sta=gccpm:8062126.300.2.subject-1008&track_params={%22gccpm%22:%228062126.300.2.subject-1008%22}";

//        System.out.println(getArticle("http://www.bxwx.org/b/5/5131/832882.html"));
        System.out.println(getArticle(uul));
    }

}
