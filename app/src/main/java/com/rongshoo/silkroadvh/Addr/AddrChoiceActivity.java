package com.rongshoo.silkroadvh.Addr;

import android.os.Bundle;

import com.rongshoo.silkroadvh.BaseActivity;
import com.rongshoo.silkroadvh.R;

public class AddrChoiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addr_choice);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.addr_container, new AddrPFragment()).commit();
        }
    }
}
