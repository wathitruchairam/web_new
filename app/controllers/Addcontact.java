package controllers;

import models.Contact;
import models.HomePicture;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ae_acer on 10/19/2018.
 */
public class Addcontact extends Controller{
    public static Form<Contact> contactForm = Form.form(Contact.class);
    public static List<Contact> contactList = new ArrayList<Contact>();
    public static Contact contact;

    public static Result listcontact(){
        contactList = contact.list();
        return Application.main(listContact.render(contactList));
    }
//    public static Result inputContact(){
//        contactForm = Form.form(Contact.class);
//        return Application.ok(inputContact.render(contactForm));
//    }
    public static Result formContact(){
        return Application.main(inputContact.render());
    }

    public static Result postContact(){
        DynamicForm myForm = Form.form().bindFromRequest();
        String name,email,tel,subject,detail;
        name = myForm.get("cname");
        email = myForm.get("cemail");
        tel = myForm.get("ctel");
        subject = myForm.get("csubject");
        detail = myForm.get("cdetail");
        Contact con = new Contact(name,email,tel,subject,detail);
        Contact.create(con);
        return Application.main(inputContact.render());
    }



    public static Result deleteContact(String id){
        contact = Contact.find.byId(id);
        if(contact != null){
            Contact.delete(contact);
        }
        return listcontact();
    }

    public static Result listdetailContact(String Id){
        int i;
        for (i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getId().equals(Id)) {
                break;
            }
        }
        return Application.main(listDetailContact.render(contactList.get(i)));
    }

}
