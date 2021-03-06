package edu.eci.arep.loadbalancer.components.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import edu.eci.arep.loadbalancer.components.models.Procfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIConnection {
    List<String> hosts;
    int roundRobin;
    public APIConnection(){
        hosts = new ArrayList<>(Arrays.asList("http://ec2-3-85-54-89.compute-1.amazonaws.com",
                "http://ec2-54-227-80-1.compute-1.amazonaws.com","http://ec2-3-85-86-158.compute-1.amazonaws.com"));
        roundRobin = 0;
    }
    public String loadApplication(Procfile procfile){
        ObjectMapper objectMapper = new ObjectMapper();

        Unirest.setTimeouts(60000, 60000);
        try {
            String url = hosts.get(roundRobin);
            procfile.setServerUrl(hosts.get(roundRobin));
            String petition = Unirest.post(url+":4567/newApp").body(objectMapper.writeValueAsString(procfile)).asString().getBody();
            System.out.println(petition);
            roundRobin = (roundRobin+=1) % hosts.size();
            return petition;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
