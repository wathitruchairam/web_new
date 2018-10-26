package controllers;

import models.User;
import play.data.Form;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ae_acer on 10/23/2018.
 */
public class Listuser {
    public static Form<User> userForm = Form.form(User.class);
    public static List<User> userList = new ArrayList<User>();
    public static User user;

    public static Result listUser(){
        userList = user.list();
        return Application.main(listUser.render(userList));
    }



    public static Result deleteUser(String id){
        user = User.finder.byId(id);
        if(user != null){
            User.delete(user);
        }
        return listUser();
    }

    public static Result listUserdetail(String Id){
        int i;
        for (i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(Id)) {
                break;
            }
        }
        return Application.main(listDetailUser.render(userList.get(i)));
    }
}