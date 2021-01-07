package com.hsiu.parallax.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.hsiu.parallax.view.MyHorizonView
import com.hsiu.parallax.R
import kotlinx.android.synthetic.main.activity_horizon.*
import kotlin.math.abs

class HorizonActivity : AppCompatActivity(){
    var nScreenWidth = 0
    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nScreenWidth = displayMetrics.widthPixels
        setContentView(R.layout.activity_horizon)
        val nResource = intArrayOf(R.drawable.background_1, R.drawable.background_2, R.drawable.background_3, R.drawable.background_4)
        supportActionBar!!.hide()
        linearLayoutManager = LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL, false)
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.adapter = MainAdapter(nResource)
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()

                if (recyclerView!!.getChildAt(0) != null) {
                    if (firstVisibleItem % 2 == 0) {
                        val hiView = recyclerView.getChildAt(0).findViewById<MyHorizonView>(R.id.image_horizon)
                        hiView.setResource(nResource[firstVisibleItem / 2])
                        hiView.isLeft = true
                        hiView.setOffsetX(abs(recyclerView.getChildAt(0).left))

                        if (recyclerView.getChildAt(2) != null) {
                            val hiView2 = recyclerView.getChildAt(2).findViewById<MyHorizonView>(R.id.image_horizon)
                            hiView2.setResource(nResource[firstVisibleItem / 2 + 1])
                            hiView2.isLeft = false
                            hiView2.setOffsetX(abs(recyclerView.getChildAt(2).left))
                        }
                    } else {
                        if (recyclerView.getChildAt(1) != null) {
                            val hiView = recyclerView.getChildAt(1).findViewById<MyHorizonView>(R.id.image_horizon)
                            hiView.setResource(nResource[(firstVisibleItem + 1) / 2])
                            hiView.isLeft = false
                            hiView.setOffsetX(abs(recyclerView.getChildAt(1).left))
                        }
                    }
                }

            }

        })
    }

    class MainAdapter(private val nResource: IntArray?) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hrizon, parent, false)

            return ViewHolder(view)
        }

        override fun getItemCount(): Int = nResource!!.size * 2

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            if (position % 2 != 0){
                holder.image!!.visibility = View.GONE
                holder.rlRoot
                val layoutParams = RelativeLayout.LayoutParams(1, RelativeLayout.LayoutParams.MATCH_PARENT)
                holder.rlRoot!!.layoutParams = layoutParams
            }else{
                holder.image!!.visibility = View.VISIBLE
                val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
                holder.rlRoot!!.layoutParams = layoutParams
            }
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val image = itemView?.findViewById<MyHorizonView>(R.id.image_horizon)
            val rlRoot = itemView?.findViewById<RelativeLayout>(R.id.rl_root)
        }
    }

}