package ru.sergeipavlov.metrology;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.sergeipavlov.metrology.kip.ScaleSignalActivity;
import ru.sergeipavlov.metrology.kip.transformation.UnitTrasformationActivity;

public class AdapterMainMenu extends RecyclerView.Adapter<AdapterMainMenu.ViewHolder> {
    private List<String> mainMenuList;
    public AdapterMainMenu(List<String> mainMenuList) {
        this.mainMenuList = mainMenuList;
    }
    @NonNull
    @Override
    public AdapterMainMenu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMainMenu.ViewHolder holder, int position) {
        holder.tv_main_menu_list.setText(this.mainMenuList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mainMenuList.size();
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
            if (this.tv_main_menu_list.getText().equals("Расчёт шкала-сигнал")) {
                Intent intent = new Intent(v.getContext(), ScaleSignalActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Перевод единиц измерения")) {
                Intent intent = new Intent(v.getContext(), UnitTrasformationActivity.class);
                v.getContext().startActivity(intent);
            }
//            } else if (this.tv_main_menu_list.getText().equals("Давление")) {
//                Intent intent = new Intent(v.getContext(), PressureActivity.class);
//                v.getContext().startActivity(intent);
//            } else if (this.tv_main_menu_list.getText().equals("Температура")) {
//                Intent intent = new Intent(v.getContext(), TemperatureActivity.class);
//                v.getContext().startActivity(intent);
//            }
        }
    }
}
