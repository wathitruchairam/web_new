package controllers;

import models.*;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import scala.Int;
import views.html.*;

import java.util.ArrayList;
import java.util.List;


public class Application extends Controller {
    public static List<HomePicture> homePictureList = new ArrayList<HomePicture>();
    public static List<ProMotion> proMotionList = new ArrayList<ProMotion>();
    public static List<Product> productList = new ArrayList<Product>();

    public static Result main(Html content){
        homePictureList = HomePicture.list();
        proMotionList = ProMotion.list();
        return ok(main.render(content,homePictureList,proMotionList));
    }

    public static Result index() {
        productList = Product.list();
        return main(home.render(productList));
    }


}
