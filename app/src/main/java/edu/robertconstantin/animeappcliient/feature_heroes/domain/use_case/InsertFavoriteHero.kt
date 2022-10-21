package edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case

import edu.robertconstantin.animeappcliient.feature_heroes.domain.model.HeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.domain.repository.IHeroRepository

class InsertFavoriteHero(private val repository: IHeroRepository) {
    suspend operator fun invoke(heroDM: HeroDM) {
        repository.insertFavoriteHero(heroDM)
    }
}