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
    public static List<Condition>conditionList= new ArrayList<Condition>();
    public static List<Modeorders>modeordersList= new ArrayList<Modeorders>();
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

    public static Result showCondition(){
        conditionList = Condition.list();
        return Application.main(ShowCondition.render(conditionList));
    }

    public static Result showProductsale(){
        productList = Product.list();

        List<Basket>basketList = (List<Basket>) Cache.get("basketList");

        return Application.main(showProductSale.render(productList,basketList));
    }

    public static Result showModeorders(){
        modeordersList = Modeorders.list();
        return Application.main(ShowModeorders.render(modeordersList));
    }

}