package com.ek.basic.model.dao.base.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ek.basic.model.dao.base.IBaseDAO;
import com.ek.basic.model.util.Page;

//abstract 抽象类可以不用实现 上层接口的方法
//这里用abstract的意义不是不 实现上层接口的方法 而是为了 让这个类不能被实例化 (BaseDAOImpl实例化是没有意义的)
//继承HibernateDaoSupport 可以使用hibernate的session 他需要注入sessionFactory
public abstract class BaseDAOImpl<T, ID extends Serializable> extends HibernateDaoSupport implements IBaseDAO<T,ID>{

	/**
	  	在UserInfoDAOImpl 中调用delete()时 实际上调用的是 BaseDAOImpl 的 delete() ( 因为在UserInfoDAOImpl 中调用delete()时调用的是父类中的delete() )
		这时 BaseDAOImpl 的 delete() 会调用 BaseDAOImpl 的 getPojoClass()
		在BaseDAOImpl 中调用 getPojoClass() 实际上调用的是 UserInfoDAOImpl 的 getPojoClass() ( 因为UserInfoDAOImpl 的 getPojoClass()是父类中 getPojoClass() 的实现 )
		
		在子类中调用父类的一个方法的时候 父类的这个方法 需要调用子类中的一个方法,  或:
		在B类中调用A类的一个方法的时候 A类的这个方法 需要调用B类中的一个方法
	 */
	
	//用一个抽象的方法交由子类来实现 返回值由子类传递进来, (这是一种回调的实现)
	//(用在deletePojo , findPojoById 等方法中 hibernate的 get()需要一个class时 )
	public abstract Class<T> getPojoClass(); //我发现Class也是实现了Serializable pojo自动生成是也是实现了Serializable
	
	
	
	
	
	//新增
	@Override
	public void addPojo(T pojo) {
		super.getHibernateTemplate().save(pojo);
	}
	
	
	
	
	
	//修改
	@Override
	public void updatePojo(T pojo) {
		super.getHibernateTemplate().merge(pojo);
	}
	
	
	
	
	
	//删除
	@Override
	public void deletePojo(ID uuid) {//这是可以实现级联的
		T getT = (T)super.getHibernateTemplate().get(this.getPojoClass(), uuid); 
		super.getHibernateTemplate().delete(getT);
	}

	
	
	
	
	//按主键查单条
	@Override
	public T findPojoById(ID uuid) {
		return  (T)super.getHibernateTemplate().get(this.getPojoClass(), uuid);
	}
	
	
	
	
	
	
	//getPojoClass().getSimpleName()得到的是类名
	//按查询条件查询 
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	@Override
	public List<T> findPojoBySearchMap(Map<String, Object> searchMap, Page page) {
	 
		final Map<String,Integer> pageMap = new HashMap<String,Integer>();
		if(page!=null){
			//当前页号-1 (就是上一页) * 每页记录数 = 上一页最大记录条数 再+1 = 就是当前页第一条记录
			Integer first = (page.getCurrentPageNum()-1) * page.getPerPageNum()+1; //当前页第一条
			Integer max = page.getPerPageNum(); 
			pageMap.put("first", first);
			pageMap.put("max", max);
		}
		
		final List<Object> values = new ArrayList<Object>(); //存查询条件对应的值集合 
		final StringBuffer hql = new StringBuffer("from "+this.getPojoClass().getSimpleName());
		
		if(searchMap!=null&&searchMap.size()>0){
			hql.append(" where 1=1 ");
			for (String key : searchMap.keySet()) {
				hql.append(" and ").append(key).append(" =? "); //给 sql 拼接查询条件
				values.add(searchMap.get(key)); // 添加?对应的值
			}
		}
		
		//List<T> pojoList = super.getHibernateTemplate().find(hql.toString(), values.toArray());//这个没有翻页
		List<T> pojoList = super.getHibernateTemplate().executeFind(new HibernateCallback(){
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql.toString());
				// 设置查询条件
				int i =0;
				for (Object value : values) {
					String type = value.getClass().getName();
					if("java.lang.String".equals(type)){
						query.setString(i, (String)value);
						i++;
						continue;
					}
					if("java.util.Date".equals(type)){
						query.setDate(i, (Date)value);
						i++;
						continue;
					}
					if("java.lang.Integer".equals(type)){
						query.setInteger(i, (Integer)value);
						i++;
						continue;
					}
					// ... 
				}
				//查询分页
				if(pageMap.get("first")!=null&&pageMap.get("max")!=null){
					query.setFirstResult(pageMap.get("first"));
					query.setMaxResults(pageMap.get("max"));
				}
				return query.list();
			}
		});
		return pojoList;
	}

 
	
	/**
	   记住：抽象方法是不能调用的
	   原因就是抽象方法没有方法体。
	   但是，在一个类中定义了一个抽象方法，在这个类 或 这个类的子类 中是可以调用的。
	   为什么可以调用呢？
	   因为，具有抽象方法的类，一定是一个抽象类，
	   而抽象类在被继承的时候，必须重写这个抽象类的抽象方法，
	   而且，抽象类不能实例化，在使用这个抽象类的时候，使用的一定是这个抽象类的子类。
	   故，你在 抽象类 或 抽象类的子类 中可以使用这个抽象方法，是因为当真正实例化去使用的时候，使用的是子类重写后的实例方法。 
	 */

}