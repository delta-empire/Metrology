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
    private EditText kilogram;
    private EditText gram;
    private EditText milligram;
    private EditText centner;
    private EditText tonne;
    private EditText carat;
    private EditText newton;
    private EditText stone;
    private EditText pound;
    private EditText ounce;
    private EditText dram;
    private EditText grain;
    private EditText shortTon;
    private EditText shortHundredweight;
    private EditText longTon;
    private EditText longHundredweight;
    private EditText troyPound;
    private EditText troyOunce;
    private EditText pennyweight;
    private EditText mite;
    private EditText doit;
    private boolean isUpdating;

    private enum Unit {
        KILOGRAM, GRAM, MILLIGRAM, CENTNER, TONNE, CARAT, NEWTON,
        STONE, POUND, OUNCE, DRAM, GRAIN,
        SHORT_TON, SHORT_HUNDREDWEIGHT, LONG_TON, LONG_HUNDREDWEIGHT,
        TROY_POUND, TROY_OUNCE, PENNYWEIGHT, MITE, DOIT
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mass, container, false);
        kilogram = view.findViewById(R.id.edit_kilogram);
        gram = view.findViewById(R.id.edit_gram);
        milligram = view.findViewById(R.id.edit_milligram);
        centner = view.findViewById(R.id.edit_centner);
        tonne = view.findViewById(R.id.edit_tonne);
        carat = view.findViewById(R.id.edit_carat);
        newton = view.findViewById(R.id.edit_newton);
        stone = view.findViewById(R.id.edit_stone);
        pound = view.findViewById(R.id.edit_pound);
        ounce = view.findViewById(R.id.edit_ounce);
        dram = view.findViewById(R.id.edit_dram);
        grain = view.findViewById(R.id.edit_grain);
        shortTon = view.findViewById(R.id.edit_short_ton);
        shortHundredweight = view.findViewById(R.id.edit_short_hundredweight);
        longTon = view.findViewById(R.id.edit_long_ton);
        longHundredweight = view.findViewById(R.id.edit_long_hundredweight);
        troyPound = view.findViewById(R.id.edit_troy_pound);
        troyOunce = view.findViewById(R.id.edit_troy_ounce);
        pennyweight = view.findViewById(R.id.edit_pennyweight);
        mite = view.findViewById(R.id.edit_mite);
        doit = view.findViewById(R.id.edit_doit);

        view.findViewById(R.id.symbol_kilogram).setOnClickListener(v -> showDescription(R.string.desc_kilogram));
        view.findViewById(R.id.symbol_gram).setOnClickListener(v -> showDescription(R.string.desc_gram));
        view.findViewById(R.id.symbol_milligram).setOnClickListener(v -> showDescription(R.string.desc_milligram));
        view.findViewById(R.id.symbol_centner).setOnClickListener(v -> showDescription(R.string.desc_centner));
        view.findViewById(R.id.symbol_tonne).setOnClickListener(v -> showDescription(R.string.desc_tonne));
        view.findViewById(R.id.symbol_carat).setOnClickListener(v -> showDescription(R.string.desc_carat));
        view.findViewById(R.id.symbol_newton).setOnClickListener(v -> showDescription(R.string.desc_newton_force));
        view.findViewById(R.id.symbol_stone).setOnClickListener(v -> showDescription(R.string.desc_stone));
        view.findViewById(R.id.symbol_pound).setOnClickListener(v -> showDescription(R.string.desc_pound));
        view.findViewById(R.id.symbol_ounce).setOnClickListener(v -> showDescription(R.string.desc_ounce));
        view.findViewById(R.id.symbol_dram).setOnClickListener(v -> showDescription(R.string.desc_dram));
        view.findViewById(R.id.symbol_grain).setOnClickListener(v -> showDescription(R.string.desc_grain));
        view.findViewById(R.id.symbol_short_ton).setOnClickListener(v -> showDescription(R.string.desc_short_ton));
        view.findViewById(R.id.symbol_short_hundredweight).setOnClickListener(v -> showDescription(R.string.desc_short_hundredweight));
        view.findViewById(R.id.symbol_long_ton).setOnClickListener(v -> showDescription(R.string.desc_long_ton));
        view.findViewById(R.id.symbol_long_hundredweight).setOnClickListener(v -> showDescription(R.string.desc_long_hundredweight));
        view.findViewById(R.id.symbol_troy_pound).setOnClickListener(v -> showDescription(R.string.desc_troy_pound));
        view.findViewById(R.id.symbol_troy_ounce).setOnClickListener(v -> showDescription(R.string.desc_troy_ounce));
        view.findViewById(R.id.symbol_pennyweight).setOnClickListener(v -> showDescription(R.string.desc_pennyweight));
        view.findViewById(R.id.symbol_mite).setOnClickListener(v -> showDescription(R.string.desc_mite));
        view.findViewById(R.id.symbol_doit).setOnClickListener(v -> showDescription(R.string.desc_doit));

        addWatcher(kilogram, Unit.KILOGRAM);
        addWatcher(gram, Unit.GRAM);
        addWatcher(milligram, Unit.MILLIGRAM);
        addWatcher(centner, Unit.CENTNER);
        addWatcher(tonne, Unit.TONNE);
        addWatcher(carat, Unit.CARAT);
        addWatcher(newton, Unit.NEWTON);
        addWatcher(stone, Unit.STONE);
        addWatcher(pound, Unit.POUND);
        addWatcher(ounce, Unit.OUNCE);
        addWatcher(dram, Unit.DRAM);
        addWatcher(grain, Unit.GRAIN);
        addWatcher(shortTon, Unit.SHORT_TON);
        addWatcher(shortHundredweight, Unit.SHORT_HUNDREDWEIGHT);
        addWatcher(longTon, Unit.LONG_TON);
        addWatcher(longHundredweight, Unit.LONG_HUNDREDWEIGHT);
        addWatcher(troyPound, Unit.TROY_POUND);
        addWatcher(troyOunce, Unit.TROY_OUNCE);
        addWatcher(pennyweight, Unit.PENNYWEIGHT);
        addWatcher(mite, Unit.MITE);
        addWatcher(doit, Unit.DOIT);

        isUpdating = true;
        double kg = 0.0;
        int precision = 3;
        setAll(kg, precision);
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
                        setAll(kg, precision);
                    } else {
                        setText(kilogram, fromKilogram(Unit.KILOGRAM, kg), precision, unit == Unit.KILOGRAM);
                        setText(gram, fromKilogram(Unit.GRAM, kg), precision, unit == Unit.GRAM);
                        setText(milligram, fromKilogram(Unit.MILLIGRAM, kg), precision, unit == Unit.MILLIGRAM);
                        setText(centner, fromKilogram(Unit.CENTNER, kg), precision, unit == Unit.CENTNER);
                        setText(tonne, fromKilogram(Unit.TONNE, kg), precision, unit == Unit.TONNE);
                        setText(carat, fromKilogram(Unit.CARAT, kg), precision, unit == Unit.CARAT);
                        setText(newton, fromKilogram(Unit.NEWTON, kg), precision, unit == Unit.NEWTON);
                        setText(stone, fromKilogram(Unit.STONE, kg), precision, unit == Unit.STONE);
                        setText(pound, fromKilogram(Unit.POUND, kg), precision, unit == Unit.POUND);
                        setText(ounce, fromKilogram(Unit.OUNCE, kg), precision, unit == Unit.OUNCE);
                        setText(dram, fromKilogram(Unit.DRAM, kg), precision, unit == Unit.DRAM);
                        setText(grain, fromKilogram(Unit.GRAIN, kg), precision, unit == Unit.GRAIN);
                        setText(shortTon, fromKilogram(Unit.SHORT_TON, kg), precision, unit == Unit.SHORT_TON);
                        setText(shortHundredweight, fromKilogram(Unit.SHORT_HUNDREDWEIGHT, kg), precision, unit == Unit.SHORT_HUNDREDWEIGHT);
                        setText(longTon, fromKilogram(Unit.LONG_TON, kg), precision, unit == Unit.LONG_TON);
                        setText(longHundredweight, fromKilogram(Unit.LONG_HUNDREDWEIGHT, kg), precision, unit == Unit.LONG_HUNDREDWEIGHT);
                        setText(troyPound, fromKilogram(Unit.TROY_POUND, kg), precision, unit == Unit.TROY_POUND);
                        setText(troyOunce, fromKilogram(Unit.TROY_OUNCE, kg), precision, unit == Unit.TROY_OUNCE);
                        setText(pennyweight, fromKilogram(Unit.PENNYWEIGHT, kg), precision, unit == Unit.PENNYWEIGHT);
                        setText(mite, fromKilogram(Unit.MITE, kg), precision, unit == Unit.MITE);
                        setText(doit, fromKilogram(Unit.DOIT, kg), precision, unit == Unit.DOIT);
                    }
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setAll(double kg, int precision) {
        setText(kilogram, fromKilogram(Unit.KILOGRAM, kg), precision, false);
        setText(gram, fromKilogram(Unit.GRAM, kg), precision, false);
        setText(milligram, fromKilogram(Unit.MILLIGRAM, kg), precision, false);
        setText(centner, fromKilogram(Unit.CENTNER, kg), precision, false);
        setText(tonne, fromKilogram(Unit.TONNE, kg), precision, false);
        setText(carat, fromKilogram(Unit.CARAT, kg), precision, false);
        setText(newton, fromKilogram(Unit.NEWTON, kg), precision, false);
        setText(stone, fromKilogram(Unit.STONE, kg), precision, false);
        setText(pound, fromKilogram(Unit.POUND, kg), precision, false);
        setText(ounce, fromKilogram(Unit.OUNCE, kg), precision, false);
        setText(dram, fromKilogram(Unit.DRAM, kg), precision, false);
        setText(grain, fromKilogram(Unit.GRAIN, kg), precision, false);
        setText(shortTon, fromKilogram(Unit.SHORT_TON, kg), precision, false);
        setText(shortHundredweight, fromKilogram(Unit.SHORT_HUNDREDWEIGHT, kg), precision, false);
        setText(longTon, fromKilogram(Unit.LONG_TON, kg), precision, false);
        setText(longHundredweight, fromKilogram(Unit.LONG_HUNDREDWEIGHT, kg), precision, false);
        setText(troyPound, fromKilogram(Unit.TROY_POUND, kg), precision, false);
        setText(troyOunce, fromKilogram(Unit.TROY_OUNCE, kg), precision, false);
        setText(pennyweight, fromKilogram(Unit.PENNYWEIGHT, kg), precision, false);
        setText(mite, fromKilogram(Unit.MITE, kg), precision, false);
        setText(doit, fromKilogram(Unit.DOIT, kg), precision, false);
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
                return value / 1_000.0;
            case MILLIGRAM:
                return value / 1_000_000.0;
            case CENTNER:
                return value * 100.0;
            case TONNE:
                return value * 1_000.0;
            case CARAT:
                return value * 0.0002;
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
            case SHORT_HUNDREDWEIGHT:
                return value * 45.359237;
            case LONG_TON:
                return value * 1_016.0469088;
            case LONG_HUNDREDWEIGHT:
                return value * 50.80234544;
            case TROY_POUND:
                return value * 0.3732417216;
            case TROY_OUNCE:
                return value * 0.0311034768;
            case PENNYWEIGHT:
                return value * 0.00155517384;
            case MITE:
                return value * 0.00000269995458;
            case DOIT:
                return value * 0.000000112497724;
        }
        return value;
    }

    private double fromKilogram(Unit unit, double kilogram) {
        switch (unit) {
            case KILOGRAM:
                return kilogram;
            case GRAM:
                return kilogram * 1_000.0;
            case MILLIGRAM:
                return kilogram * 1_000_000.0;
            case CENTNER:
                return kilogram / 100.0;
            case TONNE:
                return kilogram / 1_000.0;
            case CARAT:
                return kilogram * 5_000.0;
            case NEWTON:
                return kilogram * 9.80665;
            case STONE:
                return kilogram / 6.35029318;
            case POUND:
                return kilogram / 0.45359237;
            case OUNCE:
                return kilogram / 0.028349523125;
            case DRAM:
                return kilogram / 0.0017718451953125;
            case GRAIN:
                return kilogram / 0.00006479891;
            case SHORT_TON:
                return kilogram / 907.18474;
            case SHORT_HUNDREDWEIGHT:
                return kilogram / 45.359237;
            case LONG_TON:
                return kilogram / 1_016.0469088;
            case LONG_HUNDREDWEIGHT:
                return kilogram / 50.80234544;
            case TROY_POUND:
                return kilogram / 0.3732417216;
            case TROY_OUNCE:
                return kilogram / 0.0311034768;
            case PENNYWEIGHT:
                return kilogram / 0.00155517384;
            case MITE:
                return kilogram / 0.00000269995458;
            case DOIT:
                return kilogram / 0.000000112497724;
        }
        return kilogram;
    }
}
