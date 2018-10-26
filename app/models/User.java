package models;

import com.avaje.ebean.Expr;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Random;

/**
 * Created by ae_acer on 10/11/18.
 */
@Entity
@Table(name = "tb_User")
public class User extends Model{
    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String status;

    public User() {
        setId();
        setStatus();
    }

    public User( String name,String username, String password ,String email, String phone, String status) {
        setId();
        setStatus();
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getStatus() {
        return status;
    }

    public void setStatus() {
        status = "user";
    }

    public static Finder<String, User> finder =
            new Finder<String, User>(String.class, User.class);

    public static List<User> list() {
        return finder.all();
    }
    public static void create(User user){
        user.save();
    }
    public static void update(User user){
        user.update();
    }
    public static void delete(User user){
        user.delete();
    }

    public static User authen(String uuser, String upass) {
        User user = finder.where().and(
                Expr.eq("Username", uuser),
                Expr.eq("Password", upass)
        ).findUnique();
        return user;
    }
}