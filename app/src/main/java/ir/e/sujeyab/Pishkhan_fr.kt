package ir.e.sujeyab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import ir.e.sujeyab.CustomClasses.Recyclerview
import ir.e.sujeyab.models.RecyclerModel
import ir.e.sujeyab.models.SliderModel
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.net_connection.view.*
import kotlinx.android.synthetic.main.pishkhan_fr.*
import kotlinx.android.synthetic.main.pishkhan_fr.view.*
import kotlinx.android.synthetic.main.setting.*
import me.relex.circleindicator.CircleIndicator
import java.util.*
import kotlin.collections.ArrayList


class Pishkhan_fr : Fragment() {
    var inflatedview: View? = null
    private var mPager: ViewPager? = null
    private val ImgArray = ArrayList<SliderModel>()
    private var currentPage = 0

    private var mPagerSujeHayeVijeh: ViewPager? = null
    private val ImgArraySujeHayeVijeh = ArrayList<SliderModel>()
    private var currentPageSujeHayeVijeh = 0

    private var mPagerKhedmatHayeVijeh: ViewPager? = null
    private val ImgArrayKhedmatHayeVijeh = ArrayList<SliderModel>()
    private var currentPageKhedmatHayeVijeh = 0

    private var rAdapter: RecyclerAdapter? = null
    private var rModels: ArrayList<RecyclerModel>? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.pishkhan_fr, container, false)
        //val clVorodBaNamKarbari = inflatedview!!.findViewById<View>(R.id.clVorodBaNamKarbari) as ConstraintLayout
        //val etUsername = inflatedview!!.findViewById<View>(R.id.etUsername) as EditText

        LoadData.LoadPishkhanSliderByRetrofit(activity, inflatedview!!.clWifiState, inflatedview!!.pager, inflatedview!!.indicator, ImgArray)

        //LoadData.loadPishkhanSlider(activity, inflatedview!!.clWifiState, inflatedview!!.pager, inflatedview!!.indicator, ImgArray)

        LoadData.loadSujeHayeVijehSlider(activity, inflatedview!!.clWifiState, inflatedview!!.pagerSujeHayeVijeh, inflatedview!!.indicatorSujeHayeVijeh, ImgArraySujeHayeVijeh)

        LoadData.loadKhadamatVijehSlider(activity, inflatedview!!.clWifiState, inflatedview!!.pagerKhedmatHayeVijeh, inflatedview!!.indicatorkhedmatHayeVijeh, ImgArrayKhedmatHayeVijeh)


        rModels = ArrayList()
        rAdapter = RecyclerAdapter("farakhan_vije", activity, rModels, rAdapter)
        Recyclerview.define_recyclerview(activity, inflatedview!!.rv1, rAdapter, rModels)
        //LoadData.loadFarakhanVijeh(activity, inflatedview!!.clWifiState, rModels, rAdapter)
        LoadData.loadFarakhanVijehBaRetrofit(activity, inflatedview!!.clWifiState, rModels, rAdapter)



        //val txEdame = (activity as Login).txEdame

        /*txEdame.setOnClickListener {
            //Toast.makeText(activity,"کاربر حقیقی",Toast.LENGTH_SHORT).show()
            *//*val fr: Fragment = TaeidShomareTelepohe()
            //fr.arguments = args
            val fm = fragmentManager
            val fragmentTransaction =
                    fm!!.beginTransaction()
            fragmentTransaction.replace(R.id.clcl, fr)
            fragmentTransaction.commit()*//*
            LoadData.checUsernameExsist(activity,(activity as Login).clWifiState,etUsername)

        }*/
/*        clVorodBaNamKarbari.setOnClickListener {
            Toast.makeText(activity,"کاربر حقیقی",Toast.LENGTH_SHORT).show()
            val fr: Fragment = VorodBaNamKarbari_FR()
            //fr.arguments = args
            val fm = fragmentManager
            val fragmentTransaction =
                fm!!.beginTransaction()
            fragmentTransaction.replace(R.id.clcl, fr)
            fragmentTransaction.commit()
        }*/

        return inflatedview
    }

/*    private fun init() {
        val handler = Handler()
        val Update = Runnable {
            if (currentPageSujeHayeVijeh === 1) {
                currentPageSujeHayeVijeh = 0
            }
            mPagerSujeHayeVijeh!!.setCurrentItem(currentPageSujeHayeVijeh++, true)
        }
        //Auto start
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 3500, 3500)
    }*/

}