package com.example.rockpaperscissors_level4_assignment2.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors_level4_assignment2.R
import com.example.rockpaperscissors_level4_assignment2.enums.GameChoice
import com.example.rockpaperscissors_level4_assignment2.enums.GameResult
import com.example.rockpaperscissors_level4_assignment2.models.Result
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val results: List<Result>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        context = parent.context

        //bind the history partial to the viewHolder
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_history, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: Result) {

            //get the winner text
            val winnerText = when(result.ganeResult){
                GameResult.LOSE.value -> {
                    "Computer wins"
                }
                GameResult.WIN.value -> {
                    "You win!"
                }
                GameResult.DRAW.value ->{
                    "Draw"
                }
                else ->{"Something went wrong"}
            }

            //initialize the list item
            itemView.tvWinner.text = winnerText
            itemView.ivComputerRoll.setImageDrawable(getResultResource(result.computerRoll))
            itemView.ivYouRoll.setImageDrawable(getResultResource(result.myRoll))
            itemView.tvDate.text = result.date
        }
    }

    /***
     * creates a Drawable resource based on the passed GameChoice (rock, paper, scissors)
     */
    private fun getResultResource(gameChoice : Int) : Drawable {
        //determine the path based on the GameChoice/ roll that was passed
        val result : Drawable?? = when(gameChoice){
            GameChoice.ROCK.value -> {
                ContextCompat.getDrawable(context, R.drawable.rock)
            }
            GameChoice.PAPER.value -> {
                ContextCompat.getDrawable(context, R.drawable.paper)
            }
            GameChoice.SCISSORS.value -> {
                ContextCompat.getDrawable(context, R.drawable.scissors)
            }
            else -> {
                null
            }
        }
        return result!!
    }
}