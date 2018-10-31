package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.List;
import java.util.Random;

/**
 * Created by ae_acer on 10/31/2018.
 */
@Entity
@Table(name = "tb_Slip")
public class Slip extends Model {
    @Id
    private String id;
    private String slip_order;
    private String picture;

    public Slip() {
        setId();
    }

    public Slip(String id, String slip_order, String picture) {
        setId();
        this.slip_order = slip_order;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        Random random = new Random();
        id = Integer.toString(random.nextInt(100000)+1);
    }

    public String getSlip_order() {
        return slip_order;
    }

    public void setSlip_order(String slip_order) {
        this.slip_order = slip_order;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public static Model.Finder<String,Slip> find = new Model.Finder<String, Slip>(String.class,Slip.class);


    public static List<Slip> list(){
        return find.all();
    }

    public static void create(Slip slip){
        slip.save();
    }

    public static void update(Slip slip){
        slip.update();
    }

    public static  void delete(Slip slip){
        slip.delete();
    }
}
