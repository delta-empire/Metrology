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

public class FrequencyFragment extends Fragment {
    private EditText hertz;
    private EditText kilohertz;
    private EditText megahertz;
    private EditText gigahertz;
    private boolean isUpdating;

    private enum Unit {HERTZ, KILOHERTZ, MEGAHERTZ, GIGAHERTZ}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frequency, container, false);
        hertz = view.findViewById(R.id.edit_hertz);
        kilohertz = view.findViewById(R.id.edit_kilohertz);
        megahertz = view.findViewById(R.id.edit_megahertz);
        gigahertz = view.findViewById(R.id.edit_gigahertz);

        view.findViewById(R.id.symbol_hertz).setOnClickListener(v -> showDescription(R.string.desc_hertz));
        view.findViewById(R.id.symbol_kilohertz).setOnClickListener(v -> showDescription(R.string.desc_kilohertz));
        view.findViewById(R.id.symbol_megahertz).setOnClickListener(v -> showDescription(R.string.desc_megahertz));
        view.findViewById(R.id.symbol_gigahertz).setOnClickListener(v -> showDescription(R.string.desc_gigahertz));

        addWatcher(hertz, Unit.HERTZ);
        addWatcher(kilohertz, Unit.KILOHERTZ);
        addWatcher(megahertz, Unit.MEGAHERTZ);
        addWatcher(gigahertz, Unit.GIGAHERTZ);

        isUpdating = true;
        double hz = 0.0;
        int precision = 0;
        setText(hertz, fromHertz(Unit.HERTZ, hz), precision, false);
        setText(kilohertz, fromHertz(Unit.KILOHERTZ, hz), precision, false);
        setText(megahertz, fromHertz(Unit.MEGAHERTZ, hz), precision, false);
        setText(gigahertz, fromHertz(Unit.GIGAHERTZ, hz), precision, false);
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
                    double hz = toHertz(unit, value);
                    if (hz < 0.0) {
                        Toast.makeText(requireContext(), R.string.warn_negative_frequency, Toast.LENGTH_SHORT).show();
                        hz = 0.0;
                        setAll(hz, precision);
                    } else {
                        setText(hertz, fromHertz(Unit.HERTZ, hz), precision, unit == Unit.HERTZ);
                        setText(kilohertz, fromHertz(Unit.KILOHERTZ, hz), precision, unit == Unit.KILOHERTZ);
                        setText(megahertz, fromHertz(Unit.MEGAHERTZ, hz), precision, unit == Unit.MEGAHERTZ);
                        setText(gigahertz, fromHertz(Unit.GIGAHERTZ, hz), precision, unit == Unit.GIGAHERTZ);
                    }
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setAll(double hz, int precision) {
        setText(hertz, fromHertz(Unit.HERTZ, hz), precision, false);
        setText(kilohertz, fromHertz(Unit.KILOHERTZ, hz), precision, false);
        setText(megahertz, fromHertz(Unit.MEGAHERTZ, hz), precision, false);
        setText(gigahertz, fromHertz(Unit.GIGAHERTZ, hz), precision, false);
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

    private double toHertz(Unit unit, double value) {
        switch (unit) {
            case HERTZ:
                return value;
            case KILOHERTZ:
                return value * 1_000.0;
            case MEGAHERTZ:
                return value * 1_000_000.0;
            case GIGAHERTZ:
                return value * 1_000_000_000.0;
        }
        return value;
    }

    private double fromHertz(Unit unit, double hertz) {
        switch (unit) {
            case HERTZ:
                return hertz;
            case KILOHERTZ:
                return hertz / 1_000.0;
            case MEGAHERTZ:
                return hertz / 1_000_000.0;
            case GIGAHERTZ:
                return hertz / 1_000_000_000.0;
        }
        return hertz;
    }
}
