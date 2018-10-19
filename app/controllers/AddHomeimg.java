package controllers;

import models.HomePicture;
import models.Product;
import play.Play;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ae_acer on 10/18/2018.
 */
public class AddHomeimg extends Controller {
    public static List<HomePicture> homePictureList = new ArrayList<HomePicture>();
    public static Form<HomePicture> homePictureForm=Form.form(HomePicture.class);
    public static HomePicture homePicture;
    public static String comPicPathHome = Play.application().configuration().getString("com_pic_path_home");

    public static Result listImg(){
        homePictureList = HomePicture.list();
        return Application.main(listImg.render(homePictureList));
    }

    public static Result inputImg(){
        homePictureForm = Form.form(HomePicture.class);
        return Application.main(inputImg.render(homePictureForm));
    }

    public static Result saveImg(){
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        Form<HomePicture> newImg = homePictureForm.bindFromRequest();

        if (newImg.hasErrors()) {
            flash("Error", "ท่านป้อนข้อมูลไม่ถูกต้องกรุณาป้อนข้อมูลใหม่");
            return Application.main(inputImg.render(newImg));
        } else {
            homePicture = newImg.get();
            Product chk;
            chk = Product.find.byId(homePicture.getId());
            if (chk != null) {
                flash("Error", "รหัสสินค้าซ้ำ กรุณาป้อนข้อมูลใหม่");
                return Application.main(inputImg.render(newImg));
            } else {
                if (picture != null) {
                    contentType = picture.getContentType();
                    File file = picture.getFile();
                    fileName = picture.getFilename();
                    if (contentType.startsWith("images")) {
                        return Application.main(inputImg.render(newImg));
                    }
                    fileName = homePicture.getId() + fileName.substring(fileName.lastIndexOf("."));
                    file.renameTo(new File(comPicPathHome, fileName));
                    homePicture.setPicture(fileName);
                    HomePicture.create(homePicture);
                }
                return listImg();
            }
        }
    }

    public static Result deleteImg(String id){
        homePicture = HomePicture.find.byId(id);
        if(homePicture != null){
            File temp = new File("public/images/home/" + homePicture.getPicture());
            temp.delete();
            HomePicture.delete(homePicture);
        }
        return listImg();
    }

}
