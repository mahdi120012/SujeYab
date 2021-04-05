package ir.e.sujeyab.SabtSuje

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import ir.e.sujeyab.Controller.ApiForUpload
import ir.e.sujeyab.Controller.RetrofitProvider
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.ViewPagerAdapterForSlider
import ir.e.sujeyab.models.SliderModel
import kotlinx.android.synthetic.main.button_sabt_fori_suje.*
import kotlinx.android.synthetic.main.button_sabt_fori_suje.view.*
import kotlinx.android.synthetic.main.sabt_fori_suje.*
import kotlinx.android.synthetic.main.vijegiha_fr.*
import kotlinx.android.synthetic.main.vijegiha_fr.view.*
import me.relex.circleindicator.CircleIndicator
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*


class VijegiHaFr() : Fragment(), UploadRequestBody.UploadCallback {
    var inflatedview: View? = null
    private var selectedImageUri: Uri? = null
    var etOnvanP:EditText? = null
    var etMozoP:EditText? = null
    var etTozihatP:EditText? = null

    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        inflatedview = inflater.inflate(R.layout.vijegiha_fr, container, false)

        /*var t = inflatedview!!.txEdame
        t.setOnClickListener {
            activity!!.viewPager.setCurrentItem(1)
            //LoadData.addSujeJadid(activity,clWifiState,etOnvan,etMozo,etTozihat)


        }*/
        (activity!!.txEdame)!!.setText("ثبت سوژه")

        inflatedview!!.clEdame.setOnClickListener {

           if(((activity)!!.tabLayout.getTabAt(1)!!.view as LinearLayout).visibility == View.GONE){
               inflatedview!!.clProgressBar!!.visibility = View.VISIBLE
               imgPreview.setImageURI(selectedImageUri)
               //inflatedview!!.progress_bar.visibility = View.VISIBLE
               uploadImage()
               //activity!!.viewPager.setCurrentItem(0)
           }else{
               activity!!.viewPager.setCurrentItem(1)
           }

        }

        inflatedview!!.clBazgasht.setOnClickListener {
            activity!!.viewPager.setCurrentItem(3)
        }

        inflatedview!!.clEntakhabTasvirSuje.setOnClickListener {
            //کد زیر برای دسترسی دادن به حافظست
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(
                    activity!!,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:packageName"))
                activity!!.finish()
                startActivity(intent)
                return@setOnClickListener
            }else{
                openImageChooser()
            }*/
            openImageChooser()
        }

       /* inflatedview!!.button_upload.setOnClickListener {
           *//* inflatedview!!.progress_bar.visibility = View.VISIBLE
            uploadImage()*//*
        }*/


        return inflatedview
    }


    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            inflatedview!!.clTasvirSuje.visibility = View.VISIBLE
            //inflatedview!!. button_upload.visibility = View.VISIBLE
            when (requestCode) {
                REQUEST_CODE_PICK_IMAGE -> {
                    selectedImageUri = data?.data
                    image_view.setImageURI(selectedImageUri)
                }
            }
        }
    }
    fun LoadPishkhanSliderByRetrofit(c: Context?, clWifi: ConstraintLayout?, mPager: ViewPager,
                                     indicator: CircleIndicator, ImgArray: ArrayList<SliderModel?>) {

        //String usernameEncode = UrlEncoderClass.urlEncoder(etUsername.getText().toString());
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
          Api api = retrofit.create(Api.class);*/

        //Api api = new RetrofitProvider().getApi();
        val call = RetrofitProvider().api.getSlider("main_slider_pishkhan")
        call.enqueue(object : Callback<List<SliderModel>> {
            override fun onResponse(call: Call<List<SliderModel>>, response: Response<List<SliderModel>>) {

                //if (response.isSuccessful()){}
                val sliderModels = response.body()!!
                if (response.body().toString().length <= 0) {
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show()
                }
                for (sliderModel in sliderModels) {
                    LoadData.lastId = sliderModel.id
                    ImgArray.add(SliderModel(LoadData.lastId, sliderModel.picture, sliderModel.link, sliderModel.description, ""))
                    mPager.adapter = ViewPagerAdapterForSlider(c, ImgArray, "slider")
                    indicator.setViewPager(mPager)
                }
            }

            override fun onFailure(call: Call<List<SliderModel>>, t: Throwable) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
     fun uploadImage() {
        if (selectedImageUri == null) {
            inflatedview!!.clcl.snackbar("Select an Image First")
            return
        }

        val parcelFileDescriptor =
                activity!!.contentResolver.openFileDescriptor(selectedImageUri!!, "r", null) ?: return

        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(selectedImageUri!!))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

         val r = Random()
         val randomNumber = r.nextInt(9999999)

        progress_bar.progress = 0
        val body = UploadRequestBody(file, "image", this)
        ApiForUpload().uploadImage(MultipartBody.Part.createFormData("image",file.name, body),
                                   RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "json"),
                                   RequestBody.create("multipart/form-data".toMediaTypeOrNull(), etOnvanP!!.text.toString()),
                                   RequestBody.create("multipart/form-data".toMediaType(),etMozoP!!.text.toString()),
                                   RequestBody.create("multipart/form-data".toMediaType(), etTozihatP!!.text.toString()),
                                   RequestBody.create("multipart/form-data".toMediaType(), SharedPrefClass.getUserId(activity ,"user")),
                                   RequestBody.create("multipard/form-data".toMediaType(),"سوژه ها"),
                                   RequestBody.create("multipard/form-data".toMediaType(),randomNumber.toString()))
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

                    SharedPrefClass.clearShenaseRahgiri(activity)
                    val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences("file", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("shenase_rahgiri", randomNumber.toString())
                    editor.commit()

                    activity!!.viewPager.setCurrentItem(0)

                }
            }
        })

    }

    override fun onProgressUpdate(percentage: Int) {
        progress_bar.progress = percentage
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        this.etOnvanP = event.etOnvan
        this.etMozoP = event.etMozo
        this.etTozihatP = event.etTozihat
        //Toast.makeText(activity, event.etOnvan.text.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        EventBus.getDefault().register(this)
        super.onStart()
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }
}