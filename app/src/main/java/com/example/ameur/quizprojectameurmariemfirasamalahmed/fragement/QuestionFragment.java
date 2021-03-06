package com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.Events.ScoreUpdate;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.R;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.core.Question;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.Collections;


public class QuestionFragment extends Fragment implements View.OnClickListener {
    private static Question question;
    Snackbar snackbar;
    int i = 0;
    String reponseCorrect;
    private RadioGroup radioGroup;
    private CoordinatorLayout coordinatorLayout;
    private static Bus eventBus;

    private Button  mButtonq1, mButtonq2, mButtonq3, mButtonq4;
    private ArrayList<String> propositions;
    private TextView mQuestion;
    private static int code;
    public QuestionFragment() {

    }

    public static QuestionFragment newInstance(Question q,Bus Bus,int codeQuestion) {
        QuestionFragment questionFragment = new QuestionFragment();
        question = q;
        eventBus = Bus;
        code=codeQuestion;
        return questionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        mQuestion = (TextView) view.findViewById(R.id.mQuestion);
        mQuestion.setText(question.getQuestion());
        propositions = generatePropositions(question.getProposition());

        mButtonq1 = (Button) view.findViewById(R.id.button1);
        mButtonq1.setText(propositions.get(0));
        mButtonq1.setOnClickListener(this);

        mButtonq2 = (Button) view.findViewById(R.id.button2);
        mButtonq2.setText(propositions.get(1));
        mButtonq2.setOnClickListener(this);

        mButtonq3 = (Button) view.findViewById(R.id.button3);
        mButtonq3.setText(propositions.get(2));
        mButtonq3.setOnClickListener(this);

        mButtonq4 = (Button) view.findViewById(R.id.button4);
        mButtonq4.setText(propositions.get(3));
        mButtonq4.setOnClickListener(this);


        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id
                .coordinatorLayout);

        return view;
    }

    public ArrayList<String> generatePropositions(ArrayList<String> propositions) {
        ArrayList<String> propos = new ArrayList<>();
        propos.add(question.getCorrecte());
        for (String p: propositions) {
            if ((!p.equals(question.getCorrecte())) && (propos.size() < 4)) {
                propos.add(p);
            }
        }
        Collections.shuffle(propos);
        return propos;
    }

    @Override
    public void onClick(View v) {
        String reponse = "";
        reponseCorrect = question.getCorrecte();
        int score = 0;
        switch (v.getId()) {
            case R.id.button1:
                reponse = mButtonq1.getText().toString();
                if (reponseCorrect.equals(reponse)) {
                    score += 5;
                }

                break;
            case R.id.button2:

                reponse = mButtonq2.getText().toString();
                if (reponseCorrect.equals(reponse)) {
                    score += 5;
                }
                break;
            case R.id.button3:

                reponse = mButtonq3.getText().toString();
                if (reponseCorrect.equals(reponse)) {
                    score += 5;
                }

                break;
            case R.id.button4:

                reponse = mButtonq4.getText().toString();
                if (reponseCorrect.equals(reponse)) {
                    score += 5;
                }
            default:
                break;

        }
        eventBus.post(new ScoreUpdate(score,code));
    }
}