package com.example.greenup.ui.moreeco

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenup.R
import com.example.greenup.databinding.ActivityEcoMoreBinding
import com.example.greenup.model.data.MoreData
import com.example.greenup.model.data.NotiData
import com.example.greenup.ui.main.MainActivity
import com.example.greenup.ui.mainnoti.NotiAdapter
import com.example.greenup.ui.morerecycle.ReMoreAdapter
import com.example.greenup.ui.morerecycle.ReMoreMoreActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EcoMoreActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    lateinit var more_re_more : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eco_more)
        more_re_more = findViewById<RecyclerView>(R.id.more_re_more)
        val binding = DataBindingUtil.setContentView<ActivityEcoMoreBinding>(
            this,
            R.layout.activity_eco_more
        )
        binding.mainImgPrevious.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        initRecycler()
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
        private fun initRecycler() {
            val recyclerView: RecyclerView = findViewById(R.id.more_re_more)
            val layoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = layoutManager
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.item_spacing)
            val itemDecoration = ItemSpacingDecoration(spacingInPixels)
            recyclerView.addItemDecoration(itemDecoration)
            val ecoAdapter = EcoMoreAdapter(this)
            recyclerView.adapter = ecoAdapter
            auth = FirebaseAuth.getInstance()

            val datas = mutableListOf<MoreData>()
            val db = FirebaseFirestore.getInstance()
            db.collection("Eco")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot) {
                        val data = document.data
                        val title = data["title"] as String
                        val context = data["context"] as String

                        datas.add(MoreData(title,context))
                        ecoAdapter.datas = datas
                        ecoAdapter.notifyDataSetChanged()
                    }

                }
                .addOnFailureListener { exception ->
                }
            // 리사이클러뷰 클릭 이벤트 처리
            recyclerView.addOnItemTouchListener(
                RecyclerTouchListener(
                    this,
                    recyclerView,
                    object : RecyclerTouchListener.ClickListener {

                        override fun onClick(view: View, position: Int) {
                            // 클릭 이벤트 처리
                            val clickedItem = datas[position]
                            var intent = Intent(this@EcoMoreActivity, EcoMoreMoreActivity::class.java)

                            intent.putExtra("title",clickedItem.title)
                            startActivity(intent)

                        }
                    })
            )
        }

        // 리사이클러뷰 터치 이벤트 리스너 클래스
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