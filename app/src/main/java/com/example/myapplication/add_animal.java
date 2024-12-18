package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class add_animal extends AppCompatActivity {
    boolean addName = false;
    boolean addDiet = false;
    String name = "";
    boolean[] dietArray = new boolean[2];

    OrganismTree pyramid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_animal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pyramid = (OrganismTree) getIntent().getSerializableExtra("FOOD_PYRAMID");
    }

    public void addInfo(View v){
        TextView y = findViewById(R.id.Output);
        if(v.getId() == R.id.enterName){
            EditText t = findViewById(R.id.AddNameField);
            name = t.getText().toString();
            addName = true;
            if(name.equals("Empty")){
                addName = false;
                y.setText("Please input a valid name");
            }

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
                    y.setText("Please use C, H or O to define diet");
            }
        }
    }

    public void addAnimal(View v){
        TextView t = findViewById(R.id.Output);
        if(addDiet && addName){
            try{
                pyramid.addAnimalChild(name, dietArray[0], dietArray[1]);
                t.setText("A(n) " + name + " has successfully been added as prey for the "
                        + pyramid.getCursor().getName() + "!");
                Intent add = new Intent(this, MainActivity.class);
                add.putExtra("FOOD_PYRAMID", pyramid);
                startActivity(add);
            } catch(PositionNotAvailableException | IllegalArgumentException e){
                t.setText(e.getMessage());
            }
        }
    }

    public void back(View v){
        Intent add = new Intent(this, MainActivity.class);
        add.putExtra("FOOD_PYRAMID", pyramid);
        startActivity(add);
    }
}