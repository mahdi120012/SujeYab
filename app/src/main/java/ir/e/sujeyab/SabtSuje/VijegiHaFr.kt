package ir.e.sujeyab.SabtSuje


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import ir.e.sujeyab.Controller.ApiForUpload
import ir.e.sujeyab.Controller.RetrofitProvider
import ir.e.sujeyab.CustomClasses.Recyclerview
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.ViewPagerAdapterForSlider
import ir.e.sujeyab.adapters.SelectedImageAdapter
import ir.e.sujeyab.models.SliderModel
import kotlinx.android.synthetic.main.button_sabt_fori_suje.*
import kotlinx.android.synthetic.main.button_sabt_fori_suje.view.*
import kotlinx.android.synthetic.main.sabt_fori_suje.*
import kotlinx.android.synthetic.main.sabt_fr.*
import kotlinx.android.synthetic.main.takmil_etelaat.*
import kotlinx.android.synthetic.main.tarh_suje_fr.*
import kotlinx.android.synthetic.main.vijegiha_fr.*
import kotlinx.android.synthetic.main.vijegiha_fr.progress_bar
import kotlinx.android.synthetic.main.vijegiha_fr.view.*
import me.relex.circleindicator.CircleIndicator
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    var txIdFarakhan:TextView? = null
    private var arrayList: ArrayList<Uri>? = null
    private var arrayListTozihat: ArrayList<Uri>? = null

    private val REQUEST_CODE_PERMISSIONS = 1
    private val REQUEST_CODE_READ_STORAGE = 2
    var rAdapter: SelectedImageAdapter? = null
    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {


        inflatedview = inflater.inflate(R.layout.vijegiha_fr, container, false)

        inflatedview!!.clEdame.setOnClickListener {

           if(((activity)!!.tabLayout.getTabAt(1)!!.view as LinearLayout).visibility == View.GONE){

               if (arrayList!!.size == 0){
                   inflatedview!!.clcl.snackbar("حداقل یک تصویر انتخاب کنید")
               }else{

               inflatedview!!.clProgressBar!!.visibility = View.VISIBLE
               imgPreview.setImageURI(selectedImageUri)
               //inflatedview!!.progress_bar.visibility = View.VISIBLE
                   //uploadImagesToServer()


                       uploadImagesToServer()

                   //uploadImage1()

                   /*// Display the file chooser dialog
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                       askForPermission()
                   } else {
                       uploadImagesToServer()
                   }
                   uploadImagesToServer()*/

               }

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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                askForPermission()
            } else {
                openImageChooser()
            }

            //openImageChooser()
        }

       /* inflatedview!!.button_upload.setOnClickListener {
           *//* inflatedview!!.progress_bar.visibility = View.VISIBLE
            uploadImage()*//*
        }*/


        inflatedview!!.clEntakhabFilmSuje.setOnClickListener {

            openVideoChooser()
            //inflatedview!!.clAfzodanFilmBaLinkMostaghim.visibility = View.VISIBLE
        }


     /*   inflatedview!!.imgShowPreview.setOnClickListener {
            inflatedview!!.clPishnamayeshFilm.visibility = View.VISIBLE
            var videoLink = inflatedview!!.etLinkVideo.text.toString()
            inflatedview!!.videoView.setSource(Uri.parse(videoLink))
            inflatedview!!.videoView.enableControls()
            inflatedview!!.videoView.start()
        }*/

        arrayList = ArrayList()
        arrayListTozihat = ArrayList()
        rAdapter = SelectedImageAdapter("selected_image",activity,arrayList,arrayListTozihat,rAdapter)
        Recyclerview.defineRecyclerViewForImageSelected(activity, inflatedview!!.rv1, rAdapter, arrayList)

        return inflatedview
    }




    private fun openVideoChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 30)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        //intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, MAX_VIDEO_RECORDING_TIME_IN_SEC)
        intent.type = "video/*"
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)


        //Intent(Intent.ACTION_PICK).also {
        //    it.type = "video/*"
        //    val mimeTypes = arrayOf("video/*")
        //    it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        //    it.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        //    startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        //}
    }


    private fun openImageChooser() {

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, REQUEST_CODE_READ_STORAGE)

      /*  ImagePicker.with(this)
            .crop()	    			//Crop image(Optional), Check Customization for more option
            .compress(1024)			//Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
            .start()*/

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {


        super.onActivityResult(requestCode, resultCode, resultData)
        if (resultCode == Activity.RESULT_OK) {
            inflatedview!!.clTasvirSuje.visibility = View.VISIBLE
            if (requestCode == REQUEST_CODE_READ_STORAGE) {
                if (resultData != null) {
                    if (resultData.getClipData() != null) {
                        val count: Int = resultData.getClipData()!!.getItemCount()
                        var currentItem = 0

                        if (count > 10) {
                            Toast.makeText(activity,"حداکثر 10 تصویر انتخاب کنید",Toast.LENGTH_SHORT).show()
                        }else{


                        while (currentItem < count) {
                            val imageUri: Uri =
                                resultData.getClipData()!!.getItemAt(currentItem).getUri()
                            currentItem = currentItem + 1
                            Log.d("Uri Selected", imageUri.toString())
                            try {
                                arrayList!!.add(imageUri)
                                rAdapter!!.notifyDataSetChanged()
                            } catch (e: Exception) {
                                /*Log.e(
                                    org.snowcorp.sample.uploadfiles.MainActivity.TAG,
                                    "File select error",
                                    e
                                )*/
                            }
                        }}
                    } else if (resultData.getData() != null) {
                        val uri: Uri = resultData.getData()!!
                        //Log.i(org.snowcorp.sample.uploadfiles.MainActivity.TAG, "Uri = $uri")

                        try {
                            arrayList!!.add(uri)
                            rAdapter!!.notifyDataSetChanged()
                        } catch (e: Exception) {
                            /*Log.e(
                                org.snowcorp.sample.uploadfiles.MainActivity.TAG,
                                "File select error",
                                e
                            )*/
                        }
                    }
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
                    mPager.adapter = ViewPagerAdapterForSlider(c, ImgArray, "slider", mPager)
                    indicator.setViewPager(mPager)
                }
            }

            override fun onFailure(call: Call<List<SliderModel>>, t: Throwable) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }


    fun uploadImage1() {

        val parcelFileDescriptor0 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(0)!!, "r", null) ?: return

        val inputStream0 = FileInputStream(parcelFileDescriptor0.fileDescriptor)

        val file0 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(0)))

        val outputStream0 = FileOutputStream(file0)

        inputStream0.copyTo(outputStream0)

        val r = Random()
        val randomNumber = r.nextInt(9999999)

        progress_bar.progress = 0
        val body0 = UploadRequestBody(file0, "image", this)

        ApiForUpload().uploadImage(MultipartBody.Part.createFormData("p1",file0.name, body0),
            MultipartBody.Part.createFormData("p2","", body0),
            MultipartBody.Part.createFormData("p3","", body0),
            MultipartBody.Part.createFormData("p4","", body0),
            MultipartBody.Part.createFormData("p5","", body0),
            MultipartBody.Part.createFormData("p6","", body0),
            MultipartBody.Part.createFormData("p7","", body0),
            MultipartBody.Part.createFormData("p8","", body0),
            MultipartBody.Part.createFormData("p9","", body0),
            MultipartBody.Part.createFormData("p10","", body0),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "json"),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), etOnvanP!!.text.toString()),
            RequestBody.create("multipart/form-data".toMediaType(),etMozoP!!.text.toString()),
            RequestBody.create("multipart/form-data".toMediaType(), etTozihatP!!.text.toString()),
            RequestBody.create("multipart/form-data".toMediaType(), SharedPrefClass.getUserId(activity ,"user")),
            RequestBody.create("multipard/form-data".toMediaType(),"سوژه ها"),
            RequestBody.create("multipard/form-data".toMediaType(),randomNumber.toString()),
            RequestBody.create("multipard/form-data".toMediaType(),etLinkVideo.text.toString()),
            RequestBody.create("multipard/form-data".toMediaType(),etTozihVideo.text.toString()),
            RequestBody.create("multipard/form-data".toMediaType(),txIdFarakhan!!.text.toString()))
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

                        (activity!!.txShenaseRahgiri)!!.setText(randomNumber.toString())

                        activity!!.viewPager.setCurrentItem(0)

                    }
                }
            })

    }


    override fun onProgressUpdate(percentage: Int) {
        progress_bar.progress = percentage
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent){
        this.etOnvanP = event.etOnvan
        this.etMozoP = event.etMozo
        this.etTozihatP = event.etTozihat
        this.txIdFarakhan = event.txIdFarakhan
        //Toast.makeText(activity, event.txIdFarakhan.text.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        EventBus.getDefault().register(this)
        super.onStart()
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    private fun askForPermission() {
        if (ContextCompat.checkSelfPermission(
                activity!!,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) +
            ContextCompat.checkSelfPermission(
                activity!!,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            /* Ask for permission */
            // need to request permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity!!,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) ||
                ActivityCompat.shouldShowRequestPermissionRationale(
                    activity!!,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                Snackbar.make(inflatedview!!.clcl,"Please grant permissions to write data in sdcard",
                    Snackbar.LENGTH_INDEFINITE).setAction("ENABLE") { v: View? ->
                    ActivityCompat.requestPermissions(
                        activity!!, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_PERMISSIONS)
                }.show()
            } else {
                /* Request for permission */
                ActivityCompat.requestPermissions(
                    activity!!, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE),REQUEST_CODE_PERMISSIONS)
            }
        } else {
            openImageChooser()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                openImageChooser()
            } else {
                // Permission Denied
                Toast.makeText(activity!!, "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun createPartFromString(descriptionString: String): RequestBody {
        return RequestBody.create(FileUtils.MIME_TYPE_TEXT.toMediaTypeOrNull(), descriptionString)
    }

    private fun prepareFilePart(partName: String, fileUri: Uri): MultipartBody.Part {
        // use the FileUtils to get the actual file by uri
        val file = FileUtils.getFile(activity, fileUri)

        // create RequestBody instance from file
        val requestFile = RequestBody.create(FileUtils.MIME_TYPE_IMAGE.toMediaTypeOrNull(), file)

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.Companion.createFormData(partName, file.name, requestFile)
    }

    private fun uploadImagesToServer() {
        if (InternetConnection.checkConnection(activity!!)) {
            val retrofit = Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            //showProgress()

            // create list of file parts (photo, video, ...)
            val parts: MutableList<MultipartBody.Part> = ArrayList<MultipartBody.Part>()

            // create upload service client
            val service = retrofit.create(ApiService::class.java)
            if (arrayList != null) {
                // create part for file (photo, video, ...)
                for (i in arrayList!!.indices) {
                    parts.add(prepareFilePart("image$i", arrayList!![i]))
                }

                for (i in arrayListTozihat!!.indices) {
                    parts.add(prepareFilePart("tozih$i", arrayListTozihat!![i]))
                }
            }
            val r = Random()
            val randomNumber = r.nextInt(9999999)

            // create a map of data to pass along
            val id_ferestande: RequestBody = createPartFromString(SharedPrefClass.getUserId(activity ,"user"))
            val onvan: RequestBody = createPartFromString(etOnvanP!!.text.toString())
            val mozo: RequestBody = createPartFromString(etMozoP!!.text.toString())
            val tozihat: RequestBody = createPartFromString(etTozihatP!!.text.toString())
            val type: RequestBody = createPartFromString("سوژه ها")
            val shenase_rahgiri: RequestBody = createPartFromString(randomNumber.toString())
            val link_video: RequestBody = createPartFromString(etLinkVideo.text.toString())
            val tozih_video: RequestBody = createPartFromString(etTozihVideo.text.toString())
            val id_farakhan: RequestBody = createPartFromString(txIdFarakhan!!.text.toString())
            val size: RequestBody = createPartFromString("" + parts.size)

            // finally, execute the request
            val call = service.uploadMultiple(id_ferestande, onvan, mozo,tozihat,type,shenase_rahgiri, link_video, tozih_video,
                id_farakhan, size, parts)

            call.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    //hideProgress()
                    if (response.isSuccessful) {

                        progress_bar.progress = 100
                        (activity!!.txShenaseRahgiri)!!.setText(randomNumber.toString())
                        activity!!.viewPager.setCurrentItem(0)


                        Toast.makeText(activity,"Images successfully uploaded!", Toast.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(inflatedview!!.clcl,"R.string.string_some_thing_wrong", Snackbar.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    //hideProgress()
                   /* Log.e(
                        org.snowcorp.sample.uploadfiles.MainActivity.TAG,
                        "Image upload failed!",
                        t
                    )*/
                    Snackbar.make(inflatedview!!.clcl,t.toString(), Snackbar.LENGTH_LONG).show()
                }
            })
        } else {
            //hideProgress()
            Toast.makeText(activity,"R.string.string_internet_connection_not_available", Toast.LENGTH_SHORT).show()
        }
    }

}