package blog.dao;

import java.sql.SQLException;
import java.util.List;

import blog.domin.User;

/**
 * 增加接口约束，使代码逻辑更清晰
 * @author 梁建辉
 *
 */
public interface User_Dao {
	/**
     * 查询总记录数
     * @return
     * @param condition
     */
    int findTotalCount();

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @return
     */
    List<User> findByPage(int start, int rows);
    /**
     * 根据姓名找用户,登录时用
     * @param username
     */
	public User findByUserName(String username);
	/**
     * 根据Id查找用户
	 * @throws SQLException 
     */
	public User findByUserId(String userId);
	/**
	 * 更新用户
	 * @param user
	 * @throws SQLException 
	 */
	public void updateUser(User user);
	/**
	 * 新增用户
	 * @param user
	 * @throws SQLException
	 */
	public void addUser(User user);

	void delUser(String userId);
}
