package com.rx.android.rxrecyclerexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.rx.android.rxrecycleradapter.RxDataSource
import com.rx.android.rxrecyclerexample.databinding.ActivityMain2Binding
import com.rx.android.rxrecyclerexample.databinding.ItemLayoutBinding

class Main2Activity : AppCompatActivity() {
    var count: Int = 1
var handle=Handler()
    var rxDataSource:RxDataSource<ItemLayoutBinding,String>?=null
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mActivityMainBinding = DataBindingUtil.setContentView<ActivityMain2Binding>(
                this,
                R.layout.activity_main2
        )
        mActivityMainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        val dataSet = mutableListOf<String>()


        for (i in 0..20) {
            dataSet.add("Data $i")
        }


        rxDataSource = RxDataSource(R.layout.item_layout, dataSet)
        rxDataSource?.bindRecyclerView(mActivityMainBinding.recyclerView)

        rxDataSource?.asObservable()
                ?.subscribe {
                    val binding = it.viewDataBinding ?: return@subscribe
                    binding.textViewItem.text = it.item
                }

        mActivityMainBinding.recyclerView.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                count++
                handle.postDelayed({
                    val dataSe = mutableListOf<String>()
                    for (i in rxDataSource?.dataSet?.size!!..20*count) {
                        dataSe.add("Data $i")
                    }
                    rxDataSource?.updateDataSetMore(dataSe)
                    rxDataSource?.updateAdapter()
                    mActivityMainBinding.recyclerView.loadMoreComplete()

                },3000)

            }

            override fun onRefresh() {

            }

        })

        mActivityMainBinding.recyclerView
                .defaultRefreshHeaderView // get default refresh header view
                .setRefreshTimeVisible(true)  // make refresh time visible,false means hiding
        mActivityMainBinding.recyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin)
    }
}
