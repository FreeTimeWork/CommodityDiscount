package com.mwb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.json.JSONArray;
import org.json.JSONObject;

//import cn.crxy.maven.Spider.domain.Page;
//import cn.crxy.maven.Spider.utils.HtmlUtil;
//import cn.crxy.maven.Spider.utils.PageUtil;
/**
 * Created by MengWeiBo on 2017-03-30
 */
public class ProcessImp {
//    public void process(Page page) {
//
//        HtmlCleaner htmlCleaner = new HtmlCleaner();
//        TagNode rootNode = htmlCleaner.clean(page.getContent());
//        try {
//            String goodName = HtmlUtil.getText(rootNode, "//*[@id='name']/h1");// 得到商品名称
//            page.setGoodName(goodName);
//
//            String picUrl = HtmlUtil.getAttributeByName(rootNode, "//*[@id='spec-n1']/img","src");// 获取商品图片url
//            page.setPicUrl("http:"+picUrl);
//
//            // 获取商品号
//            String url = page.getDataUrl();
//            Pattern compile = Pattern.compile("http://item.jd.com/([0-9]+).html");
//            Matcher matcher = compile.matcher(url);
//            String goodid = null;
//            if (matcher.find()) {
//                goodid = matcher.group(1);
//                page.setGoodId(goodid);
//            }
//
//            // 获取商品价格
//            // 得到价格的json格式[{"id":"J_1593512","p":"17988.00","m":"17989.00"}]
//            String pricejson = PageUtil
//                    .getContent("http://p.3.cn/prices/get?skuid=J_" + goodid);
//            JSONArray jsonArray = new JSONArray(pricejson);
//            JSONObject jsonObject = jsonArray.getJSONObject(0);
//            String price = jsonObject.getString("p");
//            page.setPrice(price);
//
//            // 获取规格参数
//            // *[@id="product-detail-2"]
//            // *[@id="product-detail-2"]/table/tbody/tr[1]/th
//            Object[] evaluateXPath = rootNode
//                    .evaluateXPath("//*[@id='product-detail-2']/table/tbody/tr");
//            JSONArray jsonArray2 = new JSONArray();
//            if(evaluateXPath != null && evaluateXPath.length > 0){
//                for(Object object : evaluateXPath){
//                    TagNode tagnode = (TagNode) object;
//                    if(!"".equals(tagnode.getText().toString().trim())){//有数据
//
//                        Object[] evaluateXPath2 = tagnode.evaluateXPath("/th");
//                        JSONObject jsonObject2 = new JSONObject();
//                        if(evaluateXPath2.length>0){
//                            TagNode tagNode2 = (TagNode) evaluateXPath2[0];
//                            jsonObject2.put("name", tagNode2.getText().toString());
//                            jsonObject2.put("value", "");
//                        }else {
//
//                            Object[] evaluateXPath3 = tagnode.evaluateXPath("/td");
//                            TagNode tagNode1 = (TagNode) evaluateXPath3[0];
//                            TagNode tagNode2 = (TagNode) evaluateXPath3[1];
//                            jsonObject2.put("name", tagNode1.getText().toString());
//                            jsonObject2.put("value", tagNode2.getText().toString());
//                        }
//                        jsonArray2.put(jsonObject2);
//                    }
//                }
//            }
//            page.setParam("spec",jsonArray2.toString());
//        } catch (XPatherException e) {
//            e.printStackTrace();
//        }
//    }
}

