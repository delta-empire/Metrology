package ru.sergeipavlov.metrology.kip;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.sergeipavlov.metrology.AdapterMainMenu;
import ru.sergeipavlov.metrology.R;
import ru.sergeipavlov.metrology.kip.transformation.PressureActivity;
import ru.sergeipavlov.metrology.kip.transformation.TemperatureActivity;

public class AdapterUnitTransformation extends RecyclerView.Adapter<AdapterUnitTransformation.ViewHolder> {
    private List<String> unitTransformation;
    public AdapterUnitTransformation(List<String> unitTransformation) {
        this.unitTransformation = unitTransformation;
    }
    @NonNull
    @Override
    public AdapterUnitTransformation.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new AdapterUnitTransformation.ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUnitTransformation.ViewHolder holder, int position) {
        holder.tv_main_menu_list.setText(this.unitTransformation.get(position));
    }

    @Override
    public int getItemCount() {
        return this.unitTransformation.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_main_menu_list;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.tv_main_menu_list = view.findViewById(R.id.tv_main_menu_list);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(itemView.getContext(), "position:" + this.tv_main_menu_list.getText(), Toast.LENGTH_LONG).show();
            if (this.tv_main_menu_list.getText().equals("Давление")) {
                Intent intent = new Intent(v.getContext(), PressureActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Температура")) {
                Intent intent = new Intent(v.getContext(), TemperatureActivity.class);
                v.getContext().startActivity(intent);
            }
        }
    }
}
