package cn.easybuy.dao.user;
/**
 * 操作用户数据的接口
 * @author lyonb
 *
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.easybuy.entity.User;

public interface UserMapper {
	//1.获得分页的用户列表
	/**
	 * 
	 * @param startLineIndex 本页数据从第几行开始
	 * @param pageSize 每页显示多少行
	 * @return 每页显示的数据
	 * @throws Exception
	 */
	public List<User> getUserList(
			@Param("startLineIndex")Integer startLineIndex,
			@Param("pageSize")Integer pageSize
			)throws Exception;
	/**
	 * 新增功能，可以用于普通用户注册，还可以用于管理员新增用户
	 * @param user 
	 * @return
	 * @throws Exception
	 */
	public int add(User user)throws Exception;
	/**
	 * 查找用户，当传的参数为用户编号时候，该方法可以获得用户详细信息时用
	 * 当参数为用户名的时候，可以进行登录操作
	 * 
	 * @param id
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	public User getUser(
			@Param("id") Integer id,
			@Param("loginName")String loginName)throws Exception;
}
