package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by ae_acer on 10/31/2018.
 */
@Entity
@Table(name = "tb_Modeorders")
public class Modeorders extends Model {
    @Id
    private String id;
    private  String name;
    private String picture;

    public Modeorders() {

    }

    public Modeorders(String id, String name, String picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public static Model.Finder<String,Modeorders> find = new Model.Finder<String, Modeorders>(String.class,Modeorders.class);


    public static List<Modeorders> list(){
        return find.all();
    }

    public static void create(Modeorders modeorders){
        modeorders.save();
    }

    public static void update(Modeorders modeorders){
        modeorders.update();
    }

    public static  void delete(Modeorders modeorders){
        modeorders.delete();
    }
}
