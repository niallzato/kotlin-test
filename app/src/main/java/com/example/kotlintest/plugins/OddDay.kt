package com.example.kotlintest.plugins

import com.segment.analytics.kotlin.core.Analytics
import com.segment.analytics.kotlin.core.BaseEvent
import com.segment.analytics.kotlin.core.platform.Plugin
import com.segment.analytics.kotlin.core.utilities.putInContext
import java.time.LocalDate

/**
 * Analytics Plugin to return if today is an odd day
 *
 */
class OddDay() : Plugin {

    override val type: Plugin.Type = Plugin.Type.Enrichment
    override lateinit var analytics: Analytics

    companion object {
    }

    private fun isOdd(): Boolean {
        var odd = false
        var day: Int = LocalDate.now().getDayOfWeek().value

        if (day % 2 == 1) odd = true
        return odd
    }

    private fun attachOddDay(payload: BaseEvent): BaseEvent {
        payload.putInContext("isOddDay", isOdd())
        return payload
    }

    override fun execute(event: BaseEvent): BaseEvent {
        return attachOddDay(event)
    }

}