package com.example.orangeandroidapp.features.data.service.handler

import android.content.Context
import com.example.orangeandroidapp.core.utils.ApiStatus
import com.example.orangeandroidapp.core.utils.AppConstants.API_FAILURE_CODE
import com.example.orangeandroidapp.core.utils.AppConstants.API_INTERNET_CODE
import com.example.orangeandroidapp.core.utils.AppConstants.API_INTERNET_MESSAGE
import com.example.orangeandroidapp.core.utils.AppConstants.API_SOMETHING_WENT_WRONG_MESSAGE
import com.example.orangeandroidapp.core.utils.NetWorkResult
import com.example.orangeandroidapp.core.utils.Utils
import com.example.orangeandroidapp.features.data.service.dao.Item
import kotlin.reflect.full.memberProperties

class ApiResultHandler<T>(private val context: Context, private val onLoading: () -> Unit,
                          private val onSuccess: (T?) -> Unit,
                          private val onFailure: (T?) -> Unit) {

    fun handleApiResult(result: NetWorkResult<T?>) {
        when (result.status) {
            ApiStatus.LOADING -> {
                onLoading()
            }
            ApiStatus.SUCCESS -> {
                onSuccess(result.data)
            }

            ApiStatus.ERROR -> {
                onFailure(result.data)
                when (result.data?.getField<String>("ErrorCode") ?: "0") {
                    API_FAILURE_CODE -> {
                        Utils.showAlertDialog(context, result.message.toString())
                    }
                    API_INTERNET_CODE -> {
                        Utils.showAlertDialog(context, API_INTERNET_MESSAGE)
                    }
                    else -> {
                        Utils.showAlertDialog(context, API_SOMETHING_WENT_WRONG_MESSAGE)
                    }
                }
            }
        }
    }

    @Throws(IllegalAccessException::class, ClassCastException::class)
    inline fun <reified T> Any.getField(fieldName: String): T? {
        this::class.memberProperties.forEach { kCallable ->
            if (fieldName == kCallable.name) {
                return kCallable.getter.call(this) as T?
            }
        }
        return null
    }

}