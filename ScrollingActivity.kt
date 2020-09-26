package com.example.scrollingdemo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    private var inAnimator: ObjectAnimator? = null
    private var outAnimator: ObjectAnimator? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)


        nested_sv.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            animator(btn_stop)
        }
    }

    fun animator(child: View) {
        val isVisible = Util.checkIsVisible(this, btn1111)
        if (isVisible) {
            // 隐藏
            if (outAnimator == null) {
                outAnimator =
                    ObjectAnimator.ofFloat(child, "translationY", 0f, child.height.toFloat())
                outAnimator?.setDuration(200)
            }
            if (!outAnimator!!.isRunning() && child.translationY <= 0) {
                outAnimator?.start()
            }
        } else {
            // 显示
            if (inAnimator == null) {
                inAnimator =
                    ObjectAnimator.ofFloat(child, "translationY", child.getHeight().toFloat(), 0f)
                inAnimator?.duration = 200
            }
            if (!inAnimator!!.isRunning && child.translationY >= child.height) {
                inAnimator?.start()
            }
        }

    }

}
