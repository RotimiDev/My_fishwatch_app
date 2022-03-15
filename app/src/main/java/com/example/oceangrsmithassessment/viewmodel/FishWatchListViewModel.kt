package com.example.oceangrsmithassessment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oceangrsmithassessment.data.model.FishWatch
import com.example.oceangrsmithassessment.repository.FishAppRepository
import com.example.oceangrsmithassessment.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class FishWatchListViewModel @Inject constructor(
    private val repository: FishAppRepository
) : ViewModel() {

    private var _fishWatchList = MutableLiveData<Resource<Array<FishWatch>>>()
    val fishWatchList: LiveData<Resource<Array<FishWatch>>> get() = _fishWatchList

    private var cachedFishWatch = MutableLiveData<Resource<Array<FishWatch>>>()
    private var isSearchStarting = true
    var isSearching = MutableStateFlow(false)

    init {
        loadPaginatedFishWatchList()
    }

    fun loadPaginatedFishWatchList() = viewModelScope.launch {
        try {
            _fishWatchList.postValue(Resource.Loading)
            val array = repository.getFishWatch()
            _fishWatchList.postValue(handleFishData(array))
        } catch (e: Exception){

        }

    }

    //Search Fish
    fun searchFishWatch(query: String)  {
       if (isSearchStarting) {
           cachedFishWatch.value = _fishWatchList.value
           isSearchStarting = false
       }

        val fishToSearch = if (isSearchStarting) {
            fishWatchList.value
        } else {
            cachedFishWatch.value
        }

        viewModelScope.launch {
            if (query.isEmpty()) {
                _fishWatchList.value = cachedFishWatch.value
                isSearching.value = false
                isSearchStarting = true
                return@launch
            } else {
                val results = fishToSearch?.data?.filter {
                    it.speciesName.contains(query.trim(), ignoreCase = true)
                }

                }
            if (isSearchStarting) {
                cachedFishWatch.value = _fishWatchList.value
                isSearchStarting = false
            }
            isSearching.value = true

            }
        }
    }

    private fun handleFishData(
        fishData: Response<Array<FishWatch>>
    ): Resource<Array<FishWatch>> {
        if (fishData.isSuccessful) {
            fishData.body()?.let { data ->
                return Resource.Success(data)
            }
        }
        return Resource.Error(fishData.message(), null)
    }