package id.gits.gitsmvvmkotlin.settings.setting


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.gits.gitsutils.base.BaseFragment
import android.arch.lifecycle.ViewModelProviders
import id.co.gits.gitsutils.base.MessageType
import id.co.gits.gitsutils.base.MessageType.MESSAGE_TYPE_SNACK_CUSTOM
import id.gits.gitsmvvmkotlin.settings.databinding.FragmentSettingBinding


class SettingFragment : BaseFragment<SettingViewModel>(), SettingUserActionListener {


    lateinit var mViewDataBinding: FragmentSettingBinding
    lateinit var mViewModel: SettingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mViewDataBinding = FragmentSettingBinding.inflate(inflater, container, false).apply {
            mViewModel = this@SettingFragment.mViewModel
            mListener = this@SettingFragment
        }

        return mViewDataBinding.root
    }

    override fun onCreateViewModel(): SettingViewModel {
        mViewModel = ViewModelProviders.of(this@SettingFragment).get(SettingViewModel::class.java)
        return mViewModel
    }


    override fun onCreateObserver(viewModel: SettingViewModel) {

    }


    override fun setContentData() {
        mViewDataBinding.mViewModel?.start()
    }

    override fun getMessageType(): MessageType {
        return MESSAGE_TYPE_SNACK_CUSTOM
    }

    override fun onDestroyObserver(viewModel: SettingViewModel) {
    }

    override fun onRefresh() {

    }

    override fun onClickTest(text: String) {

    }

    override fun onNavigationToolbarClick() {
        requireActivity().onBackPressed()
    }


    companion object {
        fun newInstance() = SettingFragment().apply {

        }

    }

}