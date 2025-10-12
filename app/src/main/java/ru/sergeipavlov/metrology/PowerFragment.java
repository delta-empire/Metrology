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

public class PowerFragment extends Fragment {
    private static final double CALORIE_TO_JOULE = 4.1868;
    private static final double WATTS_IN_GCAL_PER_SECOND = CALORIE_TO_JOULE * 1_000_000_000.0;
    private static final double WATTS_IN_KCAL_PER_SECOND = CALORIE_TO_JOULE * 1_000.0;
    private static final double WATTS_IN_CAL_PER_SECOND = CALORIE_TO_JOULE;
    private static final double WATTS_IN_GCAL_PER_HOUR = WATTS_IN_GCAL_PER_SECOND / 3600.0;
    private static final double WATTS_IN_KCAL_PER_HOUR = WATTS_IN_KCAL_PER_SECOND / 3600.0;
    private static final double WATTS_IN_CAL_PER_HOUR = WATTS_IN_CAL_PER_SECOND / 3600.0;
    private static final double WATTS_IN_HP_S = 9810.55407401514;
    private static final double WATTS_IN_HP_E = 746.0;
    private static final double WATTS_IN_HP_H = 745.9235815437058;
    private static final double WATTS_IN_HP_I = 745.6998715822702;
    private static final double WATTS_IN_HP_M = 735.49875;
    private static final double WATTS_IN_KGF_M_PER_SECOND = 9.80665;
    private static final double WATTS_IN_JOULE_PER_SECOND = 1.0;
    private static final double WATTS_IN_JOULE_PER_HOUR = 1.0 / 3600.0;
    private static final double WATTS_IN_ERG_PER_SECOND = 1e-7;
    private static final double WATTS_IN_RT = 3516.8528420667;
    private static final double WATTS_IN_USRT = 3516.8528420667;
    private static final double WATTS_IN_BTU_PER_SECOND = 1055.05585262;
    private static final double WATTS_IN_BTU_PER_MINUTE = WATTS_IN_BTU_PER_SECOND / 60.0;
    private static final double WATTS_IN_BTU_PER_HOUR = WATTS_IN_BTU_PER_SECOND / 3600.0;
    private static final double WATTS_IN_FT_LBF_PER_SECOND = 1.3558179483314004;

    private EditText watt;
    private EditText megawatt;
    private EditText kilowatt;
    private EditText voltAmpere;
    private EditText gcalPerSecond;
    private EditText kcalPerSecond;
    private EditText calPerSecond;
    private EditText gcalPerHour;
    private EditText kcalPerHour;
    private EditText calPerHour;
    private EditText hpS;
    private EditText hpE;
    private EditText hpH;
    private EditText hpI;
    private EditText hpM;
    private EditText kgfMeterPerSecond;
    private EditText joulePerSecond;
    private EditText joulePerHour;
    private EditText ergPerSecond;
    private EditText rt;
    private EditText usrt;
    private EditText btuPerSecond;
    private EditText btuPerMinute;
    private EditText btuPerHour;
    private EditText footPoundPerSecond;
    private boolean isUpdating;

    private enum Unit {
        WATT,
        MEGAWATT,
        KILOWATT,
        VOLT_AMPERE,
        GCAL_PER_SECOND,
        KCAL_PER_SECOND,
        CAL_PER_SECOND,
        GCAL_PER_HOUR,
        KCAL_PER_HOUR,
        CAL_PER_HOUR,
        HP_S,
        HP_E,
        HP_H,
        HP_I,
        HP_M,
        KGF_M_PER_SECOND,
        JOULE_PER_SECOND,
        JOULE_PER_HOUR,
        ERG_PER_SECOND,
        RT,
        USRT,
        BTU_PER_SECOND,
        BTU_PER_MINUTE,
        BTU_PER_HOUR,
        FT_LBF_PER_SECOND
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_power, container, false);
        watt = view.findViewById(R.id.edit_watt);
        megawatt = view.findViewById(R.id.edit_megawatt);
        kilowatt = view.findViewById(R.id.edit_kilowatt);
        voltAmpere = view.findViewById(R.id.edit_volt_ampere);
        gcalPerSecond = view.findViewById(R.id.edit_gcal_per_second);
        kcalPerSecond = view.findViewById(R.id.edit_kcal_per_second);
        calPerSecond = view.findViewById(R.id.edit_cal_per_second);
        gcalPerHour = view.findViewById(R.id.edit_gcal_per_hour);
        kcalPerHour = view.findViewById(R.id.edit_kcal_per_hour);
        calPerHour = view.findViewById(R.id.edit_cal_per_hour);
        hpS = view.findViewById(R.id.edit_hp_s);
        hpE = view.findViewById(R.id.edit_hp_e);
        hpH = view.findViewById(R.id.edit_hp_h);
        hpI = view.findViewById(R.id.edit_hp_i);
        hpM = view.findViewById(R.id.edit_hp_m);
        kgfMeterPerSecond = view.findViewById(R.id.edit_kgf_m_per_second);
        joulePerSecond = view.findViewById(R.id.edit_joule_per_second);
        joulePerHour = view.findViewById(R.id.edit_joule_per_hour);
        ergPerSecond = view.findViewById(R.id.edit_erg_per_second);
        rt = view.findViewById(R.id.edit_rt);
        usrt = view.findViewById(R.id.edit_usrt);
        btuPerSecond = view.findViewById(R.id.edit_btu_per_second);
        btuPerMinute = view.findViewById(R.id.edit_btu_per_minute);
        btuPerHour = view.findViewById(R.id.edit_btu_per_hour);
        footPoundPerSecond = view.findViewById(R.id.edit_ft_lbf_per_second);

        view.findViewById(R.id.symbol_watt).setOnClickListener(v -> showDescription(R.string.desc_watt));
        view.findViewById(R.id.symbol_megawatt).setOnClickListener(v -> showDescription(R.string.desc_megawatt));
        view.findViewById(R.id.symbol_kilowatt).setOnClickListener(v -> showDescription(R.string.desc_kilowatt));
        view.findViewById(R.id.symbol_volt_ampere).setOnClickListener(v -> showDescription(R.string.desc_volt_ampere));
        view.findViewById(R.id.symbol_gcal_per_second).setOnClickListener(v -> showDescription(R.string.desc_gcal_per_second));
        view.findViewById(R.id.symbol_kcal_per_second).setOnClickListener(v -> showDescription(R.string.desc_kcal_per_second));
        view.findViewById(R.id.symbol_cal_per_second).setOnClickListener(v -> showDescription(R.string.desc_cal_per_second));
        view.findViewById(R.id.symbol_gcal_per_hour).setOnClickListener(v -> showDescription(R.string.desc_gcal_per_hour));
        view.findViewById(R.id.symbol_kcal_per_hour).setOnClickListener(v -> showDescription(R.string.desc_kcal_per_hour));
        view.findViewById(R.id.symbol_cal_per_hour).setOnClickListener(v -> showDescription(R.string.desc_cal_per_hour));
        view.findViewById(R.id.symbol_hp_s).setOnClickListener(v -> showDescription(R.string.desc_hp_s));
        view.findViewById(R.id.symbol_hp_e).setOnClickListener(v -> showDescription(R.string.desc_hp_e));
        view.findViewById(R.id.symbol_hp_h).setOnClickListener(v -> showDescription(R.string.desc_hp_h));
        view.findViewById(R.id.symbol_hp_i).setOnClickListener(v -> showDescription(R.string.desc_hp_i));
        view.findViewById(R.id.symbol_hp_m).setOnClickListener(v -> showDescription(R.string.desc_hp_m));
        view.findViewById(R.id.symbol_kgf_m_per_second).setOnClickListener(v -> showDescription(R.string.desc_kgf_m_per_second));
        view.findViewById(R.id.symbol_joule_per_second).setOnClickListener(v -> showDescription(R.string.desc_joule_per_second));
        view.findViewById(R.id.symbol_joule_per_hour).setOnClickListener(v -> showDescription(R.string.desc_joule_per_hour));
        view.findViewById(R.id.symbol_erg_per_second).setOnClickListener(v -> showDescription(R.string.desc_erg_per_second));
        view.findViewById(R.id.symbol_rt).setOnClickListener(v -> showDescription(R.string.desc_rt));
        view.findViewById(R.id.symbol_usrt).setOnClickListener(v -> showDescription(R.string.desc_usrt));
        view.findViewById(R.id.symbol_btu_per_second).setOnClickListener(v -> showDescription(R.string.desc_btu_per_second));
        view.findViewById(R.id.symbol_btu_per_minute).setOnClickListener(v -> showDescription(R.string.desc_btu_per_minute));
        view.findViewById(R.id.symbol_btu_per_hour).setOnClickListener(v -> showDescription(R.string.desc_btu_per_hour));
        view.findViewById(R.id.symbol_ft_lbf_per_second).setOnClickListener(v -> showDescription(R.string.desc_ft_lbf_per_second));

        addWatcher(watt, Unit.WATT);
        addWatcher(megawatt, Unit.MEGAWATT);
        addWatcher(kilowatt, Unit.KILOWATT);
        addWatcher(voltAmpere, Unit.VOLT_AMPERE);
        addWatcher(gcalPerSecond, Unit.GCAL_PER_SECOND);
        addWatcher(kcalPerSecond, Unit.KCAL_PER_SECOND);
        addWatcher(calPerSecond, Unit.CAL_PER_SECOND);
        addWatcher(gcalPerHour, Unit.GCAL_PER_HOUR);
        addWatcher(kcalPerHour, Unit.KCAL_PER_HOUR);
        addWatcher(calPerHour, Unit.CAL_PER_HOUR);
        addWatcher(hpS, Unit.HP_S);
        addWatcher(hpE, Unit.HP_E);
        addWatcher(hpH, Unit.HP_H);
        addWatcher(hpI, Unit.HP_I);
        addWatcher(hpM, Unit.HP_M);
        addWatcher(kgfMeterPerSecond, Unit.KGF_M_PER_SECOND);
        addWatcher(joulePerSecond, Unit.JOULE_PER_SECOND);
        addWatcher(joulePerHour, Unit.JOULE_PER_HOUR);
        addWatcher(ergPerSecond, Unit.ERG_PER_SECOND);
        addWatcher(rt, Unit.RT);
        addWatcher(usrt, Unit.USRT);
        addWatcher(btuPerSecond, Unit.BTU_PER_SECOND);
        addWatcher(btuPerMinute, Unit.BTU_PER_MINUTE);
        addWatcher(btuPerHour, Unit.BTU_PER_HOUR);
        addWatcher(footPoundPerSecond, Unit.FT_LBF_PER_SECOND);

        isUpdating = true;
        double w = 1.0;
        int precision = 3;
        setAll(w, precision);
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
                    double wattValue = toWatt(unit, value);
                    setAll(wattValue, precision, unit);
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setAll(double wattValue, int precision) {
        setAll(wattValue, precision, null);
    }

    private void setAll(double wattValue, int precision, Unit source) {
        setText(watt, fromWatt(Unit.WATT, wattValue), precision, source == Unit.WATT);
        setText(megawatt, fromWatt(Unit.MEGAWATT, wattValue), precision, source == Unit.MEGAWATT);
        setText(kilowatt, fromWatt(Unit.KILOWATT, wattValue), precision, source == Unit.KILOWATT);
        setText(voltAmpere, fromWatt(Unit.VOLT_AMPERE, wattValue), precision, source == Unit.VOLT_AMPERE);
        setText(gcalPerSecond, fromWatt(Unit.GCAL_PER_SECOND, wattValue), precision, source == Unit.GCAL_PER_SECOND);
        setText(kcalPerSecond, fromWatt(Unit.KCAL_PER_SECOND, wattValue), precision, source == Unit.KCAL_PER_SECOND);
        setText(calPerSecond, fromWatt(Unit.CAL_PER_SECOND, wattValue), precision, source == Unit.CAL_PER_SECOND);
        setText(gcalPerHour, fromWatt(Unit.GCAL_PER_HOUR, wattValue), precision, source == Unit.GCAL_PER_HOUR);
        setText(kcalPerHour, fromWatt(Unit.KCAL_PER_HOUR, wattValue), precision, source == Unit.KCAL_PER_HOUR);
        setText(calPerHour, fromWatt(Unit.CAL_PER_HOUR, wattValue), precision, source == Unit.CAL_PER_HOUR);
        setText(hpS, fromWatt(Unit.HP_S, wattValue), precision, source == Unit.HP_S);
        setText(hpE, fromWatt(Unit.HP_E, wattValue), precision, source == Unit.HP_E);
        setText(hpH, fromWatt(Unit.HP_H, wattValue), precision, source == Unit.HP_H);
        setText(hpI, fromWatt(Unit.HP_I, wattValue), precision, source == Unit.HP_I);
        setText(hpM, fromWatt(Unit.HP_M, wattValue), precision, source == Unit.HP_M);
        setText(kgfMeterPerSecond, fromWatt(Unit.KGF_M_PER_SECOND, wattValue), precision, source == Unit.KGF_M_PER_SECOND);
        setText(joulePerSecond, fromWatt(Unit.JOULE_PER_SECOND, wattValue), precision, source == Unit.JOULE_PER_SECOND);
        setText(joulePerHour, fromWatt(Unit.JOULE_PER_HOUR, wattValue), precision, source == Unit.JOULE_PER_HOUR);
        setText(ergPerSecond, fromWatt(Unit.ERG_PER_SECOND, wattValue), precision, source == Unit.ERG_PER_SECOND);
        setText(rt, fromWatt(Unit.RT, wattValue), precision, source == Unit.RT);
        setText(usrt, fromWatt(Unit.USRT, wattValue), precision, source == Unit.USRT);
        setText(btuPerSecond, fromWatt(Unit.BTU_PER_SECOND, wattValue), precision, source == Unit.BTU_PER_SECOND);
        setText(btuPerMinute, fromWatt(Unit.BTU_PER_MINUTE, wattValue), precision, source == Unit.BTU_PER_MINUTE);
        setText(btuPerHour, fromWatt(Unit.BTU_PER_HOUR, wattValue), precision, source == Unit.BTU_PER_HOUR);
        setText(footPoundPerSecond, fromWatt(Unit.FT_LBF_PER_SECOND, wattValue), precision, source == Unit.FT_LBF_PER_SECOND);
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

    private double toWatt(Unit unit, double value) {
        switch (unit) {
            case WATT:
                return value;
            case MEGAWATT:
                return value * 1_000_000.0;
            case KILOWATT:
                return value * 1_000.0;
            case VOLT_AMPERE:
                return value;
            case GCAL_PER_SECOND:
                return value * WATTS_IN_GCAL_PER_SECOND;
            case KCAL_PER_SECOND:
                return value * WATTS_IN_KCAL_PER_SECOND;
            case CAL_PER_SECOND:
                return value * WATTS_IN_CAL_PER_SECOND;
            case GCAL_PER_HOUR:
                return value * WATTS_IN_GCAL_PER_HOUR;
            case KCAL_PER_HOUR:
                return value * WATTS_IN_KCAL_PER_HOUR;
            case CAL_PER_HOUR:
                return value * WATTS_IN_CAL_PER_HOUR;
            case HP_S:
                return value * WATTS_IN_HP_S;
            case HP_E:
                return value * WATTS_IN_HP_E;
            case HP_H:
                return value * WATTS_IN_HP_H;
            case HP_I:
                return value * WATTS_IN_HP_I;
            case HP_M:
                return value * WATTS_IN_HP_M;
            case KGF_M_PER_SECOND:
                return value * WATTS_IN_KGF_M_PER_SECOND;
            case JOULE_PER_SECOND:
                return value * WATTS_IN_JOULE_PER_SECOND;
            case JOULE_PER_HOUR:
                return value * WATTS_IN_JOULE_PER_HOUR;
            case ERG_PER_SECOND:
                return value * WATTS_IN_ERG_PER_SECOND;
            case RT:
                return value * WATTS_IN_RT;
            case USRT:
                return value * WATTS_IN_USRT;
            case BTU_PER_SECOND:
                return value * WATTS_IN_BTU_PER_SECOND;
            case BTU_PER_MINUTE:
                return value * WATTS_IN_BTU_PER_MINUTE;
            case BTU_PER_HOUR:
                return value * WATTS_IN_BTU_PER_HOUR;
            case FT_LBF_PER_SECOND:
                return value * WATTS_IN_FT_LBF_PER_SECOND;
        }
        return value;
    }

    private double fromWatt(Unit unit, double wattValue) {
        switch (unit) {
            case WATT:
                return wattValue;
            case MEGAWATT:
                return wattValue / 1_000_000.0;
            case KILOWATT:
                return wattValue / 1_000.0;
            case VOLT_AMPERE:
                return wattValue;
            case GCAL_PER_SECOND:
                return wattValue / WATTS_IN_GCAL_PER_SECOND;
            case KCAL_PER_SECOND:
                return wattValue / WATTS_IN_KCAL_PER_SECOND;
            case CAL_PER_SECOND:
                return wattValue / WATTS_IN_CAL_PER_SECOND;
            case GCAL_PER_HOUR:
                return wattValue / WATTS_IN_GCAL_PER_HOUR;
            case KCAL_PER_HOUR:
                return wattValue / WATTS_IN_KCAL_PER_HOUR;
            case CAL_PER_HOUR:
                return wattValue / WATTS_IN_CAL_PER_HOUR;
            case HP_S:
                return wattValue / WATTS_IN_HP_S;
            case HP_E:
                return wattValue / WATTS_IN_HP_E;
            case HP_H:
                return wattValue / WATTS_IN_HP_H;
            case HP_I:
                return wattValue / WATTS_IN_HP_I;
            case HP_M:
                return wattValue / WATTS_IN_HP_M;
            case KGF_M_PER_SECOND:
                return wattValue / WATTS_IN_KGF_M_PER_SECOND;
            case JOULE_PER_SECOND:
                return wattValue / WATTS_IN_JOULE_PER_SECOND;
            case JOULE_PER_HOUR:
                return wattValue / WATTS_IN_JOULE_PER_HOUR;
            case ERG_PER_SECOND:
                return wattValue / WATTS_IN_ERG_PER_SECOND;
            case RT:
                return wattValue / WATTS_IN_RT;
            case USRT:
                return wattValue / WATTS_IN_USRT;
            case BTU_PER_SECOND:
                return wattValue / WATTS_IN_BTU_PER_SECOND;
            case BTU_PER_MINUTE:
                return wattValue / WATTS_IN_BTU_PER_MINUTE;
            case BTU_PER_HOUR:
                return wattValue / WATTS_IN_BTU_PER_HOUR;
            case FT_LBF_PER_SECOND:
                return wattValue / WATTS_IN_FT_LBF_PER_SECOND;
        }
        return wattValue;
    }
}
