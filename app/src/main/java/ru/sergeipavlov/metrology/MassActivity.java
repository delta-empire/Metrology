package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class MassActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mass;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.mass_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new MassFragment();
    }
}
