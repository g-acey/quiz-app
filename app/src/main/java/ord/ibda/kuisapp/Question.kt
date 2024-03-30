package ord.ibda.kuisapp

data class Question (
    val id: Int,
    val question: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val correctAnswer: Int
)