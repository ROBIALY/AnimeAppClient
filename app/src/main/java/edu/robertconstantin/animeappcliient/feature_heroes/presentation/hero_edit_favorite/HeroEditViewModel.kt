package edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_edit_favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.robertconstantin.animeappcliient.R
import edu.robertconstantin.animeappcliient.core.presentation.UiEvent
import edu.robertconstantin.animeappcliient.core.util.UiText
import edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case.GetFavHeroByIdUseCase
import edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case.UpdateFavHeroUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroEditViewModel @Inject constructor(
    private val updateFavHeroUseCase: UpdateFavHeroUseCase,
    private val getFavHeroByIdUseCase: GetFavHeroByIdUseCase,
    private val savedSateHandle: SavedStateHandle
    ): ViewModel() {


    var state by mutableStateOf(HeroEditScreenState())
        private set

    var heroImgState by mutableStateOf(String())
        private set

    private var _singleUiEvent = Channel<UiEvent>()
    val singleUiEvent = _singleUiEvent.receiveAsFlow()

    private val heroId = savedSateHandle.get<Int>("heroId")


    init {
        heroId?.let { heroId ->
            getFavHeroById(heroId)
        }
//        savedSateHandle.get<Int>("heroId")?.let { heroId ->
//            getFavHeroById(heroId)
//        }
    }

    private fun getFavHeroById(heroId: Int) {
        viewModelScope.launch {
            getFavHeroByIdUseCase.invoke(heroId).collectLatest {
                heroImgState = it.image
            }
        }
    }

    fun onEvent(event: HeroEditScreenEvent) {
        when(event) {
            is HeroEditScreenEvent.OnAboutChange -> { state = state.copy(about = event.text) }
            is HeroEditScreenEvent.OnNameChange -> { state = state.copy(name = event.text) }
            is HeroEditScreenEvent.OnPowerChange -> { state = state.copy(power = event.text) }
            is HeroEditScreenEvent.OnUpdateClick -> { heroId?.let { onUpdateFavoriteHero(heroId) } }
        }
    }

    private fun onUpdateFavoriteHero(heroId: Int) {
        viewModelScope.launch {
            if (updateFavHeroUseCase.invoke(state.name, state.about, state.power, heroId)) {
                _singleUiEvent.send(UiEvent.ShowSnackBar(UiText.StringResource(R.string.fav_hero_updated)))
            } else  _singleUiEvent.send(UiEvent.ShowSnackBar(UiText.StringResource(R.string.all_fields_needed)))
        }
    }

}