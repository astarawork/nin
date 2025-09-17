package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private fun parseDouble(s: String): Double? = s.toDoubleOrNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputA = findViewById<EditText>(R.id.inputA)
        val inputB = findViewById<EditText>(R.id.inputB)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        fun calc(op: (Double, Double) -> Double) {
            val a = parseDouble(inputA.text.toString())
            val b = parseDouble(inputB.text.toString())
            if (a == null || b == null) {
                txtResult.text = "نتیجه: ورودی نامعتبر"
                return
            }
            try {
                val r = op(a, b)
                txtResult.text = "نتیجه: $r"
            } catch (e: ArithmeticException) {
                txtResult.text = "خطا: ${e.message}"
            }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { calc { a, b -> a + b } }
        findViewById<Button>(R.id.btnSub).setOnClickListener { calc { a, b -> a - b } }
        findViewById<Button>(R.id.btnMul).setOnClickListener { calc { a, b -> a * b } }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { calc { a, b ->
            if (b == 0.0) throw ArithmeticException("تقسیم بر صفر مجاز نیست")
            a / b
        } }
    }
}
