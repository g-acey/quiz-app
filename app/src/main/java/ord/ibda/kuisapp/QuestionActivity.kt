package ord.ibda.kuisapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCheat: Int = 0
    private var mName: String? = null
    private lateinit var userAnswers: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        mName = intent.getStringExtra("NAME")
        mQuestionsList = Constants.getQuestions()

        mQuestionsList?.let {
            userAnswers = IntArray(it.size) { 0 }
        }

        setQuestion()

        var optionOne = findViewById<TextView>(R.id.optionOne)
        var optionTwo = findViewById<TextView>(R.id.optionTwo)
        var btnNext = findViewById<Button>(R.id.btnNext)
        var btnBack = findViewById<Button>(R.id.btnBack)
        var btnCheat = findViewById<Button>(R.id.btnCheat)

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        btnNext.setOnClickListener(this)
        btnBack.setOnClickListener(this)
        btnCheat.setOnClickListener(this)

    }

    private fun setQuestion() {

        var optionOne = findViewById<TextView>(R.id.optionOne)
        var optionTwo = findViewById<TextView>(R.id.optionTwo)
        var btnNext = findViewById<Button>(R.id.btnNext)
        var btnBack = findViewById<Button>(R.id.btnBack)
        var btnCheat = findViewById<Button>(R.id.btnCheat)

        val question = mQuestionsList!![mCurrentPosition - 1]

        defaultOptionsView()

        val layoutParams = btnNext.layoutParams as ViewGroup.LayoutParams
        if(mCurrentPosition == mQuestionsList!!.size) {
            btnNext.text = "Submit"
            val density = resources.displayMetrics.density
            layoutParams.width = (120 * density).toInt()
        } else {
            btnNext.text = ">"
            val density = resources.displayMetrics.density
            layoutParams.width = (60 * density).toInt()
        }
        btnNext.layoutParams = layoutParams

        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        var tvProgress = findViewById<TextView>(R.id.tvProgress)
        progressBar.progress = mCurrentPosition
        tvProgress.text = "$mCurrentPosition" + "/" + progressBar.max

        var tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        tvQuestion.text = question!!.question

        var ivImage = findViewById<ImageView>(R.id.ivImage)
        ivImage.setImageResource(question.image)

        optionOne.text = question.optionOne
        optionTwo.text = question.optionTwo

        if (userAnswers[mCurrentPosition - 1] == 1) {
            selectedOptionView(optionOne, 1)
        } else if (userAnswers[mCurrentPosition - 1] == 2) {
            selectedOptionView(optionTwo, 2)
        }

    }

    private fun defaultOptionsView() {

        var optionOne = findViewById<TextView>(R.id.optionOne)
        var optionTwo = findViewById<TextView>(R.id.optionTwo)

        val options = ArrayList<TextView>()
        options.add(0, optionOne)
        options.add(1, optionTwo)

        for (option in options) {
            option.setTextColor(Color.parseColor("#333333"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {

        var optionOne = findViewById<TextView>(R.id.optionOne)
        var optionTwo = findViewById<TextView>(R.id.optionTwo)
        var btnNext = findViewById<Button>(R.id.btnNext)
        var btnBack = findViewById<Button>(R.id.btnBack)
        var btnCheat = findViewById<Button>(R.id.btnCheat)

        when(v?.id) {
            R.id.optionOne -> {
                selectedOptionView(optionOne, 1)
                userAnswers[mCurrentPosition - 1] = 1
            }
            R.id.optionTwo -> {
                selectedOptionView(optionTwo, 2)
                userAnswers[mCurrentPosition - 1] = 2
            }
            R.id.btnNext -> {

                mCurrentPosition ++

                when {
                    mCurrentPosition <= mQuestionsList!!.size -> {
                        setQuestion()
                    } else -> {
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("NAME", mName)
                        intent.putExtra("SCORE", userAnswers)
                        startActivity(intent)
                    }
                }

                mSelectedOptionPosition = 0

            }
            R.id.btnBack -> {
                if(mCurrentPosition == 1) {
                    Toast.makeText(this, "You are at the first question", Toast.LENGTH_SHORT).show()
                } else {
                    mCurrentPosition -= 1
                    setQuestion()
                }
            }
            R.id.btnCheat -> {
                if(mCheat < 2) {
                    mCheat++
                    val intent = Intent(this, CheatActivity::class.java)
                    intent.putExtra("POSITION", mCurrentPosition)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "You have no cheats left", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun selectedOptionView(tv: TextView,
                                   selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#FF000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("CURRENT_POSITION", mCurrentPosition)
        outState.putIntArray("USER_ANSWERS", userAnswers)
        outState.putInt("CHEAT_COUNT", mCheat)
        outState.putString("NAME", mName)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mCurrentPosition = savedInstanceState.getInt("CURRENT_POSITION")
        userAnswers = savedInstanceState.getIntArray("USER_ANSWERS") ?: IntArray(mQuestionsList!!.size)
        mCheat = savedInstanceState.getInt("CHEAT_COUNT")
        mName = savedInstanceState.getString("NAME")
        setQuestion()
    }

}