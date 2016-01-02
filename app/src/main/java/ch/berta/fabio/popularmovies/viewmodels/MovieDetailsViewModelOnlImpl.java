/*
 * Copyright (c) 2016 Fabio Berta
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

package ch.berta.fabio.popularmovies.viewmodels;

import android.databinding.Bindable;
import android.os.Parcel;
import android.support.annotation.NonNull;

import ch.berta.fabio.popularmovies.R;
import ch.berta.fabio.popularmovies.data.models.Movie;
import ch.berta.fabio.popularmovies.data.models.MovieDetails;

/**
 * Provides an implementation of the {@link MovieDetailsViewModelOnl} interface.
 * <p/>
 * Subclass of {@link MovieDetailsViewModelBaseImpl}.
 */
public class MovieDetailsViewModelOnlImpl extends
        MovieDetailsViewModelBaseImpl<MovieDetailsViewModelBaseImpl.ViewInteractionListener> implements
        MovieDetailsViewModelOnl {

    public static final Creator<MovieDetailsViewModelOnlImpl> CREATOR = new Creator<MovieDetailsViewModelOnlImpl>() {
        public MovieDetailsViewModelOnlImpl createFromParcel(Parcel source) {
            return new MovieDetailsViewModelOnlImpl(source);
        }

        public MovieDetailsViewModelOnlImpl[] newArray(int size) {
            return new MovieDetailsViewModelOnlImpl[size];
        }
    };

    /**
     * Constructs a new {@link MovieDetailsViewModelFavImpl}.
     *
     * @param movie      the movie to use
     * @param useTwoPane whether the view uses two panes or not
     */
    public MovieDetailsViewModelOnlImpl(@NonNull Movie movie, boolean useTwoPane) {
        super(useTwoPane);

        mMovie = movie;
    }

    protected MovieDetailsViewModelOnlImpl(Parcel in) {
        super(in);
    }

    @Override
    @Bindable
    public boolean isMovieFavoured() {
        return mMovie != null && mMovie.isFavoured();
    }

    @Override
    public void loadMovieDetails() {
        if (!mMovie.areReviewsAndVideosSet()) {
            mView.loadQueryMovieDetailsWorker(getMovieDbId());
        }
    }

    @Override
    public void onMenuInflation() {
        setYoutubeShareUrl();
    }

    @Override
    public int getMovieDbId() {
        return mMovie.getDbId();
    }

    @Override
    public void onMovieDeleted() {
        super.onMovieDeleted();

        mView.restartLoader();
    }

    @Override
    protected void onMovieDeletedOnePane() {
        mView.showSnackbar(R.string.snackbar_removed_from_favorites, null);
    }

    @Override
    public void onMovieDetailsOnlineLoaded(@NonNull MovieDetails movieDetails) {
        mView.removeQueryMovieDetailsWorker();

        mMovie.setReviews(movieDetails.getReviewsPage().getReviews());
        mMovie.setVideos(movieDetails.getVideosPage().getVideos());
        mMovie.setReviewsAndVideosSet(true);

        setYoutubeShareUrl();
        mView.invalidateOptionsMenu();

        setReviewsAndVideosCount();
        mView.notifyItemRangeInserted(adjustPosForTwoPane(1),
                getNumberOfHeaderRows() + mReviewsCount + mVideosCount);
    }

    @Override
    public void onMovieDetailsOnlineLoadFailed() {
        mView.removeQueryMovieDetailsWorker();

        mView.showSnackbar(R.string.snackbar_error_reviews_videos, null);
    }
}
