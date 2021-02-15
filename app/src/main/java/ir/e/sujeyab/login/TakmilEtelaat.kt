package ir.e.sujeyab.login

import android.annotation.SuppressLint
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar
import ir.e.sujeyab.CustomClasses.EnglishNumberToPersian
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.takmil_etelaat.*
import java.lang.reflect.Array.newInstance


class TakmilEtelaat : Fragment(),TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    var inflatedview: View? = null

    var jensiyatSp:String = ""
    var vaziyatTaaholSp :String = ""
    var vaziyatNezamVazifeSp :String = ""
    var akharinMadrakTahsiliSp:String = ""
    var keshvarSp:String = ""
    var ostanSp:String = ""
    var shahrestanSp:String = ""
    var shahrSp:String = ""
    lateinit var etTarikhTavalod2: EditText


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        inflatedview = inflater.inflate(ir.e.sujeyab.R.layout.takmil_etelaat, container, false)

        val tabLayout = (activity as Login).tabLayout
        tabLayout.visibility = View.GONE

        val viewPager = (activity as Login).viewPager
        viewPager.setUserInputEnabled(false);

        val txEdame2 = inflatedview!!.findViewById<View>(R.id.txEdame2) as TextView


        val spinner_jensiyat: Spinner = inflatedview!!.findViewById(R.id.spinner_jensiyat)
        etTarikhTavalod2 = inflatedview!!.findViewById(R.id.etTarikhTavalod)



        etTarikhTavalod2.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val persianCalendar = PersianCalendar()
                val datePickerDialog = DatePickerDialog.newInstance(
                        this,
                        persianCalendar.persianYear,
                        persianCalendar.persianMonth,
                        persianCalendar.persianDay
                )
                datePickerDialog.show((activity as Login).fragmentManager, "TarikhShoro")
                return@OnTouchListener true
            }
            false
        })


        //etTarikhTavalod2.setOnClickListener { Toast.makeText(activity,"salem",Toast.LENGTH_SHORT).show() }



        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                activity as Login,
                R.array.jensiyat,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.custom_spinner_item)
            // Apply the adapter to the spinner
            spinner_jensiyat.adapter = adapter

            spinner_jensiyat.setOnItemSelectedListener(object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    jensiyatSp = adapter.getItem(position).toString()
                    if (jensiyatSp == ""){

                    }else{
                        etJensiyat.setText(adapter.getItem(position).toString())
                        (spinner_jensiyat.getSelectedView() as TextView).setText("")
                    }


                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //etJensiyat.setText("")
                }
            })

        }


        val spinnerVaziyatTaahol: Spinner = inflatedview!!.findViewById(R.id.spinnerVaziyatTaahol)
            // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                activity as Login,
                R.array.vaziyat_taahol,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.custom_spinner_item)
            // Apply the adapter to the spinner
            spinnerVaziyatTaahol.adapter = adapter

            spinnerVaziyatTaahol.setOnItemSelectedListener(object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    vaziyatTaaholSp = adapter.getItem(position).toString()
                    if (vaziyatTaaholSp == ""){

                    }else{
                        etVaziyatTaahol.setText(adapter.getItem(position).toString())
                        (spinnerVaziyatTaahol.getSelectedView() as TextView).setText("")
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })
        }





        val spinnerVaziyatNezamVazife: Spinner = inflatedview!!.findViewById(R.id.spinnerVaziyatNezamVazife)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                activity as Login,
                R.array.vaziyat_nezam_vazife,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.custom_spinner_item)
            // Apply the adapter to the spinner
            spinnerVaziyatNezamVazife.adapter = adapter

            spinnerVaziyatNezamVazife.setOnItemSelectedListener(object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    vaziyatNezamVazifeSp = adapter.getItem(position).toString()
                    if (vaziyatNezamVazifeSp == ""){

                    }else{
                        etVaziyatNezamVazife.setText(adapter.getItem(position).toString())
                        (spinnerVaziyatNezamVazife.getSelectedView() as TextView).setText("")
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })
        }


        val spinnerKeshvar: Spinner = inflatedview!!.findViewById(R.id.spinnerKeshvar)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                activity as Login,
                R.array.keshvar,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.custom_spinner_item)
            // Apply the adapter to the spinner
            spinnerKeshvar.adapter = adapter

            spinnerKeshvar.setOnItemSelectedListener(object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    keshvarSp = adapter.getItem(position).toString()
                    if (keshvarSp == ""){

                    }else{
                        etKeshvar.setText(adapter.getItem(position).toString())
                        (spinnerKeshvar.getSelectedView() as TextView).setText("")
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })
        }

        val spinnerOstan: Spinner = inflatedview!!.findViewById(R.id.spinnerOstan)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                activity as Login,
                R.array.ostan,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.custom_spinner_item)
            // Apply the adapter to the spinner
            spinnerOstan.adapter = adapter

            spinnerOstan.setOnItemSelectedListener(object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    ostanSp = adapter.getItem(position).toString()
                    if (ostanSp == ""){

                    }else{
                        etOstan.setText(adapter.getItem(position).toString())
                        (spinnerOstan.getSelectedView() as TextView).setText("")
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })
        }


        val spinnerShahrestan: Spinner = inflatedview!!.findViewById(R.id.spinnerShahrestan)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                activity as Login,
                R.array.shahrestan,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.custom_spinner_item)
            // Apply the adapter to the spinner
            spinnerShahrestan.adapter = adapter

            spinnerShahrestan.setOnItemSelectedListener(object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    shahrestanSp = adapter.getItem(position).toString()
                    if (shahrestanSp == ""){

                    }else{
                        etShahrestan.setText(adapter.getItem(position).toString())
                        (spinnerShahrestan.getSelectedView() as TextView).setText("")
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })
        }


        val spinnerShahr: Spinner = inflatedview!!.findViewById(R.id.spinnerSahr)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                activity as Login,
                R.array.shahr,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.custom_spinner_item)
            // Apply the adapter to the spinner
            spinnerShahr.adapter = adapter

            spinnerShahr.setOnItemSelectedListener(object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    shahrSp = adapter.getItem(position).toString()
                    if (shahrSp == ""){

                    }else{
                        etShahr.setText(adapter.getItem(position).toString())
                        (spinnerShahr.getSelectedView() as TextView).setText("")
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })
        }





        val spinnerAkharinMadrakTahsili: Spinner = inflatedview!!.findViewById(R.id.spinnerAkharinMadrakTahsili)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                activity as Login,
                R.array.akharin_madrak_tahsili,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.custom_spinner_item)
            // Apply the adapter to the spinner
            spinnerAkharinMadrakTahsili.adapter = adapter

            spinnerAkharinMadrakTahsili.setOnItemSelectedListener(object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    akharinMadrakTahsiliSp = adapter.getItem(position).toString()
                    if (akharinMadrakTahsiliSp == ""){

                    }else{
                        etAkharinMadrakTahsili.setText(adapter.getItem(position).toString())
                        (spinnerAkharinMadrakTahsili.getSelectedView() as TextView).setText("")
                    }

                }


                override fun onNothingSelected(parent: AdapterView<*>?) {}
            })
        }


        val clWifiState = inflatedview!!.findViewById<View>(R.id.clWifiState) as ConstraintLayout
        var imgProfileImage = inflatedview!!.findViewById<View>(R.id.imgProfileImage) as ImageView
        var etNameFamily = inflatedview!!.findViewById<View>(R.id.etNameFamily) as EditText
        var etTarikhTavalod = inflatedview!!.findViewById<View>(R.id.etTarikhTavalod) as EditText
        var etJensiyat =inflatedview!!.findViewById<View>(R.id.etJensiyat) as EditText
        var etVaziyatTaahol = inflatedview!!.findViewById<View>(R.id.etVaziyatTaahol) as EditText
        var etVaziyatNezamVazife =inflatedview!!.findViewById<View>(R.id.etVaziyatNezamVazife) as EditText
        var etAkharinMadrakTahsili = inflatedview!!.findViewById<View>(R.id.etAkharinMadrakTahsili) as EditText
        var etMoadelMadrakTahsili =inflatedview!!.findViewById<View>(R.id.etMoadelMadrakTahsili) as EditText
        var etReshteTahsili = inflatedview!!.findViewById<View>(R.id.etReshteTahsili) as EditText
        var etZamineMoredAlaghaHamkari = inflatedview!!.findViewById<View>(R.id.etZamineMoredAlaghaHamkari) as EditText
        var etMizanSabegheKarMortabet = inflatedview!!.findViewById<View>(R.id.etMizanSabegheKarMortabet) as EditText
        var etSematShoghli = inflatedview!!.findViewById<View>(R.id.etSematShoghli) as EditText
        var etCodePerseneli = inflatedview!!.findViewById<View>(R.id.etCodePerseneli) as EditText
        var etEmail = inflatedview!!.findViewById<View>(R.id.etEmail) as EditText
        var etShomaeTelephoneTamas =inflatedview!!.findViewById<View>(R.id.etShomaeTelephoneTamas) as EditText
        var etKeshvar = inflatedview!!.findViewById<View>(R.id.etKeshvar) as EditText
        var etOstan = inflatedview!!.findViewById<View>(R.id.etOstan) as EditText
        var etShahrestan = inflatedview!!.findViewById<View>(R.id.etShahrestan) as EditText
        var etShahr = inflatedview!!.findViewById<View>(R.id.etShahr) as EditText
        var etRosta =inflatedview!!.findViewById<View>(R.id.etRosta) as EditText
        var etNeshani = inflatedview!!.findViewById<View>(R.id.etNeshani) as EditText
        var etMoaref = inflatedview!!.findViewById<View>(R.id.etMoaref) as EditText
        var etTelephoneTamasMoaref = inflatedview!!.findViewById<View>(R.id.etTelephoneTamasMoaref) as EditText
        var etTozihat = inflatedview!!.findViewById<View>(R.id.etTozihat) as EditText


        LoadData.loadMoshakhasatBaRetrofit(activity, clWifiState, etNameFamily, etTarikhTavalod, etJensiyat, etVaziyatTaahol, etVaziyatNezamVazife, etAkharinMadrakTahsili, etMoadelMadrakTahsili, etReshteTahsili, etZamineMoredAlaghaHamkari, etMizanSabegheKarMortabet, etSematShoghli, etCodePerseneli, etEmail, etShomaeTelephoneTamas,
                etKeshvar, etOstan, etShahrestan, etShahr, etRosta, etNeshani, etMoaref, etTelephoneTamasMoaref, etTozihat,imgProfileImage)

        txEdame2.setOnClickListener {
            //Toast.makeText(activity, "تغییری ایجاد نشده", Toast.LENGTH_SHORT).show()
            var nameFamily = etNameFamily.text.toString()
            var tarikhTavallod = etTarikhTavalod.text.toString()
            var jensiyat = etJensiyat.text.toString()
            var vaziyatTaahol = etVaziyatTaahol.text.toString()
            var vaziyatNezamVazife = etVaziyatNezamVazife.text.toString()
            var akharinMadrakTahsili = etAkharinMadrakTahsili.text.toString()
            var moadelMadrakTahsili = etMoadelMadrakTahsili.text.toString()
            var reshteTahsili = etReshteTahsili.text.toString()
            var zaminehMoredAlagheHamkari = etZamineMoredAlaghaHamkari.text.toString()
            var mizanSabegheKarMortabet = etMizanSabegheKarMortabet.text.toString()
            var sematShoghli = etSematShoghli.text.toString()
            var codePerseneli = etCodePerseneli.text.toString()
            var email = etEmail.text.toString()
            var shomaeTelephoneTamas = etShomaeTelephoneTamas.text.toString()
            var keshvar = etKeshvar.text.toString()
            var ostan = etOstan.text.toString()
            var shahrestan = etShahrestan.text.toString()
            var shahr = etShahr.text.toString()
            var rosta = etRosta.text.toString()
            var neshani = etNeshani.text.toString()
            var moaref = etMoaref.text.toString()
            var telephoneTamasMoaref = etTelephoneTamasMoaref.text.toString()
            var tozihat = etTozihat.text.toString()

            LoadData.editMoshakhasatMan(activity, clWifiState, SharedPrefClass.getUserId(activity, "user"), nameFamily, tarikhTavallod, jensiyat, vaziyatTaahol, vaziyatNezamVazife, akharinMadrakTahsili, moadelMadrakTahsili, reshteTahsili, zaminehMoredAlagheHamkari, mizanSabegheKarMortabet, sematShoghli, codePerseneli, email, shomaeTelephoneTamas, keshvar, ostan, shahrestan, shahr, rosta, neshani, moaref, telephoneTamasMoaref, tozihat, jensiyatSp, vaziyatTaaholSp, vaziyatNezamVazifeSp, akharinMadrakTahsiliSp)
        }



        return inflatedview
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {

        val month = monthOfYear + 1
        var fm = "" + month
        var fd = "" + dayOfMonth
        if (month < 10) {
            fm = "0$month"
        }
        if (dayOfMonth < 10) {
            fd = "0$dayOfMonth"
        }

        val date = "$year/$fm/$fd"

        if (view.toString().contains("TarikhShoro")){
            etTarikhTavalod2.setText(EnglishNumberToPersian().convert(date))
        }
    }

    override fun onTimeSet(view: RadialPickerLayout?, hourOfDay: Int, minute: Int) {
        TODO("Not yet implemented")
    }


}
