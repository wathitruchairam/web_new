package controllers;

import models.Condition;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ae_acer on 10/30/2018.
 */
public class AddCondition extends Controller {
    public static List<Condition> conditionList = new ArrayList<Condition>();
    public static Form<Condition>conditionForm =Form.form(Condition.class);
    public static Condition condition;

    public static Result listCondition(){
        conditionList = Condition.list();
        return Application.main(listCondition.render(conditionList));
    }

    public static Result inputCondition(){
        conditionForm = Form.form(Condition.class);
        return Application.main(inputCondition.render(conditionForm));
    }

    public static Result saveCondition() {
        Form<Condition> newCondition = conditionForm.bindFromRequest();
        if(newCondition.hasErrors()){
            flash("Error","ท่านป้อนข้อมูลไม่ถูกต้องกรุณาป้อนข้อมูลใหม่");
            return Application.main(inputCondition.render(newCondition));
        }else {
            condition=newCondition.get();
            Condition chk;
            chk = Condition.find.byId(condition.getId());
            if(chk!=null){
                flash("Error","รหัสสินค้าซ้ำ กรุณาป้อนข้อมูลใหม่");
                return Application.main(inputCondition.render(newCondition));
            }else {
                Condition.create(condition);
                return listCondition();
            }
        }
    }

    public static Result editCondition(String id){
        condition = Condition.find.byId(id);
        if(condition == null){
            return listCondition();
        }else {
            conditionForm=Form.form(Condition.class).fill(condition);
            return Application.main(editCondition.render(conditionForm));
        }
    }

    public static  Result updateCondition(){
        Form<Condition> newCondition = conditionForm.bindFromRequest();
        if(newCondition.hasErrors()){
            flash("Error","รหัสสินค้าซ้ำ กรุณาป้อนข้อมูลใหม่");
            return Application.main(inputCondition.render(newCondition));
        }else {
            condition=newCondition.get();
            Condition.update(condition);
            return listCondition();
        }
    }

    public static Result deleteCondition(String id){
        condition = Condition.find.byId(id);
        if(condition != null){
            Condition.delete(condition);
        }
        return listCondition();
    }

    public static Result listDetailCondition(String Id){
        int i;
        for (i = 0; i < conditionList.size(); i++) {
            if (conditionList.get(i).getId().equals(Id)) {
                break;
            }
        }
        return Application.main(listDetailCondition.render(conditionList.get(i)));
    }
}
