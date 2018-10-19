package models;

import com.avaje.ebean.Expr;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by ae_acer on 10/11/18.
 */
@Entity
@Table(name = "tb_User")
public class User extends Model{
    @Id
    private String id;
    private String name;
    private String status;
    private String password;

    public User() {

    }

    public User(String id, String name, String status, String password) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public static User authen(String uid, String upass) {
        User user = finder.where().and(
                Expr.eq("id", uid),
                Expr.eq("password", upass)
        ).findUnique();
        return user;
    }
}