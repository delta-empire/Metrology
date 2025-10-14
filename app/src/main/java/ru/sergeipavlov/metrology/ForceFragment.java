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

public class ForceFragment extends Fragment {
    private EditText newton;
    private EditText meganewton;
    private EditText kilonewton;
    private EditText millinewton;
    private EditText micronewton;
    private EditText tonneForce;
    private EditText kilopond;
    private EditText kilogramForce;
    private EditText gramForce;
    private EditText rc;
    private EditText mgf;
    private EditText mgs;
    private EditText dyne;
    private EditText tonForceUs;
    private EditText poundForce;
    private EditText ounceForce;
    private EditText poundal;
    private boolean isUpdating;

    private enum Unit {
        NEWTON,
        MEGANEWTON,
        KILONEWTON,
        MILLINEWTON,
        MICRONEWTON,
        TONNE_FORCE,
        KILOPOND,
        KILOGRAM_FORCE,
        GRAM_FORCE,
        RC,
        MGF,
        MGS,
        DYNE,
        TON_FORCE_US,
        POUND_FORCE,
        OUNCE_FORCE,
        POUNDAL
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_force, container, false);
        newton = view.findViewById(R.id.edit_force_newton);
        meganewton = view.findViewById(R.id.edit_force_meganewton);
        kilonewton = view.findViewById(R.id.edit_force_kilonewton);
        millinewton = view.findViewById(R.id.edit_force_millinewton);
        micronewton = view.findViewById(R.id.edit_force_micronewton);
        tonneForce = view.findViewById(R.id.edit_force_tonne);
        kilopond = view.findViewById(R.id.edit_force_kilopond);
        kilogramForce = view.findViewById(R.id.edit_force_kilogram_force);
        gramForce = view.findViewById(R.id.edit_force_gram_force);
        rc = view.findViewById(R.id.edit_force_rc);
        mgf = view.findViewById(R.id.edit_force_mgf);
        mgs = view.findViewById(R.id.edit_force_mgs);
        dyne = view.findViewById(R.id.edit_force_dyne);
        tonForceUs = view.findViewById(R.id.edit_force_tf);
        poundForce = view.findViewById(R.id.edit_force_lbf);
        ounceForce = view.findViewById(R.id.edit_force_ozf);
        poundal = view.findViewById(R.id.edit_force_pdl);

        view.findViewById(R.id.symbol_force_newton).setOnClickListener(v -> showDescription(R.string.desc_newton_unit));
        view.findViewById(R.id.symbol_force_meganewton).setOnClickListener(v -> showDescription(R.string.desc_meganewton));
        view.findViewById(R.id.symbol_force_kilonewton).setOnClickListener(v -> showDescription(R.string.desc_kilonewton));
        view.findViewById(R.id.symbol_force_millinewton).setOnClickListener(v -> showDescription(R.string.desc_millinewton));
        view.findViewById(R.id.symbol_force_micronewton).setOnClickListener(v -> showDescription(R.string.desc_micronewton));
        view.findViewById(R.id.symbol_force_tonne).setOnClickListener(v -> showDescription(R.string.desc_tonne_force));
        view.findViewById(R.id.symbol_force_kilopond).setOnClickListener(v -> showDescription(R.string.desc_kilopond));
        view.findViewById(R.id.symbol_force_kilogram_force).setOnClickListener(v -> showDescription(R.string.desc_kilogram_force));
        view.findViewById(R.id.symbol_force_gram_force).setOnClickListener(v -> showDescription(R.string.desc_gram_force));
        view.findViewById(R.id.symbol_force_rc).setOnClickListener(v -> showDescription(R.string.desc_centner_force));
        view.findViewById(R.id.symbol_force_mgf).setOnClickListener(v -> showDescription(R.string.desc_milligram_force));
        view.findViewById(R.id.symbol_force_mgs).setOnClickListener(v -> showDescription(R.string.desc_milligram_force));
        view.findViewById(R.id.symbol_force_dyne).setOnClickListener(v -> showDescription(R.string.desc_dyne));
        view.findViewById(R.id.symbol_force_tf).setOnClickListener(v -> showDescription(R.string.desc_ton_force_us));
        view.findViewById(R.id.symbol_force_lbf).setOnClickListener(v -> showDescription(R.string.desc_pound_force));
        view.findViewById(R.id.symbol_force_ozf).setOnClickListener(v -> showDescription(R.string.desc_ounce_force));
        view.findViewById(R.id.symbol_force_pdl).setOnClickListener(v -> showDescription(R.string.desc_poundal));

        addWatcher(newton, Unit.NEWTON);
        addWatcher(meganewton, Unit.MEGANEWTON);
        addWatcher(kilonewton, Unit.KILONEWTON);
        addWatcher(millinewton, Unit.MILLINEWTON);
        addWatcher(micronewton, Unit.MICRONEWTON);
        addWatcher(tonneForce, Unit.TONNE_FORCE);
        addWatcher(kilopond, Unit.KILOPOND);
        addWatcher(kilogramForce, Unit.KILOGRAM_FORCE);
        addWatcher(gramForce, Unit.GRAM_FORCE);
        addWatcher(rc, Unit.RC);
        addWatcher(mgf, Unit.MGF);
        addWatcher(mgs, Unit.MGS);
        addWatcher(dyne, Unit.DYNE);
        addWatcher(tonForceUs, Unit.TON_FORCE_US);
        addWatcher(poundForce, Unit.POUND_FORCE);
        addWatcher(ounceForce, Unit.OUNCE_FORCE);
        addWatcher(poundal, Unit.POUNDAL);

        isUpdating = true;
        double n = 0.0;
        int precision = 3;
        setInitialValues(n, precision);
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
                    double newtonValue = toNewton(unit, value);
                    setText(newton, fromNewton(Unit.NEWTON, newtonValue), precision, unit == Unit.NEWTON);
                    setText(meganewton, fromNewton(Unit.MEGANEWTON, newtonValue), precision, unit == Unit.MEGANEWTON);
                    setText(kilonewton, fromNewton(Unit.KILONEWTON, newtonValue), precision, unit == Unit.KILONEWTON);
                    setText(millinewton, fromNewton(Unit.MILLINEWTON, newtonValue), precision, unit == Unit.MILLINEWTON);
                    setText(micronewton, fromNewton(Unit.MICRONEWTON, newtonValue), precision, unit == Unit.MICRONEWTON);
                    setText(tonneForce, fromNewton(Unit.TONNE_FORCE, newtonValue), precision, unit == Unit.TONNE_FORCE);
                    setText(kilopond, fromNewton(Unit.KILOPOND, newtonValue), precision, unit == Unit.KILOPOND);
                    setText(kilogramForce, fromNewton(Unit.KILOGRAM_FORCE, newtonValue), precision, unit == Unit.KILOGRAM_FORCE);
                    setText(gramForce, fromNewton(Unit.GRAM_FORCE, newtonValue), precision, unit == Unit.GRAM_FORCE);
                    setText(rc, fromNewton(Unit.RC, newtonValue), precision, unit == Unit.RC);
                    setText(mgf, fromNewton(Unit.MGF, newtonValue), precision, unit == Unit.MGF);
                    setText(mgs, fromNewton(Unit.MGS, newtonValue), precision, unit == Unit.MGS);
                    setText(dyne, fromNewton(Unit.DYNE, newtonValue), precision, unit == Unit.DYNE);
                    setText(tonForceUs, fromNewton(Unit.TON_FORCE_US, newtonValue), precision, unit == Unit.TON_FORCE_US);
                    setText(poundForce, fromNewton(Unit.POUND_FORCE, newtonValue), precision, unit == Unit.POUND_FORCE);
                    setText(ounceForce, fromNewton(Unit.OUNCE_FORCE, newtonValue), precision, unit == Unit.OUNCE_FORCE);
                    setText(poundal, fromNewton(Unit.POUNDAL, newtonValue), precision, unit == Unit.POUNDAL);
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setInitialValues(double newtonValue, int precision) {
        newton.setText(format(fromNewton(Unit.NEWTON, newtonValue), precision));
        meganewton.setText(format(fromNewton(Unit.MEGANEWTON, newtonValue), precision));
        kilonewton.setText(format(fromNewton(Unit.KILONEWTON, newtonValue), precision));
        millinewton.setText(format(fromNewton(Unit.MILLINEWTON, newtonValue), precision));
        micronewton.setText(format(fromNewton(Unit.MICRONEWTON, newtonValue), precision));
        tonneForce.setText(format(fromNewton(Unit.TONNE_FORCE, newtonValue), precision));
        kilopond.setText(format(fromNewton(Unit.KILOPOND, newtonValue), precision));
        kilogramForce.setText(format(fromNewton(Unit.KILOGRAM_FORCE, newtonValue), precision));
        gramForce.setText(format(fromNewton(Unit.GRAM_FORCE, newtonValue), precision));
        rc.setText(format(fromNewton(Unit.RC, newtonValue), precision));
        mgf.setText(format(fromNewton(Unit.MGF, newtonValue), precision));
        mgs.setText(format(fromNewton(Unit.MGS, newtonValue), precision));
        dyne.setText(format(fromNewton(Unit.DYNE, newtonValue), precision));
        tonForceUs.setText(format(fromNewton(Unit.TON_FORCE_US, newtonValue), precision));
        poundForce.setText(format(fromNewton(Unit.POUND_FORCE, newtonValue), precision));
        ounceForce.setText(format(fromNewton(Unit.OUNCE_FORCE, newtonValue), precision));
        poundal.setText(format(fromNewton(Unit.POUNDAL, newtonValue), precision));
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

    private double toNewton(Unit unit, double value) {
        switch (unit) {
            case NEWTON:
                return value;
            case MEGANEWTON:
                return value * 1_000_000.0;
            case KILONEWTON:
                return value * 1_000.0;
            case MILLINEWTON:
                return value * 0.001;
            case MICRONEWTON:
                return value * 0.000001;
            case TONNE_FORCE:
                return value * 9_806.65;
            case KILOPOND:
            case KILOGRAM_FORCE:
                return value * 9.80665;
            case GRAM_FORCE:
                return value * 0.00980665;
            case RC:
                return value * 980.665;
            case MGF:
            case MGS:
                return value * 0.00000980665;
            case DYNE:
                return value * 0.00001;
            case TON_FORCE_US:
                return value * 8_896.443230521;
            case POUND_FORCE:
                return value * 4.4482216152605;
            case OUNCE_FORCE:
                return value * 0.278013851;
            case POUNDAL:
                return value * 0.13825502798973;
        }
        return value;
    }

    private double fromNewton(Unit unit, double newtonValue) {
        switch (unit) {
            case NEWTON:
                return newtonValue;
            case MEGANEWTON:
                return newtonValue / 1_000_000.0;
            case KILONEWTON:
                return newtonValue / 1_000.0;
            case MILLINEWTON:
                return newtonValue / 0.001;
            case MICRONEWTON:
                return newtonValue / 0.000001;
            case TONNE_FORCE:
                return newtonValue / 9_806.65;
            case KILOPOND:
            case KILOGRAM_FORCE:
                return newtonValue / 9.80665;
            case GRAM_FORCE:
                return newtonValue / 0.00980665;
            case RC:
                return newtonValue / 980.665;
            case MGF:
            case MGS:
                return newtonValue / 0.00000980665;
            case DYNE:
                return newtonValue / 0.00001;
            case TON_FORCE_US:
                return newtonValue / 8_896.443230521;
            case POUND_FORCE:
                return newtonValue / 4.4482216152605;
            case OUNCE_FORCE:
                return newtonValue / 0.278013851;
            case POUNDAL:
                return newtonValue / 0.13825502798973;
        }
        return newtonValue;
    }
}
