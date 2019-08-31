package com.example.lista4e5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Lista5 extends AppCompatActivity {

    List<Municipio> listaDeMunicipios = new ArrayList<>();
    List<String> MUNICIPIOS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista5);
        InputStream is = getResources().openRawResource(R.raw.amostramunicipios);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                Municipio municipio = new Municipio();
                municipio.setNome(tokens[0]);
                MUNICIPIOS.add(tokens[0]);
                municipio.setCodigo(tokens[1]);
                municipio.setGentilico(tokens[2]);
                municipio.setPrefeito(tokens[3]);
                municipio.setArea(tokens[4]);
                municipio.setPopulacao(tokens[5]);
                municipio.setDensidade(tokens[6]);
                municipio.setEscolarizacao(tokens[7]);
                municipio.setIdhm(tokens[8]);
                municipio.setMortalidade(tokens[9]);
                municipio.setReceita(tokens[10]);
                municipio.setDespesa(tokens[11]);
                municipio.setPib(tokens[12]);
                listaDeMunicipios.add(municipio);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, MUNICIPIOS);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.pesquisaMunicipios);
        textView.setAdapter(adapter);
    }

    public void showInfo(View view) {
        TextView nomeCidade = findViewById(R.id.nomeCidade);
        TextView populacaoCidade = findViewById(R.id.populacaoCidade);
        TextView areaoCidade = findViewById(R.id.areaCidade);
        TextView densidadeCidade = findViewById(R.id.densidadeCidade);
        AutoCompleteTextView textView = findViewById(R.id.pesquisaMunicipios);
        for (Municipio municipio : listaDeMunicipios) {
            if (municipio.getNome().equals(textView.getText().toString())) {
                nomeCidade.setText(municipio.getNome());
                populacaoCidade.setText(municipio.getPopulacao());
                areaoCidade.setText(municipio.getArea());
                densidadeCidade.setText(municipio.getDensidade());
            }
        }
    }
}
