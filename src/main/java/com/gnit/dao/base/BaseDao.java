package com.gnit.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.gnit.utils.PageList;


/**
 * Hibernate操作工具封装
 * @author JDRY-BUXC
 *
 */
public interface BaseDao {
	/**
     * 
     * @param hql
     * @return
     */
    <T> T findObject(String hql);
     
    /**
     * 
     * @param hql
     * @param objects
     * @return
     */
    <T> T findObject(String hql, Map<String,Object> parameter);
     
    /**
     * 
     * @param cls
     * @param id
     * @return
     */
    <T> T findObject(Class<T> cls, Serializable id);
     
    /**
     * 
     * @param sql
     * @return
     */
    <T> T findObjectBySql(String sql);
     
    /**
     * 
     * @param sql
     * @param objects
     * @return
     */
    <T> T findObjectBySql(String sql, Map<String,Object> parameter);
     
    /**
     * 
     * @param hql
     * @return
     */
    <T> List<T> findList(String hql);
     
    /**
     * 
     * @param hql
     * @param objects
     * @return
     */
    <T> List<T> findList(String hql, Map<String,Object> parameter);
     
    /**
     * 
     * @param cls
     * @return
     */
    <T> List<T> findList(Class<T> cls);
     
    /**
     * 
     * @param sql
     * @return
     */
    <T> List<T> findListBySql(String sql);
     
    /**
     * 
     * @param sql
     * @param objects
     * @return
     */
    <T> List<T> findListBySql(String sql, Map<String,Object> parameter);
     
    /**
     * 
     * @param obj
     */
    <T> void saveObject(T obj);
     
    /**
     * 
     * @param obj
     */
    <T> void updateObject(T obj);
     
    /**
     * 
     * @param obj
     */
    <T> void saveOrUpdateObject(T obj);
     
    /**
     * 
     * @param sql
     * @return
     */
    int executeSql(String sql);
     
    /**
     * 
     * @param sql
     * @param objects
     * @return
     */
    int executeSql(String sql, Map<String,Object> parameter);
     
    /**
     * 
     * @param hql
     * @return
     */
    int coutObjects(String hql);
     
    /**
     * 
     * @param hql
     * @param objects
     * @return
     */
    int countObjects(String hql, Map<String,Object> parameter);
     
    /**
     * 
     * @param hql
     * @param page
     * @param rows
     * @return
     */
    <T> PageList<T> findPageList(String hql, int page, int rows);
     
    /**
     * 
     * @param hql
     * @param page
     * @param rows
     * @param objects
     * @return
     */
    <T> PageList<T> findPageList(String hql, int page, int rows, Map<String,Object> parameter);
    
}
