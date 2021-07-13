package com.atguigu.crowd.mvc.config;


import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.exception.LoginAcctAlreadyInUserException;
import com.atguigu.crowd.exception.LoginAcctAlreadyInUserUpdateException;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@ControllerAdvice表示当前类使一个基于注解的异常处理器类
@ControllerAdvice
public class CrowdExceptionResolver {

    @ExceptionHandler(value = LoginAcctAlreadyInUserUpdateException.class)
    public ModelAndView LoginAcctAlreadyInUserException(
            LoginAcctAlreadyInUserUpdateException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String viewName = "system-error";
        return commonResolve(exception,request,response,viewName);
    }


    @ExceptionHandler(value = LoginAcctAlreadyInUserException.class)
    public ModelAndView LoginAcctAlreadyInUserException(
            LoginAcctAlreadyInUserException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String viewName = "admin-add";
        return commonResolve(exception,request,response,viewName);
    }

    // 触发登录失败异常，则继续返回登陆页面
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolverLoginFailedException(
            LoginFailedException exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        return commonResolve(exception,request,response,viewName);
    }

    // 整理出的不同异常的可重用代码
    private ModelAndView commonResolve(
        //触发的异常，此处借助多态
        Exception exception,
        //客户器端的请求
        HttpServletRequest request,
        //服务端的响应
        HttpServletResponse response,
        //指定普通页面请求时，去的错误页面
        String viewName) throws IOException {
        boolean judgeRequestType = CrowdUtil.judgeRequestType(request);
        if (judgeRequestType) {
            //if判断-是json请求
            ResultEntity<Object> failed = ResultEntity.failed(exception.getMessage());
            //创建Gson对象
            Gson gson = new Gson();
            //将ResultEntity对象转换成json格式
            String json = gson.toJson(failed);
            //通过原生servlet的response传回异常内容
            response.getWriter().write(json);
            //此时只需要返回null（因为是通过json格式返回数据）
            return null;
        } else {
            //if判断-是普通页面请求
            //创建ModelAndView对象
            ModelAndView modelAndView = new ModelAndView();
            //设置触发异常跳转的页面（会自动被视图解析器加上前后缀）
            modelAndView.setViewName(viewName);
            //将异常信息加入
            modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);
            //返回设置完成的ModelAndView
            return modelAndView;

        }
    }

}
