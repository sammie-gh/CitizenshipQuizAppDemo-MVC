package com.gh.sammie.citizenshipquizappdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gh.sammie.citizenshipquizappdemo.Model.Question;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button falseButton;
    private Button trueButton;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private TextView questionTextView;

    private Question[] questionBank = new Question[]{
            new Question(R.string.question_amendments, false), //correct: 27
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
            //Todo and add more!
    };

    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
//
//        questions.setAnswerResId(R.string.question_declaration);
//        questions.setAnswerTrue(true);

        //register buttons
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
    }

    private void initViews() {
        falseButton = findViewById(R.id.false_btn);
        trueButton = findViewById(R.id.true_btn);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);
        questionTextView = findViewById(R.id.ans_text_view);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.true_btn:
                checkAnswer(true);
                break;

            case R.id.false_btn:
                checkAnswer(false);
                break;

            case R.id.next_button:
                //goto next question
//                currentQuestionIndex++;
                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length; //check with remainder and update value
                updateQuestion();

        }
    }

    private void updateQuestion() {
        Log.d("Current", "onClick: " + currentQuestionIndex);
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }

    private void checkAnswer(boolean userChooseCorrect) {
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();
        int toastMessageId;

        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
        } else {
            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(MainActivity.this, toastMessageId,
                Toast.LENGTH_SHORT)
                .show();

    }
}
