package me.bartoszrobakowski.unquote;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewGame {
    List<Question> questions;

    int currentQuestionIndex;
    int totalCorrect = 0;
    int totalQuestions;

    boolean printedHint = false;

    ImageView questionImageView;
    TextView questionTextView;
    TextView questionsRemainingTextView;
    Button answer0Button;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button submitButton;

    Context context;

    public NewGame(ImageView questionImageView, TextView questionTextView, TextView questionsRemainingTextView, Button answer0Button, Button answer1Button, Button answer2Button, Button answer3Button, Button submitButton, Context context) {
        this.questionImageView = questionImageView;
        this.questionTextView = questionTextView;
        this.questionsRemainingTextView = questionsRemainingTextView;
        this.answer0Button = answer0Button;
        this.answer1Button = answer1Button;
        this.answer2Button = answer2Button;
        this.answer3Button = answer3Button;
        this.submitButton = submitButton;
        this.context = context;
        setOnClickListeners();
    }

    public void setOnClickListeners() {
        answer0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(0);
            }
        });
        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(1);
            }
        });
        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(2);
            }
        });
        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSelected(3);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAnswerSubmission();
            }
        });
    }

    public void onAnswerSelected(int answerSelected) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.setPlayerAnswer(answerSelected);
        displayQuestion(currentQuestion); // should delete ✔ before answer
        switch (answerSelected) {
            case 0:
                answer0Button.setText(String.format("✔ %s", currentQuestion.getAnswerZero()));
                break;
            case 1:
                answer1Button.setText(String.format("✔ %s", currentQuestion.getAnswerOne()));
                break;
            case 2:
                answer2Button.setText(String.format("✔ %s", currentQuestion.getAnswerTwo()));
                break;
            case 3:
                answer3Button.setText(String.format("✔ %s", currentQuestion.getAnswerThree()));
                break;
        }

    }

    public void onAnswerSubmission() {
        if (getCurrentQuestion().getPlayerAnswer() != -1) {
            if (getCurrentQuestion().isCorrect()) {
                totalCorrect++;
            }
            questions.remove(currentQuestionIndex);
            displayQuestionsRemaining(questions.size());
            if (questions.size() == 0) {
                printEndGameAlert();
            } else {
                chooseNewQuestion();
                displayQuestion(getCurrentQuestion());
            }
        } else {
            if (!printedHint) {
                printHintAlert();
                printedHint = true;
            }
        }
    }

    public void printEndGameAlert() {
        AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(context);
        gameOverDialogBuilder.setCancelable(false);
        gameOverDialogBuilder.setTitle("Game Over!");
        gameOverDialogBuilder.setMessage(getGameOverMessage(totalCorrect, totalQuestions));
        gameOverDialogBuilder.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startNewGame();
            }
        });
        gameOverDialogBuilder.create().show();
    }

    public void printHintAlert() {
        AlertDialog.Builder hintDialogBuilder = new AlertDialog.Builder(context);
        hintDialogBuilder.setCancelable(false);
        hintDialogBuilder.setTitle("Hint");
        hintDialogBuilder.setMessage("Choose one answer and then click \"Submit\"");
        hintDialogBuilder.setPositiveButton("Ok, I got it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        hintDialogBuilder.create().show();
    }

    public String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalCorrect + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }

    public void startNewGame() {
        questions = new ArrayList<>(Questions.QUESTIONS);
        randomizeQuestions();
        totalQuestions = questions.size();

        Question firstQuestion;
        firstQuestion = chooseNewQuestion();
        displayQuestion(firstQuestion);
        displayQuestionsRemaining(questions.size());
    }

    public void randomizeQuestions() {
        for (int i = 0; i < 7; i++) {
            chooseNewQuestion();
            questions.remove(currentQuestionIndex);
        }
    }

    public void displayQuestionsRemaining(int questionRemaining) {
        questionsRemainingTextView.setText(String.valueOf(questionRemaining));
    }

    public Question chooseNewQuestion() {
        currentQuestionIndex = generateRandomNumber(questions.size());
        return getCurrentQuestion();
    }

    public int generateRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    public void displayQuestion(Question question) {
        questionImageView.setImageResource(question.getImageId());
        questionTextView.setText(question.getQuestionText());
        answer0Button.setText(question.getAnswerZero());
        answer1Button.setText(question.getAnswerOne());
        answer2Button.setText(question.getAnswerTwo());
        answer3Button.setText(question.getAnswerThree());
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }
}