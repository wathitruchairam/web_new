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

    public static Result postLogin() {
        DynamicForm myForm = Form.form().bindFromRequest();
        String uuser = myForm.get("uuser");
        String upass=myForm.get("upass");
        User user = User.authen(uuser, upass);
        if(user == null){
            flash("loginError", "Invalid user or password");
            return ok(login.render());
        }else{
            session("uid", user.getId());
            session("uname", user.getName());
            session("uusername",user.getUsername());
            session("uemail", user.getEmail());
            session("uphone", user.getPhone());
            session("ustatus", user.getStatus());
            return main(home.render());
        }
    }

    public static Result Logout() {
        session().clear();
        return main(home.render());
    }


    public static Result formregister(){
        return ok(register.render());
    }

    public static Result postRegister(){
        User u;
        DynamicForm myForm = Form.form().bindFromRequest();
        String name = myForm.get("Rname");
        String username = myForm.get("Rusername");
        String password = myForm.get("Rpass");
        String email = myForm.get("Remail");
        String phone = myForm.get("Rphone");
        String stutus   = myForm.get("status");
        u = new User(name,username,password,email,phone,stutus);
        User.create(u);
        return ok(login.render());
    }

}
