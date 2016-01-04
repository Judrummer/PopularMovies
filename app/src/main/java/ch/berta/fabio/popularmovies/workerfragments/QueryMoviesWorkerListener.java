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

package ch.berta.fabio.popularmovies.workerfragments;

import android.support.annotation.NonNull;

import java.util.List;

import ch.berta.fabio.popularmovies.data.models.Movie;

/**
 * Defines the actions after a query for movies was attempted.
 */
public interface QueryMoviesWorkerListener {
    /**
     * Handles the event when movie query finished.
     *
     * @param movies the {@link List<Movie>} containing the queried movies
     */
    void onMoviesOnlineLoaded(@NonNull List<Movie> movies);

    /**
     * Handles the event when movie query failed.
     */
    void onMoviesOnlineLoadFailed();
}
