package ir.e.sujeyab.FarakhanVijehClick

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.e.sujeyab.CustomClasses.OnSwipeListener
import ir.e.sujeyab.CustomClasses.Recyclerview
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.RecyclerAdapter
import ir.e.sujeyab.adapters.RecyclerAdapterSujeHa
import ir.e.sujeyab.adapters.RecyclerAdapterVaziyatSujeha
import ir.e.sujeyab.models.FarakhanVijehModel
import ir.e.sujeyab.models.RecyclerModel
import kotlinx.android.synthetic.main.farakhan_ha_fr.view.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.net_connection.view.*
import kotlinx.android.synthetic.main.suje_ha_fr.*


class SujeHaDarFarakhanFr : Fragment() {

    var inflatedview: View? = null
    private var rModels: ArrayList<FarakhanVijehModel>? = null
    private var rAdapterSujeHa: RecyclerAdapterSujeHa? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.suje_ha_dar_farakhan, container, false)
        var postId:String = activity!!.intent.extras!!.getString("id").toString()

//        rModels = ArrayList()
//        rAdapter = RecyclerAdapterVaziyatSujeha(
//            "vaziyat_suje_ha",
//            activity,
//            rModels,
//            rAdapter
//        )
//        Recyclerview.defineRecyclerViewHorizontalVaziyat(activity, inflatedview!!.rv1, rAdapter, rModels)
//        LoadData.loadVaziyatSujeHaBaRetrofit(
//            activity,
//            inflatedview!!.clWifiState,
//            rModels,
//            rAdapter
//        )

/*        rModels = ArrayList()
        rAdapter = RecyclerAdapter(
            "vaziyat_suje_ha",
            activity,
            rModels,
            rAdapter,rModels2,rAdapterSujeHa, inflatedview!!.rv2 , inflatedview!!.clWifiState)
        Recyclerview.defineRecyclerViewHorizontal(activity, inflatedview!!.rv1, rAdapter, rModels)
        LoadData.loadVaziyatSujeHa(
            activity,
            inflatedview!!.clWifiState,
            rModels,
            rAdapter
        )*/

        rModels = ArrayList()
        rAdapterSujeHa = RecyclerAdapterSujeHa("suje_ha", activity, rModels, rAdapterSujeHa)
        Recyclerview.defineRecyclerViewVertical2(activity, inflatedview!!.rv2, rAdapterSujeHa, rModels)
        LoadData.loadSujeHaBaRetrofitDarFarakhan(activity,inflatedview!!.clWifiState,rModels, rAdapterSujeHa,postId)

        return inflatedview
    }

}


