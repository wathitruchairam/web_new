package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ae_acer on 9/27/18.
 */
@Entity
@Table(name = "tb_Types")
public class Types extends Model {
    @Id
    private String id;
    private  String name;

    @OneToMany(mappedBy = "types", cascade = CascadeType.ALL)

    private List<Product>productList = new ArrayList<Product>();

    public Types() {

    }

    public Types(String id, String name) {
        this.id = id;
        this.name = name;
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


    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public static Finder<String,Types> find = new Finder<String,Types>(String.class,Types.class);

    public static List<Types>list(){
        return find.all();
    }

    public static void create(Types types){
        types.save();
    }

    public static void update(Types types){
        types.update();
    }

    public static void delete(Types types){
        types.delete();
    }
}
