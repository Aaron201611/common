package com.yunkouan.base;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yunkouan.entity.ResultModel;
import com.yunkouan.exception.BizException;
import com.yunkouan.util.MessageUtil;
import com.yunkouan.util.StringUtil;

/**
 * 公共异常处理类
 * <P>GlobalExceptionHandler</P>
 * <P>GlobalExceptionHandler.java</P>
 * <P>Description: </P>
 * <P>Copyright: Copyright(c) 2016</P>
 * <P>Company: yunkouan.com</P>
 * <P>Date: 2016年12月12日 下午1:41:30</P>
 * @author andy
 */
@ControllerAdvice
public class GlobalExceptionController {
	/**
	 * 日志对象
     * @author andy wang<br/>
	 */
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 处理业务异常
	 * @param request - 请求对象
	 * @param ex - 捕捉到的异常
	 * @return
	 * @author andy
	 * @date 2016年12月12日 下午1:45:00
	 */
	@ExceptionHandler(BizException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResultModel handleBizException(HttpServletRequest request, BizException ex) {
		ResultModel rm = new ResultModel();
		rm.setError();
		String localizedMessage = ex.getLocalizedMessage();
		if(log.isErrorEnabled()) log.error(ex.getMessage(),ex);
		String message = localizedMessage;
		if ( !localizedMessage.startsWith("{{") && !localizedMessage.endsWith("}}") ) {
			message = MessageUtil.getMessage(request, localizedMessage);
		} else {
			message = message.substring(2,message.length()-2);
		}
		try {
			message = StringUtil.messageReplace(message, ex.getParam());
		} catch (Exception e) {
			e.printStackTrace();
		}
		rm.addMessage(message);
		ex.printStackTrace();
		return rm;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResultModel handleException(HttpServletRequest request, Exception ex) {
		ex.printStackTrace();
		if(log.isErrorEnabled()) log.error(ex.getMessage(),ex);
		ResultModel rm = new ResultModel();
		rm.setError();
		rm.addMessage(MessageUtil.getMessage(request, "net_exception"));
		return rm;
	}
}