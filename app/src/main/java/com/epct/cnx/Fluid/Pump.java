package com.epct.cnx.Fluid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epct.cnx.R;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AppCompatActivity;

public class Pump extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fluid_pump);

        Button pump_size = (Button) findViewById(R.id.pump_size);
        pump_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView pump_Q_input = (TextView) findViewById(R.id.pump_Q_input);
                TextView pump_rho_input = (TextView) findViewById(R.id.pump_rho_input);
                TextView pump_P1_input = (TextView) findViewById(R.id.pump_P1_input);
                TextView pump_P2_input = (TextView) findViewById(R.id.pump_P2_input);
                TextView pump_eff_input = (TextView) findViewById(R.id.pump_eff_input);

                TextView pump_power_output = (TextView) findViewById(R.id.pump_power_output);

                Double Q=-1.0, rho=-1.0, P1=-1.0, P2=-1.0, eff=-1.0;

                if (pump_Q_input.getText().length() > 0
                        & pump_rho_input.getText().length() > 0
                        & pump_P1_input.getText().length() > 0
                        & pump_P2_input.getText().length() > 0) {
                    Q = Double.parseDouble(pump_Q_input.getText().toString());
                    rho = Double.parseDouble(pump_rho_input.getText().toString());
                    P1 = Double.parseDouble(pump_P1_input.getText().toString());
                    P2 = Double.parseDouble(pump_P2_input.getText().toString());
                } else {
                    Toast.makeText(Pump.this, "Please fill in all inputs " +
                            "(except \u03b5)!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (P2/P1 < 1.) {
                    Toast.makeText(Pump.this, "Outlet pressure smaller than inlet pressure!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Double g = 9.80665;
                Double dP = P2 - P1;
                Double power = (Q / 3600.) * dP; // useful power in kW

                Double H = dP / (rho * g); // required head in m

                if (pump_eff_input.getText().length() == 0) {

                    Double H_ft = H * 3.281; // required head in ft
                    Double Q_gpm = Q * 4.403; // flowrate in gal/min (gpm)

                    if (50 <= H_ft && H_ft <= 300 && 100 <= Q_gpm && Q_gpm <= 1000) {
                        eff = 80 - 0.2855 * H_ft + 3.78e-4 * H_ft * Q_gpm
                                - 2.38e-7 * H_ft * Math.pow(Q_gpm, 2)
                                + 5.39e-4 * Math.pow(H_ft, 2)
                                - 6.39e-7 * Math.pow(H_ft, 2)*Q_gpm
                                + 4.e-10 * Math.pow(H_ft, 2)*Math.pow(Q_gpm, 2);
                        eff /= 100;
                        Toast.makeText(Pump.this, "\u03b5 not specified! " +
                                "Estimating \u03b5 instead using " +
                                "GPSA Engineering Data Book, 9th Ed. (1972)!",
                                Toast.LENGTH_SHORT).show();
                    } else if (0 <= power && power <= 3000) {
                        LinearInterpolator li = new LinearInterpolator();
                        double[] x = {0., 2., 5., 10., 30., 55., 300.};
                        double[] y = {0.55, 0.55, 0.6, 0.65, 0.7, 0.75, 0.75};
                        PolynomialSplineFunction psf = li.interpolate(x, y);
                        eff = psf.value(power);
                        Toast.makeText(Pump.this, "\u03b5 not specified! " +
                                        "Estimating \u03b5 instead using " +
                                        "North Carolina Cooperative Extension Service, " +
                                        "Publication Number: AG 452-6!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        eff = 0.75;
                        Toast.makeText(Pump.this, "\u03b5 not specified! " +
                                "Assuming \u03b5 = 0.75 instead!", Toast.LENGTH_SHORT).show();
                    }

                    pump_eff_input.setText(eff.toString());
                }
                eff = Double.parseDouble(pump_eff_input.getText().toString());

                Double pumppower = power / eff;
                pump_power_output.setText(pumppower.toString());
            }
        });

        TextView fluid_pump_Q = (TextView) findViewById(R.id.pump_Q);
        fluid_pump_Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pump.this, "Volumetric flow rate through pump (m\u00b3/h)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_pump_rho = (TextView) findViewById(R.id.pump_rho);
        fluid_pump_rho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pump.this, "Stream density (m\u00b3/kg)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_pump_P1 = (TextView) findViewById(R.id.pump_P1);
        fluid_pump_P1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pump.this, "Stream inlet pressure (kPa)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_pump_P2 = (TextView) findViewById(R.id.pump_P2);
        fluid_pump_P2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pump.this, "Stream outlet pressure (kPa)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_pump_eff = (TextView) findViewById(R.id.pump_eff);
        fluid_pump_eff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pump.this, "Pump efficiency (optional)",
                        Toast.LENGTH_SHORT).show();
            }
        });
        TextView fluid_pump_power = (TextView) findViewById(R.id.pump_power);
        fluid_pump_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pump.this, "Pump power (kW)",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}