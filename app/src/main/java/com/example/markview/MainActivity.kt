package com.example.markview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_toolbar.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var vpAdapter: FragmentStatePagerAdapter? = null
    var mBackWait: Long = 0
    var currentPosition = 0
    val handler = Handler(Looper.getMainLooper()) {
        setPage()
        true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vpAdapter = CustomPagerAdapter(supportFragmentManager)
        viewpager.adapter = vpAdapter

        indicator.setViewPager(viewpager)


        main_navigationView.setNavigationItemSelectedListener(this)
        setSupportActionBar(main_layout_toolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_account) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게


        val thread = Thread(PagerRunnable())
        thread.start()


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { // 메뉴 버튼

                main_drawer_layout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.account -> Toast.makeText(this, "마이페이지", Toast.LENGTH_SHORT).show()
            R.id.item2 -> {
                val intent = Intent(this, GeneralActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.item3 -> Toast.makeText(this, "이미지로 판별", Toast.LENGTH_SHORT).show()
            R.id.item4 -> {
                val intent = Intent(this, CostViewActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.item5 -> {
                Toast.makeText(this, "이용 안내", Toast.LENGTH_SHORT).show()
            }

            R.id.item6 -> {
                Toast.makeText(this, "로그아웃", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return false
    }


    class CustomPagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val PAGENUMBER = 4

        override fun getCount(): Int {
            return PAGENUMBER
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> TestFragment.newInstance(R.drawable.mark_logo, "")
                1 -> TestFragment.newInstance(R.raw.img2, "")
                2 -> TestFragment.newInstance(R.raw.img3, "")
                else -> TestFragment.newInstance(R.raw.img4, "")
            }
        }
    }

    fun setPage() {
        if (currentPosition == 5) currentPosition = 0
        viewpager.setCurrentItem(currentPosition, true)
        currentPosition += 1
    }

    //2초 마다 페이지 넘기기
    inner class PagerRunnable : Runnable {
        override fun run() {
            while (true) {
                Thread.sleep(2000)
                handler.sendEmptyMessage(0)
            }
        }
    }


    fun cost_view(view: View) {
        val intent = Intent(this, CostViewActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun general_btn(view: View) {
        val intent = Intent(this, FirstActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun professional_btn(view: View) {
        Toast.makeText(this, "아직 못해써여", Toast.LENGTH_SHORT).show()
    }

    fun use_btn(view: View) {
        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://markview.kr/"))
        startActivity(intent)
    }


    override fun onBackPressed() { //뒤로가기 처리
        when {
            main_drawer_layout.isDrawerOpen(GravityCompat.START) -> {
                main_drawer_layout.closeDrawers()
            }
            System.currentTimeMillis() - mBackWait >= 2000 -> {
                mBackWait = System.currentTimeMillis()
                Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                finish() //액티비티 종료
            }
        }

    }

}