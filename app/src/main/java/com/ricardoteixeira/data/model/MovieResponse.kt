package com.ricardoteixeira.data.model

import com.google.gson.annotations.SerializedName

class MovieResponse (

    @SerializedName("page")
    var page: Int? = null,

    @SerializedName("total_results")
    var totalResults: Int? = null,

    @SerializedName("total_pages")
    var totalPages: Int? = null,

    @SerializedName("page")
    var results: List<Movie>? = null
)