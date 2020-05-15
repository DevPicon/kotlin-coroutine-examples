package pe.devpicon.android.coroutineexample5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {

                for (person in getPersonList()) {
                    val personId = insertPerson(person)
                    log("personId=$personId")
                    if (personId < 0) {
                        continue
                    }

                    val xId = insertRelationship(person, personId)
                    log(xId)
                }


            }
        }
    }

    private suspend fun insertRelationship(person: Person, personId: Long): String {
        delay(3000)
        return person.name
    }

    private suspend fun insertPerson(person: Person): Long {
        delay(1000)
        return person.id
    }

    private fun log(message: String) {
        println("${MainActivity::class.java.simpleName} ${Thread.currentThread().name} message: $message")
    }
}

fun getPersonList() = listOf(Person(1, "A"),
        Person(2, "B"),
        Person(3, "C"),
        Person(4, "D"),
        Person(5, "E"))

class Person(val id: Long, val name: String)