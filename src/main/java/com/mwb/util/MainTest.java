package com.mwb.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by MengWeiBo on 2017-03-16
 */
public class MainTest {
    private  URL url;
    private int responsecode;
    private  HttpURLConnection urlConnection;
    private BufferedReader reader;
    private String line;
    public static void main(String args[]) {

        URL url;

        int responsecode;

        HttpURLConnection urlConnection;

        BufferedReader reader;

        String line;

        try {

            //生成一个URL对象，要获取源代码的网页地址为：http://www.sina.com.cn

            url = new URL("https://detail.tmall.com/item.htm?spm=a3211.0-7235607.commonSinglePic_1490172553871_24.3.cZNCa7&id=41822129281&gccpm=13141887.300.2.subject-1008&sta=gccpm:13141887.300.2.subject-1008&track_params={%22gccpm%22:%2213141887.300.2.subject-1008%22}");
            url = new URL("https://item.taobao.com/item.htm?id=546128980616&ali_refid=a3_430676_1006:1108619018:N:%E7%94%B7%E5%A4%B9%E5%85%8B:b894320bfe3f73f89beec3bc62ea1ac6&ali_trackid=1_b894320bfe3f73f89beec3bc62ea1ac6&spm=a231o.7712113/a.1004.245.MItKnY&ali_refid=a3_430676_1006:1108619018:N:%E7%94%B7%E5%A4%B9%E5%85%8B:b894320bfe3f73f89beec3bc62ea1ac6&ali_trackid=1_b894320bfe3f73f89beec3bc62ea1ac6&spm=a231o.7712113/a.1004.245.MItKnY");
//            url = new URL("");
//            url = new URL("");
//            url = new URL("");
            String uul = "https://detail.tmall.com/item.htm?spm=a3211.0-7235607.commonSinglePic_1490172710949_24.3.cZNCa7&id=529213042920&rn=ba7b37c6cc996baa465ea750f4acb665&abbucket=3&gccpm=8062126.300.2.subject-1008&sta=gccpm:8062126.300.2.subject-1008&track_params={%22gccpm%22:%228062126.300.2.subject-1008%22}";
//            url = new URL(URLDecoder.decode(uul,"utf-8"));
//            url = new URL(uul);
//
////            //天猫
            url = new URL("https://detail.tmall.com/item.htm?spm=a3211.0-7235607.commonSinglePic_1490172710949_24.3.cZNCa7&id=529213042920&rn=ba7b37c6cc996baa465ea750f4acb665&abbucket=3&gccpm=8062126.300.2.subject-1008&sta=gccpm:8062126.300.2.subject-1008&track_params={%22gccpm%22:%228062126.300.2.subject-1008%22}");


            //打开URL

            urlConnection = (HttpURLConnection) url.openConnection();

            //获取服务器响应代码

            responsecode = urlConnection.getResponseCode();

            if (responsecode == 200) {

                //得到输入流，即获得了网页的内容

                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "gbk"));

                StringBuffer sb = new StringBuffer();
                while ((line = reader.readLine()) != null) {

                    System.out.println(line);
                    sb.append(line);
                }
//                System.out.println(HTMLParserUtils.getClickUrlFromReqUrl(sb.toString()));
//                System.out.println(HTMLParserUtils.getClickUrlListFromIFrame(sb.toString()));

            } else {

                System.out.println("获取不到网页的源码，服务器响应代码为：" + responsecode);

            }

        } catch (Exception e) {

            System.out.println("获取不到网页的源码,出现异常：" + e);

        }

    }


}
