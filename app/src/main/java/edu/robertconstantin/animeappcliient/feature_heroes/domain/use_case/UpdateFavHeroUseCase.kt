package edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case

import edu.robertconstantin.animeappcliient.feature_heroes.domain.repository.IHeroRepository

class UpdateFavHeroUseCase(private val repository: IHeroRepository) {

    suspend operator fun invoke(name: String, about: String, power: String, id: Int): Boolean {

        if (name.isEmpty() || about.isEmpty() || power.isEmpty()) {
            return false
        }
        repository.updateFavoriteHero(name, about, power, id)
        return true
    }

}