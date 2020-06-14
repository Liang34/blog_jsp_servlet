package blog.service;


import blog.domin.PageBean;
import blog.domin.User;

/**
 * 改进，将与操作用户有关的数据放在这里定义规则，方便后期维护
 *
 */
public interface User_Service {
	/**
     * User分页查询
     * @param currentPage
     * @param rows
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows);
    /**
     * 根据username去dao层查找用户，然后进行密码匹配，失败返回null，成功发挥user
     * @param username
     * @param password
     * @return
     */
	User findByUserName(String username, String password);
	/**
	 * 先去数据库查询改名字是否已被注册，若注册则失败，成功发挥true
	 * @param user
	 * @return
	 */
	Boolean addUser(User user);
	/**
	 * 根据userId查找用户
	 * @param userId
	 * @return
	 */
	User findByUserId(String userId);
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	Boolean updateUser(User user);
	/**
	 * 刪除用戶,注：管理員不可刪除，此外，曾經評論過的、或者發表過文章的小夥伴也是不可刪除的會返回false
	 * @param userId
	 * @return
	 */
	Boolean delUser(String userId);
	
}
