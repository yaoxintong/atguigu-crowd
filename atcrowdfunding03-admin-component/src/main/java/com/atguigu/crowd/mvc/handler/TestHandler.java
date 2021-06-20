package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestHandler {
    @Autowired
    private AdminService adminService;

    private Logger logger =LoggerFactory.getLogger(TestHandler.class);
    @RequestMapping("send/array.html")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array){

        for(Integer number: array){
            System.out.println("number"+number);
        }
        return "target  ";
    }

    @RequestMapping("/test/ssm.html")
    public String testSsm(ModelMap modelMap, HttpServletRequest request){

        boolean judgeResult = CrowdUtil.judgeRequestType(request);

        logger.info("judgeResult="+judgeResult);
        List<Admin> adminList = adminService.getAll();

        modelMap.addAttribute("adminList",adminList);
        String a = null;
        System.out.println(a.length());

        return "target";
    }

}
