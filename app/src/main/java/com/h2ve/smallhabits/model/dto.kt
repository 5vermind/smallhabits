package com.h2ve.smallhabits.model

data class LoginRes (
        var success: Boolean,
        var resultCode: String,
        var id: Int,
        var nickname: String
        )

data class LogoutRes (
        var success: Boolean,
        var resultCode: String
        )

data class SignUpRes (
        var success: Boolean,
        var resultCode: String
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

data class HabitRes(
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