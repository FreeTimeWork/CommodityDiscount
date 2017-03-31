package com.mwb.controller.frontend;

import com.mwb.dao.model.comm.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontend")
public class FrontendController {
    private static final Log LOG = Log.getLog(FrontendController.class);

    @RequestMapping("/index")
    public String index() {

        return "";
    }

}
