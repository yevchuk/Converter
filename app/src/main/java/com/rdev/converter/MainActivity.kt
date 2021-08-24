package com.rdev.converter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnKeyListener, View.OnClickListener {
    private lateinit var model: MainActivityModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProvider(this)[MainActivityModel::class.java]
        model.getResult().observe(this,
            { data ->
                if (data?.error == null) {
                    tvResult.text = data.toString()
                } else {
                    editText.error = data.error
                }
            })

        editText.filters = arrayOf(InputFilter.LengthFilter(5))
        editText.setOnKeyListener(this)
        cl.setOnClickListener(this)
    }

    override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
        if (p2?.action == KeyEvent.ACTION_DOWN && p1 == KeyEvent.KEYCODE_ENTER) {
            model.calculate(editText.text.toString())
            return true
        }
        return false
    }

    override fun onClick(p0: View?) {
        hideKeyboard(currentFocus ?: View(this))
        editText.clearFocus()
        model.calculate(editText.text.toString())
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}