package controllers;

import models.*;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ae_acer on 9/28/18.
 */
public class Show extends Controller {
    public static List<ProMotion> proMotionList = new ArrayList<ProMotion>();
    public static List<Product> productList = new ArrayList<Product>();
    public static Product product;
    public List<OrdersDetail>basketList = new ArrayList<OrdersDetail>();

    public static Result showPro(){
        productList = Product.list();
        List<Basket>basketList = (List<Basket>) Cache.get("basketList");
        return Application.main(showProduct.render(productList,basketList));
    }

    public static Result showPromotion(){
        proMotionList = ProMotion.list();
        return Application.main(showPromotion.render(proMotionList));
    }

    public static Result showProductsale(){
        productList = Product.list();

        List<Basket>basketList = (List<Basket>) Cache.get("basketList");

        return Application.main(showProductSale.render(productList,basketList));
    }

    public static Result addOrder(String id) {
        List<Basket> basketList = new ArrayList<Basket>();
        boolean found = false;
        if (Cache.get("basketList") != null) {
            basketList.addAll((List<Basket>) Cache.get("basketList"));
            for (int i = 0; i < basketList.size(); i++) {
                if (basketList.get(i).getProduct().getId().equals(id)) {
                    int amount = basketList.get(i).getAmount();
                    basketList.get(i).setAmount(amount + 1);
                    found = true;
                    break;
                }
            }
        }
        if(found == false){
            product = Product.find.byId(id);
            basketList.add(new Basket(product,1));
        }
        Cache.set("basketList",basketList);
        return redirect("/showProductSale");
    }

    public static Result removeItem(String id){
        List<Basket>basketList = new ArrayList<Basket>();
        if(Cache.get("basketList") != null){
            basketList.addAll((List<Basket>)Cache.get("basketList"));
            for(int i=0;i<basketList.size();i++){
                if (basketList.get(i).getProduct().getId().equals(id)){
                    basketList.remove(i);
                    break;
                }
            }
        }
        Cache.set("basketList",basketList);
        return redirect("/showProductSale");
    }

    public static Result checkBill(){
        List<Basket>basketList = new ArrayList<Basket>();
        if(Cache.get("basketList")!= null){
            basketList = (List<Basket>) Cache.get("basketList");
        }
        return Application.main(checkBill.render(basketList));
    }

    public static Result saveBill(){
        List<Basket>basketList = new ArrayList<Basket>();
        if(Cache.get("basketList") != null){
            Orders orders = new Orders();
            User user = User.finder.byId(session().get("uid"));
            orders.setDate(new Date());
            orders.setUser(user);
            orders.setStatus("order");
            orders.create(orders);

            basketList = (List<Basket>) Cache.get("basketList");
            for (int i=0;i<basketList.size();i++){
                OrdersDetail ordersDetail = new OrdersDetail();
                ordersDetail.setOrders(orders);
                ordersDetail.setProduct(basketList.get(i).getProduct());
                ordersDetail.setAmount(basketList.get(i).getAmount());
                OrdersDetail.create(ordersDetail);
            }
        }

        Cache.remove("basketList");
        return redirect("/showProductSale");
    }

}