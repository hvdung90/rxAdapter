package com.rx.android.rxrecycleradapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rx.android.rxrecycleradapter.viewHolder.OnViewHolderInflated
import com.rx.android.rxrecycleradapter.viewHolder.SimpleViewHolder
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by hvdung on 09/12/2015.
 */
internal class RxAdapter<DataType, LayoutBinding : ViewDataBinding>(@param:LayoutRes private val mItem_layout: Int, dataSet: List<DataType>) : RecyclerView.Adapter<SimpleViewHolder<DataType, LayoutBinding>>() {
    private var dataSet: List<DataType>
    private val mPublishSubject: PublishSubject<SimpleViewHolder<DataType, LayoutBinding>>
    private var mOnViewHolderInflate: OnViewHolderInflated? = null

    init {
        this.dataSet = dataSet
        mPublishSubject = PublishSubject.create()
    }

    fun setOnViewHolderInflate(onViewHolderInflate: OnViewHolderInflated) {
        mOnViewHolderInflate = onViewHolderInflate
    }

    fun asObservable(): Observable<SimpleViewHolder<DataType, LayoutBinding>> {
        return mPublishSubject
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): SimpleViewHolder<DataType, LayoutBinding> {
        val view = LayoutInflater.from(parent.context).inflate(mItem_layout, parent, false)
        if (mOnViewHolderInflate != null) mOnViewHolderInflate!!.onInflated(view, parent, viewType)
        return SimpleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleViewHolder<DataType, LayoutBinding>, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isNotEmpty()) {
            holder.item = dataSet[position]
            holder.payloads = payloads
            mPublishSubject.onNext(holder)
        } else super.onBindViewHolder(holder, position, payloads)

    }

    override fun onBindViewHolder(holder: SimpleViewHolder<DataType, LayoutBinding>,
                                  position: Int) {
        holder.item = dataSet[position]
        holder.payloads = null
        mPublishSubject.onNext(holder)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun updateDataSet(dataSet: List<DataType>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    fun updateDataSetWithOneEfectedItem(dataSet: List<DataType>, position: Int) {
        this.dataSet = dataSet
        notifyItemChanged(position)
    }

    fun notifyDataSetChanged(dataSet: List<DataType>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    fun notifyItemChanged(dataSet: List<DataType>, position: Int) {
        this.dataSet = dataSet
        notifyItemChanged(position)
    }

    fun notifyItemInserted(dataSet: List<DataType>, position: Int) {
        this.dataSet = dataSet
        notifyItemInserted(position)
    }

    fun notifyItemRemoved(dataSet: List<DataType>, position: Int) {
        this.dataSet = dataSet
        notifyItemRemoved(position)
    }

    fun notifyItemChanged(dataSet: List<DataType>?, position: Int, payloads: Any) {
        if (dataSet != null)
            this.dataSet = dataSet
        notifyItemChanged(position, payloads)
    }

}
