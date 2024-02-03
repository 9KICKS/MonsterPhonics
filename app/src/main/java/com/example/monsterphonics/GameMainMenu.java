package com.example.monsterphonics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameMainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main_menu);
    }

    public void openGameLogic(View view) {
        startActivity(new Intent(this, GameLogic.class));
    }
}
