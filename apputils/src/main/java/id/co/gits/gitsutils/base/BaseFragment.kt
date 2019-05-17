package id.co.gits.gitsutils.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import id.co.gits.gitsutils.helper.extensions.showSnackbarDefault
import id.co.gits.gitsutils.helper.extensions.showSnackbarWithCustomColor
import id.co.gits.gitsutils.helper.extensions.showToast
import id.co.gits.gitsutils.R
import id.co.gits.gitsutils.base.MessageType.*

/**
 * Created by radhikayusuf on 17/05/19.
 */

abstract class BaseFragment<T : BaseViewModel> : Fragment() {
    lateinit var mParentVM: T
    private var mMessageType = MESSAGE_TYPE_SNACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mParentVM = onCreateViewModel()
    }

    override fun onViewCreated(paramView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(paramView, savedInstanceState)
        mParentVM.apply {
            eventGlobalMessage.observe(this@BaseFragment, Observer {
                if (it != null) {
                    when (mMessageType) {
                        MESSAGE_TYPE_SNACK_CUSTOM -> {
                            view?.showSnackbarWithCustomColor(it,
                                    R.color.colorAccent,
                                    R.color.greyBackgroundDefault)
                        }
                        MESSAGE_TYPE_SNACK -> {
                            view?.showSnackbarDefault(it)
                        }
                        else -> {
                            requireContext().showToast(it)
                        }
                    }
                }
            })
        }

        onCreateObserver(mParentVM)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setContentData()
        mMessageType = getMessageType()
        mParentVM.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mParentVM.onClearDisposable()
        onDestroyObserver(mParentVM)
    }

    abstract fun onCreateViewModel(): T
    abstract fun onCreateObserver(viewModel: T)
    abstract fun setContentData()
    abstract fun getMessageType(): MessageType
    abstract fun onDestroyObserver(viewModel: T)

}