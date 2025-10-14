package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class PressureActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pressure;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.pressure_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new PressureFragment();
    }
}
