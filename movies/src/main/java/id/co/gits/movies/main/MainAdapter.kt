package id.co.gits.movies.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import id.co.gits.movies.databinding.MainItemBinding
import id.gits.gitsmvvmkotlin.data.model.Movie
import id.co.gits.movies.BR

/**
 * Created by irfanirawansukirman on 26/01/18.
 */
class MainAdapter(private val movieItemClickListener: MainItemUserActionListener,
                  private val data: List<Movie>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MainItemHolder).bindItem(data[position], movieItemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MainItemHolder(MainItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false))
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        (holder as MainItemHolder).apply {
            mainItemBinding.apply {
                setVariable(BR.item, null)
                setVariable(BR.userActionListener, null)
                executePendingBindings()
            }
        }

        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int = data.size

    class MainItemHolder(val mainItemBinding: MainItemBinding) :
            RecyclerView.ViewHolder(mainItemBinding.root) {

        fun bindItem(movie: Movie, userActionListener: MainItemUserActionListener) {
            mainItemBinding.apply {
                setVariable(BR.item, movie)
                setVariable(BR.userActionListener, userActionListener)
                executePendingBindings()
            }
        }
    }
}