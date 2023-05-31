package bookstore.web;

import bookstore.utils.AliyunOSS;
import bookstore.utils.FileTest;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UploadHeaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //判断是否是文件
        if (ServletFileUpload.isMultipartContent(req)){
            // 创建 FileItemFactory 工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            // 创建用于解析上传数据的工具类 ServletFileUpload 类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            // 解析上传的数据，得到每一个表单项 FileItem
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                // 循环判断，每一个表单项，是普通类型，还是上传的文件
                for (FileItem fileItem:list){
                    if (!fileItem.isFormField()){
                        //获取上传文件的文件名，文件大小，
                        long size = fileItem.getSize();
                        String name = fileItem.getName();
                        FileTest fileTest = new FileTest();
                        boolean b = fileTest.testType(name); //true 为正常结尾
                        boolean b1 = fileTest.testContext(fileItem.getInputStream());
                        if (!b || !b1) {
                            //图片格式不允许
                            String msg = "图片格式不正确!!!";
                            resp.setContentType("text/html;charset=UTF-8");
                            resp.getWriter().write(msg);
                            return;
                        }
                        boolean b2 = fileTest.testFileSize(size);
                            if (!b2){
                              //图片大于10MB
                                String msg ="图片大于10MB!!";
                                resp.getWriter().write(msg);
                                return;
                            }

                        String imgUrl = new AliyunOSS().getAlyunOss(fileItem);
//                        System.out.println(imgUrl);

                        resp.getWriter().write(imgUrl);
                      /*  req.setAttribute("url",imgUrl);
                        req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);*/
                        }

                    }



            } catch (FileUploadException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
