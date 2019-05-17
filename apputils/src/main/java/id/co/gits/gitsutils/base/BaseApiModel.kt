package id.co.gits.gitsutils.base

import com.google.gson.annotations.SerializedName

/**
 * Created by radhikayusuf on 17/05/19.
 */

data class BaseApiModel<T>(
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String?,
        @SerializedName("data") val data: T? = null,
        // Remove code below if project is running
        val page: Int,
        val total_results: Int,
        val total_pages: Int,
        val results: T? = null
)