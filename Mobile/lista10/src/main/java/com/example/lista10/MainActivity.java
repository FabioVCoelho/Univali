package com.example.lista10;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    //TODO: Criar Intent para atualização do Usuario.
    List<Item> listOfItems;
    CustomListAdapter adapter;
    List<Item> cart = null;
    Response response = null;
    private boolean autheticated = false;
    private User user = null;
    private LocationManager locationManager = null;
    private CustomLocationListener customLocationListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listOfItems = new ArrayList<>();
        EditText myTextBox = findViewById(R.id.filtroDescricao);
        myTextBox.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                Runnable runnable = () -> {
                    try {
                        getLikeDescritpion(myTextBox.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
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
            }
        });
        Runnable runnable = () -> {
            try {
                getDataFromHtml("http://192.168.0.107:8080/ExemploService/ListaItemsMenu");
            } catch (Exception e) {
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
        adapter = new CustomListAdapter(this, R.layout.custom_list, listOfItems);
        ListView viewListOfQuestions = findViewById(R.id.foodList);
        viewListOfQuestions.setAdapter(adapter);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        customLocationListener = new CustomLocationListener();
        if (displayGpsStatus()) {
            requestLocation();
        }
    }

    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, customLocationListener);
    }

    private void getLikeDescritpion(String detail) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        listOfItems = new ArrayList<>();
        JSONObject jsonObject = null;
        RequestBody formBody = new FormBody.Builder().add("descricao", detail).build();
        Request request = new Request.Builder().url("http://192.168.0.107:8080/ExemploService/ListaItemsMenu").post(formBody).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        try {
            jsonObject = new JSONObject(response.body().string());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray itens = jsonObject.getJSONArray("itens");
        addItemToList(itens);

        adapter.updatedData(listOfItems);
    }

    public void getDataFromHtml(String urlToRead) throws Exception {
        JSONObject jsonReader = new JsonReader().readJsonFromUrl(urlToRead);
        JSONArray itens = jsonReader.getJSONArray("itens");
        addItemToList(itens);
    }

    public void onFinalizarCompra(View view) {
        //TODO: Retornar o código de confirmação.
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("user", userToJSON());
            jsonObject.put("itens", itensToJSON());
            jsonObject.put("latitude", customLocationListener.getLatitude());
            jsonObject.put("longetude", customLocationListener.getLongitude());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .header("X-Client-Type", "Android")
                .url("http://192.168.0.107:8080/ExemploService/purchase")
                .post(body)
                .build();
        Runnable runnable = () -> {
            try {
                response = client.newCall(request).execute();
            } catch (Exception e) {
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
        System.out.println(response);
    }


    public void onAddItensToChart(View view) {
        //TODO: Adicionar na tabela de PEDIDO_COMPRA quando o cart estiver nulo depois da verificação de autenticação, e pegar seu ID
        //TODO: para depois utilizar na hora de fazer a compra.
        if (autheticated) {
            cart = adapter.itensWithQuantityMoreThanOne();
            Toast.makeText(this, "Added to Cart", Toast.LENGTH_LONG).show();
        } else {
            Intent sendToAuthentication = new Intent(getBaseContext(), Authentication.class);
            startActivityForResult(sendToAuthentication, 90);
            Toast.makeText(this, "Added to Cart", Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 90:
                if (resultCode == RESULT_OK) {
                    autheticated = true;
                    Bundle res = data.getExtras();
                    user = new User();
                    user.setLogin(res.getString("login"));
                    user.setCodigoSeguranca(res.getInt("codigoSeguranca"));
                    user.setNumeroCartao(res.getString("numeroCartao"));
                    user.setDataValidade(res.getString("dataValidade"));
                    String result = res.getString("results");
                    cart = adapter.itensWithQuantityMoreThanOne();
                }
                break;
        }
    }

    public JSONObject userToJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login", user.getLogin());
        jsonObject.put("codigoSeguranca", user.getCodigoSeguranca());
        jsonObject.put("dataValidade", user.getDataValidade());
        jsonObject.put("numeroCartao", user.getNumeroCartao());
        return jsonObject;
    }

    private JSONObject itensToJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONArray itens = new JSONArray();
        for (Item item : cart) {
            JSONObject jsonItem = new JSONObject();
            jsonItem.put("nome", item.getName());
            jsonItem.put("descricao", item.getDescription());
            jsonItem.put("detalhe", item.getDetail());
            jsonItem.put("Id", item.getId());
            jsonItem.put("quantidade", item.getQuantity());
            itens.put(jsonItem);
        }
        jsonObject.put("itens", itens);
        return jsonObject;
    }

    private void addItemToList(JSONArray itens) throws JSONException {
        for (int i = 0; i < itens.length(); i++) {
            Item item = new Item();
            JSONObject foodJSON = (JSONObject) itens.get(i);
            item.id = foodJSON.getInt("Id");
            item.name = foodJSON.getString("nome");
            item.description = foodJSON.getString("descricao");
            item.detail = foodJSON.getString("detalhe");
            listOfItems.add(item);
        }
    }

    public void visualizacaoDoCarrinho() {
        //TODO: Criar intent para visualização dos itens
        //TODO: Exclusão dos itens.
    }

    public void cancelarPedido() {
        //TODO: Cancelar Pedido
    }

    private Boolean displayGpsStatus() {
        ContentResolver contentResolver = getBaseContext().getContentResolver();
        return Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER);
    }

}
