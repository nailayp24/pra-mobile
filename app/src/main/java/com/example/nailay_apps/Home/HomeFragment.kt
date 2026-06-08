package com.example.nailay_apps.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope // Pastikan import ini ada untuk coroutine
import com.example.nailay_apps.Home.pertemuan_10.TenthActivity
import com.example.nailay_apps.Home.pertemuan_13.ThirteenthActivity
import com.example.nailay_apps.Home.pertemuan_13.ThirteenthTabsAdapter
import com.example.nailay_apps.Home.pertemuan_2.SecondActivity
import com.example.nailay_apps.Home.pertemuan_3.ThirdActivity
import com.example.nailay_apps.Home.pertemuan_4.FourthActivity
import com.example.nailay_apps.Home.pertemuan_5.FifthActivity
import com.example.nailay_apps.Home.pertemuan_7.SeventhActivity
import com.example.nailay_apps.Home.pertemuan_8.EightActivity
import com.example.nailay_apps.R
import com.example.nailay_apps.data.api.CatFactApiClient
import com.example.nailay_apps.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch // Pastikan import ini ada

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) // Tambahkan super call untuk keamanan lifecycle

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        // ---  INI UNTUK PERTEMUAN 2 ---
        binding.btnToSecond.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }

        // ---  INI UNTUK PERTEMUAN 3 ---
        binding.btnToThird.setOnClickListener {
            val intent = Intent(requireContext(), ThirdActivity::class.java)
            startActivity(intent)
        }

        // Tombol ke Pertemuan 4
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("nama", "Politeknik Caltex Riau")
            intent.putExtra("asal", "Rumbai")
            intent.putExtra("umur", 25)
            startActivity(intent)
        }

        // ---  INI UNTUK PERTEMUAN 5 ---
        binding.btnToFifth.setOnClickListener {
            val intent = Intent(requireContext(), FifthActivity::class.java)
            startActivity(intent)
        }

        // ---  INI UNTUK PERTEMUAN 7 ---
        binding.btnToSeventh.setOnClickListener {
            val intent = Intent(requireContext(), SeventhActivity::class.java)
            startActivity(intent)
        }

        // ---  INI UNTUK PERTEMUAN 8 ---
        binding.btnToEight.setOnClickListener {
            val intent = Intent(requireContext(), EightActivity::class.java)
            startActivity(intent)
        }

        // ---  INI UNTUK PERTEMUAN 10 ---
        binding.btnToTenth.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        // ---  INI UNTUK PERTEMUAN 10 ---
        binding.btnToThirteenth.setOnClickListener {
            startActivity(Intent(requireContext(), ThirteenthActivity::class.java))
        }

        // Memanggil fungsi load data pertama kali fragment dibuat
        loadCatFact()

        // Tombol Logout
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    sharedPref.edit {
                        clear()
                        apply()
                    }
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Tidak!")
                }
                .show()
        }

        // Tombol Refresh
        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }
    }

    // --- FUNGSI SEKARANG BERDIRI SENDIRI DI LUAR onViewCreated ---
    private fun loadCatFact() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
                Log.e("HomeFragment", "Error fetch API", e)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}