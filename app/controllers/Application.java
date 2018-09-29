package controllers;

import play.api.templates.Html;
import play.mvc.*;
import views.html.*;


public class Application extends Controller {



    public static Result main(Html content){
        return ok(main.render(content));
    }

    public static Result index() {
        return main(home.render());
    }



}
