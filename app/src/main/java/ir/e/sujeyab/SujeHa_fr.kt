package ir.e.sujeyab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import ir.e.sujeyab.CustomClasses.Recyclerview
import ir.e.sujeyab.models.RecyclerModel
import ir.e.sujeyab.models.SliderModel
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.pishkhan_fr.*
import kotlinx.android.synthetic.main.setting.*
import me.relex.circleindicator.CircleIndicator


class SujeHa_fr : Fragment() {
    var inflatedview: View? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.suje_ha_fr, container, false)

        return inflatedview
    }

}