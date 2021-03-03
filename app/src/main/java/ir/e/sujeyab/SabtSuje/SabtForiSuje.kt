package ir.e.sujeyab.SabtSuje

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.sabt_fori_suje.*


class SabtForiSuje : AppCompatActivity() {
    private val titles =
        arrayOf("ثبت", "ورود", "ویژگی ها", "طرح سوژه")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sabt_fori_suje)
        init()

        imgBack.setOnClickListener { finish() }
        viewPager.setCurrentItem(3)
        //line zir baraye ine ke"
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
    }

    private inner class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return SabtFr()
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