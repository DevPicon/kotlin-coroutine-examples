package pe.devpicon.android.coroutineexample2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_launch.setOnClickListener {
            val coroutineScope = CoroutineScope(Dispatchers.Main)
            coroutineScope.launch {
                log("start launch")
                val product = getProductById("123")
                log(product.toString())
                log("end onClickListener()")
            }
        }
    }

    private suspend fun getProductById(productId: String): Product {
        delay(3000)
        return Product(productId, "Fideos")
    }

    private suspend fun getProductByIdStandard(productId: String): Product {
        Thread.sleep(3000)
        return Product(productId, "Fideos")
    }

    private fun getProductById(productId: String,
                               callback: (Product) -> Unit) {
        thread {
            Thread.sleep(3000)
            callback(Product(productId, "Fideos"))
        }
    }

    private fun log(message: String) {
        println("${Thread.currentThread().name} example 02: $message")
    }
}
