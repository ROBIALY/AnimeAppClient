package edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case

import edu.robertconstantin.animeappcliient.feature_heroes.domain.model.HeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.domain.repository.IHeroRepository

class ToogleFavoriteHeroUseCase(private val repository: IHeroRepository) {
    suspend operator fun invoke(heroDM: HeroDM, isHeroAddedToFavorites: Boolean) {

        if (isHeroAddedToFavorites) repository.deleteFavoriteHero(heroDM)
        else repository.insertFavoriteHero(heroDM)
    }
}