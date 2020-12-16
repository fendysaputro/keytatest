package com.phephen.keytatest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.phephen.keytatest.R
import com.phephen.keytatest.api.model.DataModel
import com.phephen.keytatest.databinding.ActivitySpinnerBinding

class SpinnerActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivitySpinnerBinding
    private lateinit var btnSave: Button
    private lateinit var spinner: Spinner
    private lateinit var data: DataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null)
            supportActionBar?.hide()
        getIntentData()
        initView()
        spinner.onItemSelectedListener = this
        getSpinnerData()
    }

    private fun getIntentData() {
        data = intent.extras!!.getParcelable("data")!!
    }

    private fun initView() {
        btnSave = binding.btnSave
        spinner = binding.spinner
    }

    private fun getSpinnerData() {
        val languages = listOf(data.name)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languages)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}