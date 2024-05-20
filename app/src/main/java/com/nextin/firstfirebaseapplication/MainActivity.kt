package com.nextin.firstfirebaseapplication

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.nextin.firstfirebaseapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var binding :ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Write a message to the database
        val database = Firebase.database
        val myRef1 = database.getReference("message")
        val myRef2 = database.getReference("id")
        val myRef3 = database.getReference("username")
        myRef1.setValue("Hello, World!")
        myRef2.setValue("Ak47")
        myRef3.setValue("kshitij26")

        myRef2.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()

                binding?.tvMessage?.text = value
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Failed to read value", error.toException())
            }
        })
    }
}