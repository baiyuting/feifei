package dao;

import entity.Goods;
import sun.security.pkcs11.Secmod;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品表 处理
 * <p>
 * 分页显示最新商品列表(只显示 商品名称，商品状态)
 * select id,name,status from goods order by id desc limit ?,?;
 * <p>
 * 文本框中根据 商品名称 模糊查询列表信息
 * <p>
 * 商品总数
 * <p>
 * 商品详情 （根据商品 id 查询商品详情，此时包含商品所有信息）
 * <p>
 * 商品添加
 * <p>
 * 商品删除
 * <p>
 * 修改商品上下架状态
 */
public class GoodsDAO {

    /**
     * 分页显示最新商品列表(只显示 商品名称，商品状态)，此时 文本框中根据 商品名称 模糊查询列表信息
     * select id,name,status from goods where name like %?% order by id desc limit ?,?;
     *
     * @param startIndex 从哪一行开始往后面分，不包括这一行
     * @param pageSize   查询的记录条数
     * @param name       商品名称
     *                   name 为 null 表示没有商品名称，
     *                   name 有值 按照名字模糊查询
     * @return 商品列表信息，没有查到 list.size()==0
     */
    public List<Goods> getListByName(Integer startIndex, Integer pageSize, String name) throws SQLException {
        List<Goods> list = new ArrayList<>();
        DBUtil.getInstance().getConn();//建立连接
        List params = new ArrayList();
        params.add(startIndex);
        params.add(pageSize);
        ResultSet resultSet = null;
        if (null != name && !"".equals(name.trim())) {
            params.add(0, "%" + name.trim() + "%"); // 设置 name 值
            resultSet = DBUtil.getInstance().query("select id,name,status from goods where name like ? order by id desc limit ?,?;", params);
        } else
            resultSet = DBUtil.getInstance().query("select id,name,status from goods order by id desc limit ?,?;", params);
        while (resultSet.next()) {
            Goods goods = new Goods(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
            list.add(goods);
        }
        DBUtil.getInstance().close();//关闭连接
        return list;
    }

    /**
     * 商品总数
     * <p>
     * 有需要根据 name 查询的情况
     * <p>
     * select count(1) from goods where name like %?%
     *
     * @param name 商品名称
     *             name==null 或者 空字符串 的时候 不用考虑本条件
     *             name 有值得时候 考虑按照 name 值 模糊查询
     * @return 商品列表总数
     */
    public Integer count(String name) throws SQLException {
        DBUtil.getInstance().getConn();
        ResultSet resultSet = null;
        if (null != name && !"".equals(name.trim())) {
            List params = new ArrayList();
            params.add("%" + name.trim() + "%");
            resultSet = DBUtil.getInstance().query("select count(1) from goods where name like ?", params);
        } else
            resultSet = DBUtil.getInstance().query("select count(1) from goods", null);
        resultSet.next();//光标移到下一行开始取值，如果有值 这个 next() 会返回 true 的，在 本sql中应该是会有值得
        Integer count = resultSet.getInt(1);
        DBUtil.getInstance().close();
        return count;
    }

    /**
     * 根据商品 id 查询商品详情，此时包含商品所有信息
     * select id,name,description,image,status from goods where id=#{id};//查询某个商品详细信息
     *
     * @param id 商品id, 不能为 null
     * @return 商品详细信息
     */
    public Goods getById(Integer id) throws SQLException {
        Goods goods = null;
        DBUtil.getInstance().getConn();
        List params = new ArrayList();
        params.add(id);
        ResultSet resultSet = DBUtil.getInstance().query("select id,name,description,image,status from goods where id=?", params);
        if (resultSet.next())// 如果有数据的话
            goods = new Goods(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getInt(5));
        DBUtil.getInstance().close();
        return goods;
    }

    /**
     * 商品添加
     * insert into goods(name,description,image,status) values(?,?,?,?);
     *
     * @param goods 需要添加的商品，不能为 null ，内部 name,description,image,status 字段必须有值
     */
    public void insert(Goods goods) {
        DBUtil.getInstance().getConn();
        List<Object> params = new ArrayList<>();
        params.add(goods.getName());
        params.add(goods.getDescription());
        params.add(goods.getImage());
        params.add(goods.getStatus());
        DBUtil.getInstance().update("insert into goods(name,description,image,status) values(?,?,?,?);", params);
        DBUtil.getInstance().close();
    }

    /**
     * 商品删除，根据 id 删除商品
     * delete from goods where id=#{id};
     *
     * @param id 要删除的商品 id
     */
    public void delete(Integer id) {
        DBUtil.getInstance().getConn();
        List params = new ArrayList();
        params.add(id);
        DBUtil.getInstance().update("delete from goods where id=?;", params);
        DBUtil.getInstance().close();
    }

    /**
     * 修改商品上下架状态
     * update goods set status=#{status} where id=#{id};
     * 更新某个商品的上下架状态
     *
     * @param goods 不能为 null ，里面的所有字段也不能为 null
     */
    public void updateStatusById(Goods goods) {
        DBUtil.getInstance().getConn();
        List params = new ArrayList();
        params.add(goods.getStatus());
        params.add(goods.getId());
        DBUtil.getInstance().update("update goods set status=? where id=?;", params);
        DBUtil.getInstance().close();
    }

}
