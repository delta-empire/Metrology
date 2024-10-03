package ru.sergeipavlov.metrology.kip;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.sergeipavlov.metrology.R;

public class ScaleSignalActivity extends AppCompatActivity {

    private EditText etStartPhysicalScale,
            etEndPhysicalScale,
            etPhysicalValue,
            etUnifiedSignal,
            etStartUnifiedSignal,
            etEndUnifiedSignal;

    private String scaleType = "Линейная";

    ScaleSignalCalc scaleSignalCalc = new ScaleSignalCalc();

    public double getEtEndPhysicalScale() {
        if (etEndPhysicalScale.getText().toString().isEmpty()) {
            etEndPhysicalScale.setText(R.string.one_hundred);
        }
            return Double.parseDouble(etEndPhysicalScale.getText().toString());
    }

    public double getEtEndUnifiedSignal() {
        if (etEndUnifiedSignal.getText().toString().isEmpty()) {
            etEndUnifiedSignal.setText(R.string.twenty);
        }
        return Double.parseDouble(etEndUnifiedSignal.getText().toString());
    }

    public double getPhysicalValue() {
        if (etPhysicalValue.getText().toString().isEmpty()) {
            etPhysicalValue.setText(R.string.fifty);
        }
        return Double.parseDouble(etPhysicalValue.getText().toString());
    }

    public double getEtStartPhysicalScale() {
        if (etStartPhysicalScale.getText().toString().isEmpty()) {
            etStartPhysicalScale.setText(R.string.zero);
        }
        return Double.parseDouble(etStartPhysicalScale.getText().toString());

    }

    public double getEtStartUnifiedSignal() {
        if (etStartUnifiedSignal.getText().toString().isEmpty()) {
            etStartUnifiedSignal.setText(R.string.four);
        }
        return Double.parseDouble(etStartUnifiedSignal.getText().toString());
    }

    public double getEtUnifiedSignal() {
        if (etUnifiedSignal.getText().toString().isEmpty()) {
            etUnifiedSignal.setText(R.string.twelve);
        }
        return Double.parseDouble(etUnifiedSignal.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.scale_signal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scale_signal), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spChooseScaleType = findViewById(R.id.spChooseScaleType);

        etStartPhysicalScale = findViewById(R.id.etStartPhysicalScale);
        etEndPhysicalScale = findViewById(R.id.etEndPhysicalScale);
        etPhysicalValue = findViewById(R.id.etPhysicalValue);
        etUnifiedSignal = findViewById(R.id.etUnifiedSignal);
        etStartUnifiedSignal = findViewById(R.id.etStartUnifiedSignal);
        etEndUnifiedSignal = findViewById(R.id.etEndUnifiedSignal);

        etStartPhysicalScale.setHint("0");
        etEndPhysicalScale.setHint("100");
        etPhysicalValue.setHint("50");
        etUnifiedSignal.setHint("12");
        etStartUnifiedSignal.setHint("4");
        etEndUnifiedSignal.setHint("20");

        spChooseScaleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scaleType = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                scaleType = "Линейная";
            }
        });

        etStartPhysicalScale.setOnFocusChangeListener((v, hasFocus) -> calcPhysicalValuew());

        etEndPhysicalScale.setOnFocusChangeListener((v, hasFocus) -> calcPhysicalValuew());

        etPhysicalValue.setOnFocusChangeListener((v, hasFocus) -> calcUnifiedSignal());

        etUnifiedSignal.setOnFocusChangeListener((v, hasFocus) -> calcPhysicalValuew());

        etStartUnifiedSignal.setOnFocusChangeListener((v, hasFocus) -> calcPhysicalValuew());

        etEndUnifiedSignal.setOnFocusChangeListener((v, hasFocus) -> calcPhysicalValuew());
    }

    private void calcPhysicalValuew() {
        double result;
        switch (scaleType) {
            case "Линейная":
                result = scaleSignalCalc.calcPhysicalValueLinearScale(getEtUnifiedSignal(), getEtStartUnifiedSignal(),
                        getEtEndUnifiedSignal(), getEtEndPhysicalScale(), getEtStartPhysicalScale());
                etPhysicalValue.setText(String.valueOf(result));
                break;
            case "Линейная убывающая":
                result = scaleSignalCalc.calcPhysicalValueLinearDecreasingScale(getEtUnifiedSignal(), getEtStartUnifiedSignal(),
                        getEtEndUnifiedSignal(), getEtEndPhysicalScale(), getEtStartPhysicalScale());
                etPhysicalValue.setText(String.valueOf(result));
                break;
            case "Квадратичная":
                result = scaleSignalCalc.calcPhysicalValueQuadraticScale(getEtUnifiedSignal(), getEtStartUnifiedSignal(),
                        getEtEndUnifiedSignal(), getEtEndPhysicalScale(), getEtStartPhysicalScale());
                etPhysicalValue.setText(String.valueOf(result));
                break;
            case "Квадратичная, убывающая":
                result = scaleSignalCalc.calcPhysicalValueQuadraticDecreasingScale(getEtUnifiedSignal(), getEtStartUnifiedSignal(),
                        getEtEndUnifiedSignal(), getEtEndPhysicalScale(), getEtStartPhysicalScale());
                etPhysicalValue.setText(String.valueOf(result));
                break;
            case "Корнеизвлекающая":
                result = scaleSignalCalc.calcPhysicalValueRootextractingScale(getEtUnifiedSignal(), getEtStartUnifiedSignal(),
                        getEtEndUnifiedSignal(), getEtEndPhysicalScale(), getEtStartPhysicalScale());
                etPhysicalValue.setText(String.valueOf(result));
                break;
            case "Корнеизвлекающая, убывающая":
                result = scaleSignalCalc.calcPhysicalValueRootextractingDecreasingScale(getEtUnifiedSignal(), getEtStartUnifiedSignal(),
                        getEtEndUnifiedSignal(), getEtEndPhysicalScale(), getEtStartPhysicalScale());
                etPhysicalValue.setText(String.valueOf(result));
                break;
            default:
                Toast.makeText(ScaleSignalActivity.this, "Not selected scale type", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void valueFromCalcUnified() {
        double[] value = {getPhysicalValue(),
        getEtStartPhysicalScale(),
        getEtEndPhysicalScale(),
        getEtEndUnifiedSignal(),
        getEtStartUnifiedSignal()
        };
    }

    private void calcUnifiedSignal() {
        double result;
        switch (scaleType) {
            case "Линейная":
                result = scaleSignalCalc.calcUnifiedSignalLinearScale(getPhysicalValue(), getEtStartPhysicalScale(),
                        getEtEndPhysicalScale(), getEtEndUnifiedSignal(), getEtStartUnifiedSignal());
                etUnifiedSignal.setText(String.valueOf(result));
                break;
            case "Линейная убывающая":
                result = scaleSignalCalc.calcUnifiedSignalLinearDecreasingScale(getPhysicalValue(), getEtStartPhysicalScale(),
                        getEtEndPhysicalScale(), getEtEndUnifiedSignal(), getEtStartUnifiedSignal());
                etUnifiedSignal.setText(String.valueOf(result));
                break;
            case "Квадратичная":
                result = scaleSignalCalc.calcUnifiedSignalQuadraticScale(getPhysicalValue(), getEtStartPhysicalScale(),
                        getEtEndPhysicalScale(), getEtEndUnifiedSignal(), getEtStartUnifiedSignal());
                etUnifiedSignal.setText(String.valueOf(result));
                break;
            case "Квадратичная, убывающая":
                result = scaleSignalCalc.calcUnifiedSignalQuadraticDecreasingScale(getPhysicalValue(), getEtStartPhysicalScale(),
                        getEtEndPhysicalScale(), getEtEndUnifiedSignal(), getEtStartUnifiedSignal());
                etUnifiedSignal.setText(String.valueOf(result));
                break;
            case "Корнеизвлекающая":
                result = scaleSignalCalc.calcUnifiedSignalRootextractingScale(getPhysicalValue(), getEtStartPhysicalScale(),
                        getEtEndPhysicalScale(), getEtEndUnifiedSignal(), getEtStartUnifiedSignal());
                etUnifiedSignal.setText(String.valueOf(result));
                break;
            case "Корнеизвлекающая, убывающая":
                result = scaleSignalCalc.calcUnifiedSignalRootextractingDecreasingScale(getPhysicalValue(), getEtStartPhysicalScale(),
                        getEtEndPhysicalScale(), getEtEndUnifiedSignal(), getEtStartUnifiedSignal());
                etUnifiedSignal.setText(String.valueOf(result));
                break;
            default:
                Toast.makeText(ScaleSignalActivity.this, "Not selected scale type", Toast.LENGTH_LONG).show();
                break;
        }
    }
}