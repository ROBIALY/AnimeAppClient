package edu.robertconstantin.animeappcliient.feature_heroes.domain.use_case

import edu.robertconstantin.animeappcliient.feature_heroes.domain.model.HeroDM
import edu.robertconstantin.animeappcliient.feature_heroes.domain.repository.IHeroRepository
import kotlinx.coroutines.flow.Flow

class GetFavHeroByIdUseCase(private val repository: IHeroRepository) {
    operator fun invoke(heroId: Int): Flow<HeroDM> {
        return repository.getHeroById(heroId)
    }
}