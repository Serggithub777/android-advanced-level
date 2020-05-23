package com.example.dialogfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

public class DialogCustomFragment extends DialogFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Подключаем макет
        android.view.View view = inflater. inflate(R. layout. fragment_dialog, null) ;
// Устанавливаем слушателя
        view. findViewById(R. id. yes) . setOnClickListener(listener) ;
        view. findViewById(R. id. nearYes) . setOnClickListener(listener) ;
        view. findViewById(R. id. dunno) . setOnClickListener(listener) ;
        view. findViewById(R. id. nearNo) . setOnClickListener(listener) ;
        view. findViewById(R. id. no) . setOnClickListener(listener) ;
        return view;
    }private View. OnClickListener listener = new View. OnClickListener() {
        @Override
        public void onClick(View view) {
// Закрываем диалог
            dismiss() ;
// Передаём в Activity информацию об нажатой кнопке
            String answer = ((Button) view) . getText() . toString() ;
            ((MainActivity) getActivity() ) . onDialogResult(answer) ;
        }
    } ;
}
