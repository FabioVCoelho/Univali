package com.example.lista10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Authentication extends AppCompatActivity {

    JSONObject jsonObject = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
    }

    public void isAuthenticated(View view) throws IOException {
        EditText loginTextBox = (EditText) findViewById(R.id.login);
        EditText passwordTextBox = (EditText) findViewById(R.id.password);
        Runnable runnable = () -> {
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder().add("senha", passwordTextBox.getText().toString()).add("nome", loginTextBox.getText().toString()).build();
            Request request = new Request.Builder().url("http://192.168.0.107:8080/ExemploService/login").post(formBody).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!response.isSuccessful())
                try {
                    throw new IOException("Unexpected code " + response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            try {
                jsonObject = new JSONObject(response.body().string());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread t = new Thread(runnable);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            if (jsonObject.getBoolean("authorizated")) {
                Bundle conData = new Bundle();
                conData.putString("results", "Authorized");
                conData.putString("login", jsonObject.getJSONObject("user").getString("login"));
                try {
                    conData.putInt("codigoSeguranca", jsonObject.getJSONObject("user").getInt("codigoSeguranca"));
                } catch (Exception e) {
                    conData.putInt("codigoSeguranca", 9999);
                }

                conData.putString("dataValidade", jsonObject.getJSONObject("user").getString("dataValidade"));
                conData.putString("numeroCartao", jsonObject.getJSONObject("user").getString("numeroCartao"));
                Intent intent = new Intent();
                intent.putExtras(conData);
                setResult(RESULT_OK, intent);
                finish();
                Toast.makeText(this, "Authorized", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Authentication failed", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
