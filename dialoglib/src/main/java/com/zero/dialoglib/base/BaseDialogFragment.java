package com.zero.dialoglib.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

/**
 * Created by Jin_ on 2016/7/4
 * 邮箱：Jin_Zboy@163.com
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private static final String EXTRA_DIALOG_TITLE      = "extra_dialog_title";
    private static final String EXTRA_DIALOG_MESSAGE    = "extra_dialog_message";
    private static final String EXTRA_DIALOG_CANCELABLE = "extra_dialog_cancelable";
    private static final String EXTRA_DIALOG_CUSTOM     = "extra_dialog_custom";
    private static final String EXTRA_DIALOG_ID         = "extra_dialog_id";
    private static final String EXTRA_DIALOG_LAYOUT_RES = "extra_dialog_layout_res";

    protected DialogBaseActivity _mBaseActivity;
    protected static final int DEFAULT_LAYOUT_RES = -1;

    protected int mDialogId;
    protected int mLayoutRes;
    protected String mTitle;
    protected boolean mIsCustom = false;
    protected boolean mIsCancelable = false;

    protected boolean mIsParseListener = false;

    public interface BaseDialogListener {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof DialogBaseActivity) {
            _mBaseActivity = (DialogBaseActivity) activity;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        BaseDialogListener mListener = null;

        if (!mIsParseListener) {
            mIsParseListener = true;

            if (getParentFragment() instanceof BaseDialogFragment) {

            } else if (null != _mBaseActivity) {
                mListener = _mBaseActivity.getDialogListener();
            }

            if (null != mListener) {
                onReceiveDialogListener(mListener);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseArgs(getArguments());

        setCancelable(mIsCancelable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getParentFragment() instanceof  BaseDialogFragment) {
//            ((DialogBaseFragment)getParentFragment())
        } else if (null != _mBaseActivity) {
            _mBaseActivity.clearDialogListener();
        }
    }

    protected abstract void onReceiveDialogListener(BaseDialogListener listener);

    protected void parseArgs(Bundle args) {
        mTitle = args.getString(EXTRA_DIALOG_TITLE);
        mIsCustom = args.getBoolean(EXTRA_DIALOG_CUSTOM);
        mDialogId = args.getInt(EXTRA_DIALOG_ID);
        mIsCancelable = args.getBoolean(EXTRA_DIALOG_CANCELABLE);
        if (mIsCustom) {
            mLayoutRes = args.getInt(EXTRA_DIALOG_LAYOUT_RES);
        }
    }

    @NonNull
    protected static void putIdParam(Bundle bundle, int dialogId) {
        if (null != bundle) {
            bundle.putInt(EXTRA_DIALOG_ID, dialogId);
        }
    }

    @NonNull
    protected static void putIsCustomParam(Bundle bundle, boolean isCustom) {
        if (null != bundle) {
            bundle.putBoolean(EXTRA_DIALOG_CUSTOM, isCustom);
        }
    }

    @NonNull
    protected static void putTitleParam(Bundle bundle, String title) {
        if (null != bundle) {
            bundle.putString(EXTRA_DIALOG_TITLE, title);
        }
    }

    @NonNull
    protected static void putCancelableParam(Bundle bundle, boolean cancelable) {
        if (null != bundle) {
            bundle.putBoolean(EXTRA_DIALOG_CANCELABLE, cancelable);
        }
    }

    @NonNull
    protected static void putMessageParam(Bundle bundle, String message) {
        if (null != bundle) {
            bundle.putString(EXTRA_DIALOG_MESSAGE, message);
        }
    }

    protected static void putLayoutRes(Bundle bundle, int layoutRes) {
        if (null != bundle) {
            bundle.putInt(EXTRA_DIALOG_LAYOUT_RES, layoutRes);
        }
    }

    protected String getMessageParam() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return null;
        }
        return bundle.getString(EXTRA_DIALOG_MESSAGE);
    }

}
