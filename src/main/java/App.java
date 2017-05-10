import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/new_stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/new_stylist", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String stylistName = request.queryParams("name");
      Stylist newStylist = new Stylist(stylistName);
      newStylist.save();
      String url = String.format("/");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/new_client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients/new_client", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String clientName = request.queryParams("name");
      int testStylistId = Integer.parseInt(request.queryParams("stylist"));
      Client newClient = new Client(clientName, testStylistId);
      newClient.save();
      String url = String.format("/stylists/%d", testStylistId);
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/client/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("client",Client.find(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int holder = Integer.parseInt(request.params(":id"));
      model.put("clients",Client.findClients(holder));
      model.put("stylist",Stylist.find(holder));
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int stylist = Integer.parseInt(request.params(":id"));
      model.put("stylist", Stylist.find(stylist));
      model.put("template", "templates/stylist_client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/clients/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String clientName = request.queryParams("name");
      int stylistId = Integer.parseInt(request.params(":id"));
      Client newClient = new Client(clientName, stylistId);
      newClient.save();
      String url = String.format("/stylists/%d", stylistId);
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id/edit_stylist", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("template", "templates/edit_stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/edit_stylist", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String editName = request.queryParams("edit_name");
      stylist.editStylist(editName);
      String url = String.format("/stylists/%d", stylist.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/client/:id/edit_client", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      model.put("client", client);
      model.put("template", "templates/edit_client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients/:id/edit_client", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      String editName = request.queryParams("edit_name");
      client.editClient(editName);
      String url = String.format("/client/%d", client.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/deleteStylists", (request,response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist.delete();
      String url = String.format("/");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/deleteClients", (request,response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client.delete();
      String url = String.format("/");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists/:id/delete", (request,response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int stylistId = Integer.parseInt(request.params(":id"));
      Stylist.deleteStylist(stylistId);
      String url = String.format("/");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/client/:id/delete", (request,response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int clientId = Integer.parseInt(request.params(":id"));
      Client.deleteClient(clientId);
      String url = String.format("/");
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
