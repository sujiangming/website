package com.gnit.service;

import java.util.List;
import java.util.Map;

import com.gnit.pojo.News;
import com.gnit.utils.PageList;

public interface NewsService {
	PageList<News> selectNewsList(Map<String, Object> parameter);
	void updateOrInsert(News news);
	void delete(String id);
	News findObjectById(String id);
	List<News> selectAllByPage(PageList page,int type);
}
