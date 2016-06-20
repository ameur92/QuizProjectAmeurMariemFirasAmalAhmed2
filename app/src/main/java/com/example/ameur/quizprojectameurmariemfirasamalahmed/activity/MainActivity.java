package com.example.ameur.quizprojectameurmariemfirasamalahmed.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.SQLException;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.Database.MySQLiteHelper;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Database.PropositionsHelper;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Database.QuestionsHelper;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.EventProvider.EventBus;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Events.LanguageSettings;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Events.Launch;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Events.LoadQuestions;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Events.PostStage;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Provider.PropositionsContentProvider;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Provider.QuestionsContentProvider;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.R;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.core.Question;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement.ConfigFragment;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement.ListFragment;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement.ListeQuestionFragment;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement.MainFragment;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement.QuestionFragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.plus.PlusShare;
<<<<<<< HEAD
=======
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

<<<<<<< HEAD
public class MainActivity extends AppCompatActivity implements MainFragment.MainMenuListener, ConfigFragment.ConfigListener, ListeQuestionFragment.QuestionListner, ListFragment.ListedQuestionLiner {
=======
public class MainActivity extends AppCompatActivity{
>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350
    private static String Correcte = "";

    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    public static MediaPlayer mPlayer;
<<<<<<< HEAD

=======
    private Bus mbus= EventBus.getInstance();
>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDB();
        setContentView(R.layout.activity_main);
        facebookSDKInitialize();
        shareDialog = new ShareDialog(this);
        launchMenu();
<<<<<<< HEAD

    }
=======
       createDB();
    }

    //lors du lancement de l'application je lance une musique
    @Override
    protected void onStart() {
        super.onStart();
        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.sound_track);
        try {
            mPlayer.setLooping(true);
            if (getSharedPreferences("quiz", 0).getBoolean("status", false) == true)
                mPlayer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        mbus.register(this);
    }

    @Override
    protected void onPause() {
        mbus.unregister(this);
        super.onPause();
    }
    //lors de Sortie  de l'application j'arret la  musique
>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350

    //lors du lancement de l'application je lance une musique
    @Override
    protected void onStart() {
        super.onStart();
        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.sound_track);
        try {
            mPlayer.setLooping(true);
            if (getSharedPreferences("quiz", 0).getBoolean("status", false) == true)
                mPlayer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

<<<<<<< HEAD
    }

    //lors de Sortie  de l'application j'arret la  musique
    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayer.isPlaying())
            mPlayer.release();
    }

    //cette fonction "launchMenu()" pour lancer le fragement Menu la premiere interface de notre jeu Quizz :)
    private void launchMenu() {
        FragmentTransaction fgm = getSupportFragmentManager().beginTransaction();
        fgm.replace(R.id.main_layout, MainFragment.newInstance(this));
        fgm.addToBackStack("MainFragment");
        fgm.commit();
=======
    //cette fonction "launchMenu()" pour lancer le fragement Menu la premiere interface de notre jeu Quizz :)
    private void launchMenu() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, MainFragment.newInstance(mbus)).commit();
>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350
    }

    //cette fonction "facebookSDKInitialize()" pour instaliser le fenetre de partage  sur facebook :)
    private void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    //cette fonction "sharefb()" pour le partage sur facebook :)
    public void sharefb() {

        String text = "Look at my awesome picture";
        //Uri imageUri = Uri.parse("http://previews.123rf.com/images/vectorikart/vectorikart1411/vectorikart141100002/33142849-Conceptual-flat-icon-of-brain-and-idea-in-the-form-of-light-bulb-with-soft-shadow-on-a-blue-backgrou-Stock-Vector.jpg");
        Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/drawable/health");

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
        for (final ResolveInfo app : activityList) {
            if ((app.activityInfo.name).contains("facebook")) {
                final ActivityInfo activity = app.activityInfo;
                final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                shareIntent.setComponent(name);
                startActivity(shareIntent);
                break;
            }
        }

    }

    //cette fonction "launchConfig()" pour lancer le dialog fragement de changement de langue :)
    public void launchConfig() {
        ConfigFragment configFragment = ConfigFragment.newInstance(mbus);
        configFragment.setCancelable(false);
        configFragment.show(getSupportFragmentManager(), "");
    }

    //cette fonction "shareG()" pour le partage sur GooglePlus :)
<<<<<<< HEAD
    @Override
=======
>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350
    public void shareG() {
        Uri selectedImage = Uri.parse("android.resource://" + getPackageName() + "/drawable/health");

        Intent shareIntent = new PlusShare.Builder(this)
                .setType("text/plain")
                .setText("QUIZZ SHARE")
                .setContentUrl(Uri.parse("https://developers.google.com/+/"))
                .setStream(selectedImage)
                .getIntent();

        startActivityForResult(shareIntent, 0);


    }

    public void launchListeStage() {
<<<<<<< HEAD
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, ListFragment.newInstance(this)).addToBackStack("ListFragment").commit();
    }

    //cette fonction "changeLanguageSettings(String lang)" lang=en|fr pour le changement du langue du fr au en et vis vers ca :)
    public void changeLanguageSettings(String lang) {
        Locale locale = new Locale(lang);
=======
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, ListFragment.newInstance(mbus)).addToBackStack("ListFragment").commit();

    }

    //cette fonction "changeLanguageSettings(String lang)" lang=en|fr pour le changement du langue du fr au en et vis vers ca :)
    @Subscribe public void changeLanguageSettings(LanguageSettings ls) {

        Locale locale = new Locale(ls.getLanguage());
>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getResources().updateConfiguration(config, this.getResources().getDisplayMetrics());
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
<<<<<<< HEAD

=======
>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350
    // populate Propositions Array from database depends of question id
    private ArrayList<String> generatePropositions(int idQuestion, int correctOne, String langue) {
        int idLangue;
        ArrayList<String> propositions = new ArrayList<>();
        String[] projection = {PropositionsHelper.COLUMN_ID, PropositionsHelper.COLUMN_QUESTION, PropositionsHelper.COLUMN_FRANCAIS, PropositionsHelper.COLUMN_ANGLAIS};
        Uri uri = PropositionsContentProvider.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, projection, PropositionsHelper.COLUMN_QUESTION + "=" + idQuestion, null, null);
        if (langue.equals("fr")) {
            idLangue = 2;
        } else {
            idLangue = 3;
        }
        while (cursor.moveToNext()) {
            propositions.add(cursor.getString(idLangue));
            if (cursor.getInt(0) == correctOne) {
                Correcte = cursor.getString(idLangue);
            }
        }
        return propositions;
    }

    // populate Question Array from database depends on level
    private ArrayList<Question> QuestionsLoader(int stage, String langue) {
        int idLangue;
        Question question;
        ArrayList<Question> qestions = new ArrayList<Question>();
        ArrayList<String> propositions;
        String[] projection = {QuestionsHelper.COLUMN_ID, QuestionsHelper.COLUMN_FRANCAIS, QuestionsHelper.COLUMN_ANGLAIS, QuestionsHelper.COLUMN_CORRECTE, QuestionsHelper.COLUMN_STAGE};
        Uri uri = QuestionsContentProvider.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, projection, QuestionsHelper.COLUMN_STAGE + "=" + stage, null, null);
        try {
            if (langue.equals("fr")) {
                idLangue = 1;
            } else {
                idLangue = 2;
            }
            while (cursor.moveToNext()) {
                question = new Question();
                question.setQuestion(cursor.getString(idLangue));
                question.setProposition(generatePropositions(cursor.getInt(0), cursor.getInt(3), langue));
                question.setCorrecte(Correcte);
                qestions.add(question);
            }
        } finally {
            cursor.close();
        }
        return qestions;
    }

<<<<<<< HEAD
    @Override

    public void update(Question question) {

        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, QuestionFragment.newInstance(question)).addToBackStack("QuestionFragment").commit();
=======
    @Subscribe
    public void updateQuestion(LoadQuestions lq) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, QuestionFragment.newInstance(lq.getQuestion())).commit();
>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350

    }

    @Subscribe
    public void updateStage(PostStage ps)
    {
        String langue = Locale.getDefault().getLanguage();
<<<<<<< HEAD
        ArrayList<Question> questions = QuestionsLoader(NumStage, langue);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, ListeQuestionFragment.newInstance(questions, this)).addToBackStack("ListeQuestionFragment").commit();
    }


=======
        ArrayList<Question> questions =QuestionsLoader(ps.getStage(),langue);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, ListeQuestionFragment.newInstance(questions, mbus)).commit();

    }

>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350
   @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }
    }

<<<<<<< HEAD
    public void createDB() {
=======
    public void createDB()
    {
>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350
        MySQLiteHelper myDbHelper = new MySQLiteHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
<<<<<<< HEAD
        } catch (SQLException sqle) {
=======

        }catch(java.sql.SQLException sqle){

>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350
            try {
                throw sqle;
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
<<<<<<< HEAD
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
=======

>>>>>>> c18336815321daceded7b205fd5a0e21efa4e350
        }
    }

    //Fragment launcher
    @Subscribe
    public void fragmentLauncher(Launch launch)
    {
        Log.v("press",launch.getBesoin());
        switch (launch.getBesoin())
        {
            case "fcbk":
                sharefb();
                break;
            case "config":
                launchConfig();
                break;
            case "google":
                shareG();
                break;
            case "listeStage":
                launchListeStage();
                break;
            default:
                break;
        }

    }




}