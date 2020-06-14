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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import blog.domin.User;
import blog.service.User_Service;
import blog.service.impl.User_ServiceImpl;

/**
 * 解析二进制表单，封装user再传给RegisterService去验证邮箱是否正确，后再交给Dao层
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
            		user.setUsername(item.getString("utf-8"));
            	}
            	else if(item.getFieldName().equals("password")) {
            		user.setPassword(item.getString("utf-8"));
            	}
            	else if(item.getFieldName().equals("email")) {
            		user.setEmail(item.getString("utf-8"));
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
        // 设置为普通用户
        user.setRole("user");
        // 状态为可用
        user.setUserType(0);
        // userId通过时间来保证不重复，但是也许会重复，有待优化
        user.setUserId((int)new Date().getTime());
        // 将user传给UserService
        User_Service us = new User_ServiceImpl();
        Boolean flag = us.addUser(user);
        if(flag) {
        	//存储数据
    		HttpSession session = req.getSession();
    		session.setAttribute("user", user);
        	res.sendRedirect("/blog_JSP_Servlet/HomeServlet");
        }else {
        	// 注册失败
        	req.setAttribute("msg", "用户名已经被占用");
        	req.setAttribute("code", 406);
        	req.setAttribute("url", "/blog_JSP_Servlet/register.jsp");
        	req.getRequestDispatcher("/blog_JSP_Servlet/error/error.jsp").forward(req, res);
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
