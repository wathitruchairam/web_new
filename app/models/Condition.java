package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by ae_acer on 10/30/2018.
 */
@Entity
@Table(name = "tb_Condition")
public class Condition extends Model {
        @Id
        private String id;
        private String pro;
        private String repair;
        private String rate;

    public Condition() {

    }

    public Condition(String id, String pro, String repair, String rate) {
        this.id = id;
        this.pro = pro;
        this.repair = repair;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getRepair() {
        return repair;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public static Model.Finder<String,Condition> find = new Model.Finder<String, Condition>(String.class,Condition.class);

    public static List<Condition> list(){
        return find.all();
    }

    public static void create(Condition condition){
        condition.save();
    }

    public static void update(Condition condition){
        condition.update();
    }

    public static  void delete(Condition condition){
        condition.delete();
    }
}
