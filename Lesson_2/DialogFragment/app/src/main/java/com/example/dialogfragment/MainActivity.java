package com.example.dialogfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentDialogResult{
    DialogCustomFragment dlgCustom;
    DialogBuilderFragment dlgBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dlgCustom = new DialogCustomFragment() ;
        dlgBuilder = new DialogBuilderFragment() ;

        findViewById(R.id.dialogBuilder).setOnClickListener(onClickDialogBuilder);
        findViewById(R.id.dialogCustom).setOnClickListener(onClickDialogCustom);
    }
    // Вызов диалога с билдером
    OnClickListener onClickDialogBuilder = new OnClickListener() {
        @Override
        public void onClick(View v) {
            dlgBuilder.show(getSupportFragmentManager(), "dialogBuilder") ;
        }
    };

    // Вызов диалога с макетом
    OnClickListener onClickDialogCustom =new OnClickListener() {
        @Override
        public void onClick(View v) {
            dlgCustom.show(getSupportFragmentManager(), "dialogCustom") ;
        }
    };

    @Override
    public void onDialogResult(String result) {
        Toast. makeText(this, "Выбрано " + result, Toast. LENGTH_SHORT) . show() ;
    }
}
