package com.example.monsterphonics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameLogic extends Activity {
    private ImageView currentSelectedImage;
    private TextView currentSelectedText;
    private DrawView drawView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_logic);

        drawView = findViewById(R.id.draw_view);

        setupTouchListener(R.id.dogImage, "Dog");
        setupTouchListener(R.id.lionImage, "Lion");
        setupTouchListener(R.id.snakeImage, "Snake");
    }

    private void setupTouchListener(int imageViewId, final String expectedText) {
        final RelativeLayout imageLayout = findViewById(imageViewId);
        final ImageView imageView = imageLayout.findViewById(R.id.currentImage);
        final TextView textView = imageLayout.findViewById(R.id.currentText);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        currentSelectedImage = imageView;
                        drawView.clearLines();
                        break;
                    case MotionEvent.ACTION_UP:
                        currentSelectedImage = null;
                        if (textView.getText().toString().equals(expectedText)) {
                            drawLineAndShowMessage(imageView, textView, true);
                        } else {
                            drawLineAndShowMessage(imageView, textView, false);
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void drawLineAndShowMessage(ImageView imageView, TextView textView, boolean isCorrect) {
        int[] imageLocation = new int[2];
        imageView.getLocationOnScreen(imageLocation);
        float startX = imageLocation[0] + imageView.getWidth() / 2f;
        float startY = imageLocation[1] + imageView.getHeight() / 2f;

        int[] textLocation = new int[2];
        textView.getLocationOnScreen(textLocation);
        float endX = textLocation[0] + textView.getWidth() / 2f;
        float endY = textLocation[1] + textView.getHeight() / 2f;

        drawView.drawLine(startX, startY, endX, endY);

        String message = isCorrect ? "Correct!" : "Wrong!";
        Toast toast = new Toast(getApplicationContext());
        View toastView = getLayoutInflater().inflate(R.layout.custom_toast, null);
        TextView toastText = toastView.findViewById(R.id.custom_toast_text);
        toastText.setText(message);
        toast.setView(toastView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
