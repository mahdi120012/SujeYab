package ir.e.sujeyab.login

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.karbar_haghighi.*
import kotlinx.android.synthetic.main.login.*

class KarbarHoghoghi : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(ir.e.sujeyab.R.layout.karbar_hoghoghi, container, false)

    }
}