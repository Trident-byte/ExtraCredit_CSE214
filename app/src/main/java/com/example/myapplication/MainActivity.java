package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String input = "";
    int commandNum = 0;
    OrganismTree pyramid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pyramid = (OrganismTree) getIntent().getSerializableExtra("FOOD_PYRAMID");
    }

    public void toggle(View v){
        int idNum = v.getId();
        commandNum = idNum;
        Intent add;
        TextView t = findViewById(R.id.output);
        if (idNum == R.id.AddAnimal) {
            add = new Intent(this, add_animal.class);
            add.putExtra("FOOD_PYRAMID", pyramid);
            startActivity(add);
        } else if (idNum == R.id.AddPlant) {
            add = new Intent(this, add_plants.class);
            add.putExtra("FOOD_PYRAMID", pyramid);
            startActivity(add);
        } else if (idNum == R.id.RemoveOrganism) {
            add = new Intent(this, remove_organism.class);
            add.putExtra("FOOD_PYRAMID", pyramid);
            startActivity(add);
        } else if (idNum == R.id.MoveCursor) {
            add = new Intent(this, move_cursor.class);
            add.putExtra("FOOD_PYRAMID", pyramid);
            startActivity(add);
        } else if (idNum == R.id.ResetCursor) {
            pyramid.cursorReset();
            t.setText("Cursor has been reset");
        } else if (idNum == R.id.ListPlants) {
            String ans = pyramid.listAllPlants();
            t.setText(ans);
        } else if (idNum == R.id.PrintFoodPyramid) {
            Log.d("test", "Activated method");
            String ans = pyramid.printOrganismTree();
            t.setText(ans);
        } else if (idNum == R.id.PrintFoodChain) {
            String ans = pyramid.listFoodChain();
            t.setText(ans);
        } else if(idNum == R.id.PrintPrey) {
            try{
                String ans = pyramid.listPrey();
                t.setText(ans);
            }catch(IsPlantException e){
                t.setText(e.getMessage());
            }
        }
    }
}