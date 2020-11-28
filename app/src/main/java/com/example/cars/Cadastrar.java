package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Cadastrar extends AppCompatActivity {

    EditText editnome_empressa, editendereco, editmail, editcontato, editavalicao;
    TextView Results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        Results = findViewById(R.id.Results);

        editnome_empressa = findViewById(R.id.editnome_empressa);
        editendereco = findViewById(R.id.editendereco);
        editmail = findViewById(R.id.editmail);
        editcontato = findViewById(R.id.editcontato);
        editavalicao = findViewById(R.id.editavalicao);

    }

    public void CadastrarEmpressa(View view) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://matheus.tech4every1.com.br:8001/empressa/";

        JSONObject obj = new JSONObject();
        try {
            obj.put("nome_empressa",editnome_empressa.getText().toString());
            obj.put("endereco",editendereco.getText().toString());
            obj.put("mail",editmail.getText().toString());
            obj.put("contato",editcontato.getText().toString());
            obj.put("avalicao",editavalicao.getText().toString());

        } catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {



                        try {
                            String empressa = response.toString();
                            JSONObject obj = new JSONObject(empressa);
                            Empressa empress = new Empressa();

                            empress.setNome_empressa(obj.getString("nome_empressa"));
                            empress.setEndereco(obj.getString("endereco"));
                            empress.setMail(obj.getString("mail"));
                            empress.setContato(obj.getString("contato"));
                            empress.setAvalicao(obj.getString("avalicao"));

                            Results.setText(empress.Cadastradoempressa());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Empressa empress = new Empressa();
                            Results.setText(empress.erro2());


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Empressa empress = new Empressa();
                        Results.setText(empress.erro());
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