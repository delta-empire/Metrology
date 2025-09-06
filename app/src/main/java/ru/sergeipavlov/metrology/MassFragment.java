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

public class MassFragment extends Fragment {
    private EditText[] edits;
    private boolean isUpdating;

    private enum Unit {
        KILOGRAM, GRAM, MILLIGRAM, CENTNER, TONNE, CARAT, NEWTON,
        STONE, POUND, OUNCE, DRAM, GRAIN,
        SHORT_TON, SHORT_CENTNER,
        LONG_TON, LONG_CENTNER,
        TROY_POUND, TROY_OUNCE, PENNYWEIGHT, MITE, DOYT
    }

    private final Unit[] units = Unit.values();

    private final int[] editIds = {
            R.id.edit_kilogram,
            R.id.edit_gram,
            R.id.edit_milligram,
            R.id.edit_centner,
            R.id.edit_tonne,
            R.id.edit_carat,
            R.id.edit_newton,
            R.id.edit_stone,
            R.id.edit_pound,
            R.id.edit_ounce,
            R.id.edit_dram,
            R.id.edit_grain,
            R.id.edit_short_ton,
            R.id.edit_short_centner,
            R.id.edit_long_ton,
            R.id.edit_long_centner,
            R.id.edit_troy_pound,
            R.id.edit_troy_ounce,
            R.id.edit_pennyweight,
            R.id.edit_mite,
            R.id.edit_doyt
    };

    private final int[] symbolIds = {
            R.id.symbol_kilogram,
            R.id.symbol_gram,
            R.id.symbol_milligram,
            R.id.symbol_centner,
            R.id.symbol_tonne,
            R.id.symbol_carat,
            R.id.symbol_newton,
            R.id.symbol_stone,
            R.id.symbol_pound,
            R.id.symbol_ounce,
            R.id.symbol_dram,
            R.id.symbol_grain,
            R.id.symbol_short_ton,
            R.id.symbol_short_centner,
            R.id.symbol_long_ton,
            R.id.symbol_long_centner,
            R.id.symbol_troy_pound,
            R.id.symbol_troy_ounce,
            R.id.symbol_pennyweight,
            R.id.symbol_mite,
            R.id.symbol_doyt
    };

    private final int[] descIds = {
            R.string.desc_kilogram,
            R.string.desc_gram,
            R.string.desc_milligram,
            R.string.desc_centner,
            R.string.desc_tonne,
            R.string.desc_carat,
            R.string.desc_newton,
            R.string.desc_stone,
            R.string.desc_pound,
            R.string.desc_ounce,
            R.string.desc_dram,
            R.string.desc_grain,
            R.string.desc_short_ton,
            R.string.desc_short_centner,
            R.string.desc_long_ton,
            R.string.desc_long_centner,
            R.string.desc_troy_pound,
            R.string.desc_troy_ounce,
            R.string.desc_pennyweight,
            R.string.desc_mite,
            R.string.desc_doyt
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mass, container, false);
        edits = new EditText[units.length];
        for (int i = 0; i < units.length; i++) {
            edits[i] = view.findViewById(editIds[i]);
            final int desc = descIds[i];
            view.findViewById(symbolIds[i]).setOnClickListener(v -> showDescription(desc));
            final Unit unit = units[i];
            addWatcher(edits[i], unit);
        }
        isUpdating = true;
        setAll(0.0, 3);
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
                    double kg = toKilogram(unit, value);
                    if (kg < 0.0) {
                        Toast.makeText(requireContext(), R.string.warn_negative_mass, Toast.LENGTH_SHORT).show();
                        kg = 0.0;
                    }
                    for (int i = 0; i < units.length; i++) {
                        setText(edits[i], fromKilogram(units[i], kg), precision, unit == units[i]);
                    }
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setAll(double kilograms, int precision) {
        for (int i = 0; i < units.length; i++) {
            setText(edits[i], fromKilogram(units[i], kilograms), precision, false);
        }
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

    private double toKilogram(Unit unit, double value) {
        switch (unit) {
            case KILOGRAM:
                return value;
            case GRAM:
                return value / 1000.0;
            case MILLIGRAM:
                return value / 1_000_000.0;
            case CENTNER:
                return value * 100.0;
            case TONNE:
                return value * 1000.0;
            case CARAT:
                return value / 5_000.0;
            case NEWTON:
                return value / 9.80665;
            case STONE:
                return value * 6.35029318;
            case POUND:
                return value * 0.45359237;
            case OUNCE:
                return value * 0.028349523125;
            case DRAM:
                return value * 0.0017718451953125;
            case GRAIN:
                return value * 0.00006479891;
            case SHORT_TON:
                return value * 907.18474;
            case SHORT_CENTNER:
                return value * 45.359237;
            case LONG_TON:
                return value * 1_016.0469088;
            case LONG_CENTNER:
                return value * 50.80234544;
            case TROY_POUND:
                return value * 0.3732417216;
            case TROY_OUNCE:
                return value * 0.0311034768;
            case PENNYWEIGHT:
                return value * 0.00155517384;
            case MITE:
                return value * 0.0000032399455;
            case DOYT:
                return value * 0.000000134997729;
        }
        return value;
    }

    private double fromKilogram(Unit unit, double kg) {
        switch (unit) {
            case KILOGRAM:
                return kg;
            case GRAM:
                return kg * 1000.0;
            case MILLIGRAM:
                return kg * 1_000_000.0;
            case CENTNER:
                return kg / 100.0;
            case TONNE:
                return kg / 1000.0;
            case CARAT:
                return kg * 5_000.0;
            case NEWTON:
                return kg * 9.80665;
            case STONE:
                return kg / 6.35029318;
            case POUND:
                return kg / 0.45359237;
            case OUNCE:
                return kg / 0.028349523125;
            case DRAM:
                return kg / 0.0017718451953125;
            case GRAIN:
                return kg / 0.00006479891;
            case SHORT_TON:
                return kg / 907.18474;
            case SHORT_CENTNER:
                return kg / 45.359237;
            case LONG_TON:
                return kg / 1_016.0469088;
            case LONG_CENTNER:
                return kg / 50.80234544;
            case TROY_POUND:
                return kg / 0.3732417216;
            case TROY_OUNCE:
                return kg / 0.0311034768;
            case PENNYWEIGHT:
                return kg / 0.00155517384;
            case MITE:
                return kg / 0.0000032399455;
            case DOYT:
                return kg / 0.000000134997729;
        }
        return kg;
    }
}
