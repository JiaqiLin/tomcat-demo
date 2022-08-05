package com.xmu.web;

import com.alibaba.fastjson.JSON;
import com.xmu.pojo.*;
import com.xmu.service.DemandService;
import com.xmu.service.IdleService;
import com.xmu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@MultipartConfig
@WebServlet(urlPatterns ="/userServlet/*")
public class UserServlet extends BaseServlet {

    //根据闲置号查询闲置发布用户
    public void getUserByIdleNumber(HttpServletRequest request,HttpServletResponse response)throws IOException{
        String idleNumber= request.getParameter("idleNumber");
        IdleService idleService =new IdleService();
        Idle idle = idleService.selectReleasedByIdleNumber(idleNumber);
        if(idle !=null)
        {
            UserService userService=new UserService();
            User user=userService.selectByUserID(idle.getUserID());
            String jsonString= JSON.toJSONString(user);
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(jsonString);
        }
        else{
            response.getWriter().write("");
        }
    }
    //根据需求号查询需求发布用户
    public void getUserByDemandNumber(HttpServletRequest request,HttpServletResponse response)throws IOException{
        String demandNumber= request.getParameter("demandNumber");
        System.out.println(demandNumber);
        DemandService demandService=new DemandService();
        Demand demand=demandService.selectReleasedByDemandNumber(demandNumber);
        if(demand!=null)
        {
            UserService userService=new UserService();
            User user=userService.selectByUserID(demand.getUserID());
            String jsonString= JSON.toJSONString(user);
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(jsonString);
        }
        else{
            response.getWriter().write("");
        }
    }
    //获取根据用户号获取用户名
    public void getUserVerifyStatus(HttpServletRequest request,HttpServletResponse response)throws IOException{
        UserService userService=new UserService();
        User user= userService.selectByUserID(request.getParameter("userID"));
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(String.valueOf(user.getVerifyStatus()));
    }
    //添加用户
    public void addUser(HttpServletRequest request,HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("UTF-8");
        BufferedReader br=request.getReader();
        String params=br.readLine();
        User user= JSON.parseObject(params, User.class);
        UserService userService=new UserService();
        if(userService.selectByUserIDInSchoolRoll(user.getUserID())==null)
        {
            response.getWriter().write("errorUserID");
        }
        else if(userService.selectByUserIDAndRegisterStatusInSchoolRoll(user.getUserID())==null)
        {
            response.getWriter().write("haveRegistered");
        }
        else
        {
            userService.add(user);
            response.getWriter().write("success");
        }

    }
    //根据用户号获取用户
    public void getUserByUserID(HttpServletRequest request,HttpServletResponse response)throws IOException{
        String userID= request.getParameter("userID");
        UserService userService=new UserService();
        User user=userService.selectByUserID(userID);
        String jsonString= JSON.toJSONString(user);
        System.out.println(jsonString);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }
    //更新用户信息
    public void updateUserInformation(HttpServletRequest request,HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("UTF-8");
        BufferedReader br=request.getReader();
        String params=br.readLine();
        User user= JSON.parseObject(params, User.class);
        UserService userService=new UserService();
        userService.updateUserInformation(user);
        response.getWriter().write("success");

    }
    //获取所有用户
    public void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException{
        UserService userService =new UserService();
        List<User> users= userService.selectAllUsers();
        String jsonString= JSON.toJSONString(users);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    //上传认证图片
    public void addVerifyApply(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userID=request.getParameter("userID");
        String dirPath1="D:\\IDEA\\tomcat-demo\\src\\main\\webapp\\images\\studentCards\\";   //先保存在磁盘里吧
        String studentCardFrontURL="";
        String studentCardContraryURL="";
        //获取所有表单提交的文件集合
        Collection<Part> parts = request.getParts();
        int number=0;  //用来记图片的个数
        for (Part part : parts) {
            if(number>=2){
                break;
            }
            number++;
            String fileNameIncludeExtension = part.getSubmittedFileName();//获取文件名
            String extension=fileNameIncludeExtension.split("[.]")[1];
            String fileName= UUID.randomUUID().toString().replaceAll("-", "");
            String path1= dirPath1 + fileName+"."+extension;
            part.write(path1);//将文件写入磁盘，文件上传成功
            switch (number){
                case 1:
                    studentCardFrontURL="/images/studentCards/"+fileName+"."+extension;
                    break;
                case 2:
                    studentCardContraryURL="/images/studentCards/"+fileName+"."+extension;
                    break;
            }
        }
        UserService userService =new UserService();
        userService.addVerifyApply(userID,studentCardFrontURL,studentCardContraryURL);
        response.getWriter().write("success");
    }
    //查看上传的申请
    public void getVerifyInfoByUserID(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String userID=request.getParameter("userID");
        UserService userService =new UserService();
        VerifyInfo verifyInfo= userService.selectVerifyInfoByUserID(userID);
        String jsonString= JSON.toJSONString(verifyInfo);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    /**
     * 根据用户ID删除用户
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteByUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // 获取删除对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为user
        User user = JSON.parseObject(params,User.class);

        //2、调用service查询
        UserService userService =new UserService();
        userService.deleteByUser(user);
    }
    // 分页查询
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //1、接收当前页码 和 每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2、调用service查询
        UserService userService =new UserService();
        PageBean<User> userPageBean = userService.selectByPage(currentPage, pageSize);

        //3、转换为JSON
        String jsonString= JSON.toJSONString(userPageBean);

        //4、写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    //分页条件查询
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //1、接收当前页码 和 每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        request.setCharacterEncoding("UTF-8");
        // 获取查询条件对象啊对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为user
        User user = JSON.parseObject(params,User.class);
        System.out.println(user);

        //2、调用service查询
        UserService userService =new UserService();
        PageBean<User> userPageBean = userService.selectByPageAndCondition(currentPage,pageSize,user);

        //3、转换为JSON
        String jsonString= JSON.toJSONString(userPageBean);

        //4、写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    //查询所有待审核信息
    public void selectAllVerifyInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{

        UserService userService =new UserService();
        List<VerifyInfo> verifyInfos = userService.selectAllVerifyInfo();
        String jsonString= JSON.toJSONString(verifyInfos);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    //通过审核
    public void verifyPass(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        //转为verifyInfo
        VerifyInfo verifyInfo = JSON.parseObject(params,VerifyInfo.class);
        UserService userService =new UserService();
        userService.verifyPass(verifyInfo);
        response.getWriter().write("success");
    }
    //未通过审核
    public void verifyNotPass(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String userID=request.getParameter("userID");
        UserService userService =new UserService();
        userService.verifyNotPass(userID);
        response.getWriter().write("success");
    }
    /**
     * 根据用户ID批量删除用户
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteByIDs(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        //接收数据 【“123”，“345”】
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        System.out.println(params);

        List<User> users =JSON.parseArray(params,User.class);

        System.out.println(users.size());
        for(User user : users){
            System.out.println(user);
        }
        //调用service服务
        UserService userService =new UserService();
        userService.deleteByIDs(users);

        //响应成功标识
        response.getWriter().write("success");
    }
    }

