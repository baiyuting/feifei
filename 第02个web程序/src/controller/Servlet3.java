package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 通过注解配置的 Servlet， 路径是 /hello， 名字是 Servlet
 * <p>
 * 本类的目的：
 * 知道 servlet 的 生命周期
 * init [Servlet初始化调用，第1次访问 /hello 的时候初始化 Servlet3]
 * service  [每次请求调用 service 方法，super.service() 方法中会将请求分为 get和post 请求，分别调用 Servlet 的 doGet和doPost 方法]
 * destroy [Servlet销毁时调用，一般在服务器关闭的时候]
 * <p>
 * 三个方法来源于 HttpServlet -> GenericServlet -> Servlet接口
 */
@WebServlet(name = "Servlet3", urlPatterns = {"/hello3"})
public class Servlet3 extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("-----------------init-----------------------------");
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("-----------------service-----------------------------");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("-----------------destroy-----------------------------");
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("这里是Servlet3");
    }
}
