package edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_feed_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.robertconstantin.animeappcliient.core.presentation.UiEvent
import edu.robertconstantin.animeappcliient.core.util.UiText
import edu.robertconstantin.animeappcliient.feature_heroes.domain.GetAllHeroesUseCase
import edu.robertconstantin.animeappcliient.feature_heroes.presentation.mapper.toHeroVo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroFeedScreenViewModel
@Inject constructor(private val getAllHeroesUseCase: GetAllHeroesUseCase) : ViewModel() {


    var state by mutableStateOf<HeroFeedScreenState>(HeroFeedScreenState())
        private set

    private var _singleUiEvent = Channel<UiEvent>()
    val singleUiEvent = _singleUiEvent.receiveAsFlow()


    init {
        getAllHeroes()
    }

    private fun getAllHeroes() {

        viewModelScope.launch {
            state = state.copy(loading = true)
            getAllHeroesUseCase.invoke().mapResourceData(
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