package id.co.gits.moviesdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.gits.moviesdetail.databinding.MainDetailFragmentBinding
import id.gits.gitsmvvmkotlin.base.BaseFragment

class MainDetailFragment : Fragment() {

    private lateinit var viewBinding: MainDetailFragmentBinding
    private lateinit var viewModel: MainDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewBinding = MainDetailFragmentBinding.inflate(inflater, container, false)
//                .apply {
//            viewModel = (activity as MainDetailActivity).obtainViewModel().apply {
//                eventGlobalMessage.observe(this@MainDetailFragment, Observer { message ->
//                    viewBinding.root.showSnackbarDefault(viewBinding.root, message
//                            ?: GitsHelper.Const.SERVER_ERROR_MESSAGE_DEFAULT,
//                            GitsHelper.Const.SNACKBAR_TIMER_SHOWING_DEFAULT)
//                })
//                movieImagePosterUrl.observe(this@MainDetailFragment, Observer { imageUrl ->
        // Sample create image file from URL n' save it into memory
//                    GlideApp.with(this@MainDetailFragment)
//                            .asBitmap()
//                            .load(GitsHelper.Const.BASE_IMAGE_URL_MOVIE_DB + imageUrl)
//                            .listener(object : RequestListener<Bitmap> {
//                                override fun onLoadFailed(e: GlideException?, model: Any?,
//                                                          target: Target<Bitmap>?, isFirstResource:
//                                                          Boolean): Boolean {
//                                    return false
//                                }
//
//                                override fun onResourceReady(resource: Bitmap?, model: Any?,
//                                                             target: Target<Bitmap>?, dataSource:
//                                                             DataSource?, isFirstResource: Boolean):
//                                        Boolean {
//                                    return if (resource != null) {
//                                        GitsHelper.Func.saveBitmapToLocalFile(context!!, resource,
//                                                null, true)
//                                        true
//                                    } else {
//                                        false
//                                    }
//                                }
//                            })
//                            .submit()
//                })
//            }
//        }
//
//        viewBinding.let {
//            it.viewModel = viewBinding.viewModel
//            it.setLifecycleOwner(this@MainDetailFragment)
//        }

        return viewBinding.root
    }

    private fun setupMainDetailViewModel() {
    }

    private fun getMoviesById(movieId: Int) {
    }

    companion object {
        fun newInstance(movieId: Int) = MainDetailFragment().apply {
        }
    }

}
