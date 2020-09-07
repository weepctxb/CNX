package com.epct.cnx.FluSol;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;

import com.epct.cnx.Fluid.Friction;
import com.epct.cnx.R;

public class PackedBed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flusol_packedbed);

        Spinner spinner_packedbed_eqn = (Spinner) findViewById(R.id.packedbed_eqn_input);
        ArrayAdapter<CharSequence> adapter_packedbed_eqn = ArrayAdapter.createFromResource(this,
                R.array.packedbed_eqn, android.R.layout.simple_spinner_item);
        adapter_packedbed_eqn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_packedbed_eqn.setAdapter(adapter_packedbed_eqn);

        Button packedbed_solve = (Button) findViewById(R.id.packedbed_solve);
        packedbed_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView packedbed_x_input = (TextView) findViewById(R.id.packedbed_x_input);
                TextView packedbed_Q_input = (TextView) findViewById(R.id.packedbed_Q_input);
                TextView packedbed_H_input = (TextView) findViewById(R.id.packedbed_H_input);
                TextView packedbed_A_input = (TextView) findViewById(R.id.packedbed_A_input);
                TextView packedbed_rhof_input = (TextView) findViewById(R.id.packedbed_rhof_input);
                TextView packedbed_mu_input = (TextView) findViewById(R.id.packedbed_mu_input);
                TextView packedbed_eps_input = (TextView) findViewById(R.id.packedbed_eps_input);

                Spinner spinner_packedbed_eqn = (Spinner) findViewById(R.id.packedbed_eqn_input);

                TextView packedbed_U_output = (TextView) findViewById(R.id.packedbed_U_output);
                TextView packedbed_Reb_output = (TextView) findViewById(R.id.packedbed_Reb_output);
                TextView packedbed_flow_output = (TextView) findViewById(R.id.packedbed_flow_output);
                TextView packedbed_dP_output = (TextView) findViewById(R.id.packedbed_dP_output);

                packedbed_U_output.setText("");
                packedbed_Reb_output.setText("");
                packedbed_flow_output.setText("");
                packedbed_dP_output.setText("");

                Double x=-1.0, Q=-1.0, H=-1.0, A=-1.0, rhof=-1.0, mu=-1.0, eps=-1.0;
                String regime;

                if (packedbed_eps_input.getText().length() == 0) {
                    eps = 0.4;
                    Toast.makeText(PackedBed.this, "\u03b5 not specified! " +
                            "Assuming \u03b5 = 0.4!", Toast.LENGTH_SHORT).show();
                    packedbed_eps_input.setText(eps.toString());
                }

                if (packedbed_x_input.getText().length() > 0
                        & packedbed_Q_input.getText().length() > 0
                        & packedbed_H_input.getText().length() > 0
                        & packedbed_A_input.getText().length() > 0
                        & packedbed_rhof_input.getText().length() > 0
                        & packedbed_mu_input.getText().length() > 0) {
                    x = Double.parseDouble(packedbed_x_input.getText().toString());
                    Q = Double.parseDouble(packedbed_Q_input.getText().toString());
                    H = Double.parseDouble(packedbed_H_input.getText().toString());
                    A = Double.parseDouble(packedbed_A_input.getText().toString());
                    rhof = Double.parseDouble(packedbed_rhof_input.getText().toString());
                    mu = Double.parseDouble(packedbed_mu_input.getText().toString());
                    eps = Double.parseDouble(packedbed_eps_input.getText().toString());

                    Boolean carkoz = false, ergun = false;
                    switch (spinner_packedbed_eqn.getSelectedItemPosition()) {
                        case 0: //carkoz
                            carkoz = true;
                            ergun = false;
                            break;
                        case 1: //ergun
                            carkoz = false;
                            ergun = true;
                            break;
                    }

                    Double Reb, U, dP;

                    U = Q / A;
                    Reb = x * U * rhof / mu / (1 - eps);

                    if (carkoz) {
                        if (Reb <= 10) {
                            regime = "Laminar";
                            dP = H * 180 * mu * U / Math.pow(x, 2)
                                    * Math.pow(1-eps, 2) / Math.pow(eps, 3);

                            packedbed_U_output.setText(U.toString());
                            packedbed_Reb_output.setText(Reb.toString());
                            packedbed_flow_output.setText(regime);
                            packedbed_dP_output.setText(dP.toString());
                        } else if (Reb >= 2000) {
                            regime = "Turbulent";
                            dP = H * 1.75 * rhof * Math.pow(U, 2) / x * (1-eps) / Math.pow(eps, 3);

                            packedbed_U_output.setText(U.toString());
                            packedbed_Reb_output.setText(Reb.toString());
                            packedbed_flow_output.setText(regime);
                            packedbed_dP_output.setText(dP.toString());
                        } else {
                            regime = "Use Ergun instead (Intermediate regime)";
                            Toast.makeText(PackedBed.this, "Packed bed Re in intermediate region! " +
                                    "Use Ergun equation instead!", Toast.LENGTH_SHORT).show();

                            packedbed_U_output.setText(U.toString());
                            packedbed_Reb_output.setText(Reb.toString());
                            packedbed_flow_output.setText(regime);
                        }
                    } else if (ergun) {
                        regime = "-";
                        Double lami, turb;
                        lami = 150 * mu * U / Math.pow(x, 2) * Math.pow(1-eps, 2) / Math.pow(eps, 3);
                        turb = 1.75 * rhof * Math.pow(U, 2) / x * (1-eps) / Math.pow(eps, 3);
                        dP = H * (lami + turb);

                        packedbed_U_output.setText(U.toString());
                        packedbed_Reb_output.setText(Reb.toString());
                        packedbed_flow_output.setText(regime);
                        packedbed_dP_output.setText(dP.toString());
                    }
                } else {
                    Toast.makeText(PackedBed.this, "Please fill in all inputs!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button fluidbed_solve = (Button) findViewById(R.id.fluidbed_solve);
        fluidbed_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView fluidbed_x_input = (TextView) findViewById(R.id.fluidbed_x_input);
                TextView fluidbed_rhop_input = (TextView) findViewById(R.id.fluidbed_rhop_input);
                TextView fluidbed_packedbedH_input = (TextView) findViewById(R.id.fluidbed_packedbedH_input);
                TextView fluidbed_A_input = (TextView) findViewById(R.id.fluidbed_A_input);
                TextView fluidbed_rhof_input = (TextView) findViewById(R.id.fluidbed_rhof_input);
                TextView fluidbed_mu_input = (TextView) findViewById(R.id.fluidbed_mu_input);
                TextView fluidbed_epsmf_input = (TextView) findViewById(R.id.fluidbed_epsmf_input);

                TextView fluidbed_Umf_output = (TextView) findViewById(R.id.fluidbed_Umf_output);
                TextView fluidbed_Remf_output = (TextView) findViewById(R.id.fluidbed_Remf_output);
                TextView fluidbed_dP_output = (TextView) findViewById(R.id.fluidbed_dP_output);
                TextView fluidbed_Qmf_output = (TextView) findViewById(R.id.fluidbed_Qmf_output);
                TextView fluidbed_Ar_output = (TextView) findViewById(R.id.fluidbed_Ar_output);

                fluidbed_Umf_output.setText("");
                fluidbed_Remf_output.setText("");
                fluidbed_dP_output.setText("");
                fluidbed_Qmf_output.setText("");
                fluidbed_Ar_output.setText("");

                Double x=-1.0, rhop=-1.0, H=-1.0, A=-1.0, rhof=-1.0, mu=-1.0, eps=-1.0;
                Double g = 9.80665;
                String regime;

                if (fluidbed_x_input.getText().length() > 0
                        & fluidbed_rhop_input.getText().length() > 0
                        & fluidbed_packedbedH_input.getText().length() > 0
                        & fluidbed_A_input.getText().length() > 0
                        & fluidbed_rhof_input.getText().length() > 0
                        & fluidbed_mu_input.getText().length() > 0
                        & fluidbed_epsmf_input.getText().length() == 0) {
                    x = Double.parseDouble(fluidbed_x_input.getText().toString());
                    rhop = Double.parseDouble(fluidbed_rhop_input.getText().toString());
                    H = Double.parseDouble(fluidbed_packedbedH_input.getText().toString());
                    A = Double.parseDouble(fluidbed_A_input.getText().toString());
                    rhof = Double.parseDouble(fluidbed_rhof_input.getText().toString());
                    mu = Double.parseDouble(fluidbed_mu_input.getText().toString());

                    Double Ar, Umf, Qmf, Remf, a, b, rhs, dP;

                    Ar = rhof * (rhop-rhof) * g * Math.pow(x,3) / Math.pow(mu,2);

                    if (x <= 1e-4) {

                        Toast.makeText(PackedBed.this, "\u03b5 not specified!" +
                                "\nUsing Baeyens and Geldart (1974) correlation" +
                                " to estimate U_mf and \u03b5.", Toast.LENGTH_SHORT).show();

                        Umf = Math.pow(rhop-rhof,0.934) * Math.pow(g,0.934) * Math.pow(x,1.8) /
                                (1110 * Math.pow(mu,0.87) * Math.pow(rhof,0.066));
                        Qmf = Umf * A;
                        Remf = x * Umf * rhof / mu;

                        Double epsleft = 1e-9, epsright = 1-1e-9;
                        int iter = 0;
                        while (Math.abs(epsright-epsleft)/epsleft>1e-9 && iter<1000) {
                            eps = (epsright + epsleft) / 2;
                            a = 1.75 / Math.pow(eps, 3);
                            b = 150 * (1-eps) / Math.pow(eps, 3);
                            rhs = b * Remf + a * Math.pow(Remf, 2);
                            if (Ar < rhs) {
                                // if iterated rhs is too high, increase eps
                                epsleft = eps;
                            } else {
                                // if iterated rhs is too low, decrease eps
                                epsright = eps;
                            }
                            iter = iter + 1;
                        }

                        Toast.makeText(PackedBed.this, "Iterative calculation for " +
                                "\u03b5 using Baeyens and Geldart stopped at iteration " +
                                Integer.toString(iter), Toast.LENGTH_SHORT).show();
                        dP = H * (1-eps) * (rhop-rhof) * g;

                        fluidbed_epsmf_input.setText(eps.toString());

                        fluidbed_Umf_output.setText(Umf.toString());
                        fluidbed_Remf_output.setText(Remf.toString());
                        fluidbed_dP_output.setText(dP.toString());
                        fluidbed_Qmf_output.setText(Qmf.toString());
                        fluidbed_Ar_output.setText(Ar.toString());

                        return;

                    } else if (x > 1e-4) {

                        // Test first if Wen & Yu is valid
                        Remf = 33.7 * (Math.sqrt(1 + 3.59e-5 * Ar) - 1);

                        if (0.01 <= Remf && Remf <= 1000) {
                            Toast.makeText(PackedBed.this, "\u03b5 not specified!" +
                                    "\nUsing Wen and Yu (1966) correlation" +
                                    " to estimate U_mf and \u03b5.", Toast.LENGTH_SHORT).show();
                            Umf = Remf * mu / (x * rhof);
                            Qmf = Umf * A;

                            Double epsleft = 1e-9, epsright = 1-1e-9;
                            int iter = 0;
                            while (Math.abs(epsright-epsleft)/epsleft>1e-9 && iter<1000) {
                                eps = (epsright + epsleft) / 2;
                                a = 1.75 / Math.pow(eps, 3);
                                b = 150 * (1-eps) / Math.pow(eps, 3);
                                rhs = b * Remf + a * Math.pow(Remf, 2);
                                if (Ar < rhs) {
                                    // if iterated rhs is too high, increase eps
                                    epsleft = eps;
                                } else {
                                    // if iterated rhs is too low, decrease eps
                                    epsright = eps;
                                }
                                iter = iter + 1;
                            }

                            Toast.makeText(PackedBed.this, "Iterative calculation for " +
                                    "\u03b5 using Wen and Yu stopped at iteration " +
                                    Integer.toString(iter), Toast.LENGTH_SHORT).show();
                            dP = H * (1-eps) * (rhop-rhof) * g;

                            fluidbed_epsmf_input.setText(eps.toString());

                            fluidbed_Umf_output.setText(Umf.toString());
                            fluidbed_Remf_output.setText(Remf.toString());
                            fluidbed_dP_output.setText(dP.toString());
                            fluidbed_Qmf_output.setText(Qmf.toString());
                            fluidbed_Ar_output.setText(Ar.toString());

                            return;

                        } else {

                            eps = 0.4;
                            Toast.makeText(PackedBed.this, "\u03b5 not specified and " +
                                    "no valid correlations available! " +
                                    "Assuming \u03b5 = 0.4!", Toast.LENGTH_SHORT).show();
                            fluidbed_epsmf_input.setText(eps.toString());

                            // continue onwards to next code block

                        }
                    }
                }

                if (fluidbed_x_input.getText().length() > 0
                        & fluidbed_rhop_input.getText().length() > 0
                        & fluidbed_packedbedH_input.getText().length() > 0
                        & fluidbed_A_input.getText().length() > 0
                        & fluidbed_rhof_input.getText().length() > 0
                        & fluidbed_mu_input.getText().length() > 0
                        & fluidbed_epsmf_input.getText().length() > 0) {
                    x = Double.parseDouble(fluidbed_x_input.getText().toString());
                    rhop = Double.parseDouble(fluidbed_rhop_input.getText().toString());
                    H = Double.parseDouble(fluidbed_packedbedH_input.getText().toString());
                    A = Double.parseDouble(fluidbed_A_input.getText().toString());
                    rhof = Double.parseDouble(fluidbed_rhof_input.getText().toString());
                    mu = Double.parseDouble(fluidbed_mu_input.getText().toString());
                    eps = Double.parseDouble(fluidbed_epsmf_input.getText().toString());

                    Double Ar, a, b, Remf, Umf, Qmf, dP;

                    Ar = rhof * (rhop-rhof) * g * Math.pow(x,3) / Math.pow(mu,2);
                    a = 1.75 / Math.pow(eps, 3);
                    b = 150 * (1-eps) / Math.pow(eps, 3);
                    Remf = (-b + Math.sqrt(Math.pow(b,2) - 4 * a * -Ar)) / (2*a);
                    Umf = Remf * mu / (x * rhof);
                    Qmf = Umf * A;
                    dP = H * (1-eps) * (rhop-rhof) * g;

                    fluidbed_Umf_output.setText(Umf.toString());
                    fluidbed_Remf_output.setText(Remf.toString());
                    fluidbed_dP_output.setText(dP.toString());
                    fluidbed_Qmf_output.setText(Qmf.toString());
                    fluidbed_Ar_output.setText(Ar.toString());
                } else {
                    Toast.makeText(PackedBed.this, "Please fill in all inputs!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView packedbed_x = (TextView) findViewById(R.id.packedbed_x);
        packedbed_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Particle diameter (in m)\n" +
                        "(or use x\u209B\u1D65 = diameter of sphere having same SA:V ratio for non-spherical particles)"
                        , Toast.LENGTH_SHORT).show();
            }
        });

        TextView packedbed_Q = (TextView) findViewById(R.id.packedbed_Q);
        packedbed_Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Superficial flow rate (in m\u00b3/s)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView packedbed_H = (TextView) findViewById(R.id.packedbed_H);
        packedbed_H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Packed bed height (in m)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView packedbed_A = (TextView) findViewById(R.id.packedbed_A);
        packedbed_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Packed bed cross-sectional area (in m\u00b2)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView packedbed_rhof = (TextView) findViewById(R.id.packedbed_rhof);
        packedbed_rhof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Fluid density (in kg/m\u00b3)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView packedbed_mu = (TextView) findViewById(R.id.packedbed_mu);
        packedbed_mu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Fluid viscosity (in Pa.s)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView packedbed_eps = (TextView) findViewById(R.id.packedbed_eps);
        packedbed_eps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Packed bed voidage (dimensionless, default = 0.4)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView packedbed_eqn = (TextView) findViewById(R.id.packedbed_eqn);
        packedbed_eqn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Equation type to use for calculation " +
                                "(Use Carman-Kozeny or Ergun)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView packedbed_U = (TextView) findViewById(R.id.packedbed_U);
        packedbed_U.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Superficial velocity in packed bed (in m/s)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView packedbed_Reb = (TextView) findViewById(R.id.packedbed_Reb);
        packedbed_Reb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Packed bed Reynolds number (dimensionless)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView packedbed_flow = (TextView) findViewById(R.id.packedbed_flow);
        packedbed_flow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Flow regime",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView packedbed_dP = (TextView) findViewById(R.id.packedbed_dP);
        packedbed_dP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Pressure drop across packed bed (in Pa)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView fluidbed_x = (TextView) findViewById(R.id.fluidbed_x);
        fluidbed_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Particle diameter (in m)\n" +
                                "(or use x_SV = diameter of sphere having same SA:V ratio for non-spherical particles)"
                        , Toast.LENGTH_SHORT).show();
            }
        });

        TextView fluidbed_rhop = (TextView) findViewById(R.id.fluidbed_rhop);
        fluidbed_rhop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Particle density (in kg/m\u00b3)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView fluidbed_packedbedH = (TextView) findViewById(R.id.fluidbed_packedbedH);
        fluidbed_packedbedH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Packed bed height = Bed height at minimum fluidisation (in m)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView fluidbed_A = (TextView) findViewById(R.id.fluidbed_A);
        fluidbed_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Fluidised bed cross-sectional area (in m\u00b2)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView fluidbed_rhof = (TextView) findViewById(R.id.fluidbed_rhof);
        fluidbed_rhof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Fluid density (in kg/m\u00b3)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView fluidbed_mu = (TextView) findViewById(R.id.fluidbed_mu);
        fluidbed_mu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Fluid viscosity (in Pa.s)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView fluidbed_epsmf = (TextView) findViewById(R.id.fluidbed_epsmf);
        fluidbed_epsmf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Voidage at minimum fluidisation = Packed bed voidage" +
                                " (dimensionless)\nIf left blank, it will be estimated using " +
                                "(in order of priority): Baeyens and Geldart (1974), Wen and Yu (1966), \u03b5=0.4 otherwise",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView fluidbed_Umf = (TextView) findViewById(R.id.fluidbed_Umf);
        fluidbed_Umf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Minimum fluidisation velocity (in m/s)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView fluidbed_Remf = (TextView) findViewById(R.id.fluidbed_Remf);
        fluidbed_Remf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Minimum fluidisation Reynolds number (dimensionless)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView fluidbed_Qmf = (TextView) findViewById(R.id.fluidbed_Qmf);
        fluidbed_Qmf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Minimum fluidisation flow rate (in m\u00b3/s)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView fluidbed_Ar = (TextView) findViewById(R.id.fluidbed_Ar);
        fluidbed_Ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Archimedes number between solid particle and fluid (dimensionless)",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView fluidbed_dP = (TextView) findViewById(R.id.fluidbed_dP);
        fluidbed_dP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PackedBed.this, "Pressure drop across fluidised bed = Pressure drop at minimum fluidisation (in Pa)",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}