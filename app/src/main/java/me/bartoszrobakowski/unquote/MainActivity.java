package me.bartoszrobakowski.unquote;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set xml layout to display
        setContentView(R.layout.activity_main);

        // Save resource ID's to variables
        ImageView questionImageView = findViewById(R.id.iv_main_quote_image);
        TextView questionTextView = findViewById(R.id.tv_main_question_title);
        TextView questionsRemainingTextView = findViewById(R.id.tv_main_questions_remaining_count);
        Button answer0Button = findViewById(R.id.btn_main_answer_0);
        Button answer1Button = findViewById(R.id.btn_main_answer_1);
        Button answer2Button = findViewById(R.id.btn_main_answer_2);
        Button answer3Button = findViewById(R.id.btn_main_answer_3);
        Button submitButton = findViewById(R.id.btn_main_submit_answer);

        // Show app icon in ActionBar
        generateActionBarIcon();

        // construct instance of NewGame class
        NewGame newGame = new NewGame(
                questionImageView,
                questionTextView,
                questionsRemainingTextView,
                answer0Button,
                answer1Button,
                answer2Button,
                answer3Button,
                submitButton,
                MainActivity.this);

        // start new game
        newGame.startNewGame();
    }

    public void generateActionBarIcon() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(" \"Unquote\" — who said it?");
        getSupportActionBar().setElevation(0);
    }
}