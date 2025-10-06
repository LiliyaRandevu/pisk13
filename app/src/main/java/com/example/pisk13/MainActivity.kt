package com.example.pisk13

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var helloText: TextView
    private lateinit var dynamicText: TextView
    private lateinit var changeTextBtn: Button
    private lateinit var imageChangeBtn: ImageButton
    private lateinit var quizGroup: RadioGroup
    private lateinit var checkBtn: Button
    private lateinit var resultText: TextView
    private lateinit var blinkText: TextView

    private val texts = listOf(
        "1",
        "2",
        "3",
        "4",
        "Ты в заложниках"
    )

    private val changeVariants = listOf("Мяу", "Миау", "Миу", "Мау", "Мем")
    private var index = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloText = findViewById(R.id.helloText)
        dynamicText = findViewById(R.id.dynamicText)
        changeTextBtn = findViewById(R.id.changeTextBtn)
        imageChangeBtn = findViewById(R.id.imageChangeBtn)
        quizGroup = findViewById(R.id.quizGroup)
        checkBtn = findViewById(R.id.checkAnswerBtn)
        resultText = findViewById(R.id.resultText)
        blinkText = findViewById(R.id.blinkText)

        val textViews = listOf(
            findViewById<TextView>(R.id.text1),
            findViewById<TextView>(R.id.text2),
            findViewById<TextView>(R.id.text3),
            findViewById<TextView>(R.id.text4),
            findViewById<TextView>(R.id.text5)
        )

        texts.forEachIndexed { i, msg ->
            handler.postDelayed({
                textViews[i].text = msg
                textViews[i].setTextColor(Color.MAGENTA)
            }, (i + 1) * 1000L)
        }

        val clickAction = {
            index = (index + 1) % changeVariants.size
            dynamicText.text = changeVariants[index]
        }
        changeTextBtn.setOnClickListener { clickAction() }
        imageChangeBtn.setOnClickListener { clickAction() }

        val correct = R.id.optionKotlin
        checkBtn.setOnClickListener {
            val selected = quizGroup.checkedRadioButtonId
            if (selected == -1) {
                resultText.text = "Выбери вариант, заложник"
                resultText.setTextColor(Color.BLACK)
            } else if (selected == correct) {
                resultText.text = "Верно?"
                resultText.setTextColor(Color.GREEN)
            } else {
                resultText.text = "Неверно блина"
                resultText.setTextColor(Color.RED)
            }
        }

        var visible = true
        handler.post(object : Runnable {
            override fun run() {
                blinkText.visibility =
                    if (visible) TextView.INVISIBLE else TextView.VISIBLE
                visible = !visible
                handler.postDelayed(this, 800)
            }
        })
    }
}
