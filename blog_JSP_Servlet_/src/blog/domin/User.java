package blog.domin;

public class User {
	Integer userId;											/*用户ID，主键*/
	Integer userType; 										/*用户是0时可用，为1时禁用*/							
    String username;									/*用户名*/
    String password; 									/*用户密码*/
    String image; 										/*用户头像*/
	String email; 										/*用戶郵箱*/
	String role;										/*普通用户user,超级用户admin*/
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userType=" + userType + ", username=" + username + ", password=" + password
				+ ", image=" + image + ", email=" + email + ", role=" + role + "]";
	}
}
