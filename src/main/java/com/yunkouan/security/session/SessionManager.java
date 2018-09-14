/**
 * Copyright &copy; YUNKOUAN Limited All rights reserved.
 */
package com.yunkouan.security.session;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

/**
 * 自定义WEB会话管理类
 * 
 * @author admin
 * @version 2014-7-20
 */
public class SessionManager extends DefaultWebSessionManager {
	protected static Log log = LogFactory.getLog(SessionManager.class);

	public SessionManager() {
		super();
	}

	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		// 如果参数中包含“__sid”参数，则使用此sid会话。
		// 例如：http://localhost/project?__sid=xxx&__cookie=true
		String sid = request.getParameter("__sid");
		if ( sid != null && sid.trim().isEmpty() ) {
			// 是否将sid保存到cookie，浏览器模式下使用此参数。
			if (WebUtils.isTrue(request, "__cookie")) {
				HttpServletRequest rq = (HttpServletRequest) request;
				HttpServletResponse rs = (HttpServletResponse) response;
				Cookie template = getSessionIdCookie();
				Cookie cookie = new SimpleCookie(template);
				cookie.setValue(sid);
				cookie.saveTo(rq, rs);
			}
			// 设置当前session状态
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
					ShiroHttpServletRequest.URL_SESSION_ID_SOURCE); // session来源与url
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sid);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
			return sid;
		} else {
			return super.getSessionId(request, response);
		}
	}

	@Override
	public void validateSessions() {
		super.validateSessions();
	}

	protected Session retrieveSession(SessionKey sessionKey) {
		try {
			return super.retrieveSession(sessionKey);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
			return null;
		}
	}

	public Date getStartTimestamp(SessionKey key) {
		try {
			return super.getStartTimestamp(key);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
			return null;
		}
	}

	public Date getLastAccessTime(SessionKey key) {
		try {
			return super.getLastAccessTime(key);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
			return null;
		}
	}

	public long getTimeout(SessionKey key) {
		try {
			return super.getTimeout(key);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
			return 0;
		}
	}

	public void setTimeout(SessionKey key, long maxIdleTimeInMillis) {
		try {
			super.setTimeout(key, maxIdleTimeInMillis);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
		}
	}

	public void touch(SessionKey key) {
		try {
			super.touch(key);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
		}
	}

	public String getHost(SessionKey key) {
		try {
			return super.getHost(key);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
			return null;
		}
	}

	public Collection<Object> getAttributeKeys(SessionKey key) {
		try {
			return super.getAttributeKeys(key);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
			return null;
		}
	}

	public Object getAttribute(SessionKey sessionKey, Object attributeKey) {
		try {
			return super.getAttribute(sessionKey, attributeKey);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
			return null;
		}
	}

	public void setAttribute(SessionKey sessionKey, Object attributeKey, Object value) {
		try {
			super.setAttribute(sessionKey, attributeKey, value);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
		}
	}

	public Object removeAttribute(SessionKey sessionKey, Object attributeKey) {
		try {
			return super.removeAttribute(sessionKey, attributeKey);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
			return null;
		}
	}

	public void stop(SessionKey key) {
		try {
			super.stop(key);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
		}
	}

	public void checkValid(SessionKey key) {
		try {
			super.checkValid(key);
		} catch (Throwable e) {
			// 获取不到SESSION不抛出异常
			if(log.isErrorEnabled()) log.error(e.getMessage());
		}
	}

	@Override
	protected Session doCreateSession(SessionContext context) {
		try {
			return super.doCreateSession(context);
		} catch (Throwable e) {
			if(log.isErrorEnabled()) log.error(e.getMessage());
			return null;
		}
	}

	@Override
	protected Session newSessionInstance(SessionContext context) {
		Session session = super.newSessionInstance(context);
		session.setTimeout(getGlobalSessionTimeout());
		return session;
	}

	@Override
	public Session start(SessionContext context) {
		try {
			return super.start(context);
		} catch (Throwable e) {
			if(log.isErrorEnabled()) log.error(e.getMessage());
			SimpleSession session = new SimpleSession();
			session.setId(0);
			return session;
		}
	}
}