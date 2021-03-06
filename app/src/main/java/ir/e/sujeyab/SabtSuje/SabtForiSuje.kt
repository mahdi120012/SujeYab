package ir.e.sujeyab.SabtSuje

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.CustomClasses.UrlEncoderClass
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.karbar_haghighi.*
import kotlinx.android.synthetic.main.sabt_fori_suje.*


class SabtForiSuje : AppCompatActivity() {
    private val titles = arrayOf("ثبت", "ورود", "ویژگی ها", "طرح سوژه")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sabt_fori_suje)
        init()

        imgBack.setOnClickListener { finish() }
        viewPager.setCurrentItem(3)
        viewPager.setOffscreenPageLimit(3);
        viewPager.setUserInputEnabled(false);

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

        //لاین زیر برای غیرفعال کردن کلیک روی ایتم های تب ویو در ویو پیجره
        tabLayout.clearOnTabSelectedListeners()
        for (v in tabLayout.getTouchables()) {
            v.setEnabled(false)
        }


        var username = SharedPrefClass.getUserId(this,"user")
        if (username == ""){

        }else{
            (tabLayout.getTabAt(1)!!.view as LinearLayout).visibility = View.GONE
        }

    }

    private inner class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 ->return SabtFr()
                1 -> return VorodFr()
                2 -> return VijegiHaFr()
                3 -> return TarhSujeFr()
            }
            return TarhSujeFr()
        }

        override fun getItemCount(): Int {
            return titles.size
        }
    }
}