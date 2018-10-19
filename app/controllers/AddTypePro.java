package controllers;

import models.Product;
import models.Types;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ae_acer on 10/18/2018.
 */
public class AddTypePro extends Controller{

    public static List<Types> typesList = new ArrayList<Types>();
    public static Form<Types> typesForm= Form.form(Types.class);
    public static Types types;

    public static Result listType(){
        typesList = Types.list();
        return Application.main(listType.render(typesList));
    }

    public static Result inputType(){
        typesForm= Form.form(Types.class);
        return Application.main(inputType.render(typesForm));
    }

    public static Result saveType(){
        Form<Types> newType = typesForm.bindFromRequest();
        if (newType.hasErrors()) {
            flash("Error", "ท่านป้อนข้อมูลไม่ถูกต้องกรุณาป้อนข้อมูลใหม่");
            return Application.main(inputType.render(newType));
        } else {
            types = newType.get();
            Types chk;
            chk = Types.find.byId(types.getId());
            if (chk != null) {
                flash("Error", "รหัสประเภทซ้ำ กรุณาป้อนข้อมูลใหม่");
                return Application.main(inputType.render(newType));
            } else {
                Types.create(types);
                return listType();
            }
        }
    }

    public static Result editType(String id){
        types = Types.find.byId(id);
        if(types == null){
            return listType();
        }else{
            typesForm=Form.form(Types.class).fill(types);
            return Application.main(editType.render(typesForm));
        }
    }

    public static Result updateType(){
        Form<Types> newType = typesForm.bindFromRequest();
        if (newType.hasErrors()){
            flash("msgError","ท่านป้อนข้อมุลไม่ถูกต้อง/ไม่สมบุรณ์ กรุณาตรวจสอบและแก้ไขให้ถูกต้อง");
            return Application.main(inputType.render(newType));
        }else{
            types=newType.get();
            Types.update(types);
            return listType();
        }
    }

    public static Result deleteType(String id){
        types = Types.find.byId(id);
        if(types != null){
            Types.delete(types);
        }
        return listType();
    }
}
