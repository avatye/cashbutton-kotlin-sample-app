package com.avatye.app.sample.kotlin_cashbutton_sample

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView

class HeaderView : RelativeLayout {

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet? = null) : super(context, attrs) {
        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HeaderView)
        initializeViews(typedArray)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        val typedArray =
            getContext().obtainStyledAttributes(attrs, R.styleable.HeaderView, defStyle, 0)
        initializeViews(typedArray)
    }

    private fun initializeViews(typedArray: TypedArray) {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.component_common_header_layout, this)


        // visible
        findViewById<RelativeLayout>(R.id.ly_header_close).visibility = GONE
        findViewById<RelativeLayout>(R.id.ly_header_back)?.visibility = GONE

        //set TypedArray attributes
        findViewById<TextView>(R.id.tv_header_title).text = typedArray.getString(R.styleable.HeaderView_text)
        typedArray.recycle()
    }

    fun setBack(listener: OnClickListener?) {
        findViewById<RelativeLayout>(R.id.ly_header_back).visibility = VISIBLE
        findViewById<RelativeLayout>(R.id.ly_header_back).setOnClickListener(listener)
    }
}