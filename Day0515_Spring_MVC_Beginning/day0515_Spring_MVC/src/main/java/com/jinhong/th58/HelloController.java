package com.jinhong.th58;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class HelloController {
    @RequestMapping("/hello")
    @ResponseBody
    public Object hello(){
        Result ok = new Result(200, "OK", null);
        return ok;
    }
}
