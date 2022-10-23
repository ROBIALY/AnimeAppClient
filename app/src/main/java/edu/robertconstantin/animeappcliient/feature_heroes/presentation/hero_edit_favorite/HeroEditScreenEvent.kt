package edu.robertconstantin.animeappcliient.feature_heroes.presentation.hero_edit_favorite

sealed class HeroEditScreenEvent {
    data class OnNameChange(val text: String): HeroEditScreenEvent()
    data class OnAboutChange(val text: String): HeroEditScreenEvent()
    data class OnPowerChange(val text: String): HeroEditScreenEvent()
    object OnUpdateClick: HeroEditScreenEvent()
}
