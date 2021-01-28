package com.cemfindikli.myparkingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cemfindikli.myparkingapp.adapter.RecyclerViewAdapter
import com.cemfindikli.myparkingapp.model.LocationModel
import com.cemfindikli.myparkingapp.service.LocationAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var locationModels: ArrayList<LocationModel>? = null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null

    //Disposable
    private var compositeDisposable: CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        compositeDisposable = CompositeDisposable()


        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        loadData()

    }

    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(LocationAPI::class.java)


        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))



    }

    private fun handleResponse(locationList : List<LocationModel>){
        locationModels = ArrayList(locationList)

        locationModels?.let {
            recyclerViewAdapter = RecyclerViewAdapter(it,this@MainActivity)
            recyclerView.adapter = recyclerViewAdapter
        }
    }

    override fun onItemClick(locationModel: LocationModel) {
        Toast.makeText(this,"Clicked : ${locationModel.name}", Toast.LENGTH_LONG ).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable?.clear()
    }

}