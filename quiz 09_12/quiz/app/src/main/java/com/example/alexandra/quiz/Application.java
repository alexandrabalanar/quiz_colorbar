package com.example.alexandra.quiz;

import android.content.Intent;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

/**
 * Created by Alexandra on 11.12.2017.
 */



    public class Application extends android.app.Application {
        VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
            @Override
            public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
                if (newToken == null) {
                    System.out.print("ne rabotaet govno");
// VKAccessToken is invalid
                }
            }
        };
        @Override
        public void onCreate() {
            super.onCreate();
            vkAccessTokenTracker.startTracking();
            VKSdk.initialize(this);
        }
    }
