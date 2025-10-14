package ru.sergeipavlov.metrology;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class CurrentActivity extends SingleFragmentActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_current;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.current_container;
    }

    @NonNull
    @Override
    protected Fragment createFragment() {
        return new CurrentFragment();
    }
}
