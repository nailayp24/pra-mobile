package com.example.nailay_apps.Home.pertemuan_5

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.nailay_apps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Merdeka"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true

            // IMPROVISASI 4: Mengaktifkan Zoom Controls (Gak ada di modul)
            // Memungkinkan user untuk mencubit layar (pinch) untuk perbesar web
            settings.builtInZoomControls = true
            settings.displayZoomControls = false // Sembunyikan tombol zoom yang mengganggu UI

            loadUrl("https://merdeka.com")

            // IMPROVISASI 5: Sinkronisasi Scroll dengan pergerakan Toolbar (Sticky/Hide)
            // Membuat area baca menjadi lebih luas saat user scroll ke bawah
            setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (scrollY > oldScrollY) binding.appBar.setExpanded(false, true)
                else if (scrollY < oldScrollY) binding.appBar.setExpanded(true, true)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}