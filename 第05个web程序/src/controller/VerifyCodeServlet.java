package controller;

import util.ImageCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/**
 * 本类是验证码实现类
 * <p>
 * 登陆页面，第一次加载显示验证码 window.onload
 * 点击一次 按钮，通过 ajax 异步发送请求 到 /verifyCode ，获取 图片 base64 编码，设置到 src 字段显示
 * <p>
 * base64 编码 byte[] 数组成为 字符串
 * Base64.getEncoder() 获取编码器 encodeToString(byte[]) 将 byte[] 字节数组 转化为 string 类型字符串
 * Base64.getDecoder() 获取解码器 decode(String) 将字符串解码成为 byte[] 字节数组
 * 前端 img 显示 <img src='data:image/png;base64,XXXXXX'/>  Base64 编码之后前面加上一节 data:image/png;base64, 来显示图片，好像还有其他的办法显示 base64 编码过后的图片，肥肥可以查一下
 * <p>
 * ImageCodeUtil.getImageCode() 返回一个 map
 * code 是生成的验证码的内容，比如 1435 ，需要保存到 session 中，用户在 index.jsp 填好用户名和密码之后， 在 /login LoginServlet 中 通过 session.get() 方法获取验证码 与 用户输入验证码比对，记得 /login 中比对之后要删掉
 * imageCode 是生成的图形验证码 byte[]
 */
@WebServlet(name = "VerifyCodeServlet", urlPatterns = {"/verifyCode"})
public class VerifyCodeServlet extends BaseServlet {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> res = ImageCodeUtil.getImageCode();
        String image = Base64.getEncoder().encodeToString((byte[]) (res.get("imageCode")));
        image = "data:image/png;base64," + image;
        request.getSession().setAttribute("imageCode", res.get("code"));//session 中设置值
        response.getWriter().print(image);//返回图形码
    }
}
