package com.leon.train

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.leon.myapplication.horizontalpicker.SliderLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var tag = 1
    var adapter: PickerAdapter? = null
    var listData = createData(tag.toString(), 20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        val layoutManager = SliderLayoutManager(this, rv)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        layoutManager.reverseLayout = true
        layoutManager.callback = object :
            SliderLayoutManager.OnItemSelectedListener {
            override fun onItemSelected(layoutPosition: Int) {
                Toast.makeText(
                    this@MainActivity,
                    "Selected value : $layoutPosition",
                    Toast.LENGTH_SHORT
                ).show()
                for (item in listData) {
                    item.isSelect = false
                }
                listData[layoutPosition].isSelect = true
                adapter!!.notifyDataSetChanged()
                //加载更多
                if (layoutPosition >= listData.size - 3) {
                    loadMore()
                }
            }
        }
        adapter = PickerAdapter(this, listData, rv)
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv)
        rv.layoutManager = layoutManager
        rv.adapter = adapter
    }

    fun loadMore() {
        tag++
        listData.addAll(createData(tag.toString(), 10))
    }

    fun createData(tag: String, count: Int): MutableList<ItemData> {
        val data: MutableList<ItemData> = ArrayList()
        for (i in 0 until count) {
            val item = ItemData()
            item.content = i.toString() + "-" + tag
            data.add(item)
        }
        return data
    }
}