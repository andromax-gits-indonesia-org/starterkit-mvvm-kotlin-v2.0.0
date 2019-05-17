package id.co.gits.gitsutils.helper.extensions

import android.content.Context
import android.net.Uri
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.net.URI

/**
 * Created by mochadwi on 12/26/2018.

 */

val MEDIA_TYPE_TEXT = MediaType.parse("text/plain")
val MEDIA_TYPE_IMAGE = MediaType.parse("image/*")
val MEDIA_TYPE_BINARY = MediaType.parse("application/octet-stream")

fun String?.toRequestBody(type: MediaType? = MEDIA_TYPE_TEXT): RequestBody =
        RequestBody.create(type, this ?: "")

fun ByteArray?.toRequestBody(type: MediaType? = MEDIA_TYPE_BINARY): RequestBody =
        RequestBody.create(type, this ?: byteArrayOf(0))

fun File?.toRequestBody(type: MediaType? = MEDIA_TYPE_IMAGE): RequestBody =
        RequestBody.create(type, this ?: File(URI.create(Uri.EMPTY.path)))

fun RequestBody.toMultipartBody(partName: String = "image", file: File?): MultipartBody.Part =
        MultipartBody.Part.createFormData(partName, file?.name, this)

fun Context.prepareFilePart(partName: String = "image", file: File): MultipartBody.Part {
    val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
    return requestFile.toMultipartBody(file.name ?: "", file)
}
