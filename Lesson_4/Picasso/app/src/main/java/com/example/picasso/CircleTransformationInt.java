package com.example.picasso;

import android.graphics.Bitmap;
import android.service.autofill.Transformation;

interface CircleTransformationInt extends Transformation {
    Bitmap transform(Bitmap source);

    String key();
}
