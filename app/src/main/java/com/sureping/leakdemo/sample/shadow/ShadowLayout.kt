package com.sureping.leakdemo.sample.shadow

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.ViewGroup
import android.widget.FrameLayout
import android.R.attr.shadowColor
import android.R.attr.shadowRadius
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.view.View


class ShadowLayout : FrameLayout {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    private var mShadowPaint: Paint = Paint()

    init {
        mShadowPaint.style = Paint.Style.FILL
        mShadowPaint.isAntiAlias = true
        mShadowPaint.color =  Color.GREEN
        mShadowPaint.maskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.OUTER)
        mShadowPaint.setShadowLayer(5f, 15f, 5f,  Color.GREEN)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        val child = getChildAt(0)
        val left = child.left
        val top = child.top
        val right = child.right
        val bottom = child.bottom

        val paint3 = Paint()
        paint3.color = Color.RED
        paint3.style = Paint.Style.FILL
        paint3.maskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.OUTER)
        canvas?.drawCircle(300f, 400f, 50f, paint3)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setLayerType(LAYER_TYPE_SOFTWARE, null);//对单独的View在运行时阶段禁用硬件加速
            canvas?.drawRoundRect(left.toFloat(), top.toFloat(), right.toFloat(),
                bottom.toFloat(), 5f, 5f, mShadowPaint)
            Log.v("t","having running")
        }
        setLayerType(LAYER_TYPE_SOFTWARE, null);//对单独的View在运行时阶段禁用硬件加速
//        canvas?.drawText("OUTER", 0f, 400f, mShadowPaint)

        super.dispatchDraw(canvas)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }
}