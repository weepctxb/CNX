package com.epct.cnx.Fluid;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epct.cnx.R;

public class Atm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fluid_atm);

        Button antoine_solve = (Button) findViewById(R.id.atm_solve);
        antoine_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView atm_z_input = (TextView) findViewById(R.id.atm_z_input);
                TextView atm_p_output = (TextView) findViewById(R.id.atm_p_output);
                TextView atm_t_output = (TextView) findViewById(R.id.atm_t_output);
                TextView atm_rho_output = (TextView) findViewById(R.id.atm_rho_output);
                TextView atm_layer_output = (TextView) findViewById(R.id.atm_layer_output);
                TextView atm_basez_output = (TextView) findViewById(R.id.atm_basez_output);
                TextView atm_basep_output = (TextView) findViewById(R.id.atm_basep_output);
                TextView atm_baset_output = (TextView) findViewById(R.id.atm_baset_output);
                TextView atm_baserho_output = (TextView) findViewById(R.id.atm_baserho_output);
                TextView atm_lapse_output = (TextView) findViewById(R.id.atm_lapse_output);

                if (atm_z_input.getText().length()>0){
                    Double z = Double.parseDouble(atm_z_input.getText().toString());

                    Double g = 9.80665, R = 8.3144598, M = 0.0289644, eps = 0.0000001;
                    Double p = 999999.0, t = 999.0, rho = 999.0, basez = 0.0, basep = 0.0, baset = 0.0, baserho = 0.0, lapse = 999.0;
                    String layer = "";

                    if (z <= 11000.0) {
                        basez = 0.0;
                        basep = 101325.0;
                        baset = 288.15;
                        baserho = 1.2250;
                        lapse = -0.0065;
                    } else if (z <= 20000.0) {
                        basez = 11000.0;
                        basep = 22632.10;
                        baset = 216.65;
                        baserho = 0.36391;
                        lapse = 0.0;
                    } else if (z <= 32000.0) {
                        basez = 20000.0;
                        basep = 5474.89;
                        baset = 216.65;
                        baserho = 0.08803;
                        lapse = 0.001;
                    } else if (z <= 47000.0) {
                        basez = 32000.0;
                        basep = 868.02;
                        baset = 228.65;
                        baserho = 0.01322;
                        lapse = 0.0028;
                    } else if (z <= 51000.0) {
                        basez = 47000.0;
                        basep = 110.91;
                        baset = 270.65;
                        baserho = 0.00143;
                        lapse = 0.0;
                    } else if (z <= 71000.0) {
                        basez = 51000.0;
                        basep = 66.94;
                        baset = 270.65;
                        baserho = 0.00086;
                        lapse = -0.0028;
                    } else if (z <= 84852.0) {
                        basez = 71000.0;
                        basep = 3.96;
                        baset = 214.65;
                        baserho = 0.000064;
                        lapse = -0.002;
                    } else {
                        Toast.makeText(Atm.this, "z must be between 0 m and 84852 m!", Toast.LENGTH_SHORT).show();
                    }

                    if (z <= 12000.0) {
                        layer = "troposphere";
                    } else if (z <= 50000.0) {
                        layer = "stratosphere";
                    } else if (z <= 80000.0) {
                        layer = "mesosphere";
                    } else if (z <= 700000.0) {
                        layer = "thermosphere";
                    }

                    if (Math.abs(lapse) <= 0.0001) {
                        p = basep * Math.exp(-g * M * (z - basez) / (R * baset));
                        rho = baserho * Math.exp(-g * M * (z - basez) / (R * baset));
                        t = baset + lapse * (z - basez);
                    } else if (Math.abs(lapse) <= 1.0) {
                        p = basep * Math.pow(baset / (baset + lapse * (z - basez)), (g * M / (R * lapse)));
                        rho = baserho * Math.pow(baset / (baset + lapse * (z - basez)), 1 + g * M / (R * lapse));
                        t = baset + lapse * (z - basez);
                    }

                    if (Math.abs(lapse) <= 1.0) {
                        atm_p_output.setText(p.toString());
                        atm_t_output.setText(t.toString());
                        atm_rho_output.setText(rho.toString());
                        atm_layer_output.setText(layer.toString());
                        atm_basez_output.setText(basez.toString());
                        atm_basep_output.setText(basep.toString());
                        atm_baset_output.setText(baset.toString());
                        atm_baserho_output.setText(baserho.toString());
                        atm_lapse_output.setText(lapse.toString());
                    } else {
                        atm_p_output.setText("");
                        atm_t_output.setText("");
                        atm_rho_output.setText("");
                        atm_layer_output.setText("");
                        atm_basez_output.setText("");
                        atm_basep_output.setText("");
                        atm_baset_output.setText("");
                        atm_baserho_output.setText("");
                        atm_lapse_output.setText("");
                    }
                }
                else {
                    Toast.makeText(Atm.this, "Please key in altitude!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView atm_z = (TextView) findViewById(R.id.atm_z);
        atm_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Atm.this, "Altitude z (in m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView atm_p = (TextView) findViewById(R.id.atm_p);
        atm_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Atm.this, "Pressure P (in Pa)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView atm_t = (TextView) findViewById(R.id.atm_t);
        atm_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Atm.this, "Temperature T (in K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView atm_rho = (TextView) findViewById(R.id.atm_rho);
        atm_rho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Atm.this, "Density \u03C1 (in kg/m\u00B3)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView atm_layer = (TextView) findViewById(R.id.atm_layer);
        atm_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Atm.this, "Atmosphere layer", Toast.LENGTH_SHORT).show();
            }
        });
        TextView atm_basez = (TextView) findViewById(R.id.atm_basez);
        atm_basez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Atm.this, "Base Altitude z (in m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView basep = (TextView) findViewById(R.id.atm_basep);
        basep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Atm.this, "Base Pressure P (in Pa)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView atm_baset = (TextView) findViewById(R.id.atm_baset);
        atm_baset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Atm.this, "Base Temperature T (in K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView atm_baserho = (TextView) findViewById(R.id.atm_baserho);
        atm_baserho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Atm.this, "Base Density \u03C1 (in kg/m\u00B3)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView atm_lapse = (TextView) findViewById(R.id.atm_lapse);
        atm_lapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Atm.this, "Lapse rate (in K/m)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}