package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

/**
 * Created by ae_acer on 10/30/2018.
 */
public class Access extends Controller {
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
            session("uaddress", user.getAddress());
            session("uphone", user.getPhone());
            session("ustatus", user.getStatus());
            return Application.index();
        }
    }

    public static Result Logout() {
        session().clear();
        return Application.index();
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
        String address = myForm.get("Raddress");
        String phone = myForm.get("Rphone");
        String stutus   = myForm.get("status");
        u = new User(name,username,password,email,address,phone,stutus);
        User.create(u);
        return ok(login.render());
    }
}
