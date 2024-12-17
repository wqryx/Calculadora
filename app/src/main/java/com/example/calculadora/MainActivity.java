package com.example.calculadora;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView mostrarPantalla;
    private String entradaActual = "";
    private String entradaAnterior = "";
    private String operador = "";
    private boolean operadorPresionado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mostrarPantalla = findViewById(R.id.mostrarPantalla);
        configurarBotones();
    }

    private void configurarBotones() {
        findViewById(R.id.boton1).setOnClickListener(v -> agregarAEntrada("1"));
        findViewById(R.id.boton2).setOnClickListener(v -> agregarAEntrada("2"));
        findViewById(R.id.boton3).setOnClickListener(v -> agregarAEntrada("3"));
        findViewById(R.id.boton4).setOnClickListener(v -> agregarAEntrada("4"));
        findViewById(R.id.boton5).setOnClickListener(v -> agregarAEntrada("5"));
        findViewById(R.id.boton6).setOnClickListener(v -> agregarAEntrada("6"));
        findViewById(R.id.boton7).setOnClickListener(v -> agregarAEntrada("7"));
        findViewById(R.id.boton8).setOnClickListener(v -> agregarAEntrada("8"));
        findViewById(R.id.boton9).setOnClickListener(v -> agregarAEntrada("9"));
        findViewById(R.id.boton0).setOnClickListener(v -> agregarAEntrada("0"));
        findViewById(R.id.botonSuma).setOnClickListener(v -> establecerOperador("+"));
        findViewById(R.id.botonResta).setOnClickListener(v -> establecerOperador("-"));
        findViewById(R.id.botonMultiplicacion).setOnClickListener(v -> establecerOperador("×"));
        findViewById(R.id.botonDivision).setOnClickListener(v -> establecerOperador("÷"));
        findViewById(R.id.botonIgual).setOnClickListener(v -> calcularResultado());
        findViewById(R.id.botonBorrar).setOnClickListener(v -> borrarEntrada());
    }

    private void agregarAEntrada(String valor) {
        if (operadorPresionado) {
            entradaActual = "";
            operadorPresionado = false;
        }
        entradaActual += valor;
        mostrarPantalla.setText(entradaActual);
    }

    private void establecerOperador(String operador) {
        if (!entradaActual.isEmpty()) {
            entradaAnterior = entradaActual;
            entradaActual = "";
            this.operador = operador;
            operadorPresionado = true;
        }
    }

    private void calcularResultado() {
        if (!entradaAnterior.isEmpty() && !entradaActual.isEmpty() && !operador.isEmpty()) {
            double num1 = Double.parseDouble(entradaAnterior);
            double num2 = Double.parseDouble(entradaActual);
            double resultado = 0;
            switch (operador) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "×":
                    resultado = num1 * num2;
                    break;
                case "÷":
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        mostrarPantalla.setText("Error");
                        return;
                    }
                    break;
            }
            mostrarPantalla.setText(String.valueOf(resultado));
            entradaActual = String.valueOf(resultado);
            operador = "";
            entradaAnterior = "";
        }
    }

    private void borrarEntrada() {
        entradaActual = "";
        entradaAnterior = "";
        operador = "";
        mostrarPantalla.setText("0");
    }
}