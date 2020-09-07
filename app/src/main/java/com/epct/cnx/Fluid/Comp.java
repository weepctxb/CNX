package com.epct.cnx.Fluid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;

import com.epct.cnx.FluSol.PackedBed;
import com.epct.cnx.R;

import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AppCompatActivity;

public class Comp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fluid_comp);

        Button comp_size = (Button) findViewById(R.id.comp_size);
        comp_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView comp_G_input = (TextView) findViewById(R.id.comp_G_input);
                TextView comp_T1_input = (TextView) findViewById(R.id.comp_T1_input);
                TextView comp_P1_input = (TextView) findViewById(R.id.comp_P1_input);
                TextView comp_P2_input = (TextView) findViewById(R.id.comp_P2_input);
                TextView comp_cp_input = (TextView) findViewById(R.id.comp_cp_input);
                TextView comp_cv_input = (TextView) findViewById(R.id.comp_cv_input);
                TextView comp_Z_input = (TextView) findViewById(R.id.comp_Z_input);
                TextView comp_eff_input = (TextView) findViewById(R.id.comp_eff_input);

                TextView comp_power_output = (TextView) findViewById(R.id.comp_power_output);
                TextView comp_T2_output = (TextView) findViewById(R.id.comp_T2_output);

                Double G=-1.0, T1=-1.0, T2=-1.0, P1=-1.0, P2=-1.0,
                        cp=-1.0, cv=-1.0, Z=-1.0, eff=-1.0;

                if (comp_G_input.getText().length() > 0
                        & comp_T1_input.getText().length() > 0
                        & comp_P1_input.getText().length() > 0
                        & comp_P2_input.getText().length() > 0
                        & comp_cp_input.getText().length() > 0
                        & comp_cv_input.getText().length() > 0) {
                    G = Double.parseDouble(comp_G_input.getText().toString());
                    T1 = Double.parseDouble(comp_T1_input.getText().toString());
                    P1 = Double.parseDouble(comp_P1_input.getText().toString());
                    P2 = Double.parseDouble(comp_P2_input.getText().toString());
                    cp = Double.parseDouble(comp_cp_input.getText().toString());
                    cv = Double.parseDouble(comp_cv_input.getText().toString());
                } else {
                    Toast.makeText(Comp.this, "Please fill in all inputs " +
                            "(except Z and \u03b5)!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (P2/P1 > 10.) {
                    Toast.makeText(Comp.this, "Compression ratio > 10 is too large!",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if (P2/P1 > 4.) {
                    Toast.makeText(Comp.this, "Compression ratio > 4 is too large - " +
                            "check that outlet temperature is not too high! " +
                            "Nevertheless continuing calculation...",
                            Toast.LENGTH_SHORT).show();
                } else if (P2/P1 < 1.) {
                    Toast.makeText(Comp.this, "Outlet pressure smaller than inlet pressure!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (comp_Z_input.getText().length() == 0) {
                    Z = 1.0;
                    Toast.makeText(Comp.this, "Gas compressibility factor Z not specified! " +
                            "Assuming Z = 1.0 (ideal gas)!", Toast.LENGTH_SHORT).show();
                    comp_Z_input.setText(Z.toString());
                }
                Z = Double.parseDouble(comp_Z_input.getText().toString());

                if (comp_eff_input.getText().length() == 0) {
                    LinearInterpolator li = new LinearInterpolator();
                    double[] x = {1., 1.5, 2., 3., 6., 10.};
                    double[] y = {0.65, 0.65, 0.75, 0.8, 0.85, 0.85};
                    PolynomialSplineFunction psf = li.interpolate(x, y);
                    eff = psf.value(P2/P1);

                    Toast.makeText(Comp.this, "\u03b5 not specified! " +
                            "Estimating \u03b5 instead!", Toast.LENGTH_SHORT).show();
                    comp_eff_input.setText(eff.toString());
                }
                eff = Double.parseDouble(comp_eff_input.getText().toString());

                G = G / 3600;
                Double R = 8.31446261815324;
                Double k = cp / cv;
                Double a = (k - 1) / k;
                Double power = (G*Z*R*T1) * Math.pow((P2/P1), a) / a;  // useful power
                power /= 1000.;  // convert W to kW

                Double comppower = power / eff; // actual power consumed
                T2 = T1 * Math.pow(P2 / P1, a);

                if (T2 > 273.15 + 200) {
                    Toast.makeText(Comp.this, "Gas outlet temperature too high! " +
                            "Consider reducing compression ratio P2/P1! " +
                            "Nevertheless continuing calculation...", Toast.LENGTH_SHORT).show();
                }

                comp_power_output.setText(comppower.toString());
                comp_T2_output.setText(T2.toString());
            }
        });

        TextView fluid_comp_G = (TextView) findViewById(R.id.comp_G);
        fluid_comp_G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Comp.this, "Gas mass flow rate through compressor (kg/h)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_comp_T1 = (TextView) findViewById(R.id.comp_T1);
        fluid_comp_T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Comp.this, "Gas inlet temperature (K)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_comp_P1 = (TextView) findViewById(R.id.comp_P1);
        fluid_comp_P1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Comp.this, "Gas inlet pressure (bar)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_comp_P2 = (TextView) findViewById(R.id.comp_P2);
        fluid_comp_P2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Comp.this, "Gas outlet pressure (bar)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_comp_cp = (TextView) findViewById(R.id.comp_cp);
        fluid_comp_cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Comp.this, "Constant-pressure heat capacity of gas " +
                                "(same units as c\u1d65)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_comp_cv = (TextView) findViewById(R.id.comp_cv);
        fluid_comp_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Comp.this, "Constant-volume heat capacity of gas " +
                                "(same units as c\u209a)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_comp_Z = (TextView) findViewById(R.id.comp_Z);
        fluid_comp_Z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Comp.this, "Gas compressibility factor " +
                                "(optional, default = 1 for ideal gas)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_comp_eff = (TextView) findViewById(R.id.comp_eff);
        fluid_comp_eff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Comp.this, "Compressor efficiency " +
                                "(optional)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_comp_power = (TextView) findViewById(R.id.comp_power);
        fluid_comp_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Comp.this, "Compressor power (kW)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_comp_T2 = (TextView) findViewById(R.id.comp_T2);
        fluid_comp_T2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Comp.this, "Gas outlet temperature (K)",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}