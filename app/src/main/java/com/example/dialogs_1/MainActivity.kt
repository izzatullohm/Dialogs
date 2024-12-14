package com.example.dialogs_1

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.dialogs_1.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.btnAlert.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setTitle("Dialog")
                .setMessage("Hello World")
                .setPositiveButton("Positive") { dialog, which ->
                    Toast.makeText(this, "Positive", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Negative") { dialog, which ->
                    Toast.makeText(this, "Negative", Toast.LENGTH_SHORT).show()
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
        binding.btnCustom.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            val dialog = alertDialog.create()

            dialog.setTitle("Title Custom dialog")
            val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null, false)
            dialog.setView(dialogView)
            dialog.show()
        }
        binding.btnDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this)
            datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(this, "${dayOfMonth}.${month+1}.$year", Toast.LENGTH_SHORT).show()
            }
            datePickerDialog.show()
        }
        binding.btnTime.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        Toast.makeText(this@MainActivity, "$hourOfDay:$minute", Toast.LENGTH_SHORT).show()
                    }},24, 60, true)
            timePickerDialog.show()
        }
        binding.btnBottom.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(layoutInflater.inflate(R.layout.item_bottom_sheets, null, false))
            bottomSheetDialog.show()
        }
        binding.btnSnackbar.setOnClickListener {
            val snackbar = Snackbar.make(it, "Salom snackbar", Snackbar.LENGTH_LONG)

            snackbar.setAction("Bos", object : View.OnClickListener {
                override fun onClick(v: View?) {
                    Toast.makeText(this@MainActivity, "Bosildi", Toast.LENGTH_SHORT).show()
                }
            })

            snackbar.show()
        }

        binding.btnFragment.setOnClickListener {
            val myDialogFragment = MyDialogFragment()
            myDialogFragment.show(supportFragmentManager.beginTransaction(), "keylar")
        }
    }
}