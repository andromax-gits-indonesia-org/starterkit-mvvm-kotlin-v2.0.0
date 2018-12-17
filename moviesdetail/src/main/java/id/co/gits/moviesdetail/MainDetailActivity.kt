package id.co.gits.moviesdetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import id.co.gits.moviesdetail.databinding.MainDetailActivityBinding
import id.gits.gitsmvvmkotlin.base.BaseActivity
import id.gits.gitsmvvmkotlin.util.replaceFragmentInActivity
import id.gits.gitsmvvmkotlin.util.transparentStatusBar

class MainDetailActivity : BaseActivity() {

    private lateinit var viewBinding: MainDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.main_detail_activity)
        viewBinding.apply {
            transparentStatusBar(window.decorView)

            if (savedInstanceState == null) {
                replaceFragmentInActivity(MainDetailFragment.newInstance(212),
                        R.id.frame_container)
            }
        }
    }

    fun obtainViewModel(): MainDetailViewModel = obtainViewModel(MainDetailViewModel::class.java)
}
