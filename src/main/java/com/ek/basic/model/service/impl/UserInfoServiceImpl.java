package com.ek.basic.model.service.impl;

import java.util.List;
import java.util.Map;

import com.ek.basic.model.dao.userinfo.IUserInfoDAO;
import com.ek.basic.model.pojo.UserInfo;
import com.ek.basic.model.service.IUserInfoService;
import com.ek.basic.model.util.Page;

public class UserInfoServiceImpl implements IUserInfoService {
	
	private IUserInfoDAO userInfoDAO;
	public void setUserInfoDAO(IUserInfoDAO userInfoDAO) {
		this.userInfoDAO = userInfoDAO;
	}

	
	@Override
	public void addUserInfo(UserInfo user) {
		userInfoDAO.addPojo(user);
	}

	@Override
	public void updateUserInfo(UserInfo user) {
		userInfoDAO.updatePojo(user);
	}

	@Override
	public void deleteUserInfo(String uuid) {
		userInfoDAO.deletePojo(Integer.valueOf(uuid));
	}

	@Override
	public UserInfo findUserInfoById(String uuid) {
		return userInfoDAO.findPojoById(Integer.valueOf(uuid));
	}

	@Override
	public List<UserInfo> findUserInfoBySearchMap(Map<String, Object> searchMap, Page page) {
		return userInfoDAO.findPojoBySearchMap(searchMap, page);
	}

}
