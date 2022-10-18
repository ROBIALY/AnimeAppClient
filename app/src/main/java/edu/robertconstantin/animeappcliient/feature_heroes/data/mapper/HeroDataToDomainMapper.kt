package edu.robertconstantin.animeappcliient.feature_heroes.data.mapper

import edu.robertconstantin.animeappcliient.feature_heroes.data.dto.HeroDto
import edu.robertconstantin.animeappcliient.feature_heroes.domain.model.HeroDM

fun HeroDto.toHeroDM() = HeroDM(
    id, name, image, about, rating, power, month, day, family, abilities, natureTypes
)