package controller;

import entity.Goods;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 本类是处理添加逻辑的 Servlet
 * <p>
 * 本类中要实现 上传功能实现，需要添加 commons-fileupload 和 commons-io 包，注意版本选择，
 * 没有选择好启动会报错，commons-fileupload-1.3.1.jar 和 commons-io-1.3.2.jar 好像没有问题
 */
@WebServlet(name = "AddServlet", urlPatterns = {"/add"})
public class AddServlet extends BaseServlet {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

        try {
            Goods goods = new Goods();
            List<FileItem> list = servletFileUpload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) { // 如果不是文件
                    if ("name".equals(item.getFieldName()))
                        goods.setName(item.getString("utf-8"));
                    else if ("description".equals(item.getFieldName()))
                        goods.setDescription(item.getString("utf-8"));
                } else { //此时找到上传的文件
//                    String path = request.getServletContext().getRealPath("/") + "/"
                    System.out.println(request.getServletContext().getRealPath("/"));
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
