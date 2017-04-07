package com.mwb.service;

import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductPicture;
import com.mwb.dao.model.product.Store;
import com.mwb.dao.model.product.StoreType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-03-29
 */

public class ParserService {
    private static final Logger LOG = LoggerFactory.getLogger(ParserService.class);

    private String urlStr;

    public ParserService(String urlStr) {
        this.urlStr = urlStr;
    }

    public Product grabProduct() {
        LOG.info("grabProduct urlStr:{}", urlStr);

        Product product = new Product();
        product.setUrl(urlStr);
        setProductId(product);

        if (urlStr.contains("tmall.com")) {
            setTmallStoreScore(product);
            setTmallProductPictures(product);
        } else {
            setTaoBaoStoreScore(product);
            setTaoBaoProductPictures(product);
        }

        return product;
    }

    private void setProductId(Product product) {
        String id = "";

        if (urlStr.contains("&id=")) {
            String urlSpilts[] = urlStr.split("&id=");
            id = urlSpilts[urlSpilts.length - 1].split("&")[0];
        } else if (urlStr.contains("?id=")) {
            String urlSpilts[] = urlStr.split("\\?id=");
            id = urlSpilts[urlSpilts.length - 1].split("&")[0];
        }
        product.setProductId(id);
    }

    private void setParser(String urlStr) {
        try {
            StringBuilder sb = new StringBuilder();

            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //获取服务器响应代码
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                //得到输入流，即获得了网页的内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "gbk"));
                String line = reader.readLine();
                while (StringUtils.isNotBlank(line)) {
                    sb.append(line);
                }
            } else {
                LOG.error("获取不到网页的源码，服务器响应代码为：" + responseCode);
                throw new RuntimeException();
            }
            Parser parser = Parser.createParser(getContent(urlStr), "UTF-8");

        } catch (Exception e) {
            LOG.error("获取不到网页的源码,出现异常：" + e);
        }
    }

    private void setTaoBaoStoreScore(Product product) {
        Store store = product.getStore();

        if (store == null) {
            store = new Store();
            product.setStore(store);
        }

        try {
            String html = getContent(urlStr);
            if (StringUtils.isBlank(html)) {
                return;
            }
            Parser parser = Parser.createParser(html, "UTF-8");
            TagNameFilter filter = new TagNameFilter("dd");

            NodeList nodes = parser.extractAllNodesThatMatch(filter);

            for (int i = 0; i < nodes.size(); ++i) {
                TagNode tn = (TagNode) nodes.elementAt(i);
                String clkUrl = tn.getAttribute("class");

                if ("tb-rate-higher".equals(clkUrl)) {
                    LinkTag node = (LinkTag) tn.getChildren().extractAllNodesThatMatch(new TagNameFilter("a")).elementAt(0);
                    String score = node.getChild(0).getText().trim();

                    TagNode tagNode = (TagNode) tn.getParent().getChildren().extractAllNodesThatMatch(new TagNameFilter("dt")).elementAt(0);
                    String title = tagNode.toString();

                    if (title.contains("描述")) {
                        store.setDescriptionScore(new BigDecimal(score));
                    } else if (title.contains("服务")) {
                        store.setServiceScore(new BigDecimal(score));
                    } else if (title.contains("物流")) {
                        store.setSpeedScore(new BigDecimal(score));
                    }
                }
            }
        } catch (ParserException e) {
            LOG.error("setTmallStoreScore is err:{}", e);
        }
    }

    private void setTmallStoreScore(Product product) {

        Store store = product.getStore();
        if (store == null) {
            store = new Store();
            product.setStore(store);
        }
        String html = getContent(urlStr);
        if (StringUtils.isBlank(html)) {
            return;
        }
        Parser parser = Parser.createParser(html, "UTF-8");

        TagNameFilter filter = new TagNameFilter("span");
        NodeList nodes = null;
        try {
            nodes = parser.extractAllNodesThatMatch(filter);
            for (int i = 0; i < nodes.size(); ++i) {
                TagNode tn = (TagNode) nodes.elementAt(i);
                String clkUrl = tn.getAttribute("class");

                if ("shopdsr-score-con".equals(clkUrl)) {
                    String score = ((Span) tn).getChild(0).getText().trim();

                    TagNode tagNode = (TagNode) tn.getParent().getParent();
                    String title = tagNode.toString();
                    if (title.contains("描 述")) {
                        store.setDescriptionScore(new BigDecimal(score));
                    } else if (title.contains("服 务")) {
                        store.setServiceScore(new BigDecimal(score));
                    } else if (title.contains("物 流")) {
                        store.setSpeedScore(new BigDecimal(score));
                    }
                }
            }
        } catch (ParserException e) {
            LOG.error("setTmallStoreScore is err:{}", e);
        }
    }

    private void setTmallProductPictures(Product product) {
        try {
            List<ProductPicture> pictures = product.getPictures();
            if (CollectionUtils.isEmpty(pictures)) {
                pictures = new ArrayList<>();
                product.setPictures(pictures);
            }
            String html = getContent(urlStr);
            if (StringUtils.isBlank(html)) {
                return;
            }
            Parser parser = Parser.createParser(html, "UTF-8");

            TagNameFilter filter = new TagNameFilter("ul");
            NodeList nodes = parser.extractAllNodesThatMatch(filter);
            for (int i = 0; i < nodes.size(); i++) {
                TagNode tn = (TagNode) nodes.elementAt(i);
                String clkUrl = tn.getAttribute("class");

                if ("tb-thumb tm-clear ".equals(clkUrl)) {
                    NodeList imageNodes = tn.getChildren();
                    for (int j = 0; j < imageNodes.size(); j++) {
                        Node node = imageNodes.elementAt(j);
                        if (node instanceof Tag) {
                            TagNameFilter imageFilter = new TagNameFilter("a");
                            LinkTag linkTag = (LinkTag) node.getChildren().extractAllNodesThatMatch(imageFilter).elementAt(0);
                            NodeList nodeList = linkTag.getChildren();
                            for (int z = 0; z < nodeList.size(); z++) {
                                Node imageNode = nodeList.elementAt(z);
                                if (imageNode instanceof ImageTag) {
                                    String imageUrl = ((ImageTag) imageNode).getImageURL();

                                    ProductPicture productPicture = new ProductPicture();
                                    productPicture.setProduct(product);
                                    productPicture.setUrl(imageUrl);
                                    pictures.add(productPicture);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("setTmallProductPictures is err e:{}", e);
        }
    }

    private void setTaoBaoProductPictures(Product product) {
        try {
            List<ProductPicture> pictures = product.getPictures();
            if (CollectionUtils.isEmpty(pictures)) {
                pictures = new ArrayList<>();
                product.setPictures(pictures);
            }
            String html = getContent(urlStr);
            if (StringUtils.isBlank(html)) {
                return;
            }
            Parser parser = Parser.createParser(html, "UTF-8");

            TagNameFilter filter = new TagNameFilter("div");
            NodeList nodes = parser.extractAllNodesThatMatch(filter);
            for (int i = 0; i < nodes.size(); i++) {
                TagNode tn = (TagNode) nodes.elementAt(i);
                String clkUrl = tn.getAttribute("class");

                if ("tb-pic tb-s50".equals(clkUrl)) {
                    NodeList imageNodes = tn.getChildren();
                    for (int j = 0; j < imageNodes.size(); j++) {
                        Node node = imageNodes.elementAt(j);
                        if (node instanceof LinkTag) {
                            NodeList nodeList = node.getChildren();
                            for (int z = 0; z < nodeList.size(); z++) {
                                Node imageNode = nodeList.elementAt(z);
                                if (imageNode instanceof Tag) {
                                    String imageUrl = ((Tag) imageNode).getAttribute("data-src");

                                    ProductPicture productPicture = new ProductPicture();
                                    productPicture.setProduct(product);
                                    productPicture.setUrl(imageUrl);
                                    pictures.add(productPicture);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("setTmallProductPictures is err e:{}", e);
        }
    }

    private String getContent(String url) {
        HttpClientBuilder custom = HttpClients.custom();//创建httpclient
        //通过构建器构建一个httpclient对象，可以认为是获取到一个浏览器对象
        CloseableHttpClient build = custom.build();
        //把url封装到get请求中
        HttpGet httpGet = new HttpGet(url);
        String content = null;
        try {
            //使用client执行get请求,获取请求的结果，请求的结果被封装到response中
            CloseableHttpResponse response = build.execute(httpGet);
            //表示获取返回的内容实体对象
            HttpEntity entity = response.getEntity();
            //解析实体中页面的内容，返回字符串形式
            content = EntityUtils.toString(entity);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
