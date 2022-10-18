package edu.robertconstantin.animeappcliient.core.util

import android.content.Context
import edu.robertconstantin.animeappcliient.R

const val UNKNOWN_ERROR = "Unknown Error"
sealed class UiText {
    //string from api
    data class DynamicString(val text: String?) : UiText()

    //string from resource id
    data class StringResource(val id: Int) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> text ?: UNKNOWN_ERROR
            is StringResource -> context.getString(id)
        }
    }

    companion object {
        fun unknownError(): UiText {
            return StringResource(R.string.error_unknown)
        }
    }
}
