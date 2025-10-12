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

public class EnergyFragment extends Fragment {
    private static final double JOULES_IN_MEGAJOULE = 1_000_000.0;
    private static final double JOULES_IN_KILOJOULE = 1_000.0;
    private static final double JOULES_IN_CALORIE = 4.1868;
    private static final double JOULES_IN_KILOCALORIE = JOULES_IN_CALORIE * 1_000.0;
    private static final double JOULES_IN_MEGACALORIE = JOULES_IN_KILOCALORIE * 1_000.0;
    private static final double JOULES_IN_KGF_METER = 9.80665;
    private static final double JOULES_IN_WATT_HOUR = 3_600.0;
    private static final double JOULES_IN_KILOWATT_HOUR = JOULES_IN_WATT_HOUR * 1_000.0;
    private static final double JOULES_IN_WATT_SECOND = 1.0;
    private static final double JOULES_IN_ERG = 1e-7;
    private static final double JOULES_IN_THERM_EU = 105_505_585.257348;
    private static final double JOULES_IN_THERM_US = 105_480_400.0;
    private static final double JOULES_IN_THERM_UK = 105_505_585.257348;
    private static final double JOULES_IN_BTU = 1_055.05585262;
    private static final double JOULES_IN_MMBTU = JOULES_IN_BTU * 1_000_000.0;
    private static final double JOULES_IN_FOOT_POUND = 1.3558179483314004;

    private EditText joule;
    private EditText megajoule;
    private EditText kilojoule;
    private EditText megacalorie;
    private EditText kilocalorie;
    private EditText calorie;
    private EditText kgfMeter;
    private EditText kilowattHour;
    private EditText wattHour;
    private EditText wattSecond;
    private EditText erg;
    private EditText thermEu;
    private EditText thermUs;
    private EditText thermUk;
    private EditText btu;
    private EditText mmbtu;
    private EditText footPound;
    private boolean isUpdating;

    private enum Unit {
        JOULE,
        MEGAJOULE,
        KILOJOULE,
        MEGACALORIE,
        KILOCALORIE,
        CALORIE,
        KGF_METER,
        KILOWATT_HOUR,
        WATT_HOUR,
        WATT_SECOND,
        ERG,
        THERM_EU,
        THERM_US,
        THERM_UK,
        BTU,
        MMBTU,
        FOOT_POUND
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_energy, container, false);
        joule = view.findViewById(R.id.edit_joule);
        megajoule = view.findViewById(R.id.edit_megajoule);
        kilojoule = view.findViewById(R.id.edit_kilojoule);
        megacalorie = view.findViewById(R.id.edit_megacalorie);
        kilocalorie = view.findViewById(R.id.edit_kilocalorie);
        calorie = view.findViewById(R.id.edit_calorie);
        kgfMeter = view.findViewById(R.id.edit_kgf_meter);
        kilowattHour = view.findViewById(R.id.edit_kilowatt_hour);
        wattHour = view.findViewById(R.id.edit_watt_hour);
        wattSecond = view.findViewById(R.id.edit_watt_second);
        erg = view.findViewById(R.id.edit_erg);
        thermEu = view.findViewById(R.id.edit_tm_eu);
        thermUs = view.findViewById(R.id.edit_tm_us);
        thermUk = view.findViewById(R.id.edit_tm_uk);
        btu = view.findViewById(R.id.edit_btu);
        mmbtu = view.findViewById(R.id.edit_mmbtu);
        footPound = view.findViewById(R.id.edit_ft_lbf);

        view.findViewById(R.id.symbol_joule).setOnClickListener(v -> showDescription(R.string.desc_joule));
        view.findViewById(R.id.symbol_megajoule).setOnClickListener(v -> showDescription(R.string.desc_megajoule));
        view.findViewById(R.id.symbol_kilojoule).setOnClickListener(v -> showDescription(R.string.desc_kilojoule));
        view.findViewById(R.id.symbol_megacalorie).setOnClickListener(v -> showDescription(R.string.desc_megacalorie));
        view.findViewById(R.id.symbol_kilocalorie).setOnClickListener(v -> showDescription(R.string.desc_kilocalorie));
        view.findViewById(R.id.symbol_calorie).setOnClickListener(v -> showDescription(R.string.desc_calorie));
        view.findViewById(R.id.symbol_kgf_meter).setOnClickListener(v -> showDescription(R.string.desc_kgf_meter));
        view.findViewById(R.id.symbol_kilowatt_hour).setOnClickListener(v -> showDescription(R.string.desc_kilowatt_hour));
        view.findViewById(R.id.symbol_watt_hour).setOnClickListener(v -> showDescription(R.string.desc_watt_hour));
        view.findViewById(R.id.symbol_watt_second).setOnClickListener(v -> showDescription(R.string.desc_watt_second));
        view.findViewById(R.id.symbol_erg).setOnClickListener(v -> showDescription(R.string.desc_erg));
        view.findViewById(R.id.symbol_tm_eu).setOnClickListener(v -> showDescription(R.string.desc_tm_eu));
        view.findViewById(R.id.symbol_tm_us).setOnClickListener(v -> showDescription(R.string.desc_tm_us));
        view.findViewById(R.id.symbol_tm_uk).setOnClickListener(v -> showDescription(R.string.desc_tm_uk));
        view.findViewById(R.id.symbol_btu).setOnClickListener(v -> showDescription(R.string.desc_btu));
        view.findViewById(R.id.symbol_mmbtu).setOnClickListener(v -> showDescription(R.string.desc_mmbtu));
        view.findViewById(R.id.symbol_ft_lbf).setOnClickListener(v -> showDescription(R.string.desc_ft_lbf));

        addWatcher(joule, Unit.JOULE);
        addWatcher(megajoule, Unit.MEGAJOULE);
        addWatcher(kilojoule, Unit.KILOJOULE);
        addWatcher(megacalorie, Unit.MEGACALORIE);
        addWatcher(kilocalorie, Unit.KILOCALORIE);
        addWatcher(calorie, Unit.CALORIE);
        addWatcher(kgfMeter, Unit.KGF_METER);
        addWatcher(kilowattHour, Unit.KILOWATT_HOUR);
        addWatcher(wattHour, Unit.WATT_HOUR);
        addWatcher(wattSecond, Unit.WATT_SECOND);
        addWatcher(erg, Unit.ERG);
        addWatcher(thermEu, Unit.THERM_EU);
        addWatcher(thermUs, Unit.THERM_US);
        addWatcher(thermUk, Unit.THERM_UK);
        addWatcher(btu, Unit.BTU);
        addWatcher(mmbtu, Unit.MMBTU);
        addWatcher(footPound, Unit.FOOT_POUND);

        isUpdating = true;
        double base = 1.0;
        int precision = 3;
        setAll(base, precision, Unit.JOULE);
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
                if (isUpdating) {
                    return;
                }
                String text = s.toString();
                if (text.isEmpty() || text.equals("-") || text.equals(".") || text.equals("-.") ) {
                    return;
                }
                try {
                    double value = Double.parseDouble(text);
                    int precision = Math.max(getPrecision(text), 3);
                    isUpdating = true;
                    double joules = toJoule(unit, value);
                    setAll(joules, precision, unit);
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setAll(double joules, int precision, Unit source) {
        setText(joule, fromJoule(Unit.JOULE, joules), precision, source == Unit.JOULE);
        setText(megajoule, fromJoule(Unit.MEGAJOULE, joules), precision, source == Unit.MEGAJOULE);
        setText(kilojoule, fromJoule(Unit.KILOJOULE, joules), precision, source == Unit.KILOJOULE);
        setText(megacalorie, fromJoule(Unit.MEGACALORIE, joules), precision, source == Unit.MEGACALORIE);
        setText(kilocalorie, fromJoule(Unit.KILOCALORIE, joules), precision, source == Unit.KILOCALORIE);
        setText(calorie, fromJoule(Unit.CALORIE, joules), precision, source == Unit.CALORIE);
        setText(kgfMeter, fromJoule(Unit.KGF_METER, joules), precision, source == Unit.KGF_METER);
        setText(kilowattHour, fromJoule(Unit.KILOWATT_HOUR, joules), precision, source == Unit.KILOWATT_HOUR);
        setText(wattHour, fromJoule(Unit.WATT_HOUR, joules), precision, source == Unit.WATT_HOUR);
        setText(wattSecond, fromJoule(Unit.WATT_SECOND, joules), precision, source == Unit.WATT_SECOND);
        setText(erg, fromJoule(Unit.ERG, joules), precision, source == Unit.ERG);
        setText(thermEu, fromJoule(Unit.THERM_EU, joules), precision, source == Unit.THERM_EU);
        setText(thermUs, fromJoule(Unit.THERM_US, joules), precision, source == Unit.THERM_US);
        setText(thermUk, fromJoule(Unit.THERM_UK, joules), precision, source == Unit.THERM_UK);
        setText(btu, fromJoule(Unit.BTU, joules), precision, source == Unit.BTU);
        setText(mmbtu, fromJoule(Unit.MMBTU, joules), precision, source == Unit.MMBTU);
        setText(footPound, fromJoule(Unit.FOOT_POUND, joules), precision, source == Unit.FOOT_POUND);
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

    private double toJoule(Unit unit, double value) {
        switch (unit) {
            case JOULE:
                return value;
            case MEGAJOULE:
                return value * JOULES_IN_MEGAJOULE;
            case KILOJOULE:
                return value * JOULES_IN_KILOJOULE;
            case MEGACALORIE:
                return value * JOULES_IN_MEGACALORIE;
            case KILOCALORIE:
                return value * JOULES_IN_KILOCALORIE;
            case CALORIE:
                return value * JOULES_IN_CALORIE;
            case KGF_METER:
                return value * JOULES_IN_KGF_METER;
            case KILOWATT_HOUR:
                return value * JOULES_IN_KILOWATT_HOUR;
            case WATT_HOUR:
                return value * JOULES_IN_WATT_HOUR;
            case WATT_SECOND:
                return value * JOULES_IN_WATT_SECOND;
            case ERG:
                return value * JOULES_IN_ERG;
            case THERM_EU:
                return value * JOULES_IN_THERM_EU;
            case THERM_US:
                return value * JOULES_IN_THERM_US;
            case THERM_UK:
                return value * JOULES_IN_THERM_UK;
            case BTU:
                return value * JOULES_IN_BTU;
            case MMBTU:
                return value * JOULES_IN_MMBTU;
            case FOOT_POUND:
                return value * JOULES_IN_FOOT_POUND;
        }
        return value;
    }

    private double fromJoule(Unit unit, double joules) {
        switch (unit) {
            case JOULE:
                return joules;
            case MEGAJOULE:
                return joules / JOULES_IN_MEGAJOULE;
            case KILOJOULE:
                return joules / JOULES_IN_KILOJOULE;
            case MEGACALORIE:
                return joules / JOULES_IN_MEGACALORIE;
            case KILOCALORIE:
                return joules / JOULES_IN_KILOCALORIE;
            case CALORIE:
                return joules / JOULES_IN_CALORIE;
            case KGF_METER:
                return joules / JOULES_IN_KGF_METER;
            case KILOWATT_HOUR:
                return joules / JOULES_IN_KILOWATT_HOUR;
            case WATT_HOUR:
                return joules / JOULES_IN_WATT_HOUR;
            case WATT_SECOND:
                return joules / JOULES_IN_WATT_SECOND;
            case ERG:
                return joules / JOULES_IN_ERG;
            case THERM_EU:
                return joules / JOULES_IN_THERM_EU;
            case THERM_US:
                return joules / JOULES_IN_THERM_US;
            case THERM_UK:
                return joules / JOULES_IN_THERM_UK;
            case BTU:
                return joules / JOULES_IN_BTU;
            case MMBTU:
                return joules / JOULES_IN_MMBTU;
            case FOOT_POUND:
                return joules / JOULES_IN_FOOT_POUND;
        }
        return joules;
    }
}
