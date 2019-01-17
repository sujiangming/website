package com.gnit.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gnit.pojo.News;
import com.gnit.service.NewsService;
import com.gnit.utils.CharacterUtil;
import com.gnit.utils.JdryTime;
import com.gnit.utils.PageList;

@Controller
@RequestMapping(value="news")
public class NewsController {
	
	@Resource
	NewsService newsService;
	
	/**
	 * 查询新闻列表
	 * @param para
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="list",produces = "application/json; charset=utf-8")
	public @ResponseBody String selectList(@RequestParam Map<String, Object> para,HttpServletRequest request,
								 HttpServletResponse response,HttpSession session) throws Exception{
		int currentPage = request.getParameter("offset") == null ? 1 : Integer.parseInt(request.getParameter("offset"));// 每页行数
		int showCount = request.getParameter("limit") == null ? 10 : Integer.parseInt(request.getParameter("limit"));
		String search = request.getParameter("search") == null ? "" : request.getParameter("search");
		String type = request.getParameter("type") == null ? "" : request.getParameter("type");
		search = CharacterUtil.getUTF_8String(search);
		if (currentPage != 0) {// 获取页数
			currentPage = currentPage / showCount;
		}
		currentPage += 1;
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("search", search);
		parameter.put("type", type);
		parameter.put("currentPage", currentPage);
		parameter.put("showCount", showCount);
		PageList<News> list = newsService.selectNewsList(parameter);
		long count = list.getTotal();
		String b = JSON.toJSONString(list.getList());
		String r = "{\"total\":" + count + ",\"rows\":" + b + "}"; // 服务端分页必须返回total和rows,rows为每页记录
		return r;
	}
	
	@RequestMapping(value = "saveOrUpdate", produces = "application/json; charset=utf-8")
	public @ResponseBody String saveOrUpdate(@RequestParam Map<String, Object> para, HttpServletRequest arg0,
			HttpServletResponse resp, HttpSession httpSession) throws Exception {
		String title = arg0.getParameter("title") == null ? "" : arg0.getParameter("title");
		String content = arg0.getParameter("content") == null ? "" : arg0.getParameter("content");
		String id = arg0.getParameter("id") == null ? "" : arg0.getParameter("id");
		String author = arg0.getParameter("author") == null ? "" : arg0.getParameter("author");
		int type = Integer.parseInt(arg0.getParameter("type") == null ? "" : arg0.getParameter("type"));
		Map<String, Object> result = new HashMap<String, Object>();
		News sa = new News();
		if(!id.isEmpty()){
			sa = newsService.findObjectById(id);
		}
		sa.setContent(content);
		sa.setCreateTime(JdryTime.getFullTimeBySec(new Date().getTime()));
		sa.setType(type);
		sa.setAuthor(author);
		sa.setTitle(title);
		sa.setIsPubish(new Boolean(false));
		sa.setPubishStatus("未发布");
		sa.setCreateTime(JdryTime.getDayHourMinBySec(new Date().getTime()));
		try{
			newsService.updateOrInsert(sa);
			result.put("status", "1");
			result.put("message", "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result.put("status", "0");
			result.put("message", "保存失败");
		}
		return JSON.toJSONString(result);
	}
	
	@RequestMapping(value = "delete", produces = "application/json; charset=utf-8")
	public @ResponseBody String delete(@RequestParam Map<String, Object> para, HttpServletRequest arg0,
			HttpServletResponse resp, HttpSession httpSession) throws Exception {
		String id = arg0.getParameter("id") == null ? "" : arg0.getParameter("id");
		Map<String,Object> result = new HashMap<String,Object>();
		try{
			newsService.delete(id);
			result.put("status", "1");
			result.put("message", "删除成功");
		}catch(Exception e){
			result.put("status", "0");
			result.put("message", "删除失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(result);
	}
	
	@RequestMapping(value = "publish", produces = "application/json; charset=utf-8")
	public @ResponseBody String publish(@RequestParam Map<String, Object> para, HttpServletRequest arg0,
			HttpServletResponse resp, HttpSession httpSession) throws Exception {
		String id = arg0.getParameter("id") == null ? "" : arg0.getParameter("id");
		Map<String, Object> result = new HashMap<String, Object>();
		News sa = newsService.findObjectById(id);
		sa.setPublishTime(JdryTime.getFullTimeBySec(new Date().getTime()));
		sa.setIsPubish(new Boolean(true));
		sa.setPubishStatus("已发布");
		try{
			newsService.updateOrInsert(sa);
			result.put("status", "1");
			result.put("message", "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result.put("status", "0");
			result.put("message", "保存失败");
		}
		return JSON.toJSONString(result);
	}
	
}
