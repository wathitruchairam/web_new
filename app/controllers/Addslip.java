package controllers;

import models.Slip;
import play.Play;
import play.data.Form;
import play.mvc.*;
import play.mvc.Result;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by ae_acer on 10/31/2018.
 */
public class Addslip extends Controller {

    public static Form<Slip>slipForm = Form.form(Slip.class);
    public static Slip slip;
    public static String comPicPathSlip = Play.application().configuration().getString("com_pic_path_slip");

    public static Result inputSlip(){
        slipForm = Form.form(Slip.class);
        return Application.main(inputSlip.render(slipForm));
    }

    public static Result saveSlip(){
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;
        Form<Slip> newSlip = slipForm.bindFromRequest();
        if (newSlip.hasErrors()) {
            flash("Error", "ท่านป้อนข้อมูลไม่ถูกต้องกรุณาป้อนข้อมูลใหม่");
            return Application.main(inputSlip.render(newSlip));
        } else {
            slip = newSlip.get();
            Slip chk;
            chk = Slip.find.byId(slip.getId());
            if (chk != null) {
                flash("Error", "รหัสสินค้าซ้ำ กรุณาป้อนข้อมูลใหม่");
                return Application.main(inputSlip.render(newSlip));
            } else {
                if (picture != null) {
                    contentType = picture.getContentType();
                    File file = picture.getFile();
                    fileName = picture.getFilename();
                    if (contentType.startsWith("images")) {
                        return Application.main(inputSlip.render(newSlip));
                    }
                    fileName = slip.getId() + fileName.substring(fileName.lastIndexOf("."));
                    file.renameTo(new File(comPicPathSlip, fileName));
                    slip.setPicture(fileName);
                    Slip.create(slip);
                }
                return inputSlip();
            }
        }
    }


}
