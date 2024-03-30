package ord.ibda.kuisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private var mQuestionsList: ArrayList<Question>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val userName = intent.getStringExtra("NAME").toString()
        val userAnswers = intent.getIntArrayExtra("SCORE") ?: return

        val mQuestionsList = Constants.getQuestions()
        var correctAnswers = 0
        for (i in userAnswers.indices) {
            if (userAnswers[i] == mQuestionsList[i].correctAnswer) {
                correctAnswers++
            }

            Log.i("Score", "Question ${i + 1}: Correct answer = ${mQuestionsList[i].correctAnswer}, User's answer = ${userAnswers[i]}")

        }

        Log.i("Score", "Total correct answers: $correctAnswers")

        var congratulationsTv = findViewById<TextView>(R.id.congratulationsTv)
        var scoreTv = findViewById<TextView>(R.id.scoreTv)
        val btnRestart = findViewById<Button>(R.id.btnRestart)

        congratulationsTv.text = "Congratulations, $userName!"
        scoreTv.text = "Your score is $correctAnswers of 8"
        btnRestart.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}