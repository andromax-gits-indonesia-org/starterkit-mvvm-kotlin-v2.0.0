package id.co.gits.movies.main

import id.co.gits.movies.R
import id.co.gits.movies.databinding.MainActivityBinding
import id.gits.gitsmvvmkotlin.base.BaseActivity

class MainActivity : BaseActivity<MainActivityBinding>() {

    override fun bindFragmentInstance() = MainFragment.newInstance()

    override fun bindFragmentContainerId() = R.id.frame_container

    override fun bindLayoutRes() = R.layout.main_activity

    override fun bindToolbarId() = 0

    override fun onStartWork() {

    }

}
