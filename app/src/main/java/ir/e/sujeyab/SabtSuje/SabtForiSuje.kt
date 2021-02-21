package ir.e.sujeyab.SabtSuje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import ir.e.sujeyab.R
import ir.e.sujeyab.login.VorodBaNamKarbari_FR
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
                0 -> return VorodBaNamKarbari_FR()
                1 -> return VorodBaNamKarbari_FR()
                2 -> return VorodBaNamKarbari_FR()
                3 -> return TarhSujeFr()
            }
            return TarhSujeFr()
        }

        override fun getItemCount(): Int {
            return titles.size
        }
    }
}