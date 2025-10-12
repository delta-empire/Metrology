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

public class AreaFragment extends Fragment {
    private EditText squareMeter;
    private EditText squareKilometer;
    private EditText hectare;
    private EditText are;
    private EditText squareDecimeter;
    private EditText squareCentimeter;
    private EditText squareMillimeter;
    private EditText township;
    private EditText squareMile;
    private EditText homestead;
    private EditText acre;
    private EditText rood;
    private EditText squareRod;
    private EditText squareYard;
    private EditText squareFoot;
    private EditText squareInch;
    private boolean isUpdating;

    private enum Unit {
        SQUARE_METER,
        SQUARE_KILOMETER,
        HECTARE,
        ARE,
        SQUARE_DECIMETER,
        SQUARE_CENTIMETER,
        SQUARE_MILLIMETER,
        TOWNSHIP,
        SQUARE_MILE,
        HOMESTEAD,
        ACRE,
        ROOD,
        SQUARE_ROD,
        SQUARE_YARD,
        SQUARE_FOOT,
        SQUARE_INCH
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area, container, false);
        squareMeter = view.findViewById(R.id.edit_area_square_meter);
        squareKilometer = view.findViewById(R.id.edit_area_square_kilometer);
        hectare = view.findViewById(R.id.edit_area_hectare);
        are = view.findViewById(R.id.edit_area_are);
        squareDecimeter = view.findViewById(R.id.edit_area_square_decimeter);
        squareCentimeter = view.findViewById(R.id.edit_area_square_centimeter);
        squareMillimeter = view.findViewById(R.id.edit_area_square_millimeter);
        township = view.findViewById(R.id.edit_area_township);
        squareMile = view.findViewById(R.id.edit_area_square_mile);
        homestead = view.findViewById(R.id.edit_area_homestead);
        acre = view.findViewById(R.id.edit_area_acre);
        rood = view.findViewById(R.id.edit_area_rood);
        squareRod = view.findViewById(R.id.edit_area_square_rod);
        squareYard = view.findViewById(R.id.edit_area_square_yard);
        squareFoot = view.findViewById(R.id.edit_area_square_foot);
        squareInch = view.findViewById(R.id.edit_area_square_inch);

        view.findViewById(R.id.symbol_area_square_meter).setOnClickListener(v -> showDescription(R.string.desc_square_meter));
        view.findViewById(R.id.symbol_area_square_kilometer).setOnClickListener(v -> showDescription(R.string.desc_square_kilometer));
        view.findViewById(R.id.symbol_area_hectare).setOnClickListener(v -> showDescription(R.string.desc_hectare));
        view.findViewById(R.id.symbol_area_are).setOnClickListener(v -> showDescription(R.string.desc_are));
        view.findViewById(R.id.symbol_area_square_decimeter).setOnClickListener(v -> showDescription(R.string.desc_square_decimeter));
        view.findViewById(R.id.symbol_area_square_centimeter).setOnClickListener(v -> showDescription(R.string.desc_square_centimeter));
        view.findViewById(R.id.symbol_area_square_millimeter).setOnClickListener(v -> showDescription(R.string.desc_square_millimeter));
        view.findViewById(R.id.symbol_area_township).setOnClickListener(v -> showDescription(R.string.desc_township));
        view.findViewById(R.id.symbol_area_square_mile).setOnClickListener(v -> showDescription(R.string.desc_square_mile));
        view.findViewById(R.id.symbol_area_homestead).setOnClickListener(v -> showDescription(R.string.desc_homestead));
        view.findViewById(R.id.symbol_area_acre).setOnClickListener(v -> showDescription(R.string.desc_acre));
        view.findViewById(R.id.symbol_area_rood).setOnClickListener(v -> showDescription(R.string.desc_rood));
        view.findViewById(R.id.symbol_area_square_rod).setOnClickListener(v -> showDescription(R.string.desc_square_rod));
        view.findViewById(R.id.symbol_area_square_yard).setOnClickListener(v -> showDescription(R.string.desc_square_yard));
        view.findViewById(R.id.symbol_area_square_foot).setOnClickListener(v -> showDescription(R.string.desc_square_foot));
        view.findViewById(R.id.symbol_area_square_inch).setOnClickListener(v -> showDescription(R.string.desc_square_inch));

        addWatcher(squareMeter, Unit.SQUARE_METER);
        addWatcher(squareKilometer, Unit.SQUARE_KILOMETER);
        addWatcher(hectare, Unit.HECTARE);
        addWatcher(are, Unit.ARE);
        addWatcher(squareDecimeter, Unit.SQUARE_DECIMETER);
        addWatcher(squareCentimeter, Unit.SQUARE_CENTIMETER);
        addWatcher(squareMillimeter, Unit.SQUARE_MILLIMETER);
        addWatcher(township, Unit.TOWNSHIP);
        addWatcher(squareMile, Unit.SQUARE_MILE);
        addWatcher(homestead, Unit.HOMESTEAD);
        addWatcher(acre, Unit.ACRE);
        addWatcher(rood, Unit.ROOD);
        addWatcher(squareRod, Unit.SQUARE_ROD);
        addWatcher(squareYard, Unit.SQUARE_YARD);
        addWatcher(squareFoot, Unit.SQUARE_FOOT);
        addWatcher(squareInch, Unit.SQUARE_INCH);

        isUpdating = true;
        double squareMeters = 0.0;
        int precision = 3;
        setInitialValues(squareMeters, precision);
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
                    double squareMeters = toSquareMeters(unit, value);
                    updateAll(squareMeters, precision, unit);
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setInitialValues(double squareMeters, int precision) {
        squareMeter.setText(format(fromSquareMeters(Unit.SQUARE_METER, squareMeters), precision));
        squareKilometer.setText(format(fromSquareMeters(Unit.SQUARE_KILOMETER, squareMeters), precision));
        hectare.setText(format(fromSquareMeters(Unit.HECTARE, squareMeters), precision));
        are.setText(format(fromSquareMeters(Unit.ARE, squareMeters), precision));
        squareDecimeter.setText(format(fromSquareMeters(Unit.SQUARE_DECIMETER, squareMeters), precision));
        squareCentimeter.setText(format(fromSquareMeters(Unit.SQUARE_CENTIMETER, squareMeters), precision));
        squareMillimeter.setText(format(fromSquareMeters(Unit.SQUARE_MILLIMETER, squareMeters), precision));
        township.setText(format(fromSquareMeters(Unit.TOWNSHIP, squareMeters), precision));
        squareMile.setText(format(fromSquareMeters(Unit.SQUARE_MILE, squareMeters), precision));
        homestead.setText(format(fromSquareMeters(Unit.HOMESTEAD, squareMeters), precision));
        acre.setText(format(fromSquareMeters(Unit.ACRE, squareMeters), precision));
        rood.setText(format(fromSquareMeters(Unit.ROOD, squareMeters), precision));
        squareRod.setText(format(fromSquareMeters(Unit.SQUARE_ROD, squareMeters), precision));
        squareYard.setText(format(fromSquareMeters(Unit.SQUARE_YARD, squareMeters), precision));
        squareFoot.setText(format(fromSquareMeters(Unit.SQUARE_FOOT, squareMeters), precision));
        squareInch.setText(format(fromSquareMeters(Unit.SQUARE_INCH, squareMeters), precision));
    }

    private void updateAll(double squareMeters, int precision, Unit source) {
        setText(squareMeter, fromSquareMeters(Unit.SQUARE_METER, squareMeters), precision, source == Unit.SQUARE_METER);
        setText(squareKilometer, fromSquareMeters(Unit.SQUARE_KILOMETER, squareMeters), precision, source == Unit.SQUARE_KILOMETER);
        setText(hectare, fromSquareMeters(Unit.HECTARE, squareMeters), precision, source == Unit.HECTARE);
        setText(are, fromSquareMeters(Unit.ARE, squareMeters), precision, source == Unit.ARE);
        setText(squareDecimeter, fromSquareMeters(Unit.SQUARE_DECIMETER, squareMeters), precision, source == Unit.SQUARE_DECIMETER);
        setText(squareCentimeter, fromSquareMeters(Unit.SQUARE_CENTIMETER, squareMeters), precision, source == Unit.SQUARE_CENTIMETER);
        setText(squareMillimeter, fromSquareMeters(Unit.SQUARE_MILLIMETER, squareMeters), precision, source == Unit.SQUARE_MILLIMETER);
        setText(township, fromSquareMeters(Unit.TOWNSHIP, squareMeters), precision, source == Unit.TOWNSHIP);
        setText(squareMile, fromSquareMeters(Unit.SQUARE_MILE, squareMeters), precision, source == Unit.SQUARE_MILE);
        setText(homestead, fromSquareMeters(Unit.HOMESTEAD, squareMeters), precision, source == Unit.HOMESTEAD);
        setText(acre, fromSquareMeters(Unit.ACRE, squareMeters), precision, source == Unit.ACRE);
        setText(rood, fromSquareMeters(Unit.ROOD, squareMeters), precision, source == Unit.ROOD);
        setText(squareRod, fromSquareMeters(Unit.SQUARE_ROD, squareMeters), precision, source == Unit.SQUARE_ROD);
        setText(squareYard, fromSquareMeters(Unit.SQUARE_YARD, squareMeters), precision, source == Unit.SQUARE_YARD);
        setText(squareFoot, fromSquareMeters(Unit.SQUARE_FOOT, squareMeters), precision, source == Unit.SQUARE_FOOT);
        setText(squareInch, fromSquareMeters(Unit.SQUARE_INCH, squareMeters), precision, source == Unit.SQUARE_INCH);
    }

    private void setText(EditText edit, double value, int precision, boolean isSource) {
        if (isSource) {
            return;
        }
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

    private double toSquareMeters(Unit unit, double value) {
        switch (unit) {
            case SQUARE_METER:
                return value;
            case SQUARE_KILOMETER:
                return value * 1_000_000.0;
            case HECTARE:
                return value * 10_000.0;
            case ARE:
                return value * 100.0;
            case SQUARE_DECIMETER:
                return value * 0.01;
            case SQUARE_CENTIMETER:
                return value * 0.0001;
            case SQUARE_MILLIMETER:
                return value * 0.000001;
            case TOWNSHIP:
                return value * 93_239_571.972096;
            case SQUARE_MILE:
                return value * 2_589_988.110336;
            case HOMESTEAD:
                return value * 647_497.027584;
            case ACRE:
                return value * 4_046.8564224;
            case ROOD:
                return value * 1_011.7141056;
            case SQUARE_ROD:
                return value * 25.29285264;
            case SQUARE_YARD:
                return value * 0.83612736;
            case SQUARE_FOOT:
                return value * 0.09290304;
            case SQUARE_INCH:
                return value * 0.00064516;
        }
        return value;
    }

    private double fromSquareMeters(Unit unit, double squareMeters) {
        switch (unit) {
            case SQUARE_METER:
                return squareMeters;
            case SQUARE_KILOMETER:
                return squareMeters / 1_000_000.0;
            case HECTARE:
                return squareMeters / 10_000.0;
            case ARE:
                return squareMeters / 100.0;
            case SQUARE_DECIMETER:
                return squareMeters / 0.01;
            case SQUARE_CENTIMETER:
                return squareMeters / 0.0001;
            case SQUARE_MILLIMETER:
                return squareMeters / 0.000001;
            case TOWNSHIP:
                return squareMeters / 93_239_571.972096;
            case SQUARE_MILE:
                return squareMeters / 2_589_988.110336;
            case HOMESTEAD:
                return squareMeters / 647_497.027584;
            case ACRE:
                return squareMeters / 4_046.8564224;
            case ROOD:
                return squareMeters / 1_011.7141056;
            case SQUARE_ROD:
                return squareMeters / 25.29285264;
            case SQUARE_YARD:
                return squareMeters / 0.83612736;
            case SQUARE_FOOT:
                return squareMeters / 0.09290304;
            case SQUARE_INCH:
                return squareMeters / 0.00064516;
        }
        return squareMeters;
    }
}
