package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        service.addGoods(request);//添加商品
        request.getRequestDispatcher("/list").forward(request, response);//跳转到 列表页面
    }
}
