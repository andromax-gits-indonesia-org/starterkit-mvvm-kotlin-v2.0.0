package id.gits.gitsmvvmkotlin.settings.about


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.gits.gitsutils.base.BaseFragment
import android.arch.lifecycle.ViewModelProviders
import id.co.gits.gitsutils.base.MessageType.MESSAGE_TYPE_SNACK_CUSTOM
import id.co.gits.gitsutils.base.MessageType
import id.gits.gitsmvvmkotlin.settings.databinding.FragmentAboutBinding


class AboutFragment : BaseFragment<AboutViewModel>(), AboutUserActionListener {

    lateinit var mViewDataBinding: FragmentAboutBinding
    lateinit var mViewModel: AboutViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mViewDataBinding = FragmentAboutBinding.inflate(inflater, container, false).apply {
            mViewModel = this@AboutFragment.mViewModel
            mListener = this@AboutFragment
        }

        return mViewDataBinding.root
    }

    override fun onCreateViewModel(): AboutViewModel {
        mViewModel = ViewModelProviders.of(this@AboutFragment).get(AboutViewModel::class.java)
        return mViewModel
    }


    override fun onCreateObserver(viewModel: AboutViewModel) {

    }


    override fun setContentData() {
        mViewDataBinding.mViewModel?.start()
    }

    override fun getMessageType(): MessageType {
        return MESSAGE_TYPE_SNACK_CUSTOM
    }

    override fun onDestroyObserver(viewModel: AboutViewModel) {
    }

    override fun onRefresh() {

    }

    override fun onClickTest(text: String) {

    }

    override fun onNavigationToolbarClick() {
        requireActivity().onBackPressed()
    }


    companion object {
        fun newInstance() = AboutFragment().apply {

        }

    }

}