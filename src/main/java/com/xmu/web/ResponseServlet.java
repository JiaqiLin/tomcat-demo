package com.xmu.web;

import com.alibaba.fastjson.JSON;
import com.xmu.pojo.Response;
import com.xmu.pojo.User;
import com.xmu.service.ResponseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/responseServlet/*")
public class ResponseServlet extends BaseServlet{

    //根据需求号获取该需求的响应列表
    public void getResponsesByDemandNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String demandNumber=request.getParameter("demandNumber");
        ResponseService responseService =new ResponseService();
        List<Response> responses= responseService.selectByDemandNumber(demandNumber);  //根据需求号获取每一条需求的所有响应
        String jsonString= JSON.toJSONString(responses);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //添加响应
    public void addResponse(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        BufferedReader br=request.getReader();
        String params=br.readLine();
        Response response1= JSON.parseObject(params, Response.class);
        ResponseService responseService =new ResponseService();
        responseService.addResponse(response1);
        response.getWriter().write("success");
    }
}
