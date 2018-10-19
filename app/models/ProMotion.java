package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by ae_acer on 10/18/2018.
 */
@Entity
@Table(name = "tb_Promotion")
public class ProMotion extends Model {
    @Id
    private String id;
    private String picture;

    public ProMotion() {

    }

    public ProMotion(String id, String picture) {
        this.id = id;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public static Model.Finder<String,ProMotion> find = new Model.Finder<String, ProMotion>(String.class,ProMotion.class);


    public static List<ProMotion> list(){
        return find.all();
    }

    public static void create(ProMotion proMotion){
        proMotion.save();
    }

    public static void update(ProMotion proMotion){
        proMotion.update();
    }

    public static  void delete(ProMotion proMotion){
        proMotion.delete();
    }
}
