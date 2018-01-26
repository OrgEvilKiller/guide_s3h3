package com.ek.basic.model.dao.userinfo;

import com.ek.basic.model.dao.base.IBaseDAO;
import com.ek.basic.model.pojo.UserInfo;

//这的泛型要用具体值
public interface IUserInfoDAO extends IBaseDAO<UserInfo,Integer>{
	
}