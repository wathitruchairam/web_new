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
@Table(name = "tb_Homeimg")
public class HomePicture extends Model{
    @Id
    private String id;
    private String picture;

    public HomePicture() {

    }

    public HomePicture(String id, String picture) {
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

    public static Model.Finder<String,HomePicture> find = new Model.Finder<String, HomePicture>(String.class,HomePicture.class);


    public static List<HomePicture> list(){
        return find.all();
    }

    public static void create(HomePicture homePicture){
        homePicture.save();
    }

    public static void update(HomePicture homePicture){
        homePicture.update();
    }

    public static  void delete(HomePicture homePicture){
        homePicture.delete();
    }
}
