package com.haz7.guesstheanswer

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import java.util.zip.Inflater


class WinnerFragment : Fragment() {

    lateinit var finalScore:TextView
    lateinit var requestPermission:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_winner, container, false)
        finalScore=view.findViewById(R.id.FragmentScore)
        requestPermission=view.findViewById(R.id.btnReqPer)
        val result = arguments?.getInt("Scour")
        finalScore.text = "this is your Score :$result"


        requestPermission.setOnClickListener {
            requestPermission()
        }



        // Inflate the layout for this fragment
        return view
    }
    //check if we have permission
    private fun hasWriteExternalStorePermission():Boolean{
        return getContext()?.let { ActivityCompat.checkSelfPermission(it,Manifest.permission.WRITE_EXTERNAL_STORAGE) } ==PackageManager.PERMISSION_GRANTED

    }
    //make the request permission
    private fun requestPermission(){
        //add the permission
        var permission= mutableListOf<String>()
        //if we don't have per
        if (!hasWriteExternalStorePermission()){
            permission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        //grant the permission in the list
        if (permission.isNotEmpty()){
            ActivityCompat.requestPermissions(getContext() as Activity,permission.toTypedArray(),0)
        }


    }


}