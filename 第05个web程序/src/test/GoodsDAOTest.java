package test;

import dao.GoodsDAO;
import entity.Goods;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class GoodsDAOTest {

    private GoodsDAO dao = new GoodsDAO();

    @Test
    public void insert() {
        for (int i = 11; i <= 20; i++)
            dao.insert(new Goods("name" + i, "description" + i, "image" + i, 0));
    }

    @Test
    public void count() throws SQLException {
        System.out.println(dao.count("ame10"));
    }

    @Test
    public void getListByName() throws SQLException {
        List<Goods> list = dao.getListByName(1, 4, "ame");
        for (Goods goods : list)
            System.out.println(goods);
    }

    @Test
    public void getById() throws SQLException {
        System.out.println(dao.getById(1));
    }

    @Test
    public void updateStatusById() throws SQLException {
        Goods goods = dao.getById(1);
        goods.setStatus(1);
        dao.updateStatusById(goods);
    }

    @Test
    public void delete(){
        dao.delete(1);
    }
}
