package com.example.rockpaperscissors_level4_assignment2

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
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

    /**
     * initialize the view and all its components.
     */
    private fun initView(){
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
        btnOpenHisotry.setOnClickListener{
            openHistory()
        }
    }

    /**
     * Opens the history activity
     */
    private fun openHistory(){
        val historyActivity = Intent(this, HistoryActivity::class.java)
        startActivity(historyActivity)
    }

    private fun doRock(){
        //perform the roll of the pc against your roll and evaluate the result
        val pcRoll = performComputerRoll()
        when(pcRoll){
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
        //set the result images to indicate to the user what the result of the match was
        setResultImages(GameChoice.ROCK.value, pcRoll)
    }

    private fun doPaper(){
        //perform the roll of the pc against your roll and evaluate the result
        val pcRoll = performComputerRoll()
        when(pcRoll){
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
        //set the result images to indicate to the user what the result of the match was
        setResultImages(GameChoice.PAPER.value, pcRoll)
    }

    private fun doScissors(){
        //perform the roll of the pc against your roll and evaluate the result
        val  pcRoll = performComputerRoll()
        when(pcRoll){
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
        //set the result images to indicate to the user what the result of the match was
        setResultImages(GameChoice.SCISSORS.value, pcRoll)
    }

    /**
     * Writes a lost score to the database and informs the user the round was lost
     */
    private fun performLose(myRoll : Int, computerRoll : Int){
        resultRepository.insertResult(
            Result(
                myRoll = myRoll,
                computerRoll = computerRoll,
                ganeResult = GameResult.LOSE.value
            ))
        tvResult.text = "You lose!"

    }

    /**
     * Writes a win score to the database and informs the user the round was won
     */
    private fun performWin(myRoll : Int, computerRoll : Int){
        resultRepository.insertResult(
            Result(
                myRoll = myRoll,
                computerRoll = computerRoll,
                ganeResult = GameResult.WIN.value
            ))
        tvResult.text = "You win!"
    }

    /**
     * Writes a draw score to the database and informs the user the round was a draw
     */
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
     * Sets the result images based on the player/ computer roll
     */
    private fun setResultImages(myRoll :  Int, computerRoll: Int){
        ivYouRoll.setImageDrawable(getResultResource(myRoll))
        ivComputerRoll.setImageDrawable(getResultResource(computerRoll))
    }

    /***
     * creates a Drawable resource based on the passed GameChoice (rock, paper, scissors)
     */
    private fun getResultResource(gameChoice : Int) : Drawable{
        //determine the path based on the GameChoice/ roll that was passed
        val result : Drawable?? = when(gameChoice){
            GameChoice.ROCK.value -> {
                ContextCompat.getDrawable(this, R.drawable.rock)
            }
            GameChoice.PAPER.value -> {
                ContextCompat.getDrawable(this, R.drawable.paper)
            }
            GameChoice.SCISSORS.value -> {
                ContextCompat.getDrawable(this, R.drawable.scissors)
            }
            else -> {
                null
            }
        }
        return result!!
    }


    /**
     * Function to perform a roll for the computer and generate a random result
     */
    private fun performComputerRoll() : Int{
        return Random.nextInt(1,4)
    }
}
