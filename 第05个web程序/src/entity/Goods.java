package entity;

/**
 * 商品表 对应 类
 * <p>
 * id 自增 主键
 * 名称 name NOT NULL
 * 描述 description NOT NULL
 * 图片 image NOT NULL
 * 上架状态 status NOT NULL
 */
public class Goods {

    private Integer id;//商品id
    private String name;//商品名称
    private String description;//商品描述
    private String image;//商品图片
    private Integer status;//商品上架状态

    public Goods() {
    }

    public Goods(Integer id, Integer status) {
        this.id = id;
        this.status = status;
    }

    public Goods(Integer id, String name, Integer status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Goods(String name, String description, String image, Integer status) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.status = status;
    }

    public Goods(Integer id, String name, String description, String image, Integer status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", status=" + status +
                '}';
    }
}
