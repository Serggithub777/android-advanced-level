package com.example.retrofit.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {
    // "wind":{"speed":1, "deg":140}
        @SerializedName("speed")
        @Expose
        private float speed;
        @SerializedName("deg")
        @Expose
        private float deg;
        public float getSpeed() {
            return speed;
        }public void setSpeed(float speed) {
            this. speed = speed;
        }public float getDeg() {
            return deg;
        }public void setDeg(float deg) {
            this. deg = deg;
        }
}
