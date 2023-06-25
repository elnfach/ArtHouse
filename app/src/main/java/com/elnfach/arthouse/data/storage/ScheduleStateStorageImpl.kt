package com.elnfach.arthouse.data.storage

import android.content.Context
import com.elnfach.arthouse.data.repository.storage.KEY_THEME_SETTING
import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.ThemeSetting
import com.elnfach.arthouse.presentation.ui.theme.Theme

const val SHARED_PREFS_SCHEDULE_STATE = "shared_prefs_schedule_state"
const val KEY_SCHEDULE_STATE_YEAR = "schedule_state_year"
const val KEY_SCHEDULE_STATE_MONTH = "schedule_state_month"
const val KEY_SCHEDULE_STATE_DAY_OF_MONTH = "schedule_state_day_of_month"

class ScheduleStateStorageImpl(context: Context) : ScheduleStateStorage {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_SCHEDULE_STATE, Context.MODE_PRIVATE)
    override fun save(date: LocalDate) {
        sharedPreferences.edit().putInt(KEY_SCHEDULE_STATE_YEAR, date.year).apply()
        sharedPreferences.edit().putInt(KEY_SCHEDULE_STATE_MONTH, date.monthNumber).apply()
        sharedPreferences.edit().putInt(KEY_SCHEDULE_STATE_DAY_OF_MONTH, date.dayOfMonth).apply()
    }

    override fun get(): LocalDate {
        return LocalDate(
            sharedPreferences.getInt(KEY_SCHEDULE_STATE_YEAR, 2023),
            sharedPreferences.getInt(KEY_SCHEDULE_STATE_MONTH, 6),
            sharedPreferences.getInt(KEY_SCHEDULE_STATE_DAY_OF_MONTH, 1))
    }
}