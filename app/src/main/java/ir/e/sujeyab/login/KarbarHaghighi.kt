package ir.e.sujeyab.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.karbar_hoghoghi.view.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.net_connection.*


class KarbarHaghighi : Fragment() {
    var inflatedview: View? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.karbar_haghighi, container, false)

        inflatedview!!.clVorodBaGoogle.setOnClickListener {
            Toast.makeText(activity,"به زودی راه اندازی می شود",Toast.LENGTH_SHORT).show()
        }

        val clVorodBaNamKarbari = inflatedview!!.findViewById<View>(R.id.clVorodBaNamKarbari) as ConstraintLayout
        val etUsername = inflatedview!!.findViewById<View>(R.id.etUsername) as EditText



        val txEdame = (activity as Login).txEdame

        txEdame.setOnClickListener {
            //Toast.makeText(activity,"کاربر حقیقی",Toast.LENGTH_SHORT).show()
            val fr: Fragment = TaeidShomareTelepohe()
            //fr.arguments = args
            val fm = fragmentManager
            val fragmentTransaction =
                    fm!!.beginTransaction()
            fragmentTransaction.replace(R.id.clcl, fr)
            fragmentTransaction.commit()
            LoadData.checUsernameExsist(activity,(activity as Login).clWifiState,etUsername)

        }

        clVorodBaNamKarbari.setOnClickListener {
            val fr: Fragment = RegisterFr()
            //fr.arguments = args
            val fm = fragmentManager
            val fragmentTransaction = fm!!.beginTransaction()
            fragmentTransaction.replace(R.id.clcl, fr)
            fragmentTransaction.commit()
        }

        return inflatedview
    }

}