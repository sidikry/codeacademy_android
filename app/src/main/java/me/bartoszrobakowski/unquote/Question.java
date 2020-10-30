package me.bartoszrobakowski.unquote;

public class Question {
    private int imageId;
    private String questionText;
    private String answerZero;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private int correctAnswer;
    private int playerAnswer;

    public Question(int imageId, String questionText, String answerZero, String answerOne, String answerTwo, String answerThree, int correctAnswer) {
        this.imageId = imageId;
        this.questionText = questionText;
        this.answerZero = answerZero;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.correctAnswer = correctAnswer;
        this.playerAnswer = -1;
    }

    public int getImageId() {
        return imageId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswerZero() {
        return answerZero;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public int getPlayerAnswer() {
        return playerAnswer;
    }

    public void setPlayerAnswer(int playerAnswer) {
        this.playerAnswer = playerAnswer;
    }

    public boolean isCorrect() {
        return correctAnswer == playerAnswer;
    }
}
