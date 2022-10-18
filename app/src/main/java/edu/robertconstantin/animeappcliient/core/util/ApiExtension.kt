package edu.robertconstantin.animeappcliient.core.util

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

inline fun <T> callApi(call: () -> Response<T>): Resource<T> {
    return try {

        val response = call.invoke()

        when {
            response.isSuccessful && response.body() != null -> Resource.Success(data = response.body())
            else -> Resource.Error(UiText.DynamicString(response.message()))
        }
    }catch (exception: IOException) {
        Resource.Error(UiText.DynamicString(exception.message))
    }catch (exception: HttpException) {
        Resource.Error(UiText.DynamicString(exception.message))
    }
}
