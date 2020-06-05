package com.example.picasso;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView1 = findViewById(R.id.imageView1) ;
        ImageView imageView2 = findViewById(R.id.imageView2) ;
        ImageView imageView3 = findViewById(R.id.imageView3) ;
        Picasso.get()
        .load("https://c1.staticflickr.com/1/186/31520440226_175445c41a_b.jpg")
                .transform((Transformation) new CircleTransformation())
                .rotate(-90)
                .into(imageView1);
        Picasso.get()
                .load("https://images.unsplash.com/photo-1486671736870-2f695ecdf813?ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80")
                .transform((Transformation) new CircleTransformation())
                .rotate(0)
                .into(imageView2);
        Picasso.get()
                .load("https://images.unsplash.com/photo-1486671736870-2f695ecdf813?ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80")
                .into(imageView3);
    }
}
