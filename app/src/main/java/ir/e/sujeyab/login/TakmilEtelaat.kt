package ir.e.sujeyab.login


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.github.dhaval2404.imagepicker.ImagePicker
import com.mapbox.android.core.location.*
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar
import ir.e.sujeyab.Controller.ApiForUpload
import ir.e.sujeyab.CustomClasses.EnglishNumberToPersian
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.SabtSuje.*
import ir.map.sdk_map.MapirStyle
import kotlinx.android.synthetic.main.button_sabt_fori_suje.view.clEdame
import kotlinx.android.synthetic.main.login.tabLayout
import kotlinx.android.synthetic.main.login.viewPager
import kotlinx.android.synthetic.main.takmil_etelaat.*
import kotlinx.android.synthetic.main.takmil_etelaat.progress_bar
import kotlinx.android.synthetic.main.takmil_etelaat.view.*
import kotlinx.android.synthetic.main.vijegiha_fr.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.ref.WeakReference
import java.util.*


open class TakmilEtelaat : Fragment(),TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, UploadRequestBody.UploadCallback {
    var inflatedview: View? = null
    private var arrayList: ArrayList<Uri>? = null
    var jensiyatSp:String = ""
    var vaziyatTaaholSp :String = ""
    var vaziyatNezamVazifeSp :String = ""
    var akharinMadrakTahsiliSp:String = ""
    var keshvarSp:String = ""
    var ostanSp:String = ""
    var shahrestanSp:String = ""
    var shahrSp:String = ""
    lateinit var etTarikhTavalod2: EditText

    var map: MapboxMap? = null
    var mapStyle: Style? = null
    //var mapView: ir.map.sdk_map.maps.MapView? = null
    var lastKnowLatLng: LatLng? = null

    // This is an object to manage location update
    private var locationEngine: LocationEngine? = null

    // These are two number to handle interval of location update and its delay
    private val DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L
    private val DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5

    // Declare an object with CustomLocationCallBack class type
    private val callback = MyLocationCallback(this)

    // This is a custom Class to manage last known location
    private class MyLocationCallback internal constructor(activity: TakmilEtelaat) : LocationEngineCallback<LocationEngineResult> {
        private val activityWeakReference: WeakReference<TakmilEtelaat>

        /**
         * The LocationEngineCallback interface's method which fires when the device's location has changed.
         *
         * @param result the LocationEngineResult object which has the last known location within it.
         */
        override fun onSuccess(result: LocationEngineResult) {
            val activity = activityWeakReference.get()
            if (activity != null) {
                val location = result.lastLocation ?: return
                // Here you access last know location
                activity.lastKnowLatLng = LatLng(location.latitude, location.longitude)
                //Toast.makeText(this, activity.lastKnowLatLng!!.latitude.toString() + ", " + activity.lastKnowLatLng!!.longitude, Toast.LENGTH_SHORT).show()
                // Pass the new location to the Mapir SDK's LocationComponent
                if (activity.map != null && result.lastLocation != null) activity.map!!.locationComponent.forceLocationUpdate(result.lastLocation)
            }
        }

        /**
         * The LocationEngineCallback interface's method which fires when the device's location can not be captured
         *
         * @param exception the exception message
         */
        override fun onFailure(exception: Exception) {
            Log.d("LocationChangeActivity", exception.localizedMessage)
            val activity = activityWeakReference.get()
            //if (activity != null) Toast.makeText(activity, "exception.localizedMessage.toString()", Toast.LENGTH_SHORT).show()
        }

        init {
            activityWeakReference = WeakReference(activity)
        }
    }


    // Call this method to enable location update and see it in map
    @SuppressLint("MissingPermission")
    private fun enableLocationComponent() {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(activity)) {
            // Create and customize the LocationComponent's options
            val customLocationComponentOptions = LocationComponentOptions.builder(this!!.activity!!)
                    .elevation(5f)
                    .accuracyAlpha(.6f)
                    .accuracyColor(Color.RED)
                    .build()
            // Get an instance of the component
            val locationComponent = map!!.locationComponent
            val locationComponentActivationOptions = LocationComponentActivationOptions.builder(this!!.activity!!, mapStyle!!)
                    .useDefaultLocationEngine(false) // This line is necessary
                    .locationComponentOptions(customLocationComponentOptions)
                    .build()
            // Activate with options
            locationComponent.activateLocationComponent(locationComponentActivationOptions)
            // Enable to make component visible
            locationComponent.isLocationComponentEnabled = true
            // Set the component's camera mode
            locationComponent.cameraMode = CameraMode.TRACKING
            // Set the component's render mode
            locationComponent.renderMode = RenderMode.COMPASS
            initLocationEngine()
            // Add the location icon click listener
            locationComponent.addOnLocationClickListener { }
        } else {
            val permissionsManager = PermissionsManager(object : PermissionsListener {
                override fun onExplanationNeeded(permissionsToExplain: List<String>) {}
                override fun onPermissionResult(granted: Boolean) {
                    if (granted) enableLocationComponent() else Toast.makeText(activity, "Permission Denied", Toast.LENGTH_LONG).show()
                }
            })
            permissionsManager.requestLocationPermissions(activity)
        }
    }

    /**
     * Set up the LocationEngine and the parameters for querying the device's location
     */
    @SuppressLint("MissingPermission")
    private fun initLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(this!!.activity!!)
        val request = LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build()
        locationEngine!!.requestLocationUpdates(request, callback, Looper.getMainLooper())
        locationEngine!!.getLastLocation(callback)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        inflatedview = inflater.inflate(ir.e.sujeyab.R.layout.takmil_etelaat, container, false)


        inflatedview!!.imgProfileImage.setOnClickListener {
            Intent(Intent.ACTION_PICK).also {
                it.type = "image/*"
                val mimeTypes = arrayOf("image/jpeg", "image/png")
                it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                it.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                startActivityForResult(it, VijegiHaFr.REQUEST_CODE_PICK_IMAGE)
            }
        }

        arrayList = ArrayList()

        val tabLayout = (activity as Login).tabLayout
        tabLayout.visibility = View.GONE

        val viewPager = (activity as Login).viewPager
        viewPager.setUserInputEnabled(false);


        val spinner_jensiyat: Spinner = inflatedview!!.findViewById(R.id.spinner_jensiyat)
        etTarikhTavalod2 = inflatedview!!.findViewById(R.id.etTarikhTavalod)


        inflatedview!!.map_view.onCreate(savedInstanceState);
        inflatedview!!.map_view!!.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(mapboxMap: MapboxMap) {
                map = mapboxMap
                map!!.setStyle(
                    Style.Builder().fromUri(MapirStyle.MAIN_MOBILE_VECTOR_STYLE),
                    object : Style.OnStyleLoaded {
                        override fun onStyleLoaded(style: Style) {
                            mapStyle = style
                            enableLocationComponent()
                            // TODO; جهت انجام هرکاری با شیٔ نقشه، از اینجا به بعد می توانید اقدام کنید
                        }
                    })
            }
        })



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

        var username:String = SharedPrefClass.getUserId(activity,"user");
        LoadData.loadMoshakhasatBaRetrofit(activity, clWifiState,username, etNameFamily, etTarikhTavalod, etJensiyat, etVaziyatTaahol, etVaziyatNezamVazife, etAkharinMadrakTahsili, etMoadelMadrakTahsili, etReshteTahsili, etZamineMoredAlaghaHamkari, etMizanSabegheKarMortabet, etSematShoghli, etCodePerseneli, etEmail, etShomaeTelephoneTamas,
                etKeshvar, etOstan, etShahrestan, etShahr, etRosta, etNeshani, etMoaref, etTelephoneTamasMoaref, etTozihat,imgProfileImage)

        inflatedview!!.clEdame.setOnClickListener {
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

            LoadData.editMoshakhasatMan(activity, clWifiState, username, nameFamily, tarikhTavallod, jensiyat, vaziyatTaahol, vaziyatNezamVazife, akharinMadrakTahsili, moadelMadrakTahsili, reshteTahsili, zaminehMoredAlagheHamkari, mizanSabegheKarMortabet, sematShoghli, codePerseneli, email, shomaeTelephoneTamas, keshvar, ostan, shahrestan, shahr, rosta, neshani, moaref, telephoneTamasMoaref, tozihat, jensiyatSp, vaziyatTaaholSp, vaziyatNezamVazifeSp, akharinMadrakTahsiliSp)
        }



        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    //Log.d(TAG, "Fragment back pressed invoked")
                    activity!!.finish()

                    // if you want onBackPressed() to be called as normal afterwards
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
            )



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


    override fun onStart() {
        super.onStart()
        map_view!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        map_view!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        map_view!!.onPause()
    }

    override fun onStop() {
        super.onStop()
        map_view!!.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map_view!!.onLowMemory()
    }

   /* override fun onDestroy() {
        super.onDestroy()
        map_view!!.onDestroy()
    }
*/
     override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
         map_view!!.onSaveInstanceState(outState)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //inflatedview!!.clTasvirSuje.visibility = View.VISIBLE

            var count:Int = data!!.getClipData()!!.itemCount
            var currentItem:Int = 0;
            if (count > 10) {
                Toast.makeText(activity,"حداکثر 10 تصویر انتخاب کنید",Toast.LENGTH_SHORT).show()
            }else{

                while (currentItem < count) {
                    var imageUri: Uri  = data!!.getClipData()!!.getItemAt(currentItem).getUri();
                    currentItem = currentItem + 1;

                    arrayList!!.add(imageUri)
                    uploadImage()
                    progress_bar.setVisibility(View.VISIBLE)
                }
            }
        }
    }


    fun uploadImage() {

        val parcelFileDescriptor0 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(0)!!, "r", null) ?: return

        val inputStream0 = FileInputStream(parcelFileDescriptor0.fileDescriptor)

        val file0 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(0)))

        val outputStream0 = FileOutputStream(file0)

        inputStream0.copyTo(outputStream0)

        progress_bar.progress = 0
        val body0 = UploadRequestBody(file0, "image", this)

        ApiForUpload().uploadImageProfile(
            MultipartBody.Part.createFormData("p1",file0.name, body0),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), SharedPrefClass.getUserId(activity,"user")))
            .enqueue(object : Callback<UploadResponse> {

                override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                    inflatedview!!.clcl.snackbar(t.message!!)

                    progress_bar.progress = 0
                }

                override fun onResponse(
                    call: Call<UploadResponse>,
                    response: Response<UploadResponse>
                ) {
                    response.body()?.let {
                        inflatedview!!.clcl.snackbar(it.message)
                        progress_bar.progress = 100
                        progress_bar.visibility = View.GONE
                        imgProfileImage.setImageURI(arrayList!!.get(0))


                    }
                }
            })

    }

    override fun onProgressUpdate(percentage: Int) {
        inflatedview!!.progress_bar.progress = percentage
    }

}
