package com.my.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author QinHe at 2019-08-02
 */
public class ShiroPermsFilter extends AuthorizationFilter {

    /**
     * 重写perm验证方法
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;//页面在spring-shiro.xml中设置的权限标识
        if (perms == null || perms.length == 0) {
            return true;
        }
        for (String perm : perms) {
            if (subject.isPermitted(perm)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是ajax请求，返回json
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //获取当前用户
        Subject subject = this.getSubject(httpServletRequest, httpServletResponse);
        //判断是否已经认证

        if (subject.getPrincipal() == null) {
            //没有认证则重定向到登录页面
            this.saveRequestAndRedirectToLogin(httpServletRequest, httpServletResponse);
        } else {
            //认证过了
            String requestedWith = httpServletRequest.getHeader("X-Requested-With");
            if (StringUtils.isNotEmpty(requestedWith) &&
                    StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定格式数据
                //是ajax请求
                httpServletResponse.setContentType("text/json;charset=UTF-8");//设置响应头
                //返回json 数据，告知无权限
                httpServletResponse.getWriter().write("{\"success\":false,\"message\":\"对不起，你没有这个权限!\",\"errorCode\":-10001}");
            } else {//如果是普通请求进行重定向
                //不是ajax请求
                String unauthorizedUrl = this.getUnauthorizedUrl();
                if (StringUtils.isNotEmpty(unauthorizedUrl)) {
                    WebUtils.issueRedirect(httpServletRequest, httpServletResponse, unauthorizedUrl);
                } else {
                    WebUtils.toHttp(httpServletResponse).sendError(401);
                }
            }

        }
        return false;
    }
}
