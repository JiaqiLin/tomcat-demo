package com.xmu.web;

import com.xmu.pojo.Administrator;
import com.xmu.pojo.User;
import com.xmu.service.AdministratorService;
import com.xmu.service.UserService;
import com.xmu.util.MD5FactoryUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/loginServlet",urlPatterns ="/loginServlet" )
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userID=request.getParameter("userID");
        String password= request.getParameter("password") ;
        //判断账号和密码是否正确（用户）
        UserService userService=new UserService();
        User user=userService.selectByUserIDAndPassword(userID,password);
        //判断账号和密码是否正确（管理员）
        AdministratorService administratorService=new AdministratorService();
        Administrator administrator=administratorService.login(request.getParameter("userID"),request.getParameter("password"));
        if(user!=null)
        {
            response.getWriter().write("user");
        }else if(administrator!=null)
        {
            response.getWriter().write("administrator");
        }else
        {
            response.getWriter().write("false");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request,response);

    }
}
