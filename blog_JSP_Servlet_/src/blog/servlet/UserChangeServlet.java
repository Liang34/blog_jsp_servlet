package blog.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import blog.domin.User;
import blog.service.User_Service;
import blog.service.impl.User_ServiceImpl;

/**
 * 这个servlet负责两个工作，将二进制表单进行解析，封装到user中在调用userdao来进行密码比对，密码正确后更新数据库
 */
@WebServlet("/UserChangeServlet")
public class UserChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletReq request, HttpServletResponse res)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//设置工厂
        DiskFileItemFactory factory=new DiskFileItemFactory();
        //设置解析器
        ServletFileUpload sUpload=new ServletFileUpload(factory);
      //解析结果放list
        List<FileItem> list = null;
        User user = new User();
        try {
             list=sUpload.parseRequest(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 遍历list集合封装user
        for(FileItem item:list){
            if(item.isFormField()){// 一般表单
            	/**
            	 * 因为表单是二进制数据，无法通过getParameter获取所以我只好用if条件判断了，查找百度无果。。。。。
            	 */
            	if(item.getFieldName().equals("username")) {
            		user.setUsername(item.getString());
            	}
            	else if(item.getFieldName().equals("password")) {
            		user.setPassword(item.getString());
            	}
            	else if(item.getFieldName().equals("email")) {
            		user.setEmail(item.getString());
            	}
            	else if(item.getFieldName().equals("role")) {
            		user.setRole(item.getString());
            	}
            	else if(item.getFieldName().equals("userType")) {
            		user.setUserType(Integer.parseInt(item.getString()));
            	}
            	else if(item.getFieldName().equals("userId")) {
            		user.setUserId(Integer.parseInt(item.getString()));
            	}
            }else {
            	// 文件名
				String fileName1 = item.getName();
				if (fileName1 == null || fileName1.trim() == "") {
					System.out.println("文件名为空！");
				}
				// 处理不同浏览器提交的文件名带路径问题
				fileName1 = fileName1.substring(fileName1.lastIndexOf("\\") + 1);
				// 文件扩展名
				String fileExtension = fileName1.substring(fileName1.lastIndexOf(".") + 1);
				// 判断扩展名是否合法
				System.out.println("------Filename:--" + fileName1 + "------");
				System.out.println("------FileExtension:--" + fileExtension + "------");
				String[] tmp = fileName1.split("\\.");
				String filename = tmp[0] + new java.util.Date().getTime() + ".jpg";
				System.out.println("------New Filename:--" + filename + "------");
				String basePath = req.getServletContext().getRealPath("/images/user");
				File file = new File(basePath, filename);
				// 获取文件的字节码：
				InputStream in = item.getInputStream();
				System.out.println(basePath);
				// 文件大小
				int size = item.getInputStream().available();
				// 声明输出字节流
				OutputStream out = new FileOutputStream(file);
				// 文件copy
				byte[] b = new byte[1024];
				int len = 0;
				while ((len = in.read(b)) != -1) {
					out.write(b, 0, len);
				}
				out.flush();
				out.close();
				user.setImage("/blog_JSP_Servlet/images/user/" + filename);
            }
        }
        if(user.getPassword()!=null) {
        	// 调用userService去验证密码是否正确
        	User_Service uservice = new User_ServiceImpl();
        	Boolean flag = uservice.updateUser(user);
        	if(flag) {//修改信息成功，重定向到UserManage
        		res.sendRedirect("/blog_JSP_Servlet/UserManage");
        	}else {
            	req.setAttribute("code", 409);
            	req.setAttribute("msg", "密码不正确");
            	req.setAttribute("url", "/blog_JSP_Servlet/UserManage");
            	req.getRequestDispatcher("error/error.jsp").forward(req,res);
            }
        }else {
        	req.setAttribute("code", 405);
        	req.setAttribute("msg", "请输入密码");
        	req.setAttribute("url", "/blog_JSP_Servlet/UserManage");
        	req.getRequestDispatcher("error/error.jsp").forward(req,res);
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
