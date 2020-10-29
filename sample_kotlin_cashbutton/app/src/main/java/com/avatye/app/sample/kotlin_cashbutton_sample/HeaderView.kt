package com.avatye.app.sample.kotlin_cashbutton_sample

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.component_common_header_layout.view.*

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
        ly_header_close?.visibility = GONE
        ly_header_back?.visibility = GONE

        //set TypedArray attributes
        tv_header_title?.text = typedArray.getString(R.styleable.HeaderView_text)
        typedArray.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setTitle(@StringRes stringRes: Int) {
        tv_header_title.setText(stringRes)
    }

    fun setTitle(title: String) {
        tv_header_title.text = title
    }

    fun setClose(listener: OnClickListener?) {
        ly_header_close.visibility = VISIBLE
        ly_header_close.setOnClickListener(listener)
    }

    fun setBack(listener: OnClickListener?) {
        ly_header_back.visibility = VISIBLE
        ly_header_back.setOnClickListener(listener)
    }
}