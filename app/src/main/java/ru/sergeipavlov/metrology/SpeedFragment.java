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

public class SpeedFragment extends Fragment {
    private EditText meterPerSecond;
    private EditText kilometerPerSecond;
    private EditText kilometerPerHour;
    private EditText meterPerMinute;
    private EditText milePerSecond;
    private EditText milePerHour;
    private EditText footPerSecond;
    private EditText footPerMinute;
    private EditText knot;
    private EditText nauticalMilePerHour;
    private boolean isUpdating;

    private enum Unit {
        METER_PER_SECOND,
        KILOMETER_PER_SECOND,
        KILOMETER_PER_HOUR,
        METER_PER_MINUTE,
        MILE_PER_SECOND,
        MILE_PER_HOUR,
        FOOT_PER_SECOND,
        FOOT_PER_MINUTE,
        KNOT,
        NAUTICAL_MILE_PER_HOUR
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speed, container, false);
        meterPerSecond = view.findViewById(R.id.edit_speed_meter_per_second);
        kilometerPerSecond = view.findViewById(R.id.edit_speed_kilometer_per_second);
        kilometerPerHour = view.findViewById(R.id.edit_speed_kilometer_per_hour);
        meterPerMinute = view.findViewById(R.id.edit_speed_meter_per_minute);
        milePerSecond = view.findViewById(R.id.edit_speed_mile_per_second);
        milePerHour = view.findViewById(R.id.edit_speed_mile_per_hour);
        footPerSecond = view.findViewById(R.id.edit_speed_foot_per_second);
        footPerMinute = view.findViewById(R.id.edit_speed_foot_per_minute);
        knot = view.findViewById(R.id.edit_speed_knot);
        nauticalMilePerHour = view.findViewById(R.id.edit_speed_nautical_mile_per_hour);

        view.findViewById(R.id.symbol_speed_meter_per_second).setOnClickListener(v -> showDescription(R.string.desc_meter_per_second));
        view.findViewById(R.id.symbol_speed_kilometer_per_second).setOnClickListener(v -> showDescription(R.string.desc_kilometer_per_second));
        view.findViewById(R.id.symbol_speed_kilometer_per_hour).setOnClickListener(v -> showDescription(R.string.desc_kilometer_per_hour));
        view.findViewById(R.id.symbol_speed_meter_per_minute).setOnClickListener(v -> showDescription(R.string.desc_meter_per_minute));
        view.findViewById(R.id.symbol_speed_mile_per_second).setOnClickListener(v -> showDescription(R.string.desc_mile_per_second));
        view.findViewById(R.id.symbol_speed_mile_per_hour).setOnClickListener(v -> showDescription(R.string.desc_mile_per_hour));
        view.findViewById(R.id.symbol_speed_foot_per_second).setOnClickListener(v -> showDescription(R.string.desc_foot_per_second));
        view.findViewById(R.id.symbol_speed_foot_per_minute).setOnClickListener(v -> showDescription(R.string.desc_foot_per_minute));
        view.findViewById(R.id.symbol_speed_knot).setOnClickListener(v -> showDescription(R.string.desc_knot));
        view.findViewById(R.id.symbol_speed_nautical_mile_per_hour).setOnClickListener(v -> showDescription(R.string.desc_nautical_mile_per_hour));

        addWatcher(meterPerSecond, Unit.METER_PER_SECOND);
        addWatcher(kilometerPerSecond, Unit.KILOMETER_PER_SECOND);
        addWatcher(kilometerPerHour, Unit.KILOMETER_PER_HOUR);
        addWatcher(meterPerMinute, Unit.METER_PER_MINUTE);
        addWatcher(milePerSecond, Unit.MILE_PER_SECOND);
        addWatcher(milePerHour, Unit.MILE_PER_HOUR);
        addWatcher(footPerSecond, Unit.FOOT_PER_SECOND);
        addWatcher(footPerMinute, Unit.FOOT_PER_MINUTE);
        addWatcher(knot, Unit.KNOT);
        addWatcher(nauticalMilePerHour, Unit.NAUTICAL_MILE_PER_HOUR);

        isUpdating = true;
        double metersPerSecond = 0.0;
        int precision = 3;
        setInitialValues(metersPerSecond, precision);
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
                    double metersPerSecond = toMetersPerSecond(unit, value);
                    updateAll(metersPerSecond, precision, unit);
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setInitialValues(double metersPerSecond, int precision) {
        meterPerSecond.setText(format(fromMetersPerSecond(Unit.METER_PER_SECOND, metersPerSecond), precision));
        kilometerPerSecond.setText(format(fromMetersPerSecond(Unit.KILOMETER_PER_SECOND, metersPerSecond), precision));
        kilometerPerHour.setText(format(fromMetersPerSecond(Unit.KILOMETER_PER_HOUR, metersPerSecond), precision));
        meterPerMinute.setText(format(fromMetersPerSecond(Unit.METER_PER_MINUTE, metersPerSecond), precision));
        milePerSecond.setText(format(fromMetersPerSecond(Unit.MILE_PER_SECOND, metersPerSecond), precision));
        milePerHour.setText(format(fromMetersPerSecond(Unit.MILE_PER_HOUR, metersPerSecond), precision));
        footPerSecond.setText(format(fromMetersPerSecond(Unit.FOOT_PER_SECOND, metersPerSecond), precision));
        footPerMinute.setText(format(fromMetersPerSecond(Unit.FOOT_PER_MINUTE, metersPerSecond), precision));
        knot.setText(format(fromMetersPerSecond(Unit.KNOT, metersPerSecond), precision));
        nauticalMilePerHour.setText(format(fromMetersPerSecond(Unit.NAUTICAL_MILE_PER_HOUR, metersPerSecond), precision));
    }

    private void updateAll(double metersPerSecond, int precision, Unit source) {
        setText(meterPerSecond, fromMetersPerSecond(Unit.METER_PER_SECOND, metersPerSecond), precision, source == Unit.METER_PER_SECOND);
        setText(kilometerPerSecond, fromMetersPerSecond(Unit.KILOMETER_PER_SECOND, metersPerSecond), precision, source == Unit.KILOMETER_PER_SECOND);
        setText(kilometerPerHour, fromMetersPerSecond(Unit.KILOMETER_PER_HOUR, metersPerSecond), precision, source == Unit.KILOMETER_PER_HOUR);
        setText(meterPerMinute, fromMetersPerSecond(Unit.METER_PER_MINUTE, metersPerSecond), precision, source == Unit.METER_PER_MINUTE);
        setText(milePerSecond, fromMetersPerSecond(Unit.MILE_PER_SECOND, metersPerSecond), precision, source == Unit.MILE_PER_SECOND);
        setText(milePerHour, fromMetersPerSecond(Unit.MILE_PER_HOUR, metersPerSecond), precision, source == Unit.MILE_PER_HOUR);
        setText(footPerSecond, fromMetersPerSecond(Unit.FOOT_PER_SECOND, metersPerSecond), precision, source == Unit.FOOT_PER_SECOND);
        setText(footPerMinute, fromMetersPerSecond(Unit.FOOT_PER_MINUTE, metersPerSecond), precision, source == Unit.FOOT_PER_MINUTE);
        setText(knot, fromMetersPerSecond(Unit.KNOT, metersPerSecond), precision, source == Unit.KNOT);
        setText(nauticalMilePerHour, fromMetersPerSecond(Unit.NAUTICAL_MILE_PER_HOUR, metersPerSecond), precision, source == Unit.NAUTICAL_MILE_PER_HOUR);
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

    private double toMetersPerSecond(Unit unit, double value) {
        switch (unit) {
            case METER_PER_SECOND:
                return value;
            case KILOMETER_PER_SECOND:
                return value * 1_000.0;
            case KILOMETER_PER_HOUR:
                return value * (1000.0 / 3600.0);
            case METER_PER_MINUTE:
                return value / 60.0;
            case MILE_PER_SECOND:
                return value * 1609.344;
            case MILE_PER_HOUR:
                return value * 0.44704;
            case FOOT_PER_SECOND:
                return value * 0.3048;
            case FOOT_PER_MINUTE:
                return value * 0.00508;
            case KNOT:
                return value * 0.514444444444;
            case NAUTICAL_MILE_PER_HOUR:
                return value * 0.514444444444;
        }
        return value;
    }

    private double fromMetersPerSecond(Unit unit, double metersPerSecond) {
        switch (unit) {
            case METER_PER_SECOND:
                return metersPerSecond;
            case KILOMETER_PER_SECOND:
                return metersPerSecond / 1_000.0;
            case KILOMETER_PER_HOUR:
                return metersPerSecond / (1000.0 / 3600.0);
            case METER_PER_MINUTE:
                return metersPerSecond * 60.0;
            case MILE_PER_SECOND:
                return metersPerSecond / 1609.344;
            case MILE_PER_HOUR:
                return metersPerSecond / 0.44704;
            case FOOT_PER_SECOND:
                return metersPerSecond / 0.3048;
            case FOOT_PER_MINUTE:
                return metersPerSecond / 0.00508;
            case KNOT:
                return metersPerSecond / 0.514444444444;
            case NAUTICAL_MILE_PER_HOUR:
                return metersPerSecond / 0.514444444444;
        }
        return metersPerSecond;
    }
}
