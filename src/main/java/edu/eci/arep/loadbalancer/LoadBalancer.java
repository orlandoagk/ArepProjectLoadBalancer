package edu.eci.arep.loadbalancer;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arep.loadbalancer.components.connection.APIConnection;
import edu.eci.arep.loadbalancer.components.models.Procfile;
import org.json.JSONObject;

import static spark.Spark.*;

public class LoadBalancer {
    public static void main(String[] args) {
        APIConnection apiConnection = new APIConnection();
        port(getPort());
        get("/helloworld",(req,res)->"Hello World");
        post("/application/new",(req,res)->{
            System.out.println(req.body());
            JSONObject body = new JSONObject(req.body());
            try {
                Procfile procfile = new Procfile(body.getString("mainClass"), body.getString("gitRepo"), body.getString("AppName"));
                return apiConnection.loadApplication(procfile);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }


        });
    }
    private static int getPort(){
        if(System.getenv("port")!=null){
            return Integer.parseInt(System.getenv("port"));
        }

        return 4567;
    }
}
