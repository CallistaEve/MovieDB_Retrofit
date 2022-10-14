package com.uc.week4retrofit.view

import android.app.Activity
import android.app.AlertDialog
import com.uc.week4retrofit.R

class LoadingDialog (val mActivity: Activity){
    private lateinit var isdialog:AlertDialog
    fun startLoading(){
        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.loading,null)
        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog = builder.create()
        isdialog.show()
    }
    fun isDismiss(){
        isdialog.dismiss()
    }
}