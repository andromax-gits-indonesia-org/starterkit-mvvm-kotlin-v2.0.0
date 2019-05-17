package id.gits.gitsmvvmkotlin.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.gits.gitsutils.base.BaseFragment
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import id.co.gits.gitsutils.GitsRouteNavigation
import id.co.gits.gitsutils.base.MessageType
import id.co.gits.gitsutils.base.MessageType.MESSAGE_TYPE_SNACK_CUSTOM
import id.gits.gitsmvvmkotlin.databinding.FragmentMainBinding


class MainFragment : BaseFragment<MainViewModel>(), MainUserActionListener {


    lateinit var mViewDataBinding: FragmentMainBinding
    lateinit var mViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mViewDataBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            mViewModel = this@MainFragment.mViewModel
            mListener = this@MainFragment
        }

        return mViewDataBinding.root
    }

    override fun onCreateViewModel(): MainViewModel {
        mViewModel = ViewModelProviders.of(this@MainFragment).get(MainViewModel::class.java)
        return mViewModel
    }


    override fun onCreateObserver(viewModel: MainViewModel) {

    }


    override fun setContentData() {
        setupListData()
    }

    override fun getMessageType(): MessageType {
        return MESSAGE_TYPE_SNACK_CUSTOM
    }

    override fun onNavigationToolbarClick() {

    }

    override fun onDestroyObserver(viewModel: MainViewModel) {
    }

    override fun onRefresh() {

    }

    override fun onClickItem(data: MainModel) {
        GitsRouteNavigation.openSettingPage(requireContext())
    }


    private fun setupListData() {
        mViewDataBinding.recyclerMain.adapter = MainAdapter(mViewModel, this@MainFragment)
        mViewDataBinding.recyclerMain.layoutManager = LinearLayoutManager(context)
    }


    companion object {
        fun newInstance() = MainFragment().apply {

        }

    }

}
