package com.example.kjogodavelha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val playerOne = arrayListOf<Int>()
    private val playerTwo = arrayListOf<Int>()
    private var currentPlayer = 1;
    private val winningData = WinningData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun handleOnClickPlayerMovement(position: Int, btnSelected: Button) {
        if (currentPlayer == 1) {
            btnSelected.text = "X"
            btnSelected.setBackgroundResource(R.color.colorPlayer1)
            playerOne.add(position)
            checkResults()
            currentPlayer = 2
        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundResource(R.color.colorPlayer1)
            playerTwo.add(position)
            checkResults()
            currentPlayer = 1
        }
        btnSelected.isClickable = false
    }

    fun renderMovePlayer(view: View) = when (view.id) {
        R.id.btn1_row1 -> handleOnClickPlayerMovement(1, view as Button)
        R.id.btn2_row1 -> handleOnClickPlayerMovement(2, view as Button)
        R.id.btn3_row1 -> handleOnClickPlayerMovement(3, view as Button)
        R.id.btn1_row2 -> handleOnClickPlayerMovement(4, view as Button)
        R.id.btn2_row2 -> handleOnClickPlayerMovement(5, view as Button)
        R.id.btn3_row2 -> handleOnClickPlayerMovement(6, view as Button)
        R.id.btn1_row3 -> handleOnClickPlayerMovement(7, view as Button)
        R.id.btn2_row3 -> handleOnClickPlayerMovement(8, view as Button)
        R.id.btn3_row3 -> handleOnClickPlayerMovement(9, view as Button)
        else -> handleOnClickPlayerMovement(0, view as Button)
    }

    private fun checkResults() {
            when {
                winningData.playerIsTheWinner(playerOne) -> winnerMessage()
                winningData.playerIsTheWinner(playerTwo) -> winnerMessage()
                playerOne.size == 5 -> drawMessage()
            }
    }

    private fun winnerMessage() {
        Toast.makeText(this, "Parabéns! Jogador $currentPlayer venceu.", Toast.LENGTH_LONG).show()
        stopGame()
    }

    private fun drawMessage() {
        Toast.makeText(this, "Vish, vocês são muito ruins, deu empate!", Toast.LENGTH_LONG).show()
        stopGame()
    }

    fun restartGame(view: View) {
        playerOne.clear()
        playerTwo.clear()
        currentPlayer = 1;
        // reset views
        setContentView(R.layout.activity_main)
    }

    private fun stopGame() {
        findViewById<Button>(R.id.btn1_row1).isClickable = false;
        findViewById<Button>(R.id.btn2_row1).isClickable = false;
        findViewById<Button>(R.id.btn3_row1).isClickable = false;
        findViewById<Button>(R.id.btn1_row2).isClickable = false;
        findViewById<Button>(R.id.btn2_row2).isClickable = false;
        findViewById<Button>(R.id.btn3_row2).isClickable = false;
        findViewById<Button>(R.id.btn1_row3).isClickable = false;
        findViewById<Button>(R.id.btn2_row3).isClickable = false;
        findViewById<Button>(R.id.btn3_row3).isClickable = false;
    }

}


class WinningData() {

    private val winningRow = arrayListOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))
    private val winningColumn = arrayListOf(listOf(1, 4, 7), listOf(2, 5, 8), listOf(3, 6, 9))
    private val winningDiagonal = arrayListOf(listOf(1, 5, 9), listOf(3, 5, 7))

    fun playerIsTheWinner(playerMovement: ArrayList<Int>): Boolean {
        return (wonByTheColumn(playerMovement) || wonByTheRow(playerMovement) || wonByTheDiagonal(
            playerMovement
        ))
    }

    private fun wonByTheRow(playerMovement: ArrayList<Int>): Boolean {
        return (playerMovement.containsAll(winningRow[0]) || playerMovement.containsAll(winningRow[1]) || playerMovement.containsAll(
            winningRow[2]
        ))
    }

    private fun wonByTheColumn(playerMovement: ArrayList<Int>): Boolean {
        return (playerMovement.containsAll(winningColumn[0]) || playerMovement.containsAll(
            winningColumn[1]
        ) || playerMovement.containsAll(winningColumn[2]))
    }

    private fun wonByTheDiagonal(playerMovement: ArrayList<Int>): Boolean {
        return (playerMovement.containsAll(winningDiagonal[0]) || playerMovement.containsAll(
            winningDiagonal[1]
        ))
    }
}