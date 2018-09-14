package com.yunkouan.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.yunkouan.entity.ResultModel;
import com.yunkouan.exception.BizException;
import com.yunkouan.util.MessageUtil;
import com.yunkouan.util.StringUtil;

public abstract class BaseController {
	protected static Log log = LogFactory.getLog(BaseController.class);

	/**项目页面前缀访问路径*/
	@Value("${adminPath}")
	protected String adminPath;

	/**项目页面前缀访问路径*/
	@Value("${web.view.prefix}")
	protected String prefix;

	/**
	 * 处理校验
	 * @param br
	 * @throws BizException
	 * <P>@author andy</P>
	 * <P>Date 2017年1月13日 下午2:42:20</P>
	 */
	public ResultModel handleValid( BindingResult br ) throws BizException {
		ResultModel rm = new ResultModel();
		rm.setStatus(0);
		if ( br.hasErrors() ) {
			List<ObjectError> allErrors = br.getAllErrors();
			for (int i = 0; i < allErrors.size(); i++) {
				ObjectError objectError = allErrors.get(i);
				if ( !rm.contains(objectError.getDefaultMessage()) ) {
					rm.addMessage(objectError.getDefaultMessage()+"<br/><br/>");
				}
			}
		}
		return rm;
	}

	protected String handleBizException(HttpServletRequest request, BizException ex) {
		String localizedMessage = ex.getLocalizedMessage();
		String message = localizedMessage;
		if ( !localizedMessage.startsWith("{{") && !localizedMessage.endsWith("}}") ) {
			message = MessageUtil.getMessage(request, localizedMessage, null);
		} else {
			message = message.substring(2,message.length()-2);
		}
		try {
			message = StringUtil.messageReplace(message, ex.getParam());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
}