package com.epct.cnx.HeatMass;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.epct.cnx.R;

public class HeatExc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heatmass_heatexc);

        TextView heatexc_ch = (TextView) findViewById(R.id.heatexc_ch);
        heatexc_ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatExc.this, "Hot stream specific heat capacity c\u2095 (in J/(kg.K))", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatexc_mh = (TextView) findViewById(R.id.heatexc_mh);
        heatexc_mh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatExc.this, "Hot stream mass flow rate \u1e41\u2095 (in kg/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatexc_cc = (TextView) findViewById(R.id.heatexc_cc);
        heatexc_cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatExc.this, "Cold stream specific heat capacity c_c (in J/(kg.K))", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatexc_mc = (TextView) findViewById(R.id.heatexc_mc);
        heatexc_mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatExc.this, "Cold stream mass flow rate \u1e41_c (in kg/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatexc_Th1 = (TextView) findViewById(R.id.heatexc_Th1);
        heatexc_Th1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatExc.this, "Hot stream inlet temperature T\u2095\u2081 (in K or \u2103)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatexc_Th2 = (TextView) findViewById(R.id.heatexc_Th2);
        heatexc_Th2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatExc.this, "Hot stream outlet temperature T\u2095\u2082 (in K or \u2103)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatexc_Tc1 = (TextView) findViewById(R.id.heatexc_Tc1);
        heatexc_Tc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatExc.this, "Cold stream inlet temperature T_c\u2081 (in K or \u2103)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatexc_Tc2 = (TextView) findViewById(R.id.heatexc_Tc2);
        heatexc_Tc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatExc.this, "Cold stream outlet temperature T_c\u2082 (in K or \u2103)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatexc_F = (TextView) findViewById(R.id.heatexc_F);
        heatexc_F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatExc.this, "Correction factor for crossflow & shell-and-tube heat exchangers\n(Default value is 1)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatexc_A = (TextView) findViewById(R.id.heatexc_A);
        heatexc_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatExc.this, "Area of thermal contact within heat exchanger (in m\u00b2)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatexc_q = (TextView) findViewById(R.id.heatexc_q);
        heatexc_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatExc.this, "Total heat transfer rate (in W)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}