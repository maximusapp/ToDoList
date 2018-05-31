package com.list.todo.todolist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.list.todo.todolist.R;
import com.list.todo.todolist.data.Data;

import java.util.ArrayList;
import java.util.List;


public class ItemListMainAdapter extends RecyclerView.Adapter<ItemListMainAdapter.ViewHolder> {

    private List<Data>items = new ArrayList<>();
    private Context context;

    public ItemListMainAdapter(Context context, List<Data>items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemListMainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_main_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListMainAdapter.ViewHolder holder, int position) {

        holder.tvTitle.setText(items.get(position).getTitle());
        holder.tvDescribe.setText(items.get(position).getDescribe());
        holder.tvCategory.setText(items.get(position).getCategory());

    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvTitle;
        public TextView tvDescribe;
        public TextView tvCategory;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView)itemView.findViewById(R.id.title);
            tvDescribe = (TextView)itemView.findViewById(R.id.description);
            tvCategory = (TextView)itemView.findViewById(R.id.item_category);


        }
    }
}
