package pe.devpicon.android.coroutineexample5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val trustUserId = "0001"
    private val anyUserId = "0002"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch.setOnClickListener {

            val coroutineScope = CoroutineScope(Dispatchers.Main)

            coroutineScope.launch {

                val result1 = async { getAccountFromRemote(trustUserId) }
                val result2 = async { getDebtFromRemote(trustUserId) }
                isATrustCustomer(result1.await(), result2.await())

            }

        }
    }

    private fun isATrustCustomer(accountResult: Boolean, debtResult: Boolean) {
        log("Result ${accountResult && debtResult}")
    }

    private suspend fun getAccountFromRemote(userId: String): Boolean {
        log("Received on getAccountFromRemote() id $userId")
        delay(4000)
        return true
    }

    private suspend fun getDebtFromRemote(userId: String): Boolean {
        log("Received on getDebtFromRemote() id $userId")
        delay(2000)
        return false
    }

    private fun log(message: String) {
        println("${MainActivity::class.java.simpleName} ${Thread.currentThread().name} message: $message")
    }
}