package com.hsiu.parallax.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.hsiu.parallax.R
import com.hsiu.parallax.view.MyListView


class MyListAdapter(var context: Context?, var nResource: IntArray?, var nScreenHeight: Int?, var nStatusHeight: Int?) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val holder: Holder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
            holder = Holder()
            holder.imgTest = view!!.findViewById(R.id.img_test)
            holder.rlRoot = view.findViewById(R.id.rl_root)
            holder.llContent = view.findViewById(R.id.ll_content)
            holder.imgTest!!.minimumHeight = nScreenHeight!! - this.nStatusHeight!!
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as Holder
        }

        if (position % 2 != 0) {
            holder.llContent!!.visibility = View.VISIBLE
            val layoutParams: RelativeLayout.LayoutParams
            when (position) {
                1 -> {
                    layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 1)
                    holder.rlRoot!!.layoutParams = layoutParams
                }
                3 -> {
                    layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 400)
                    holder.rlRoot!!.layoutParams = layoutParams
                }
                5 -> {
                    layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 1200)
                    holder.rlRoot!!.layoutParams = layoutParams
                }
                7 -> {
                    layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
                    holder.rlRoot!!.layoutParams = layoutParams
                }
            }
            holder.imgTest!!.setBackgroundColor(Color.WHITE)
            holder.imgTest!!.setResource(0)
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return position

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return nResource!!.size * 2
    }

    internal inner class Holder {
        var imgTest: MyListView? = null
        var rlRoot: RelativeLayout? = null
        var llContent: LinearLayout? = null
    }

}