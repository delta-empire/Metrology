package ru.sergeipavlov.metrology;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TemperatureFragment extends Fragment {
    private EditText kelvin;
    private EditText celsius;
    private EditText fahrenheit;
    private EditText reaumur;
    private EditText romer;
    private EditText rankine;
    private EditText delisle;
    private EditText newton;
    private boolean isUpdating;

    private enum Unit {KELVIN, CELSIUS, FAHRENHEIT, REAUMUR, ROMER, RANKINE, DELISLE, NEWTON}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);
        kelvin = view.findViewById(R.id.edit_kelvin);
        celsius = view.findViewById(R.id.edit_celsius);
        fahrenheit = view.findViewById(R.id.edit_fahrenheit);
        reaumur = view.findViewById(R.id.edit_reaumur);
        romer = view.findViewById(R.id.edit_romer);
        rankine = view.findViewById(R.id.edit_rankine);
        delisle = view.findViewById(R.id.edit_delisle);
        newton = view.findViewById(R.id.edit_newton);

        view.findViewById(R.id.symbol_kelvin).setOnClickListener(v -> showDescription(R.string.desc_kelvin));
        view.findViewById(R.id.symbol_celsius).setOnClickListener(v -> showDescription(R.string.desc_celsius));
        view.findViewById(R.id.symbol_fahrenheit).setOnClickListener(v -> showDescription(R.string.desc_fahrenheit));
        view.findViewById(R.id.symbol_reaumur).setOnClickListener(v -> showDescription(R.string.desc_reaumur));
        view.findViewById(R.id.symbol_romer).setOnClickListener(v -> showDescription(R.string.desc_romer));
        view.findViewById(R.id.symbol_rankine).setOnClickListener(v -> showDescription(R.string.desc_rankine));
        view.findViewById(R.id.symbol_delisle).setOnClickListener(v -> showDescription(R.string.desc_delisle));
        view.findViewById(R.id.symbol_newton).setOnClickListener(v -> showDescription(R.string.desc_newton));

        addWatcher(kelvin, Unit.KELVIN);
        addWatcher(celsius, Unit.CELSIUS);
        addWatcher(fahrenheit, Unit.FAHRENHEIT);
        addWatcher(reaumur, Unit.REAUMUR);
        addWatcher(romer, Unit.ROMER);
        addWatcher(rankine, Unit.RANKINE);
        addWatcher(delisle, Unit.DELISLE);
        addWatcher(newton, Unit.NEWTON);
        // Initialize with absolute zero
        isUpdating = true;
        double k = 0.0;
        int precision = 3;
        setText(kelvin, fromKelvin(Unit.KELVIN, k), precision, false);
        setText(celsius, fromKelvin(Unit.CELSIUS, k), precision, false);
        setText(fahrenheit, fromKelvin(Unit.FAHRENHEIT, k), precision, false);
        setText(reaumur, fromKelvin(Unit.REAUMUR, k), precision, false);
        setText(romer, fromKelvin(Unit.ROMER, k), precision, false);
        setText(rankine, fromKelvin(Unit.RANKINE, k), precision, false);
        setText(delisle, fromKelvin(Unit.DELISLE, k), precision, false);
        setText(newton, fromKelvin(Unit.NEWTON, k), precision, false);
        isUpdating = false;

        return view;
    }

    private void showDescription(int resId) {
        Toast.makeText(requireContext(), resId, Toast.LENGTH_SHORT).show();
    }

    private void addWatcher(EditText editText, Unit unit) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (isUpdating) return;
                String text = s.toString();
                if (text.isEmpty() || text.equals("-") || text.equals(".") || text.equals("-.") ) {
                    return;
                }
                try {
                    double value = Double.parseDouble(text);
                    int precision = Math.max(getPrecision(text), 3);
                    isUpdating = true;
                    double k = toKelvin(unit, value);
                    if (k < 0.0) {
                        Toast.makeText(requireContext(), R.string.warn_below_zero_kelvin, Toast.LENGTH_SHORT).show();
                        k = 0.0;
                        setText(kelvin, fromKelvin(Unit.KELVIN, k), precision, false);
                        setText(celsius, fromKelvin(Unit.CELSIUS, k), precision, false);
                        setText(fahrenheit, fromKelvin(Unit.FAHRENHEIT, k), precision, false);
                        setText(reaumur, fromKelvin(Unit.REAUMUR, k), precision, false);
                        setText(romer, fromKelvin(Unit.ROMER, k), precision, false);
                        setText(rankine, fromKelvin(Unit.RANKINE, k), precision, false);
                        setText(delisle, fromKelvin(Unit.DELISLE, k), precision, false);
                        setText(newton, fromKelvin(Unit.NEWTON, k), precision, false);
                    } else {
                        setText(kelvin, fromKelvin(Unit.KELVIN, k), precision, unit == Unit.KELVIN);
                        setText(celsius, fromKelvin(Unit.CELSIUS, k), precision, unit == Unit.CELSIUS);
                        setText(fahrenheit, fromKelvin(Unit.FAHRENHEIT, k), precision, unit == Unit.FAHRENHEIT);
                        setText(reaumur, fromKelvin(Unit.REAUMUR, k), precision, unit == Unit.REAUMUR);
                        setText(romer, fromKelvin(Unit.ROMER, k), precision, unit == Unit.ROMER);
                        setText(rankine, fromKelvin(Unit.RANKINE, k), precision, unit == Unit.RANKINE);
                        setText(delisle, fromKelvin(Unit.DELISLE, k), precision, unit == Unit.DELISLE);
                        setText(newton, fromKelvin(Unit.NEWTON, k), precision, unit == Unit.NEWTON);
                    }
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setText(EditText edit, double value, int precision, boolean isSource) {
        if (isSource) return;
        edit.setText(format(value, precision));
    }

    private String format(double value, int precision) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(precision, RoundingMode.HALF_UP);
        return bd.toPlainString();
    }

    private int getPrecision(String text) {
        int idx = text.indexOf('.');
        return idx >= 0 ? text.length() - idx - 1 : 0;
    }

    private double toKelvin(Unit unit, double value) {
        switch (unit) {
            case KELVIN:
                return value;
            case CELSIUS:
                return value + 273.15;
            case FAHRENHEIT:
                return (value - 32.0) * 5.0 / 9.0 + 273.15;
            case REAUMUR:
                return value * 5.0 / 4.0 + 273.15;
            case ROMER:
                return (value - 7.5) * 40.0 / 21.0 + 273.15;
            case RANKINE:
                return value * 5.0 / 9.0;
            case DELISLE:
                return 373.15 - value * 2.0 / 3.0;
            case NEWTON:
                return value * 100.0 / 33.0 + 273.15;
        }
        return value;
    }

    private double fromKelvin(Unit unit, double kelvin) {
        switch (unit) {
            case KELVIN:
                return kelvin;
            case CELSIUS:
                return kelvin - 273.15;
            case FAHRENHEIT:
                return (kelvin - 273.15) * 9.0 / 5.0 + 32.0;
            case REAUMUR:
                return (kelvin - 273.15) * 4.0 / 5.0;
            case ROMER:
                return (kelvin - 273.15) * 21.0 / 40.0 + 7.5;
            case RANKINE:
                return kelvin * 9.0 / 5.0;
            case DELISLE:
                return (373.15 - kelvin) * 3.0 / 2.0;
            case NEWTON:
                return (kelvin - 273.15) * 33.0 / 100.0;
        }
        return kelvin;
    }
}
