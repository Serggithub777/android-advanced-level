package com.example.retrofit.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {
    // "clouds":{"all":75}

        @SerializedName("all")
        @Expose
        private int all;
        public int getAll() {
            return all;
        }
        public void setAll(int all) {
            this. all = all;
        }
}
