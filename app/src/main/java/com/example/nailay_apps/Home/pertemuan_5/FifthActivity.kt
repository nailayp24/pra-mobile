package com.example.nailay_apps.Home.pertemuan_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nailay_apps.R
import com.example.nailay_apps.databinding.ActivityFifthBinding

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            // IMPROVISASI 3: Mengatur Title & Subtitle secara dinamis via kode (Bukan XML)
            // Memberikan sentuhan personal pada aplikasi
            title = "Halo, Naila Yohanda!"
            subtitle = "Mahasiswa Sistem Informasi"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            // Logika untuk menu Checkbox improvisasi
            R.id.action_status -> {
                item.isChecked = !item.isChecked // Membalik status centang
                val status = if (item.isChecked) "Aktif" else "Nonaktif"
                Toast.makeText(this, "Mode Hemat Data: $status", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sub_profile -> {
                Toast.makeText(this, "Membuka Profil...", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}