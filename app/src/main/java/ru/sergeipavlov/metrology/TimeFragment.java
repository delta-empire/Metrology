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

public class TimeFragment extends Fragment {
    private EditText second;
    private EditText microsecond;
    private EditText millisecond;
    private EditText minute;
    private EditText hour;
    private EditText day;
    private EditText week;
    private EditText year;
    private EditText century;
    private EditText millennium;
    private boolean isUpdating;

    private enum Unit {SECOND, MICROSECOND, MILLISECOND, MINUTE, HOUR, DAY, WEEK, YEAR, CENTURY, MILLENNIUM}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time, container, false);
        second = view.findViewById(R.id.edit_second);
        microsecond = view.findViewById(R.id.edit_microsecond);
        millisecond = view.findViewById(R.id.edit_millisecond);
        minute = view.findViewById(R.id.edit_minute);
        hour = view.findViewById(R.id.edit_hour);
        day = view.findViewById(R.id.edit_day);
        week = view.findViewById(R.id.edit_week);
        year = view.findViewById(R.id.edit_year);
        century = view.findViewById(R.id.edit_century);
        millennium = view.findViewById(R.id.edit_millennium);

        view.findViewById(R.id.symbol_second).setOnClickListener(v -> showDescription(R.string.desc_second));
        view.findViewById(R.id.symbol_microsecond).setOnClickListener(v -> showDescription(R.string.desc_microsecond));
        view.findViewById(R.id.symbol_millisecond).setOnClickListener(v -> showDescription(R.string.desc_millisecond));
        view.findViewById(R.id.symbol_minute).setOnClickListener(v -> showDescription(R.string.desc_minute));
        view.findViewById(R.id.symbol_hour).setOnClickListener(v -> showDescription(R.string.desc_hour));
        view.findViewById(R.id.symbol_day).setOnClickListener(v -> showDescription(R.string.desc_day));
        view.findViewById(R.id.symbol_week).setOnClickListener(v -> showDescription(R.string.desc_week));
        view.findViewById(R.id.symbol_year).setOnClickListener(v -> showDescription(R.string.desc_year));
        view.findViewById(R.id.symbol_century).setOnClickListener(v -> showDescription(R.string.desc_century));
        view.findViewById(R.id.symbol_millennium).setOnClickListener(v -> showDescription(R.string.desc_millennium));

        addWatcher(second, Unit.SECOND);
        addWatcher(microsecond, Unit.MICROSECOND);
        addWatcher(millisecond, Unit.MILLISECOND);
        addWatcher(minute, Unit.MINUTE);
        addWatcher(hour, Unit.HOUR);
        addWatcher(day, Unit.DAY);
        addWatcher(week, Unit.WEEK);
        addWatcher(year, Unit.YEAR);
        addWatcher(century, Unit.CENTURY);
        addWatcher(millennium, Unit.MILLENNIUM);

        isUpdating = true;
        double s = 0.0;
        int precision = 0;
        setText(second, fromSecond(Unit.SECOND, s), precision, false);
        setText(microsecond, fromSecond(Unit.MICROSECOND, s), precision, false);
        setText(millisecond, fromSecond(Unit.MILLISECOND, s), precision, false);
        setText(minute, fromSecond(Unit.MINUTE, s), precision, false);
        setText(hour, fromSecond(Unit.HOUR, s), precision, false);
        setText(day, fromSecond(Unit.DAY, s), precision, false);
        setText(week, fromSecond(Unit.WEEK, s), precision, false);
        setText(year, fromSecond(Unit.YEAR, s), precision, false);
        setText(century, fromSecond(Unit.CENTURY, s), precision, false);
        setText(millennium, fromSecond(Unit.MILLENNIUM, s), precision, false);
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
                    double seconds = toSecond(unit, value);
                    if (value < 0.0) {
                        Toast.makeText(requireContext(), R.string.warn_negative_time, Toast.LENGTH_SHORT).show();
                        seconds = 0.0;
                        setAll(seconds, precision);
                    } else {
                        setText(second, fromSecond(Unit.SECOND, seconds), precision, unit == Unit.SECOND);
                        setText(microsecond, fromSecond(Unit.MICROSECOND, seconds), precision, unit == Unit.MICROSECOND);
                        setText(millisecond, fromSecond(Unit.MILLISECOND, seconds), precision, unit == Unit.MILLISECOND);
                        setText(minute, fromSecond(Unit.MINUTE, seconds), precision, unit == Unit.MINUTE);
                        setText(hour, fromSecond(Unit.HOUR, seconds), precision, unit == Unit.HOUR);
                        setText(day, fromSecond(Unit.DAY, seconds), precision, unit == Unit.DAY);
                        setText(week, fromSecond(Unit.WEEK, seconds), precision, unit == Unit.WEEK);
                        setText(year, fromSecond(Unit.YEAR, seconds), precision, unit == Unit.YEAR);
                        setText(century, fromSecond(Unit.CENTURY, seconds), precision, unit == Unit.CENTURY);
                        setText(millennium, fromSecond(Unit.MILLENNIUM, seconds), precision, unit == Unit.MILLENNIUM);
                    }
                } catch (NumberFormatException ignore) {
                } finally {
                    isUpdating = false;
                }
            }
        });
    }

    private void setAll(double seconds, int precision) {
        setText(second, fromSecond(Unit.SECOND, seconds), precision, false);
        setText(microsecond, fromSecond(Unit.MICROSECOND, seconds), precision, false);
        setText(millisecond, fromSecond(Unit.MILLISECOND, seconds), precision, false);
        setText(minute, fromSecond(Unit.MINUTE, seconds), precision, false);
        setText(hour, fromSecond(Unit.HOUR, seconds), precision, false);
        setText(day, fromSecond(Unit.DAY, seconds), precision, false);
        setText(week, fromSecond(Unit.WEEK, seconds), precision, false);
        setText(year, fromSecond(Unit.YEAR, seconds), precision, false);
        setText(century, fromSecond(Unit.CENTURY, seconds), precision, false);
        setText(millennium, fromSecond(Unit.MILLENNIUM, seconds), precision, false);
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

    private double toSecond(Unit unit, double value) {
        switch (unit) {
            case SECOND:
                return value;
            case MICROSECOND:
                return value / 1_000_000.0;
            case MILLISECOND:
                return value / 1_000.0;
            case MINUTE:
                return value * 60.0;
            case HOUR:
                return value * 3_600.0;
            case DAY:
                return value * 86_400.0;
            case WEEK:
                return value * 604_800.0;
            case YEAR:
                return value * 31_536_000.0;
            case CENTURY:
                return value * 3_153_600_000.0;
            case MILLENNIUM:
                return value * 31_536_000_000.0;
        }
        return value;
    }

    private double fromSecond(Unit unit, double seconds) {
        switch (unit) {
            case SECOND:
                return seconds;
            case MICROSECOND:
                return seconds * 1_000_000.0;
            case MILLISECOND:
                return seconds * 1_000.0;
            case MINUTE:
                return seconds / 60.0;
            case HOUR:
                return seconds / 3_600.0;
            case DAY:
                return seconds / 86_400.0;
            case WEEK:
                return seconds / 604_800.0;
            case YEAR:
                return seconds / 31_536_000.0;
            case CENTURY:
                return seconds / 3_153_600_000.0;
            case MILLENNIUM:
                return seconds / 31_536_000_000.0;
        }
        return seconds;
    }
}
