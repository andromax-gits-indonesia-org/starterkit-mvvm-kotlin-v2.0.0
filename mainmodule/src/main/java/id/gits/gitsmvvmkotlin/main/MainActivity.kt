package id.gits.gitsmvvmkotlin.main;

import android.support.v4.app.Fragment
import id.co.gits.gitsutils.base.BaseActivity
import id.gits.gitsmvvmkotlin.R
import id.gits.gitsmvvmkotlin.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {


    override fun bindLayoutRes(): Int = R.layout.activity_main

    override fun bindToolbarId(): Int = 0

    override fun bindFragmentContainerId(): Int {
        return R.id.frame_main_content
    }

    override fun bindFragmentInstance(): Fragment {
        return MainFragment.newInstance()
    }

    override fun onStartWork() {

    }

}
