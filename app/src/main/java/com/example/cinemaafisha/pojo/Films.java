package com.example.cinemaafisha.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Films implements Parcelable {

    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;
    @SerializedName("video")
    @Expose
    public Boolean video;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("adult")
    @Expose
    public Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("original_title")
    @Expose
    public String originalTitle;
    @SerializedName("genre_ids")
    @Expose
    public List<Integer> genreIds = null;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("release_date")
    @Expose
    public String releaseDate;

    public Films(String posterPath, String title, String overview) {
        this.posterPath = posterPath;
        this.title = title;
        this.overview = overview;
    }

    public Films(Parcel in) {
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        if (in.readByte() == 0) {
            voteCount = null;
        } else {
            voteCount = in.readInt();
        }
        byte tmpVideo = in.readByte();
        video = tmpVideo == 0 ? null : tmpVideo == 1;
        posterPath = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        byte tmpAdult = in.readByte();
        adult = tmpAdult == 0 ? null : tmpAdult == 1;
        backdropPath = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        title = in.readString();
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readDouble();
        }
        overview = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<Films> CREATOR = new Creator<Films>() {
        @Override
        public Films createFromParcel(Parcel in) {
            return new Films(in);
        }

        @Override
        public Films[] newArray(int size) {
            return new Films[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (popularity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(popularity);
        }
        if (voteCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(voteCount);
        }
        dest.writeByte((byte) (video == null ? 0 : video ? 1 : 2));
        dest.writeString(posterPath);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeByte((byte) (adult == null ? 0 : adult ? 1 : 2));
        dest.writeString(backdropPath);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeString(title);
        if (voteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(voteAverage);
        }
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }
}

