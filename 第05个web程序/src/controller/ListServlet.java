package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 商品列表显示
 * <p>
 * 1、商品信息列表 返回 id,name,status -> List<Goods> items
 * 2、商品总数 count
 * 3、当前页码 pageNo
 * 4、页面大小 pageSize
 */
@WebServlet(name = "ListServlet", urlPatterns = {"/list"})
public class ListServlet extends BaseServlet {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("page", service.getPage(request.getParameter("pageNo"), request.getParameter("pageSize"), request.getParameter("name")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("hi", 1);
        request.getRequestDispatcher("/list.jsp").forward(request, response);//跳转到 list.jsp
    }
}
