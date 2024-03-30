package ord.ibda.kuisapp

object Constants {

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val que1 = Question(1,
            "Is this a Hammerhead Shark?",
            R.drawable.hammerhead,
            "True",
            "False",
            1
        )

        questionsList.add(que1)

        val que2 = Question(2,
            "Is this a Great White Shark?",
            R.drawable.blue,
            "True",
            "False",
            2
        )

        questionsList.add(que2)

        val que3 = Question(3,
            "Is this a Thresher Shark?",
            R.drawable.thresher,
            "True",
            "False",
            1
        )

        questionsList.add(que3)

        val que4 = Question(4,
            "Is this a Whale Shark?",
            R.drawable.lemon,
            "True",
            "False",
            2
        )

        questionsList.add(que4)

        val que5 = Question(5,
            "Is this a Lemon Shark?",
            R.drawable.whale,
            "True",
            "False",
            2
        )

        questionsList.add(que5)

        val que6 = Question(6,
            "Is this a Nurse Shark?",
            R.drawable.nurse,
            "True",
            "False",
            1
        )

        questionsList.add(que6)

        val que7 = Question(7,
            "Is this a Leopard Shark?",
            R.drawable.leopard,
            "True",
            "False",
            1
        )

        questionsList.add(que7)

        val que8 = Question(8,
            "Is this a Tiger Shark?",
            R.drawable.tiger,
            "True",
            "False",
            1
        )

        questionsList.add(que8)

        return questionsList
    }

}