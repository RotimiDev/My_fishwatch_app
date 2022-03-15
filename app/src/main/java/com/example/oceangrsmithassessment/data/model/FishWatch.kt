package com.example.oceangrsmithassessment.data.model

import android.os.Parcelable
import com.example.oceangrsmithassessment.data.remote.response.SpeciesIllustrationPhoto
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class FishWatch(
    @SerializedName("Habitat Impacts") val habitatImpacts: String,
    @SerializedName("Species Name") val speciesName: String,
    @SerializedName("Species Illustration Photo") val speciesIllustrationPhoto: @RawValue SpeciesIllustrationPhoto,
//    @SerializedName("Image Gallery") val imageGallery: List<ImageGallery>
) : Parcelable
