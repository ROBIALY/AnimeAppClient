package edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_feed_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.robertconstantin.animeappcliient.R
import edu.robertconstantin.animeappcliient.core.presentation.UiEvent
import edu.robertconstantin.animeappcliient.core.util.UiText
import edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case.HeroUseCases
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.mapper.toHeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.mapper.toHeroVo
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.model.HeroVO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroFeedScreenViewModel
@Inject constructor(
    private val useCases: HeroUseCases
) : ViewModel() {


    var state by mutableStateOf<HeroFeedScreenState>(HeroFeedScreenState())
        private set

    private var _singleUiEvent = Channel<UiEvent>()
    val singleUiEvent = _singleUiEvent.receiveAsFlow()


    init {
        getAllHeroes()
    }

    fun onEvent(event: HeroFeedScreenEvent) {
        when (event) {
            is HeroFeedScreenEvent.OnFavoriteClick -> {
                toggleFavoriteHero(event.hero)
            }
        }
    }

    private fun toggleFavoriteHero(hero: HeroVO) {
        viewModelScope.launch {
            useCases.toogleFavoriteHeroUseCase.invoke(hero.toHeroDM(), hero.isAddedToFavorites)

            state = state.copy(heroes = state.heroes.map {
                if (it.id == hero.id) it.copy(isAddedToFavorites = !it.isAddedToFavorites)
                else it
            })

            when (hero.isAddedToFavorites) {
                true -> _singleUiEvent.send(
                    UiEvent.ShowSnackBar(UiText.StringResource(R.string.hero_deleted_from_fav))
                )
                false -> _singleUiEvent.send(
                    UiEvent.ShowSnackBar(UiText.StringResource(R.string.hero_added_to_fav))
                )
            }
        }
    }

    private fun getAllHeroes() {

        viewModelScope.launch {
            state = state.copy(loading = true)
            useCases.getAllHeroesUseCase.invoke().mapResourceData(
                success = {
                    it?.let { heroes ->
                        state = state.copy(
                            loading = false,
                            heroes = heroes.map { hero -> hero.toHeroVo() })
                    }
                },
                error = { msg ->
                    state = state.copy(loading = false)
                    _singleUiEvent.send(UiEvent.ShowSnackBar(msg ?: UiText.unknownError()))
                }
            )
        }
    }
}