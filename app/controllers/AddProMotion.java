package controllers;

import jdk.nashorn.internal.objects.PrototypeObject;
import models.HomePicture;
import models.ProMotion;
import models.Product;
import play.Play;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ae_acer on 10/18/2018.
 */
public class AddProMotion extends Controller {
    public static List<ProMotion> proMotionList = new ArrayList<ProMotion>();
    public static Form<ProMotion> proMotionForm = Form.form(ProMotion.class);
    public static ProMotion proMotion;
    public static String comPicPathPromotion = Play.application().configuration().getString("com_pic_path_promotion");

    public static Result listPromotion(){
        proMotionList = ProMotion.list();
        return Application.main(listPromotion.render(proMotionList));
    }

    public static Result inputPromotion(){
        proMotionForm = Form.form(ProMotion.class);
        return Application.main(inputPromotion.render(proMotionForm));
    }

    public static Result savePromotion(){
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        Form<ProMotion> newPromotion = proMotionForm.bindFromRequest();
        if (newPromotion.hasErrors()) {
            flash("Error", "ท่านป้อนข้อมูลไม่ถูกต้องกรุณาป้อนข้อมูลใหม่");
            return Application.main(inputPromotion.render(newPromotion));
        } else {
            proMotion = newPromotion.get();
            ProMotion chk;
            chk = ProMotion.find.byId(proMotion.getId());
            if (chk != null) {
                flash("Error", "รหัสสินค้าซ้ำ กรุณาป้อนข้อมูลใหม่");
                return Application.main(inputPromotion.render(newPromotion));
            } else {
                if (picture != null) {
                    contentType = picture.getContentType();
                    File file = picture.getFile();
                    fileName = picture.getFilename();
                    if (contentType.startsWith("images")) {
                        return Application.main(inputPromotion.render(newPromotion));
                    }
                    fileName = proMotion.getId() + fileName.substring(fileName.lastIndexOf("."));
                    file.renameTo(new File(comPicPathPromotion, fileName));
                    proMotion.setPicture(fileName);
                    ProMotion.create(proMotion);
                }
                return listPromotion();
            }
        }
    }


//    public static Result editPromotion(){
//        return Application.ok();
//    }
//
//    public static Result updatePromotion(){
//        return Application.ok();
//    }

    public static Result deletePromotion(String id){
        proMotion = ProMotion.find.byId(id);
        if(proMotion != null){
            File temp = new File("public/images/promotion/" + proMotion.getPicture());
            temp.delete();
            ProMotion.delete(proMotion);
        }
        return listPromotion();
    }
}
