package com.mwb.service;

import com.mwb.dao.model.Product;
import com.mwb.dao.model.Store;
import com.mwb.dao.model.StoreType;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public class ParserService {
    private static final Logger LOG = LoggerFactory.getLogger(ParserService.class);

    private URL url;
    private int responseCode;
    private HttpURLConnection urlConnection;
    private BufferedReader reader;
    private String line;
    private String htmlBody;
    private String urlStr;

    public ParserService(String urlStr) {
        this.urlStr = urlStr;
        this.htmlBody = getHtmlBody(urlStr);
    }

    private String getHtmlBody(String urlStr) {
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();

            //获取服务器响应代码
            responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                //得到输入流，即获得了网页的内容
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "gbk"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                LOG.error("获取不到网页的源码，服务器响应代码为：" + responseCode);

                throw new RuntimeException();
            }
        }catch (Exception e) {
            LOG.error("获取不到网页的源码,出现异常：" + e);
        }
        return sb.toString();
    }

    public void setStoreScore(Product product) throws Exception {
        if (product.getStore() == null) {
            Store store = new Store();
            product.setStore(store);
        }

        Store store = product.getStore();

        Parser parser = Parser.createParser(htmlBody, "UTF-8");
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
                }else if (title.contains("服务")) {
                    store.setServiceScore(new BigDecimal(score));
                }else if (title.contains("物流")) {
                    store.setSpeedScore(new BigDecimal(score));
                }
            }
        }
    }

    public void setProductUrl(Product product) {
        String id = "";
        if (urlStr.contains("&id=")) {
            String urlSpilts[] = urlStr.split("&id=");
            id = urlSpilts[urlSpilts.length - 1].split("&")[0];
            product.getStore().setType(StoreType.TMALL);
        } else if (urlStr.contains("?id=")) {
            String urlSpilts[] = urlStr.split("\\?id=");
            id = urlSpilts[urlSpilts.length - 1].split("&")[0];
            product.getStore().setType(StoreType.TAOBAO);
        }else {
            product.getStore().setType(StoreType.OTHER);
        }
        product.setProductId(id);
    }

    public static void main(String[] args) throws Exception {
        String url = new String("https://item.taobao.com/item.htm?id=546128980616&ali_refid=a3_430676_1006:1108619018:N:%E7%94%B7%E5%A4%B9%E5%85%8B:b894320bfe3f73f89beec3bc62ea1ac6&ali_trackid=1_b894320bfe3f73f89beec3bc62ea1ac6&spm=a231o.7712113/a.1004.245.MItKnY&ali_refid=a3_430676_1006:1108619018:N:%E7%94%B7%E5%A4%B9%E5%85%8B:b894320bfe3f73f89beec3bc62ea1ac6&ali_trackid=1_b894320bfe3f73f89beec3bc62ea1ac6&spm=a231o.7712113/a.1004.245.MItKnY");
        url = "https://detail.tmall.com/item.htm?spm=a222r.8295401.7232537034.2.HkcdqB&acm=lb-zebra-223892-1815928.1003.4.1549547&id=544701354389&scm=1003.4.lb-zebra-223892-1815928.ITEM_544701354389_1549547&sku_properties=5919063:6536025";
        ParserService parserService = new ParserService(url);
        Product product = new Product();

        parserService.setStoreScore(product);
        parserService.setProductUrl(product);
        System.out.println(product.toString());
    }
}
