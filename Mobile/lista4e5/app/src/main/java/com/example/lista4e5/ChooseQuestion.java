package com.example.lista4e5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChooseQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_question);
        List<Question> questions = getQuestions();
        ListView viewListOfQuestions = findViewById(R.id.listView);
        ArrayAdapter<Question> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questions);
        viewListOfQuestions.setAdapter(adapter);
        viewListOfQuestions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Question questionClicked = (Question) parent.getItemAtPosition(position);
                Intent intent = new Intent(parent.getContext(), questionClicked.getClasse());
                startActivity(intent);
            }
        });
    }

    private List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Lista4", "1", Lista4.class));
        questions.add(new Question("Lista5", "1", Lista5.class));
        return questions;
    }
}