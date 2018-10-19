package controllers;

import models.Product;
import models.Types;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ae_acer on 9/29/18.
 */
public class AddProducts extends Controller {
    public static List<Product> productList = new ArrayList<Product>();
    public static Form<Product> productForm=Form.form(Product.class);
    public static Product product;
    public static String comPicPath = Play.application().configuration().getString("com_pic_path");

    public static List<Types> typesList = new ArrayList<Types>();


    public static Result listProduct(){
        productList = Product.list();
        return Application.main(listProduct.render(productList));
    }

    public static Result inputProduct(){
        typesList = Types.list();
        productForm = Form.form(Product.class);
        return Application.main(inputProduct.render(productForm, typesList));
    }

    public static Result saveProduct() {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        String fileName, contentType;


        typesList = Types.list();
        Form<Product> newProduct = productForm.bindFromRequest();
        if (newProduct.hasErrors()) {
            flash("Error", "ท่านป้อนข้อมูลไม่ถูกต้องกรุณาป้อนข้อมูลใหม่");
            return Application.main(inputProduct.render(newProduct, typesList));
        } else {
            product = newProduct.get();
            Product chk;
            chk = Product.find.byId(product.getId());
            if (chk != null) {
                flash("Error", "รหัสสินค้าซ้ำ กรุณาป้อนข้อมูลใหม่");
                return Application.main(inputProduct.render(newProduct, typesList));
            } else {
                if (picture != null) {
                    contentType = picture.getContentType();
                    File file = picture.getFile();
                    fileName = picture.getFilename();
                    if (contentType.startsWith("images")) {
                        return Application.main(inputProduct.render(newProduct, typesList));
                    }
                    fileName = product.getId() + fileName.substring(fileName.lastIndexOf("."));
                    file.renameTo(new File(comPicPath, fileName));
                    product.setPicture(fileName);
                    Product.create(product);
                }
                return listProduct();
            }
        }
    }



    public static Result editProduct(String id){
        product = Product.find.byId(id);
        if(product == null){
            return listProduct();
        }else {
            typesList = Types.list();
            productForm=Form.form(Product.class).fill(product);
            return Application.main(editProduct.render(productForm, typesList));
        }
    }

    public static  Result updateProduct(){
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("picture");
        typesList = Types.list();
        Form<Product> newProduct = productForm.bindFromRequest();
        if(newProduct.hasErrors()){
            flash("Error","รหัสสินค้าซ้ำ กรุณาป้อนข้อมูลใหม่");
            return Application.main(inputProduct.render(newProduct, typesList));
        }else {
            product=newProduct.get();
            if (picture != null) {
                String fileName = picture.getFilename();
                String extension = fileName.substring(fileName.indexOf("."));
                String realName = product.getId() + extension;
                File file = picture.getFile();
                File temp = new File("public/images/product/" + realName);
                if (temp.exists()) {
                    temp.delete();
                }
                file.renameTo(new File("public/images/product/" + realName));
                product.setPicture(realName);
            }
            Product.update(product);
            return listProduct();
        }
    }

    public static Result deleteProduct(String id){
        product = Product.find.byId(id);
        if(product != null){
            File temp = new File("public/images/product/" + product.getPicture());
            temp.delete();
            Product.delete(product);
        }
        return listProduct();
    }
    public static Result listdetail(String Id){
        int i;
        for (i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equals(Id)) {
                break;
            }
        }
        return Application.main(listdetail.render(productList.get(i)));
    }


}
