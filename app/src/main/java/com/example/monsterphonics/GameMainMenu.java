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

        Map<String, Object> user = new HashMap<>();
        user.put("username", "9KICKS");

        firestore.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Success",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed",Toast.LENGTH_LONG).show();
            }
        });

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

    private void exitGame() { finish(); }

    public void openGameLogic(View view) {
        startActivity(new Intent(this, GameLogic.class));
    }
}
