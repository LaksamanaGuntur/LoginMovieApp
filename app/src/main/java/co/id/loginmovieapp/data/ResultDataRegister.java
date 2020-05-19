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
public class ResultDataRegister implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    protected ResultDataRegister(Parcel in) {
        name = in.readString();
        email = in.readString();
        password = in.readString();
    }

    @Generated(hash = 1227379758)
    public ResultDataRegister(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Generated(hash = 1882991297)
    public ResultDataRegister() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public static final Creator<ResultDataRegister> CREATOR = new Creator<ResultDataRegister>() {
        @Override
        public ResultDataRegister createFromParcel(Parcel in) {
            return new ResultDataRegister(in);
        }

        @Override
        public ResultDataRegister[] newArray(int size) {
            return new ResultDataRegister[size];
        }
    };
}
