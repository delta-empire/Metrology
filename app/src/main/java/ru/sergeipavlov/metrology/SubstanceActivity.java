package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SubstanceActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_substance;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.substance_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new SubstanceFragment();
    }
}
