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
import com.alibaba.fastjson.JSONObject;
import com.gnit.pojo.News;
import com.gnit.service.NewsService;
import com.gnit.utils.CharacterUtil;
import com.gnit.utils.JdryTime;
import com.gnit.utils.PageList;

@Controller
@RequestMapping(value = "client/news")
public class ClientNewsController {

	JSONObject json = new JSONObject();
	
	@Resource
	NewsService newsService;

	/**
	 * 查询新闻列表
	 * 
	 * @param para
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list", produces = "application/json; charset=utf-8")
	public @ResponseBody String selectList(@RequestParam Map<String, Object> para, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
		int currentPage = para.get("page") == null ? 1 : 	Integer.parseInt(para.get("page").toString());// 每页行数
		int showCount = para.get("rows") == null ? 10 : Integer.parseInt( para.get("rows").toString());
		//类型1是学校新闻 2 是行业新闻
		int type = para.get("type") == null ? 1 : Integer.parseInt( para.get("type").toString());
		PageList page = new PageList();
		page.setPage(currentPage);
		page.setRows(showCount);
		List<News> list = newsService.selectAllByPage(page,type);
		if (null == list || list.size() == 0) {
			json.put("status", 0);
			json.put("message", "当前暂时没有新闻~");
			json.put("data", "");
			return json.toJSONString();
		}
		json.put("status", 1);
		json.put("message", "已查询到新闻数据");
		json.put("data", JSON.toJSONString(list));
		return json.toJSONString();
	}
}
