package com.example.zaferkirik.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    TextView helloTextView;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        helloTextView =  findViewById(R.id.zafer);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue requstQueue = Volley.newRequestQueue(this);
        String url="http://api.trakus.org/api/gozlem/gozlemgonder";


        List<Object> list=new ArrayList<Object>();
        Map<String,String> listitem=new HashMap();
        listitem.put("turkod","122");
        listitem.put("tursayisi","10");
        list.add(listitem);
        Map<String,String> listitem2=new HashMap();
        listitem2.put("turkod","124");
        listitem2.put("tursayisi","12");
        list.add(listitem2);

         JSONArray listobje = new JSONArray(list);


       token="g8QBPvBVBb3iWOroxwt3e8SQ3gGV3y88zKW2vVPA4BxNzNSn/VuqCoYhcMnHgy8s7QExtNNYAEgE9c6oX67vjqPlQOMEK0Y3VIsaD59h4yzv/ro5YnyDWQ1ggUQtTjw9qygCrcM/PjhPQdHt891Fsb+Uwlz+dRBE8Bd3V3R/Pp9f305kDOBU1ykZFRo/93HsLXGLHZvwpkffJo2rSydjho0EtG+pgAxDPENOf3LYFtocV1BWNqfqT5NN8IonjtCg";

        Map<String,Object> params=new HashMap();
        params.put("aciklama","zafer");
        params.put("kul_ad","zaferkrk");
        params.put("il","İstanbul");
        params.put("gecen_sure","1 Saat");
        params.put("uuIDMobil","ascfaslkcbasjcka2133d12das");
        params.put("b_tarih","2019-01-02 20:08:00");
        params.put("foto1","");
        params.put("foto2","");
        params.put("foto3","");
         params.put("turler",listobje);
        JSONObject parameters = new JSONObject(params);

               Log.v("resdf", "zzz="+ listobje.toString());
        Log.v("sda", "zscszz="+ parameters.toString());

        try{
            JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url,parameters,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //VolleyLog.v("Response:%n %s", response.toString());
                            Log.v("res", "index="+ response.toString());
                           //helloTextView.setText("Response:%n %s"+response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //helloTextView.setText(error.toString());
                            Log.v("ress", "index="+ error.toString());
                        }
                    }
            ){
                //here I want to post data to sever
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json;");

                    String auth = "Basic "+token;
                    params.put("Authorization", auth);
                    return params;
                }
            };
            requstQueue.add(jsonobj);

        }catch (Exception ex){

            Log.v("resccs", "index="+ ex.toString());
        }

    }
}
