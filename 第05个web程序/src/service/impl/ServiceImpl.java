package service.impl;

import dao.GoodsDAO;
import dao.UserDAO;
import entity.Goods;
import entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceImpl implements Service {

    // userDAO user表处理
    private UserDAO userDAO = new UserDAO();

    //goodsDAO goods表处理
    private GoodsDAO goodsDAO = new GoodsDAO();

    @Override
    public User verifyUser(String username, String password, String sessionVerifyCode, String userVerifyCode) {
        if (null != username && !"".equals(username.trim()) && null != password && !"".equals(password.trim()) &&
                null != sessionVerifyCode && !"".equals(sessionVerifyCode) && null != userVerifyCode && !"".equals(userVerifyCode) &&
                sessionVerifyCode.equals(userVerifyCode)) {//如果 username, password, sessionVerifyCode, userVerifyCode 非空，并且 验证码比对正确
            try {
                User user = userDAO.findByUsername(username);//通过数据库 按照 username 查询 user 信息
                if (password.equals(user.getPassword()))//如果数据库中查询 user 的 password 和 用户输入的 password 一致，返回 user 信息
                    return user;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> getPage(String sPageNo, String sPageSize, String name) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        int pageNo = null != sPageNo && sPageNo.matches("[1-9]\\d*") ? Integer.parseInt(sPageNo) : 1;//页号信息，如果不是数字，设为1
        map.put("pageNo", pageNo);
        int pageSize = null != sPageSize && sPageSize.matches("[1-9]\\d*") ? Integer.parseInt(sPageSize) : 10;//页面大小信息，如果不是数字，设为10
        map.put("pageSize", pageSize);
        map.put("list", goodsDAO.getListByName((pageNo - 1) * pageSize, pageSize, name));//获取 商品列表信息
        map.put("count", goodsDAO.count(name));//统计总数
        map.put("name", name);
        return map;
    }

    @Override
    public void addGoods(HttpServletRequest request) {
        try {
            Goods goods = new Goods();
            List<FileItem> list = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);//解析 request
            for (FileItem item : list) {
                if (item.isFormField()) { // 如果不是文件，是属性值
                    if ("name".equals(item.getFieldName()))
                        goods.setName(item.getString("utf-8"));//设置名称
                    else if ("description".equals(item.getFieldName()))
                        goods.setDescription(item.getString("utf-8"));//设置商品描述
                } else { //此时找到上传的文件
                    String path = request.getServletContext().getRealPath("/") + "image";//将图片上传到 image 文件夹中
                    File file = new File(path + "/" + item.getName());
                    if (!file.getParentFile().exists())//如果父目录不存在，创建目录
                        file.getParentFile().mkdirs();
                    item.write(file);//文件写入
                    goods.setImage("/image/" + file.getName());//设置存储图片路径
                }
            }
            goods.setStatus(0);//商品没有上架
            goodsDAO.insert(goods);//添加商品
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(String idStr, String statusStr) {
        if (null != idStr && null != statusStr && idStr.matches("\\d+") && statusStr.matches("\\d+"))//必须要为 数字
            goodsDAO.updateStatusById(new Goods(Integer.parseInt(idStr), Integer.parseInt(statusStr)));//执行更新操作
    }

    @Override
    public Goods getGoodsById(String sid) {
        if (null == sid || !sid.matches("\\d+"))//如果 传入参数为 null 或者 sid 不是数字，返回 null
            return null;
        try {
            return goodsDAO.getById(Integer.parseInt(sid));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
