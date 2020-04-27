package com.example.googlecalculatorkotlin

import android.support.v7.app.AppCompatActivity
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.math.BigInteger

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
    var buttonSin: Button? = null
    var buttonCos: Button? = null
    var buttonTan: Button? = null
    var buttonLn: Button? = null
    var buttonLog: Button? = null
    var buttonSquare: Button? = null
    var buttonNRoot: Button? = null
    var buttonFactorial: Button? = null
    var buttonTenDegree: Button? = null
    var edt1: TextView? = null
    var isCos = false
    var isSin = false
    var isTan = false
    var isLog = false
    var isLn = false
    var isFactorial = false
    var isTenDegree = false
    var isSquare = false
    var isNRoot = false

    var operations = "+-√%÷×^"

    fun checker(text: String): Boolean {
        return text.length > 0 && (text[0] == 'd' || text[0] == 'm')
    }

    fun checkForReturn(text: String): Boolean {
        if (text.length > 0) {
            val current = text[text.length - 1]
            if (current == 'n' || current == 'g' || current == 's' || operations.indexOf(current) != -1) {
                return true
            }
        }
        return false
    }

    fun changeTrigionimicFunctions(text: String, trigio: String): String {
        var indexOfString = text.indexOf(trigio)
        var cur = ""
        if (text[indexOfString + trigio.length] == '-') {
            cur += '-'
            indexOfString++
        }
        for (i in indexOfString + trigio.length until text.length) {
            val s = text[i]
            cur += if (s >= '0' && s <= '9') {
                s
            } else if (s == '.') {
                s
            } else {
                break
            }
        }
        var t = cur.toDouble()
        if (trigio == "tan") t = Math.tan(t)
        else if (trigio == "sin") t = Math.sin(t)
        else if (trigio == "cos") t = Math.cos(t)
        else if (trigio == "10^") t = Math.pow(10.0, t)
        else if (trigio == "log") t = Math.log10(t)
        else if (trigio == "ln") t = Math.log(t)
        else if (trigio == "^1/") return cur
        indexOfString = text.indexOf(trigio)
        var newly = text.substring(0, indexOfString)
        newly += java.lang.Double.toString(t)
        newly += text.substring(indexOfString + trigio.length + cur.length)
        return newly
    }

    fun factorial(t: Double): BigInteger {
        var c: Int
        var i = BigInteger("1")
        var f = BigInteger("1")
        val m = BigInteger("-1")
        c = 1
        while (c <= t) {
            f = f.multiply(i)
            i = i.add(BigInteger.ONE)
            c++
        }
        return f
    }

    fun getReverseString(text: String): String {
        var ans = ""
        for (i in text.length - 1 downTo 0) {
            ans += text[i]
        }
        return ans
    }

    fun changeBeforeOperation(text: String, operation: String): String {
        val indexOfString = text.indexOf(operation)
        var cur = ""
        var start = -1
        for (i in indexOfString - 1 downTo 0) {
            if (text[i] >= '0' && text[i] <= '9') cur += text[i] else if (text[i] == '.') {
                cur += if (operation == "!") {
                    return "dangeroues"
                } else {
                    text[i]
                }
            } else {
                start = i
                break
            }
        }
        if (start == -1) start = 0 else start++
        val cur2 = getReverseString(cur)
        if (operation == "!" && cur2.length >= 3) {
            return "dangeroues"
        }
        if (operation == "^1/") {
            return cur2
        }
        val t = cur2.toDouble()
        var ans = -1.0
        var ansFac = BigInteger("-1")
        if (operation == "!") ansFac = factorial(t)
        else if (operation == "²") ans = Math.pow(t, 2.0)
        var newly = text.substring(0, start)
        if (ans != -1.0) newly += java.lang.Double.toString(ans)
        else if (ansFac.equals(-1) == false) {
            newly += ansFac.toString(10)
        }
        if (indexOfString + operation.length < text.length) newly += text.substring(indexOfString + operation.length)
        return newly
    }

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

        buttonSin = findViewById<Button>(R.id.buttonSin)
        buttonCos = findViewById<Button>(R.id.buttonCos)
        buttonTan = findViewById<Button>(R.id.buttonTan)
        buttonTenDegree = findViewById<Button>(R.id.buttonTenDegree)
        buttonNRoot = findViewById<Button>(R.id.buttonNRoot)
        buttonFactorial = findViewById<Button>(R.id.buttonFactorial)
        buttonSquare = findViewById<Button>(R.id.buttonSquare)
        buttonLn = findViewById<Button>(R.id.buttonLn)
        buttonLog = findViewById<Button>(R.id.buttonLog)
        if (savedInstanceState != null) {
            edt1?.text = savedInstanceState.getString("numberInput")
            isCos = savedInstanceState.getBoolean("isCos")
            isSin = savedInstanceState.getBoolean("isSin")
            isTan = savedInstanceState.getBoolean("isTan")
            isLog = savedInstanceState.getBoolean("isLog")
            isLn = savedInstanceState.getBoolean("isLn")
            isFactorial = savedInstanceState.getBoolean("isFactorial")
            isTenDegree = savedInstanceState.getBoolean("isTenDegree")
            isSquare = savedInstanceState.getBoolean("isSquare")
            isNRoot = savedInstanceState.getBoolean("isNRoot")
        }

        val buttonDigit = View.OnClickListener { v ->
            var text = edt1?.text.toString()
            val btn = v as Button
            if (checker(text)) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (text.length != 0 && (text[text.length - 1] == '!' || text[text.length - 1] == '²')) return@OnClickListener
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
            if (checker(text)) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (text.length != 0) {
                val cur = text[text.length - 1]
                if (cur == 'n' && text[text.length - 2] == 'l') {
                    isLn = false
                    edt1?.text = text.substring(0, text.length - 2)
                } else if (cur == 'n') {
                    val t = text.substring(text.length - 3)
                    if (t == "tan") {
                        isTan = false
                        edt1?.text = text.substring(0, text.length - 3)
                    } else if (t == "sin") {
                        isSin = false
                        edt1?.text = text.substring(0, text.length - 3)
                    }
                } else if (cur == 'g') {
                    val t = text.substring(text.length - 3)
                    if (t == "log") {
                        isLog = false
                        edt1?.text = text.substring(0, text.length - 3)
                    }
                } else if (cur == 's') {
                    val t = text.substring(text.length - 3)
                    if (t == "cos") {
                        isCos = false
                        edt1?.text = text.substring(0, text.length - 3)
                    }
                } else edt1?.text = text.substring(0, text.length - 1)
            }
        }

        val oclButtonOperation = View.OnClickListener { v ->
            var text = edt1?.text.toString()
            val btn = v as Button
            if (checker(text)) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (text.length == 1 && text[0] == '-' && btn.text.toString() == "+") {
                text = ""
            } else if (text.length == 1 && text[0] == '-') {
                return@OnClickListener
            }
            if (text.length != 0 && text[text.length - 1] != '.') {
                val current = text[text.length - 1]
                if (current == 'n' || current == 's' || current == 'g') {
                    return@OnClickListener
                }
                if (current == '+' || current == '÷' || current == '×' || current == '%' || current == '^') {
                    text = text.substring(0, text.length - 1) + btn.text.toString()
                } else if (current == '-') {
                    if (text.length > 1 && (text[text.length - 2] == '+' || text[text.length - 2] == '+' || text[text.length - 2] == '%' || text[text.length - 2] == '×' || text[text.length - 2] == '^' || text[text.length - 2] == '÷')) return@OnClickListener
                    text = text.substring(0, text.length - 1) + btn.text.toString()
                } else if (current != '√') {
                    text += btn.text.toString()
                }
            }
            edt1?.text = text
        }
        val oclButtonMinus = View.OnClickListener { v ->
            var text = edt1?.text.toString()
            if (checker(text)) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (text.length > 0 && (text[text.length - 1] == 'g' || text[text.length - 1] == 'n')) return@OnClickListener
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
                        text = "minus number under square root prohibited"
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
            if (checker(text)) {
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
            if (checker(text)) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (text.length > 0) {
                val current = text[text.length - 1]
                if (current == 'n' || current == 'g' || current == 's' || current == '²') {
                    return@OnClickListener
                }
            }
            if (text.length != 0 && text[text.length - 1] == '-') {
                text += "√"
            } else if (text.length != 0 && text[text.length - 1] == '!') return@OnClickListener
            else if (text.length != 0 && text[text.length - 1] != '.' &&
                    (text[text.length - 1] < '0' || text[text.length - 1] > '9')) {
                val current = text[text.length - 1]
                if (current != '√' && current != '.') text += '√'
            } else if (text.length == 0) {
                text += '√'
            }
            edt1?.text = text
        }
        val oclButtonTrigionometric = View.OnClickListener { v ->
            var text = edt1?.text.toString()
            val btn = v as Button
            if (checker(text)) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (text.length != 0 && (text[text.length - 1] == '!' || text[text.length - 1] == '²')) return@OnClickListener
            else if (text.length != 0 && (text[text.length - 1] == '-' || text[text.length - 1] == '+' || text[text.length - 1] == '÷' || text[text.length - 1] == '×' || text[text.length - 1] == '^')) {
                text += btn.text.toString()
            } else if (text.length == 0) {
                text += btn.text.toString()
            }
            edt1?.text = text
        }
        val oclButtonFactorial = View.OnClickListener {
            var text = edt1?.text.toString()
            if (text.length != 0) {
                for (i in text.length - 1 downTo 0) {
                    if (operations.indexOf(text[i]) != -1) break
                    else if (text[i] == '.') return@OnClickListener
                }
                if (text[text.length - 1] >= '0' && text[text.length - 1] <= '9') {
                    text += '!'
                }
            }
            edt1?.text = text
        }
        val oclButtonTenDegree = View.OnClickListener {
            var text = edt1?.text.toString()
            if (checker(text)) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (text.length != 0) {
                val current = text[text.length - 1]
                if (current >= '0' && current <= '9') return@OnClickListener
                if (current == '.') return@OnClickListener
                if (current == 'n' || current == 'g' || current == 's') return@OnClickListener
                if (current == '!') return@OnClickListener
                if (current == '²') return@OnClickListener
                text += "10^"
            } else text = "10^"
            edt1?.text = text
        }
        val oclButtonSquare = View.OnClickListener { v ->
            var text = edt1?.text.toString()
            val btn = v as Button
            if (checker(text)) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (text.length != 0) {
                val cur = text[text.length - 1]
                if (cur >= '0' && cur <= '9') text += "²"
            }
            edt1?.text = text
        }
        val oclButtonNRoot = View.OnClickListener { v ->
            var text = edt1?.text.toString()
            val btn = v as Button
            if (checker(text)) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (text.length != 0) {
                val cur = text[text.length - 1]
                if (cur == '²') return@OnClickListener
                if (cur >= '0' && cur <= '9') {
                    text += "^1/"
                }
            }
            edt1?.text = text
        }

        val oclButtonEqual = View.OnClickListener {
            var text = edt1?.text.toString()
            if (checker(text)) {
                edt1?.text = ""
                return@OnClickListener
            }
            if (checkForReturn(text)) {
                return@OnClickListener
            }
            isLog = if (text.indexOf("log") != -1) true
            else false
            isLn = if (text.indexOf("ln") != -1) true
            else false
            isTan = if (text.indexOf("tan") != -1) true
            else false
            isSin = if (text.indexOf("sin") != -1) true
            else false
            isCos = if (text.indexOf("cos") != -1) true
            else false
            isFactorial = if (text.indexOf("!") != -1) true
            else false
            isTenDegree = if (text.indexOf("10^") != -1) true
            else false
            isSquare = if (text.indexOf("²") != -1) true
            else false
            isNRoot = if (text.indexOf("^1/") != -1) true
            else false
            if (isLog) {
                while (text.indexOf("log") != -1) text = changeTrigionimicFunctions(text, "log")
            }
            if (isLn) {
                while (text.indexOf("ln") != -1) text = changeTrigionimicFunctions(text, "ln")
            }
            if (isTan) {
                while (text.indexOf("tan") != -1) text = changeTrigionimicFunctions(text, "tan")
            }
            if (isSin) {
                while (text.indexOf("sin") != -1) text = changeTrigionimicFunctions(text, "sin")
            }
            if (isCos) {
                while (text.indexOf("cos") != -1) text = changeTrigionimicFunctions(text, "cos")
            }
            if (isTenDegree) {
                while (text.indexOf("10^") != -1) text = changeTrigionimicFunctions(text, "10^")
            }
            if (isFactorial) {
                while (text.indexOf("!") != -1) {
                    text = changeBeforeOperation(text, "!")
                    if (text == "dangeroues") {
                        edt1?.text = text
                        return@OnClickListener
                    }
                }
            }
            if (isSquare) {
                while (text.indexOf("²") != -1) {
                    text = changeBeforeOperation(text, "²")
                }
            }
            if (isNRoot) {
                val a = text.indexOf("^1/")
                val cur2 = changeBeforeOperation(text, "^1/")
                var t = cur2.toDouble()
                val cur = changeTrigionimicFunctions(text, "^1/")
                var w = cur.toDouble()
                w = 1 / w
                t = Math.pow(t, w)
                var newly = ""
                if (a != cur2.length) newly = text.substring(0, a - cur2.length)
                newly += java.lang.Double.toString(t)
                newly += text.substring(cur2.length + 3 + cur.length)
                text = newly
            }
            var last = 0.0
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
                if (text[a] == '-') {
                    current = "-"
                    a++
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
                    edt1?.text = "divide by Zero prohibited"
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
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            buttonFactorial?.setOnClickListener(oclButtonFactorial)
            buttonCos?.setOnClickListener(oclButtonTrigionometric)
            buttonSin?.setOnClickListener(oclButtonTrigionometric)
            buttonLog?.setOnClickListener(oclButtonTrigionometric)
            buttonLn?.setOnClickListener(oclButtonTrigionometric)
            buttonTan?.setOnClickListener(oclButtonTrigionometric)
            buttonTenDegree?.setOnClickListener(oclButtonTenDegree)
            buttonSquare?.setOnClickListener(oclButtonSquare)
            buttonNRoot?.setOnClickListener(oclButtonNRoot)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("numberInput", edt1?.text.toString())
        outState.putBoolean("isCos", isCos)
        outState.putBoolean("isSin", isSin)
        outState.putBoolean("isTan", isTan)
        outState.putBoolean("isLog", isLog)
        outState.putBoolean("isLn", isLn)
        outState.putBoolean("isFactorial", isFactorial)
        outState.putBoolean("isTenDegree", isTenDegree)
        outState.putBoolean("isSquare", isSquare)
        outState.putBoolean("isNRoot", isNRoot)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString("numberInput")
        savedInstanceState.getString("isCos")
        savedInstanceState.getString("isSin")
        savedInstanceState.getString("isTan")
        savedInstanceState.getString("isLog")
        savedInstanceState.getString("isLn")
        savedInstanceState.getString("isFactorial")
        savedInstanceState.getString("isTenDegree")
        savedInstanceState.getString("isSquare")
        savedInstanceState.getString("isNRoot")
    }
}
