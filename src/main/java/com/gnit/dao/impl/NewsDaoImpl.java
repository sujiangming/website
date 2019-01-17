package com.gnit.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gnit.dao.base.BaseDaoImpl;
import com.gnit.pojo.News;
import com.gnit.utils.PageList;

@Repository
public class NewsDaoImpl extends BaseDaoImpl {
	/**
	 * 提供给前端使用的获取分页接口
	 * 
	 * @param page
	 * @return
	 */
	public List<News> selectAllByPage(PageList page, int type) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(News.class);
		// 每页开始的索引数在hibernate中的计算公式是：(currentPage-1)*perPageRows
		criteria.setFirstResult((page.getPage() - 1) * page.getRows());
		criteria.setMaxResults(page.getRows());
		criteria.add(Restrictions.eq("type", type));
		criteria.add(Restrictions.eq("isPubish", true));
		List<News> list = criteria.list();
		return list;
	}

	/**
	 * 查询学校新闻列表 type = 1 查询行业新闻列表type = 2
	 */
	public PageList<News> selectNewsList(Map<String, Object> parameter) {
		String hql = "from " + News.class.getName();
		String whereCase = " a where 1=1 ";
		Map<String, Object> map = new HashMap<String, Object>();
		int currentPage = Integer.parseInt(parameter.get("currentPage").toString());
		int showCount = Integer.parseInt(parameter.get("showCount").toString());
		String search = parameter.get("search") == null ? "" : parameter.get("search").toString();
		if (!search.equals("")) {
			whereCase += " and a.title like:search ";
			map.put("search", "%" + search + "%");
		}
		// 根据类型查询
		int type = Integer.parseInt(parameter.get("type").toString());
		// 最后的hql
		hql += whereCase + " and a.type=" + type + " order by a.createTime desc";
		return this.findPageList(hql, currentPage, showCount, map);
	}

	/**
	 * 保存或更新
	 * 
	 * @param news
	 */
	public void updateOrInsert(News news) {
		this.saveOrUpdateObject(news);
	}

	/**
	 * 根据id查询新闻
	 * 
	 * @param id
	 * @return
	 */
	public News getRoleById(String id) {
		return this.findObject("from " + News.class.getName() + " where id='" + id + "'");
	}

	/**
	 * 删除新闻，根据id
	 * 
	 * @param news
	 */
	public void delete(String id) {
		String[] roleIds = id.split(",");
		Session session = this.getSession();
		for (int i = 0; i < roleIds.length; i++) {
			News role = this.getRoleById(roleIds[i]);
			if (role != null) {
				session.delete(role);
			}
		}
	}
}
