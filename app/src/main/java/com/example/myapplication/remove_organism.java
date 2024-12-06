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

public class remove_organism extends AppCompatActivity {

    boolean addName = false;
    String name = "";

    OrganismTree pyramid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_remove_organism);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pyramid = (OrganismTree) getIntent().getSerializableExtra("FOOD_PYRAMID");
    }

    public void addInfo(View v){
        EditText t = findViewById(R.id.AddNameField);
        name = t.getText().toString();
        if(!name.equals("Empty")){
            addName = true;
        }
        else{
            TextView y = findViewById(R.id.Output);
            y.setText("Please input a name");
        }
    }

    public void removeOrganism(View v){
        TextView t = findViewById(R.id.Output);
        if(addName){
            try{
                pyramid.removeChild(name);
                t.setText("A(n) " + name + " has been successfully removed as prey for the "
                        + pyramid.getCursor().getName() + "!");
                Intent add = new Intent(this, MainActivity.class);
                add.putExtra("FOOD_PYRAMID", pyramid);
                startActivity(add);
            } catch(Exception e){
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