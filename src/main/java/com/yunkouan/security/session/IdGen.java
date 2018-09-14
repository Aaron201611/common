/**
 * Copyright &copy; YUNKOUAN Limited All rights reserved.
 */
package com.yunkouan.security.session;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * 
 * @author admin
 * @version 2013-01-15
 */
@Service
@Lazy(false)
public class IdGen extends UuidUtils implements SessionIdGenerator {

	@Override
	public Serializable generateId(Session session) {
		return uuid();
	}

	public static void main(String[] args) {
		System.out.println(uuid());
		System.out.println("10011" + uuid().toLowerCase().substring(0, 10));
		System.out.println("10011" + uuid().toLowerCase().substring(0, 10));
		System.out.println(uuid().length());
		for (int i = 0; i < 10; i++) {
			System.out.println(randomLong() + "  " + randomBase62(10));
		}
	}

}
