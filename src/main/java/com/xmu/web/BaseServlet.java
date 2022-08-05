package com.xmu.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    {
        //获取请求路径
        String uri=req.getRequestURI();
        //获取最后一段路径，方法名
        int index=uri.lastIndexOf('/');
        String methodName=uri.substring(index+1);
        //执行方法
        Class<?extends BaseServlet> cls=this.getClass(); //获取具体Servlet的字节码对象Class
         //获取方法Method对象
        try {
                Method  method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                //执行方法
                method.invoke(this,req,resp);

            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }


}
