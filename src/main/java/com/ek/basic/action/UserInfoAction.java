package com.ek.basic.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.ek.basic.form.UserInfoForm;
import com.ek.basic.model.pojo.UserInfo;
import com.ek.basic.model.service.IUserInfoService;
import com.ek.basic.model.util.BaseMultiActionController;

public class UserInfoAction extends BaseMultiActionController {

	private String successView; 
	private String formView;
	private IUserInfoService userService ; 
	private String successAction;
	
	public void setUserService(IUserInfoService userService) {
		this.userService = userService;
	}
	public void setSuccessView(String successView) {
		this.successView = successView;
	}
	public void setFormView(String formView) {
		this.formView = formView;
	}
	public void setSuccessAction(String successAction) {
		this.successAction = successAction;
	}
	
	
	
	
	
	
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<UserInfo> userInfoList = userService.findUserInfoBySearchMap(null, null);
		
		return new ModelAndView(successView , "userInfoList" , userInfoList);
	}
	
	
	
	public ModelAndView showSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserInfoForm userInfoForm = new UserInfoForm();
		//和页面form 的commandName 对应 没有这个会报错 
		//还可以设一些默认值
		userInfoForm.setSex("1");
		return new ModelAndView(formView , "userInfoForm" , userInfoForm);
	}

	
	
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UserInfoForm userInfoForm = (UserInfoForm)super.binderAndValidate(request);
		//把 UserInfoForm 拷贝到 UserInfo
		UserInfo user = new UserInfo();
		user.setUserName(userInfoForm.getUserName());
		user.setSex(userInfoForm.getSex());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		user.setBirthday(sdf.parse(userInfoForm.getBirthday()));
		
		userService.addUserInfo(user);
		
		return new ModelAndView(successAction);
	}







	
 
	
	
}