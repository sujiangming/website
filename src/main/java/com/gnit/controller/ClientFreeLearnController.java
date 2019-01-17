package com.gnit.controller;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.gnit.pojo.FreeLearn;
import com.gnit.service.FreeLearnService;
import com.gnit.utils.CharacterUtil;
import com.gnit.utils.JdryTime;
import com.gnit.utils.PageList;
import com.gnit.utils.Utils;

/**
 * 免费试听
 * 
 * @author JDRY-SJM
 *
 */
@Controller
@RequestMapping(value = "client/freeLearn")
public class ClientFreeLearnController {

	@Resource
	FreeLearnService freeLearnService;

	@RequestMapping(value = "saveOrUpdate", produces = "application/json; charset=utf-8")
	public @ResponseBody String saveOrUpdate(@RequestParam Map<String, Object> para, HttpServletRequest request,
			HttpServletResponse resp, HttpSession httpSession) throws Exception {
		resp.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
		Map<String, Object> result = new HashMap<String, Object>();
		String phoneNumber = request.getParameter("phoneNumber") == null ? "" : request.getParameter("phoneNumber");
		// 先查询数据库中是否存在这个手机号
		FreeLearn sa = freeLearnService.findObjectByPhoneNum(phoneNumber);
		if (null != sa && !"".equals(sa.getId())) {
			result.put("status", "0");
			result.put("message", "该手机号已经存在，请勿重复提交");
			return JSON.toJSONString(result);
		}

		String userName = request.getParameter("userName") == null ? "" : request.getParameter("userName");
		String netAddress = request.getParameter("netAddress") == null ? "" : request.getParameter("netAddress");
		String ip = Utils.getIpAddr(request);

		sa = new FreeLearn();
		sa.setIp(ip);
		sa.setNetAddress(netAddress);
		sa.setPhoneNumber(phoneNumber);
		sa.setUserName(URLDecoder.decode(userName, "UTF-8"));
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
}
