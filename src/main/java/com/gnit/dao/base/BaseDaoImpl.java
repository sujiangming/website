package com.gnit.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gnit.utils.PageList;

/**
 * Hibernate工具类封装
 * 
 * @author JDRY-BUXC
 *
 */
@SuppressWarnings("unchecked")
@Repository("baseDao")
public class BaseDaoImpl implements BaseDao {

	@Autowired
	public SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findObject(java.lang.String)
	 */
	public <T> T findObject(String hql) {
		List<T> list = findList(hql);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findObject(java.lang.String, java.lang.Object[])
	 */

	public <T> T findObject(String hql, Map<String, Object> parameter) {
		List<T> list = findList(hql, parameter);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findObject(java.lang.Class, java.io.Serializable)
	 */

	public <T> T findObject(Class<T> cls, Serializable id) {
		return (T) getSession().get(cls, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findObjectBySql(java.lang.String)
	 */

	public <T> T findObjectBySql(String sql) {
		List<T> list = findListBySql(sql);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findObjectBySql(java.lang.String,
	 * java.lang.Object[])
	 */

	public <T> T findObjectBySql(String sql, Map<String, Object> parameter) {
		List<T> list = findListBySql(sql, parameter);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findList(java.lang.String)
	 */

	public <T> List<T> findList(String hql) {
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findList(java.lang.String, java.lang.Object[])
	 */

	public <T> List<T> findList(String hql, Map<String, Object> parameter) {
		Query query = getSession().createQuery(hql);
		setParameter(query, parameter);
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findList(java.lang.Class)
	 */

	public <T> List<T> findList(Class<T> cls) {
		String hql = "FROM " + cls.getName();
		return findList(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findListBySql(java.lang.String)
	 */

	public <T> List<T> findListBySql(String sql) {
		Query query = getSession().createSQLQuery(sql);
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findListBySql(java.lang.String,
	 * java.lang.Object[])
	 */

	public <T> List<T> findListBySql(String sql, Map<String, Object> parameter) {
		Query query = getSession().createSQLQuery(sql);
		setParameter(query, parameter);
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#saveObject(java.lang.Object)
	 */

	public <T> void saveObject(T obj) {
		getSession().save(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#updateObject(java.lang.Object)
	 */

	public <T> void updateObject(T obj) {
		getSession().update(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#saveOrUpdateObject(java.lang.Object)
	 */

	public <T> void saveOrUpdateObject(T obj) {
		getSession().saveOrUpdate(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#executeSql(java.lang.String)
	 */

	public int executeSql(String sql) {
		Query query = getSession().createSQLQuery(sql);
		return query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#executeSql(java.lang.String, java.lang.Object[])
	 */

	public int executeSql(String sql, Map<String, Object> parameter) {
		Query query = getSession().createSQLQuery(sql);
		setParameter(query, parameter);
		return query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#coutObjects(java.lang.String)
	 */

	public int coutObjects(String hql) {
		Query query = getSession().createQuery(hql);
		ScrollableResults sr = query.scroll();
		sr.last();
		return sr.getRowNumber() == -1 ? 0 : sr.getRowNumber() + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#countObjects(java.lang.String,
	 * java.lang.Object[])
	 */

	public int countObjects(String hql, Map<String, Object> parameter) {
		Query query = getSession().createQuery(hql);
		setParameter(query, parameter);
		ScrollableResults sr = query.scroll();
		sr.last();
		return sr.getRowNumber() == -1 ? 0 : sr.getRowNumber() + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findPageList(java.lang.String, int, int)
	 */

	public <T> PageList<T> findPageList(String hql, int page, int rows) {
		Query query = getSession().createQuery(hql);
		return findPageList(query, page, rows);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.school.dao.BaseDao#findPageList(java.lang.String, int, int,
	 * java.lang.Object[])
	 */
	public <T> PageList<T> findPageList(String hql, int page, int rows, Map<String, Object> parameter) {
		Query query = getSession().createQuery(hql);
		setParameter(query, parameter);
		return findPageList(query, page, rows);
	}

	<T> PageList<T> findPageList(Query query, int page, int rows) {
		ScrollableResults sr = query.scroll();
		sr.last();
		int count = sr.getRowNumber() == -1 ? 0 : sr.getRowNumber() + 1;
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		return new PageList<T>(page, rows, count, query.list());
	}

	void setParameter(Query query, Map<String, Object> parameter) {
		for (Map.Entry<String, Object> entry : parameter.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

	public <T> List<T> findList(Session session, String hql) {
		Query query = session.createQuery(hql);
		return query.list();
	}

	public <T> T findObject(Session session, String hql) {
		List<T> list = findList(session, hql);
		return (null == list || list.size() == 0) ? null : list.get(0);
	}
}