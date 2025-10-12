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

public class VolumeFragment extends Fragment {
    private EditText cubicMeter;
    private EditText cubicDecimeter;
    private EditText cubicCentimeter;
    private EditText cubicMillimeter;
    private EditText hectoliter;
    private EditText decaliter;
    private EditText liter;
    private EditText deciliter;
    private EditText centiliter;
    private EditText milliliter;
    private EditText microliter;
    private EditText cubicYard;
    private EditText cubicFoot;
    private EditText cubicInch;
    private EditText acreFoot;
    private EditText usFluidBarrel;
    private EditText usLiquidGallon;
    private EditText usLiquidQuart;
    private EditText usFluidPint;
    private EditText usFluidOunce;
    private EditText usFluidDram;
    private EditText usMinim;
    private EditText usOilBarrel;
    private EditText usDryBarrel;
    private EditText usBushel;
    private EditText usPeck;
    private EditText usDryGallon;
    private EditText usDryQuart;
    private EditText usDryPint;
    private EditText ukBarrel;
    private EditText ukBushel;
    private EditText ukPeck;
    private EditText ukGallon;
    private EditText ukQuart;
    private EditText ukPint;
    private EditText ukFluidOunce;
    private boolean isUpdating;

    private enum Unit {
        CUBIC_METER,
        CUBIC_DECIMETER,
        CUBIC_CENTIMETER,
        CUBIC_MILLIMETER,
        HECTOLITER,
        DECALITER,
        LITER,
        DECILITER,
        CENTILITER,
        MILLILITER,
        MICROLITER,
        CUBIC_YARD,
        CUBIC_FOOT,
        CUBIC_INCH,
        ACRE_FOOT,
        US_FLUID_BARREL,
        US_LIQUID_GALLON,
        US_LIQUID_QUART,
        US_FLUID_PINT,
        US_FLUID_OUNCE,
        US_FLUID_DRAM,
        US_MINIM,
        US_OIL_BARREL,
        US_DRY_BARREL,
        US_BUSHEL,
        US_PECK,
        US_DRY_GALLON,
        US_DRY_QUART,
        US_DRY_PINT,
        UK_BARREL,
        UK_BUSHEL,
        UK_PECK,
        UK_GALLON,
        UK_QUART,
        UK_PINT,
        UK_FLUID_OUNCE
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volume, container, false);
        cubicMeter = view.findViewById(R.id.edit_volume_cubic_meter);
        cubicDecimeter = view.findViewById(R.id.edit_volume_cubic_decimeter);
        cubicCentimeter = view.findViewById(R.id.edit_volume_cubic_centimeter);
        cubicMillimeter = view.findViewById(R.id.edit_volume_cubic_millimeter);
        hectoliter = view.findViewById(R.id.edit_volume_hectoliter);
        decaliter = view.findViewById(R.id.edit_volume_decaliter);
        liter = view.findViewById(R.id.edit_volume_liter);
        deciliter = view.findViewById(R.id.edit_volume_deciliter);
        centiliter = view.findViewById(R.id.edit_volume_centiliter);
        milliliter = view.findViewById(R.id.edit_volume_milliliter);
        microliter = view.findViewById(R.id.edit_volume_microliter);
        cubicYard = view.findViewById(R.id.edit_volume_cubic_yard);
        cubicFoot = view.findViewById(R.id.edit_volume_cubic_foot);
        cubicInch = view.findViewById(R.id.edit_volume_cubic_inch);
        acreFoot = view.findViewById(R.id.edit_volume_acre_foot);
        usFluidBarrel = view.findViewById(R.id.edit_volume_us_fluid_barrel);
        usLiquidGallon = view.findViewById(R.id.edit_volume_us_liquid_gallon);
        usLiquidQuart = view.findViewById(R.id.edit_volume_us_liquid_quart);
        usFluidPint = view.findViewById(R.id.edit_volume_us_fluid_pint);
        usFluidOunce = view.findViewById(R.id.edit_volume_us_fluid_ounce);
        usFluidDram = view.findViewById(R.id.edit_volume_us_fluid_dram);
        usMinim = view.findViewById(R.id.edit_volume_us_minim);
        usOilBarrel = view.findViewById(R.id.edit_volume_us_oil_barrel);
        usDryBarrel = view.findViewById(R.id.edit_volume_us_dry_barrel);
        usBushel = view.findViewById(R.id.edit_volume_us_bushel);
        usPeck = view.findViewById(R.id.edit_volume_us_peck);
        usDryGallon = view.findViewById(R.id.edit_volume_us_dry_gallon);
        usDryQuart = view.findViewById(R.id.edit_volume_us_dry_quart);
        usDryPint = view.findViewById(R.id.edit_volume_us_dry_pint);
        ukBarrel = view.findViewById(R.id.edit_volume_uk_barrel);
        ukBushel = view.findViewById(R.id.edit_volume_uk_bushel);
        ukPeck = view.findViewById(R.id.edit_volume_uk_peck);
        ukGallon = view.findViewById(R.id.edit_volume_uk_gallon);
        ukQuart = view.findViewById(R.id.edit_volume_uk_quart);
        ukPint = view.findViewById(R.id.edit_volume_uk_pint);
        ukFluidOunce = view.findViewById(R.id.edit_volume_uk_fluid_ounce);

        view.findViewById(R.id.symbol_volume_cubic_meter).setOnClickListener(v -> showDescription(R.string.desc_cubic_meter));
        view.findViewById(R.id.symbol_volume_cubic_decimeter).setOnClickListener(v -> showDescription(R.string.desc_cubic_decimeter));
        view.findViewById(R.id.symbol_volume_cubic_centimeter).setOnClickListener(v -> showDescription(R.string.desc_cubic_centimeter));
        view.findViewById(R.id.symbol_volume_cubic_millimeter).setOnClickListener(v -> showDescription(R.string.desc_cubic_millimeter));
        view.findViewById(R.id.symbol_volume_hectoliter).setOnClickListener(v -> showDescription(R.string.desc_hectoliter));
        view.findViewById(R.id.symbol_volume_decaliter).setOnClickListener(v -> showDescription(R.string.desc_decaliter));
        view.findViewById(R.id.symbol_volume_liter).setOnClickListener(v -> showDescription(R.string.desc_liter));
        view.findViewById(R.id.symbol_volume_deciliter).setOnClickListener(v -> showDescription(R.string.desc_deciliter));
        view.findViewById(R.id.symbol_volume_centiliter).setOnClickListener(v -> showDescription(R.string.desc_centiliter));
        view.findViewById(R.id.symbol_volume_milliliter).setOnClickListener(v -> showDescription(R.string.desc_milliliter));
        view.findViewById(R.id.symbol_volume_microliter).setOnClickListener(v -> showDescription(R.string.desc_microliter));
        view.findViewById(R.id.symbol_volume_cubic_yard).setOnClickListener(v -> showDescription(R.string.desc_cubic_yard));
        view.findViewById(R.id.symbol_volume_cubic_foot).setOnClickListener(v -> showDescription(R.string.desc_cubic_foot));
        view.findViewById(R.id.symbol_volume_cubic_inch).setOnClickListener(v -> showDescription(R.string.desc_cubic_inch));
        view.findViewById(R.id.symbol_volume_acre_foot).setOnClickListener(v -> showDescription(R.string.desc_acre_foot));
        view.findViewById(R.id.symbol_volume_us_fluid_barrel).setOnClickListener(v -> showDescription(R.string.desc_us_liquid_barrel));
        view.findViewById(R.id.symbol_volume_us_liquid_gallon).setOnClickListener(v -> showDescription(R.string.desc_us_liquid_gallon));
        view.findViewById(R.id.symbol_volume_us_liquid_quart).setOnClickListener(v -> showDescription(R.string.desc_us_liquid_quart));
        view.findViewById(R.id.symbol_volume_us_fluid_pint).setOnClickListener(v -> showDescription(R.string.desc_us_liquid_pint));
        view.findViewById(R.id.symbol_volume_us_fluid_ounce).setOnClickListener(v -> showDescription(R.string.desc_us_fluid_ounce));
        view.findViewById(R.id.symbol_volume_us_fluid_dram).setOnClickListener(v -> showDescription(R.string.desc_us_fluid_dram));
        view.findViewById(R.id.symbol_volume_us_minim).setOnClickListener(v -> showDescription(R.string.desc_us_minim));
        view.findViewById(R.id.symbol_volume_us_oil_barrel).setOnClickListener(v -> showDescription(R.string.desc_us_oil_barrel));
        view.findViewById(R.id.symbol_volume_us_dry_barrel).setOnClickListener(v -> showDescription(R.string.desc_us_dry_barrel));
        view.findViewById(R.id.symbol_volume_us_bushel).setOnClickListener(v -> showDescription(R.string.desc_us_bushel));
        view.findViewById(R.id.symbol_volume_us_peck).setOnClickListener(v -> showDescription(R.string.desc_us_peck));
        view.findViewById(R.id.symbol_volume_us_dry_gallon).setOnClickListener(v -> showDescription(R.string.desc_us_dry_gallon));
        view.findViewById(R.id.symbol_volume_us_dry_quart).setOnClickListener(v -> showDescription(R.string.desc_us_dry_quart));
        view.findViewById(R.id.symbol_volume_us_dry_pint).setOnClickListener(v -> showDescription(R.string.desc_us_dry_pint));
        view.findViewById(R.id.symbol_volume_uk_barrel).setOnClickListener(v -> showDescription(R.string.desc_uk_barrel));
        view.findViewById(R.id.symbol_volume_uk_bushel).setOnClickListener(v -> showDescription(R.string.desc_uk_bushel));
        view.findViewById(R.id.symbol_volume_uk_peck).setOnClickListener(v -> showDescription(R.string.desc_uk_peck));
        view.findViewById(R.id.symbol_volume_uk_gallon).setOnClickListener(v -> showDescription(R.string.desc_uk_gallon));
        view.findViewById(R.id.symbol_volume_uk_quart).setOnClickListener(v -> showDescription(R.string.desc_uk_quart));
        view.findViewById(R.id.symbol_volume_uk_pint).setOnClickListener(v -> showDescription(R.string.desc_uk_pint));
        view.findViewById(R.id.symbol_volume_uk_fluid_ounce).setOnClickListener(v -> showDescription(R.string.desc_uk_fluid_ounce));

        addWatcher(cubicMeter, Unit.CUBIC_METER);
        addWatcher(cubicDecimeter, Unit.CUBIC_DECIMETER);
        addWatcher(cubicCentimeter, Unit.CUBIC_CENTIMETER);
        addWatcher(cubicMillimeter, Unit.CUBIC_MILLIMETER);
        addWatcher(hectoliter, Unit.HECTOLITER);
        addWatcher(decaliter, Unit.DECALITER);
        addWatcher(liter, Unit.LITER);
        addWatcher(deciliter, Unit.DECILITER);
        addWatcher(centiliter, Unit.CENTILITER);
        addWatcher(milliliter, Unit.MILLILITER);
        addWatcher(microliter, Unit.MICROLITER);
        addWatcher(cubicYard, Unit.CUBIC_YARD);
        addWatcher(cubicFoot, Unit.CUBIC_FOOT);
        addWatcher(cubicInch, Unit.CUBIC_INCH);
        addWatcher(acreFoot, Unit.ACRE_FOOT);
        addWatcher(usFluidBarrel, Unit.US_FLUID_BARREL);
        addWatcher(usLiquidGallon, Unit.US_LIQUID_GALLON);
        addWatcher(usLiquidQuart, Unit.US_LIQUID_QUART);
        addWatcher(usFluidPint, Unit.US_FLUID_PINT);
        addWatcher(usFluidOunce, Unit.US_FLUID_OUNCE);
        addWatcher(usFluidDram, Unit.US_FLUID_DRAM);
        addWatcher(usMinim, Unit.US_MINIM);
        addWatcher(usOilBarrel, Unit.US_OIL_BARREL);
        addWatcher(usDryBarrel, Unit.US_DRY_BARREL);
        addWatcher(usBushel, Unit.US_BUSHEL);
        addWatcher(usPeck, Unit.US_PECK);
        addWatcher(usDryGallon, Unit.US_DRY_GALLON);
        addWatcher(usDryQuart, Unit.US_DRY_QUART);
        addWatcher(usDryPint, Unit.US_DRY_PINT);
        addWatcher(ukBarrel, Unit.UK_BARREL);
        addWatcher(ukBushel, Unit.UK_BUSHEL);
        addWatcher(ukPeck, Unit.UK_PECK);
        addWatcher(ukGallon, Unit.UK_GALLON);
        addWatcher(ukQuart, Unit.UK_QUART);
        addWatcher(ukPint, Unit.UK_PINT);
        addWatcher(ukFluidOunce, Unit.UK_FLUID_OUNCE);

        isUpdating = true;
        double cubicMeters = 0.0;
        int precision = 3;
        setInitialValues(cubicMeters, precision);
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
                    double cubicMeters = toCubicMeters(unit, value);
                    updateAll(cubicMeters, precision, unit);
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setInitialValues(double cubicMeters, int precision) {
        cubicMeter.setText(format(fromCubicMeters(Unit.CUBIC_METER, cubicMeters), precision));
        cubicDecimeter.setText(format(fromCubicMeters(Unit.CUBIC_DECIMETER, cubicMeters), precision));
        cubicCentimeter.setText(format(fromCubicMeters(Unit.CUBIC_CENTIMETER, cubicMeters), precision));
        cubicMillimeter.setText(format(fromCubicMeters(Unit.CUBIC_MILLIMETER, cubicMeters), precision));
        hectoliter.setText(format(fromCubicMeters(Unit.HECTOLITER, cubicMeters), precision));
        decaliter.setText(format(fromCubicMeters(Unit.DECALITER, cubicMeters), precision));
        liter.setText(format(fromCubicMeters(Unit.LITER, cubicMeters), precision));
        deciliter.setText(format(fromCubicMeters(Unit.DECILITER, cubicMeters), precision));
        centiliter.setText(format(fromCubicMeters(Unit.CENTILITER, cubicMeters), precision));
        milliliter.setText(format(fromCubicMeters(Unit.MILLILITER, cubicMeters), precision));
        microliter.setText(format(fromCubicMeters(Unit.MICROLITER, cubicMeters), precision));
        cubicYard.setText(format(fromCubicMeters(Unit.CUBIC_YARD, cubicMeters), precision));
        cubicFoot.setText(format(fromCubicMeters(Unit.CUBIC_FOOT, cubicMeters), precision));
        cubicInch.setText(format(fromCubicMeters(Unit.CUBIC_INCH, cubicMeters), precision));
        acreFoot.setText(format(fromCubicMeters(Unit.ACRE_FOOT, cubicMeters), precision));
        usFluidBarrel.setText(format(fromCubicMeters(Unit.US_FLUID_BARREL, cubicMeters), precision));
        usLiquidGallon.setText(format(fromCubicMeters(Unit.US_LIQUID_GALLON, cubicMeters), precision));
        usLiquidQuart.setText(format(fromCubicMeters(Unit.US_LIQUID_QUART, cubicMeters), precision));
        usFluidPint.setText(format(fromCubicMeters(Unit.US_FLUID_PINT, cubicMeters), precision));
        usFluidOunce.setText(format(fromCubicMeters(Unit.US_FLUID_OUNCE, cubicMeters), precision));
        usFluidDram.setText(format(fromCubicMeters(Unit.US_FLUID_DRAM, cubicMeters), precision));
        usMinim.setText(format(fromCubicMeters(Unit.US_MINIM, cubicMeters), precision));
        usOilBarrel.setText(format(fromCubicMeters(Unit.US_OIL_BARREL, cubicMeters), precision));
        usDryBarrel.setText(format(fromCubicMeters(Unit.US_DRY_BARREL, cubicMeters), precision));
        usBushel.setText(format(fromCubicMeters(Unit.US_BUSHEL, cubicMeters), precision));
        usPeck.setText(format(fromCubicMeters(Unit.US_PECK, cubicMeters), precision));
        usDryGallon.setText(format(fromCubicMeters(Unit.US_DRY_GALLON, cubicMeters), precision));
        usDryQuart.setText(format(fromCubicMeters(Unit.US_DRY_QUART, cubicMeters), precision));
        usDryPint.setText(format(fromCubicMeters(Unit.US_DRY_PINT, cubicMeters), precision));
        ukBarrel.setText(format(fromCubicMeters(Unit.UK_BARREL, cubicMeters), precision));
        ukBushel.setText(format(fromCubicMeters(Unit.UK_BUSHEL, cubicMeters), precision));
        ukPeck.setText(format(fromCubicMeters(Unit.UK_PECK, cubicMeters), precision));
        ukGallon.setText(format(fromCubicMeters(Unit.UK_GALLON, cubicMeters), precision));
        ukQuart.setText(format(fromCubicMeters(Unit.UK_QUART, cubicMeters), precision));
        ukPint.setText(format(fromCubicMeters(Unit.UK_PINT, cubicMeters), precision));
        ukFluidOunce.setText(format(fromCubicMeters(Unit.UK_FLUID_OUNCE, cubicMeters), precision));
    }

    private void updateAll(double cubicMeters, int precision, Unit source) {
        setText(cubicMeter, fromCubicMeters(Unit.CUBIC_METER, cubicMeters), precision, source == Unit.CUBIC_METER);
        setText(cubicDecimeter, fromCubicMeters(Unit.CUBIC_DECIMETER, cubicMeters), precision, source == Unit.CUBIC_DECIMETER);
        setText(cubicCentimeter, fromCubicMeters(Unit.CUBIC_CENTIMETER, cubicMeters), precision, source == Unit.CUBIC_CENTIMETER);
        setText(cubicMillimeter, fromCubicMeters(Unit.CUBIC_MILLIMETER, cubicMeters), precision, source == Unit.CUBIC_MILLIMETER);
        setText(hectoliter, fromCubicMeters(Unit.HECTOLITER, cubicMeters), precision, source == Unit.HECTOLITER);
        setText(decaliter, fromCubicMeters(Unit.DECALITER, cubicMeters), precision, source == Unit.DECALITER);
        setText(liter, fromCubicMeters(Unit.LITER, cubicMeters), precision, source == Unit.LITER);
        setText(deciliter, fromCubicMeters(Unit.DECILITER, cubicMeters), precision, source == Unit.DECILITER);
        setText(centiliter, fromCubicMeters(Unit.CENTILITER, cubicMeters), precision, source == Unit.CENTILITER);
        setText(milliliter, fromCubicMeters(Unit.MILLILITER, cubicMeters), precision, source == Unit.MILLILITER);
        setText(microliter, fromCubicMeters(Unit.MICROLITER, cubicMeters), precision, source == Unit.MICROLITER);
        setText(cubicYard, fromCubicMeters(Unit.CUBIC_YARD, cubicMeters), precision, source == Unit.CUBIC_YARD);
        setText(cubicFoot, fromCubicMeters(Unit.CUBIC_FOOT, cubicMeters), precision, source == Unit.CUBIC_FOOT);
        setText(cubicInch, fromCubicMeters(Unit.CUBIC_INCH, cubicMeters), precision, source == Unit.CUBIC_INCH);
        setText(acreFoot, fromCubicMeters(Unit.ACRE_FOOT, cubicMeters), precision, source == Unit.ACRE_FOOT);
        setText(usFluidBarrel, fromCubicMeters(Unit.US_FLUID_BARREL, cubicMeters), precision, source == Unit.US_FLUID_BARREL);
        setText(usLiquidGallon, fromCubicMeters(Unit.US_LIQUID_GALLON, cubicMeters), precision, source == Unit.US_LIQUID_GALLON);
        setText(usLiquidQuart, fromCubicMeters(Unit.US_LIQUID_QUART, cubicMeters), precision, source == Unit.US_LIQUID_QUART);
        setText(usFluidPint, fromCubicMeters(Unit.US_FLUID_PINT, cubicMeters), precision, source == Unit.US_FLUID_PINT);
        setText(usFluidOunce, fromCubicMeters(Unit.US_FLUID_OUNCE, cubicMeters), precision, source == Unit.US_FLUID_OUNCE);
        setText(usFluidDram, fromCubicMeters(Unit.US_FLUID_DRAM, cubicMeters), precision, source == Unit.US_FLUID_DRAM);
        setText(usMinim, fromCubicMeters(Unit.US_MINIM, cubicMeters), precision, source == Unit.US_MINIM);
        setText(usOilBarrel, fromCubicMeters(Unit.US_OIL_BARREL, cubicMeters), precision, source == Unit.US_OIL_BARREL);
        setText(usDryBarrel, fromCubicMeters(Unit.US_DRY_BARREL, cubicMeters), precision, source == Unit.US_DRY_BARREL);
        setText(usBushel, fromCubicMeters(Unit.US_BUSHEL, cubicMeters), precision, source == Unit.US_BUSHEL);
        setText(usPeck, fromCubicMeters(Unit.US_PECK, cubicMeters), precision, source == Unit.US_PECK);
        setText(usDryGallon, fromCubicMeters(Unit.US_DRY_GALLON, cubicMeters), precision, source == Unit.US_DRY_GALLON);
        setText(usDryQuart, fromCubicMeters(Unit.US_DRY_QUART, cubicMeters), precision, source == Unit.US_DRY_QUART);
        setText(usDryPint, fromCubicMeters(Unit.US_DRY_PINT, cubicMeters), precision, source == Unit.US_DRY_PINT);
        setText(ukBarrel, fromCubicMeters(Unit.UK_BARREL, cubicMeters), precision, source == Unit.UK_BARREL);
        setText(ukBushel, fromCubicMeters(Unit.UK_BUSHEL, cubicMeters), precision, source == Unit.UK_BUSHEL);
        setText(ukPeck, fromCubicMeters(Unit.UK_PECK, cubicMeters), precision, source == Unit.UK_PECK);
        setText(ukGallon, fromCubicMeters(Unit.UK_GALLON, cubicMeters), precision, source == Unit.UK_GALLON);
        setText(ukQuart, fromCubicMeters(Unit.UK_QUART, cubicMeters), precision, source == Unit.UK_QUART);
        setText(ukPint, fromCubicMeters(Unit.UK_PINT, cubicMeters), precision, source == Unit.UK_PINT);
        setText(ukFluidOunce, fromCubicMeters(Unit.UK_FLUID_OUNCE, cubicMeters), precision, source == Unit.UK_FLUID_OUNCE);
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

    private double toCubicMeters(Unit unit, double value) {
        switch (unit) {
            case CUBIC_METER:
                return value;
            case CUBIC_DECIMETER:
                return value * 0.001;
            case CUBIC_CENTIMETER:
                return value * 0.000001;
            case CUBIC_MILLIMETER:
                return value * 0.000000001;
            case HECTOLITER:
                return value * 0.1;
            case DECALITER:
                return value * 0.01;
            case LITER:
                return value * 0.001;
            case DECILITER:
                return value * 0.0001;
            case CENTILITER:
                return value * 0.00001;
            case MILLILITER:
                return value * 0.000001;
            case MICROLITER:
                return value * 0.000000001;
            case CUBIC_YARD:
                return value * 0.764554857984;
            case CUBIC_FOOT:
                return value * 0.028316846592;
            case CUBIC_INCH:
                return value * 0.000016387064;
            case ACRE_FOOT:
                return value * 1233.48183754752;
            case US_FLUID_BARREL:
                return value * 0.119240471196;
            case US_LIQUID_GALLON:
                return value * 0.003785411784;
            case US_LIQUID_QUART:
                return value * 0.000946352946;
            case US_FLUID_PINT:
                return value * 0.000473176473;
            case US_FLUID_OUNCE:
                return value * 0.0000295735295625;
            case US_FLUID_DRAM:
                return value * 0.0000036966911953125;
            case US_MINIM:
                return value * 0.000000061611519921875;
            case US_OIL_BARREL:
                return value * 0.158987294928;
            case US_DRY_BARREL:
                return value * 0.115627124;
            case US_BUSHEL:
                return value * 0.0352390703538;
            case US_PECK:
                return value * 0.00880976758845;
            case US_DRY_GALLON:
                return value * 0.00440488377086;
            case US_DRY_QUART:
                return value * 0.001101220942715;
            case US_DRY_PINT:
                return value * 0.0005506104713575;
            case UK_BARREL:
                return value * 0.16365924;
            case UK_BUSHEL:
                return value * 0.03636872;
            case UK_PECK:
                return value * 0.00909218;
            case UK_GALLON:
                return value * 0.00454609;
            case UK_QUART:
                return value * 0.0011365225;
            case UK_PINT:
                return value * 0.00056826125;
            case UK_FLUID_OUNCE:
                return value * 0.0000284130625;
        }
        return value;
    }

    private double fromCubicMeters(Unit unit, double cubicMeters) {
        switch (unit) {
            case CUBIC_METER:
                return cubicMeters;
            case CUBIC_DECIMETER:
                return cubicMeters / 0.001;
            case CUBIC_CENTIMETER:
                return cubicMeters / 0.000001;
            case CUBIC_MILLIMETER:
                return cubicMeters / 0.000000001;
            case HECTOLITER:
                return cubicMeters / 0.1;
            case DECALITER:
                return cubicMeters / 0.01;
            case LITER:
                return cubicMeters / 0.001;
            case DECILITER:
                return cubicMeters / 0.0001;
            case CENTILITER:
                return cubicMeters / 0.00001;
            case MILLILITER:
                return cubicMeters / 0.000001;
            case MICROLITER:
                return cubicMeters / 0.000000001;
            case CUBIC_YARD:
                return cubicMeters / 0.764554857984;
            case CUBIC_FOOT:
                return cubicMeters / 0.028316846592;
            case CUBIC_INCH:
                return cubicMeters / 0.000016387064;
            case ACRE_FOOT:
                return cubicMeters / 1233.48183754752;
            case US_FLUID_BARREL:
                return cubicMeters / 0.119240471196;
            case US_LIQUID_GALLON:
                return cubicMeters / 0.003785411784;
            case US_LIQUID_QUART:
                return cubicMeters / 0.000946352946;
            case US_FLUID_PINT:
                return cubicMeters / 0.000473176473;
            case US_FLUID_OUNCE:
                return cubicMeters / 0.0000295735295625;
            case US_FLUID_DRAM:
                return cubicMeters / 0.0000036966911953125;
            case US_MINIM:
                return cubicMeters / 0.000000061611519921875;
            case US_OIL_BARREL:
                return cubicMeters / 0.158987294928;
            case US_DRY_BARREL:
                return cubicMeters / 0.115627124;
            case US_BUSHEL:
                return cubicMeters / 0.0352390703538;
            case US_PECK:
                return cubicMeters / 0.00880976758845;
            case US_DRY_GALLON:
                return cubicMeters / 0.00440488377086;
            case US_DRY_QUART:
                return cubicMeters / 0.001101220942715;
            case US_DRY_PINT:
                return cubicMeters / 0.0005506104713575;
            case UK_BARREL:
                return cubicMeters / 0.16365924;
            case UK_BUSHEL:
                return cubicMeters / 0.03636872;
            case UK_PECK:
                return cubicMeters / 0.00909218;
            case UK_GALLON:
                return cubicMeters / 0.00454609;
            case UK_QUART:
                return cubicMeters / 0.0011365225;
            case UK_PINT:
                return cubicMeters / 0.00056826125;
            case UK_FLUID_OUNCE:
                return cubicMeters / 0.0000284130625;
        }
        return cubicMeters;
    }
}
