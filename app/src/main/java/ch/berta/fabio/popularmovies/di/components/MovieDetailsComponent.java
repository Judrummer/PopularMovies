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

import ch.berta.fabio.popularmovies.di.modules.MovieDetailsViewModelModule;
import ch.berta.fabio.popularmovies.di.modules.MovieRepositoryModule;
import ch.berta.fabio.popularmovies.di.scopes.PerFragment;
import ch.berta.fabio.popularmovies.presentation.ui.fragments.MovieDetailsFavFragment;
import ch.berta.fabio.popularmovies.presentation.ui.fragments.MovieDetailsOnlFragment;
import ch.berta.fabio.popularmovies.presentation.viewmodels.MovieDetailsViewModelFav;
import ch.berta.fabio.popularmovies.presentation.viewmodels.MovieDetailsViewModelOnl;
import dagger.Component;

/**
 * Defines the dependency injection component for the Movie Details screen.
 */
@PerFragment
@Component(modules = {MovieDetailsViewModelModule.class, MovieRepositoryModule.class},
        dependencies = {ApplicationComponent.class})
public interface MovieDetailsComponent {

    void inject(MovieDetailsOnlFragment movieDetailsOnlFragment);

    void inject(MovieDetailsFavFragment movieDetailsFavFragment);

    MovieDetailsViewModelOnl getMovieDetailsViewModelOnl();

    MovieDetailsViewModelFav getMovieDetailsViewModelFav();
}
