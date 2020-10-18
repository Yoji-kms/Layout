package com.yoji.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import java.lang.NumberFormatException
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val likesCheckBox:CheckBox by lazy{ findViewById(R.id.likesCheckBoxId) }
    private val shareCounterTxtView:TextView by lazy { findViewById(R.id.shareCounterTxtViewId) }
    private val watchesCounterTxtView:TextView by lazy { findViewById(R.id.watchesCounterTxtViewId) }
    private val resetCountersBtn:Button by lazy { findViewById(R.id.resetCountersBtnId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun Int.toFormattedString() = when (this) {
        in 0..999 -> this.toString()
        in 1_000..9_999 -> this.roundToThousandsWithOneDecimal().toString() + "K"
        in 10_000..999_999 -> (this / 1_000).toString() + "K"
        in 1_000_000..9_999_999 -> (this / 1_000).roundToThousandsWithOneDecimal().toString() + "M"
        in 10_000_000..999_999_999 -> (this / 1_000_000).toString() + "M"
        in 1_000_000_000..Int.MAX_VALUE -> (this / 1_000_000).roundToThousandsWithOneDecimal()
                .toString() + "B"
        else -> "0"
    }

    private fun Int.roundToThousandsWithOneDecimal(): Double =
            (this / 100).toDouble() / 10

    private fun init (){
        initCheckBox()
        initTxtView(shareCounterTxtView)
        initTxtView(watchesCounterTxtView)
        initBtn()
    }

    private fun initTxtView(txtView: TextView) {
        txtView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    txtView.text = s.toString().toInt().toFormattedString()
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        txtView.text = Random.nextInt(from = 0, until = Int.MAX_VALUE).toString()
    }

    private fun initCheckBox() {
        likesCheckBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try {
                    likesCheckBox.text = s.toString().toInt().toFormattedString()
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        likesCheckBox.text = Random.nextInt(from = 0, until = Int.MAX_VALUE).toString()
    }

    private fun initBtn(){
        resetCountersBtn.setOnClickListener {
            likesCheckBox.text = Random.nextInt(from = 0, until = Int.MAX_VALUE).toString()
            shareCounterTxtView.text = Random.nextInt(from = 0, until = Int.MAX_VALUE).toString()
            watchesCounterTxtView.text = Random.nextInt(from = 0, until = Int.MAX_VALUE).toString()
        }
    }
}