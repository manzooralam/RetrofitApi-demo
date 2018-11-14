package restaurent.manzoor.restaurentapp.slider

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller

class SliderView : ViewPager {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        setDurationScroll(DEFAULT_SCROLL_DURATION)
        this.setOnTouchListener { v, event -> true }
    }

    fun setDurationScroll(millis: Int) {
        try {
            val viewpager = ViewPager::class.java
            val scroller = viewpager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this, OwnScroller(context, millis))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    inner class OwnScroller(context: Context, durationScroll: Int) : Scroller(context, DecelerateInterpolator()) {

        private var durationScrollMillis = 1

        init {
            durationScrollMillis = durationScroll
        }

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, durationScrollMillis)
        }
    }

    companion object {
        val DEFAULT_SCROLL_DURATION = 200
        val SLIDE_MODE_SCROLL_DURATION = 1000
    }
}
