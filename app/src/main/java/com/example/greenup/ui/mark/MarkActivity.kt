package com.example.greenup.ui.mark

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenup.R
import com.example.greenup.databinding.ActivityMarkBinding
import com.example.greenup.model.data.MarkData
import com.example.greenup.model.data.NotiData
import com.example.greenup.ui.main.FoodSearchResultActivity
import com.example.greenup.ui.mainnoti.NotiAdapter

class MarkActivity : AppCompatActivity() {
    lateinit var notiAdapter: MarkData
    var datas = mutableListOf<MarkData>()
    lateinit var mark_re_mark : RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mark)
        mark_re_mark = findViewById<RecyclerView>(R.id.mark_re_mark)
        val binding = DataBindingUtil.setContentView<ActivityMarkBinding>(this,
            R.layout.activity_mark
        )
        val foodName = intent.getStringExtra("foodName")
        val foodCompany = intent.getStringExtra("foodCompany")
        val foodCategory = intent.getStringExtra("foodCategory")
        val foodKind = intent.getStringExtra("foodKind")
        val foodId = intent.getStringExtra("foodId")
        binding.mainImgPrevious.setOnClickListener {

            val intent = Intent(this, FoodSearchResultActivity::class.java).apply {
                putExtra("foodName", foodName)
                putExtra("foodCompany", foodCompany)
                putExtra("foodCategory", foodCategory)
                putExtra("foodId",foodKind)
                putExtra("foodKind", foodId)
            }
            startActivityForResult(intent, 1) // 1 is the request code
        }
        initRecycler()
    }
    private fun initRecycler() {
        val recyclerView: RecyclerView = findViewById(R.id.mark_re_mark)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val markAdapter = MarkAdapter(this)
        recyclerView.adapter = markAdapter

        val datas = mutableListOf<MarkData>()
        datas.add(MarkData(R.drawable.ic_gap, "농산물우수관리인증", "농산물의 생산, 수확 후 관리 및 유통 각 단계에서 위해요소를 적절하게 관리한 농산물에 대해 인증 부여"))

        markAdapter.datas = datas
        markAdapter.notifyDataSetChanged()
    }
}