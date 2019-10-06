package com.example.rockpaperscissors_level4_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rockpaperscissors_level4_assignment2.adapters.HistoryAdapter
import com.example.rockpaperscissors_level4_assignment2.database.ResultRepository
import com.example.rockpaperscissors_level4_assignment2.models.Result
import kotlinx.android.synthetic.main.activity_history.*
import androidx.recyclerview.widget.DividerItemDecoration



class HistoryActivity : AppCompatActivity() {

    private lateinit var resultRepository: ResultRepository

    private val results = arrayListOf<Result>()
    private val historyAdapter = HistoryAdapter(results)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        //initialize the repos
        resultRepository = ResultRepository(this)

        initView()
    }

    fun initView(){
        //configure the recyclerView and load the results from the database
        rvHistory.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rvHistory.adapter = historyAdapter
        //horizontal divider
        //add a horizontal divider (line/ border) at the bottom of the items in the collection
        rvHistory.addItemDecoration(DividerItemDecoration(this@HistoryActivity, DividerItemDecoration.HORIZONTAL))
        //initialize the list with data
        reloadResults()

        //bind the event handlers
        btnDeleteAll.setOnClickListener{
            removeAllHisotry()
        }

    }

    /**
     * reloads the results with the most recent data from the database
     */
    fun reloadResults(){
        results.clear()
        results.addAll(ArrayList(resultRepository.getAllResults()))
        historyAdapter.notifyDataSetChanged()
    }

    /**
     * Removes all history and reloads the recyclerView.
     */
    fun removeAllHisotry(){
        resultRepository.deleteAllResults()
        reloadResults()
    }



}
