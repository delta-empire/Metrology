package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class AreaActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_area;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.area_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new AreaFragment();
    }
}
