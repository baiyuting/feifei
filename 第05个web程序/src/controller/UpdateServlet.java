package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 更新 商品上架状态
 * <p>
 * 用户在 list.jsp 列表页面 点击下拉菜单，更改上架状态，这是通过 ajax 发送 post 请求到本接口实现 更新上架状态逻辑
 * <p>
 * 需要知道商品的 id 和 商品的上架状态 status
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/update"})
public class UpdateServlet extends BaseServlet {
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service.updateStatus(request.getParameter("id"), request.getParameter("status"));//更新上架状态
    }
}
