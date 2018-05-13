package sk.akademiasovy.tipos.server.resources;

import sk.akademiasovy.tipos.server.DrawNumbers;
import sk.akademiasovy.tipos.server.db.MySQL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Path("draw/{id}")
public class Draws
{

  @GET
  @Produces(MediaType.APPLICATION_JSON)
    public Response getDrawNumbers(@PathParam("id") int id)
  {
      MySQL mysql = new MySQL();
      DrawNumbers drawnum = mysql.getDrawNumbers(id);

      if(drawnum!=null)

      {
          String result="{\"bet1 \":"+drawnum.bet1+", ";
          result+="\"bet2 \":"+drawnum.bet2+", ";
          result+="\"bet3 \":"+drawnum.bet3+", ";
          result+="\"bet4 \":"+drawnum.bet4+", ";
          result+="\"bet5 \":"+drawnum.bet5+", ";
          result+= "\"date \":\""+drawnum.date +"\"} ";
          return Response.ok(result).build();


      }
      return Response.status(Response.Status.NOT_FOUND).build();


  }
}
