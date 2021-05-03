package ir.e.sujeyab.pishkhan

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.e.sujeyab.CustomClasses.Recyclerview
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.adapters.RecyclerAdapterSujeHa
import ir.e.sujeyab.models.FarakhanVijehModel
import kotlinx.android.synthetic.main.farakhan_ha_fr.view.*
import kotlinx.android.synthetic.main.farakhan_ha_fr.view.rv1
import kotlinx.android.synthetic.main.net_connection.view.*
import kotlinx.android.synthetic.main.search_fr.view.*


class Search_fr : Fragment(), View.OnTouchListener {


    private lateinit var gestureDetector: GestureDetector

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        Log.d(TAG, "onTouch: ");
        gestureDetector.onTouchEvent(event);
        return true
    }

    var inflatedview: View? = null
    private var rModels: ArrayList<FarakhanVijehModel>? = null
    private var rAdapterSujeHa: RecyclerAdapterSujeHa? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.search_fr, container, false)


        //var onvan:String = activity!!.intent.extras!!.getString("onvan").toString()



        inflatedview!!.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                /*if (list.contains(query)) {
                    adapter.getFilter().filter(query)
                } else {
                    Toast.makeText(activity, "No Match found", Toast.LENGTH_LONG).show()
                }*/
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                rModels = ArrayList()
                rAdapterSujeHa = RecyclerAdapterSujeHa("suje_ha", activity, rModels, rAdapterSujeHa)
                Recyclerview.defineRecyclerViewVertical2(activity, inflatedview!!.rv1, rAdapterSujeHa, rModels)
                LoadData.loadSearchResult(activity, inflatedview!!.clWifiState, rModels, rAdapterSujeHa, newText)
                return false
            }
        })

        inflatedview!!.searchView.setIconified(false);

        return inflatedview
    }

}


