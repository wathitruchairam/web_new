package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Random;

/**
 * Created by ae_acer on 10/20/2018.
 */
@Entity
@Table(name = "tb_OrdersDetsil")
public class OrdersDetail extends Model {
    @Id
    private String id;
    @ManyToOne
    private Orders orders;
    @ManyToOne
    private Product product;
    private int amount;

    public OrdersDetail() {
        setId();
    }

    public OrdersDetail( Orders orders, Product product, int amount) {
        setId();
        this.orders = orders;
        this.product = product;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        int n;
        Random random = new Random();
        n = random.nextInt(100000)+1;
        id = "b1-" + Integer.toString(n);
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static Finder<String,OrdersDetail>finder = new Finder<String, OrdersDetail>(String.class, OrdersDetail.class);

    public static List<OrdersDetail> list(){
        return finder.all();
    }

    public static void  create(OrdersDetail ordersDetail){
        ordersDetail.save();
    }

    public static void update(OrdersDetail ordersDetail){
        ordersDetail.update();
    }

    public static void delete(OrdersDetail ordersDetail){
        ordersDetail.delete();
    }
}
