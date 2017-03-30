package com.mwb.util;

import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public class HTMLParserUtils {
    private static final Logger LOG = LoggerFactory.getLogger(HTMLParserUtils.class);
    private HTMLParserUtils() {
    }

    public static String getClickUrlFromReqUrl(String htContent) {
        Parser parser = Parser.createParser(htContent, "UTF-8");
        TagNameFilter filter = new TagNameFilter("div");
        try {
            NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if (nodes.size() != 0) {
                LinkTag lt = (LinkTag) nodes.elementAt(0);
                return lt.getAttribute("href");
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
        return null;
    }

    public static String getClickUrlFromIFrame(String htContent) {
        Parser parser = Parser.createParser(htContent, "UTF-8");
        TagNameFilter filter = new TagNameFilter("iframe");
        try {
            NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if (nodes.size() != 0) {
                TagNode tn = (TagNode) nodes.elementAt(0);
                return tn.getAttribute("src");
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
        return null;
    }

    public static List<String> getClickUrlListFromIFrame(String htContent) {
        Parser parser = Parser.createParser(htContent, "UTF-8");
        TagNameFilter filter = new TagNameFilter("dd");

        try {
            NodeList nodes = parser.extractAllNodesThatMatch(filter);
            for(int i=0;i<nodes.size();++i){
                TagNode tn = (TagNode) nodes.elementAt(i);
                String clkUrl = tn.getAttribute("class");

                if ("tb-rate-higher".equals(clkUrl)){
                    LinkTag node =(LinkTag )tn.getChildren().extractAllNodesThatMatch(new TagNameFilter("a")).elementAt(0);
//
                    System.out.println(node.getChild(0).getText().trim());
                    TagNode tagNode = (TagNode) tn.getParent().getChildren().extractAllNodesThatMatch(new TagNameFilter("dt")).elementAt(0);
                    String textTag = tagNode.toString();
                    System.out.println(textTag);
                    if (textTag.contains("描述")){
                        System.out.println("1111111111111111111111111");
                    }
                }
            }
            return null;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
    public static String getClickUrlFromScript(String htContent) {
        Parser parser = Parser.createParser(htContent, "UTF-8");
        TagNameFilter filter = new TagNameFilter("script");
        String ret = null;
        try {
            NodeList nodes = parser.extractAllNodesThatMatch(filter);
            for (int i = 0; i < nodes.size(); ++i) {
                TagNode tn = (TagNode) nodes.elementAt(i);
                String str = tn.toString();
                if (str.indexOf("imgArray") >= 0) {
                    int start = str.indexOf("\\\"link\\\":\\\"");
                    if (start >= 0) {
                        String sub1 = str.substring(start + 11);
                        int end = sub1.indexOf("\\\",\\\"");
                        if (end >= 0) {
                            ret = sub1.substring(0, end);
                        } else {
                            ret = sub1;
                        }
                    }

                }
            }
            return ret;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}

