package com.phephen.keytatest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.phephen.keytatest.api.model.DataModel
import com.phephen.keytatest.api.service.RetrofitClient
import com.phephen.keytatest.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var btnSave: Button
    private lateinit var etName: EditText
    private lateinit var tvTitle: TextView
    private lateinit var progressBar: ProgressBar
    private var datas = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null)
            supportActionBar?.hide()

        initView()
        getData()
    }

    private fun initView() {
        btnSave = binding.btnSave
        etName = binding.etName
        tvTitle = binding.tvTitle
        progressBar = binding.progressBar
    }

    private fun getData() {
        val call: Call<List<DataModel>> = RetrofitClient.getClient.getDataName()
        call.enqueue(object : Callback<List<DataModel>> {
            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val datas = response.body()
                    for (i in datas!!.indices) {
                        if (i < datas.size) {
                        val data = datas[i]
                            etName.isEnabled = false
                            val name: String = data.name.toString()
                            val newName = name.replace(" ", ", ")
                            etName.setText(newName)
                            println(newName)

                            btnSave.setOnClickListener {
                                val intent = Intent(this@MainActivity, SpinnerActivity::class.java)
                                intent.putExtra("data", data)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "error" + t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}