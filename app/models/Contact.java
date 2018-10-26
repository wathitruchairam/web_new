package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Random;

/**
 * Created by ae_acer on 10/19/2018.
 */
@Entity
@Table(name = "tb_Contact")
public class Contact extends Model {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String subject;
    private String description;

    public Contact() {
        setId();
    }

    public Contact(String name, String email, String phone, String subject, String description) {
        setId();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subject = subject;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        Random random = new Random();
        id = Integer.toString(random.nextInt(100000)+1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Model.Finder<String,Contact> find = new Model.Finder<String, Contact>(String.class,Contact.class);

    public static List<Contact> list(){
        return find.all();
    }

    public static void create(Contact contact){
        contact.save();
    }

    public static  void delete(Contact contact){
        contact.delete();
    }
}
