package ir.e.sujeyab.SabtSuje

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import ir.e.sujeyab.CustomClasses.Recyclerview
import ir.e.sujeyab.R
import ir.e.sujeyab.RecyclerAdapter
import ir.e.sujeyab.models.RecyclerModel
import ir.e.sujeyab.models.SliderModel
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.net_connection.view.*
import kotlinx.android.synthetic.main.pishkhan_fr.*
import kotlinx.android.synthetic.main.pishkhan_fr.view.*
import java.util.*
import kotlin.collections.ArrayList


class TarhSujeFr : Fragment() {
    var inflatedview: View? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.tarh_suje_fr, container, false)


        return inflatedview
    }

}