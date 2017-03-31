package com.mwb.controller.frontend;

import com.mwb.controller.api.ContentType;
import com.mwb.controller.frontend.api.DataResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MengWeiBo on 2017-03-31
 */

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @ResponseBody
    @RequestMapping(value = "/resource", produces = ContentType.APPLICATION_JSON_UTF8)
    public DataResponse getData() {

        return new DataResponse();
    }
}
