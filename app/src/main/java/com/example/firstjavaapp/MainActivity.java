package com.example.firstjavaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView helloTextView = (TextView) findViewById(R.id.h);


        helloTextView.setText("Covid Kerala");

        String urlLoc = "https://covid19-kerala-api.herokuapp.com/api/location";
    //    System.out.println("HI");

    /*    String temp = "{\"locations\":[\"alappuzha\",\"ernakulam\"]}";
        try {
            JSONObject jsonval1 = new JSONObject(temp);
            JSONArray jar =  jsonval1.getJSONArray("locations");
            for (int i =0 ; i <jar.length(); i++)
            {
                System.out.println(jar.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/
    //  String[] locations = null ;
   // final
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 );
         ListView listView = (ListView) findViewById(R.id.l1);
        RequestQueue q1 = Volley.newRequestQueue(this);
       StringRequest req1 = new StringRequest(Request.Method.GET, urlLoc, new Response.Listener<String>() {

            public void onResponse(String response) {
               try {
                   JSONObject jsonVal = new JSONObject(response);
                   JSONArray jsonL1 = jsonVal.getJSONArray("locations");
                   List<String> l = new ArrayList<String>();
                   for(int i =0; i < jsonL1.length(); i++)
                   {
                   //  System.out.println("hi"+jsonL1.get(i).toString());
                  l.add(jsonL1.get(i).toString());
                   }
                  String [] locations = new String[l.size()];
                   l.toArray(locations);
                   //ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,locations );
                    adapter1.addAll(locations);
                  //  listView.setAdapter(adapter1);
                //   locations = jsonL1.names();
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            }
        }
     );

       q1.add(req1);

      listView.setAdapter(adapter1);


  // String [] myStringarray = {"kannur","kozhikode"};

  /*  System.out.println(l.get(2));

 for (String loc: locations)
 {
     System.out.println( "here "+loc);
 }*/
//list view
    // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myStringarray );
     //   ListView listView = (ListView) findViewById(R.id.l1);
      //  listView.setAdapter(adapter);

        final String url = "https://covid19-kerala-api.herokuapp.com/api/location?loc=";
        //kannur&date=latest
        final RequestQueue queue = Volley.newRequestQueue(this);
      final  ArrayAdapter<String> lad1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
         ListView listView1 = (ListView) findViewById(R.id.l1);
        final TextView textView = (TextView) findViewById(R.id.t1);
        textView.setText("hello inital");
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                final String selectedItem = (String)parent.getItemAtPosition(position);
                String url1 = url+selectedItem+"&date=latest";
lad1.clear();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url1,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                List<String> lresponse = new ArrayList<String>();
                                lresponse.clear();
                                String lrespArr [] = null;
                               try{ JSONObject respObj = new JSONObject(response);
                                   String timeS = respObj.keys().next().toString();
                                   JSONObject job1 = respObj.getJSONObject(timeS);
                                   JSONObject loc = job1.getJSONObject(job1.names().get(0).toString());
                                   Iterator<String> it = loc.keys();
                                     int count =0 ;
                                   while(it.hasNext())
                                   {count ++;
                                       String keyName = it.next().toString();
                                       System.out.println(keyName);
                                       String keyVal = loc.getString(keyName);
                                      System.out.println(keyVal);
                                        lresponse.add(keyName+":"+keyVal );
                                   }
                                   lrespArr = new String[count];
                                   lresponse.toArray(lrespArr);
                               // Iterator<String> it =  respObj.keys();

                              /*  while(it.hasNext())
                                {
                                    System.out.println(it.next().toString());
                                }*/
                                 // JSONArray jArr =    respObj.names().get(1)

                                System.out.println("selected Item"+selectedItem);
                              //   JSONObject jo1 =  respObj.getJSONObject(selectedItem);
                                  // System.out.println(jo1.getJSONObject("no_of_persons_discharged_from_home_isolation"));
                                /*   for(int i = 0; i <jArr.length(); i++)
                                    {
                                       // System.out.println(jArr.get(i));
                                    }*/
                               }
                               catch(Exception e){ e.printStackTrace();}

                               ListView l2 = (ListView)findViewById(R.id.l2);
                               lad1.addAll(lrespArr);
                               l2.setAdapter(lad1);
                                textView.setText("Response is: " + response.toString());

                            }
                        }, new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {
                        textView.setText("That didn't work!");
                    }
                }
                );
                queue.add(stringRequest);
            }
        });

/*
        final TextView textView = (TextView) findViewById(R.id.t1);
textView.setText("hello inital");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://covid19-kerala-api.herokuapp.com/api/location?loc=kannur&date=latest";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: " + response.toString());
                    }
                }, new Response.ErrorListener() {

            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        }
        );
        queue.add(stringRequest);
*/
    }
}

