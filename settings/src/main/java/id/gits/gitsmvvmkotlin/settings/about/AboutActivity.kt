package id.gits.gitsmvvmkotlin.settings.about;

import android.support.v4.app.Fragment
import id.co.gits.gitsutils.base.BaseActivity
import id.gits.gitsmvvmkotlin.settings.R
import id.gits.gitsmvvmkotlin.settings.databinding.ActivityAboutBinding


class AboutActivity : BaseActivity<ActivityAboutBinding>() {


    override fun bindLayoutRes(): Int = R.layout.activity_about

    override fun bindToolbarId(): Int = 0

    override fun bindFragmentContainerId(): Int {
        return R.id.frame_main_content
    }

    override fun bindFragmentInstance(): Fragment {
        return AboutFragment.newInstance()
    }

    override fun onStartWork() {

    }

}
