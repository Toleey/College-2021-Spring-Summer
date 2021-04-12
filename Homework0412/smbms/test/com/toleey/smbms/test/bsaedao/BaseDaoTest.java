package com.toleey.smbms.test.bsaedao;

import com.mysql.jdbc.log.Log;
import com.toleey.smbms.dao.BaseDao;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BaseDaoTest {

    Logger logger = Logger.getLogger(BaseDaoTest.class);
    @Test
    public void testQueryUsers() throws SQLException {
        Connection conn = BaseDao.getConnection();
        String sql = "SELECT * FROM smbms_user";
        ResultSet rst =  BaseDao.executeQuery(conn,sql);
        while (rst.next()){
            for(int i=1;i<rst.getMetaData().getColumnCount();i++) {
                logger.info(rst.getObject(i) + "\n");
            }
        }
        BaseDao.close(conn,null,rst);//关不掉prep
    }

    @Test
    public void testQueryUpdate() throws SQLException{
        Connection conn = BaseDao.getConnection();
        String sql =
                "INSERT INTO `smbms`.`smbms_user` " +
                            "(`id`, `userCode`, `userName`, `userPassword`, `gender`, `birthday`, `phone`, `address`, " +
                            "`userRole`, `createdBy`, `creationDate`, `modifyBy`, `modifyDate`) " +
                        "VALUES " +
                            "(29, 'TW', 'Paxrs', '000000', 1, '1987-01-20', '123412341234', '北京', " +
                            "1, 1, '2015-11-11 11:11:11', 2, '2015-11-11 11:11:11')";
        int line = 0;
        line = BaseDao.executeUpdate(conn,sql);
        BaseDao.close(conn,null,null);
        if (line != 0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

}
