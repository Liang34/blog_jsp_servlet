package blog.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import blog.domin.Article;
import blog.service.ArticleService;
import blog.service.impl.ArticleServiceImpl;

/**
 * 这个servlet用于解析addArticle所上传的表单，调用Service去保存，在转发到首页
 */
@WebServlet("/AddArticle")
public class AddArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//设置工厂
        DiskFileItemFactory factory=new DiskFileItemFactory();
        //设置解析器
        ServletFileUpload sUpload=new ServletFileUpload(factory);
        //解析结果放list
        List<FileItem> list = null;
        Article article = new Article();
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
            	System.out.println(item.getFieldName()+":"+item.getString("utf-8"));
            	if(item.getFieldName().equals("title")) {
            		article.setTitle(item.getString("utf-8"));
            	}
            	else if(item.getFieldName().equals("userId")) {
            		article.setUserId(Integer.parseInt(item.getString()));
            	}
            	else if(item.getFieldName().equals("pubDate")) {
            		// string转为sql的date类型
//            		String time = item.getString();
//            		SimpleDateFormat sdf =new SimpleDateFormat( "yyyy-MM-dd" ); 
//            		java.util.Date date = null;
//					try {
//						date = sdf.parse(time);
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
            		article.setPubDate(item.getString("utf-8"));
            	}
            	else if(item.getFieldName().equals("content")) {
            		article.setContent(item.getString("utf-8"));
            	}
            }else {
				// ************/
				System.out.println("------" + item.getName() + "------");
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
				// ************/
				// 获取文件名+随机数以防文件重名,先把jpg切掉，拼上时间在接回jpg
				// String[] tmp = item.getName().split("\\.");
				// String filename = tmp[0]+new java.util.Date().getTime()+".jpg";
				String[] tmp = fileName1.split("\\.");
				String filename = tmp[0] + new java.util.Date().getTime() + ".jpg";
				System.out.println("------New Filename:--" + filename + "------");
				// 存储路径，用ecilpse存放的话会出现一个很奇怪的路径，或许是我Tomcat配置ecilpse时没放在webapp下
//                String basePath = req.getServletContext().getRealPath("/images/user");
				// 用绝对路径进行存储的话，即是是放在项目下将来访问也会报错
//                File file = new File("D:\\eclispe\\blog_JSP_Servlet\\WebContent\\images\\article", filename);
				// 最后一种方法了，打包放在webapp下
				String basePath = req.getServletContext().getRealPath("/images/article");
				File file = new File(basePath, filename);
				// ************/
				System.out.println("------New FilePath:--" + file.getAbsolutePath() + "------");

				// 获取文件名：
				// String fileName = item.getName();
				// 获取文件的类型：
				// String fileType = item.getContentType();
				// 获取文件的字节码：
				InputStream in = item.getInputStream();
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

				// ************/

				try {
					// item.write(file);
					// 将路径名传给user.image,自己拼接路径存到项目
					article.setCover("/blog_JSP_Servlet/images/article/" + filename);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
        int id = (int) new java.util.Date().getTime();
        article.setArticleId(id);
        // 将article传给ArticleService
        
        System.out.println("------Article Cover:--"+article.getCover()+"------");      
        
        ArticleService as = new ArticleServiceImpl();
        as.addArticle(article);
       // 回到首页
       req.getRequestDispatcher("/HomeServlet").forward(req,res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
