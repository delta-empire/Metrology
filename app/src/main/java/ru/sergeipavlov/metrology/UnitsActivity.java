package ru.sergeipavlov.metrology;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import ru.sergeipavlov.metrology.BaseActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UnitsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_units);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.units_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.units_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<UnitsAdapter.Item> items = new ArrayList<>();
        items.add(new UnitsAdapter.Item(true, getString(R.string.base_units_title), null));
        String[] baseUnits = getResources().getStringArray(R.array.base_units);
        for (int i = 0; i < baseUnits.length; i++) {
            Class<?> activity = null;
            if (i == 0) {
                activity = TimeActivity.class;
            } else if (i == 1) {
                activity = LengthActivity.class;
            } else if (i == 2) {
                activity = MassActivity.class;
            } else if (i == 3) {
                activity = CurrentActivity.class;
            } else if (i == 4) {
                activity = TemperatureActivity.class;
            }
            items.add(new UnitsAdapter.Item(false, baseUnits[i], activity));
        }
        items.add(new UnitsAdapter.Item(true, getString(R.string.derived_units_title), null));
        String[] derivedUnits = getResources().getStringArray(R.array.derived_units);
        for (int i = 0; i < derivedUnits.length; i++) {
            Class<?> activity = null;
            if (i == 0) {
                activity = PressureActivity.class;
            } else if (i == 1) {
                activity = PowerActivity.class;
            } else if (i == 2) {
                activity = EnergyActivity.class;
            } else if (i == 3) {
                activity = FrequencyActivity.class;
            }
            items.add(new UnitsAdapter.Item(false, derivedUnits[i], activity));
        }

        recyclerView.setAdapter(new UnitsAdapter(items));
    }
}
