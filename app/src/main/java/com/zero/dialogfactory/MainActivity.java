package com.zero.dialogfactory;

import android.os.Bundle;

import com.zero.dialoglib.base.DialogBaseActivity;

public class MainActivity extends DialogBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDialogFactory.showProgress("加载中...", true, true, R.layout.dlg_progress);
    }

}
