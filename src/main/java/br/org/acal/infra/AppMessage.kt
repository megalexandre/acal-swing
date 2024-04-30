package br.org.acal.infra

import java.util.Locale
import org.springframework.context.MessageSource
import org.springframework.stereotype.Component

@Component
class AppMessage(private val messageSource: MessageSource) {
    fun message(code: String): String {

        return messageSource.getMessage(code.trim(), null, Locale.getDefault())
    }

    fun message(code: String, args: Array<String>): String {
        return messageSource.getMessage(code.trim(), args, Locale.getDefault())
    }
}
