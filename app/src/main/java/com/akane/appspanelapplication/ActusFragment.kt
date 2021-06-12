package com.akane.appspanelapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akane.appspanelapplication.adapter.ActuAdapter
import com.akane.appspanelapplication.model.Actu
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_actus.*
import okhttp3.*
import org.json.JSONException
import java.io.IOException

class ActusFragment : Fragment() {

    var actus = ArrayList<Actu>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_actus, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListRecyclerView()
    }



    private fun initListRecyclerView() {

        rvActus.layoutManager = LinearLayoutManager(context)
        val adapter = ActuAdapter(actus)
        rvActus.adapter = adapter
        getActus()
    }


    private fun getActus(){


        val url = "https://test-pgt-dev.apnl.ws/events"

        val client = OkHttpClient()

        val request = Request.Builder().addHeader("X-AP-Key", "uD4Muli8nO6nzkSlsNM3d1Pm").addHeader(
            "X-AP-DeviceUID",
            "trial"
        ).addHeader("Accept", "application/json").url(url).get().build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val responseData = response.body!!.toString()
                     //Log.e("test", responseData)
                   try {

                       // Déserialiser le json mais ne fonctionne pas

//                       val gson = Gson()
//                       val jsonString = gson.toJson(responseData)
//                       val sType = object : TypeToken<Array<Actu>>(){}.type
//
//                       val actus = gson.fromJson<Array<Actu>>(jsonString, sType)
                      // val dest = actus.data?.get(1)?.description


                       rvActus.adapter?.notifyDataSetChanged()
                   } catch (e:JSONException){
                       e.printStackTrace()
                   }

                }



            }
        })
    }

}