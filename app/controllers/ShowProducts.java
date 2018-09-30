package controllers;

import models.Product;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ae_acer on 9/28/18.
 */
public class ShowProducts extends Controller {

    public static List<Product> productList = new ArrayList<Product>();
    public static Product product;
    public static Result showPro(){
        productList = Product.list();
        return Application.main(showProduct.render(productList));
    }

    public static Result listdetail(String Id){
        int i;
        for (i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equals(Id)) {
                break;
            }
        }
        return Application.main(listdetail.render(productList.get(i)));
    }
}
