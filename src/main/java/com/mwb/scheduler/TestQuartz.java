package com.mwb.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Fangchen.chai on 2017/3/30.
 */
public class TestQuartz {
    public static Logger logger = LoggerFactory.getLogger(TestQuartz.class);
    public  static int i = 0;
    public void TestMethod(){
        System.out.println(i++);
    }
}
