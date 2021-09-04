package com.h2ve.smallhabits.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
        var token: String
        )

data class LogoutRes (
        var success: Boolean,
        var resultCode: String
        )

data class RegisterResponse (
        var userId: String,
        var name: String
        )

data class Login(
        var userId: String,
        var password: String
)

data class Register(
        var userId: String,
        var password: String,
        var passwordAgain: String,
        var name: String
)

data class HabitPost(
        var title: String,
        var detail: String,
        var timeToDo: String,
        var placeToDo: String,
        var rating: Int,
        var nextHabit: String
)

data class HabitResponse(
        var title: String,
        var detail: String,
        var timeToDo: String,
        var placeToDo: String,
        var rating: Int,
        var nextHabit: String,
        var _id: String,
        var uId: String,
        var calendar: Array<String>
)

data class ErrorResponse(
        @SerializedName("errorAt") var errorAt:String,
        @SerializedName("message") var message: String
)

sealed class ViewModelTransferObject<out T> {
        data class Success<out T>(val value : T) : ViewModelTransferObject<T>()
        data class GenericError(val error: ErrorResponse? = null): ViewModelTransferObject<Nothing>()
        object NetworkError: ViewModelTransferObject<Nothing>()
}