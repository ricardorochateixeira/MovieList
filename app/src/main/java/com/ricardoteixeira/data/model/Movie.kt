package com.ricardoteixeira.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("backdrop_path")
    var adult: Boolean? = null,

    @Embedded(prefix = "genres_")
    @SerializedName("id")
    var genresId: List<Int?>? = null,

    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,

    @SerializedName("original_language")
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("popularity")
    var popularity: Double? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("video")
    var video: Boolean? = false,

    @SerializedName("vote_average")
    var voteAverage: Double? = null,

    @SerializedName("vote_count")
    var voteCount: Int? = null,

    var watched: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (backdropPath != other.backdropPath) return false
        if (adult != other.adult) return false
        if (genresId != other.genresId) return false
        if (id != other.id) return false
        if (originalLanguage != other.originalLanguage) return false
        if (originalTitle != other.originalTitle) return false
        if (overview != other.overview) return false
        if (popularity != other.popularity) return false
        if (posterPath != other.posterPath) return false
        if (releaseDate != other.releaseDate) return false
        if (title != other.title) return false
        if (video != other.video) return false
        if (voteAverage != other.voteAverage) return false
        if (voteCount != other.voteCount) return false
        if (watched != other.watched) return false

        return true
    }

    override fun hashCode(): Int {
        var result = backdropPath?.hashCode() ?: 0
        result = 31 * result + (adult?.hashCode() ?: 0)
        result = 31 * result + (genresId?.hashCode() ?: 0)
        result = 31 * result + (id ?: 0)
        result = 31 * result + (originalLanguage?.hashCode() ?: 0)
        result = 31 * result + (originalTitle?.hashCode() ?: 0)
        result = 31 * result + (overview?.hashCode() ?: 0)
        result = 31 * result + (popularity?.hashCode() ?: 0)
        result = 31 * result + (posterPath?.hashCode() ?: 0)
        result = 31 * result + (releaseDate?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (video?.hashCode() ?: 0)
        result = 31 * result + (voteAverage?.hashCode() ?: 0)
        result = 31 * result + (voteCount ?: 0)
        result = 31 * result + watched.hashCode()
        return result
    }

}