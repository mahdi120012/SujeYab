package ir.e.sujeyab.pishkhan

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ir.e.sujeyab.CustomClasses.Recyclerview
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.RecyclerAdapter
import ir.e.sujeyab.adapters.RecyclerAdapterSujeHa
import ir.e.sujeyab.adapters.RecyclerAdapterTv
import ir.e.sujeyab.models.FarakhanVijehModel
import ir.e.sujeyab.models.RecyclerModel
import kotlinx.android.synthetic.main.farakhan_ha_fr.view.*
import kotlinx.android.synthetic.main.farakhan_ha_fr.view.rv1
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.net_connection.view.*
import kotlinx.android.synthetic.main.tv_fr.view.*

class Tv_fr : Fragment(), SwipeRefreshLayout.OnRefreshListener {


    var inflatedview: View? = null
    private var rModels2: ArrayList<FarakhanVijehModel>? = null
    private var rAdapterSujeHa: RecyclerAdapterTv? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.tv_fr, container, false)


        // SwipeRefreshLayout
        inflatedview!!.swiperefresh.setOnRefreshListener(this)
        inflatedview!!.swiperefresh.setColorSchemeResources(
            R.color.systemColor,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark
        )

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */


        inflatedview!!.swiperefresh.post {
            //swiperefresh!!.isRefreshing = true

            var username = SharedPrefClass.getUserId(activity,"user")
            rModels2 = ArrayList()
            rAdapterSujeHa = RecyclerAdapterTv("tv", activity, rModels2, rAdapterSujeHa)
            Recyclerview.defineRecyclerViewHorizontal2(activity, inflatedview!!.rv1, rAdapterSujeHa, rModels2)

            LoadData.loadTvBaVolley(activity,inflatedview!!.clWifiState,rModels2,rAdapterSujeHa,username,inflatedview!!.swiperefresh)

        }


        return inflatedview
    }

    override fun onRefresh() {

        //Toast.makeText(activity,"refresh shod",Toast.LENGTH_SHORT).show()
        var username = SharedPrefClass.getUserId(activity,"user")
        rModels2 = ArrayList()
        rAdapterSujeHa = RecyclerAdapterTv("tv", activity, rModels2, rAdapterSujeHa)
        Recyclerview.defineRecyclerViewHorizontal2(activity, inflatedview!!.rv1, rAdapterSujeHa, rModels2)

        LoadData.loadTvBaVolley(activity,inflatedview!!.clWifiState,rModels2,rAdapterSujeHa,username,inflatedview!!.swiperefresh)
    }

}


