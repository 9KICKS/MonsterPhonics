package com.example.monsterphonics;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameLogic extends AppCompatActivity {

    private ImageView currentImage;
    private TextView currentText;
    private MatchingView matchingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_logic);

        currentImage = findViewById(R.id.currentImage);
        currentText = findViewById(R.id.currentText);
        matchingView = findViewById(R.id.matchingView);

        setCurrentItem(R.drawable.dog, "Dog");
    }

    private void setCurrentItem(int imageResource, String word) {
        currentImage.setImageResource(imageResource);
        currentText.setText(word);
    }

    public void onImageClick(View view) {
        if (view.getId() == R.id.dogImage) {
            checkMatch(R.drawable.dog, "Dog");
        } else if (view.getId() == R.id.lionImage) {
            checkMatch(R.drawable.lion, "Lion");
        } else if (view.getId() == R.id.snakeImage) {
            checkMatch(R.drawable.snake, "Snake");
        }
    }

    private void checkMatch(int imageResource, String word) {
        if (currentImage.getDrawable().getConstantState().equals(getResources().getDrawable(imageResource).getConstantState()) && currentText.getText().toString().equals(word)) {
            Toast.makeText(this, "Matched!", Toast.LENGTH_SHORT).show();
            setCurrentItem(R.drawable.lion, "Lion");
        } else {
            Toast.makeText(this, "Not matched. Try again!", Toast.LENGTH_SHORT).show();
        }
    }
}
