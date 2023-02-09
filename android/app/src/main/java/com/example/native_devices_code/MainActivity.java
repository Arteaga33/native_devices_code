//There is an issue with the version, can't find symbol.
//Similar issue with the BatteryManage....

package com.example.native_devices_code;

import androidx.annotation.NonNull;
import io.flutter.plugins.GeneratedPluginRegistrant;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.BatteryManager;
import android.os.Build;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.EventChannel.EventSink;
import io.flutter.plugin.common.EventChannel.StreamHandler;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "course.futter.dev/battery";
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine){
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL).setMethodCallHandler((call, result)->{
            //Note that this method is invoked on the main thread.
            if(call.method.equals("getBatteryLevel")){
                int batteryLevel = getBatteryLevel();
                if(batteryLevel != -1){
                    result.success(batteryLevel);
                }
                else{
                    result.error("UNAVAILABLE", "Battery level not available.", null);
                }
            }else{
                result.notImplemented();
            }
        });
    }    

    private int getBatteryLevel(){
        int batteryLevel = -1;
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        return batteryLevel;
    }
}
