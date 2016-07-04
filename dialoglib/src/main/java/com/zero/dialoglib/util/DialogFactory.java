package com.zero.dialoglib.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zero.dialoglib.widget.ProgressDialogFragment;

/**
 * Created by Jin_ on 2016/7/4
 * 邮箱：Jin_Zboy@163.com
 */
public class DialogFactory {
    private static final String TAG_DIALOG_PROGRESS = "tag_progress";
    private static final String TAG_DIALOG_CONFIRM  = "tag_confirm";
    private static final String TAG_DIALOG_LIST     = "tag_list";

    private FragmentManager mFragmentManager;

    public DialogListenerHolder mListenerHolder;

    public DialogFactory(FragmentManager fragmentManager, Bundle saveInstanceState) {
        mFragmentManager = fragmentManager;
        mListenerHolder = new DialogListenerHolder();
        mListenerHolder.getDialogListenerKey(saveInstanceState);
    }

    public void restoreDialogListener(Object obj) {
        mListenerHolder.restoreDialogListener(obj);
    }

    /**
     * 显示加载弹出框
     * @param msg           提示信息
     * @param cancelable    是否点击外部可关闭
     * @param isCustom      是否自定义布局
     * @param layoutRes     自定义布局id
     */
    public void showProgress(String msg, boolean cancelable, boolean isCustom, int layoutRes) {
        if (null != mFragmentManager) {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            mFragmentManager.executePendingTransactions();
            Fragment fragment = mFragmentManager.findFragmentByTag(TAG_DIALOG_PROGRESS);
            if (null != fragment) {
                ft.remove(fragment).commit();
            }

            ProgressDialogFragment progressDialogFragment =
                    ProgressDialogFragment.newInstance(msg, cancelable, isCustom, layoutRes);
            progressDialogFragment.show(mFragmentManager, TAG_DIALOG_PROGRESS);

            mFragmentManager.executePendingTransactions();
        }
    }
}
