package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class PowerActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_power;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.power_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new PowerFragment();
    }
}
