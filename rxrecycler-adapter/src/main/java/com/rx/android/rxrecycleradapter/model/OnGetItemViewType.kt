package com.rx.android.rxrecycleradapter.model

abstract class OnGetItemViewType {
    abstract fun getItemViewType(position: Int): Int
}