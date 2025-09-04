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

public class LengthFragment extends Fragment {
    private EditText[] edits;
    private boolean isUpdating;

    private enum Unit {
        METER, KILOMETER, DECIMETER, CENTIMETER, MILLIMETER, MICROMETER, NANOMETER, ANGSTROM,
        LEAGUE, MILE, FURLONG, CHAIN, ROD, YARD, CUBIT, FOOT, SPAN, HAND, PALM, INCH, LINE, CALIBER, MIL,
        NAUTICAL_LEAGUE, NAUTICAL_MILE, CABLE, FATHOM, US_NAUTICAL_MILE, BRITISH_NAUTICAL_MILE
    }

    private final Unit[] units = Unit.values();

    private final int[] editIds = {
            R.id.edit_meter,
            R.id.edit_kilometer,
            R.id.edit_decimeter,
            R.id.edit_centimeter,
            R.id.edit_millimeter,
            R.id.edit_micrometer,
            R.id.edit_nanometer,
            R.id.edit_angstrom,
            R.id.edit_league,
            R.id.edit_mile,
            R.id.edit_furlong,
            R.id.edit_chain,
            R.id.edit_rod,
            R.id.edit_yard,
            R.id.edit_cubit,
            R.id.edit_foot,
            R.id.edit_span,
            R.id.edit_hand,
            R.id.edit_palm,
            R.id.edit_inch,
            R.id.edit_line,
            R.id.edit_caliber,
            R.id.edit_mil,
            R.id.edit_nautical_league,
            R.id.edit_nautical_mile,
            R.id.edit_cable,
            R.id.edit_fathom,
            R.id.edit_us_nautical_mile,
            R.id.edit_british_nautical_mile
    };

    private final int[] symbolIds = {
            R.id.symbol_meter,
            R.id.symbol_kilometer,
            R.id.symbol_decimeter,
            R.id.symbol_centimeter,
            R.id.symbol_millimeter,
            R.id.symbol_micrometer,
            R.id.symbol_nanometer,
            R.id.symbol_angstrom,
            R.id.symbol_league,
            R.id.symbol_mile,
            R.id.symbol_furlong,
            R.id.symbol_chain,
            R.id.symbol_rod,
            R.id.symbol_yard,
            R.id.symbol_cubit,
            R.id.symbol_foot,
            R.id.symbol_span,
            R.id.symbol_hand,
            R.id.symbol_palm,
            R.id.symbol_inch,
            R.id.symbol_line,
            R.id.symbol_caliber,
            R.id.symbol_mil,
            R.id.symbol_nautical_league,
            R.id.symbol_nautical_mile,
            R.id.symbol_cable,
            R.id.symbol_fathom,
            R.id.symbol_us_nautical_mile,
            R.id.symbol_british_nautical_mile
    };

    private final int[] descIds = {
            R.string.desc_meter,
            R.string.desc_kilometer,
            R.string.desc_decimeter,
            R.string.desc_centimeter,
            R.string.desc_millimeter,
            R.string.desc_micrometer,
            R.string.desc_nanometer,
            R.string.desc_angstrom,
            R.string.desc_league,
            R.string.desc_mile,
            R.string.desc_furlong,
            R.string.desc_chain,
            R.string.desc_rod,
            R.string.desc_yard,
            R.string.desc_cubit,
            R.string.desc_foot,
            R.string.desc_span,
            R.string.desc_hand,
            R.string.desc_palm,
            R.string.desc_inch,
            R.string.desc_line,
            R.string.desc_caliber,
            R.string.desc_mil,
            R.string.desc_nautical_league,
            R.string.desc_nautical_mile,
            R.string.desc_cable,
            R.string.desc_fathom,
            R.string.desc_us_nautical_mile,
            R.string.desc_british_nautical_mile
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_length, container, false);
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
                    double meters = toMeter(unit, value);
                    if (meters < 0.0) {
                        Toast.makeText(requireContext(), R.string.warn_negative_length, Toast.LENGTH_SHORT).show();
                        meters = 0.0;
                    }
                    for (int i = 0; i < units.length; i++) {
                        setText(edits[i], fromMeter(units[i], meters), precision, unit == units[i]);
                    }
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setAll(double meters, int precision) {
        for (int i = 0; i < units.length; i++) {
            setText(edits[i], fromMeter(units[i], meters), precision, false);
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

    private double toMeter(Unit unit, double value) {
        switch (unit) {
            case METER:
                return value;
            case KILOMETER:
                return value * 1_000.0;
            case DECIMETER:
                return value / 10.0;
            case CENTIMETER:
                return value / 100.0;
            case MILLIMETER:
                return value / 1_000.0;
            case MICROMETER:
                return value / 1_000_000.0;
            case NANOMETER:
                return value / 1_000_000_000.0;
            case ANGSTROM:
                return value / 10_000_000_000.0;
            case LEAGUE:
                return value * 4_828.032;
            case MILE:
                return value * 1_609.344;
            case FURLONG:
                return value * 201.168;
            case CHAIN:
                return value * 20.1168;
            case ROD:
                return value * 5.0292;
            case YARD:
                return value * 0.9144;
            case CUBIT:
                return value * 0.4572;
            case FOOT:
                return value * 0.3048;
            case SPAN:
                return value * 0.2286;
            case HAND:
                return value * 0.1016;
            case PALM:
                return value * 0.0762;
            case INCH:
                return value * 0.0254;
            case LINE:
                return value * 0.0021166666667;
            case CALIBER:
                return value * 0.000254;
            case MIL:
                return value * 0.0000254;
            case NAUTICAL_LEAGUE:
                return value * 5_556.0;
            case NAUTICAL_MILE:
                return value * 1_852.0;
            case CABLE:
                return value * 185.2;
            case FATHOM:
                return value * 1.8288;
            case US_NAUTICAL_MILE:
                return value * 1_853.248;
            case BRITISH_NAUTICAL_MILE:
                return value * 1_853.184;
        }
        return value;
    }

    private double fromMeter(Unit unit, double meters) {
        switch (unit) {
            case METER:
                return meters;
            case KILOMETER:
                return meters / 1_000.0;
            case DECIMETER:
                return meters * 10.0;
            case CENTIMETER:
                return meters * 100.0;
            case MILLIMETER:
                return meters * 1_000.0;
            case MICROMETER:
                return meters * 1_000_000.0;
            case NANOMETER:
                return meters * 1_000_000_000.0;
            case ANGSTROM:
                return meters * 10_000_000_000.0;
            case LEAGUE:
                return meters / 4_828.032;
            case MILE:
                return meters / 1_609.344;
            case FURLONG:
                return meters / 201.168;
            case CHAIN:
                return meters / 20.1168;
            case ROD:
                return meters / 5.0292;
            case YARD:
                return meters / 0.9144;
            case CUBIT:
                return meters / 0.4572;
            case FOOT:
                return meters / 0.3048;
            case SPAN:
                return meters / 0.2286;
            case HAND:
                return meters / 0.1016;
            case PALM:
                return meters / 0.0762;
            case INCH:
                return meters / 0.0254;
            case LINE:
                return meters / 0.0021166666667;
            case CALIBER:
                return meters / 0.000254;
            case MIL:
                return meters / 0.0000254;
            case NAUTICAL_LEAGUE:
                return meters / 5_556.0;
            case NAUTICAL_MILE:
                return meters / 1_852.0;
            case CABLE:
                return meters / 185.2;
            case FATHOM:
                return meters / 1.8288;
            case US_NAUTICAL_MILE:
                return meters / 1_853.248;
            case BRITISH_NAUTICAL_MILE:
                return meters / 1_853.184;
        }
        return meters;
    }
}
