package id.co.gits.gitsutils.data.source.remote

data class ApiResult<T>(
        val code: Int,
        val message: String,
        val data: T?
)