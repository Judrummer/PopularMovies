/*
 * Copyright (c) 2015 Fabio Berta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.berta.fabio.popularmovies.data.models;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import ch.berta.fabio.popularmovies.data.storage.MovieContract;

/**
 * Created by fabio on 09.12.15.
 */
public class Video implements Parcelable {

    public static final String YOUTUBE_THUMB_BASE_URL = "https://img.youtube.com/vi/";
    public static final String YOUTUBE_THUMB_DEFAULT = "/default.jpg";
    public static final String YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v=";
    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
    @SerializedName("name")
    private String mName;
    @SerializedName("key")
    private String mKey;
    @SerializedName("site")
    private String mSite;
    @SerializedName("size")
    private int mSize;
    @SerializedName("type")
    private String mType;

    public Video() {
    }

    public Video(String name, String key, String site, int size, String type) {
        mName = name;
        mKey = key;
        mSite = site;
        mSize = size;
        mType = type;
    }

    protected Video(Parcel in) {
        mName = in.readString();
        mKey = in.readString();
        mSite = in.readString();
        mSize = in.readInt();
        mType = in.readString();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getSite() {
        return mSite;
    }

    public void setSite(String site) {
        mSite = site;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public ContentValues getContentValuesEntry() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContract.Video.COLUMN_NAME, mName);
        contentValues.put(MovieContract.Video.COLUMN_KEY, mKey);
        contentValues.put(MovieContract.Video.COLUMN_SITE, mSite);
        contentValues.put(MovieContract.Video.COLUMN_SIZE, mSize);
        contentValues.put(MovieContract.Video.COLUMN_TYPE, mType);
        return contentValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mKey);
        dest.writeString(mSite);
        dest.writeInt(mSize);
        dest.writeString(mType);
    }
}
