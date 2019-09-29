package com.example.rockpaperscissors_level4_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rockpaperscissors_level4_assignment2.database.ResultRepository
import com.example.rockpaperscissors_level4_assignment2.enums.GameChoice
import com.example.rockpaperscissors_level4_assignment2.enums.GameResult
import com.example.rockpaperscissors_level4_assignment2.models.Result
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var resultRepository: ResultRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize the repos
        resultRepository = ResultRepository(this)

        //initialize the view elements/ controls
        initView()
    }

    fun initView(){
        //setup the clickHandlers
        btnRock.setOnClickListener{
            doRock()
        }
        btnPaper.setOnClickListener{
            doPaper()
        }
        btnScissors.setOnClickListener{
            doScissors()
        }
    }

    private fun doRock(){
        when(val pcRoll = performComputerRoll()){
            GameChoice.PAPER.value ->{
                performLose(GameChoice.ROCK.value, pcRoll)
            }
            GameChoice.SCISSORS.value -> {
                performWin(GameChoice.ROCK.value, pcRoll)
            }
            GameChoice.ROCK.value -> {
                performDraw(GameChoice.ROCK.value, pcRoll)
            }
            else ->{
                Toast.makeText(this, "Invalid roll, something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun doPaper(){
        when(val pcRoll = performComputerRoll()){
            GameChoice.PAPER.value ->{
                performDraw(GameChoice.PAPER.value,pcRoll)
            }
            GameChoice.SCISSORS.value -> {
                performLose(GameChoice.PAPER.value, pcRoll)
            }
            GameChoice.ROCK.value -> {
                performWin(GameChoice.PAPER.value, pcRoll)
            }
            else ->{
                Toast.makeText(this, "Invalid roll, something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun doScissors(){
        when(val  pcRoll = performComputerRoll()){
            GameChoice.SCISSORS.value -> {
                performDraw(GameChoice.SCISSORS.value, pcRoll)
            }
            GameChoice.ROCK.value ->{
                performLose(GameChoice.SCISSORS.value,pcRoll )
            }
            GameChoice.PAPER.value ->{
                performWin(GameChoice.SCISSORS.value, pcRoll)
            }
            else ->{
                Toast.makeText(this, "Invalid roll, something went wrong", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun performLose(myRoll : Int, computerRoll : Int){
        resultRepository.insertResult(
            Result(
                myRoll = myRoll,
                computerRoll = computerRoll,
                ganeResult = GameResult.LOSE.value
            ))
        tvResult.text = "You lose!"
    }

    private fun performWin(myRoll : Int, computerRoll : Int){
        resultRepository.insertResult(
            Result(
                myRoll = myRoll,
                computerRoll = computerRoll,
                ganeResult = GameResult.WIN.value
            ))
        tvResult.text = "You win!"
    }

    private fun performDraw(myRoll : Int, computerRoll : Int){
        resultRepository.insertResult(
            Result(
                myRoll = myRoll,
                computerRoll = computerRoll,
                ganeResult = GameResult.DRAW.value
            ))
        tvResult.text = "Draw!"
    }

    /**
     * Function to perform a roll for the computer and generate a random result
     */
    private fun performComputerRoll() : Int{
        return Random.nextInt(1,4)
    }
}
