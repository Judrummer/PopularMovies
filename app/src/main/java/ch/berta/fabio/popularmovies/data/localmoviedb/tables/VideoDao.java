/*
 * Copyright (c) 2017 Fabio Berta
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

package ch.berta.fabio.popularmovies.data.localmoviedb.tables;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface VideoDao {

    @Query("SELECT id, movie_id, name, key, site, size, type FROM video " +
            "WHERE movie_id = :movieId")
    Flowable<List<VideoEntity>> getByMovieId(int movieId);

    @Insert
    void insertAll(List<VideoEntity> videos);

    @Query("DELETE FROM video " +
            "WHERE movie_id IN (SELECT id FROM movie WHERE id = :movieId)")
    int deleteByMovieId(int movieId);
}