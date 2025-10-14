package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class EnergyActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_energy;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.energy_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new EnergyFragment();
    }
}
