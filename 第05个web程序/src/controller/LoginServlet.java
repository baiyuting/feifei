package controller;

import entity.User;
import service.Service;
import service.impl.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆 Servlet
 * <p>
 * 本类中：
 * 1、获取 username 和 password
 * 2、调用 service 方法查询，比较 password 是否正确
 * 3、验证码的比较
 * 4、如果正确，返回用户信息 user，设置到 session 中，跳转到列表页
 * 5、如果错误，返回到 index.jsp 页面
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private Service service = new ServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        User user = service.verifyUser(request.getParameter("username"), request.getParameter("password"),
                (String) request.getSession().getAttribute("imageCode"), request.getParameter("verifyCode"));//验证用户信息
        if (null == user)
            request.getRequestDispatcher("/index.jsp").forward(request, response);//跳转回 index.jsp
        else {
            request.getSession().setAttribute("user", user);//session 中 设置 user 信息
            request.getRequestDispatcher("/list.jsp").forward(request, response);// 跳转到 list.jsp
        }
    }
}
