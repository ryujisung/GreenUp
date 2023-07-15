package com.example.greenup.ui.my.noti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import com.example.greenup.R
import com.example.greenup.databinding.ActivityNotiBinding
import com.example.portplay.base.BaseActivity

class NotiActivity : BaseActivity<ActivityNotiBinding>(R.layout.activity_noti){
    private lateinit var notificationSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noti)
        notificationSwitch = binding.notiSwitchNoti
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            saveNotificationSetting(isChecked)
            onSwitchChanged(isChecked)
        }
        val notificationEnabled = isNotificationEnabled()
        notificationSwitch.isChecked = notificationEnabled

    }
    private fun onSwitchChanged(isChecked: Boolean) {
        // 스위치 상태 변경에 따라 수행할 작업을 여기에 작성합니다.
        if (isChecked) {
            // 스위치가 켜진 경우
            // 알림을 받도록 설정하거나 필요한 작업을 수행합니다.
        } else {
            // 스위치가 꺼진 경우
            // 알림을 받지 않도록 설정하거나 필요한 작업을 수행합니다.
        }
    }
    private fun saveNotificationSetting(enabled: Boolean) {
        val sharedPreferences = getSharedPreferences("NotificationSettings", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("notificationEnabled", enabled)
        editor.apply()
    }

    private fun isNotificationEnabled(): Boolean {
        val sharedPreferences = getSharedPreferences("NotificationSettings", MODE_PRIVATE)
        return sharedPreferences.getBoolean("notificationEnabled", true)
    }
}