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

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabio on 09.12.15.
 */
public class VideosPage implements Parcelable {

    @SerializedName("results")
    private List<Video> mVideos = new ArrayList<>();

    public VideosPage() {
    }

    public List<Video> getVideos() {
        return mVideos;
    }

    public void setVideos(List<Video> videos) {
        mVideos = videos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mVideos);
    }

    protected VideosPage(Parcel in) {
        mVideos = new ArrayList<Video>();
        in.readList(mVideos, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<VideosPage> CREATOR = new Parcelable.Creator<VideosPage>() {
        public VideosPage createFromParcel(Parcel source) {
            return new VideosPage(source);
        }

        public VideosPage[] newArray(int size) {
            return new VideosPage[size];
        }
    };
}