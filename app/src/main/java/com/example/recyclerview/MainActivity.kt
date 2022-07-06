package com.example.recyclerview

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.adapter.AdapterTeamBola
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.model.Pemain

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val  listPemain = ArrayList<Pemain>()
        listPemain.add(Pemain("Kim Namjoon",R.drawable.bts1, "Ketua", "183cm","Ilsan","1 Januari 1993"))
        listPemain.add(Pemain("Kim Seokjin",R.drawable.bts2, "Vocal", "184cm","Seoul","2 Januari 1993"))
        listPemain.add(Pemain("Min Yoongi",R.drawable.bts3, "Rapper", "185cm","Seoul","3 Januari 1994"))
        listPemain.add(Pemain("J-Hope",R.drawable.bts4, "Rapper", "186cm","Seoul","4 Januari 1995"))
        listPemain.add(Pemain("Park Jimin",R.drawable.bts5, "Vocal", "187cm","Seoul","5 Januari 1996"))

        binding.list.adapter = AdapterTeamBola(this, listPemain,object : AdapterTeamBola.OnClikListener{
            override fun detailData(item: Pemain?) {
                Dialog(this@MainActivity).apply {
                    requestWindowFeature(Window.FEATURE_NO_TITLE)
                    setCancelable(true)
                    setContentView(R.layout.detail_data_pemain)

                    val image = this.findViewById<ImageView>(R.id.image_pemain)
                    val nama = this.findViewById<TextView>(R.id.txtnama)

                    val posisi = this.findViewById<TextView>(R.id.txtposisi)
                    val tinggi = this.findViewById<TextView>(R.id.txttinggi)
                    val tempatlahir = this.findViewById<TextView>(R.id.txttempatlahir)
                    val tanggallahir = this.findViewById<TextView>(R.id.txttanggallahir)
                    val btn = this.findViewById<Button>(R.id.btnClose)

                    image.setImageResource(item?.foto ?:0)
                    nama.text = "${item?.nama}"
                    posisi.text = "${item?.posisi}"
                    tinggi.text = "${item?.tinggi}"
                    tempatlahir.text = "${item?.tempatlahir}"
                    tanggallahir.text = "${item?.tgllahir}"


                    btn.setOnClickListener {
                        this.dismiss()
                    }


                }.show()
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode (selectedMode : Int){
        when (selectedMode){
            R.id.myprofile -> {
                val  intent = Intent ( this,Profile::class.java)
                startActivity(intent)
            }
        }
    }

}