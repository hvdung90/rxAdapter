package com.rx.android.rxrecycleradapter

import android.content.Context
import android.util.AttributeSet
import com.jcodecraeer.xrecyclerview.XRecyclerView

class RXrecyclerview : XRecyclerView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)
}