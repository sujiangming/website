package com.gnit.controller;

import java.net.URLDecoder;
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
import com.gnit.pojo.FreeLearn;
import com.gnit.pojo.OrderSignUp;
import com.gnit.service.OrderSignUpService;
import com.gnit.utils.CharacterUtil;
import com.gnit.utils.JdryTime;
import com.gnit.utils.PageList;
import com.gnit.utils.Utils;

/**
 * 预约报名
 * 
 * @author JDRY-SJM
 *
 */
@Controller
@RequestMapping(value = "client/orderSignUp")
public class ClientOrderSignUpController {

	@Resource
	OrderSignUpService orderSignUpService;

	@RequestMapping(value = "saveOrUpdate", produces = "application/json; charset=utf-8")
	public @ResponseBody String saveOrUpdate(@RequestParam Map<String, Object> para, HttpServletRequest arg0,
			HttpServletResponse resp, HttpSession httpSession) throws Exception {
		resp.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
		Map<String, Object> result = new HashMap<String, Object>();
		String phoneNumber = arg0.getParameter("phoneNumber") == null ? "" : arg0.getParameter("phoneNumber");
		OrderSignUp sa = orderSignUpService.findObjectByPhoneNum(phoneNumber);
		if (null != sa && !"".equals(sa.getId())) {
			result.put("status", "0");
			result.put("message", "该手机号已经存在，请勿重复提交");
			return JSON.toJSONString(result);
		}

		String userName = arg0.getParameter("userName") == null ? "" : arg0.getParameter("userName");
		String courseName = arg0.getParameter("courseName") == null ? "" : arg0.getParameter("courseName");
		String netAddress = arg0.getParameter("netAddress") == null ? "" : arg0.getParameter("netAddress");
		String ip = Utils.getIpAddr(arg0);
		
		sa = new OrderSignUp();

		sa.setIp(ip);
		sa.setNetAddress(netAddress);
		sa.setPhoneNumber(phoneNumber);
		sa.setUserName(URLDecoder.decode(userName, "UTF-8"));
		sa.setCourseName(URLDecoder.decode(courseName, "UTF-8"));
		sa.setCreateTime(JdryTime.getDayHourMinBySec(new Date().getTime()));
		try {
			orderSignUpService.saveOrUpdate(sa);
			result.put("status", "1");
			result.put("message", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "0");
			result.put("message", "保存失败");
		}
		return JSON.toJSONString(result);
	}
}
