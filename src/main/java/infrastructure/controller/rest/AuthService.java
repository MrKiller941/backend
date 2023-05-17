package infrastructure.controller.rest;

import application.auth.IAuth;
import application.auth.user.User;
import infrastructure.builder.Build.Built;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class AuthService {

    @Inject @Built
    IAuth auth;
    
    @POST
    @Path("/auth")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response signIn(String dataJSON) {   
        Jsonb jsonb = JsonbBuilder.create();
        User user = jsonb.fromJson(dataJSON, User.class);
        boolean isAuth = auth.checkUserData(user);
        if(isAuth){
            String token = auth.createToken(user.getLogin());
            return Response.ok(jsonb.toJson(token)).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("/reg")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response signUp(String dataJSON) {            
        Jsonb jsonb = JsonbBuilder.create();          
        User user = jsonb.fromJson(dataJSON, User.class);
        boolean isReg = auth.registrateNewUser(user);
        if(isReg){
            String token = auth.createToken(user.getLogin());
            return Response.ok(jsonb.toJson(token)).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
