package com.example.menu.ui.home;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu.IFragmentList;
import com.example.menu.ListAdapter;
import com.example.menu.MainActivity;
import com.example.menu.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IFragmentList {

    private ListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initList(root);
        ((MainActivity)requireActivity()).setFragmentList(this);
        setHasOptionsMenu(true); //будем использовать меню

        return root;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_send) {
            Bundle bundle = new Bundle();
            bundle.putString("arg", "data");
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.action_nav_home_to_nav_gallery, bundle);
        }
        return super.onOptionsItemSelected(item);
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
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu. ContextMenuInfo menuInfo) {
        super. onCreateContextMenu(menu, v, menuInfo) ;
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu) ;
    }@
            Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo() ;
        int id = item.getItemId() ;
        switch (id) {
            case R.id.add_context:
                adapter.addItem(String.format("New element %d", adapter.getItemCount()));
                return true;
            case R.id.update_context:
                adapter.updateItem(String. format("Updated element %d",
                        adapter.getMenuPosition() ) , adapter.getMenuPosition() ) ;
                return true;
            case R.id.remove_context:
                adapter.removeItem(adapter.getMenuPosition());
                return true;
            case R.id.clear_context:
                adapter.clearItems();
                return true;
        }return super.onContextItemSelected(item);
    }

    @Override
    public void addItem(String string) {
        adapter.addItem(string);
    }

    @Override
    public void clearItems() {
        adapter.clearItems();
    }
}

