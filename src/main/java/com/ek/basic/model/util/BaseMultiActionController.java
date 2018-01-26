package com.ek.basic.model.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
@SuppressWarnings("rawtypes")
// MultiActionController中没有数据绑定的方法 在这手动写一个可以绑定数据的 公共方法
public abstract class BaseMultiActionController extends MultiActionController {
	
	
	private Class commandClass;  //MultiActionController没有 command 自己注入一个(在子类注入)
	public void setCommandClass(Class commandClass) {
		this.commandClass = commandClass;
	}
	
	// 受保护的 只有子类可以用
	protected Object binderAndValidate(HttpServletRequest request) throws Exception{
		
		/**数据绑定**/
		// 创建数据绑定对象
		Object command = super.newCommandObject(commandClass);
		ServletRequestDataBinder binder = super.createBinder(request, command);
		
		// 客户化数据格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor cde = new CustomDateEditor(sdf,true); //第二个参数 指定是否可以为空
		binder.registerCustomEditor(Date.class, cde);
		
		super.initBinder(request, binder); //初始化绑定
		super.bind(request, command); //绑定
		
		
		
		/**数据效验**/
		// 数据效验错误对象
		BindException errors = new BindException(command,"userinfo");
		//进行数据效验
		if(this.getValidators()!=null){
			Validator[] Validators = this.getValidators();
			for (Validator validator : Validators) {
				if(validator.supports(command.getClass())){
					validator.validate(command, errors);
				}
			}
		}
		return command;
	}
}