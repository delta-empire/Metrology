package ru.sergeipavlov.metrology.kip.transformation;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

import ru.sergeipavlov.metrology.R;

public class TemperatureActivity extends AppCompatActivity {

    DecimalFormat decimalFormat = new DecimalFormat("#.###");

    UnitTransformationCalc unitTransformationCalc = new UnitTransformationCalc();

    EditText etTemperatureCelsium,
                etTemperatureKelvin,
                etTemperatureFahrengeit,
                etTempretureRankin,
                etTemperatureReomur;

    public double getEtTemperatureCelsium() {
        if (etTemperatureCelsium.getText().toString().isEmpty()) {
            etTemperatureCelsium.setText(R.string.zero);
        }
        String stringFindPoint = etTemperatureCelsium.getText().toString();
        stringFindPoint = stringFindPoint.replace(',', '.');
        return Double.parseDouble(stringFindPoint);
    }

    public double getEtTemperatureFahrengeit() {
        if (etTemperatureFahrengeit.getText().toString().isEmpty()) {
            etTemperatureFahrengeit.setText(R.string.zero);
        }
        String stringFindPoint = etTemperatureFahrengeit.getText().toString();
        stringFindPoint = stringFindPoint.replace(',', '.');
        return Double.parseDouble(stringFindPoint);
    }

    public double getEtTemperatureKelvin() {
        if (etTemperatureKelvin.getText().toString().isEmpty()) {
            etTemperatureKelvin.setText(R.string.zero);
        }
        String stringFindPoint = etTemperatureKelvin.getText().toString();
        stringFindPoint = stringFindPoint.replace(',', '.');
        return Double.parseDouble(stringFindPoint);
    }

    public double getEtTemperatureReomur() {
        if (etTemperatureReomur.getText().toString().isEmpty()) {
            etTemperatureReomur.setText(R.string.zero);
        }
        String stringFindPoint = etTemperatureReomur.getText().toString();
        stringFindPoint = stringFindPoint.replace(',', '.');
        return Double.parseDouble(stringFindPoint);
    }

    public double getEtTempretureRankin() {
        if (etTempretureRankin.getText().toString().isEmpty()) {
            etTempretureRankin.setText(R.string.zero);
        }
        String stringFindPoint = etTempretureRankin.getText().toString();
        stringFindPoint = stringFindPoint.replace(',', '.');
        return Double.parseDouble(stringFindPoint);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temperature);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.temperature), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTemperatureCelsium = findViewById(R.id.etTemperatureCelsium);
        etTemperatureKelvin = findViewById(R.id.etTemperatureKelvin);
        etTemperatureFahrengeit = findViewById(R.id.etTemperatureFahrengeit);
        etTempretureRankin = findViewById(R.id.etTemperatureRankin);
        etTemperatureReomur = findViewById(R.id.etTemperatureReomur);

        etTemperatureCelsium.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                getEtTemperatureCelsium();
                double celsium = Double.parseDouble(etTemperatureCelsium.getText().toString());
                calc(celsium);
            }
        });

    }

    void calc(double celsium) {
        double[] results = unitTransformationCalc.celsiumToAnother(celsium);
        etTemperatureKelvin.setText(String.valueOf(results[0]));
        etTemperatureFahrengeit.setText(String.valueOf(results[1]));
        etTempretureRankin.setText(String.valueOf(results[2]));
        etTemperatureReomur.setText(String.valueOf(results[3]));
    }
}
