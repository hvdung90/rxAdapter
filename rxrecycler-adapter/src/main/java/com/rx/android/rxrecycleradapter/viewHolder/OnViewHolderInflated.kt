package com.rx.android.rxrecycleradapter.viewHolder

import android.view.View
import android.view.ViewGroup

/**
 * Created by hvdung on 14/12/2015.
 */
abstract class OnViewHolderInflated {
    abstract fun onInflated(view: View, parent: ViewGroup, viewType: Int)
}