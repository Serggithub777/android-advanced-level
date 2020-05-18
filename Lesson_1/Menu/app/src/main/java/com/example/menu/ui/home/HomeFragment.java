package com.example.menu.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu.ListAdapter;
import com.example.menu.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initList(root);
        return root;
    }

    private void initList(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.recycler_list);
// Эта установка повышает производительность системы
        recyclerView.setHasFixedSize(true);
// Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
// Устанавливаем адаптер
        adapter = new ListAdapter(initData(), this);
        recyclerView.setAdapter(adapter);
    }

    private List<String> initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(String.format("Element %d", i));
        }
        return list;
    }
}

