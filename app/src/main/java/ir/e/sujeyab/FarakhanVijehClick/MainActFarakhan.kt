package ir.e.sujeyab.SujeClick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import ir.e.sujeyab.R
import ir.e.sujeyab.pishkhan.SujeHa_fr
import kotlinx.android.synthetic.main.farakhan_main_act.*

class MainActFarakhan : AppCompatActivity() {
    private val titles = arrayOf("نظرات","سوژه ها","معرفی")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.farakhan_main_act)

        init()
        imgBack.setOnClickListener { finish() }
        viewPager.setCurrentItem(2)

    }
    private fun init(){
        viewPager!!.setAdapter(ViewPagerFragmentAdapter(this))
        TabLayoutMediator(tabLayout,viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = titles[position]
        }).attach()

       /* tabLayout.clearOnTabSelectedListeners()
        for (v in tabLayout.touchables){
            v.setEnabled(false)
        }*/
    }


    private inner class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            when(position){
                0-> return CommentFarakhanFr()
                1-> return SujeHa_fr()
                2-> return MoarefiFarakhanFr()
            }
            return MoarefiFarakhanFr()

        }

        override fun getItemCount(): Int {
            return titles.size
        }
    }

}
