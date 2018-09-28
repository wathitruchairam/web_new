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

    public static Result showPro(){
        productList = Product.list();
        return Application.main(showProduct.render(productList));
    }
}
