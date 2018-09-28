package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by ae_acer on 9/27/18.
 */
@Entity
@Table(name = "tb_Product")
public class Product extends Model {
    @Id
    private String id;
    private String name,brand,detail;
    private Double amount,price;
    private String picture;

    @ManyToOne()
    private Types types;

    public Product() {

    }

    public Product(String id, String name, String brand, String detail, Double amount, Double price, String picture, Types types) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.detail = detail;
        this.amount = amount;
        this.price = price;
        this.picture = picture;
        this.types = types;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Types getTypes() {
        return types;
    }

    public void setTypes(Types types) {
        this.types = types;
    }

    public static Model.Finder<String,Product> find = new Model.Finder<String, Product>(String.class,Product.class);


    public static List<Product> list(){
        return find.all();
    }

    public static void create(Product product){
        product.save();
    }

    public static void update(Product product){
        product.update();
    }

    public static  void delete(Product product){
        product.delete();
    }
}
