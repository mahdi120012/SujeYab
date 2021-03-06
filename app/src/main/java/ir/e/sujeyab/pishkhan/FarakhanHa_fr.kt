package ir.e.sujeyab.pishkhan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.e.sujeyab.CustomClasses.Recyclerview
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.RecyclerAdapter
import ir.e.sujeyab.adapters.RadapterVaziyatFarakhan
import ir.e.sujeyab.models.RecyclerModel
import kotlinx.android.synthetic.main.farakhan_ha_fr.view.*
import kotlinx.android.synthetic.main.net_connection.view.*


class FarakhanHa_fr : Fragment() {
    var inflatedview: View? = null
    private var rAdapter: RecyclerAdapter? = null
    private var rAdapterVaziyatFarakhan: RadapterVaziyatFarakhan? = null
    private var rModels: ArrayList<RecyclerModel>? = null
    private var rModels2: ArrayList<RecyclerModel>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View?

    {
        inflatedview = inflater.inflate(R.layout.farakhan_ha_fr, container, false)

        rModels = ArrayList()
        rAdapterVaziyatFarakhan = RadapterVaziyatFarakhan("vaziyat_farakhan",activity,rModels,rAdapterVaziyatFarakhan,
        rModels2,rAdapter,inflatedview!!.rv2,inflatedview!!.clWifiState)

        Recyclerview.defineRecyclerViewHorizontalVaziyatFarakhan(activity, inflatedview!!.rv1, rAdapterVaziyatFarakhan, rModels)
        LoadData.loadVaziyatFarakhan(activity,inflatedview!!.clWifiState,rModels,rAdapterVaziyatFarakhan)


        rModels2 = ArrayList()
        rAdapter = RecyclerAdapter("farakhan_ha",activity,rModels2,rAdapter)
        Recyclerview.defineRecyclerViewVertical(activity, inflatedview!!.rv2, rAdapter, rModels2)
        LoadData.loadFarakhanHaBaRetrofit(activity,inflatedview!!.clWifiState,rModels2,rAdapter)

        return inflatedview
    }
}