package com.gnit.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gnit.dao.impl.NewsDaoImpl;
import com.gnit.pojo.News;
import com.gnit.service.NewsService;
import com.gnit.utils.PageList;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	@Resource
	NewsDaoImpl newsDaoImpl;

	
	public PageList<News> selectNewsList(Map<String, Object> parameter) {
		return newsDaoImpl.selectNewsList(parameter);
	}

	
	public void updateOrInsert(News news) {
		newsDaoImpl.updateOrInsert(news);
	}

	
	public void delete(String id) {
		newsDaoImpl.delete(id);
	}

	
	public News findObjectById(String id) {
		return newsDaoImpl.findObject("from " + News.class.getName() + " where id='" + id + "'");
	}

	
	public List<News> selectAllByPage(PageList page,int type) {
		return newsDaoImpl.selectAllByPage(page,type);
	}

}
