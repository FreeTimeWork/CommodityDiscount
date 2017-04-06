package com.mwb.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontend")
public class FrontendController {

    @RequestMapping("/login.html")
    public String login() {

        return "login";
    }

    @RequestMapping("/jurisdiction.html")
    public String index() {

        return "jurisdiction";
    }

    @RequestMapping("/report.html")
    public String report() {

        return "report";
    }

    @RequestMapping("/commodity.html")
    public String commodity() {

        return "commodity";
    }

    @RequestMapping("/detail.html")
    public String detail() {

        return "detail";
    }

    @RequestMapping("/emloyeeInfo.html")
    public String emloyeeInfo() {

        return "emloyeeInfo";
    }
    @RequestMapping("/finance.html")
    public String finance() {

        return "finance";
    }

    @RequestMapping("/zhou-1.html")
    public String zhou() {

        return "zhou-1";
    }

}
