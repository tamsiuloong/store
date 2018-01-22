package com.yaorange.store.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {

	private static SqlSessionFactory SQL_SESSION_FACTORY = null;
	static {
		InputStream is;
		try {
			is = Resources.getResourceAsStream("sqlMapConfig.xml");
			SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(is);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static SqlSession openSession() {
		return SQL_SESSION_FACTORY.openSession();
	}

}
