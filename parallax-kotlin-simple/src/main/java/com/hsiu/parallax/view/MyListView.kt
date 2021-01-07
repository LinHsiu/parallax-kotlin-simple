package com.hsiu.parallax.view


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class MyListView : View {
    private var nOffsetY = 0
    private var nResource = 0
    private var mBitmap: Bitmap? = null
    private var mPaint: Paint? = null
    var isTop = true

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(resources, nResource)
            mPaint = Paint()
        }

        if (mBitmap != null) {
            if (isTop) {
                val src = Rect(0, 0, Math.abs(mBitmap!!.width), mBitmap!!.height - nOffsetY)
                val dst = RectF(0f, nOffsetY.toFloat(), canvas.width.toFloat(), mBitmap!!.height.toFloat())
                canvas.drawBitmap(mBitmap!!, src, dst, mPaint)
            } else {
                val src = Rect(0, nOffsetY, Math.abs(mBitmap!!.width), mBitmap!!.height)
                val dst = RectF(0f, 0f, canvas.width.toFloat(), (mBitmap!!.height - nOffsetY).toFloat())
                canvas.drawBitmap(mBitmap!!, src, dst, mPaint)
            }
        }
    }

    fun setOffsetY(nOffsetY: Int) {
        this.nOffsetY = nOffsetY
        invalidate()
    }

    fun setResource(nResource: Int) {
        if (this.nResource != nResource) {
            mBitmap = null
        }
        this.nResource = nResource
        invalidate()
    }

}
