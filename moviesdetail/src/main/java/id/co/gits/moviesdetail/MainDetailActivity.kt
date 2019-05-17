package id.co.gits.moviesdetail

import id.ac.unpad.profolio.util.ext.replaceFragmentInActivity
import id.co.gits.moviesdetail.databinding.MainDetailActivityBinding
import id.gits.gitsmvvmkotlin.base.BaseActivity

class MainDetailActivity : BaseActivity<MainDetailActivityBinding>() {

    override fun bindLayoutRes() = R.layout.main_detail_activity

    override fun bindToolbarId() = 0

    override fun bindFragmentInstance() = MainDetailFragment.newInstance(212)

    override fun bindFragmentContainerId() = R.id.frame_container

    override fun onStartWork() {

    }

}
