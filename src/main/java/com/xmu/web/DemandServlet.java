package com.xmu.web;

import com.alibaba.fastjson.JSON;
import com.xmu.pojo.Demand;
import com.xmu.pojo.Idle;
import com.xmu.pojo.PageBean;
import com.xmu.service.DemandService;
import com.xmu.service.IdleService;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@MultipartConfig
@WebServlet(urlPatterns ="/demandServlet/*")
public class DemandServlet extends BaseServlet{

    //获取所有上架的需求
    public void getAllReleasedDemands(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //String userID=request.getParameter("userID");
        //要加一个用户推荐逻辑，这里暂时先没加，根据用户ID来定
        DemandService demandService=new DemandService();
        List<Demand> demands=demandService.selectAllReleased();
        String jsonString= JSON.toJSONString(demands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //根据用户号获取该用户上架的所有需求
    public void getDemandsByUserID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userID=request.getParameter("userID");
        DemandService demandService=new DemandService();
        List<Demand> demands= demandService.selectByUserID(userID);  //获取该用户所有需求
        String jsonString= JSON.toJSONString(demands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //根据需求号获取一个上架的需求
    public void getReleasedDemandByDemandNumber(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String demandNumber=request.getParameter("demandNumber");
        DemandService demandService=new DemandService();
        Demand demand= demandService.selectReleasedByDemandNumber(demandNumber);
        String jsonString= JSON.toJSONString(demand);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //添加需求
    public void addDemand(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        Demand demand =new Demand();
        demand.setStatus(1);
        demand.setUserID(request.getParameter("userID"));
        demand.setBrief(request.getParameter("brief"));
        demand.setCategory(request.getParameter("category"));
        demand.setUpperPrice(new BigDecimal(request.getParameter("upperPrice")));
        demand.setDescription(request.getParameter("description"));
        demand.setLowerPrice(new BigDecimal(request.getParameter("lowerPrice")));
        demand.setReleaseTime(request.getParameter("releaseTime"));
//        String dirPath2=getServletContext().getRealPath("/images/idles/");   //重启服务器就会图片消失啊！
        String dirPath1="D:\\IDEA\\tomcat-demo\\src\\main\\webapp\\images\\demands\\";   //先保存在磁盘里吧
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
                    demand.setImage1("/images/demands/"+fileName+"."+extension);
                    break;
                case 2:
                    demand.setImage2("/images/demands/"+fileName+"."+extension);
                    break;
                case 3:
                    demand.setImage3("/images/demands/"+fileName+"."+extension);
                    break;
            }

        }
        System.out.println(demand);
        DemandService demandService =new DemandService();
        demandService.add(demand);
        response.getWriter().write("success");
    }

    //根据需求号删除需求，用户端不可见，管理端可见
    public void invisibleDemandByDemandNumber(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String demandNumber= request.getParameter("demandNumber");
        DemandService demandService =new DemandService();
        demandService.invisibleByDemandNumber(demandNumber);
        response.getWriter().write("success");
    }

    //根据需求号下架需求
    public void offShelvesDemandByDemandNumber(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String demandNumber= request.getParameter("demandNumber");
        DemandService demandService =new DemandService();
        demandService.offShelvesByDemandNumber(demandNumber);
        response.getWriter().write("success");
    }

    //根据需求号上架需求
    public void upShelvesDemandByDemandNumber(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String demandNumber= request.getParameter("demandNumber");
        DemandService demandService =new DemandService();
        demandService.upShelvesByDemandNumber(demandNumber);
        response.getWriter().write("success");
    }

    //根据用户号获取其上架的所有需求
    public void getReleasedDemandsByUserID(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String userID=request.getParameter("userID");
        DemandService demandService =new DemandService();
        List<Demand> demands= demandService.selectReleasedByUserID(userID);
        String jsonString= JSON.toJSONString(demands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //根据用户号获取其下架的所有需求
    public void getOffShelvesDemandsByUserID(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String userID=request.getParameter("userID");
        DemandService demandService =new DemandService();
        List<Demand> demands= demandService.selectOffShelvesByUserID(userID);
        String jsonString= JSON.toJSONString(demands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //根据关键词查询所有上架的需求
    public void getReleasedDemandsByKeyword(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String keyword=request.getParameter("keyword");
        DemandService demandService =new DemandService();
        List<Demand> demands= demandService.selectReleasedByKeyword(keyword);
        String jsonString= JSON.toJSONString(demands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //根据关键词分页查询上架的需求+上架的总需求数
    public void getReleasedDemandsByPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String keyword=request.getParameter("keyword");
        int pageSize= Integer.parseInt(request.getParameter("pageSize"));
        int currentPage=Integer.parseInt(request.getParameter("currentPage"));
        DemandService demandService=new DemandService();
        PageBean<Demand> demandPageBean= demandService.selectReleasedByPage(currentPage,pageSize,keyword);
        String jsonString= JSON.toJSONString(demandPageBean);
        System.out.println(jsonString);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
