package demo.minifly.com.fuction_demo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author ：minifly
 * date: 2017/3/30
 * time: 16:17
 * desc:
 */
public class BeanIn implements Parcelable{
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.str);
    }

    public BeanIn() {
    }

    protected BeanIn(Parcel in) {
        this.str = in.readString();
    }

    public static final Creator<BeanIn> CREATOR = new Creator<BeanIn>() {
        @Override
        public BeanIn createFromParcel(Parcel source) {
            return new BeanIn(source);
        }

        @Override
        public BeanIn[] newArray(int size) {
            return new BeanIn[size];
        }
    };
}