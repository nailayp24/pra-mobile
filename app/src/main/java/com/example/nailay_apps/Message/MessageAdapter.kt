package com.example.nailay_apps.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.nailay_apps.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Menggunakan ViewBinding untuk layout item_message
        val binding: ItemMessageBinding = ItemMessageBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        val view = binding.root

        // Ambil data berdasarkan posisi item
        val data = messages[position]

        // Memuat gambar menggunakan library Glide asli
        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.avatarImg)

        // Set data teks sesuai model
        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        // Aksi klik menampilkan Snackbar (Gunakan variabel 'data')
        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Pesan dari ${data.senderName}: ${data.messageText}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}