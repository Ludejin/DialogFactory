package com.zero.dialoglib.widget;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.zero.dialoglib.base.BaseDialogFragment;

/**
 * Created by Jin_ on 2016/7/4
 * 邮箱：Jin_Zboy@163.com
 */
public class ProgressDialogFragment extends BaseDialogFragment {

    public static ProgressDialogFragment newInstance(String msg, boolean cancelable) {
        return newInstance(msg, cancelable, false, DEFAULT_LAYOUT_RES);
    }

    public static ProgressDialogFragment newInstance(String msg, boolean cancelable,
                                                     boolean isCustom, int layoutRes) {
        ProgressDialogFragment fragment = new ProgressDialogFragment();
        Bundle bundle = new Bundle();
        putMessageParam(bundle, msg);
        putCancelableParam(bundle, cancelable);
        putIsCustomParam(bundle, isCustom);
        if (isCustom) {
            putLayoutRes(bundle, layoutRes);
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!mIsCustom) {
            ProgressDialog dialog = new ProgressDialog(_mBaseActivity);
            String msg = getMessageParam();
            dialog.setMessage(msg);
            dialog.setCancelable(mIsCancelable);
            return dialog;
        } else {
            return super.onCreateDialog(savedInstanceState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mIsCustom) {
            View view = inflater.inflate(mLayoutRes, container, false);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //启用窗体的扩展特性。
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            return view;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    protected void onReceiveDialogListener(BaseDialogListener listener) {

    }
}
