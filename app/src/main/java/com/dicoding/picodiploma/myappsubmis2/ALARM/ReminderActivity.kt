package com.dicoding.picodiploma.myappsubmis2.ALARM

import android.os.Bundle
import com.dicoding.picodiploma.myappsubmis2.R

class ReminderActivity() {

    override fun onCreate(savedInstanceState: Bundle){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)
        supportActionBar?.title = "Reminder Notification"

        supportFragmentManager.beginTransaction().add(R.id.reminder_activity)
    }
}