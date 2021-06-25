package com.dicoding.picodiploma.myappsubmis2.ALARM

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.myappsubmis2.R
import kotlinx.android.synthetic.main.activity_reminder.*

class ReminderActivity: AppCompatActivity() {

    private lateinit var alarmReceiver: AlarmReceiver
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Setting of Alarm"




        alarmReceiver = AlarmReceiver()
        sharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)

        SwitchofSet()
        AlarmSw.setOnCheckedChangeListener {_, isChecked ->
            if(isChecked){
                alarmReceiver.setRepeatAlarm(
                    this, AlarmReceiver.EXTRA_MODE,"Find your favourite github user") }else {
                alarmReceiver.cancelAlarm(this)
            }
            Changeissave(isChecked)

            }
        }

    private fun SwitchofSet() {
        AlarmSw.isChecked = sharedPreferences.getBoolean(DAILY, false)
    }
    private fun Changeissave (value:Boolean) {
        val edit = sharedPreferences.edit()
        edit.putBoolean(DAILY, value)
        edit.apply()
    }



        companion object {
            const val PREFERENCE = "Preference"
            private const val DAILY = "daily"
        }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}


