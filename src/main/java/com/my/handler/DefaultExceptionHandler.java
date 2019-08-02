package com.my.handler;

import com.alibaba.fastjson.JSONObject;
import com.my.model.CommonResult;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author QinHe at 2019-07-29
 * 全局异常管理器
 */
@Component
public class DefaultExceptionHandler implements HandlerExceptionResolver {
    @Override
    //如果没实现HandlerExceptionResolver，需要加ExceptionHandler注解
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        e.printStackTrace();

        //判断是否为ajax请求
        if (request.getHeader("accept").contains("application/json") ||
                (request.getHeader("X-Requested-With") != null &&
                        request.getHeader("X-Requested-With").contains("XMLHttpRequest"))) {
            try {
                response.setContentType("text/json;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                CommonResult commonResult = CommonResult.failed(e.getMessage());
                PrintWriter writer = response.getWriter();
                writer.write(JSONObject.toJSONString(commonResult));
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        ModelAndView mv = new ModelAndView();
        request.setAttribute("message", e.getMessage());
        mv.setViewName("forward:/error.jsp");
//        mv.setViewName("redirect:/error.jsp");
        return mv;
    }
}
