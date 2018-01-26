package com.ek.basic.model.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ek.basic.model.util.Page;

/**
 E - Element (在集合中使用，因为集合中存放的是元素)
 T - Type（Java 类）
 K - Key（键）
 V - Value（值）
 N - Number（数值类型）
？ -  表示不确定的java类型
 */

//首先要知道 基本数据类型的包装类 都是继承了Serializable了的
//ID表示的是主键(类型一般是 Integer String) 这里继承Serializable表示 传进来的这个参数ID 一定是要能够被序列化的
//ID extends Serializable 习惯上成为 有界类型
public interface IBaseDAO<T , ID extends Serializable> {
	 
	public void addPojo(T pojo);
	
	public void updatePojo(T pojo);
	
	public void deletePojo(ID uuid);
	
	public T findPojoById(ID uuid);
	
	public List<T> findPojoBySearchMap(Map<String,Object> searchMap , Page page);
	
	
}