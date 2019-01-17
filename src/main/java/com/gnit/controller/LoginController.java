package com.gnit.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gnit.pojo.User;
import com.gnit.service.LoginService;

@Controller
public class LoginController {

	@Resource
	LoginService loginService;

	@RequestMapping(value = "/login")
	public @ResponseBody String login(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			@RequestParam Map<String, String> paramter) {
		String account = paramter.get("account");
		String pwd = paramter.get("pwd");

		User InnerUser = loginService.login(account, pwd);

		JSONObject jsonObject = new JSONObject();

		if (null == InnerUser) { // 登录失败
			jsonObject.put("status", 0);
			jsonObject.put("message", "用户名或密码不对~");
			jsonObject.put("data", "");
			return jsonObject.toJSONString();
		}

		// 登录成功，将用户信息存在session中
		httpSession.setAttribute("user", InnerUser);

		// 登录成功返回信息
		jsonObject.put("status", 1);
		jsonObject.put("message", "登录成功~");
		jsonObject.put("data", JSON.toJSONString(InnerUser));

		return jsonObject.toJSONString();
	}

	@RequestMapping(value = "/logout")
	public @ResponseBody String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");// 情况session信息
		request.getSession().invalidate();// 清除 session 中的所有信息
		// 退出登录的时候清空cookie信息,cookie需要通过HttpServletRequest，HttpServletResponse获取
		Cookie[] cookie = request.getCookies();
		for (Cookie c : cookie) {
			if ("autoLogin".equals(c.getName())) {
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		// 退出成功返回信息
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("message", "已退出~");

		return jsonObject.toJSONString();
	}
}
