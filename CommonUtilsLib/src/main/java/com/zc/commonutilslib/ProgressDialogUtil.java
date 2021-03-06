package com.zc.commonutilslib;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

/**
 *ProgressDialog
 */
public class ProgressDialogUtil {

    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context mContext, String message) {
        closeProgressDialog();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(true);
        if (null != progressDialog
                && !progressDialog.isShowing()
                && !((Activity)mContext).isFinishing()) {//检查activity是否finishing!!!
            progressDialog.show();
        }
    }

    public static void closeProgressDialog() {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;//此处一定置空，否则就容易导致下一个Activity show闪退！！！
    }
}
