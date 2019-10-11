package com.example.countrycodepicker;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable {

    /**
     * icon : flag_al code : 355 en : Albania locale : AL initial : A zh : 阿尔巴尼亚
     */

    @SerializedName("icon")
    private String icon;
    @SerializedName("code")
    private String code;
    @SerializedName("en")
    private String en;
    @SerializedName("locale")
    private String locale;
    @SerializedName("initial")
    private String initial;
    @SerializedName("zh")
    private String zh;
    @SerializedName("isFirst")
    private boolean isFirst;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    @Override
    public String toString() {
        return "Country{" +
                "icon='" + icon + '\'' +
                ", code='" + code + '\'' +
                ", en='" + en + '\'' +
                ", locale='" + locale + '\'' +
                ", initial='" + initial + '\'' +
                ", zh='" + zh + '\'' +
                ", isFirst=" + isFirst +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.icon);
        dest.writeString(this.code);
        dest.writeString(this.en);
        dest.writeString(this.locale);
        dest.writeString(this.initial);
        dest.writeString(this.zh);
        dest.writeByte(this.isFirst ? (byte) 1 : (byte) 0);
    }

    public Country() {
    }

    protected Country(Parcel in) {
        this.icon = in.readString();
        this.code = in.readString();
        this.en = in.readString();
        this.locale = in.readString();
        this.initial = in.readString();
        this.zh = in.readString();
        this.isFirst = in.readByte() != 0;
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
