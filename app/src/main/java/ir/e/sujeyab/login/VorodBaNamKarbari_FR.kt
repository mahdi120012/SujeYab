package ir.e.sujeyab.login

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.e.sujeyab.LoadData
import kotlinx.android.synthetic.main.karbar_haghighi.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.net_connection.*
import kotlinx.android.synthetic.main.taeid_shomare_telephone.*

class VorodBaNamKarbari_FR : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(ir.e.sujeyab.R.layout.karbar_haghighi, container, false)




    }
}