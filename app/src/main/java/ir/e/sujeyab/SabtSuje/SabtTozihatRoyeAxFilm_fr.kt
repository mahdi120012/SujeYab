package ir.e.sujeyab.SabtSuje

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
import ir.e.sujeyab.adapters.MediaAdapter
import ir.e.sujeyab.adapters.RecyclerAdapterSujeHa
import ir.e.sujeyab.adapters.RecyclerAdapterVaziyatSujeha
import ir.e.sujeyab.models.FarakhanVijehModel
import ir.e.sujeyab.models.MediaModel
import ir.e.sujeyab.models.RecyclerModel
import kotlinx.android.synthetic.main.farakhan_ha_fr.view.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.net_connection.view.*
import kotlinx.android.synthetic.main.suje_ha_fr.*


class SabtTozihatRoyeAxFilm_fr : Fragment() {

    var inflatedview: View? = null
    private var rModels: ArrayList<MediaModel>? = null
    private var rAdapter: MediaAdapter? = null
    var post_id: String? = null;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        inflatedview = inflater.inflate(R.layout.post_media_fr, container, false)

        val bundle = this.arguments

        if (bundle != null) {
            post_id = bundle.getString("post_id")
        }


        rModels = ArrayList()
        rAdapter = MediaAdapter("post_media", activity, rModels, rAdapter)
        Recyclerview.defineRecyclerViewVerticalForMedia(activity, inflatedview!!.rv1, rAdapter, rModels)
        LoadData.loadSujeMedia(activity, inflatedview!!.clWifiState, rAdapter, rModels, post_id)


        return inflatedview
    }
}


