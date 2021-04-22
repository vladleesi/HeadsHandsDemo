package ru.handh.headshandsdemo.presentation.utils.extensions

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import ru.handh.headshandsdemo.R
import ru.handh.headshandsdemo.presentation.MainActivity


fun Fragment.setSupportActionBar(toolbar: Toolbar, title: CharSequence) {
    val parentActivity = (activity as? AppCompatActivity)
    parentActivity?.setSupportActionBar(toolbar)
    parentActivity?.supportActionBar?.setDisplayShowTitleEnabled(false)
    toolbar.title = title
    setHasOptionsMenu(true)
}

fun Fragment.showErrorToast() {
    Toast.makeText(context, getString(R.string.error_toast), Toast.LENGTH_LONG).show()
}

fun Fragment.getParentActivity() = (activity as MainActivity)