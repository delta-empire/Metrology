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

public class SubstanceFragment extends Fragment {
    private EditText mole;
    private EditText millimole;
    private EditText kilomole;
    private EditText megamole;
    private boolean isUpdating;

    private enum Unit {MOLE, MILLIMOLE, KILOMOLE, MEGAMOLE}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_substance, container, false);
        mole = view.findViewById(R.id.edit_mole);
        millimole = view.findViewById(R.id.edit_millimole);
        kilomole = view.findViewById(R.id.edit_kilomole);
        megamole = view.findViewById(R.id.edit_megamole);

        view.findViewById(R.id.symbol_mole).setOnClickListener(v -> showDescription(R.string.desc_mole));
        view.findViewById(R.id.symbol_millimole).setOnClickListener(v -> showDescription(R.string.desc_millimole));
        view.findViewById(R.id.symbol_kilomole).setOnClickListener(v -> showDescription(R.string.desc_kilomole));
        view.findViewById(R.id.symbol_megamole).setOnClickListener(v -> showDescription(R.string.desc_megamole));

        addWatcher(mole, Unit.MOLE);
        addWatcher(millimole, Unit.MILLIMOLE);
        addWatcher(kilomole, Unit.KILOMOLE);
        addWatcher(megamole, Unit.MEGAMOLE);

        isUpdating = true;
        double mol = 0.0;
        int precision = 3;
        setText(mole, fromMole(Unit.MOLE, mol), precision, false);
        setText(millimole, fromMole(Unit.MILLIMOLE, mol), precision, false);
        setText(kilomole, fromMole(Unit.KILOMOLE, mol), precision, false);
        setText(megamole, fromMole(Unit.MEGAMOLE, mol), precision, false);
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
                    double mol = toMole(unit, value);
                    setText(mole, fromMole(Unit.MOLE, mol), precision, unit == Unit.MOLE);
                    setText(millimole, fromMole(Unit.MILLIMOLE, mol), precision, unit == Unit.MILLIMOLE);
                    setText(kilomole, fromMole(Unit.KILOMOLE, mol), precision, unit == Unit.KILOMOLE);
                    setText(megamole, fromMole(Unit.MEGAMOLE, mol), precision, unit == Unit.MEGAMOLE);
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

    private double toMole(Unit unit, double value) {
        switch (unit) {
            case MOLE:
                return value;
            case MILLIMOLE:
                return value / 1_000.0;
            case KILOMOLE:
                return value * 1_000.0;
            case MEGAMOLE:
                return value * 1_000_000.0;
        }
        return value;
    }

    private double fromMole(Unit unit, double mole) {
        switch (unit) {
            case MOLE:
                return mole;
            case MILLIMOLE:
                return mole * 1_000.0;
            case KILOMOLE:
                return mole / 1_000.0;
            case MEGAMOLE:
                return mole / 1_000_000.0;
        }
        return mole;
    }
}
