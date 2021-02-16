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
import kotlinx.android.synthetic.main.farakhan_ha_fr.view.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.net_connection.view.*
import kotlinx.android.synthetic.main.pishkhan_fr.*
import kotlinx.android.synthetic.main.setting.*
import me.relex.circleindicator.CircleIndicator


class FarakhanHa_fr : Fragment() {
    var inflatedview: View? = null
    private var rAdapter: RecyclerAdapter? = null
    private var rModels: ArrayList<RecyclerModel>? = null
    private var rModels2: ArrayList<RecyclerModel>? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.farakhan_ha_fr, container, false)


        rModels = ArrayList()
        rAdapter = RecyclerAdapter("vaziyat_farakhan", activity, rModels, rAdapter)
        Recyclerview.defineRecyclerViewHorizontal(activity, inflatedview!!.rv1, rAdapter, rModels)
        LoadData.loadVaziyatFarakhan(activity, inflatedview!!.clWifiState, rModels, rAdapter)


        rModels2 = ArrayList()
        rAdapter = RecyclerAdapter("farakhan_ha", activity, rModels2, rAdapter)
        Recyclerview.defineRecyclerViewVertical(activity, inflatedview!!.rv2, rAdapter, rModels2)
        LoadData.loadFarakhanHaBaRetrofit(activity, inflatedview!!.clWifiState, rModels2, rAdapter)



        return inflatedview
    }

}