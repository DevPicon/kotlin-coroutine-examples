package pe.devpicon.android.mycoroutineexamples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_launch.setOnClickListener {
            executeJob()
        }
    }

    private fun executeJob() {
        CoroutineScope(Dispatchers.IO).launch {
            val result1 = getResult1()
            displayResultOnMainThread(result1)
            val result2 = getResult2()
            displayResultOnMainThread(result2)
            val result3 = getResult3()
            displayResultOnMainThread(result3)

        }
    }

    private suspend fun displayResultOnMainThread(result: String) {
        withContext(Dispatchers.Main) {
            displayResult(result)
        }
    }

    private suspend fun getResult1(): String {
        log("execute getResult1()")
        delay(1700) // ms
        return "Result 1"
    }

    private suspend fun getResult2(): String {
        log("execute getResult2()")
        delay(2500) // ms
        return "Result 2"
    }

    private suspend fun getResult3(): String {
        log("execute getResult3()")
        delay(1000) // ms
        return "Result 3"
    }

    private fun displayResult(result: String) {
        val newText = "${tv_result.text.toString()}$result\n"
        tv_result.text = newText
    }

    private fun log(message: String) {
        println("My Coroutines - [${Thread.currentThread().name}] message:$message")
    }
}
