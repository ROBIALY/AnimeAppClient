package edu.robertconstantin.animeappcliient.core.data

import edu.robertconstantin.animeappcliient.core.util.Resource
import edu.robertconstantin.animeappcliient.core.util.UiText

data class ApiResponse<T>(
    val successful: Boolean,
    //message for error. Could be nullable
    val message: String? = null,
    //data to be sent to client. Could be null fi there is an error
    val data: T? = null
) {
    inline fun <R> mapApiResponse(transformation: (T) -> R): Resource<R> {
        return when (this.successful) {
            true -> {
                Resource.Success(this.data?.let { apiData -> transformation.invoke(apiData) })
            }
            false -> {
                val resource: Resource<R> = this.message?.let {
                    Resource.Error(UiText.DynamicString(this.message))
                } ?: Resource.Error(UiText.StringResource(edu.robertconstantin.animeappcliient.R.string.error_unknown))
                resource
            }
        }
    }

}