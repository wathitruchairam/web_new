package controllers;

import models.HomePicture;
import models.ProMotion;
import models.Product;
import models.User;
import play.api.templates.Html;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.util.ArrayList;
import java.util.List;


public class Application extends Controller {
    public static List<HomePicture> homePictureList = new ArrayList<HomePicture>();
    public static List<ProMotion> proMotionList = new ArrayList<ProMotion>();

    public static Result main(Html content){
        homePictureList = HomePicture.list();
        proMotionList = ProMotion.list();
        return ok(main.render(content,homePictureList,proMotionList));
    }

    public static Result index() {
        return main(home.render());
    }
    public static Result Login(){
        return ok(login.render());
    }

    public static Result getLogin() {
        DynamicForm myForm = Form.form().bindFromRequest();
        String uid = myForm.get("uid");
        String upass=myForm.get("upass");
        User user = User.authen(uid, upass);
        if(user == null){
            flash("loginError", "Invalid user or password");
            return ok(login.render());
        }else{
            session("uid", user.getId());
            session("uname", user.getName());
            session("ustatus", user.getStatus());
            return main(home.render());
        }
    }


}
