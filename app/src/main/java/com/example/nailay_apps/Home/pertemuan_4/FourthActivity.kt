package com.example.nailay_apps.Home.pertemuan_4

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.nailay_apps.databinding.ActivityFourthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Detail Info"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val nama = intent.getStringExtra("nama")
        Log.e("Data Intent", "Nama: $nama")

        binding.btnShowSnackbar.setOnClickListener {
            Snackbar.make(binding.root, "Ini adalah Snackbar", Snackbar.LENGTH_SHORT).show()
        }

        binding.btnShowAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin?")
                .setPositiveButton("Ya") { dialog, _ -> dialog.dismiss() }
                .setNegativeButton("Kembali") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        // btnKembali DIHAPUS karena sudah ada tombol back di Toolbar
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}