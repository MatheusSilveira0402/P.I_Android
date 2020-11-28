package com.example.cars;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView TextResults;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextResults = findViewById(R.id.TextResults);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonActivity();
            }


        });
    }

    private void buttonActivity() {
        startActivity(new Intent(MainActivity.this,  Cadastrar.class));
    }


    public void Consultaempressa(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://matheus.tech4every1.com.br:8001/empressa/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Empressa empress = new Empressa();


                        try {
                            JSONArray lista = response.getJSONArray("results");
                            for(int i = 0; i < lista.length();i++) {

                                String listar = lista.getString(i);

                                JSONObject obj = new JSONObject(listar);

                                empress.setNome_empressa(obj.getString("nome_empressa"));
                                empress.setEndereco(obj.getString("endereco"));
                                empress.setMail(obj.getString("mail"));
                                empress.setContato(obj.getString("contato"));
                                empress.setAvalicao(obj.getString("avalicao"));

                                String listar1 = empress.Consultarempressa();
                                empress.addlista(listar1);



                            }
                            TextResults.setText(empress.toStringlista());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Empressa empress = new Empressa();
                        TextResults.setText(empress.erro());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-Api-Key", "IvNWyHHb.5FFO3ASU0iGE0CfUqpYwLrcoioUiN0fU");

                return params;
            }
        };
        queue.add(request);


    }

}