package com.example.monsterphonics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameMainMenu extends AppCompatActivity {

    private Button btnSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main_menu);

        btnSound = findViewById(R.id.btnSound);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.audio);

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                mediaPlayer.start();
            }
        });
    }

    public void openGameLogic(View view) {
        startActivity(new Intent(this, GameLogic.class));
    }
}
