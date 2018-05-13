package sk.akademiasovy.tipos.server.resources;



import javax.servlet.Registration;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonProperty;
import sk.akademiasovy.tipos.server.Credentials;
import sk.akademiasovy.tipos.server.User;
import sk.akademiasovy.tipos.server.db.MySQL;


@Path("/auth")
public class Login {

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkCredentials(Credentials credential){
        System.out.println(credential.getUsername());
        MySQL mySQL = new MySQL();
        User user=mySQL.getUser(credential.login, credential.password);
        if(user==null){
            return "{}";
        }
        else{
            String result;
            result="{\"firstname\":\""+user.getFirstname()+"\" , ";
            result+="\"lastname\":\""+user.getLastname()+"\" , ";
            result+="\"email\":\""+user.getEmail()+"\" , ";
            result+="\"login\":\""+user.getLogin()+"\" , ";
            result+="\"token\":\""+user.getToken()+"\"}";
            return result;
        }

    }

    @GET
    @Path("/logout/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public String logout(@PathParam("token")  String token){
        MySQL mySQL = new MySQL();
        mySQL.logout( token);
        return "{}";
    }


    @POST
    @Path("/registration")
    @Produces(MediaType.APPLICATION_JSON)
    public String  createNewUser (sk.akademiasovy.tipos.server.Registration registration)
    {
        MySQL mySQL = new MySQL();
        boolean exist = mySQL.checkIfEmailOrLoginExist(registration.login.trim(),registration.email.trim());
        if(exist)
        {
            System.out.println("Duplicate");
        }
        else
        {
            System.out.println("Do Registration");
            mySQL.insertNewUserToDatabase(registration);
        }

        return "{}";

    }

}