package com.example.greenup.ui.mark

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
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
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing)
        val itemDecoration = ItemSpacingDecoration(spacingInPixels)
        recyclerView.addItemDecoration(itemDecoration)

        val markAdapter = MarkAdapter(this)
        recyclerView.adapter = markAdapter

        val datas = mutableListOf<MarkData>()
        datas.add(MarkData(R.drawable.ic_gap, "농산물우수관리인증", "농산물의 생산, 수확 후 관리 및 유통 각 단계에서 위해요소를 적절하게 관리한 농산물에 대해 인증 부여"))
        datas.add(MarkData(R.drawable.image3, "유기가공식품인증", "유기농∙축산물을 원료 또는 재료로 하여 제조∙가공한 식품"))
        datas.add(MarkData(R.drawable.image4, "우수천일염인증", "고품질 천일염의 생산을 촉진하고 소비자를 보호하기 위하여 우수한 품질의 천일염에 대하여 인증 부여"))
        datas.add(MarkData(R.drawable.image15, "유기수산물인증", "양식장 수질환경 및 양식과정 등을 적합한 절차에 따라 생산한 수산물에 대해 인증 부여"))
        datas.add(MarkData(R.drawable.image8, "친환경수산물인증", "양식장 수질환경 및 양식과정 등을 적합한 절차에 따라 생산한 수산물에 대해 인증 부여"))
        datas.add(MarkData(R.drawable.image9, "저탄소농축산물인증", "온실가스 배출량 저감"))
        datas.add(MarkData(R.drawable.image11, "환경마크", "지역환경오염감소, 에너지절약, 소음·진동감소, 유해물질감소, 생활환경오염감소, 지구환경오염감소, 자원순환성 향상"))
        datas.add(MarkData(R.drawable.image12, "탄소성적표지(탄소발자국 인증)", "탄소배출량 산정"))
        datas.add(MarkData(R.drawable.image13, "환경성적표지(저탄소제품 인증)", "탄소배출량 산정"))
        datas.add(MarkData(R.drawable.image14, "환경성적표지(저탄소제품 인증)", "탄소배출량 저감"))

        markAdapter.datas = datas
        markAdapter.notifyDataSetChanged()
    }
    private class ItemSpacingDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            // 아이템마다 왼쪽, 오른쪽, 아래쪽 간격 설정
            outRect.left = spacing
            outRect.right = spacing
            outRect.bottom = spacing

            // 첫 번째 아이템이 아닌 경우에만 위쪽 간격 설정
            if (parent.getChildAdapterPosition(view) != 0) {
                outRect.top = spacing
            }
        }
    }

    private class RecyclerTouchListener(
        context: Context,
        recyclerView: RecyclerView,
        private val clickListener: ClickListener?
    ) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val childView = rv.findChildViewUnder(e.x, e.y)
            if (childView != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(childView, rv.getChildAdapterPosition(childView))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

        interface ClickListener {
            fun onClick(view: View, position: Int)
        }
    }
}
