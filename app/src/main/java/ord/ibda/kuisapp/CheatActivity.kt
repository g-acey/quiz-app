package ord.ibda.kuisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlin.math.log

class CheatActivity : AppCompatActivity() {

    private var mQuestionsList: ArrayList<Question>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val btnShowAnswer = findViewById<Button>(R.id.btnShowAnswer)
        val mCurrentPosition: Int = intent.getIntExtra("POSITION", 0)

        mQuestionsList = Constants.getQuestions()
        val question = mQuestionsList!![mCurrentPosition - 1]
        val answer = question!!.correctAnswer

        btnShowAnswer.setOnClickListener {
            if(answer == 1) {
                Toast.makeText(this, "Correct Answer: True", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Correct Answer: False", Toast.LENGTH_SHORT).show()
            }

        }

    }
}