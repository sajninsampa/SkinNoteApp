package com.example.skincarenote

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.skincarenote.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var db: AppDatabase
    private var noteid = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        db = AppDatabase.getDatabase(this)
        noteid = intent.getIntExtra("id", -1)

        if (noteid != -1) {
            binding.nameET.setText(intent.getStringExtra("name"))
            binding.addressET.setText(intent.getStringExtra("address"))
            binding.phoneET.setText(intent.getStringExtra("phone"))
        }

        binding.button.setOnClickListener {
            val name = binding.nameET.text.toString()
            val address = binding.addressET.text.toString()
            val phone = binding.phoneET.text.toString()

            if (name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (noteid == -1) {
                // INSERT
                val note = Note(name = name, address = address, phone = phone)
                db.noteDao().insert(note)
            } else {
                // UPDATE
                val note = Note(id = noteid, name = name, address = address, phone = phone)
                db.noteDao().update(note)
            }

            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}