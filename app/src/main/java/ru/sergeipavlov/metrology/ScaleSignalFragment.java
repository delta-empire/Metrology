package ru.sergeipavlov.metrology;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ScaleSignalFragment extends Fragment {
    private EditText physicalStart;
    private EditText physicalEnd;
    private EditText physicalValue;
    private EditText signalStart;
    private EditText signalEnd;
    private EditText signalValue;
    private Spinner scaleTypeSpinner;
    private boolean isUpdating;

    private enum ScaleType {LINEAR, LINEAR_DESC, QUADRATIC, QUADRATIC_DESC, ROOT, ROOT_DESC}

    private ScaleType scaleType = ScaleType.LINEAR;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scale_signal, container, false);
        scaleTypeSpinner = view.findViewById(R.id.spinner_scale_type);
        physicalStart = view.findViewById(R.id.edit_physical_start);
        physicalEnd = view.findViewById(R.id.edit_physical_end);
        physicalValue = view.findViewById(R.id.edit_physical_value);
        signalStart = view.findViewById(R.id.edit_signal_start);
        signalEnd = view.findViewById(R.id.edit_signal_end);
        signalValue = view.findViewById(R.id.edit_signal_value);

        scaleTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                scaleType = ScaleType.values()[position];
                recalcFromSignal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        addWatcher(physicalValue, this::recalcFromPhysical);
        addWatcher(signalValue, this::recalcFromSignal);
        addWatcher(physicalStart, this::recalcFromSignal);
        addWatcher(physicalEnd, this::recalcFromSignal);
        addWatcher(signalStart, this::recalcFromSignal);
        addWatcher(signalEnd, this::recalcFromSignal);

        return view;
    }

    private void addWatcher(EditText edit, Runnable action) {
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (isUpdating) return;
                String text = s.toString();
                if (text.isEmpty() || text.equals("-") || text.equals(".") || text.equals("-.") ) return;
                action.run();
            }
        });
    }

    private double getValue(EditText edit) {
        return Double.parseDouble(edit.getText().toString());
    }

    private void recalcFromPhysical() {
        try {
            double scs = getValue(physicalStart);
            double sce = getValue(physicalEnd);
            double sgs = getValue(signalStart);
            double sge = getValue(signalEnd);
            double scv = getValue(physicalValue);
            double sgv = computeSignal(scs, sce, sgs, sge, scv);
            isUpdating = true;
            signalValue.setText(format(sgv));
        } catch (Exception ignore) {
        } finally {
            isUpdating = false;
        }
    }

    private void recalcFromSignal() {
        try {
            double scs = getValue(physicalStart);
            double sce = getValue(physicalEnd);
            double sgs = getValue(signalStart);
            double sge = getValue(signalEnd);
            double sgv = getValue(signalValue);
            double scv = computeScale(scs, sce, sgs, sge, sgv);
            isUpdating = true;
            physicalValue.setText(format(scv));
        } catch (Exception ignore) {
        } finally {
            isUpdating = false;
        }
    }

    private double computeSignal(double scs, double sce, double sgs, double sge, double scv) {
        double scRange = sce - scs;
        double ratio = (scv - scs) / scRange;
        switch (scaleType) {
            case LINEAR:
                return ratio * (sge - sgs) + sgs;
            case LINEAR_DESC:
                return ratio * (sgs - sge) + sge;
            case QUADRATIC:
                return Math.pow(ratio, 2) * (sge - sgs) + sgs;
            case QUADRATIC_DESC:
                return Math.pow(ratio, 2) * (sgs - sge) + sge;
            case ROOT:
                return Math.sqrt(ratio) * (sge - sgs) + sgs;
            case ROOT_DESC:
                return Math.sqrt(ratio) * (sgs - sge) + sge;
        }
        return 0.0;
    }

    private double computeScale(double scs, double sce, double sgs, double sge, double sgv) {
        double sgRange = sge - sgs;
        switch (scaleType) {
            case LINEAR:
                return ((sgv - sgs) / sgRange) * (sce - scs) + scs;
            case LINEAR_DESC:
                return ((sgv - sge) / (sgs - sge)) * (sce - scs) + scs;
            case QUADRATIC:
                return Math.sqrt((sgv - sgs) / sgRange) * (sce - scs) + scs;
            case QUADRATIC_DESC:
                return Math.sqrt((sgv - sge) / (sgs - sge)) * (sce - scs) + scs;
            case ROOT:
                return Math.pow((sgv - sgs) / sgRange, 2) * (sce - scs) + scs;
            case ROOT_DESC:
                return Math.pow((sgv - sge) / (sgs - sge), 2) * (sce - scs) + scs;
        }
        return 0.0;
    }

    private String format(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        return bd.stripTrailingZeros().toPlainString();
    }
}
