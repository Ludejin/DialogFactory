package com.zero.dialoglib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zero.dialoglib.util.DialogFactory;

/**
 * Created by Jin_ on 2016/7/4
 * 邮箱：Jin_Zboy@163.com
 */
public class DialogBaseActivity extends AppCompatActivity {
    protected DialogFactory mDialogFactory;

    public BaseDialogFragment.BaseDialogListener getDialogListener(){
        return mDialogFactory.mListenerHolder.getDialogListener();
    }

    public void clearDialogListener() {
        mDialogFactory.mListenerHolder.setDialogListener(null);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mDialogFactory.mListenerHolder.saveDialogListenerKey(outState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialogFactory = new DialogFactory(getSupportFragmentManager(), savedInstanceState);
        mDialogFactory.restoreDialogListener(this);
    }
}
