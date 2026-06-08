package com.example.nailay_apps.Home.pertemuan_13

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nailay_apps.databinding.FragmentTabQrcodeBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter // Menggunakan QRCodeWriter untuk QR Code

class TabQrcodeFragment : Fragment() {

    private var _binding: FragmentTabQrcodeBinding? = null
    // Properti ini hanya valid antara onCreateView dan onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // PERBAIKAN: Inisialisasi _binding di sini
        _binding = FragmentTabQrcodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) // Hapus ': View' dan ': Bundle?'

        binding.btnGenerate.setOnClickListener {
            val text = binding.edtQrInput.text.toString().trim()
            if (text.isEmpty()) return@setOnClickListener
            binding.ivQrCode.setImageBitmap(createQR(text))
        }
    }

    private fun createQR(text: String): Bitmap {
        // PERBAIKAN tambahan: Gunakan QRCodeWriter jika ingin membuat QR Code, bukan UPCAWriter (Barcode)
        val writer = QRCodeWriter()
        val matrix = writer.encode(
            text,
            BarcodeFormat.QR_CODE,
            500,
            500,
            mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")
        )
        return Bitmap.createBitmap(500, 500, Bitmap.Config.RGB_565).apply {
            for (x in 0 until 500) {
                for (y in 0 until 500) {
                    setPixel(x, y, if (matrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Menghindari memory leak
    }
}