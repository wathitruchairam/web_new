package controllers;

import models.Modeorders;
import play.mvc.Controller;
import play.Play;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by ae_acer on 10/31/2018.
 */
public class Addmodeorders extends Controller {

    public static List<Modeorders> modeordersList = new ArrayList<Modeorders>();
    public static Form<Modeorders> modeordersForm=Form.form(Modeorders.class);
    public static Modeorders modeorders;
    public static String comPicPathMode = Play.application().configuration().getString("com_pic_path_mode");

    public static Result listModeorders(){
        modeordersList = Modeorders.list();
        return Application.main(listModeorders.render(modeordersList));
    }

    public static Result inputModeorders(){
        modeordersForm = Form.form(Modeorders.class);
        return Application.main(inputModeorders.render(modeordersForm));
    }

    public static Result saveModeorders(){
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        Form<Modeorders> newMode = modeordersForm.bindFromRequest();

        if (newMode.hasErrors()) {
            flash("Error", "ท่านป้อนข้อมูลไม่ถูกต้องกรุณาป้อนข้อมูลใหม่");
            return Application.main(inputModeorders.render(newMode));
        } else {
            modeorders = newMode.get();
            Modeorders chk;
            chk = Modeorders.find.byId(modeorders.getId());
            if (chk != null) {
                flash("Error", "รหัสซ้ำ กรุณาป้อนข้อมูลใหม่");
                return Application.main(inputModeorders.render(newMode));
            } else {
                if (picture != null) {
                    contentType = picture.getContentType();
                    File file = picture.getFile();
                    fileName = picture.getFilename();
                    if (contentType.startsWith("images")) {
                        return Application.main(inputModeorders.render(newMode));
                    }
                    fileName = modeorders.getId() + fileName.substring(fileName.lastIndexOf("."));
                    file.renameTo(new File(comPicPathMode, fileName));
                    modeorders.setPicture(fileName);
                    modeorders.create(modeorders);
                }
                return listModeorders();
            }
        }
    }

    public static Result deleteModeorders(String id){
        modeorders = Modeorders.find.byId(id);
        if(modeorders != null){
            File temp = new File("public/images/Mode/" + modeorders.getPicture());
            temp.delete();
            Modeorders.delete(modeorders);
        }
        return listModeorders();
    }
}
