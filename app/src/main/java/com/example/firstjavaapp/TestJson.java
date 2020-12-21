package com.example.firstjavaapp;

import org.json.JSONObject;

public class TestJson {


    public static  void main( String args [])
    {

        String resp = "{\"2020-05-09T00:00:00Z\":{\"kannur\":{\"no_of_persons_discharged_from_home_isolation\":0,\"no_of_persons_hospitalized_today\":7,\"no_of_persons_under_home_isolation_as_on_today\":270,\"no_of_persons_under_observation_as_on_today\":306,\"no_of_positive_cases_admitted\":5,\"no_of_symptomatic_persons_hospitalized_as_on_today\":36,\"other_districts\":{}}},\"success\":true}";

        try{
            JSONObject job = new JSONObject(resp);
           System.out.println("Hi"+job.get("kannur"));
        }
        catch(Exception e)
        {
            e.printStackTrace();;
        }
    }
}
