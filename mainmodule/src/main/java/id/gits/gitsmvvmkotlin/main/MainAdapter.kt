package id.gits.gitsmvvmkotlin.main


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.gits.gitsmvvmkotlin.R
import id.gits.gitsmvvmkotlin.databinding.ItemMainBinding


class MainAdapter(val mViewModel: MainViewModel, private val mListener: MainUserActionListener) : RecyclerView.Adapter<MainAdapter.MainItem>() {

    private var mData: List<MainModel> = mViewModel.bMainList

    override fun onBindViewHolder(holder: MainItem, position: Int) {
        holder.bind(mData[position], mViewModel, mListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItem {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainItem(binding)
    }

    override fun getItemCount(): Int {
        return mData.size
    }


    fun replaceData(data: List<MainModel>) {
        mData = data
        notifyDataSetChanged()
    }


    class MainItem(val mBinding: ItemMainBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(data: MainModel, viewModel: MainViewModel, listener: MainUserActionListener) {
            mBinding.mData = data
            mBinding.mListener = listener
        }

    }

}