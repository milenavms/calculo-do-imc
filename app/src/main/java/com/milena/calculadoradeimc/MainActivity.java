package com.milena.calculadoradeimc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText textPeso, textAltura;
    private Button btnCalcularIMC;
    private TextView textResultadoImc, textValorImc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // getSupportActionBar().hide();

        btnCalcularIMC = findViewById(R.id.calculaIMC);
        textPeso = findViewById(R.id.editPeso);
        textAltura = findViewById(R.id.editAltura);
        textResultadoImc = findViewById(R.id.textResultadoIMC);
        textValorImc = findViewById(R.id.textResultadoValorImc);



        btnCalcularIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaInput(textPeso, textAltura);
            }
        });
    }

    public void validaInput(TextInputEditText textPeso, TextInputEditText textAltura){
        String peso = textPeso.getText().toString();
        String altura = textAltura.getText().toString();

        if (peso.isEmpty()){
            textPeso.setError(getText(R.string.peso_erro));
        }else if (altura.isEmpty()){
            textAltura.setError(getText(R.string.altura_erro));
        }else {
            calculaIMC();
        }
    }

    public  void calculaIMC(){
        Float peso = Float.parseFloat(textPeso.getText().toString());
        Float altura = Float.parseFloat(textAltura.getText().toString());
        Float imc = peso / (altura * altura);

        textValorImc.setText(imc.toString());

        if (imc < 18.5){
            textResultadoImc.setText(getText(R.string.peso_baixo));
        }else if (imc <= 24.9){
            textResultadoImc.setText(getText(R.string.saudavel));
        }else if (imc <= 29.9){
            textResultadoImc.setText(getText(R.string.sobrepeso));
        }else if (imc <= 34.9){
            textResultadoImc.setText(getText(R.string.obesidade_1));
        }else if (imc <= 39.9){
            textResultadoImc.setText(getText(R.string.obesidade_2));
        }else{
            textResultadoImc.setText(getText(R.string.obesidade_3));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.ic_limpa ){
            textPeso.setText("");
            textAltura.setText("");
            textResultadoImc.setText("-");
            textValorImc.setText("0.0");
        }
        return super.onOptionsItemSelected(item);
    }
}