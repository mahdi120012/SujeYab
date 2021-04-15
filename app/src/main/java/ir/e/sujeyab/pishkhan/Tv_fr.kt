package ir.e.sujeyab.pishkhan

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ir.e.sujeyab.CustomClasses.Recyclerview
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.RecyclerAdapter
import ir.e.sujeyab.adapters.RecyclerAdapterSujeHa
import ir.e.sujeyab.adapters.RecyclerAdapterTv
import ir.e.sujeyab.models.FarakhanVijehModel
import ir.e.sujeyab.models.RecyclerModel
import kotlinx.android.synthetic.main.farakhan_ha_fr.view.*
import kotlinx.android.synthetic.main.net_connection.view.*

class Tv_fr : Fragment() {


    var inflatedview: View? = null
    private var rModels2: ArrayList<FarakhanVijehModel>? = null
    private var rAdapterSujeHa: RecyclerAdapterTv? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.tv_fr, container, false)

        rModels2 = ArrayList()
        rAdapterSujeHa = RecyclerAdapterTv("tv", activity, rModels2, rAdapterSujeHa)

        Recyclerview.defineRecyclerViewHorizontal2(activity, inflatedview!!.rv1, rAdapterSujeHa, rModels2)
        LoadData.loadTvBaRetrofit(
            activity,
            inflatedview!!.clWifiState,
            rModels2,
            rAdapterSujeHa
        )


        return inflatedview
    }

}


