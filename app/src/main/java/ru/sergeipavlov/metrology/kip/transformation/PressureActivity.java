package ru.sergeipavlov.metrology.kip.transformation;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.sergeipavlov.metrology.R;

public class PressureActivity extends AppCompatActivity {

    EditText etPascal,
            etKgsFractionSm2,
            etKgsFractionM2,
            etMilliBar,
            etBar,
            etKiloPa,
            etMegaPascal,
            etNutonFractionMeter2,
            etKiloNutonFractionM2,
            etMegaNutonFractionM2,
            etNutonFractionSm2,
            etNutomFractionMillimetr2,
            etAtmosphere,
            etTechnicalAtmosphere,
            etMilliMetrH20,
            etSantimetrH2O,
            etMetreH2O,
            etInH2O,
            etFtH2O,
            etMillimetreHG,
            etSantimetreHG,
            etInHG,
            etKsi,
            etPsi,
            etPsf,
            etTsiUSA,
            etTsfUSA,
            etTsiUK,
            etTsfUK;

    UnitTransformationCalc unitTransformationCalc = new UnitTransformationCalc();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pressure);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pressure), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etPascal = findViewById(R.id.etPascal);
        etPascal.setText(R.string.zero);
        etKgsFractionSm2 = findViewById(R.id.etKgsFractionSm2);
        etKgsFractionM2 = findViewById(R.id.etKgsFractionM2);
        etMilliBar = findViewById(R.id.etMilliBar);
        etBar = findViewById(R.id.etBar);
        etKiloPa = findViewById(R.id.etKiloPa);
        etMegaPascal = findViewById(R.id.etMegaPascal);
        etNutonFractionMeter2 = findViewById(R.id.etNutonFractionMeter2);
        etKiloNutonFractionM2 = findViewById(R.id.etKiloNutonFractionM2);
        etMegaNutonFractionM2 = findViewById(R.id.etMegaNutonFractionM2);
        etNutonFractionSm2 = findViewById(R.id.etNutonFractionSm2);
        etNutomFractionMillimetr2 = findViewById(R.id.etNutomFractionMillimetr2);
        etAtmosphere = findViewById(R.id.etAtmosphere);
        etTechnicalAtmosphere = findViewById(R.id.etTechnicalAtmosphere);
        etMilliMetrH20 = findViewById(R.id.etMilliMetrH20);
        etSantimetrH2O = findViewById(R.id.etSantimetrH2O);
        etMetreH2O = findViewById(R.id.etMetreH2O);
        etInH2O = findViewById(R.id.etInchH2O);
        etFtH2O = findViewById(R.id.etFutH2O);
        etMillimetreHG = findViewById(R.id.etMillimetreHG);
        etSantimetreHG = findViewById(R.id.etSantimetreHG);
        etInHG = findViewById(R.id.etInchHG);
        etKsi = findViewById(R.id.etKsi);
        etPsi = findViewById(R.id.etPsi);
        etPsf = findViewById(R.id.etPsf);
        etTsiUSA = findViewById(R.id.etTsiUSA);
        etTsfUSA = findViewById(R.id.etTsfUSA);
        etTsiUK = findViewById(R.id.etTsiUK);
        etTsfUK = findViewById(R.id.etTsfUK);

        etPascal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                double pascal = Double.parseDouble(etPascal.getText().toString());
                calc(pascal);

            }
        });

    }

    void calc(double pascal) {
        double results[] = unitTransformationCalc.pascalToDerivatives(pascal);
        etKgsFractionSm2.setText(String.valueOf(results[0]));
        etKgsFractionM2.setText(String.valueOf(results[1]));
        etMilliBar.setText(String.valueOf(results[2]));
        etBar.setText(String.valueOf(results[3]));
        etKiloPa.setText(String.valueOf(results[4]));
        etMegaPascal.setText(String.valueOf(results[5]));
        etNutonFractionMeter2.setText(String.valueOf(results[6]));
        etKiloNutonFractionM2.setText(String.valueOf(results[7]));
        etMegaNutonFractionM2.setText(String.valueOf(results[8]));
        etNutonFractionSm2.setText(String.valueOf(results[9]));
        etNutomFractionMillimetr2.setText(String.valueOf(results[10]));
        etAtmosphere.setText(String.valueOf(results[11]));
        etTechnicalAtmosphere.setText(String.valueOf(results[12]));
        etMilliMetrH20.setText(String.valueOf(results[13]));
        etSantimetrH2O.setText(String.valueOf(results[14]));
        etMetreH2O.setText(String.valueOf(results[15]));
        etInH2O.setText(String.valueOf(results[16]));
        etFtH2O.setText(String.valueOf(results[17]));
        etMillimetreHG.setText(String.valueOf(results[18]));
        etSantimetreHG.setText(String.valueOf(results[19]));
        etInHG.setText(String.valueOf(results[20]));
        etKsi.setText(String.valueOf(results[21]));
        etPsi.setText(String.valueOf(results[22]));
        etPsf.setText(String.valueOf(results[23]));
        etTsiUSA.setText(String.valueOf(results[24]));
        etTsfUSA.setText(String.valueOf(results[25]));
        etTsiUK.setText(String.valueOf(results[26]));
        etTsfUK.setText(String.valueOf(results[27]));
    }
}