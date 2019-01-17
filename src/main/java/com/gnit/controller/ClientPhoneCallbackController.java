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
import com.gnit.pojo.FreeLearn;
import com.gnit.pojo.PhoneCallback;
import com.gnit.service.PhoneCallbackService;
import com.gnit.utils.CharacterUtil;
import com.gnit.utils.JdryTime;
import com.gnit.utils.PageList;
import com.gnit.utils.Utils;

/**
 * 电话回访
 * @author JDRY-SJM
 *
 */
@Controller
@RequestMapping(value="client/phoneCallback")
public class ClientPhoneCallbackController {
	
	@Resource
	PhoneCallbackService phoneCallbackService;
	
	@RequestMapping(value = "saveOrUpdate", produces = "application/json; charset=utf-8")
	public @ResponseBody String saveOrUpdate(@RequestParam Map<String, Object> para, HttpServletRequest arg0,
			HttpServletResponse resp, HttpSession httpSession) throws Exception {
		resp.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
		Map<String, Object> result = new HashMap<String, Object>();
		String phoneNumber = arg0.getParameter("phoneNumber") == null ? "" : arg0.getParameter("phoneNumber");
		PhoneCallback sa = phoneCallbackService.findObjectByPhoneNum(phoneNumber);
		if (null != sa && !"".equals(sa.getId())) {
			result.put("status", "0");
			result.put("message", "该手机号已经存在，请勿重复提交");
			return JSON.toJSONString(result);
		}
		
		String netAddress = arg0.getParameter("netAddress") == null ? "" : arg0.getParameter("netAddress");
		String ip = Utils.getIpAddr(arg0);
		
		sa = new PhoneCallback();
		sa.setIp(ip);
		sa.setNetAddress(netAddress);
		sa.setPhoneNumber(phoneNumber);
		sa.setCreateTime(JdryTime.getDayHourMinBySec(new Date().getTime()));
		try{
			phoneCallbackService.saveOrUpdate(sa);
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
