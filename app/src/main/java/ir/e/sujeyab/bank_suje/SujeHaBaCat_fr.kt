package ir.e.sujeyab.bank_suje

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
import ir.e.sujeyab.SabtSuje.snackbar
import ir.e.sujeyab.adapters.RecyclerAdapterSujeHa
import ir.e.sujeyab.adapters.RecyclerAdapterVaziyatSujeha
import ir.e.sujeyab.models.FarakhanVijehModel
import ir.e.sujeyab.models.RecyclerModel
import kotlinx.android.synthetic.main.farakhan_ha_fr.view.rv1
import kotlinx.android.synthetic.main.net_connection.view.*
import kotlinx.android.synthetic.main.suje_ha_ba_cat_fr.view.imgBack
import kotlinx.android.synthetic.main.suje_ha_ba_cat_fr.view.imgSearch
import kotlinx.android.synthetic.main.suje_ha_ba_cat_fr.view.txNameDasteBandi
import kotlinx.android.synthetic.main.suje_ha_fr.*


class SujeHaBaCat_fr : Fragment() {

    var inflatedview: View? = null
    private var rModels2: ArrayList<FarakhanVijehModel>? = null
    private var rAdapterSujeHa: RecyclerAdapterSujeHa? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.suje_ha_ba_cat_fr, container, false)

        val bundle = this.arguments
        val mozo_name: String? = bundle!!.getString("cat")

        rModels2 = ArrayList()
        rAdapterSujeHa = RecyclerAdapterSujeHa("suje_ha", activity, rModels2, rAdapterSujeHa)
        Recyclerview.defineRecyclerViewVertical2(activity, inflatedview!!.rv1, rAdapterSujeHa, rModels2)
        LoadData.loadSujeHaBaMozo1Retrofit(
            activity,
            inflatedview!!.clWifiState,
            rModels2,
            rAdapterSujeHa,mozo_name)

        inflatedview!!.txNameDasteBandi.setText(mozo_name)
        inflatedview!!.imgBack.setOnClickListener {
            activity!!.onBackPressed()
        }

        inflatedview!!.imgSearch.setOnClickListener {
            clcl.snackbar("بزودی راه اندازی می شود")
        }

        return inflatedview
    }

}


