package co.id.loginmovieapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Entity
public class FavoriteData implements Parcelable {

    @Id
    private Long idLocal;

    private int voteCount;
    private int id;
    private boolean video;
    private float voteAverage;
    private String title;
    private float popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    private String backdropPath;
    private boolean adult;
    private String overview;
    private String releaseDate;

    protected FavoriteData(Parcel in) {
        if (in.readByte() == 0) {
            idLocal = null;
        } else {
            idLocal = in.readLong();
        }
        voteCount = in.readInt();
        id = in.readInt();
        video = in.readByte() != 0;
        voteAverage = in.readFloat();
        title = in.readString();
        popularity = in.readFloat();
        posterPath = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        backdropPath = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        releaseDate = in.readString();
    }

    @Generated(hash = 963678562)
    public FavoriteData(Long idLocal, int voteCount, int id, boolean video, float voteAverage,
            String title, float popularity, String posterPath, String originalLanguage,
            String originalTitle, String backdropPath, boolean adult, String overview,
            String releaseDate) {
        this.idLocal = idLocal;
        this.voteCount = voteCount;
        this.id = id;
        this.video = video;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    @Generated(hash = 1088818046)
    public FavoriteData() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (idLocal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(idLocal);
        }
        dest.writeInt(voteCount);
        dest.writeInt(id);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeFloat(voteAverage);
        dest.writeString(title);
        dest.writeFloat(popularity);
        dest.writeString(posterPath);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeString(backdropPath);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Long getIdLocal() {
        return this.idLocal;
    }

    public void setIdLocal(Long idLocal) {
        this.idLocal = idLocal;
    }

    public int getVoteCount() {
        return this.voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getVideo() {
        return this.video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getVoteAverage() {
        return this.voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPopularity() {
        return this.popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return this.posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return this.originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return this.originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return this.backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean getAdult() {
        return this.adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public static final Creator<FavoriteData> CREATOR = new Creator<FavoriteData>() {
        @Override
        public FavoriteData createFromParcel(Parcel in) {
            return new FavoriteData(in);
        }

        @Override
        public FavoriteData[] newArray(int size) {
            return new FavoriteData[size];
        }
    };
}
