package controllers;

import models.*;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.data.Form;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ae_acer on 10/30/2018.
 */
public class Orderlist extends Controller {
    public static Form<User>userForm= Form.form(User.class);
    public static List<OrdersDetail>ordersDetailList = new ArrayList<OrdersDetail>();
    public static User user;
    public static Product product;
    public static OrdersDetail ordersDetail;
    public static Orders orders;

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
        user = User.finder.byId(session("uid"));
        List<Basket>basketList = new ArrayList<Basket>();
        if(Cache.get("basketList")!= null){
            basketList = (List<Basket>) Cache.get("basketList");
        }
        return Application.main(checkBill.render(basketList,user));
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
        return redirect("/listOrderuser");
    }

    public static Result listorderuser(){
        ordersDetailList = OrdersDetail.list();
        return Application.main(listOrderuser.render(ordersDetailList));
    }

    public static Result listorder(){
        ordersDetailList = OrdersDetail.list();
        return Application.ok(listorder.render(ordersDetailList));
    }





//    public static Result editReceiver() {
//        User datauesr = User.finder.byId(session("uid"));
//        userForm = Form.form(User.class).fill(user);
//        return Application.main(receiver.render(userForm));
//    }

    //ยังไม่สามารถแก้ไขได้
//    public static Result updateReceiver() {
//        Form<User> newUser = userForm.bindFromRequest();
//        if (newUser.hasErrors()) {
//            flash("Error", "ข้อมูลไม่สมบูรณ์");
//            return Application.main(receiver.render(newUser));
//        } else {
//            user = newUser.get();
//            user.setUsername(session("uusername"));
//            user.setPassword(session("upassword"));
//            user.setStatus("status");
//            User.update(user);
//            User user = User.finder.byId(session("uid"));
//            List<Basket> basketList = new ArrayList<Basket>();
//            if (Cache.get("basketList") != null) {
//                basketList = (List<Basket>) Cache.get("basketList");
//            }
//            return Application.main(checkBill.render(basketList, user));
//        }
//    }
}
