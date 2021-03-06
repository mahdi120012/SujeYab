package ir.e.sujeyab.pishkhan

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.javiersantos.appupdater.AppUpdater
import com.github.javiersantos.appupdater.enums.UpdateFrom
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.R
import ir.e.sujeyab.SabtSuje.SabtForiSuje
import ir.e.sujeyab.Setting
import ir.e.sujeyab.bank_suje.Main_Cat_0
import ir.e.sujeyab.login.Login
import ir.e.sujeyab.login.TakmilEtelaat
import kotlinx.android.synthetic.main.pishkhan.*
import kotlinx.android.synthetic.main.toolbar_button.*


class Pishkhan : AppCompatActivity() {

    // tab titles
    private val titles =
        arrayOf("TV","سوژه ها", "فراخوان ها", "پیشخوان")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityFragmentViewPagerBinding.inflate(layoutInflater)
        setContentView(R.layout.pishkhan)

        val appUpdater = AppUpdater(this).setUpdateFrom(UpdateFrom.JSON).
        setUpdateJSON("http://robika.ir/ultitled/practice/sujeyab/sujeyab_update_checker.json").
        setTitleOnUpdateAvailable("بروزرسانی جدید موجوده!").setButtonUpdate("بروزرسانی").
        setButtonDismiss("فعلا نه").setButtonDoNotShowAgain("")
        appUpdater.start()
        init()

        val imgSearch = findViewById<View>(R.id.imgSearch) as ImageView

        viewPager.setCurrentItem(3)

        imgHome.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.home_zard
        ));
        txHome.setTextColor(Color.BLACK)

        clSujeHa.setOnClickListener {
            val myFragment: Fragment = Main_Cat_0()
            supportFragmentManager.beginTransaction().replace(R.id.clcl, myFragment).addToBackStack(null).commit()
        }

        clSabtSuje.setOnClickListener {

            var username = SharedPrefClass.getUserId(this,"user")

            if (username == ""){
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }else{

                val intent = Intent(this, SabtForiSuje::class.java)
                startActivity(intent)
            }
        }

        clElanat.setOnClickListener {
            Toast.makeText(this,"به زودی راه اندازی می شود",Toast.LENGTH_SHORT).show()

        }

        clMan.setOnClickListener {
            startActivity(Intent(this, Setting::class.java))
        }

        imgSearch.setOnClickListener {
            val myFragment: Fragment = Search_fr()
            supportFragmentManager.beginTransaction().replace(R.id.clcl, myFragment).addToBackStack(null).commit()
        }

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
                0 -> return Tv_fr()
                1 -> return SujeHa_fr()
                2 -> return FarakhanHa_fr()
                3 -> return Pishkhan_fr()
            }
            return Pishkhan_fr()
        }

        override fun getItemCount(): Int {
            return titles.size
        }
    }
}