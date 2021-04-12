package org.bw.smbms.basedao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.bw.smbms.dao.BaseDao;
import org.junit.Test;

public class BaseDaoTest {
	Logger logger=Logger.getLogger(BaseDaoTest.class);
	@Test
	public void testQueryUser() throws SQLException {
		Connection conn =BaseDao.getConnection();
		String sql="select * from smbms_user";
		ResultSet rst=BaseDao.executeQuery(conn, sql);
		while (rst.next()) {
			for (int i = 1; i < rst.getMetaData().getColumnCount();i++ ) 
				
			logger.info(rst.getObject(i)+"\n");

		}
		BaseDao.close(conn, null, rst);
	}
}
