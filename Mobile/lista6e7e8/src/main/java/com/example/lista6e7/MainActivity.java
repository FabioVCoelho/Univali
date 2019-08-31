package com.example.lista6e7;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    List<Food> listOfFoods;
    Document doc;
    JSONObject root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listOfFoods = new ArrayList<>();
        Runnable runnable = () -> {
            try {
                getDataFromHtml("https://www.w3schools.com/xml/simple.xml");
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
        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.custom_list, listOfFoods);
        ListView viewListOfQuestions = findViewById(R.id.foodList);
        viewListOfQuestions.setAdapter(adapter);
    }

    public void getDataFromHtml(String urlToRead) throws Exception {
        URL url = new URL(urlToRead);
        InputStream in = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        createXML(dBuilder.parse(in));
        createJSON();
        NodeList foodTag = doc.getElementsByTagName("food");
        for (int i = 0; i < foodTag.getLength(); i++) {
            Food food = new Food();
            food.setQuantity("0");
            for (int j = 0; j < foodTag.item(i).getChildNodes().getLength(); j++) {
                if (foodTag.item(i).getChildNodes().item(j).hasChildNodes()) {
                    String tagName = foodTag.item(i).getChildNodes().item(j).getNodeName();
                    String tagValue = foodTag.item(i).getChildNodes().item(j).getTextContent();
                    populateFood(tagName, tagValue, food);
                }
            }
            listOfFoods.add(food);
        }
    }

    private void createJSON() throws JSONException {
        root = new JSONObject();
        JSONArray items = new JSONArray();
        NodeList foodTag = doc.getElementsByTagName("food");
        for (int i = 0; i < foodTag.getLength(); i++) {
            JSONObject food = new JSONObject();
            for (int j = 0; j < foodTag.item(i).getChildNodes().getLength(); j++) {
                if (foodTag.item(i).getChildNodes().item(j).hasChildNodes()) {
                    String tagName = foodTag.item(i).getChildNodes().item(j).getNodeName();
                    String tagValue = foodTag.item(i).getChildNodes().item(j).getTextContent();
                    food.put(tagName, tagValue);
                }
            }
            items.put(food);
        }
        root.put("items", items);
    }

    private void populateFood(String tagName, String tagValue, Food food) {
        if (tagName.equals("name"))
            food.setName(tagValue);
        if (tagName.equals("price"))
            food.setPrice(tagValue);
        if (tagName.equals("calories"))
            food.setCalories(tagValue);
        if (tagName.equals("description"))
            food.setDescription(tagValue);
    }

    public void onFinalizarCompra(View view) throws JSONException {
        onCalcularCompra(view);
        TextInputEditText name = findViewById(R.id.nome);
        doc.getDocumentElement().getChildNodes().item(0).setTextContent(name.getText().toString());
        root.put("name", name.getText().toString());
        TextInputEditText endereco = findViewById(R.id.endereco);
        doc.getDocumentElement().getChildNodes().item(1).setTextContent(endereco.getText().toString());
        root.put("address", endereco.getText().toString());
        TextInputEditText telefone = findViewById(R.id.telefone);
        doc.getDocumentElement().getChildNodes().item(2).setTextContent(telefone.getText().toString());
        root.put("phone", telefone.getText().toString());
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        doc.getDocumentElement().getChildNodes().item(3).setTextContent(date.toString());
        root.put("created", date.toString());
        CheckBox checkBox = findViewById(R.id.checkBoxToJSON);
        if (checkBox.isChecked()) {
            sendJSON();
        } else {
            sendXML();
        }
    }

    private void sendXML() {
        System.out.println(doc.toString());
    }

    private void sendJSON() {
        System.out.println(root.toString());
    }

    public void onCalcularCompra(View view) {
        findViewById(R.id.textView5).setVisibility(View.VISIBLE);
        findViewById(R.id.textView7).setVisibility(View.VISIBLE);
        TextView calorias = findViewById(R.id.calorias);
        calorias.setText(calculateCalories());
        TextView valor = findViewById(R.id.valor);
        valor.setText(calculateValor());
    }

    private String calculateValor() {
        float total = 0;
        for (Food food : listOfFoods)
            total += Float.parseFloat(food.getPrice().replace("$", "")) * Integer.parseInt(food.getQuantity());
        return Float.toString(total);
    }

    private String calculateCalories() {
        int total = 0;
        for (Food food : listOfFoods)
            total += Integer.parseInt(food.getCalories()) * Integer.parseInt(food.getQuantity());
        return Integer.toString(total);
    }

    public void createXML(Document parse) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Element rootTag = doc.createElement("order");
        Element name = doc.createElement("name");
        Element address = doc.createElement("address");
        Element phoneNumber = doc.createElement("phoneNumber");
        Element created = doc.createElement("created");
        Element items = doc.createElement("items");
        NodeList foodTag = parse.getElementsByTagName("food");
        for (int i = 0; i < foodTag.getLength(); i++) {
            Element elementFood = doc.createElement("food");
            for (int j = 0; j < foodTag.item(i).getChildNodes().getLength(); j++) {
                try {
                    Element element = doc.createElement(foodTag.item(i).getChildNodes().item(j).getNodeName());
                    element.setTextContent(foodTag.item(i).getChildNodes().item(j).getTextContent());
                    elementFood.appendChild(element);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            items.appendChild(elementFood);
        }
        rootTag.appendChild(name);
        rootTag.appendChild(address);
        rootTag.appendChild(phoneNumber);
        rootTag.appendChild(created);
        rootTag.appendChild(items);
        doc.appendChild(rootTag);
    }
}
