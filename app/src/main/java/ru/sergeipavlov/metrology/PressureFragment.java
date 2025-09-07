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

public class PressureFragment extends Fragment {
    private EditText pascal;
    private EditText kgfCm2;
    private EditText kgfM2;
    private EditText millibar;
    private EditText bar;
    private EditText kilopascal;
    private EditText megapascal;
    private EditText newtonM2;
    private EditText kilonewtonM2;
    private EditText meganewtonM2;
    private EditText newtonCm2;
    private EditText newtonMm2;
    private EditText atmosphere;
    private EditText technicalAtmosphere;
    private EditText mmH2o;
    private EditText cmH2o;
    private EditText footH2o;
    private EditText mmHg;
    private EditText cmHg;
    private EditText footHg;
    private EditText ksi;
    private EditText psi;
    private EditText psf;
    private EditText tsiUs;
    private EditText tsfUs;
    private EditText tsiUk;
    private EditText tsfUk;
    private boolean isUpdating;

    private enum Unit {
        PASCAL,
        KGF_CM2, KGF_M2, MILLIBAR, BAR, KILOPASCAL, MEGAPASCAL,
        NEWTON_M2, KILONEWTON_M2, MEGANEWTON_M2, NEWTON_CM2, NEWTON_MM2,
        ATMOSPHERE, TECHNICAL_ATMOSPHERE,
        MM_H2O, CM_H2O, FOOT_H2O,
        MM_HG, CM_HG, FOOT_HG,
        KSI, PSI, PSF,
        TSI_US, TSF_US,
        TSI_UK, TSF_UK
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pressure, container, false);
        pascal = view.findViewById(R.id.edit_pascal);
        kgfCm2 = view.findViewById(R.id.edit_kgf_cm2);
        kgfM2 = view.findViewById(R.id.edit_kgf_m2);
        millibar = view.findViewById(R.id.edit_millibar);
        bar = view.findViewById(R.id.edit_bar);
        kilopascal = view.findViewById(R.id.edit_kilopascal);
        megapascal = view.findViewById(R.id.edit_megapascal);
        newtonM2 = view.findViewById(R.id.edit_newton_m2);
        kilonewtonM2 = view.findViewById(R.id.edit_kilonewton_m2);
        meganewtonM2 = view.findViewById(R.id.edit_meganewton_m2);
        newtonCm2 = view.findViewById(R.id.edit_newton_cm2);
        newtonMm2 = view.findViewById(R.id.edit_newton_mm2);
        atmosphere = view.findViewById(R.id.edit_atmosphere);
        technicalAtmosphere = view.findViewById(R.id.edit_technical_atmosphere);
        mmH2o = view.findViewById(R.id.edit_mm_h2o);
        cmH2o = view.findViewById(R.id.edit_cm_h2o);
        footH2o = view.findViewById(R.id.edit_foot_h2o);
        mmHg = view.findViewById(R.id.edit_mm_hg);
        cmHg = view.findViewById(R.id.edit_cm_hg);
        footHg = view.findViewById(R.id.edit_foot_hg);
        ksi = view.findViewById(R.id.edit_ksi);
        psi = view.findViewById(R.id.edit_psi);
        psf = view.findViewById(R.id.edit_psf);
        tsiUs = view.findViewById(R.id.edit_tsi_us);
        tsfUs = view.findViewById(R.id.edit_tsf_us);
        tsiUk = view.findViewById(R.id.edit_tsi_uk);
        tsfUk = view.findViewById(R.id.edit_tsf_uk);

        view.findViewById(R.id.symbol_pascal).setOnClickListener(v -> showDescription(R.string.desc_pascal));
        view.findViewById(R.id.symbol_kgf_cm2).setOnClickListener(v -> showDescription(R.string.desc_kgf_cm2));
        view.findViewById(R.id.symbol_kgf_m2).setOnClickListener(v -> showDescription(R.string.desc_kgf_m2));
        view.findViewById(R.id.symbol_millibar).setOnClickListener(v -> showDescription(R.string.desc_millibar));
        view.findViewById(R.id.symbol_bar).setOnClickListener(v -> showDescription(R.string.desc_bar));
        view.findViewById(R.id.symbol_kilopascal).setOnClickListener(v -> showDescription(R.string.desc_kilopascal));
        view.findViewById(R.id.symbol_megapascal).setOnClickListener(v -> showDescription(R.string.desc_megapascal));
        view.findViewById(R.id.symbol_newton_m2).setOnClickListener(v -> showDescription(R.string.desc_newton_per_square_meter));
        view.findViewById(R.id.symbol_kilonewton_m2).setOnClickListener(v -> showDescription(R.string.desc_kilonewton_per_square_meter));
        view.findViewById(R.id.symbol_meganewton_m2).setOnClickListener(v -> showDescription(R.string.desc_meganewton_per_square_meter));
        view.findViewById(R.id.symbol_newton_cm2).setOnClickListener(v -> showDescription(R.string.desc_newton_per_square_centimeter));
        view.findViewById(R.id.symbol_newton_mm2).setOnClickListener(v -> showDescription(R.string.desc_newton_per_square_millimeter));
        view.findViewById(R.id.symbol_atmosphere).setOnClickListener(v -> showDescription(R.string.desc_atmosphere));
        view.findViewById(R.id.symbol_technical_atmosphere).setOnClickListener(v -> showDescription(R.string.desc_technical_atmosphere));
        view.findViewById(R.id.symbol_mm_h2o).setOnClickListener(v -> showDescription(R.string.desc_mm_h2o));
        view.findViewById(R.id.symbol_cm_h2o).setOnClickListener(v -> showDescription(R.string.desc_cm_h2o));
        view.findViewById(R.id.symbol_foot_h2o).setOnClickListener(v -> showDescription(R.string.desc_foot_h2o));
        view.findViewById(R.id.symbol_mm_hg).setOnClickListener(v -> showDescription(R.string.desc_mm_hg));
        view.findViewById(R.id.symbol_cm_hg).setOnClickListener(v -> showDescription(R.string.desc_cm_hg));
        view.findViewById(R.id.symbol_foot_hg).setOnClickListener(v -> showDescription(R.string.desc_foot_hg));
        view.findViewById(R.id.symbol_ksi).setOnClickListener(v -> showDescription(R.string.desc_ksi));
        view.findViewById(R.id.symbol_psi).setOnClickListener(v -> showDescription(R.string.desc_psi));
        view.findViewById(R.id.symbol_psf).setOnClickListener(v -> showDescription(R.string.desc_psf));
        view.findViewById(R.id.symbol_tsi_us).setOnClickListener(v -> showDescription(R.string.desc_tsi_us));
        view.findViewById(R.id.symbol_tsf_us).setOnClickListener(v -> showDescription(R.string.desc_tsf_us));
        view.findViewById(R.id.symbol_tsi_uk).setOnClickListener(v -> showDescription(R.string.desc_tsi_uk));
        view.findViewById(R.id.symbol_tsf_uk).setOnClickListener(v -> showDescription(R.string.desc_tsf_uk));

        addWatcher(pascal, Unit.PASCAL);
        addWatcher(kgfCm2, Unit.KGF_CM2);
        addWatcher(kgfM2, Unit.KGF_M2);
        addWatcher(millibar, Unit.MILLIBAR);
        addWatcher(bar, Unit.BAR);
        addWatcher(kilopascal, Unit.KILOPASCAL);
        addWatcher(megapascal, Unit.MEGAPASCAL);
        addWatcher(newtonM2, Unit.NEWTON_M2);
        addWatcher(kilonewtonM2, Unit.KILONEWTON_M2);
        addWatcher(meganewtonM2, Unit.MEGANEWTON_M2);
        addWatcher(newtonCm2, Unit.NEWTON_CM2);
        addWatcher(newtonMm2, Unit.NEWTON_MM2);
        addWatcher(atmosphere, Unit.ATMOSPHERE);
        addWatcher(technicalAtmosphere, Unit.TECHNICAL_ATMOSPHERE);
        addWatcher(mmH2o, Unit.MM_H2O);
        addWatcher(cmH2o, Unit.CM_H2O);
        addWatcher(footH2o, Unit.FOOT_H2O);
        addWatcher(mmHg, Unit.MM_HG);
        addWatcher(cmHg, Unit.CM_HG);
        addWatcher(footHg, Unit.FOOT_HG);
        addWatcher(ksi, Unit.KSI);
        addWatcher(psi, Unit.PSI);
        addWatcher(psf, Unit.PSF);
        addWatcher(tsiUs, Unit.TSI_US);
        addWatcher(tsfUs, Unit.TSF_US);
        addWatcher(tsiUk, Unit.TSI_UK);
        addWatcher(tsfUk, Unit.TSF_UK);

        isUpdating = true;
        double pa = 0.0;
        int precision = 3;
        setAll(pa, precision);
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
                    double pa = toPascal(unit, value);
                    setAll(pa, precision, unit);
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setAll(double pa, int precision) {
        setAll(pa, precision, null);
    }

    private void setAll(double pa, int precision, Unit source) {
        setText(pascal, fromPascal(Unit.PASCAL, pa), precision, source == Unit.PASCAL);
        setText(kgfCm2, fromPascal(Unit.KGF_CM2, pa), precision, source == Unit.KGF_CM2);
        setText(kgfM2, fromPascal(Unit.KGF_M2, pa), precision, source == Unit.KGF_M2);
        setText(millibar, fromPascal(Unit.MILLIBAR, pa), precision, source == Unit.MILLIBAR);
        setText(bar, fromPascal(Unit.BAR, pa), precision, source == Unit.BAR);
        setText(kilopascal, fromPascal(Unit.KILOPASCAL, pa), precision, source == Unit.KILOPASCAL);
        setText(megapascal, fromPascal(Unit.MEGAPASCAL, pa), precision, source == Unit.MEGAPASCAL);
        setText(newtonM2, fromPascal(Unit.NEWTON_M2, pa), precision, source == Unit.NEWTON_M2);
        setText(kilonewtonM2, fromPascal(Unit.KILONEWTON_M2, pa), precision, source == Unit.KILONEWTON_M2);
        setText(meganewtonM2, fromPascal(Unit.MEGANEWTON_M2, pa), precision, source == Unit.MEGANEWTON_M2);
        setText(newtonCm2, fromPascal(Unit.NEWTON_CM2, pa), precision, source == Unit.NEWTON_CM2);
        setText(newtonMm2, fromPascal(Unit.NEWTON_MM2, pa), precision, source == Unit.NEWTON_MM2);
        setText(atmosphere, fromPascal(Unit.ATMOSPHERE, pa), precision, source == Unit.ATMOSPHERE);
        setText(technicalAtmosphere, fromPascal(Unit.TECHNICAL_ATMOSPHERE, pa), precision, source == Unit.TECHNICAL_ATMOSPHERE);
        setText(mmH2o, fromPascal(Unit.MM_H2O, pa), precision, source == Unit.MM_H2O);
        setText(cmH2o, fromPascal(Unit.CM_H2O, pa), precision, source == Unit.CM_H2O);
        setText(footH2o, fromPascal(Unit.FOOT_H2O, pa), precision, source == Unit.FOOT_H2O);
        setText(mmHg, fromPascal(Unit.MM_HG, pa), precision, source == Unit.MM_HG);
        setText(cmHg, fromPascal(Unit.CM_HG, pa), precision, source == Unit.CM_HG);
        setText(footHg, fromPascal(Unit.FOOT_HG, pa), precision, source == Unit.FOOT_HG);
        setText(ksi, fromPascal(Unit.KSI, pa), precision, source == Unit.KSI);
        setText(psi, fromPascal(Unit.PSI, pa), precision, source == Unit.PSI);
        setText(psf, fromPascal(Unit.PSF, pa), precision, source == Unit.PSF);
        setText(tsiUs, fromPascal(Unit.TSI_US, pa), precision, source == Unit.TSI_US);
        setText(tsfUs, fromPascal(Unit.TSF_US, pa), precision, source == Unit.TSF_US);
        setText(tsiUk, fromPascal(Unit.TSI_UK, pa), precision, source == Unit.TSI_UK);
        setText(tsfUk, fromPascal(Unit.TSF_UK, pa), precision, source == Unit.TSF_UK);
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

    private double toPascal(Unit unit, double value) {
        switch (unit) {
            case PASCAL:
                return value;
            case KGF_CM2:
                return value * 98_066.5;
            case KGF_M2:
                return value * 9.80665;
            case MILLIBAR:
                return value * 100.0;
            case BAR:
                return value * 100_000.0;
            case KILOPASCAL:
                return value * 1_000.0;
            case MEGAPASCAL:
                return value * 1_000_000.0;
            case NEWTON_M2:
                return value;
            case KILONEWTON_M2:
                return value * 1_000.0;
            case MEGANEWTON_M2:
                return value * 1_000_000.0;
            case NEWTON_CM2:
                return value * 10_000.0;
            case NEWTON_MM2:
                return value * 1_000_000.0;
            case ATMOSPHERE:
                return value * 101_325.0;
            case TECHNICAL_ATMOSPHERE:
                return value * 98_066.5;
            case MM_H2O:
                return value * 9.80665;
            case CM_H2O:
                return value * 98.0665;
            case FOOT_H2O:
                return value * 2_989.06692;
            case MM_HG:
                return value * 133.322368;
            case CM_HG:
                return value * 1_333.22368;
            case FOOT_HG:
                return value * 40_636.66;
            case KSI:
                return value * 6_894_757.293168;
            case PSI:
                return value * 6_894.757293168;
            case PSF:
                return value * 47.88025898;
            case TSI_US:
                return value * 13_789_514.586336;
            case TSF_US:
                return value * 95_760.51796;
            case TSI_UK:
                return value * 15_444_256.33632;
            case TSF_UK:
                return value * 107_251.7801152;
        }
        return value;
    }

    private double fromPascal(Unit unit, double pascal) {
        switch (unit) {
            case PASCAL:
                return pascal;
            case KGF_CM2:
                return pascal / 98_066.5;
            case KGF_M2:
                return pascal / 9.80665;
            case MILLIBAR:
                return pascal / 100.0;
            case BAR:
                return pascal / 100_000.0;
            case KILOPASCAL:
                return pascal / 1_000.0;
            case MEGAPASCAL:
                return pascal / 1_000_000.0;
            case NEWTON_M2:
                return pascal;
            case KILONEWTON_M2:
                return pascal / 1_000.0;
            case MEGANEWTON_M2:
                return pascal / 1_000_000.0;
            case NEWTON_CM2:
                return pascal / 10_000.0;
            case NEWTON_MM2:
                return pascal / 1_000_000.0;
            case ATMOSPHERE:
                return pascal / 101_325.0;
            case TECHNICAL_ATMOSPHERE:
                return pascal / 98_066.5;
            case MM_H2O:
                return pascal / 9.80665;
            case CM_H2O:
                return pascal / 98.0665;
            case FOOT_H2O:
                return pascal / 2_989.06692;
            case MM_HG:
                return pascal / 133.322368;
            case CM_HG:
                return pascal / 1_333.22368;
            case FOOT_HG:
                return pascal / 40_636.66;
            case KSI:
                return pascal / 6_894_757.293168;
            case PSI:
                return pascal / 6_894.757293168;
            case PSF:
                return pascal / 47.88025898;
            case TSI_US:
                return pascal / 13_789_514.586336;
            case TSF_US:
                return pascal / 95_760.51796;
            case TSI_UK:
                return pascal / 15_444_256.33632;
            case TSF_UK:
                return pascal / 107_251.7801152;
        }
        return pascal;
    }
}
