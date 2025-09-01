package ru.sergeipavlov.metrology;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etCelsius;
    private EditText etFahrenheit;
    private EditText etKelvin;
    private boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etCelsius = findViewById(R.id.etCelsius);
        etFahrenheit = findViewById(R.id.etFahrenheit);
        etKelvin = findViewById(R.id.etKelvin);

        etCelsius.addTextChangedListener(new TemperatureWatcher(etCelsius));
        etFahrenheit.addTextChangedListener(new TemperatureWatcher(etFahrenheit));
        etKelvin.addTextChangedListener(new TemperatureWatcher(etKelvin));
    }

    private class TemperatureWatcher implements TextWatcher {

        private final EditText source;

        TemperatureWatcher(EditText source) {
            this.source = source;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (isUpdating) return;

            String text = s.toString();
            if (text.isEmpty() || text.equals("-") || text.equals(".")) {
                isUpdating = true;
                if (source != etCelsius) etCelsius.getText().clear();
                if (source != etFahrenheit) etFahrenheit.getText().clear();
                if (source != etKelvin) etKelvin.getText().clear();
                isUpdating = false;
                return;
            }

            try {
                double value = Double.parseDouble(text);
                String format = "%.3f";

                double celsius;
                double fahrenheit;
                double kelvin;

                if (source == etCelsius) {
                    celsius = value;
                    fahrenheit = celsius * 9 / 5 + 32;
                    kelvin = celsius + 273.15;
                } else if (source == etFahrenheit) {
                    fahrenheit = value;
                    celsius = (fahrenheit - 32) * 5 / 9;
                    kelvin = celsius + 273.15;
                } else {
                    kelvin = value;
                    celsius = kelvin - 273.15;
                    fahrenheit = celsius * 9 / 5 + 32;
                }

                isUpdating = true;
                if (source != etCelsius) {
                    etCelsius.setText(String.format(Locale.US, format, celsius));
                    etCelsius.setSelection(etCelsius.getText().length());
                }
                if (source != etFahrenheit) {
                    etFahrenheit.setText(String.format(Locale.US, format, fahrenheit));
                    etFahrenheit.setSelection(etFahrenheit.getText().length());
                }
                if (source != etKelvin) {
                    etKelvin.setText(String.format(Locale.US, format, kelvin));
                    etKelvin.setSelection(etKelvin.getText().length());
                }
                isUpdating = false;
            } catch (NumberFormatException e) {
                // ignore invalid input
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

}
