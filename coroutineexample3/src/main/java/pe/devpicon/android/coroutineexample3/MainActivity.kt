package pe.devpicon.android.coroutineexample3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_launch.setOnClickListener {
            // TODO
        }
    }

    private fun log(message: String) {
        println("${Thread.currentThread().name} example 03 message: $message")
    }
}
