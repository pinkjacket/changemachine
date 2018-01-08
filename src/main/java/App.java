import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class App {
    public static void main(String[] args) {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/output", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ChangeMachine changeMachine = new ChangeMachine();
            String input = request.queryParams("input");
            Float inputFloat = Float.parseFloat(input);
            String change = changeMachine.makeChange(inputFloat);
            model.put("input", inputFloat);
            model.put("output", change);
            return new ModelAndView(model, "output.hbs");
        }, new HandlebarsTemplateEngine());
    }
}