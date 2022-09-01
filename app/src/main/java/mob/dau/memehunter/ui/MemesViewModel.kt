package mob.dau.memehunter.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mob.dau.memehunter.network.MemeItem
import mob.dau.memehunter.network.MemesApi
import mob.dau.memehunter.network.MemesApi.retrofitService

private const val TAG = "MemesViewModel"

enum class Status {
    LOADING, SUCCESS, FAILURE
}

class MemesViewModel : ViewModel() {

    private val _memes = MutableLiveData<List<MemeItem>>()
    val memes: LiveData<List<MemeItem>> = _memes

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> = _status

    init {
        getMemes()
    }

    private fun getMemes() {
        viewModelScope.launch {
            _status.value = Status.LOADING
            try {
                _memes.value = MemesApi.retrofitService.fetchMemes().data.memes
                _status.value = Status.SUCCESS
            } catch (e: Exception) {
                _memes.value = listOf()
                _status.value = Status.FAILURE
            }
        }
    }
}