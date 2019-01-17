package com.gnit.controller;

import java.util.Date;
import java.util.HashMap;
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
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gnit.pojo.FreeLearn;
import com.gnit.pojo.User;
import com.gnit.service.FreeLearnService;
import com.gnit.utils.CharacterUtil;
import com.gnit.utils.JdryTime;
import com.gnit.utils.PageList;

@Controller
@RequestMapping(value = "freeLearn")
public class FreeLearnController {

	@Resource
	FreeLearnService freeLearnService;

	/**
	 * 查询所有数据
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
		int currentPage = request.getParameter("offset") == null ? 1 : Integer.parseInt(request.getParameter("offset"));// 每页行数
		int showCount = request.getParameter("limit") == null ? 10 : Integer.parseInt(request.getParameter("limit"));
		String search = request.getParameter("search") == null ? "" : request.getParameter("search");
		search = CharacterUtil.getUTF_8String(search);
		if (currentPage != 0) {// 获取页数
			currentPage = currentPage / showCount;
		}
		currentPage += 1;
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("search", search);
		parameter.put("currentPage", currentPage);
		parameter.put("showCount", showCount);
		PageList<FreeLearn> list = freeLearnService.selectList(parameter);
		long count = list.getTotal();
		String b = JSON.toJSONString(list.getList(), SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullStringAsEmpty);
		String r = "{\"total\":" + count + ",\"rows\":" + b + "}"; // 服务端分页必须返回total和rows,rows为每页记录
		return r;
	}

	@RequestMapping(value = "saveOrUpdate", produces = "application/json; charset=utf-8")
	public @ResponseBody String saveOrUpdate(@RequestParam Map<String, Object> para, HttpServletRequest arg0,
			HttpServletResponse resp, HttpSession httpSession) throws Exception {
		String userName = arg0.getParameter("userName") == null ? "" : arg0.getParameter("userName");
		String phoneNumber = arg0.getParameter("phoneNumber") == null ? "" : arg0.getParameter("phoneNumber");
		String id = arg0.getParameter("id") == null ? "" : arg0.getParameter("id");
		String netAddress = arg0.getParameter("netAddress") == null ? "" : arg0.getParameter("netAddress");
		String ip = arg0.getParameter("ip") == null ? "" : arg0.getParameter("ip");
		String effectiveStatus = arg0.getParameter("effectiveStatus") == null ? ""
				: arg0.getParameter("effectiveStatus");
		String handleStatus = arg0.getParameter("handleStatus") == null ? "" : arg0.getParameter("handleStatus");
		String remarks = arg0.getParameter("remarks") == null ? "" : arg0.getParameter("remarks");

		Map<String, Object> result = new HashMap<String, Object>();
		FreeLearn sa = freeLearnService.findObjectById(id);
		if (sa != null) {
			User user = (User) httpSession.getAttribute("user");
			sa.setHandlerName(user.getUserName());
			sa.setHandleTime(JdryTime.getDayHourMinBySec(new Date().getTime()));
			sa.setEffectiveStatus(effectiveStatus);
			sa.setHandleStatus(handleStatus);
			sa.setRemarks(remarks);
			try {
				freeLearnService.saveOrUpdate(sa);
				result.put("status", "1");
				result.put("message", "处理成功");
			} catch (Exception e) {
				e.printStackTrace();
				result.put("status", "0");
				result.put("message", "处理失败");
			}
			return JSON.toJSONString(result);
		}

		sa = new FreeLearn();
		sa.setIp(ip);
		sa.setNetAddress(netAddress);
		sa.setPhoneNumber(phoneNumber);
		sa.setUserName(userName);
		sa.setCreateTime(JdryTime.getDayHourMinBySec(new Date().getTime()));

		try {
			freeLearnService.saveOrUpdate(sa);
			result.put("status", "1");
			result.put("message", "保存成功");
		} catch (Exception e) {
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
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			freeLearnService.delete(id);
			result.put("status", "1");
			result.put("message", "删除成功");
		} catch (Exception e) {
			result.put("status", "0");
			result.put("message", "删除失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(result);
	}
}
