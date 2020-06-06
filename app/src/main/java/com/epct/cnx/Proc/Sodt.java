package com.epct.cnx.Proc;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epct.cnx.R;

public class Sodt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proc_sodt);

        Button sodt_solve = (Button) findViewById(R.id.sodt_solve);
        sodt_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView sodt_A_input = (TextView) findViewById(R.id.sodt_A_input);
                TextView sodt_B_input = (TextView) findViewById(R.id.sodt_B_input);
                TextView sodt_C_input = (TextView) findViewById(R.id.sodt_C_input);
                TextView sodt_response_output = (TextView) findViewById(R.id.sodt_response_output);
                TextView sodt_zeta_output = (TextView) findViewById(R.id.sodt_zeta_output);
                TextView sodt_tau_output = (TextView) findViewById(R.id.sodt_tau_output);
                TextView sodt_tp_output = (TextView) findViewById(R.id.sodt_tp_output);
                TextView sodt_OS_output = (TextView) findViewById(R.id.sodt_OS_output);
                TextView sodt_DR_output = (TextView) findViewById(R.id.sodt_DR_output);
                TextView sodt_p_output = (TextView) findViewById(R.id.sodt_p_output);
                TextView sodt_f_output = (TextView) findViewById(R.id.sodt_f_output);
                TextView sodt_omega_output = (TextView) findViewById(R.id.sodt_omega_output);
                TextView sodt_ts_output = (TextView) findViewById(R.id.sodt_ts_output);
                TextView sodt_tr_output = (TextView) findViewById(R.id.sodt_tr_output);

                sodt_response_output.setText("");
                sodt_zeta_output.setText("");
                sodt_tau_output.setText("");
                sodt_tp_output.setText("");
                sodt_OS_output.setText("");
                sodt_DR_output.setText("");
                sodt_p_output.setText("");
                sodt_f_output.setText("");
                sodt_omega_output.setText("");
                sodt_tr_output.setText("");

                Double A=0.1, B=0.1, C=0.1, zeta, tau, tp, OS, DR, p, f, omega, ts, tr;
                String response="";

                int unfilled = 0;
                if (sodt_A_input.getText().length() > 0) {
                    A = Double.parseDouble(sodt_A_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (sodt_B_input.getText().length() > 0) {
                    B = Double.parseDouble(sodt_B_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (sodt_C_input.getText().length() > 0) {
                    C = Double.parseDouble(sodt_C_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }

                if (unfilled>0) {
                    Toast.makeText(Sodt.this, "Underdetermined system! Please fill in all fields (A, B & C).", Toast.LENGTH_SHORT).show();
                }
                else {
                    tau = Math.sqrt(A/C);
                    zeta = (B/C)/2.0/Math.sqrt(A/C);
                    if (Math.abs(zeta-1.0)<1e-8) {
                        response = "critically damped";
                        sodt_response_output.setText(response);
                        sodt_zeta_output.setText(zeta.toString());
                        sodt_tau_output.setText(tau.toString());
                    }
                    else if (zeta>1.0) {
                        response = "overdamped";
                        sodt_response_output.setText(response);
                        sodt_zeta_output.setText(zeta.toString());
                        sodt_tau_output.setText(tau.toString());
                    }
                    else {
                        response = "underdamped";
                        tp = Math.PI*tau/Math.sqrt(1.0-Math.pow(zeta,2.0));
                        OS = Math.exp(-Math.PI*zeta/Math.sqrt(1.0-Math.pow(zeta,2.0)));
                        DR = Math.pow(OS,2.0);
                        p = 2.0*Math.PI/Math.sqrt(1.0-Math.pow(zeta,2.0));
                        f = 1.0/p;
                        omega = 2.0*Math.PI*f;
                        ts = -Math.log(0.05*Math.sqrt(1-zeta*zeta))*tau/zeta;

                        // Finding tr
                        Double tr_left=0.0, tr_right=tp;
                        tr = -1.0;
                        Double y = 0.5;
                        int iter=0;
                        while (Math.abs(y-1.0)>1e-8 && iter<1000) {
                            tr=(tr_left+tr_right)/2.0;
                            y=1.0-Math.exp(-zeta*tr/tau)*(
                                    Math.cos(tr/tau*Math.sqrt(1.0-zeta*zeta))
                                            +zeta/Math.sqrt(1.0-zeta*zeta)
                                            *Math.sin(tr/tau*Math.sqrt(1.0-zeta*zeta)));
                            if (y<1.0) {
                                tr_left = tr;
                            } else {
                                tr_right = tr;
                            }
                            iter = iter + 1;
                        }
                        Toast.makeText(Sodt.this, "Iterative calculation for rise time t\u1d63 stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();

                        sodt_response_output.setText(response);
                        sodt_zeta_output.setText(zeta.toString());
                        sodt_tau_output.setText(tau.toString());
                        sodt_tp_output.setText(tp.toString());
                        sodt_OS_output.setText(OS.toString());
                        sodt_DR_output.setText(DR.toString());
                        sodt_p_output.setText(p.toString());
                        sodt_f_output.setText(f.toString());
                        sodt_omega_output.setText(omega.toString());
                        sodt_tr_output.setText(tr.toString());
                        sodt_ts_output.setText(ts.toString());
                    }
                }
            }
        });

        TextView sodt_A = (TextView) findViewById(R.id.sodt_A);
        sodt_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sodt.this, "Denominator quadratic constant A (refer to above equation)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView sodt_B = (TextView) findViewById(R.id.sodt_B);
        sodt_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sodt.this, "Denominator quadratic constant B (refer to above equation)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView sodt_C = (TextView) findViewById(R.id.sodt_C);
        sodt_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sodt.this, "Denominator quadratic constant C (refer to above equation)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView sodt_response = (TextView) findViewById(R.id.sodt_response);
        sodt_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sodt.this, "Response type (either overdamped, critically damped or underdamped)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView sodt_zeta = (TextView) findViewById(R.id.sodt_zeta);
        sodt_zeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sodt.this, "Damping coefficient \u03b6", Toast.LENGTH_SHORT).show();
            }
        });

        TextView sodt_tau = (TextView) findViewById(R.id.sodt_tau);
        sodt_tau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sodt.this, "Time constant \u03c4", Toast.LENGTH_SHORT).show();
            }
        });
    }
}