package com.example.googlecalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var button0: Button? = null
    var button1: Button? = null
    var button2: Button? = null
    var button3: Button? = null
    var button4: Button? = null
    var button5: Button? = null
    var button6: Button? = null
    var button7: Button? = null
    var button8: Button? = null
    var button9: Button? = null
    var buttonAdd: Button? = null
    var buttonSub: Button? = null
    var buttonDiv: Button? = null
    var buttonMul: Button? = null
    var buttonClr: Button? = null
    var buttonEqual: Button? = null
    var buttonDel: Button? = null
    var buttonPercent: Button? = null
    var buttonSqrt: Button? = null
    var buttonDegree: Button? = null
    var buttonDot: Button? = null
    var edt1: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button0 = findViewById<Button>(R.id.button0)
        button1 = findViewById<Button>(R.id.button1)
        button2 = findViewById<Button>(R.id.button2)
        button3 = findViewById<Button>(R.id.button3)
        button4 = findViewById<Button>(R.id.button4)
        button5 = findViewById<Button>(R.id.button5)
        button6 = findViewById<Button>(R.id.button6)
        button7 = findViewById<Button>(R.id.button7)
        button8 = findViewById<Button>(R.id.button8)
        button9 = findViewById<Button>(R.id.button9)
        buttonAdd = findViewById<Button>(R.id.buttonAdd)
        buttonSub = findViewById<Button>(R.id.buttonSub)
        buttonMul = findViewById<Button>(R.id.buttonMul)
        buttonDiv = findViewById<Button>(R.id.buttonDiv)
        buttonEqual = findViewById<Button>(R.id.buttonEqual)
        buttonDel = findViewById<Button>(R.id.buttonDel)
        buttonClr = findViewById<Button>(R.id.buttonClr)
        buttonPercent = findViewById<Button>(R.id.buttonPercent)
        buttonDegree = findViewById<Button>(R.id.buttonDegree)
        buttonSqrt = findViewById<Button>(R.id.buttonSqrt)
        buttonDot = findViewById<Button>(R.id.buttonDot)
        edt1 = findViewById<TextView>(R.id.edt1)

        val buttonDigit = View.OnClickListener { v ->
            var text = edt1?.text.toString()
            val btn = v as Button
            if (text.length > 0 && (text[0] == 'C' || text[0] == 'I')) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (text.length != 0) {
                if (text.length == 1 && text[text.length - 1] == '0') {
                    text = btn.text.toString()
                } else {
                    if (text[text.length - 1] == '0' && (text[text.length - 2] < '0' ||
                                    text[text.length - 2] > '9') && text[text.length - 2] != '.') {
                        text = text.substring(0, text.length - 1) + btn.text.toString()
                    } else {
                        text += btn.text.toString()
                    }
                }
            } else {
                text += btn.text.toString()
            }
            edt1?.text = text
        }

        val oclButtonClr = View.OnClickListener { edt1?.text = "" }

        val oclButtonDel = View.OnClickListener {
            val text = edt1?.text.toString()
            if (text.length != 0) {
                if (text[0] == 'C' || text[0] == 'I') {
                    edt1?.text = ""
                }
                else edt1?.text = text.substring(0, text.length - 1)
            }
        }
        val oclButtonOperation = View.OnClickListener { v ->
            var text = edt1?.text.toString()
            if (text.length > 0 && (text[0] == 'C' || text[0] == 'I')) {
                edt1?.text = ""
                return@OnClickListener
            }
            val btn = v as Button
            if (text.length == 1 && text[0] == '-' && btn.text.toString() == "+") {
                text = ""
            }
            else if (text.length == 1 && text[0] == '-') {
                return@OnClickListener
            }
            if (text.length != 0 && text[text.length - 1] != '.') {
                val current = text[text.length - 1]
                if (current == '+' || current == '÷' || current == '×' || current == '%' || current == '^') {
                    text = text.substring(0, text.length - 1) + btn.text.toString()
                } else if (current == '-') {
                    if (text.length > 1 && (text[text.length - 2] == '+' || text[text.length - 2] == '+' || text[text.length - 2] == '%' || text[text.length - 2] == '×' || text[text.length - 2] == '^' || text[text.length - 2] == '÷')) return@OnClickListener
                    text = text.substring(0, text.length - 1) + btn.text.toString()
                }
                else if (current != '√') {
                    text += btn.text.toString()
                }
            }
            edt1?.text = text
        }

        val oclButtonMinus = View.OnClickListener { v ->
            var text = edt1?.text.toString()
            if (text.length > 0 && (text[0] == 'C' || text[0] == 'I')) {
                edt1?.text = ""
                return@OnClickListener
            }
            val btn = v as Button
            if (text.length == 0) {
                edt1?.text = "-"
            } else if (text[text.length - 1] == '-') {
                return@OnClickListener
            } else {
                if (text.length != 0 && text[text.length - 1] != '.') {
                    val current = text[text.length - 1]
                    if (current == '+' || current == '%') {
                        text = text.substring(0, text.length - 1) + btn.text.toString()
                    } else if (current == '÷' || current == '×' || current == '^') {
                        text += '-'
                    } else if (current == '√') {
                        text = "Cannot minus number under square root"
                    } else {
                        text += '-'
                    }
                }
                edt1?.text = text
            }
        }

        val oclButtonDot = View.OnClickListener {
            var hasDot = false
            var text = edt1?.text.toString()
            if (text.length > 0 && (text[0] == 'C' || text[0] == 'I')) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (text.length != 0) {
                if (text[text.length - 1] >= '0' && text[text.length - 1] <= '9') {
                    for (i in text.length - 1 downTo 0) {
                        if (text[i] == '.') hasDot = true
                        if (text[i] < '0' || text[i] > '9') break
                    }
                    if (!hasDot) {
                        text += '.'
                    }
                }
            }
            edt1?.text = text
        }
        val oclButtonSqrt = View.OnClickListener {
            var text = edt1?.text.toString()
            if (text.length > 0 && (text[0] == 'C' || text[0] == 'I')) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (text.length != 0 && text[text.length - 1] == '-') {
                text += "√"
            } else if (text.length != 0 && text[text.length - 1] != '.' &&
                    (text[text.length - 1] < '0' || text[text.length - 1] > '9')) {
                val current = text[text.length - 1]
                if (current != '√' && current != '.') text += '√'
            } else if (text.length == 0) {
                text += '√'
            }
            edt1?.text = text
        }

        val oclButtonEqual = View.OnClickListener {
            var last = 0.0
            var text = edt1?.text.toString()
            if (text.length > 0 && (text[0] == 'C' || text[0] == 'I')) {
                edt1?.text = ""
                return@OnClickListener
            }
            var opLast = 0.toChar()
            if (text.length != 0) opLast = text[text.length - 1]
            // after the operation will be bag
            if (text.length != 0 && (opLast == '+' || opLast == '-' || opLast == '√' || opLast == '%' || opLast == '÷' || opLast == '×' || opLast == '^')) {
                return@OnClickListener
            }
            var current = ""
            var lastOperation = '+'
            var hasSqrt = false
            var divideByZero = false
            if (text.length != 0) {
                if (text[text.length - 1] == '.') {
                    text = text.substring(0, text.length - 1)
                }
                text += '+'
                var newText: String
                var a = 0
                if (text[0] == '-') {
                    current = "-"
                    a = 1
                }
                var enter = false
                for (i in a until text.length) {
                    val cur = text[i]
                    if (cur == '√') {
                        hasSqrt = true
                    } else if (cur == '.' || cur >= '0' && cur <= '9') {
                        current += cur
                        enter = false
                    } else if (cur == '-' && enter) {
                        current += cur
                    } else {
                        enter = true
                        var have = current.toDouble()
                        if (hasSqrt) {
                            var ok = false
                            if (have < 0) {
                                have = -1 * have
                                ok = true
                            }
                            have = Math.sqrt(have)
                            if (ok) have = -1 * have
                        }
                        if (lastOperation == '+') {
                            last += have
                        } else if (lastOperation == '-') {
                            last -= have
                        } else if (lastOperation == '×') {
                            last *= have
                        } else if (lastOperation == '^') {
                            last = Math.pow(last, have)
                        } else if (lastOperation == '%') {
                            last = last * have / 100.0
                        } else if (lastOperation == '÷') {
                            var haveit = false
                            for (j in 0 until current.length) {
                                if (current[j] >= '1' && current[j] <= '9') {
                                    haveit = true
                                    break
                                }
                            }
                            if (haveit == false) {
                                divideByZero = true
                                break
                            } else {
                                last /= have
                            }
                        }
                        current = ""
                        lastOperation = cur
                    }
                }
                if (divideByZero) {
                    edt1?.text = "Cannot Divide by Zero"
                } else {
                    newText = java.lang.Double.toString(last)
                    var hasDots = false
                    for (j in newText.length - 1 downTo 0) {
                        if (newText[j] == '.') hasDots = true
                    }
                    while (hasDots && newText.length > 1 && (newText[newText.length - 1] == '.' || newText[newText.length - 1] == '0' || newText[newText.length - 1] == '√')) {
                        if (newText[newText.length - 1] == '.') {
                            newText = newText.substring(0, newText.length - 1)
                            break
                        }
                        newText = newText.substring(0, newText.length - 1)
                    }
                    if (newText == "-0") newText = "0"
                    edt1?.text = newText
                }
            }
        }


        button0?.setOnClickListener(buttonDigit)
        button1?.setOnClickListener(buttonDigit)
        button2?.setOnClickListener(buttonDigit)
        button3?.setOnClickListener(buttonDigit)
        button4?.setOnClickListener(buttonDigit)
        button5?.setOnClickListener(buttonDigit)
        button6?.setOnClickListener(buttonDigit)
        button7?.setOnClickListener(buttonDigit)
        button8?.setOnClickListener(buttonDigit)
        button9?.setOnClickListener(buttonDigit)

        buttonClr?.setOnClickListener(oclButtonClr)
        buttonDel?.setOnClickListener(oclButtonDel)

        buttonAdd?.setOnClickListener(oclButtonOperation)
        buttonSub?.setOnClickListener(oclButtonMinus)
        buttonMul?.setOnClickListener(oclButtonOperation)
        buttonDiv?.setOnClickListener(oclButtonOperation)
        buttonPercent?.setOnClickListener(oclButtonOperation)
        buttonDegree?.setOnClickListener(oclButtonOperation)

        buttonDot?.setOnClickListener(oclButtonDot)

        buttonSqrt?.setOnClickListener(oclButtonSqrt)

        buttonEqual?.setOnClickListener(oclButtonEqual)
    }
}