package com.example.greenup.ui.mainnoti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenup.R
import com.example.greenup.databinding.ActivityNoti3Binding
import com.example.greenup.model.data.NotiData
import com.example.greenup.ui.main.MainActivity

class Noti3Activity : AppCompatActivity() {
    lateinit var notiAdapter: NotiAdapter
    var datas = mutableListOf<NotiData>()
    lateinit var noti_re_noti : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noti3)

        noti_re_noti = findViewById<RecyclerView>(R.id.noti_re_noti)
        val binding = DataBindingUtil.setContentView<ActivityNoti3Binding>(this,
            R.layout.activity_noti3
        )
        binding.mainImgPrevious.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        initRecycler()
    }
    private fun initRecycler() {
        val recyclerView: RecyclerView = findViewById(R.id.noti_re_noti)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val notiAdapter = NotiAdapter(this)
        recyclerView.adapter = notiAdapter

        val datas = mutableListOf<NotiData>()
        datas.add(NotiData(R.drawable.emoji_objects, "오늘의 재활용 팁", "1시간전", "오렌지 껍질은 일반 쓰레기이다."))

        notiAdapter.datas = datas
        notiAdapter.notifyDataSetChanged()
    }

}