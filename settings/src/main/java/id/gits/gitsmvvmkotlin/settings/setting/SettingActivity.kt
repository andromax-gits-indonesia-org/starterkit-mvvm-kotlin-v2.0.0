package id.gits.gitsmvvmkotlin.settings.setting;

import android.support.v4.app.Fragment
import id.co.gits.gitsutils.base.BaseActivity
import id.gits.gitsmvvmkotlin.settings.R
import id.gits.gitsmvvmkotlin.settings.databinding.ActivitySettingBinding


class SettingActivity : BaseActivity<ActivitySettingBinding>() {


    override fun bindLayoutRes(): Int = R.layout.activity_setting

    override fun bindToolbarId(): Int = 0

    override fun bindFragmentContainerId(): Int {
        return R.id.frame_main_content
    }

    override fun bindFragmentInstance(): Fragment {
        return SettingFragment.newInstance()
    }

    override fun onStartWork() {

    }

}
