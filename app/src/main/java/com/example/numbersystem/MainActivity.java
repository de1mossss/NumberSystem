package com.example.numbersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int inID = 10, outID = 2;

    String Symbol = "0123456789ABCDEF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText text = findViewById(R.id.InputTextBox);
        RadioGroup radioGroupIn = findViewById(R.id.InGroupe);
        RadioGroup radioGroupOut = findViewById(R.id.OutGroupe);

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(text.getText().toString().length() == 0) return;
//                if(text.getText().toString().toUpperCase())

                EditText text = findViewById(R.id.InputTextBox);
                TextView logText = findViewById(R.id.log);

                logText.setText("");

                try{
                    Convert(text, logText);
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //
            }
        });

        radioGroupIn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {

                switch(checkId)
                {
                    case R.id.inSixten: inID = 16;
                        break;
                    case R.id.inTen: inID = 10;
                        break;
                    case R.id.inEig: inID = 8;
                        break;
                    case R.id.inThri: inID = 3;
                        break;
                    case R.id.inTwo: inID = 2;
                        break;
                }
            }
        });

        radioGroupOut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch(checkId)
                {
                    case R.id.outSixten: outID = 16;
                        break;
                    case R.id.outTen: outID = 10;
                        break;
                    case R.id.outEig: outID = 8;
                        break;
                    case R.id.outThri: outID = 3;
                        break;
                    case R.id.outTwo: outID = 2;
                        break;
                }
            }
        });
    }

    void Convert(EditText editText, TextView text)
    {
        String num = editText.getText().toString();

        text.setText(inXtoX(num, inID));

        if(inID == outID)
        {
            text.setText(editText.getText().toString());
        }

    }

    String inXtoX(String num, int to)
    {


        int remainder = 0;
        int pow = 0;
        String temp =  num.toUpperCase();;

        int integerResult = 0;
        String result = "";
        String t = "";

        while(temp.length() != 0)
        {
            Character lastSymbol = temp.charAt(temp.length()-1);

            if(lastSymbol >= '0' && lastSymbol <= '9')
            {
                remainder = lastSymbol - '0';
            }
            else if(lastSymbol >= 'A' && lastSymbol <= 'Z')
            {
                remainder = lastSymbol - 'A' + 10;
            }
            integerResult += outXtoX(remainder, to, pow);
            pow++;
            temp = temp.substring(0, temp.length()-1);
        }
        return inTenToX(integerResult, outID);
    }

    String inTenToX(int num, int in)
    {
        String result = "";
        int temp = 0;

        while(num != 0)
        {
            temp = num % in;
            num /= in;
            result = Symbol.charAt(temp) + result;
        }

        return result;
    }

    int outXtoX(int num, int in, int pow)
    {
        int result = 0;

        result = num * Power(in, pow);

        return result;
    }

    int Power(int number, int pow)
    {
        int result = number;
        if(pow > 0) {
            for (int i = 1; i < pow; i++) {
                result *= number;
            }
        } else
        {
            result = 1;
        }
        return result;
    }
}