package id.co.gits.gitsutils.helper.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.gits.gitsutils.helper.other.HashUtils
import java.lang.reflect.Type

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 25/02/19
 * dedicated to build e-nutri
 *
 */

fun String?.removeImageHtmlTag(): String {
    return if (this != null) {
        replace(Regex(pattern = "<img[^>]*>"), "")
    } else {
        ""
    }

}

// TODO: Change this to use Kotlin serialization instead
val gson by lazy { Gson() }

inline fun <reified T> makeType() = object : TypeToken<T>() {}.type

inline fun <reified T> makeTypeInline() = object : TypeToken<T>() {}.type

fun <T> T.toJson(): String = gson.toJson(this)

inline fun <reified T> String.fromJson(): T = gson.fromJson(this, makeType<T>())

inline fun <reified T> String.fromJsonInline(): T {
    return gson.fromJson(this, makeTypeInline<T>())
}

fun String.sha512() = HashUtils.hashString("SHA-512", this)

fun String.sha256() = HashUtils.hashString("SHA-256", this)

fun String.sha1() = HashUtils.hashString("SHA-1", this)


// JAVA interop failed
object JSON {
    @JvmField
    val gson = Gson()

    @JvmStatic
    fun <OBJ> fromJson(str: String, type: Type): OBJ = gson.fromJson<OBJ>(str, type)

    @JvmStatic
    fun <OBJ> toJson(obj: OBJ): String = gson.toJson(obj)

    @JvmStatic
    fun <TYPE> makeType(): Type = object : TypeToken<TYPE>() {}.type
}