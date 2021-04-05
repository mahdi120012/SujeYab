package ir.e.sujeyab.SujeClick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import ir.e.sujeyab.R
import ir.e.sujeyab.SabtSuje.SabtFr
import kotlinx.android.synthetic.main.suje_click2.*

class SujeClick2 : AppCompatActivity() {
    private val titles = arrayOf("نظرات","مستندات","مشخصات","معرفی")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.suje_click2)

        init()
        imgBack.setOnClickListener { finish() }
        viewPager.setCurrentItem(3)

    }
    private fun init(){
        viewPager!!.setAdapter(ViewPagerFragmentAdapter(this))
        TabLayoutMediator(tabLayout,viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = titles[position]
        }).attach()

        tabLayout.clearOnTabSelectedListeners()
        for (v in tabLayout.touchables){
            v.setEnabled(false)
        }
    }


    private inner class ViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            when(position){
                0-> return CommentFr()
                1-> return MoarefiFr()
                2-> return MoarefiFr()
                3-> return MoarefiFr()
            }
            return MoarefiFr()

        }

        override fun getItemCount(): Int {
            return titles.size
        }
    }

}