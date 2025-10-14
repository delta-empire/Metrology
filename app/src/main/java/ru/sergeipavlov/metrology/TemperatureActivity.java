package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class TemperatureActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_temperature;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.temperature_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new TemperatureFragment();
    }
}
