package com.example.retrofit.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {
    // "weather":[{"id":803, "main":"Clouds", "description":"broken
   // clouds", "icon":"04n"}]

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("main")
        @Expose
        private String main;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("icon")
        @Expose
        private String icon;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this. id = id;
        }
        public String getMain() {
            return main;
        }
        public void setMain(String main) {
            this. main = main;
        }
        public String getDescription() {
        return description;
        }
        public void setDescription(String description) {
            this. description = description;
        }public String getIcon() {
            return icon;
        }
        public void setIcon(String icon) {
            this. icon = icon;
        }
}

