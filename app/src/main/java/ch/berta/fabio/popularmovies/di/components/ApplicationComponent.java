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

package ch.berta.fabio.popularmovies.di.components;

import android.app.Application;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import ch.berta.fabio.popularmovies.data.rest.MovieService;
import ch.berta.fabio.popularmovies.di.modules.ApplicationModule;
import ch.berta.fabio.popularmovies.di.modules.MovieServiceModule;
import ch.berta.fabio.popularmovies.presentation.ui.activities.MovieGridActivity;
import dagger.Component;

/**
 * Defines the dependency injection component for the Application.
 */
@Singleton
@Component(modules = {ApplicationModule.class, MovieServiceModule.class})
public interface ApplicationComponent {

    void inject(MovieGridActivity movieGridActivity);

    Application getApplication();

    SharedPreferences getSharedPreferences();

    MovieService getPopularMovieService();
}
