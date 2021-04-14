package ir.e.sujeyab.SabtSuje

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import ir.e.sujeyab.CustomClasses.Recyclerview
import ir.e.sujeyab.CustomClasses.SharedPrefClass
import ir.e.sujeyab.LoadData
import ir.e.sujeyab.R
import ir.e.sujeyab.ViewPagerAdapterForSlider
import ir.e.sujeyab.adapters.SelectedImageAdapter
import ir.e.sujeyab.models.SliderModel
import kotlinx.android.synthetic.main.button_sabt_fori_suje.*
import kotlinx.android.synthetic.main.button_sabt_fori_suje.txEdame
import kotlinx.android.synthetic.main.button_sabt_fori_suje.view.*
import kotlinx.android.synthetic.main.sabt_fori_suje.*
import kotlinx.android.synthetic.main.sabt_fr.*
import kotlinx.android.synthetic.main.takmil_etelaat.*
import kotlinx.android.synthetic.main.vijegiha_fr.*
import kotlinx.android.synthetic.main.vijegiha_fr.progress_bar
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
    private var arrayList: ArrayList<Uri>? = null
    var rAdapter: SelectedImageAdapter? = null
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
               if (arrayList!!.size == 1){
                   uploadImage1()
               }else if (arrayList!!.size == 2){
                   uploadImage2()
               }else if (arrayList!!.size == 3){
                   uploadImage3()
               }else if (arrayList!!.size == 4){
                   uploadImage4()
               }else if (arrayList!!.size == 5){
                   uploadImage5()
               }else if (arrayList!!.size == 6){
                   uploadImage6()
               }else if (arrayList!!.size == 7){
                   uploadImage7()
               }else if (arrayList!!.size == 8){
                   uploadImage8()
               }else if (arrayList!!.size == 9){
                   uploadImage9()
               }else {
                   uploadImage10()
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
            openImageChooser()
        }

       /* inflatedview!!.button_upload.setOnClickListener {
           *//* inflatedview!!.progress_bar.visibility = View.VISIBLE
            uploadImage()*//*
        }*/


        inflatedview!!.clEntakhabFilmSuje.setOnClickListener {
            openVideoChooser()
            inflatedview!!.clAfzodanFilmBaLinkMostaghim.visibility = View.VISIBLE
        }


        inflatedview!!.imgShowPreview.setOnClickListener {
            inflatedview!!.clPishnamayeshFilm.visibility = View.VISIBLE
            var videoLink = inflatedview!!.etLinkVideo.text.toString()
            inflatedview!!.videoView.setSource(Uri.parse(videoLink))
            inflatedview!!.videoView.enableControls()
            inflatedview!!.videoView.start()
        }

        arrayList = ArrayList()
        rAdapter = SelectedImageAdapter("selected_image",activity,arrayList,rAdapter)
        Recyclerview.defineRecyclerViewForImageSelected(activity, inflatedview!!.rv1, rAdapter, arrayList)

        return inflatedview
    }




    private fun openVideoChooser() {

        Intent(Intent.ACTION_PICK).also {
            it.type = "video/*"
            val mimeTypes = arrayOf("video/*")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            it.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
    }


    private fun openImageChooser() {

        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimeTypes = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            it.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
      /*  ImagePicker.with(this)
            .crop()	    			//Crop image(Optional), Check Customization for more option
            .compress(1024)			//Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
            .start()*/

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            inflatedview!!.clTasvirSuje.visibility = View.VISIBLE
            //inflatedview!!. button_upload.visibility = View.VISIBLE


/*            if (arrayList!!.size > 9) {
                Toast.makeText(activity,"حداکثر 10 تصویر انتخاب کنید",Toast.LENGTH_SHORT).show()
            }else{
                val fileUri = data?.data

                if (fileUri != null) {
                    arrayList!!.add(fileUri)
                }
                rAdapter!!.notifyDataSetChanged()
            }*/

            //var count:Int = arrayList!!.size
            var count:Int = data!!.getClipData()!!.itemCount

            var currentItem:Int = 0;
            //Toast.makeText(activity,"sssss",Toast.LENGTH_SHORT).show()
            if (count > 10) {
                Toast.makeText(activity,"حداکثر 10 تصویر انتخاب کنید",Toast.LENGTH_SHORT).show()
            }else{
                    while (currentItem < count) {
                        var imageUri: Uri  = data!!.getClipData()!!.getItemAt(currentItem).getUri();
                        currentItem = currentItem + 1;

                        //Log.d("Uri Selected", imageUri.toString());


                            arrayList!!.add(imageUri)
                            rAdapter!!.notifyDataSetChanged()


                    }
            }

            /*when (requestCode) {
                REQUEST_CODE_PICK_IMAGE -> {
                    var count:Int = data!!.getClipData()!!.getItemCount()
                    Toast.makeText(activity,requestCode.toString(),Toast.LENGTH_SHORT).show()
                    selectedImageUri = data?.data
                    image_view.setImageURI(selectedImageUri)
                }
             }
             */
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
            RequestBody.create("multipard/form-data".toMediaType(),etTozihVideo.text.toString()))
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



    fun uploadImage2() {

        val parcelFileDescriptor0 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(0)!!, "r", null) ?: return
        val parcelFileDescriptor1 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(1)!!, "r", null) ?: return

        val inputStream0 = FileInputStream(parcelFileDescriptor0.fileDescriptor)
        val inputStream1 = FileInputStream(parcelFileDescriptor0.fileDescriptor)

        val file0 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(0)))
        val file1 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(1)))

        val outputStream0 = FileOutputStream(file0)
        val outputStream1 = FileOutputStream(file1)

        inputStream0.copyTo(outputStream0)
        inputStream1.copyTo(outputStream1)

        val r = Random()
        val randomNumber = r.nextInt(9999999)

        progress_bar.progress = 0
        val body0 = UploadRequestBody(file0, "image", this)
        val body1 = UploadRequestBody(file1, "image", this)


        ApiForUpload().uploadImage(MultipartBody.Part.createFormData("p1",file0.name, body0),
            MultipartBody.Part.createFormData("p2",file1.name, body1),
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
            RequestBody.create("multipard/form-data".toMediaType(),etTozihVideo.text.toString()))
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

                        /*SharedPrefClass.clearShenaseRahgiri(activity)
                        val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences("file", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("shenase_rahgiri", randomNumber.toString())
                        editor.commit()*/

                        //getActivity()!!.getIntent().putExtra("shenase_rahgiri", randomNumber.toString());


                        (activity!!.txShenaseRahgiri)!!.setText(randomNumber.toString())
                        //txShenaseRahgiri.setText(randomNumber.toString())

                        activity!!.viewPager.setCurrentItem(0)

                    }
                }
            })

    }


    fun uploadImage3() {

        val parcelFileDescriptor0 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(0)!!, "r", null) ?: return
        val parcelFileDescriptor1 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(1)!!, "r", null) ?: return
        val parcelFileDescriptor2 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(2)!!, "r", null) ?: return

        val inputStream0 = FileInputStream(parcelFileDescriptor0.fileDescriptor)
        val inputStream1 = FileInputStream(parcelFileDescriptor1.fileDescriptor)
        val inputStream2 = FileInputStream(parcelFileDescriptor2.fileDescriptor)

        val file0 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(0)))
        val file1 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(1)))
        val file2 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(2)))

        val outputStream0 = FileOutputStream(file0)
        val outputStream1 = FileOutputStream(file1)
        val outputStream2 = FileOutputStream(file2)

        inputStream0.copyTo(outputStream0)
        inputStream1.copyTo(outputStream1)
        inputStream2.copyTo(outputStream2)

        val r = Random()
        val randomNumber = r.nextInt(9999999)

        progress_bar.progress = 0
        val body0 = UploadRequestBody(file0, "image", this)
        val body1 = UploadRequestBody(file1, "image", this)
        val body2 = UploadRequestBody(file2, "image", this)


        ApiForUpload().uploadImage(MultipartBody.Part.createFormData("p1",file0.name, body0),
            MultipartBody.Part.createFormData("p2",file1.name, body1),
            MultipartBody.Part.createFormData("p3",file2.name, body2),
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
            RequestBody.create("multipard/form-data".toMediaType(),etTozihVideo.text.toString()))
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

                        /*SharedPrefClass.clearShenaseRahgiri(activity)
                        val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences("file", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("shenase_rahgiri", randomNumber.toString())
                        editor.commit()*/

                        //getActivity()!!.getIntent().putExtra("shenase_rahgiri", randomNumber.toString());


                        (activity!!.txShenaseRahgiri)!!.setText(randomNumber.toString())
                        //txShenaseRahgiri.setText(randomNumber.toString())

                        activity!!.viewPager.setCurrentItem(0)

                    }
                }
            })

    }


    fun uploadImage4() {

        val parcelFileDescriptor0 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(0)!!, "r", null) ?: return
        val parcelFileDescriptor1 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(1)!!, "r", null) ?: return
        val parcelFileDescriptor2 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(2)!!, "r", null) ?: return
        val parcelFileDescriptor3 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(3)!!, "r", null) ?: return

        val inputStream0 = FileInputStream(parcelFileDescriptor0.fileDescriptor)
        val inputStream1 = FileInputStream(parcelFileDescriptor1.fileDescriptor)
        val inputStream2 = FileInputStream(parcelFileDescriptor2.fileDescriptor)
        val inputStream3 = FileInputStream(parcelFileDescriptor3.fileDescriptor)

        val file0 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(0)))
        val file1 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(1)))
        val file2 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(2)))
        val file3 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(3)))

        val outputStream0 = FileOutputStream(file0)
        val outputStream1 = FileOutputStream(file1)
        val outputStream2 = FileOutputStream(file2)
        val outputStream3 = FileOutputStream(file3)

        inputStream0.copyTo(outputStream0)
        inputStream1.copyTo(outputStream1)
        inputStream2.copyTo(outputStream2)
        inputStream3.copyTo(outputStream3)

        val r = Random()
        val randomNumber = r.nextInt(9999999)

        progress_bar.progress = 0
        val body0 = UploadRequestBody(file0, "image", this)
        val body1 = UploadRequestBody(file1, "image", this)
        val body2 = UploadRequestBody(file2, "image", this)
        val body3 = UploadRequestBody(file3, "image", this)


        ApiForUpload().uploadImage(MultipartBody.Part.createFormData("p1",file0.name, body0),
            MultipartBody.Part.createFormData("p2",file1.name, body1),
            MultipartBody.Part.createFormData("p3",file2.name, body2),
            MultipartBody.Part.createFormData("p4",file3.name, body3),
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
            RequestBody.create("multipard/form-data".toMediaType(),etTozihVideo.text.toString()))
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

                        /*SharedPrefClass.clearShenaseRahgiri(activity)
                        val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences("file", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("shenase_rahgiri", randomNumber.toString())
                        editor.commit()*/

                        //getActivity()!!.getIntent().putExtra("shenase_rahgiri", randomNumber.toString());


                        (activity!!.txShenaseRahgiri)!!.setText(randomNumber.toString())
                        //txShenaseRahgiri.setText(randomNumber.toString())

                        activity!!.viewPager.setCurrentItem(0)

                    }
                }
            })

    }


    fun uploadImage5() {

        val parcelFileDescriptor0 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(0)!!, "r", null) ?: return
        val parcelFileDescriptor1 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(1)!!, "r", null) ?: return
        val parcelFileDescriptor2 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(2)!!, "r", null) ?: return
        val parcelFileDescriptor3 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(3)!!, "r", null) ?: return
        val parcelFileDescriptor4 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(4)!!, "r", null) ?: return

        val inputStream0 = FileInputStream(parcelFileDescriptor0.fileDescriptor)
        val inputStream1 = FileInputStream(parcelFileDescriptor1.fileDescriptor)
        val inputStream2 = FileInputStream(parcelFileDescriptor2.fileDescriptor)
        val inputStream3 = FileInputStream(parcelFileDescriptor3.fileDescriptor)
        val inputStream4 = FileInputStream(parcelFileDescriptor4.fileDescriptor)

        val file0 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(0)))
        val file1 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(1)))
        val file2 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(2)))
        val file3 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(3)))
        val file4 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(4)))

        val outputStream0 = FileOutputStream(file0)
        val outputStream1 = FileOutputStream(file1)
        val outputStream2 = FileOutputStream(file2)
        val outputStream3 = FileOutputStream(file3)
        val outputStream4 = FileOutputStream(file4)

        inputStream0.copyTo(outputStream0)
        inputStream1.copyTo(outputStream1)
        inputStream2.copyTo(outputStream2)
        inputStream3.copyTo(outputStream3)
        inputStream4.copyTo(outputStream4)

        val r = Random()
        val randomNumber = r.nextInt(9999999)

        progress_bar.progress = 0
        val body0 = UploadRequestBody(file0, "image", this)
        val body1 = UploadRequestBody(file1, "image", this)
        val body2 = UploadRequestBody(file2, "image", this)
        val body3 = UploadRequestBody(file3, "image", this)
        val body4 = UploadRequestBody(file4, "image", this)


        ApiForUpload().uploadImage(MultipartBody.Part.createFormData("p1",file0.name, body0),
            MultipartBody.Part.createFormData("p2",file1.name, body1),
            MultipartBody.Part.createFormData("p3",file2.name, body2),
            MultipartBody.Part.createFormData("p4",file3.name, body3),
            MultipartBody.Part.createFormData("p5",file4.name, body4),
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
            RequestBody.create("multipard/form-data".toMediaType(),etTozihVideo.text.toString()))
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

                        /*SharedPrefClass.clearShenaseRahgiri(activity)
                        val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences("file", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("shenase_rahgiri", randomNumber.toString())
                        editor.commit()*/

                        //getActivity()!!.getIntent().putExtra("shenase_rahgiri", randomNumber.toString());


                        (activity!!.txShenaseRahgiri)!!.setText(randomNumber.toString())
                        //txShenaseRahgiri.setText(randomNumber.toString())

                        activity!!.viewPager.setCurrentItem(0)

                    }
                }
            })
    }


    fun uploadImage6() {

        val parcelFileDescriptor0 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(0)!!, "r", null) ?: return
        val parcelFileDescriptor1 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(1)!!, "r", null) ?: return
        val parcelFileDescriptor2 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(2)!!, "r", null) ?: return
        val parcelFileDescriptor3 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(3)!!, "r", null) ?: return
        val parcelFileDescriptor4 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(4)!!, "r", null) ?: return
        val parcelFileDescriptor5 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(5)!!, "r", null) ?: return

        val inputStream0 = FileInputStream(parcelFileDescriptor0.fileDescriptor)
        val inputStream1 = FileInputStream(parcelFileDescriptor1.fileDescriptor)
        val inputStream2 = FileInputStream(parcelFileDescriptor2.fileDescriptor)
        val inputStream3 = FileInputStream(parcelFileDescriptor3.fileDescriptor)
        val inputStream4 = FileInputStream(parcelFileDescriptor4.fileDescriptor)
        val inputStream5 = FileInputStream(parcelFileDescriptor5.fileDescriptor)

        val file0 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(0)))
        val file1 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(1)))
        val file2 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(2)))
        val file3 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(3)))
        val file4 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(4)))
        val file5 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(5)))

        val outputStream0 = FileOutputStream(file0)
        val outputStream1 = FileOutputStream(file1)
        val outputStream2 = FileOutputStream(file2)
        val outputStream3 = FileOutputStream(file3)
        val outputStream4 = FileOutputStream(file4)
        val outputStream5 = FileOutputStream(file5)

        inputStream0.copyTo(outputStream0)
        inputStream1.copyTo(outputStream1)
        inputStream2.copyTo(outputStream2)
        inputStream3.copyTo(outputStream3)
        inputStream4.copyTo(outputStream4)
        inputStream5.copyTo(outputStream5)

        val r = Random()
        val randomNumber = r.nextInt(9999999)

        progress_bar.progress = 0
        val body0 = UploadRequestBody(file0, "image", this)
        val body1 = UploadRequestBody(file1, "image", this)
        val body2 = UploadRequestBody(file2, "image", this)
        val body3 = UploadRequestBody(file3, "image", this)
        val body4 = UploadRequestBody(file4, "image", this)
        val body5 = UploadRequestBody(file5, "image", this)


        ApiForUpload().uploadImage(MultipartBody.Part.createFormData("p1",file0.name, body0),
            MultipartBody.Part.createFormData("p2",file1.name, body1),
            MultipartBody.Part.createFormData("p3",file2.name, body2),
            MultipartBody.Part.createFormData("p4",file3.name, body3),
            MultipartBody.Part.createFormData("p5",file4.name, body4),
            MultipartBody.Part.createFormData("p6",file5.name, body5),
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
            RequestBody.create("multipard/form-data".toMediaType(),etTozihVideo.text.toString()))
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

                        /*SharedPrefClass.clearShenaseRahgiri(activity)
                        val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences("file", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("shenase_rahgiri", randomNumber.toString())
                        editor.commit()*/

                        //getActivity()!!.getIntent().putExtra("shenase_rahgiri", randomNumber.toString());


                        (activity!!.txShenaseRahgiri)!!.setText(randomNumber.toString())
                        //txShenaseRahgiri.setText(randomNumber.toString())

                        activity!!.viewPager.setCurrentItem(0)

                    }
                }
            })
    }



    fun uploadImage7() {

        val parcelFileDescriptor0 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(0)!!, "r", null) ?: return
        val parcelFileDescriptor1 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(1)!!, "r", null) ?: return
        val parcelFileDescriptor2 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(2)!!, "r", null) ?: return
        val parcelFileDescriptor3 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(3)!!, "r", null) ?: return
        val parcelFileDescriptor4 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(4)!!, "r", null) ?: return
        val parcelFileDescriptor5 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(5)!!, "r", null) ?: return
        val parcelFileDescriptor6 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(6)!!, "r", null) ?: return

        val inputStream0 = FileInputStream(parcelFileDescriptor0.fileDescriptor)
        val inputStream1 = FileInputStream(parcelFileDescriptor1.fileDescriptor)
        val inputStream2 = FileInputStream(parcelFileDescriptor2.fileDescriptor)
        val inputStream3 = FileInputStream(parcelFileDescriptor3.fileDescriptor)
        val inputStream4 = FileInputStream(parcelFileDescriptor4.fileDescriptor)
        val inputStream5 = FileInputStream(parcelFileDescriptor5.fileDescriptor)
        val inputStream6 = FileInputStream(parcelFileDescriptor6.fileDescriptor)

        val file0 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(0)))
        val file1 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(1)))
        val file2 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(2)))
        val file3 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(3)))
        val file4 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(4)))
        val file5 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(5)))
        val file6 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(6)))

        val outputStream0 = FileOutputStream(file0)
        val outputStream1 = FileOutputStream(file1)
        val outputStream2 = FileOutputStream(file2)
        val outputStream3 = FileOutputStream(file3)
        val outputStream4 = FileOutputStream(file4)
        val outputStream5 = FileOutputStream(file5)
        val outputStream6 = FileOutputStream(file6)

        inputStream0.copyTo(outputStream0)
        inputStream1.copyTo(outputStream1)
        inputStream2.copyTo(outputStream2)
        inputStream3.copyTo(outputStream3)
        inputStream4.copyTo(outputStream4)
        inputStream5.copyTo(outputStream5)
        inputStream6.copyTo(outputStream6)

        val r = Random()
        val randomNumber = r.nextInt(9999999)

        progress_bar.progress = 0
        val body0 = UploadRequestBody(file0, "image", this)
        val body1 = UploadRequestBody(file1, "image", this)
        val body2 = UploadRequestBody(file2, "image", this)
        val body3 = UploadRequestBody(file3, "image", this)
        val body4 = UploadRequestBody(file4, "image", this)
        val body5 = UploadRequestBody(file5, "image", this)
        val body6 = UploadRequestBody(file6, "image", this)


        ApiForUpload().uploadImage(MultipartBody.Part.createFormData("p1",file0.name, body0),
            MultipartBody.Part.createFormData("p2",file1.name, body1),
            MultipartBody.Part.createFormData("p3",file2.name, body2),
            MultipartBody.Part.createFormData("p4",file3.name, body3),
            MultipartBody.Part.createFormData("p5",file4.name, body4),
            MultipartBody.Part.createFormData("p6",file5.name, body5),
            MultipartBody.Part.createFormData("p7",file6.name, body6),
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
            RequestBody.create("multipard/form-data".toMediaType(),etTozihVideo.text.toString()))
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

                        /*SharedPrefClass.clearShenaseRahgiri(activity)
                        val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences("file", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("shenase_rahgiri", randomNumber.toString())
                        editor.commit()*/

                        //getActivity()!!.getIntent().putExtra("shenase_rahgiri", randomNumber.toString());


                        (activity!!.txShenaseRahgiri)!!.setText(randomNumber.toString())
                        //txShenaseRahgiri.setText(randomNumber.toString())

                        activity!!.viewPager.setCurrentItem(0)

                    }
                }
            })
    }


    fun uploadImage8() {

        val parcelFileDescriptor0 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(0)!!, "r", null) ?: return
        val parcelFileDescriptor1 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(1)!!, "r", null) ?: return
        val parcelFileDescriptor2 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(2)!!, "r", null) ?: return
        val parcelFileDescriptor3 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(3)!!, "r", null) ?: return
        val parcelFileDescriptor4 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(4)!!, "r", null) ?: return
        val parcelFileDescriptor5 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(5)!!, "r", null) ?: return
        val parcelFileDescriptor6 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(6)!!, "r", null) ?: return
        val parcelFileDescriptor7 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(7)!!, "r", null) ?: return

        val inputStream0 = FileInputStream(parcelFileDescriptor0.fileDescriptor)
        val inputStream1 = FileInputStream(parcelFileDescriptor1.fileDescriptor)
        val inputStream2 = FileInputStream(parcelFileDescriptor2.fileDescriptor)
        val inputStream3 = FileInputStream(parcelFileDescriptor3.fileDescriptor)
        val inputStream4 = FileInputStream(parcelFileDescriptor4.fileDescriptor)
        val inputStream5 = FileInputStream(parcelFileDescriptor5.fileDescriptor)
        val inputStream6 = FileInputStream(parcelFileDescriptor6.fileDescriptor)
        val inputStream7 = FileInputStream(parcelFileDescriptor7.fileDescriptor)

        val file0 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(0)))
        val file1 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(1)))
        val file2 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(2)))
        val file3 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(3)))
        val file4 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(4)))
        val file5 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(5)))
        val file6 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(6)))
        val file7 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(7)))

        val outputStream0 = FileOutputStream(file0)
        val outputStream1 = FileOutputStream(file1)
        val outputStream2 = FileOutputStream(file2)
        val outputStream3 = FileOutputStream(file3)
        val outputStream4 = FileOutputStream(file4)
        val outputStream5 = FileOutputStream(file5)
        val outputStream6 = FileOutputStream(file6)
        val outputStream7 = FileOutputStream(file7)

        inputStream0.copyTo(outputStream0)
        inputStream1.copyTo(outputStream1)
        inputStream2.copyTo(outputStream2)
        inputStream3.copyTo(outputStream3)
        inputStream4.copyTo(outputStream4)
        inputStream5.copyTo(outputStream5)
        inputStream6.copyTo(outputStream6)
        inputStream7.copyTo(outputStream7)

        val r = Random()
        val randomNumber = r.nextInt(9999999)

        progress_bar.progress = 0
        val body0 = UploadRequestBody(file0, "image", this)
        val body1 = UploadRequestBody(file1, "image", this)
        val body2 = UploadRequestBody(file2, "image", this)
        val body3 = UploadRequestBody(file3, "image", this)
        val body4 = UploadRequestBody(file4, "image", this)
        val body5 = UploadRequestBody(file5, "image", this)
        val body6 = UploadRequestBody(file6, "image", this)
        val body7 = UploadRequestBody(file7, "image", this)


        ApiForUpload().uploadImage(MultipartBody.Part.createFormData("p1",file0.name, body0),
            MultipartBody.Part.createFormData("p2",file1.name, body1),
            MultipartBody.Part.createFormData("p3",file2.name, body2),
            MultipartBody.Part.createFormData("p4",file3.name, body3),
            MultipartBody.Part.createFormData("p5",file4.name, body4),
            MultipartBody.Part.createFormData("p6",file5.name, body5),
            MultipartBody.Part.createFormData("p7",file6.name, body6),
            MultipartBody.Part.createFormData("p8",file7.name, body7),
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
            RequestBody.create("multipard/form-data".toMediaType(),etTozihVideo.text.toString()))
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

                        /*SharedPrefClass.clearShenaseRahgiri(activity)
                        val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences("file", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("shenase_rahgiri", randomNumber.toString())
                        editor.commit()*/

                        //getActivity()!!.getIntent().putExtra("shenase_rahgiri", randomNumber.toString());


                        (activity!!.txShenaseRahgiri)!!.setText(randomNumber.toString())
                        //txShenaseRahgiri.setText(randomNumber.toString())

                        activity!!.viewPager.setCurrentItem(0)

                    }
                }
            })
    }

    fun uploadImage9() {

        val parcelFileDescriptor0 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(0)!!, "r", null) ?: return
        val parcelFileDescriptor1 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(1)!!, "r", null) ?: return
        val parcelFileDescriptor2 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(2)!!, "r", null) ?: return
        val parcelFileDescriptor3 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(3)!!, "r", null) ?: return
        val parcelFileDescriptor4 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(4)!!, "r", null) ?: return
        val parcelFileDescriptor5 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(5)!!, "r", null) ?: return
        val parcelFileDescriptor6 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(6)!!, "r", null) ?: return
        val parcelFileDescriptor7 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(7)!!, "r", null) ?: return
        val parcelFileDescriptor8 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(8)!!, "r", null) ?: return

        val inputStream0 = FileInputStream(parcelFileDescriptor0.fileDescriptor)
        val inputStream1 = FileInputStream(parcelFileDescriptor1.fileDescriptor)
        val inputStream2 = FileInputStream(parcelFileDescriptor2.fileDescriptor)
        val inputStream3 = FileInputStream(parcelFileDescriptor3.fileDescriptor)
        val inputStream4 = FileInputStream(parcelFileDescriptor4.fileDescriptor)
        val inputStream5 = FileInputStream(parcelFileDescriptor5.fileDescriptor)
        val inputStream6 = FileInputStream(parcelFileDescriptor6.fileDescriptor)
        val inputStream7 = FileInputStream(parcelFileDescriptor7.fileDescriptor)
        val inputStream8 = FileInputStream(parcelFileDescriptor8.fileDescriptor)

        val file0 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(0)))
        val file1 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(1)))
        val file2 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(2)))
        val file3 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(3)))
        val file4 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(4)))
        val file5 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(5)))
        val file6 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(6)))
        val file7 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(7)))
        val file8 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(8)))

        val outputStream0 = FileOutputStream(file0)
        val outputStream1 = FileOutputStream(file1)
        val outputStream2 = FileOutputStream(file2)
        val outputStream3 = FileOutputStream(file3)
        val outputStream4 = FileOutputStream(file4)
        val outputStream5 = FileOutputStream(file5)
        val outputStream6 = FileOutputStream(file6)
        val outputStream7 = FileOutputStream(file7)
        val outputStream8 = FileOutputStream(file8)

        inputStream0.copyTo(outputStream0)
        inputStream1.copyTo(outputStream1)
        inputStream2.copyTo(outputStream2)
        inputStream3.copyTo(outputStream3)
        inputStream4.copyTo(outputStream4)
        inputStream5.copyTo(outputStream5)
        inputStream6.copyTo(outputStream6)
        inputStream7.copyTo(outputStream7)
        inputStream8.copyTo(outputStream8)

        val r = Random()
        val randomNumber = r.nextInt(9999999)

        progress_bar.progress = 0
        val body0 = UploadRequestBody(file0, "image", this)
        val body1 = UploadRequestBody(file1, "image", this)
        val body2 = UploadRequestBody(file2, "image", this)
        val body3 = UploadRequestBody(file3, "image", this)
        val body4 = UploadRequestBody(file4, "image", this)
        val body5 = UploadRequestBody(file5, "image", this)
        val body6 = UploadRequestBody(file6, "image", this)
        val body7 = UploadRequestBody(file7, "image", this)
        val body8 = UploadRequestBody(file8, "image", this)


        ApiForUpload().uploadImage(MultipartBody.Part.createFormData("p1",file0.name, body0),
            MultipartBody.Part.createFormData("p2",file1.name, body1),
            MultipartBody.Part.createFormData("p3",file2.name, body2),
            MultipartBody.Part.createFormData("p4",file3.name, body3),
            MultipartBody.Part.createFormData("p5",file4.name, body4),
            MultipartBody.Part.createFormData("p6",file5.name, body5),
            MultipartBody.Part.createFormData("p7",file6.name, body6),
            MultipartBody.Part.createFormData("p8",file7.name, body7),
            MultipartBody.Part.createFormData("p9",file8.name, body8),
            MultipartBody.Part.createFormData("p10","", body0),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "json"),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), etOnvanP!!.text.toString()),
            RequestBody.create("multipart/form-data".toMediaType(),etMozoP!!.text.toString()),
            RequestBody.create("multipart/form-data".toMediaType(), etTozihatP!!.text.toString()),
            RequestBody.create("multipart/form-data".toMediaType(), SharedPrefClass.getUserId(activity ,"user")),
            RequestBody.create("multipard/form-data".toMediaType(),"سوژه ها"),
            RequestBody.create("multipard/form-data".toMediaType(),randomNumber.toString()),
            RequestBody.create("multipard/form-data".toMediaType(),etLinkVideo.text.toString()),
            RequestBody.create("multipard/form-data".toMediaType(),etTozihVideo.text.toString()))
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

                        /*SharedPrefClass.clearShenaseRahgiri(activity)
                        val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences("file", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("shenase_rahgiri", randomNumber.toString())
                        editor.commit()*/

                        //getActivity()!!.getIntent().putExtra("shenase_rahgiri", randomNumber.toString());


                        (activity!!.txShenaseRahgiri)!!.setText(randomNumber.toString())
                        //txShenaseRahgiri.setText(randomNumber.toString())

                        activity!!.viewPager.setCurrentItem(0)

                    }
                }
            })
    }

    fun uploadImage10() {

/*        if (arrayList!!.get(0) == null || arrayList!!.get(1) == null || arrayList!!.get(2) == null ||arrayList!!.get(3) == null || arrayList!!.get(4) == null|| arrayList!!.get(5) == null||arrayList!!.get(6) == null||arrayList!!.get(7) == null||arrayList!!.get(8) == null||arrayList!!.get(9) == null) {
            inflatedview!!.clcl.snackbar("باید 10 تصویر انتخاب کنید")
            return
        }*/

        val parcelFileDescriptor0 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(0)!!, "r", null) ?: return
        val parcelFileDescriptor1 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(1)!!, "r", null) ?: return
        val parcelFileDescriptor2 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(2)!!, "r", null) ?: return
        val parcelFileDescriptor3 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(3)!!, "r", null) ?: return
        val parcelFileDescriptor4 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(4)!!, "r", null) ?: return
        val parcelFileDescriptor5 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(5)!!, "r", null) ?: return
        val parcelFileDescriptor6 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(6)!!, "r", null) ?: return
        val parcelFileDescriptor7 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(7)!!, "r", null) ?: return
        val parcelFileDescriptor8 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(8)!!, "r", null) ?: return
        val parcelFileDescriptor9 = activity!!.contentResolver.openFileDescriptor(arrayList!!.get(9)!!, "r", null) ?: return

        val inputStream0 = FileInputStream(parcelFileDescriptor0.fileDescriptor)
        val inputStream1 = FileInputStream(parcelFileDescriptor1.fileDescriptor)
        val inputStream2 = FileInputStream(parcelFileDescriptor2.fileDescriptor)
        val inputStream3 = FileInputStream(parcelFileDescriptor3.fileDescriptor)
        val inputStream4 = FileInputStream(parcelFileDescriptor4.fileDescriptor)
        val inputStream5 = FileInputStream(parcelFileDescriptor5.fileDescriptor)
        val inputStream6 = FileInputStream(parcelFileDescriptor6.fileDescriptor)
        val inputStream7 = FileInputStream(parcelFileDescriptor7.fileDescriptor)
        val inputStream8 = FileInputStream(parcelFileDescriptor8.fileDescriptor)
        val inputStream9 = FileInputStream(parcelFileDescriptor9.fileDescriptor)


        val file0 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(0)))
        val file1 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(1)))
        val file2 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(2)))
        val file3 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(3)))
        val file4 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(4)))
        val file5 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(5)))
        val file6 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(6)))
        val file7 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(7)))
        val file8 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(8)))
        val file9 = File(activity!!.cacheDir, activity!!.contentResolver.getFileName(arrayList!!.get(9)))

        val outputStream0 = FileOutputStream(file0)
        val outputStream1 = FileOutputStream(file1)
        val outputStream2 = FileOutputStream(file2)
        val outputStream3 = FileOutputStream(file3)
        val outputStream4 = FileOutputStream(file4)
        val outputStream5 = FileOutputStream(file5)
        val outputStream6 = FileOutputStream(file6)
        val outputStream7 = FileOutputStream(file7)
        val outputStream8 = FileOutputStream(file8)
        val outputStream9 = FileOutputStream(file9)

        inputStream0.copyTo(outputStream0)
        inputStream1.copyTo(outputStream1)
        inputStream2.copyTo(outputStream2)
        inputStream3.copyTo(outputStream3)
        inputStream4.copyTo(outputStream4)
        inputStream5.copyTo(outputStream5)
        inputStream6.copyTo(outputStream6)
        inputStream7.copyTo(outputStream7)
        inputStream8.copyTo(outputStream8)
        inputStream9.copyTo(outputStream9)



        val r = Random()
        val randomNumber = r.nextInt(9999999)

        progress_bar.progress = 0
        val body0 = UploadRequestBody(file0, "image", this)
        val body1 = UploadRequestBody(file1, "image", this)
        val body2 = UploadRequestBody(file2, "image", this)
        val body3 = UploadRequestBody(file3, "image", this)
        val body4 = UploadRequestBody(file4, "image", this)
        val body5 = UploadRequestBody(file5, "image", this)
        val body6 = UploadRequestBody(file6, "image", this)
        val body7 = UploadRequestBody(file7, "image", this)
        val body8 = UploadRequestBody(file8, "image", this)
        val body9 = UploadRequestBody(file9, "image", this)

        ApiForUpload().uploadImage(MultipartBody.Part.createFormData("p1",file0.name, body0),
            MultipartBody.Part.createFormData("p2",file1.name, body1),
            MultipartBody.Part.createFormData("p3",file2.name, body2),
            MultipartBody.Part.createFormData("p4",file3.name, body3),
            MultipartBody.Part.createFormData("p5",file4.name, body4),
            MultipartBody.Part.createFormData("p6",file5.name, body5),
            MultipartBody.Part.createFormData("p7",file6.name, body6),
            MultipartBody.Part.createFormData("p8",file7.name, body7),
            MultipartBody.Part.createFormData("p9",file8.name, body8),
            MultipartBody.Part.createFormData("p10",file9.name, body9),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "json"),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), etOnvanP!!.text.toString()),
            RequestBody.create("multipart/form-data".toMediaType(),etMozoP!!.text.toString()),
            RequestBody.create("multipart/form-data".toMediaType(), etTozihatP!!.text.toString()),
            RequestBody.create("multipart/form-data".toMediaType(), SharedPrefClass.getUserId(activity ,"user")),
            RequestBody.create("multipard/form-data".toMediaType(),"سوژه ها"),
            RequestBody.create("multipard/form-data".toMediaType(),randomNumber.toString()),
            RequestBody.create("multipard/form-data".toMediaType(),etLinkVideo.text.toString()),
            RequestBody.create("multipard/form-data".toMediaType(),etTozihVideo.text.toString()))
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

                        /*SharedPrefClass.clearShenaseRahgiri(activity)
                        val sharedPreferences: SharedPreferences = activity!!.getSharedPreferences("file", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("shenase_rahgiri", randomNumber.toString())
                        editor.commit()*/

                        //getActivity()!!.getIntent().putExtra("shenase_rahgiri", randomNumber.toString());


                        (activity!!.txShenaseRahgiri)!!.setText(randomNumber.toString())
                        //txShenaseRahgiri.setText(randomNumber.toString())

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