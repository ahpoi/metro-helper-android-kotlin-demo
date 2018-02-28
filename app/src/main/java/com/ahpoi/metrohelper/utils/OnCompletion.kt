package com.ahpoi.metrohelper.utils

/**
 * Created by ahpoi on 23/2/18.
 */
interface OnCompletion {

    fun onComplete(`object`: Any)

    fun onFailure(message: String)

}