package com.zc.commonutilslib;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 *ProgressDialog
 */
public class AlertDialogUtil {

    protected static AlertDialog mAlertDialog;

    public static void showDialogWithOk(Context mContext, String title, String msg,String btnText) {
        closeDialog();
        mAlertDialog = new AlertDialog.Builder(mContext).create();
        mAlertDialog.setTitle(title);
        mAlertDialog.setMessage(msg);
        mAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE,btnText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        if (null != mAlertDialog
                && !mAlertDialog.isShowing()) {
            mAlertDialog.show();
        }
    }

    public static void showDialog(Context mContext, String title, String msg,boolean canCancel) {
        closeDialog();
        mAlertDialog = new AlertDialog.Builder(mContext).create();
        mAlertDialog.setTitle(title);
        mAlertDialog.setMessage(msg);
        mAlertDialog.setCancelable(canCancel);
        if (null != mAlertDialog
                && !mAlertDialog.isShowing()) {
            mAlertDialog.show();
        }
    }

    public static void closeDialog() {
        if (null != mAlertDialog && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
        mAlertDialog = null;//此处一定置空，否则就容易导致下一个Activity show闪退！！！
    }
}
