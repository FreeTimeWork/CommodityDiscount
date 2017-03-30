package com.mwb.service;

import com.mwb.dao.model.Product;
import com.mwb.dao.model.Store;
import com.mwb.dao.model.StoreType;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
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
    private String urlStr;
    private Parser parser;

    public ParserService(String urlStr) {
        this.urlStr = urlStr;
        setParser(urlStr);
    }

    private void setParser(String urlStr) {
        StringBuilder sb = new StringBuilder();
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
                    System.out.println(line);
                }
            } else {
                LOG.error("获取不到网页的源码，服务器响应代码为：" + responseCode);
                throw new RuntimeException();
            }

            parser = Parser.createParser(sb.toString(), "UTF-8");

        } catch (Exception e) {
            LOG.error("获取不到网页的源码,出现异常：" + e);
        }
    }

    private void setTaoBaoStoreScore(Product product) throws Exception {
        if (product.getStore() == null) {
            Store store = new Store();
            product.setStore(store);
        }

        Store store = product.getStore();

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
    }

    private void setTmallStoreScore(Product product) throws Exception {

        Store store = product.getStore();

        TagNameFilter filter = new TagNameFilter("span");

        NodeList nodes = parser.extractAllNodesThatMatch(filter);

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
    }

    private void setTmallProductName(Product product) throws Exception {
        TagNameFilter filter = new TagNameFilter("title");
        NodeList nodes = parser.extractAllNodesThatMatch(filter);

        TitleTag tn = (TitleTag) nodes.elementAt(0);
        String name = tn.getChild(0).getText().trim();

        if (StringUtils.isBlank(name)) {
            return;
        }

        if (name.contains("-tmall.com")) {
            String urlSpilts[] = name.split("-tmall.com");
            name = urlSpilts[0];
        } else if (name.contains("-淘宝网")) {
            String urlSpilts[] = name.split("-淘宝网");
            name = urlSpilts[0];
        }
        product.setName(name);

        LOG.info("name :{}", product.getName());
    }

    private void setTaoBaoProductPrice(Product product) throws Exception {
        TagNameFilter filter = new TagNameFilter("li");
        NodeList nodes = parser.extractAllNodesThatMatch(filter);

        TitleTag tn = (TitleTag) nodes.elementAt(0);
        String name = tn.getChild(0).getText().trim();

        if (StringUtils.isBlank(name)) {
            return;
        }

        if (name.contains("-tmall.com")) {
            String urlSpilts[] = name.split("-tmall.com");
            name = urlSpilts[0];
        } else if (name.contains("-淘宝网")) {
            String urlSpilts[] = name.split("-淘宝网");
            name = urlSpilts[0];
        }
        product.setName(name);

        LOG.info("name :{}", product.getName());
    }

    //淘宝 天猫 商品url,店铺类型
    private void setProductUrl(Product product) {
        String id = "";
        if (product.getStore() == null) {
            Store store = new Store();
            product.setStore(store);
        }
        if (urlStr.contains("&id=")) {
            String urlSpilts[] = urlStr.split("&id=");
            id = urlSpilts[urlSpilts.length - 1].split("&")[0];
            product.getStore().setType(StoreType.TMALL);
        } else if (urlStr.contains("?id=")) {
            String urlSpilts[] = urlStr.split("\\?id=");
            id = urlSpilts[urlSpilts.length - 1].split("&")[0];
            product.getStore().setType(StoreType.TAOBAO);
        } else {
            product.getStore().setType(StoreType.OTHER);
        }
        product.setProductId(id);

        LOG.info("ProductId :{}", id);
    }

    public static void main(String[] args) throws Exception {
        String url = new String("https://item.taobao.com/item.htm?id=546128980616&ali_refid=a3_430676_1006:1108619018:N:%E7%94%B7%E5%A4%B9%E5%85%8B:b894320bfe3f73f89beec3bc62ea1ac6&ali_trackid=1_b894320bfe3f73f89beec3bc62ea1ac6&spm=a231o.7712113/a.1004.245.MItKnY&ali_refid=a3_430676_1006:1108619018:N:%E7%94%B7%E5%A4%B9%E5%85%8B:b894320bfe3f73f89beec3bc62ea1ac6&ali_trackid=1_b894320bfe3f73f89beec3bc62ea1ac6&spm=a231o.7712113/a.1004.245.MItKnY");
//        url = "https://detail.tmall.com/item.htm?spm=a222r.8295401.7232537034.2.HkcdqB&acm=lb-zebra-223892-1815928.1003.4.1549547&id=544701354389&scm=1003.4.lb-zebra-223892-1815928.ITEM_544701354389_1549547&sku_properties=5919063:6536025";
//        url = "https://detail.tmall.com/item.htm?spm=a220o.1000855.1998025129.1.sgOfpO&abtest=_AB-LR32-PR32&pvid=2d85f8f3-f8e2-4f33-80db-704408bcfb6f&pos=1&abbucket=_AB-M32_B15&acm=03054.1003.1.1539344&id=530414781951&scm=1007.12144.78696.23864_42343&sku_properties=5919063:6536025;12304035:116177;122216431:27772";
       url ="https://uland.taobao.com/coupon/edetail?activityId=38ab6234272945bf912906efbe1d293e&pid=mm_54519761_6274140_21634502&itemId=545129332754&src=mlz_mlztk&dx=1&ali_trackid=2:mm_54519761_6274140_21634502:1490882477_3k9_1290267276";
//        ParserService parserService = new ParserService(url);
        Product product = new Product();

//        parserService.setTmallProductName(product);
//        parserService.setTaoBaoStoreScore(product);
//        parserService.setProductUrl(product);
//        System.out.println(product.toString());


        getContent(url);
    }

    //http://blog.csdn.net/sang1203/article/details/51286221
    public static String getContent(String url){
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
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
