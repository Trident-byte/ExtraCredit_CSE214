package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

public class Start extends AppCompatActivity {
    boolean addName = false;
    boolean addDiet = false;
    String name = "";
    boolean[] dietArray = new boolean[2];

    OrganismTree pyramid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addInfo(View v){
        if(v.getId() == R.id.enterName){
            EditText t = findViewById(R.id.AddNameField);
            addName = true;
            name = t.getText().toString();
        }
        else{
            EditText t = findViewById(R.id.AddDietField);
            addDiet = true;
            String diet = t.getText().toString();
            switch(diet){
                case "C":
                    dietArray[1] = true;
                    break;
                case "H":
                    dietArray[0] = true;
                    break;
                case "O":
                    dietArray[0] = true;
                    dietArray[1] = true;
                    break;
                default:
                    addDiet = false;
                    TextView y = findViewById(R.id.Output);
                    y.setText("Please use C, H or O to define diet");
            }
        }
    }

    public void addAnimal(View v){
        if(addDiet && addName){
            OrganismNode newRoot = new OrganismNode(name);
            newRoot.setIsCarnivore(dietArray[1]);
            newRoot.setIsHerbivore(dietArray[0]);
            pyramid = new OrganismTree(newRoot);
            Intent add = new Intent(this, MainActivity.class);
            add.putExtra("FOOD_PYRAMID", pyramid);
            startActivity(add);
        }
    }
}