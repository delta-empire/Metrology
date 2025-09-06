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

public class CurrentFragment extends Fragment {
    private EditText ampere;
    private EditText microampere;
    private EditText milliampere;
    private EditText kiloampere;
    private boolean isUpdating;

    private enum Unit {AMPERE, MICROAMPERE, MILLIAMPERE, KILOAMPERE}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current, container, false);
        ampere = view.findViewById(R.id.edit_ampere);
        microampere = view.findViewById(R.id.edit_microampere);
        milliampere = view.findViewById(R.id.edit_milliampere);
        kiloampere = view.findViewById(R.id.edit_kiloampere);

        view.findViewById(R.id.symbol_ampere).setOnClickListener(v -> showDescription(R.string.desc_ampere));
        view.findViewById(R.id.symbol_microampere).setOnClickListener(v -> showDescription(R.string.desc_microampere));
        view.findViewById(R.id.symbol_milliampere).setOnClickListener(v -> showDescription(R.string.desc_milliampere));
        view.findViewById(R.id.symbol_kiloampere).setOnClickListener(v -> showDescription(R.string.desc_kiloampere));

        addWatcher(ampere, Unit.AMPERE);
        addWatcher(microampere, Unit.MICROAMPERE);
        addWatcher(milliampere, Unit.MILLIAMPERE);
        addWatcher(kiloampere, Unit.KILOAMPERE);

        isUpdating = true;
        double a = 0.0;
        int precision = 0;
        setText(ampere, fromAmpere(Unit.AMPERE, a), precision, false);
        setText(microampere, fromAmpere(Unit.MICROAMPERE, a), precision, false);
        setText(milliampere, fromAmpere(Unit.MILLIAMPERE, a), precision, false);
        setText(kiloampere, fromAmpere(Unit.KILOAMPERE, a), precision, false);
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
                    double a = toAmpere(unit, value);
                    setText(ampere, fromAmpere(Unit.AMPERE, a), precision, unit == Unit.AMPERE);
                    setText(microampere, fromAmpere(Unit.MICROAMPERE, a), precision, unit == Unit.MICROAMPERE);
                    setText(milliampere, fromAmpere(Unit.MILLIAMPERE, a), precision, unit == Unit.MILLIAMPERE);
                    setText(kiloampere, fromAmpere(Unit.KILOAMPERE, a), precision, unit == Unit.KILOAMPERE);
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

    private double toAmpere(Unit unit, double value) {
        switch (unit) {
            case AMPERE:
                return value;
            case MICROAMPERE:
                return value / 1_000_000.0;
            case MILLIAMPERE:
                return value / 1_000.0;
            case KILOAMPERE:
                return value * 1_000.0;
        }
        return value;
    }

    private double fromAmpere(Unit unit, double ampere) {
        switch (unit) {
            case AMPERE:
                return ampere;
            case MICROAMPERE:
                return ampere * 1_000_000.0;
            case MILLIAMPERE:
                return ampere * 1_000.0;
            case KILOAMPERE:
                return ampere / 1_000.0;
        }
        return ampere;
    }
}
