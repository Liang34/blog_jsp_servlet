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
 * 修改文章
 */
@WebServlet("/ArticleChange")
public class ArticleChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload sUpload=new ServletFileUpload(factory);
        List<FileItem> list = null;
        Article article = new Article();
        try {
             list=sUpload.parseRequest(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 遍历list集合封装user
        for(FileItem item:list){
            if(item.isFormField()){
            	System.out.println(item.getFieldName()+":"+item.getString("utf-8"));
            	if(item.getFieldName().equals("title")) {
            		article.setTitle(item.getString("utf-8"));
            	}
            	else if(item.getFieldName().equals("pubDate")) {
            		article.setPubDate(item.getString("utf-8"));
            	}
            	else if(item.getFieldName().equals("content")) {
            		article.setContent(item.getString("utf-8"));
            	}
            	else if(item.getFieldName().equals("articleId")) {
            		article.setArticleId(Integer.parseInt(item.getString("utf-8")));
            	}
            }else {
				String fileName1 = item.getName();
				if (fileName1 == null || fileName1.trim() == "") {
					System.out.println("文件名为空！");
				}
				fileName1 = fileName1.substring(fileName1.lastIndexOf("\\") + 1);
				String fileExtension = fileName1.substring(fileName1.lastIndexOf(".") + 1);
				String[] tmp = fileName1.split("\\.");
				String filename = tmp[0] + new java.util.Date().getTime() + ".jpg";
				System.out.println("------New Filename:--" + filename + "------");
				String basePath = req.getServletContext().getRealPath("/images/article");
				File file = new File(basePath, filename);
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
				article.setCover("/blog_JSP_Servlet/images/article/" + filename);
				
			}
        }
        // 将article传给ArticleService   
        System.out.println(article+"**************************************");
        ArticleService as = new ArticleServiceImpl();
        as.updateArticle(article);
       // 回到首页
       req.getRequestDispatcher("/ArticleManageServlet").forward(req,res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
