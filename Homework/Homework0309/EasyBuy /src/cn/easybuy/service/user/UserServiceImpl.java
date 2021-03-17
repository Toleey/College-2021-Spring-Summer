package cn.easybuy.service.user;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cn.easybuy.dao.order.UserAddressDao;
import cn.easybuy.dao.order.UserAddressDaoImpl;
import cn.easybuy.dao.user.UserDao;
import cn.easybuy.dao.user.UserDaoImpl;
import cn.easybuy.dao.user.UserMapper;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import cn.easybuy.entity.User;

public class UserServiceImpl implements UserService {
	
	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Override
	public boolean add(User user){
		SqlSession session=MyBatisUtil.createSQLSession();
		int line=0;
		try {
			line= session.getMapper(UserMapper.class).add(user);
			//提交事务
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//回滚事务
			session.rollback();
		}
		return line>0;
	}

	@Override
	public boolean update(User user) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			count=userDao.update(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
			return  count>0;
		}
	}

	@Override
	public boolean deleteUserById(Integer userId) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			count=userDao.deleteUserById(userId+"");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
			return  count>0;
		}
	}

	@Override
	public User getUser(Integer userId, String loginName) {
		SqlSession session=MyBatisUtil.createSQLSession();
		User user=null; 
		 try {
			user=session.getMapper(UserMapper.class).getUser(userId, loginName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		return user;
	}

	@Override
	public List<User> getUserList(Integer currentPageNo, Integer pageSize) {
		SqlSession session= MyBatisUtil.createSQLSession();
		List<User> userList=null;
		Integer startLineIndex=(currentPageNo-1)*pageSize;
		try {
			userList=session.getMapper(UserMapper.class)
					.getUserList(startLineIndex, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		return userList;
	}

	@Override
	public int count() {
		Connection connection = null;
		Integer count=null;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			count=userDao.count();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
			return count;
		}
	}
}
