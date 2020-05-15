package pe.devpicon.android.coroutineexample4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun onSubmitClicked(user: String, passw: String) {
        viewModelScope.launch {
            _loginResult.value = withContext(ioDispatcher) {
                validateLogin(user, passw)
            }
        }
    }

    private fun validateLogin(user: String, passw: String): Boolean {
        Thread.sleep(2000)
        return user.isNotEmpty() && passw.isNotEmpty()
    }
}