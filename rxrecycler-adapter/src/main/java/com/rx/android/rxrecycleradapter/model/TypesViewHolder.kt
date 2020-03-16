package com.rx.android.rxrecycleradapter.model

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by hvdung on 13/12/2015.
 */
open class TypesViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val viewDataBinding: ViewDataBinding? = DataBindingUtil.bind(itemView)

    var item: T? = null
}
