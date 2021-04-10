package ir.e.sujeyab.login

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.tabLayout
import kotlinx.android.synthetic.main.login.viewPager
import kotlinx.android.synthetic.main.sabt_fori_suje.*
import kotlinx.android.synthetic.main.setting.*


class Login : AppCompatActivity() {
    //var binding: ActivityFragmentViewPagerBinding? = null

    // tab titles
    private val titles =
        arrayOf("کاربر حقوقی", "کاربر حقیقی")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityFragmentViewPagerBinding.inflate(layoutInflater)
        setContentView(R.layout.login)
        init()

        val imgBack = findViewById<View>(R.id.imgBack) as ImageView
        imgBack.setOnClickListener {
            finish()
        }

        if (SharedPrefClass.getUserId(this, "user") != ""){
            val myFragment: Fragment = TakmilEtelaat()
            supportFragmentManager.beginTransaction().replace(R.id.clcl, myFragment).addToBackStack(null).commit()
        }

        viewPager.setCurrentItem(1)
    }


    private fun init() {
        // removing toolbar elevation
        //supportActionBar!!.elevation = 0f
        viewPager.setAdapter(ViewPagerFragmentAdapter(this))

        // attaching tab mediator
        TabLayoutMediator(tabLayout, viewPager,
                TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                    tab.text = titles[position]
                }
        ).attach()
    }

    private inner class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return KarbarHoghoghi()
                1 -> return KarbarHaghighi()
            }
            return KarbarHaghighi()
        }

        override fun getItemCount(): Int {
            return titles.size
        }
    }
}