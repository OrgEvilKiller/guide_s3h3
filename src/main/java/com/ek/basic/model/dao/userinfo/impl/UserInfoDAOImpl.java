package com.ek.basic.model.dao.userinfo.impl;

import com.ek.basic.model.dao.base.impl.BaseDAOImpl;
import com.ek.basic.model.dao.userinfo.IUserInfoDAO;
import com.ek.basic.model.pojo.UserInfo;

//这里就不需要再去实现接口的方法了  实现的东西都在BaseDAOImpl中
public class UserInfoDAOImpl extends BaseDAOImpl<UserInfo, Integer> implements IUserInfoDAO {

	@Override
	public Class<UserInfo> getPojoClass() {
		return UserInfo.class;
	}

	

}

 

