package ir.e.sujeyab.bank_suje

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.e.sujeyab.CustomClasses.Recyclerview
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.adapters.CatAdapter
import ir.e.sujeyab.models.CatModel
import kotlinx.android.synthetic.main.moarefi_fr.view.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.net_connection.view.*
import kotlinx.android.synthetic.main.pishkhan_fr.*
import kotlinx.android.synthetic.main.pishkhan_fr.view.*
import java.util.*
import kotlin.collections.ArrayList


class Main_Cat_0 : Fragment() {
    var inflatedview: View? = null

    private var rAdapter: CatAdapter? = null
    private var rModels = ArrayList<CatModel>()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.main_cat0, container, false)
        inflatedview!!.nestedScrollView.visibility = View.GONE

        rModels = ArrayList()
        rAdapter = CatAdapter("cat_0",activity,rModels,rAdapter)
        Recyclerview.defineRecyclerViewVerticalCat(activity, inflatedview!!.rv1, rAdapter, rModels)
        LoadData.loadCat_0(activity, inflatedview!!.clWifiState,rModels,rAdapter,
            inflatedview!!.nestedScrollView,inflatedview!!.progressBar);


        return inflatedview
    }

}