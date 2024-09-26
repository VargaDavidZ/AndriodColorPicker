package com.example.randomcolor;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.slider.Slider;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private TextView textView;
    private Random random;
    private Slider redSlider;
    private TextView redText;
    private Slider greenSlider;
    private TextView greenText;
    private Slider blueSlider;
    private TextView blueText;
    private FrameLayout colorSpace;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.relativeLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        relativeLayout.setOnClickListener(view -> {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            textView.setText("(" + r + "," + g + "," + b + ")");
            relativeLayout.setBackgroundColor(Color.rgb(r,g,b));
            if((r + g) < 200)
            {
                textView.setTextColor(Color.WHITE);
            }
            else {
                textView.setTextColor(Color.BLACK);
            }
        });

        relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                relativeLayout.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
                textView.setText("(0,0,0)");
                return true;
            }
        });

       redSlider.addOnChangeListener(new Slider.OnChangeListener() {
           @Override
           public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
               redText.setText(Integer.toString(Math.round(value)));
               colorSpace.setBackgroundColor(Color.rgb(Math.round(value),Integer.parseInt(greenText.getText().toString()) ,Integer.parseInt(blueText.getText().toString())));
           }
       });

        greenSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                greenText.setText(Integer.toString(Math.round(value)));
                colorSpace.setBackgroundColor(Color.rgb(Integer.parseInt(redText.getText().toString()) ,Math.round(value),Integer.parseInt(blueText.getText().toString())));
            }
        });

        blueSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                blueText.setText(Integer.toString(Math.round(value)));
                colorSpace.setBackgroundColor(Color.rgb(Integer.parseInt(redText.getText().toString()) ,Integer.parseInt(greenText.getText().toString()),Math.round(value)));

            }
        });



    }

    public void init()
    {
        relativeLayout = findViewById(R.id.relativeLayout);
        textView = findViewById(R.id.textViewColor);
        random = new Random();
        redSlider = findViewById(R.id.redSlider);
        redText = findViewById(R.id.redText);
        greenSlider = findViewById(R.id.greenSlider);
        greenText = findViewById(R.id.greenText);
        blueSlider = findViewById(R.id.blueSlider);
        blueText = findViewById(R.id.blueText);
        colorSpace = findViewById(R.id.colorSpace);
    }
}