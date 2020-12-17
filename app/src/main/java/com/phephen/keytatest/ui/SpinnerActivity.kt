package com.phephen.keytatest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.phephen.keytatest.api.model.DataModel
import com.phephen.keytatest.api.service.RetrofitClient
import com.phephen.keytatest.databinding.ActivitySpinnerBinding
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SpinnerActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpinnerBinding
    private lateinit var btnSave: Button
    private lateinit var spinner: Spinner
    private lateinit var data: DataModel
    private lateinit var tvItemSelectedTitle: TextView
    private lateinit var tvItemSelected: TextView
    val datas = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null)
            supportActionBar?.hide()
        getIntentData()
        initView()
        val datas = listOf(data.name)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, datas)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    applicationContext,
                    data.name + " " + "" + datas[position],
                    Toast.LENGTH_SHORT
                ).show()
                btnSave.text = "Kirim"
                btnSave.setOnClickListener {
                    spinner.visibility = View.GONE
                    tvItemSelectedTitle.visibility = View.VISIBLE
                    tvItemSelected.visibility = View.VISIBLE
                    tvItemSelected.text = data.name
                    postData()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun getIntentData() {
        data = intent.extras!!.getParcelable("data")!!
    }

    private fun initView() {
        btnSave = binding.btnSave
        spinner = binding.spinner
        tvItemSelectedTitle = binding.tvItemSelectedTitle
        tvItemSelected = binding.tvItemSelected
    }

    private fun postData() {
        RetrofitClient.getClient.postData(data.id, data.name)
            ?.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
                    if (response.isSuccessful()) {
                        Toast.makeText(this@SpinnerActivity, response.message(), Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@SpinnerActivity, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(this@SpinnerActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}