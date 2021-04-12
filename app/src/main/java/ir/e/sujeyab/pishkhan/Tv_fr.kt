package ir.e.sujeyab.pishkhan

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ir.e.sujeyab.R

class Tv_fr : Fragment() {


    var inflatedview: View? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.tv_fr, container, false)

        return inflatedview
    }

}


