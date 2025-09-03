package ru.sergeipavlov.metrology;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.ViewHolder> {

    static class Item {
        final boolean isHeader;
        final String text;
        final Class<?> activity;

        Item(boolean isHeader, String text, Class<?> activity) {
            this.isHeader = isHeader;
            this.text = text;
            this.activity = activity;
        }
    }

    private final List<Item> items;

    public UnitsAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.textView.setText(item.text);
        if (item.isHeader) {
            holder.textView.setTypeface(null, Typeface.BOLD);
            holder.itemView.setOnClickListener(null);
        } else {
            holder.textView.setTypeface(null, Typeface.NORMAL);
            if (item.activity != null) {
                holder.itemView.setOnClickListener(v ->
                        v.getContext().startActivity(new Intent(v.getContext(), item.activity)));
            } else {
                holder.itemView.setOnClickListener(null);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
