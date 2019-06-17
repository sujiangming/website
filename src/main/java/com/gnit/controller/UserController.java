package com.gnit.controller;

import java.net.URLDecoder;
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
import com.gnit.pojo.FreeLearn;
import com.gnit.pojo.User;
import com.gnit.service.UserService;
import com.gnit.utils.CharacterUtil;
import com.gnit.utils.PageList;

@Controller
@RequestMapping(value = "user")
public class UserController {
	@Resource
	UserService userService;

	/**
	 * @category 查询所有数据
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
		PageList<User> list = userService.selectList(parameter);
		long count = list.getTotal();
		String b = JSON.toJSONString(list.getList());
		String r = "{\"total\":" + count + ",\"rows\":" + b + "}"; // 服务端分页必须返回total和rows,rows为每页记录
		return r;
	}

	@RequestMapping(value = "saveOrUpdate", produces = "application/json; charset=utf-8")
	public @ResponseBody String saveOrUpdate(@RequestParam Map<String, Object> para, HttpServletRequest arg0,
			HttpServletResponse resp, HttpSession httpSession) throws Exception {
		String userName = arg0.getParameter("userName") == null ? "" : arg0.getParameter("userName");
		String userMobile = arg0.getParameter("userMobile") == null ? "" : arg0.getParameter("userMobile");
		String id = arg0.getParameter("id") == null ? "" : arg0.getParameter("id");
		String userGender = arg0.getParameter("userGender") == null ? "" : arg0.getParameter("userGender");
		String userRole = arg0.getParameter("userRole") == null ? "" : arg0.getParameter("userRole");

		Map<String, Object> result = new HashMap<String, Object>();
		User sa = new User();
		if (!id.isEmpty()) {
			sa = userService.getUserById(id);
		}

		String encode = "UTF-8";
		String role = URLDecoder.decode(userRole, encode);
		sa.setUserName(URLDecoder.decode(userName, encode));
		sa.setUserGender(URLDecoder.decode(userGender, encode));
		sa.setUserMobile(userMobile);
		sa.setUserRole(role);
		sa.setUserPwd("123456");//设置默认密码为123456
		sa.setIsManager(new Boolean(false));// 新增人员默认值都为false

		if ("咨询师".equals(role)) {
			sa.setIsManager(new Boolean(false));
		} else {
			sa.setIsManager(new Boolean(true));
		}
		
		try {
			userService.saveOrUpdate(sa);
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
			userService.delete(id);
			result.put("status", "1");
			result.put("message", "删除成功");
		} catch (Exception e) {
			result.put("status", "0");
			result.put("message", "删除失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(result);
	}

	@RequestMapping(value = "reset", produces = "application/json; charset=utf-8")
	public @ResponseBody String reset(@RequestParam Map<String, Object> para, HttpServletRequest arg0,
			HttpServletResponse resp, HttpSession httpSession) throws Exception {
		String id = arg0.getParameter("id") == null ? "" : arg0.getParameter("id");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			userService.reset(id);
			result.put("status", "1");
			result.put("message", "重置密码成功");
		} catch (Exception e) {
			result.put("status", "0");
			result.put("message", "重置密码失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(result);
	}

	/**
	 * 用户更新自己的密码
	 * 
	 * @param para
	 * @param arg0
	 * @param resp
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updatePwdByUser", produces = "application/json; charset=utf-8")
	public @ResponseBody String updatePwdByUser(@RequestParam Map<String, Object> para, HttpServletRequest arg0,
			HttpServletResponse resp, HttpSession httpSession) throws Exception {
		String mobel = arg0.getParameter("mobile") == null ? "" : arg0.getParameter("mobile");
		String newPassword = arg0.getParameter("newPassword") == null ? "" : arg0.getParameter("newPassword");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			userService.updateNewPwd(newPassword, mobel);
			result.put("status", "1");
			result.put("message", "修改密码成功");
		} catch (Exception e) {
			result.put("status", "0");
			result.put("message", "修改密码失败");
			e.printStackTrace();
		}
		return JSON.toJSONString(result);
	}
}
