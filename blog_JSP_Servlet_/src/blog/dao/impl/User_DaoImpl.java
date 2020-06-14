package blog.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import blog.dao.User_Dao;
import blog.domin.User;
import blog.util.JDBCUtils;

public class User_DaoImpl implements User_Dao{
	// 创建template对象
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	@Override
    public int findTotalCount() {
        String sql = "select count(userId) from blog_user";
        return template.queryForObject(sql, Integer.class);
    }
    
    
    @Override
	public List<User> findByPage(int start, int rows) {
        String sql = "select * from blog_user limit ?,?";
        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class),start, rows);
        return list;
    }


	@Override
	public User findByUserName(String username) {
		try {
			String sql = "select * from blog_user where username = ?";
			User user =template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
			return user;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public User findByUserId(String userId) {
		try {
			String sql = "select * from blog_user where userId = ?";
			User user =template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userId);
			return user;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public void updateUser(User user) {
		String sql = "update blog_user set userId=?,username=?,password=?,userType=?,image=?,email=?,role=? where userId=?";
		template.update(sql,user.getUserId(),user.getUsername(),
				user.getPassword(),user.getUserType(),user.getImage(),user.getEmail(),user.getRole(),user.getUserId());
		}
	
	@Override
	public void addUser(User user) {
		String sql = "insert into blog_user(userId, username,password, userType, image, email, role) values (?,?,?,?,?,?,?)";
		template.update(sql,user.getUserId(),user.getUsername(),
				user.getPassword(),user.getUserType(),user.getImage(),user.getEmail(),user.getRole());
	}


	@Override
	public void delUser(String userId) {
		String sql = "delete from blog_user where userId = ?";
		template.update(sql,userId);
		
	}

}
