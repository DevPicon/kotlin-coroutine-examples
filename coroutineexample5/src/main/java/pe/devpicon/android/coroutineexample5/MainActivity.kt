package pe.devpicon.android.coroutineexample5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch.setOnClickListener {


        }
    }


    private fun log(message: String) {
        println("${MainActivity::class.java.simpleName} ${Thread.currentThread().name} message: $message")
    }
}