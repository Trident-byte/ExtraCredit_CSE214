package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    boolean waitingForInput = false;
    boolean waitingForCommand = false;
    boolean enteredCommand = false;
    String input = "";
    int commandNum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toggle(View v){
        int idNum = v.getId();
        if(waitingForCommand) {
            commandNum = idNum;
            if (idNum == R.id.AddAnimal) {

            } else if (idNum == R.id.AddPlant) {

            } else if (idNum == R.id.RemoveOrganism) {

            } else if (idNum == R.id.MoveCursor) {

            } else if (idNum == R.id.ResetCursor) {

            } else if (idNum == R.id.ListPlants) {

            } else if (idNum == R.id.PrintFoodPyramid) {

            } else if (idNum == R.id.PrintFoodChain) {

            } else if (idNum == R.id.PrintPrey) {

            }
        }
    }

    public void handleText(View v){
        if(waitingForInput) {
            EditText t = findViewById(R.id.InputField);
            input = t.getText().toString();
            enteredCommand = true;
            waitingForInput = false;
        }
    }

    private void prompter(String prompt){
        TextView promptToUser = findViewById(R.id.InputTitle);
        promptToUser.setText(prompt);
        waitingForCommand = false;
        waitingForInput = true;
    }

    private void finishedCommand(){
        enteredCommand = false;
        waitingForCommand = true;
    }
}