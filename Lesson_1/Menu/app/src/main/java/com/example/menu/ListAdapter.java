package com.example.menu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<String> data;
    private Fragment fragment;

    public ListAdapter(List<String> data, Fragment fragment) {
        this. data = data;
        this. fragment= fragment;
    }
    // region Переопределение методов адаптера
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false) ;
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
// Заполнение элементов холдера
        TextView textElement = holder.getTextElement() ;
        textElement.setText(data.get(position) ) ;
// Так регистрируется контекстное меню
        if (fragment != null) {
            fragment.registerForContextMenu(textElement) ;
        }
    }@Override
    public int getItemCount() {
        return data == null ? 0 : data. size() ;
    }
    // endregion
    // region Изменение списка
    // Добавить элемент в список
    void addItem(String element) {
        data.add(element) ;
        notifyItemInserted(data.size() -1) ;
    }
    // Заменить элемент в списке
    void updateItem(String element, int position) {
        data.set(position, element) ;
        notifyItemChanged(position) ;
    }
    // Удалить элемент из списка
    void removeItem(int position) {
        data.remove(position) ;
        notifyItemRemoved(position) ;
    }
    // Очистить список
    void clearItems() {
        data.clear() ;
        notifyDataSetChanged() ;
    }
    // endregion
    public class ViewHolder extends RecyclerView. ViewHolder {
        private TextView textElement;

        public ViewHolder(@NonNull View itemView) {
            super(itemView) ;
            textElement = itemView. findViewById(R. id. textElement) ;
        }

        public TextView getTextElement() {
            return textElement;
        }
    }
}

