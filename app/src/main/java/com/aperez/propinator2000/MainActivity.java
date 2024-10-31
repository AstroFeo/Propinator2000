package com.aperez.propinator2000;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button igual;
    private RadioButton mal, bueno, normal;
    private TextView resultado, total;
    private LinearLayout pad;
    private String number1 = "";

    private ArrayList<Button> nums = new ArrayList<>();

    private double Number1;
    private double Result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        pad = findViewById(R.id.pad);
        resultado = findViewById(R.id.total);
        mal = findViewById(R.id.mal);
        normal = findViewById(R.id.normal);
        bueno = findViewById(R.id.bueno);
        total = findViewById(R.id.total2);
        igual = findViewById(R.id.igual);


        for(int i = 0;i < pad.getChildCount()-1;i++) {
            LinearLayout temp = (LinearLayout) pad.getChildAt(i);
            for(int j = 0;j < temp.getChildCount();j++) {
                nums.add((Button) temp.getChildAt(j));
            }
        }

        for(Button i : nums) {
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String numText = i.getText().toString();
                    switch (numText) {
                        case ",":
                            resultado.setText(resultado.getText().toString()+",");
                            number1 += ".";
                            break;
                        case "AC":
                            number1 = number1.substring(0, number1.length()-1);
                            resultado.setText(number1);
                            break;
                        default:
                            resultado.setText(resultado.getText().toString()+numText);
                            number1 += numText;
                            break;
                    }
                }
            });

        }

        total.setText("Elige el trato dado");
        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mal.isChecked()) {
                    Number1 = Double.parseDouble(number1);
                    Result = Number1;
                    total.setText(String.valueOf(Result));
                }else if(normal.isChecked()) {
                    Number1 = Double.parseDouble(number1);
                    Result = Number1 * 1.4;
                    total.setText(String.valueOf(Result));
                }else if(bueno.isChecked()) {
                    Number1 = Double.parseDouble(number1);
                    Result = Number1 * 1.8;
                    total.setText(String.valueOf(Result));
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}