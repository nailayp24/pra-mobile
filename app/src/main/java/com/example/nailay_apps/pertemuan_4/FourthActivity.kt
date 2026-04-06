package com.example.nailay_apps.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nailay_apps.R
import com.example.nailay_apps.databinding.ActivityFourthBinding
import com.example.nailay_apps.databinding.ActivityMainBinding
import com.example.nailay_apps.pertemuan_2.SecondActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nama = intent.getStringExtra("nama")
        val asal = intent.getStringExtra("asal")
        val umur = intent.getIntExtra("umur", 0)
        Log.e("Data Intent", "Nama: $nama , Usia: $umur, Asal: $asal")

        binding.btnShowSnackbar.setOnClickListener {
            Snackbar.make(binding.root, "Ini adalah Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("Tutup"){
                    Log.e("Info Snackbar","Snackbar ditutup")
                }
                .show()
        }
        binding.btnShowAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
//                    val i = Intent(this, SecondActivity::class.java)
                    dialog.dismiss()
                    Log.e("Info Dialog","Anda memilih Ya!")
                }
                .setNegativeButton("Kembali") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog","Anda memilih Tidak!")
                }
                .show()
        }
        binding.btnKembali.setOnClickListener {
            finish()
            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
        }
        Log.e("== onCreate ==", "FourthActivity dibuat pertama kali")
    }
        override fun onStart() {
            super.onStart()
            Log.e("onStart", "onStart: FourthActivity terlihat di layar")
        }

        override fun onDestroy() {
            super.onDestroy()
            Log.e("onDestroy", "FourthActivity dihapus dari stack")
        }
    }
