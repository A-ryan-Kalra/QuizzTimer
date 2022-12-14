package com.prototype_1.quizztimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int locationOfCorrectAnswer;
    Button goButton;
    TextView sumTextView;
    TextView resultTextView;
    int score=0;
    int numberOfQuestions=0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timer;

    ArrayList<Integer> answers=new ArrayList<Integer>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer=(TextView)findViewById(R.id.timerTextView);

        goButton=(Button)findViewById(R.id.goButton);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
        resultTextView=(TextView)findViewById(R.id.resultTextView);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        newQuestions();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000+"s"));
            }

            @Override
            public void onFinish() {
                resultTextView.setText("DONE!");
            }
        }.start();

    }
    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
    }

    public void newQuestions()
    {
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);

        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);}
            else
            {
                int wrongAnswer=rand.nextInt(41);
                while(wrongAnswer== a+b)
                {
                    wrongAnswer=rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }
    public void chooseAnswer(View view) {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!!");
            score++;
        }
        else
        {
            resultTextView.setText("Wrong :/");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestions();
    }
}