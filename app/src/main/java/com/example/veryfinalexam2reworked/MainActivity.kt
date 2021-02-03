package com.example.veryfinalexam2reworked

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.text.TextWatcher





data class CustomClass(
        var id: Int = 0,
        var name: String = ""
){

    override fun toString(): String {
        return name
    }

}


class MainActivity : AppCompatActivity() {

    private lateinit var metric: Spinner
    private lateinit var imperial: Spinner
    private lateinit var metricadapter: ArrayAdapter<CustomClass>
    private lateinit var imperialadapter: ArrayAdapter<CustomClass>
    var switch = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        metric = findViewById(R.id.metric)
        imperial = findViewById(R.id.imperial)


        val _metric = Metrics()
        val _imperial = Imperials()

        if (!switch) {
            metricadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, _metric)
            imperialadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, _imperial)
        } else {
            metricadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, _imperial)
            imperialadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, _metric)
        }
        metric.adapter = metricadapter
        imperial.adapter = imperialadapter


        metric.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val metricselectedObject = metric.selectedItem as CustomClass
                val imperialselectedObject = imperial.selectedItem as CustomClass
                val input1 = findViewById<EditText>(R.id.input1)
                val input2 = findViewById<TextView>(R.id.input2)
                if (input1.text.toString() == "" || input1.text.toString().toInt() == 0) {

                } else {
                    input2.setText(
                            (input1.text.toString()
                                    .toInt() / (imperialselectedObject.id.toFloat() / metricselectedObject.id.toFloat())).toString()
                    )
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // You can define you actions as you want
            }
        }



        imperial.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val metricselectedObject = metric.selectedItem as CustomClass
                val imperialselectedObject = imperial.selectedItem as CustomClass
                val input1 = findViewById<EditText>(R.id.input1)
                val input2 = findViewById<TextView>(R.id.input2)
                if (input1.text.toString() == "" || input1.text.toString().toInt() == 0) {

                } else {
                    input2.setText(
                            (input1.text.toString()
                                    .toInt() / (imperialselectedObject.id.toFloat() / metricselectedObject.id.toFloat())).toString()
                    )

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }


        val edittext = findViewById<EditText>(R.id.input1)

    }

    private fun Metrics(): ArrayList<CustomClass> {
        val metric = ArrayList<CustomClass>()
        metric.apply {
            add(CustomClass(0, "Choose Option"))
            add(CustomClass(10, "Millimeter"))
            add(CustomClass(100, "Centimeter"))
            add(CustomClass(10000, "Meter"))
            add(CustomClass(10000000, "Kilometer"))
        }
        return metric
    }

    private fun Imperials(): ArrayList<CustomClass> {
        val imperial = ArrayList<CustomClass>()
        imperial.apply {
            add(CustomClass(0, "Choose Option"))
            add(CustomClass(254, "Inch"))
            add(CustomClass(9144, "Yard"))
            add(CustomClass(3048, "Feet"))
            add(CustomClass(16093400, "Mile"))
        }
        return imperial
    }

    fun Swap(view: View) {
        val _metric = Metrics()
        val _imperial = Imperials()
        if (!switch) {
            metricadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, _imperial)
            imperialadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, _metric)
        } else {
            metricadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, _metric)
            imperialadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, _imperial)
        }
        switch = !switch
        metric.adapter = metricadapter
        imperial.adapter = imperialadapter
    }
}