package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private Button btn_ko, btn_papir, btn_ollo;
    private ImageView img_felso, img_also;
    private TextView txt_ember, txt_gep;
    private int ember, gep, win, lose;
    private Random random;
    private Toast customToast;
    private AlertDialog.Builder alertBuilder;
    private TextView textViewCustom;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ujJatek();

        btn_ko.setOnClickListener(view -> {
            int koPapirOllo;
            koPapirOllo = random.nextInt(3);
            img_felso.setImageResource(R.drawable.rock);
            //ha 0es gép = kő ha 1es gép = papír ha 2as gép = olló
            if(koPapirOllo == 0){
                img_also.setImageResource(R.drawable.rock);
            }
            if(koPapirOllo == 1){
                gep++;
                lose++;
                img_also.setImageResource(R.drawable.paper);
                txt_gep.setText("Computer " + gep);
            }
            if(koPapirOllo == 2){
                ember++;
                win++;
                img_also.setImageResource(R.drawable.scissors);
                txt_ember.setText("Ember " + ember);
            }

            if(ember == 3 || gep == 3){
                if(win > lose){
                    alertBuilder.setTitle("Nyertél!");
                    alertBuilder.create();
                    alertBuilder.show();
                }else{
                    alertBuilder.setTitle("Vesztettél.").create().show();
                }
            }

        });

        btn_papir.setOnClickListener(view -> {
            int koPapirOllo;
            koPapirOllo = random.nextInt(3);
            img_felso.setImageResource(R.drawable.paper);
            //ha 0es gép = kő ha 1es gép = papír ha 2as gép = olló
            if(koPapirOllo == 0){
                ember++;
                win++;
                img_also.setImageResource(R.drawable.rock);
                txt_ember.setText("Ember " + ember);
            }
            if(koPapirOllo == 1){
                img_also.setImageResource(R.drawable.paper);
            }
            if(koPapirOllo == 2){
                gep++;
                lose++;
                img_also.setImageResource(R.drawable.scissors);
                txt_gep.setText("Computer " + gep);
            }

            if(ember == 3 || gep == 3){
                if(win > lose){
                    alertBuilder.setTitle("Nyertél!");
                    alertBuilder.create();
                    alertBuilder.show();
                }else{
                    alertBuilder.setTitle("Vesztettél.").create().show();
                }
            }

        });

        btn_ollo.setOnClickListener(view -> {
            int koPapirOllo;
            koPapirOllo = random.nextInt(3);
            img_felso.setImageResource(R.drawable.scissors);
            //ha 0es gép = kő ha 1es gép = papír ha 2as gép = olló
            if(koPapirOllo == 0){
                gep++;
                lose++;
                img_also.setImageResource(R.drawable.rock);
                txt_gep.setText("Computer " + gep);

            }
            if(koPapirOllo == 1){
                ember++;
                win++;
                img_also.setImageResource(R.drawable.paper);
                txt_ember.setText("Ember " + ember);

            }
            if(koPapirOllo == 2){
                img_also.setImageResource(R.drawable.scissors);
            }

            if(ember == 3 || gep == 3){
                if(win > lose){
                    alertBuilder.setTitle("Nyertél!");
                    alertBuilder.create();
                    alertBuilder.show();
                }else{
                    alertBuilder.setTitle("Vesztettél.").create().show();
                }
            }

        });
    }

    private void ujJatek() {
        gep = 0;
        ember = 0;
        txt_ember.setText("Ember: "+ Integer.toString(ember));
        txt_gep.setText("Computer: "+ Integer.toString(gep));
    }


    private void init(){
        btn_ko = findViewById(R.id.ko);
        btn_papir = findViewById(R.id.papir);
        btn_ollo = findViewById(R.id.ollo);
        img_felso = findViewById(R.id.felso);
        img_also = findViewById(R.id.also);
        txt_ember = findViewById(R.id.ember);
        txt_gep = findViewById(R.id.computer);
        ember = 0;
        gep = 0;
        win = 0;
        lose = 0;
        random = new Random();
        alertBuilder = new AlertDialog.Builder(this);
        CreateAlertDialog();

    }

    private void CreateAlertDialog(){
        alertBuilder.setMessage("Szeretnél e új játékot?");
        alertBuilder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {finish();}
        });
        alertBuilder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ujJatek();
                closeContextMenu();
            }
        });
        alertBuilder.setCancelable(false);
    }


    private void initActivityScreenOrientPortrait()
    {
        // Avoid screen rotations (use the manifests android:screenOrientation setting)
        // Set this to nosensor or potrait

        // Set window fullscreen
        this.activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics metrics = new DisplayMetrics();
        this.activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // Test if it is VISUAL in portrait mode by simply checking it's size
        boolean bIsVisualPortrait = ( metrics.heightPixels >= metrics.widthPixels );

        if( !bIsVisualPortrait )
        {
            // Swap the orientation to match the VISUAL portrait mode
            if( this.activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT )
            { this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); }
            else { this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ); }
        }
        else { this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR); }

    }








}