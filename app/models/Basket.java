package models;

/**
 * Created by ae_acer on 10/20/2018.
 */
public class Basket {
    private Product product;
    private int amount;

    public Basket() {

    }

    public Basket(Product product, int amount) {
        this.product = product;
        this.amount = amount;
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
}
