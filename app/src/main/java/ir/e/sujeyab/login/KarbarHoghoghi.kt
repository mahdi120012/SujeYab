package ir.e.sujeyab.login

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.karbar_haghighi.*
import kotlinx.android.synthetic.main.karbar_hoghoghi.view.*
import kotlinx.android.synthetic.main.login.*

class KarbarHoghoghi : Fragment() {
    var inflatedview: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflatedview = inflater.inflate(ir.e.sujeyab.R.layout.karbar_hoghoghi, container, false)

        inflatedview!!.clVorodBaGoogle.setOnClickListener {
            Toast.makeText(activity,"به زودی راه اندازی می شود",Toast.LENGTH_SHORT).show()
        }

        return inflatedview

    }
}