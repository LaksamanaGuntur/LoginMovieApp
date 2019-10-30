package co.id.loginmovieapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import lombok.Data;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Data
@Entity
public class ResultDataLogin implements Parcelable {
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    protected ResultDataLogin(Parcel in) {
        email = in.readString();
        password = in.readString();
    }

    @Generated(hash = 1046931849)
    public ResultDataLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Generated(hash = 820616836)
    public ResultDataLogin() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static final Creator<ResultDataLogin> CREATOR = new Creator<ResultDataLogin>() {
        @Override
        public ResultDataLogin createFromParcel(Parcel in) {
            return new ResultDataLogin(in);
        }

        @Override
        public ResultDataLogin[] newArray(int size) {
            return new ResultDataLogin[size];
        }
    };
}
