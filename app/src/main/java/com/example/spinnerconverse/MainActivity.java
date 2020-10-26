package com.example.spinnerconverse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //converse to VND
//    int CAD= 17503;
//    int USD= 23150;
//    int CHF= 23920;
//    int EUR= 25979;
//    int KYD= 28231;
//    int JOD= 32721;
//    int OMR= 60208;
//    int AUD= 15658;
//    int GBP= 27979;
//    int VND=1;

    HashMap<String, Double> currencies = new HashMap<>();

    String item_from,item_to;
    double result;

    List<String> items;
    TextView txt_result;
    EditText txt_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        items.add("CAD");
        items.add("USD");
        items.add("CHF");
        items.add("EUR");
        items.add("GBP");
        items.add("KYD");
        items.add("JOD");
        items.add("OMR");
        items.add("AUD");
        items.add("VND");

        currencies.put("CAD", 17503.0);
        currencies.put("USD", 23150.0);
        currencies.put("CHF", 23920.0);
        currencies.put("EUR", 25979.0);
        currencies.put("GBP", 27979.0);
        currencies.put("KYD", 28231.0);
        currencies.put("JOD", 32721.0);
        currencies.put("OMR", 60208.0);
        currencies.put("AUD", 15658.0);
        currencies.put("VND", 1.0);

        txt_result=(TextView)findViewById(R.id.txt_result);
        txt_input= (AutoCompleteTextView)findViewById(R.id.txt_input);

        ArrayAdapter<String> adapter_from = new ArrayAdapter<>(this,
                R.layout.layout_item,
                R.id.text_view,
                items);

        Spinner spinner_from = findViewById(R.id.spinner_from);
        spinner_from.setAdapter(adapter_from);

        spinner_from.setSelection(5);

        spinner_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item_from = items.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> adapter_to = new ArrayAdapter<>(this,
                R.layout.layout_item,
                R.id.text_view,
                items);

        Spinner spinner_to = findViewById(R.id.spinner_to);
        spinner_to.setAdapter(adapter_to);

        spinner_to.setSelection(5);

        spinner_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item_to = items.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txt_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double val1 = 0, val2=0;

                for (String currency: currencies.keySet()) {
                    if (currency.equals(item_from)) val1= currencies.get(currency);
                    if (currency.equals(item_to)) val2= currencies.get(currency);
                }

                double input=Double.parseDouble(txt_input.getText().toString());

                result= (val1/val2)* input;
                txt_result.setText(input+" "+ item_from +"="+ Math.round(result*100)/100 +" "+ item_to  );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}