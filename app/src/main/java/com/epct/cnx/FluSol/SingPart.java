package com.epct.cnx.FluSol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epct.cnx.R;

public class SingPart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flusol_singpart);

        Button singpart_solve = (Button) findViewById(R.id.singpart_solve);
        singpart_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView singpart_x_input = (TextView) findViewById(R.id.singpart_x_input);
                TextView singpart_rhop_input = (TextView) findViewById(R.id.singpart_rhop_input);
                TextView singpart_rhof_input = (TextView) findViewById(R.id.singpart_rhof_input);
                TextView singpart_mu_input = (TextView) findViewById(R.id.singpart_mu_input);
                TextView singpart_regime_output = (TextView) findViewById(R.id.singpart_regime_output);
                TextView singpart_UT_output = (TextView) findViewById(R.id.singpart_UT_output);
                TextView singpart_CD_output = (TextView) findViewById(R.id.singpart_CD_output);
                TextView singpart_Rep_output = (TextView) findViewById(R.id.singpart_Rep_output);

                singpart_regime_output.setText("");
                singpart_UT_output.setText("");
                singpart_Rep_output.setText("");
                singpart_CD_output.setText("");

                Double x=-1.0, rhop=-1.0, rhof=-1.0, mu=-1.0, UT=-1.0, Rep=-1.0, CD=-1.0;
                Double g = 9.80665;
                String regime;

                if (singpart_x_input.getText().length() > 0
                        & singpart_rhop_input.getText().length() > 0
                        & singpart_rhof_input.getText().length() > 0
                        & singpart_mu_input.getText().length() > 0) {
                    x = Double.parseDouble(singpart_x_input.getText().toString());
                    rhop = Double.parseDouble(singpart_rhop_input.getText().toString());
                    rhof = Double.parseDouble(singpart_rhof_input.getText().toString());
                    mu = Double.parseDouble(singpart_mu_input.getText().toString());

                    // First assume Stokes Law
                    UT = x*x*(rhop-rhof)*g/18.0/mu;
                    CD = 4.0*g*x*(rhop-rhof)/(3.0*UT*UT*rhof);
                    Rep = x*UT*rhof/mu;
                    if (Rep>0.3) {
                        // Stokes Law invalid - now assume Newton Law
                        UT = 1.74*Math.sqrt(x*g*(rhop-rhof)/rhof);
                        CD = 0.44;
                        Rep = x*UT*rhof/mu;
                        if (Rep<500) {
                            // Newton Law also invalid - now try intermediate region
                            // Iterate the 2 eqns to find CD then Rep
                            Double CD_fromCDvsRep, CDRep2, CD_fromCDRep2;
                            Double Rep_left = 0.0, Rep_right = 2e5;
                            int iter = 0;
                            while (Math.abs(Rep_right-Rep_left)/Rep_left>1e-9 && iter<1000) {
                                Rep = (Rep_left+Rep_right)/2.0;
                                CD_fromCDvsRep = 24.0/Rep*(1.0+0.15*Math.pow(Rep,0.687));
                                CDRep2 = 4.0/3.0*Math.pow(x,3.0)*rhof*(rhop-rhof)*g/Math.pow(mu,2.0);
                                CD_fromCDRep2 = CDRep2/Math.pow(Rep,2.0);
                                if (CD_fromCDRep2>CD_fromCDvsRep) {
                                    // if iterated CD is too high, increase Rep
                                    Rep_left = Rep;
                                } else {
                                    // if iterated CD is too low, decrease Rep
                                    Rep_right = Rep;
                                }
                                iter = iter + 1;
                            }
                            Toast.makeText(SingPart.this, "Iterative calculation for Re\u209a stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();

                            // resolves discontinuities in results across regime boundaries
                            Rep = Math.min(Math.max(0.3,Rep),2e5);
                            UT = Rep*mu/x/rhof;
                            CD = 4.0*g*x*(rhop-rhof)/(3.0*UT*UT*rhof);
                        }
                    }

                    if (Rep<=0.3) {regime="Stokes Law";}
                    else if (500.0<=Rep && Rep<=2e5) {regime="Newton Law";}
                    else if (0.3<Rep && Rep<500.0) {regime="Intermediate";}
                    else if (Rep>2e5) {regime="Beyond Newton Law";}
                    else {regime="";}

                    if (Rep<2e5 && regime!="") {
                        singpart_regime_output.setText(regime);
                        singpart_UT_output.setText(UT.toString());
                        singpart_Rep_output.setText(Rep.toString());
                        singpart_CD_output.setText(CD.toString());
                    }
                    else {
                        singpart_regime_output.setText(regime);
                        singpart_UT_output.setText("");
                        singpart_Rep_output.setText("");
                        singpart_CD_output.setText("");
                        Toast.makeText(SingPart.this, "Re\u209a is beyond 2E+05, hence calculations are invalid!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(SingPart.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView singpart_x = (TextView) findViewById(R.id.singpart_x);
        singpart_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingPart.this, "Particle diameter x (in m)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView singpart_rhop = (TextView) findViewById(R.id.singpart_rhop);
        singpart_rhop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingPart.this, "Particle density \u03c1\u209a (in kg/m\u00b3)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView singpart_rhof = (TextView) findViewById(R.id.singpart_rhof);
        singpart_rhof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingPart.this, "Fluid density \u03c1 (in kg/m\u00b3", Toast.LENGTH_SHORT).show();
            }
        });

        TextView singpart_mu = (TextView) findViewById(R.id.singpart_mu);
        singpart_mu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingPart.this, "Fluid viscosity \u03bc (in Pa s)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView singpart_regime = (TextView) findViewById(R.id.singpart_regime);
        singpart_regime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingPart.this, "Settling regime (Stokes, intermediate or Newton)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView singpart_UT = (TextView) findViewById(R.id.singpart_UT);
        singpart_UT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingPart.this, "Single-particle terminal velocity (in m/s)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView singpart_CD = (TextView) findViewById(R.id.singpart_CD);
        singpart_CD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingPart.this, "Particle-fluid drag coefficient (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView singpart_Rep = (TextView) findViewById(R.id.singpart_Rep);
        singpart_Rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SingPart.this, "Particle Reynolds number\nRe\u209a=\u03c1U\u209cx/\u03bc (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}