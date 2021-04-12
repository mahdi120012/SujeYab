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
import ir.e.sujeyab.models.FarakhanVijehModel
import ir.e.sujeyab.models.RecyclerModel
import kotlinx.android.synthetic.main.farakhan_ha_fr.view.*
import kotlinx.android.synthetic.main.net_connection.view.*


class SujeHa_fr : Fragment(), View.OnTouchListener {


    private lateinit var gestureDetector: GestureDetector

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        Log.d(TAG, "onTouch: ");
        gestureDetector.onTouchEvent(event);
        return true
    }

    var inflatedview: View? = null
    private var rAdapter: RecyclerAdapter? = null
    private var rModels: ArrayList<RecyclerModel>? = null
    private var rModels2: ArrayList<FarakhanVijehModel>? = null
    private var rAdapterSujeHa: RecyclerAdapterSujeHa? = null
    //var GestureDetector: GestureDetector? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.suje_ha_fr, container, false)




        rModels = ArrayList()
        rAdapter = RecyclerAdapter(
            "vaziyat_farakhan",
            activity,
            rModels,
            rAdapter
        )
        Recyclerview.defineRecyclerViewHorizontal(activity, inflatedview!!.rv1, rAdapter, rModels)
        LoadData.loadVaziyatSujeHa(
            activity,
            inflatedview!!.clWifiState,
            rModels,
            rAdapter
        )


        rModels2 = ArrayList()
        rAdapterSujeHa = RecyclerAdapterSujeHa("suje_ha", activity, rModels2, rAdapterSujeHa)
        Recyclerview.defineRecyclerViewVertical2(activity, inflatedview!!.rv2, rAdapterSujeHa, rModels2)
        LoadData.loadSujeHaBaRetrofit(
            activity,
            inflatedview!!.clWifiState,
            rModels2,
            rAdapterSujeHa
        )

        gestureDetector = GestureDetector(activity, object : OnSwipeListener() {

            override fun onSwipe(direction: Direction): Boolean {

                when(direction){
                    Direction.up ->
                    {
                        Toast.makeText(activity,"asfafas",Toast.LENGTH_SHORT).show()
                        //Log.d(TAG, "onSwipe: up")
                        //sendCommand("UP")
                        return true
                    }
                    Direction.down ->{
                        Toast.makeText(activity,"asfafas",Toast.LENGTH_SHORT).show()
                        return true
                    }

                    Direction.left ->
                    {
                        Toast.makeText(activity,"asfafas",Toast.LENGTH_SHORT).show()
                        return true

                    }
                    Direction.right ->{
                        Toast.makeText(activity,"asfafas",Toast.LENGTH_SHORT).show()
                        return true

                    }
                    else -> {
                    }
                }
                return true
            }


        })


        inflatedview!!.setOnTouchListener(object : View.OnTouchListener {
            @Override
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
               gestureDetector.onTouchEvent(event)
                return true
            }
        })

        return inflatedview
    }

}


