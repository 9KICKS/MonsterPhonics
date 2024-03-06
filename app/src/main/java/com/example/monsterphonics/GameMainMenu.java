package com.example.monsterphonics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class GameMainMenu extends AppCompatActivity {

    private Button btnSound;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main_menu);

        firestore = FirebaseFirestore.getInstance();

        saveImageAndText("Dog", R.drawable.dog);
        saveImageAndText("Lion", R.drawable.lion);
        saveImageAndText("Snake", R.drawable.snake);

        btnSound = findViewById(R.id.btnSound);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.audio);

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                mediaPlayer.start();
            }
        });

        Button button = findViewById(R.id.btnReadMe);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameMainMenu.this, ReadMeActivity.class);
                startActivity(intent);
            }
        });

        Button exitButton = findViewById(R.id.btnExit);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitGame();
            }
        });
    }

    private void saveImageAndText(String animal, int imageResourceId) {
        Map<String, Object> data = new HashMap<>();
        data.put("text", animal);

        firestore.collection("images")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(GameMainMenu.this, "Data saved for " + animal, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(GameMainMenu.this, "Failed to save data for " + animal, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void exitGame() { finish(); }

    public void openGameLogic(View view) {
        startActivity(new Intent(this, GameLogic.class));
    }
}
