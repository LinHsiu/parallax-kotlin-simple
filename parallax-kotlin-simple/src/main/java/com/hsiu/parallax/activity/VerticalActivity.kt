package com.hsiu.parallax.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.widget.AbsListView
import com.hsiu.parallax.adapter.MyListAdapter
import com.hsiu.parallax.view.MyListView
import com.hsiu.parallax.R
import kotlinx.android.synthetic.main.activity_vertical.*
import kotlin.math.abs

class VerticalActivity : AppCompatActivity() {
    private var nScreenHeight = 0
    private var statusBarHeight = 0
    private val nResource = intArrayOf(R.drawable.background_1, R.drawable.background_2, R.drawable.background_3, R.drawable.background_4)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nScreenHeight = displayMetrics.heightPixels

        val resourceID = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceID > 0){
            statusBarHeight = resources.getDimensionPixelSize(resourceID)
        }

        list_vertical.adapter = MyListAdapter(this, nResource, nScreenHeight, statusBarHeight)
        list_vertical.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {

            }

            override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                if (view.getChildAt(0) != null) {
                    if (firstVisibleItem % 2 == 0) {
                        val hiView = view.getChildAt(0).findViewById<MyListView>(R.id.img_test)
                        hiView.setResource(nResource[firstVisibleItem / 2])
                        hiView.isTop = true
                        hiView.setOffsetY(abs(view.getChildAt(0).top))

                        if (view.getChildAt(2) != null) {
                            val hiView2 = view.getChildAt(2).findViewById<MyListView>(R.id.img_test)
                            hiView2.setResource(nResource[firstVisibleItem / 2 + 1])
                            hiView2.isTop = false
                            hiView2.setOffsetY(abs(view.getChildAt(2).top))
                        }
                    } else {
                        if (view.getChildAt(1) != null) {
                            val hiView = view.getChildAt(1).findViewById<MyListView>(R.id.img_test)
                            hiView.setResource(nResource[(firstVisibleItem + 1) / 2])
                            hiView.isTop = false
                            hiView.setOffsetY(abs(view.getChildAt(1).top))
                        }
                    }
                }
            }
        })

        supportActionBar!!.hide()
    }
}
