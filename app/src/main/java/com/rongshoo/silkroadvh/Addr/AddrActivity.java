package com.rongshoo.silkroadvh.Addr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;

public class AddrActivity extends BaseActivity {
    public static void startAddrActivity(Context context) {
        Intent intent = new Intent(context, AddrActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addr);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new AddrMainFragment()).commit();
        }
    }
}
