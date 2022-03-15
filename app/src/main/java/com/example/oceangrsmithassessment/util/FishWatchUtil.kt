package com.example.oceangrsmithassessment.util

import com.example.oceangrsmithassessment.data.model.FishWatch
import com.example.oceangrsmithassessment.util.FishWatchListUtil.localFishWatchList

object FishWatchListUtil {
    var localFishWatchList: Array<FishWatch> = arrayOf()

    }

    fun getFishWatchList(): Array<FishWatch> {
        return localFishWatchList
    }