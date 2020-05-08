package pe.devpicon.android.coroutineexample3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_launch.setOnClickListener {
            val coroutineScope = CoroutineScope(Dispatchers.Main)
            val handlerException = CoroutineExceptionHandler { coroutineContext, throwable ->
                run {
                    log("$coroutineContext - ${throwable.message}")
                }
            }
            val job1 = coroutineScope.launch(start = CoroutineStart.LAZY, context = handlerException) {
                delay(500)
                log("Executing my job 1")
                delay(500)
            }

            job1.invokeOnCompletion { throwable ->
                log("Complete my job 1")
                throwable?.let {
                    log("There is a throwable object!")
                    when (it) {
                        is CancellationException -> {
                            log(it.message ?: "Without message")
                        }
                        is IllegalStateException -> {
                            log("it was an IllegalStateException with this message: ${it.message}")
                        }
                    }
                }
            }

            val childCoroutineScope = CoroutineScope(Dispatchers.Main + job1)
            val job2 = childCoroutineScope.launch {
                delay(500)
                //val cause = CancellationException("I want to cancel my job 1")
                //job1.cancel(cause)
                log("Executing my job 2")
                throw IllegalStateException("I just wanna cut this execution")
                //job1.join()
                delay(3000)
            }

            val secondChildCoroutineScope = CoroutineScope(Dispatchers.Main + job2)
            val job3 = childCoroutineScope.launch {
                delay(1500)
                log("Executing my job 3")
                delay(3000)
            }
        }
    }

    private fun log(message: String) {
        println("Example 03 [${Thread.currentThread().name}] message: $message")
    }
}
