package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 商品详情
 * <p>
 * 显示商品id，名称，描述，图片，上架状态
 * <p>
 * list.jsp 点击查看商品详情，请求 /detail ，跳转到 detail.jsp 页面
 */
@WebServlet(name = "DetailServlet", urlPatterns = {"/detail"})
public class DetailServlet extends BaseServlet {
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("goods", service.getGoodsById(request.getParameter("id")));// 获取商品详情，设置到 request 中
        request.getRequestDispatcher("/detail.jsp").forward(request, response);//跳转到 detail.jsp
    }
}
