package com.xmu.web;

import com.alibaba.fastjson.JSON;
import com.xmu.pojo.Idle;
import com.xmu.pojo.PageBean;
import com.xmu.service.IdleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@MultipartConfig
@WebServlet(urlPatterns ="/idleServlet/*")
public class IdleServlet extends BaseServlet{
    //根据闲置号查询上架的闲置
    public void getReleasedIdleByIdleNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idleNumber= request.getParameter("idleNumber");
        IdleService idleService =new IdleService();
        Idle idle = idleService.selectReleasedByIdleNumber(idleNumber);
        String jsonString= JSON.toJSONString(idle);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    //查询所有上架的闲置
    public void getAllReleasedIdles(HttpServletRequest request, HttpServletResponse response) throws IOException{
        IdleService idleService =new IdleService();
        List<Idle> idles= idleService.selectAllReleased();
        String jsonString= JSON.toJSONString(idles);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    //添加闲置
    public void addIdle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        Idle idle =new Idle();
        idle.setStatus(1);
        idle.setUserID(request.getParameter("userID"));
        idle.setBrief(request.getParameter("brief"));
        idle.setCategory(request.getParameter("category"));
        idle.setBrand(request.getParameter("brand"));
        idle.setDescription(request.getParameter("description"));
        idle.setOldAndNew(request.getParameter("oldAndNew"));
        idle.setPrice(new BigDecimal(request.getParameter("price")));
        idle.setReleaseTime(request.getParameter("releaseTime"));
//        String dirPath2=getServletContext().getRealPath("/images/idles/");   //重启服务器就会图片消失啊！
        String dirPath1="D:\\IDEA\\tomcat-demo\\src\\main\\webapp\\images\\idles\\";   //先保存在磁盘里吧
        //获取所有表单提交的文件集合
        Collection<Part> parts = request.getParts();
        int number=0;  //用来记图片的个数
        for (Part part : parts) {
            //这里会传一些空文件，不理解？
            if(part.getContentType()==null||number>3){
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
                    idle.setImage1("/images/idles/"+fileName+"."+extension);
                    break;
                case 2:
                    idle.setImage2("/images/idles/"+fileName+"."+extension);
                    break;
                case 3:
                    idle.setImage3("/images/idles/"+fileName+"."+extension);
                    break;
            }
        }
        System.out.println(idle);
        IdleService idleService =new IdleService();
        idleService.add(idle);
        response.getWriter().write("success");
    }
    //根据闲置号删除闲置，用户删除
    public void invisibleIdleByIdleNumber(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String idleNumber= request.getParameter("idleNumber");
        IdleService idleService =new IdleService();
        idleService.invisibleByIdleNumber(idleNumber);
        response.getWriter().write("success");
    }
    //根据闲置号下架闲置
    public void offShelvesIdleByIdleNumber(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String idleNumber= request.getParameter("idleNumber");
        IdleService idleService =new IdleService();
        idleService.offShelvesByIdleNumber(idleNumber);
        response.getWriter().write("success");
    }
    //根据闲置号上架闲置
    public void upShelvesIdleByIdleNumber(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String idleNumber= request.getParameter("idleNumber");
        IdleService idleService =new IdleService();
        idleService.upShelvesByIdleNumber(idleNumber);
        response.getWriter().write("success");
    }
    //根据用户号查询该用户上架的所有闲置
    public void getReleasedIdlesByUserID(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String userID=request.getParameter("userID");
        IdleService idleService =new IdleService();
        List<Idle> idles= idleService.selectReleasedByUserID(userID);
        String jsonString= JSON.toJSONString(idles);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    //根据用户号查询该用户下架的所有闲置
    public void getOffShelvesIdlesByUserID(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String userID=request.getParameter("userID");
        IdleService idleService =new IdleService();
        List<Idle> idles= idleService.selectOffShelvesByUserID(userID);
        String jsonString= JSON.toJSONString(idles);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    //根据关键词查询所有相关的上架闲置
    public void getReleasedIdlesByKeyword(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        String keyword=request.getParameter("keyword");
        System.out.println(keyword);
        IdleService idleService =new IdleService();
        List<Idle> idles= idleService.selectReleasedByKeyword(keyword);
        String jsonString= JSON.toJSONString(idles);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    //根据关键词分页查询所有相关的闲置
    public void getReleasedIdlesByPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String keyword=request.getParameter("keyword");
        int pageSize= Integer.parseInt(request.getParameter("pageSize"));
        int currentPage=Integer.parseInt(request.getParameter("currentPage"));
        IdleService idleService=new IdleService();
        PageBean<Idle> idlePageBean= idleService.selectReleasedByPage(currentPage,pageSize,keyword);
        String jsonString= JSON.toJSONString(idlePageBean);
        System.out.println(jsonString);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
