package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通过 web.xml 配置的 Servlet2， 路径是 /hello2
 *
 * <servlet>
 * <servlet-name>Servlet2</servlet-name>
 * <servlet-class>controller.Servlet2</servlet-class>
 * </servlet>
 * <servlet-mapping>
 * <servlet-name>Servlet2</servlet-name>
 * <url-pattern>/hello2</url-pattern>
 * </servlet-mapping>
 * <p>
 * 本类的目的：
 * 1、通过 web.xml 创建 servlet 的方法
 * 2、使用 response 相应信息
 */
public class Servlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);//可以这样处理，这样只用写一个方法
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");// 设置 返回的 内容类型，是 text 文本或者 html，采用的编码是 utf-8，这样中文不会产生乱码
        response.getWriter().print("这是Servlet2，通过web.xml配置生成");
    }
}
