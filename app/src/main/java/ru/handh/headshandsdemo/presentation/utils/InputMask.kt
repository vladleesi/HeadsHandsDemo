package ru.handh.headshandsdemo.presentation.utils

import java.util.regex.Pattern

class InputMask {

    companion object {

        private val EMAIL_PATTERN: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
                    ")+"
        )

        private val PASSWORD_PATTERN: Pattern = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}\$"
        )

        fun isEmailValid(email: String?): Boolean {
            return EMAIL_PATTERN.matcher(email ?: return false).matches()
        }

        fun isPasswordValid(password: String?): Boolean {
            return PASSWORD_PATTERN.matcher(password ?: return false).matches()
        }
    }
}