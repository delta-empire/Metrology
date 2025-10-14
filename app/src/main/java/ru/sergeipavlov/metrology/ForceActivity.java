package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ForceActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_force;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.force_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new ForceFragment();
    }
}
