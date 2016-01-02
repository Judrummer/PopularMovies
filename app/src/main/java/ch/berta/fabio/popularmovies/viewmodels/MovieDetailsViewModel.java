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
import android.databinding.Observable;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;

import ch.berta.fabio.popularmovies.data.models.Movie;
import ch.berta.fabio.popularmovies.data.models.Review;
import ch.berta.fabio.popularmovies.data.models.SnackbarAction;
import ch.berta.fabio.popularmovies.data.models.Video;
import ch.berta.fabio.popularmovies.data.repositories.MovieRepository;
import ch.berta.fabio.popularmovies.ui.adapters.listeners.MovieDetailsInteractionListener;
import ch.berta.fabio.popularmovies.workerfragments.QueryMovieDetailsWorker;

/**
 * Defines a view model for the details screen of a movie.
 */
public interface MovieDetailsViewModel<T extends MovieDetailsViewModel.ViewInteractionListener> extends
        Parcelable, Observable, InteractionViewModel<T>, AdapterViewModel, MovieDetailsInteractionListener,
        MovieRepository.LocalOperationsListener, QueryMovieDetailsWorker.WorkerInteractionListener {

    int TYPE_HEADER = 0;
    int TYPE_TWO_PANE_HEADER = 1;
    int TYPE_INFO = 2;
    int TYPE_REVIEW = 3;
    int TYPE_VIDEO = 4;

    Movie getMovie();

    void setMovie(@NonNull Movie movie);

    @Bindable
    String getMovieTitle();

    @Bindable
    String getMovieBackdropPath();

    @Bindable
    boolean isMovieFavoured();

    void setMovieFavoured(boolean isFavoured);

    long getMovieRowId();

    void setMovieRowId(long rowId);

    /**
     * Returns whether the movie has any videos or not.
     *
     * @return whether the movie has any videos or not
     */
    boolean hasMovieVideos();

    /**
     * Returns the {@link Video} object at the specified position.
     *
     * @param position the position of the video to get
     * @return the {@link Video} object at the specified position
     */
    Video getMovieVideoAtPosition(int position);

    /**
     * Returns the {@link Review} object at the specified position.
     *
     * @param position the position of the review to get
     * @return the {@link Review} object at the specified position
     */
    Review getMovieReviewAtPosition(int position);

    /**
     * Returns whether the review is the last item in the list or not.
     *
     * @param review the review to check the position for
     * @return whether the review is the last item in the list or not
     */
    boolean isMovieReviewLastPosition(@NonNull Review review);

    @StringRes
    int getHeaderTitle(int position);

    void onFabClick(View view);

    /**
     * Defines the interaction with the view.
     */
    interface ViewInteractionListener {
        /**
         * Shows a snackbar with an optional click action
         *
         * @param text   the text to display
         * @param action the click action
         */
        void showSnackbar(@StringRes int text, @Nullable SnackbarAction action);

        /**
         * Restarts the loader associated with the view.
         */
        void restartLoader();

        /**
         * Inserts a movie to the local content provider.
         */
        void insertMovieLocal();

        /**
         * Deletes a movie from the local content provider.
         */
        void deleteMovieLocal();

        /**
         * Starts the postponed enter transition.
         */
        void startPostponedEnterTransition();

        /**
         * Loads the worker fragment that queries for the details of a movie.
         *
         * @param movieDbId the TheMovieDB id of the movie
         */
        void loadQueryMovieDetailsWorker(int movieDbId);

        /**
         * Removes the worker fragment that queried for movie details.
         */
        void removeQueryMovieDetailsWorker();

        /**
         * Starts an activity that can handle the playback of the video uri.
         *
         * @param videoUri the uri to play back
         */
        void startVideoActivity(@NonNull Uri videoUri);

        /**
         * Notifies the view that the data has changed.
         */
        void notifyDataChanged();

        /**
         * Notifies the view that items were inserted.
         *
         * @param positionStart position where items where inserted
         * @param itemCount     number of items that were inserted
         */
        void notifyItemRangeInserted(int positionStart, int itemCount);

        /**
         * Sets the youtube url in the intent that launches the share action.
         *
         * @param url the youtube url to set
         */
        void setYoutubeShareUrl(@NonNull String url);

        /**
         * Reloads the view's options menu.
         */
        void invalidateOptionsMenu();
    }
}
