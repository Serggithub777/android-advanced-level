package com.example.bottomsheetdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {


    private OnDialogListener dialogListener;

    public static MyBottomSheetDialogFragment newInstance() {
        return new MyBottomSheetDialogFragment() ;
    }@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater. inflate(R. layout. fragment_dialog, container,
                false) ;
        setCancelable(false) ;
        view. findViewById(R. id. btnOk) . setOnClickListener(new View. OnClickListener() {
              @Override
              public void onClick(View view) {
                  dismiss() ;
                  dismiss() ;
                  if (dialogListener != null) dialogListener. onDialogOk() ;
              }
          } ) ;
        view. findViewById(R. id. btnYes) . setOnClickListener(new  View. OnClickListener() {
               @Override
               public void onClick(View view) {
                   dismiss() ;
                   if (dialogListener != null) dialogListener. onDialogYes() ;
               }
           } ) ;
        return view;
    }
    public void setOnDialogListener(OnDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }
}
