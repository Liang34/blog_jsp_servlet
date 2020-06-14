package blog.service.impl;


import java.util.List;

import blog.dao.Article_Dao;
import blog.dao.User_Dao;
import blog.dao.impl.Article_DaoImpl;
import blog.dao.impl.User_DaoImpl;
import blog.domin.PageBean;
import blog.domin.User;
import blog.service.User_Service;

public class User_ServiceImpl implements User_Service {
	private User_Dao dao = new User_DaoImpl();
	@Override
	public PageBean<User> findUserByPage(String _currentPage, String _rows) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if(currentPage <=0) {
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount();
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start,rows);
        pb.setList(list);
        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);
        return pb;
	}
	@Override
	public User findByUserName(String username, String password) {
		User user = dao.findByUserName(username);
		// 验证密码是否正确
		if(user!=null && user.getPassword().equals(password)) {// 密码校验成功，返回user
			return user;
		}
		return null;
	}
	@Override
	public Boolean addUser(User user) {
		User user1 = dao.findByUserName(user.getUsername());
		// 可以在这进行邮箱验证
		if(user1==null) {// 用户名没被注册
			dao.addUser(user);
			return true;
		}
		return false;
	}
	@Override
	public User findByUserId(String userId) {
		return dao.findByUserId(userId);
	}
	@Override
	public Boolean updateUser(User user) {
		// 判断密码是否正确
		User user1 = dao.findByUserId(user.getUserId()+"");
		if(user.getPassword().equals(user1.getPassword())) {
			dao.updateUser(user);
			return true;
		}
		
		return false;
	}
	@Override
	public Boolean delUser(String userId) {
		User user = dao.findByUserId(userId);
		if(user.getRole().equals("admin")) {
			return false;
		}
		// 查詢該用戶是否發表過文章
		
		Article_Dao ad = new Article_DaoImpl();
		if(ad.findByUserId(userId).size()!= 0) {
			return false;
		}
		
		dao.delUser(userId);
		return true;
	}

}
