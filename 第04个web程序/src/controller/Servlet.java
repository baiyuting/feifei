package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

/**
 * 创建 Servlet ，路径是 /hello1
 * <p>
 * 本类创建目的
 * servlet 中 设置数据之后，这些数据的存活时间
 * <p>
 * 访问本 Servlet ，跳转到 index.jsp
 * 在访问的过程中，分别 通过 request.setAttribute(),request.getSession().setAttribute(),request.getServletContext().setAttribute() 方式设置值
 * <p>
 * 1、request.setAttribute()
 * request中设置值，这种设置方式在一次请求中有效，下一次请求失效
 * 这种方式常用，因为设置的值寿命短，下一次请求就被清除了，不长留在服务器内存中
 * <p>
 * 2、request.getSession().setAttribute()
 * session中设置值（通过 request.getSession() 方法可以获取到这个用户请求对应的 session 对象），在用户通过浏览器访问 本 Servlet 到 关闭浏览器之间，这个值一直存在 在 服务器 中，当 session 过期之后，这个 数据不再存在
 * 这种方式常用于设置 用户信息，这样用户跳转多个页面，也能够保证用户在登陆状态，不会因为用户跳转页面而要求用户重新登陆
 * <p>
 * 3、request.getServletContext().setAttribute()
 * application 中设置值（通过 request.getServletContext() 获取 application 对象，即为 servlet 上下文），这个值得寿命最长，只有在服务器关闭的时候才会消失，否则一直存在
 * 这种方式不常用，因为相关的值会一直存在服务器这边，占用内存
 * <p>
 * 操作步骤
 * 1、访问 /hello1，通过 Servlet 设置值后跳转到 index 页面，查看三个值，都存在
 * 2、点击链接 发起请求跳到 test.jsp ，发现在新的请求下面 ${key1} 不见了，此时 request 设置的值在 第二次请求中失效
 * 3、关闭浏览器，直接访问 /test.jsp，发现 此时 ${key2} 不见了， 只有 ${key3} 存在
 * 为什么 ${key2} 不见了
 * 1）用户访问服务器，服务器生成一个 session 对象，这个对象有过期时间，默认30分钟，到期失效，如果过期了，访问不了 这个 session 对象中设置的数据
 * 2）当用户关闭浏览器，之后打开重新访问服务器的时候，服务器会新建一个 session 对象，新的 session 对象里面没有 设置 key2 的值，以前的 session 对象继续存在服务器内存中，无法获取，直到过期失效
 * 4、重启服务器，访问 /test.jsp，发现 都为 null，说明 ${key3} 在服务器重启后 失效
 */
@WebServlet(name = "Servlet", urlPatterns = {"/hello1"})
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("key1", "value1");// request范围的值 此时的值在一次请求中有效，下次请求失效
        request.getSession().setAttribute("key2", "value2");// session范围的值  在用户通过浏览器访问 本 Servlet 到 关闭浏览器之间，这个值一直存在 在 服务器 中，当 session 过期之后，这个 数据不再存在
        request.getServletContext().setAttribute("key3", "value3");// application 范围的值 这个值得寿命最长，只有在服务器关闭的时候才会消失，否则一直存在
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
