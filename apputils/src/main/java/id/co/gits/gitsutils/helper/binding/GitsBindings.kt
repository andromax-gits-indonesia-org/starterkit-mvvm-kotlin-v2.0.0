package id.co.gits.gitsdriver.utils

import android.annotation.SuppressLint
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import id.co.gits.gitsutils.GitsEnviroment.ConstNetwork
import id.co.gits.gitsutils.GitsEnviroment.ConstOther.HTTP_STRING
import id.co.gits.gitsutils.helper.extensions.removeImageHtmlTag
import id.co.gits.gitsutils.helper.other.GlideApp

object GitsBindings {

    @BindingAdapter("app:contentAdapter")
    @JvmStatic
    fun setContentAdapter(recyclerView: RecyclerView, data: List<Any>?) {
        recyclerView.adapter?.notifyDataSetChanged()
    }

    @BindingAdapter("app:contentPaginateAdapter")
    @JvmStatic
    fun setContentPaginateAdapter(recyclerView: RecyclerView, data: List<Any>?) {
        if ((data?.size ?: 0) >= (recyclerView.adapter?.itemCount ?: 0) - 1 && (data
                        ?: emptyList()).isNotEmpty()) {
            recyclerView.adapter?.notifyItemRangeInserted((recyclerView.adapter?.itemCount
                    ?: 0), (data?.size ?: 0))
        } else {
            recyclerView.adapter?.notifyDataSetChanged()
        }

    }

    @BindingAdapter("app:progressColor")
    @JvmStatic
    fun ProgressBar.setProgressColor(color: Int) {
        indeterminateDrawable?.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN)
    }

    @BindingAdapter("app:activeColor")
    @JvmStatic
    fun View.setBackgroundColorItemList(activeColor: Int?) {
        setBackgroundColor(activeColor ?: 0)
    }


    @SuppressLint("PrivateResource")
    @BindingAdapter("app:imageUrl", "app:placeholderRes", "app:errorRes", "app:placeholder", "app:error", requireAll = false)
    @JvmStatic
    fun ImageView.setImageUrl(imageUrl: String?, @DrawableRes placeHolderRes: Int?, @DrawableRes errorRes: Int?, placeHolderDrawable: Drawable?, errorDrawable: Drawable?) {
        if (imageUrl != null) {
            GlideApp.with(context).load(if (imageUrl.contains(HTTP_STRING)) imageUrl else ConstNetwork.BASE_URL_IMAGE + imageUrl).apply {
                if (placeHolderRes != null) {
                    placeholder(placeHolderRes)
                }

                if (placeHolderDrawable != null) {
                    placeholder(placeHolderDrawable)
                }

                if (errorRes != null) {
                    error(errorRes)
                }

                if (errorDrawable != null){
                    error(errorDrawable)
                }

            }.into(this)
        } else if (errorRes != null) {
            setImageResource(errorRes)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter("app:webviewContent", "app:webviewTextSize")
    @JvmStatic
    fun WebView.setClearWebviewContent(webviewContent: String?, webviewTextSize: Int?) {
        if (webviewContent != null) {
            loadDataWithBaseURL(null, GitsHelper.Func
                    .setClearWebviewContent(webviewContent), "text/html",
                    "utf-8", null)
            settings.javaScriptEnabled = true
        }
    }

    @BindingAdapter("app:textHtmlContent", "app:removeImageTag", requireAll = false)
    @JvmStatic
    fun TextView.setHtmlTextContent(value: String?, removeImageTag: Boolean?) {
        if (value != null) {
            text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(if (removeImageTag == true) value.removeImageHtmlTag() else value, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(if (removeImageTag == true) value.removeImageHtmlTag() else value)
            }
        }
    }
}