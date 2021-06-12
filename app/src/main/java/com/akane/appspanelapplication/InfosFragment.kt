package com.akane.appspanelapplication

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_infos.*
import okhttp3.*
import java.io.IOException

// afficher une page web


class InfosFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_infos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getWebPage()

    }

    private fun getWebPage(){


            val url = "https://test-pgt-dev.apnl.ws/html"

            val client = OkHttpClient()

            val request = Request.Builder().addHeader("X-AP-Key", "uD4Muli8nO6nzkSlsNM3d1Pm").addHeader(
                "X-AP-DeviceUID",
                "trial"
            ).addHeader("Accept", "text/html").url(url).get().build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    // Log.e("test",response.body!!.string())
                        val unencodedHtml = response.body!!.string()
                        val encodeHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)

                        wvInfo.post( Runnable {
                            wvInfo.loadData(encodeHtml, "text/html", "base64")
                        })
                    }
                }
            })
        }
}