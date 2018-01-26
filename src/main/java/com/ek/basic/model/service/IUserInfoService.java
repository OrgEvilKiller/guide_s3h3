 package com.ek.basic.model.service;

import java.util.List;
import java.util.Map;

import com.ek.basic.model.pojo.UserInfo;
import com.ek.basic.model.util.Page;

public interface IUserInfoService {
	
	public void addUserInfo(UserInfo user);
	
	public void updateUserInfo(UserInfo user);
	
	public void deleteUserInfo(String uuid);
	
	public UserInfo findUserInfoById(String uuid);
	
	public List<UserInfo> findUserInfoBySearchMap(Map<String,Object> searchMap , Page page);
}