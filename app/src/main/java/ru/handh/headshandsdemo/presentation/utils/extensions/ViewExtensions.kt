package ru.handh.headshandsdemo.presentation.utils.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import ru.handh.headshandsdemo.R


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

fun TextInputLayout.checkInput(
    condition: Boolean,
    errorText: String
): Boolean {
    editText?.addTextChangedListener {
        error = null
    }

    if (!condition) {
        error = errorText
        return !condition
    }
    return condition
}

fun View.showSnackbar(text: String) {
    Snackbar.make(
        this,
        text,
        Snackbar.LENGTH_LONG
    ).show()
}