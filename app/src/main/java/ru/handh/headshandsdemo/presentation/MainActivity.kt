package ru.handh.headshandsdemo.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import ru.handh.headshandsdemo.R
import ru.handh.headshandsdemo.presentation.fragments.AuthFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fcv_content, AuthFragment.newInstance(), AuthFragment.TAG)
            .commit()
    }
}

fun Fragment.setSupportActionBar(toolbar: Toolbar, title: CharSequence) {
    val parentActivity = (activity as? AppCompatActivity)
    parentActivity?.setSupportActionBar(toolbar)
    parentActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
    toolbar.title = title
    setHasOptionsMenu(true)
}


fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showTooltip(tooltipText: String? = null) {

    val popupView = View.inflate(context, R.layout.popup_tooltip, null)
    popupView.findViewById<TextView>(R.id.tv_tooltip_text).text = tooltipText

    val popupWindow = PopupWindow(
        popupView,
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT,
        true
    )
    popupWindow.elevation = 4f
    popupWindow.showAsDropDown(this)
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.onTouchDrawableEnd(callback: (editText: EditText) -> Unit) {
    setOnTouchListener(View.OnTouchListener { _, event ->
        val drawableRight = 2
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.rawX >= right - compoundDrawables[drawableRight].bounds.width()
            ) {
                callback(this)
                return@OnTouchListener true
            }
        }
        false
    })
}