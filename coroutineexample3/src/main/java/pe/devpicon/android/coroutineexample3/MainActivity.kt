package pe.devpicon.android.coroutineexample3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_launch.setOnClickListener {
            val scope = CoroutineScope(Dispatchers.Main)
            val job1 = scope.launch {
                delay(2000)
                log("This is my first coroutine")
                delay(2000)
            }

            job1.invokeOnCompletion {
                val message = it?.message ?: "Esta corutina terminó sin problemas"
                log(message)
            }

            val scope2 = CoroutineScope(Dispatchers.Main + job1)
            val job2 = scope2.launch {
                delay(3000)
                cancel(CancellationException("Cancelling the second coroutine!"))
                log("This is my second coroutine")
                delay(2000)
            }

            val scope3 = CoroutineScope(Dispatchers.IO + job1)
            val job3 = scope3.launch {
                delay(3000)
                log("This is my third coroutine")
                delay(3000)
            }
        }
    }

    private fun log(message: String) {
        println("${Thread.currentThread().name} example 03 message: $message")
    }
}
