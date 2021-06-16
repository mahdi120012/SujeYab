package ir.e.sujeyab.pishkhan

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


class SujeHa_fr : Fragment() {

    fun onTouch(v: View?, event: MotionEvent?): Boolean {
        Log.d(TAG, "onTouch: ");
        return true }

    var inflatedview: View? = null
    private var rAdapter: RecyclerAdapter? = null
    private var rModels: ArrayList<RecyclerModel>? = null
    private var rModels2: ArrayList<FarakhanVijehModel>? = null
    private var rAdapterSujeHa: RecyclerAdapterSujeHa? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        inflatedview = inflater.inflate(R.layout.suje_ha_fr, container, false)

        rModels = ArrayList()
        rAdapter = RecyclerAdapter("vaziyat_suje_ha", activity, rModels, rAdapter,rModels2,rAdapterSujeHa, inflatedview!!.rv2 , inflatedview!!.clWifiState)
        Recyclerview.defineRecyclerViewHorizontal(activity, inflatedview!!.rv1, rAdapter, rModels)
        LoadData.loadVaziyatSujeHa(activity, inflatedview!!.clWifiState, rModels, rAdapter)

        rModels2 = ArrayList()
        rAdapterSujeHa = RecyclerAdapterSujeHa("suje_ha", activity, rModels2, rAdapterSujeHa)
        Recyclerview.defineRecyclerViewVertical2(activity, inflatedview!!.rv2, rAdapterSujeHa, rModels2)
        LoadData.loadSujeHaBaRetrofit(activity, inflatedview!!.clWifiState, rModels2, rAdapterSujeHa)

        return inflatedview
    }
}


