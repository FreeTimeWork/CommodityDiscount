package com.mwb.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Fangchen.chai on 2017/3/30.
 */
public class SecondQuartz {
    public static Logger logger = LoggerFactory.getLogger(SecondQuartz.class);
    public  static int i = 100000;
    public void TestMethod(){
        System.out.println(i++);
    }
}
