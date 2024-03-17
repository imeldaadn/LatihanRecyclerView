package com.imeldaaudina.latihanrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvHandphone: RecyclerView
    private val list = ArrayList<Handphone>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvHandphone = findViewById(R.id.rv_handphone)
        rvHandphone.setHasFixedSize(true)
        list.addAll(getListHandphone())
        showRecyclerList()
    }
    private fun getListHandphone(): ArrayList<Handphone> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription =
            resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listHandphone = ArrayList<Handphone>()
        for (i in dataName.indices) {
            val handphone = Handphone(dataName[i], dataDescription[i],
                dataPhoto.getResourceId(i, -1))
            listHandphone.add(handphone)
        }
        return listHandphone
    }
    private fun showRecyclerList() {
        rvHandphone.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHandphoneAdapter(list)
        rvHandphone.adapter = listHeroAdapter
        listHeroAdapter.setOnItemClickCallback(object :
            ListHandphoneAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Handphone) {
                showSelectedHandphone(data)
            }
        })
    }
    private fun showSelectedHandphone(handphone: Handphone) {
        Toast.makeText(this, "Kamu memilih " + handphone.name,
            Toast.LENGTH_SHORT).show()
    }

    private var isGridLayout = false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_linear -> {
                isGridLayout = false
                changeLayoutToLinear()
                true
            }
            R.id.item_grid -> {
                isGridLayout = true
                changeLayoutToGrid()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun changeLayoutToLinear() {
        rvHandphone.layoutManager = LinearLayoutManager(this)
        Toast.makeText(this, "Linear Layout dipilih", Toast.LENGTH_SHORT).show()
    }
    private fun changeLayoutToGrid() {
        rvHandphone.layoutManager = GridLayoutManager(this, 2)
        Toast.makeText(this, "Grid Layout dipilih", Toast.LENGTH_SHORT).show()
    }
}