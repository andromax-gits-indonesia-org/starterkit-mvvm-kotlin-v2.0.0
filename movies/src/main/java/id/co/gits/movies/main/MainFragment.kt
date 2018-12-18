package id.co.gits.movies.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.unpad.profolio.util.ext.navigatorImplicit
import id.ac.unpad.profolio.util.ext.putArgs
import id.ac.unpad.profolio.util.ext.showToast
import id.ac.unpad.profolio.util.ext.verticalListStyle
import id.co.gits.movies.R
import id.co.gits.movies.databinding.MainFragmentBinding
import id.gits.gitsmvvmkotlin.base.BaseFragment
import id.gits.gitsmvvmkotlin.data.model.Movie
import kotlinx.android.synthetic.main.main_fragment.*
import radhika.yusuf.id.mvvmkotlin.utils.chocohelper.ChocoBinding
import radhika.yusuf.id.mvvmkotlin.utils.chocohelper.ChocoChips
import radhika.yusuf.id.mvvmkotlin.utils.chocohelper.ChocoViewModel

/**
 * Created by irfanirawansukirman on 26/01/18.
 */

class MainFragment : BaseFragment<MainViewModel>(), MainItemUserActionListener {

    @ChocoBinding(R.layout.main_fragment)
    lateinit var viewBinding: MainFragmentBinding

    @ChocoViewModel
    lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ChocoChips.inject<MainFragmentBinding, MainViewModel, MainItemUserActionListener>(this)
        return viewBinding.root
    }

    override fun onCreateObserver(viewModel: MainViewModel) {
        viewModel.apply {
            movieListLive.observe(this@MainFragment, Observer {
                // load movie list to view
                setupMovieList(it!!)
            })
        }
    }

    override fun setContentData() {
        viewModel.start()
    }

    override fun setMessageType() = MESSAGE_TYPE_SNACK

    override fun onDestroyObserver(viewModel: MainViewModel) {
        viewModel.apply {
            movieListLive.removeObservers(this@MainFragment)
            eventGlobalMessage.removeObservers(this@MainFragment)
            eventShowProgress.removeObservers(this@MainFragment)
        }
    }

    override fun onMovieClicked(movie: Movie) {
        showToast(requireContext(), movie.original_title ?: getString(R.string.lorem_ipsum))
        navigatorImplicit(requireContext(),
                "id.co.gits.moviesdetail.MainDetailActivity")
    }

    private fun setupMovieList(movie: List<Movie>) {
        recycler_main.apply {
            verticalListStyle()
            adapter = MainAdapter(this@MainFragment, movie)
        }
    }

    companion object {
        fun newInstance() = MainFragment().putArgs { }
    }

}

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
        ViewModelProviders.of(this, MainVMFactory.getInstance(requireActivity()
                .application)).get(viewModelClass)