package com.ahpoi.metrohelper.utils

import android.app.ProgressDialog
import android.content.Context
import android.os.Handler

/**
 * Created by ahpoi on 23/2/18.
 */
interface ProgressTimeout {
    fun timeout()
}

fun showProgress(context: Context, message: String = "Please wait"): ProgressDialog {
    val progressDialog = ProgressDialog(context)
    progressDialog.setMessage(message)
    progressDialog.setCanceledOnTouchOutside(false)
    progressDialog.show()
    return progressDialog
}

fun hideProgress(progressDialog: ProgressDialog?) {
    if (progressDialog != null) {
        if (progressDialog.isShowing) {
            progressDialog.hide()
            progressDialog.dismiss()
        }
    }
}

fun showProgressWithTimeout(context: Context,
                            message: String = "Please wait...",
                            milliseconds: Long = 15000L,
                            callback: ProgressTimeout?): ProgressDialog {
    val progress = showProgress(context, message)
    val handler = Handler()
    handler.postDelayed({ hideProgress(progress)
        callback?.timeout()
    }, milliseconds)
    return progress
}
