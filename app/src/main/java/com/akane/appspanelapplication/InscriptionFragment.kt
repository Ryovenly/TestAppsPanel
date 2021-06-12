package com.akane.appspanelapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_inscription.*
import okhttp3.*
import java.io.IOException


// Inscription

class InscriptionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_inscription, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btInscription.setOnClickListener { registerUser() }
    }


    private fun registerUser(){

        if(etName.text.isEmpty()){
            Toast.makeText(context, "Veuillez insérer un nom", Toast.LENGTH_SHORT).show()
            return
        }

        if(etMail.text.isEmpty()){
            Toast.makeText(context, "Veuillez insérer un email", Toast.LENGTH_SHORT).show()
            return
        }

        if(!etMail.text.contains("@") || !etMail.text.contains(".")){
            Toast.makeText(context, "Veuillez insérer un email valide", Toast.LENGTH_SHORT).show()
            return
        }

        if(etPhone.text.isEmpty()){
            Toast.makeText(context, "Veuillez insérer un numéro de téléphone", Toast.LENGTH_SHORT).show()
            return
        }


        val url = "https://test-pgt-dev.apnl.ws/authentication/register"

        val client = OkHttpClient()

        val formBody = FormBody.Builder().add("name", etName.text.toString()).add(
            "email",
            etMail.text.toString()
        ).add("phone", etPhone.text.toString()).build()

        val request = Request.Builder().addHeader("X-AP-Key", "uD4Muli8nO6nzkSlsNM3d1Pm").addHeader(
            "X-AP-DeviceUID",
            "trial"
        ).addHeader("Accept", "application/json").url(url).post(formBody).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response ${response.body!!.string()}")
                        Log.e("test", "Inscription réussi")
                             Thread(Runnable {
                            try {
                                Toast.makeText(context, "Inscription réussi !", Toast.LENGTH_SHORT)
                                    .show()
                            } catch (e: InterruptedException) {
                                e.printStackTrace()
                            }
                        })

                }
            }
        })

    }



}