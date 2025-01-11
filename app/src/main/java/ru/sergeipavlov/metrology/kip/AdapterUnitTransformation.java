package ru.sergeipavlov.metrology.kip;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.sergeipavlov.metrology.R;
import ru.sergeipavlov.metrology.kip.transformation.AngularMeasureActivity;
import ru.sergeipavlov.metrology.kip.transformation.BoostActivity;
import ru.sergeipavlov.metrology.kip.transformation.DecibelsActivity;
import ru.sergeipavlov.metrology.kip.transformation.DensityActivity;
import ru.sergeipavlov.metrology.kip.transformation.EnergyActivity;
import ru.sergeipavlov.metrology.kip.transformation.IozonicRadiationActivity;
import ru.sergeipavlov.metrology.kip.transformation.LenghtActivity;
import ru.sergeipavlov.metrology.kip.transformation.WeightActivity;
import ru.sergeipavlov.metrology.kip.transformation.MassConsuptionActivity;
import ru.sergeipavlov.metrology.kip.transformation.MometPowerActivity;
import ru.sergeipavlov.metrology.kip.transformation.PowerActivity;
import ru.sergeipavlov.metrology.kip.transformation.PressureActivity;
import ru.sergeipavlov.metrology.kip.transformation.RadioactivityActivity;
import ru.sergeipavlov.metrology.kip.transformation.RotationSpeedActivity;
import ru.sergeipavlov.metrology.kip.transformation.SpeedActivity;
import ru.sergeipavlov.metrology.kip.transformation.SquareActivity;
import ru.sergeipavlov.metrology.kip.transformation.StrenghtActivity;
import ru.sergeipavlov.metrology.kip.transformation.TemperatureActivity;
import ru.sergeipavlov.metrology.kip.transformation.TimeActivity;
import ru.sergeipavlov.metrology.kip.transformation.ViscosityActivity;
import ru.sergeipavlov.metrology.kip.transformation.VolumeActivity;
import ru.sergeipavlov.metrology.kip.transformation.VolumetricConsumtionActivity;

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
            if (this.tv_main_menu_list.getText().equals("Расход (объёмный)")) {
                Intent intent = new Intent(v.getContext(), VolumetricConsumtionActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Расход массовый")) {
                Intent intent = new Intent(v.getContext(), MassConsuptionActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Давление")) {
                Intent intent = new Intent(v.getContext(), PressureActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Температура")) {
                Intent intent = new Intent(v.getContext(), TemperatureActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Масса")) {
                Intent intent = new Intent(v.getContext(), WeightActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Расстояние")) {
                Intent intent = new Intent(v.getContext(), LenghtActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Объём")) {
                Intent intent = new Intent(v.getContext(), VolumeActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Площадь")) {
                Intent intent = new Intent(v.getContext(), SquareActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Скорость")) {
                Intent intent = new Intent(v.getContext(), SpeedActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Время")) {
                Intent intent = new Intent(v.getContext(), TimeActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Ускорение")) {
                Intent intent = new Intent(v.getContext(), BoostActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Плотность")) {
                Intent intent = new Intent(v.getContext(), DensityActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Энергия")) {
                Intent intent = new Intent(v.getContext(), EnergyActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Мощность")) {
                Intent intent = new Intent(v.getContext(), PowerActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Сила")) {
                Intent intent = new Intent(v.getContext(), StrenghtActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Угловая мера")) {
                Intent intent = new Intent(v.getContext(), AngularMeasureActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Скорость вращения")) {
                Intent intent = new Intent(v.getContext(), RotationSpeedActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Момент силы")) {
                Intent intent = new Intent(v.getContext(), MometPowerActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Ионизирующее излучение")) {
                Intent intent = new Intent(v.getContext(), IozonicRadiationActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Радиоактивность")) {
                Intent intent = new Intent(v.getContext(), RadioactivityActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Децибелы")) {
                Intent intent = new Intent(v.getContext(), DecibelsActivity.class);
                v.getContext().startActivity(intent);
            } else if (this.tv_main_menu_list.getText().equals("Вязкость")) {
                Intent intent = new Intent(v.getContext(), ViscosityActivity.class);
                v.getContext().startActivity(intent);
            }
        }
    }
}
