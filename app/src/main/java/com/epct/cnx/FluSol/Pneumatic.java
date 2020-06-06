package com.epct.cnx.FluSol;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.epct.cnx.R;

public class Pneumatic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flusol_pneumatic);

        Spinner spinner_pneumatic_initaccel = (Spinner) findViewById(R.id.pneumatic_initaccel_input);
        ArrayAdapter<CharSequence> adapter_pneumatic_initaccel = ArrayAdapter.createFromResource(this,
                R.array.pneumatic_initaccel, android.R.layout.simple_spinner_item);
        adapter_pneumatic_initaccel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pneumatic_initaccel.setAdapter(adapter_pneumatic_initaccel);

        Button pneumatic_solve = (Button) findViewById(R.id.pneumatic_solve);
        pneumatic_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView pneumatic_D_input = (TextView) findViewById(R.id.pneumatic_D_input);
                TextView pneumatic_e_input = (TextView) findViewById(R.id.pneumatic_e_input);
                TextView pneumatic_rhop_input = (TextView) findViewById(R.id.pneumatic_rhop_input);
                TextView pneumatic_rhof_input = (TextView) findViewById(R.id.pneumatic_rhof_input);
                TextView pneumatic_Mp_input = (TextView) findViewById(R.id.pneumatic_Mp_input);
                TextView pneumatic_x_input = (TextView) findViewById(R.id.pneumatic_x_input);
                TextView pneumatic_mu_input = (TextView) findViewById(R.id.pneumatic_mu_input);
                TextView pneumatic_Lh_input = (TextView) findViewById(R.id.pneumatic_Lh_input);
                TextView pneumatic_Lv_input = (TextView) findViewById(R.id.pneumatic_Lv_input);
                Spinner spinner_pneumatic_initaccel = (Spinner) findViewById(R.id.pneumatic_initaccel_input);
                TextView pneumatic_bends_input = (TextView) findViewById(R.id.pneumatic_bends_input);
                TextView pneumatic_margin_input = (TextView) findViewById(R.id.pneumatic_margin_input);

                TextView pneumatic_Gp_output = (TextView) findViewById(R.id.pneumatic_Gp_output);
                TextView pneumatic_Usalt_output = (TextView) findViewById(R.id.pneumatic_Usalt_output);
                TextView pneumatic_UCH_output = (TextView) findViewById(R.id.pneumatic_UCH_output);
                TextView pneumatic_voidCH_output = (TextView) findViewById(R.id.pneumatic_voidCH_output);
                TextView pneumatic_Ufs_output = (TextView) findViewById(R.id.pneumatic_Ufs_output);
                TextView pneumatic_Uph_output = (TextView) findViewById(R.id.pneumatic_Uph_output);
                TextView pneumatic_voidh_output = (TextView) findViewById(R.id.pneumatic_voidh_output);
                TextView pneumatic_Ufh_output = (TextView) findViewById(R.id.pneumatic_Ufh_output);
                TextView pneumatic_Rep_output = (TextView) findViewById(R.id.pneumatic_Rep_output);
                TextView pneumatic_CD_output = (TextView) findViewById(R.id.pneumatic_CD_output);
                TextView pneumatic_fp_output = (TextView) findViewById(R.id.pneumatic_fp_output);
                TextView pneumatic_Fpwh_output = (TextView) findViewById(R.id.pneumatic_Fpwh_output);
                TextView pneumatic_Ref_output = (TextView) findViewById(R.id.pneumatic_Ref_output);
                TextView pneumatic_ff_output = (TextView) findViewById(R.id.pneumatic_ff_output);
                TextView pneumatic_Ffwh_output = (TextView) findViewById(R.id.pneumatic_Ffwh_output);
                TextView pneumatic_UT_output = (TextView) findViewById(R.id.pneumatic_UT_output);
                TextView pneumatic_voidv_output = (TextView) findViewById(R.id.pneumatic_voidv_output);
                TextView pneumatic_Ufv_output = (TextView) findViewById(R.id.pneumatic_Ufv_output);
                TextView pneumatic_Upv_output = (TextView) findViewById(R.id.pneumatic_Upv_output);
                TextView pneumatic_Ffwv_output = (TextView) findViewById(R.id.pneumatic_Ffwv_output);
                TextView pneumatic_Fpwv_output = (TextView) findViewById(R.id.pneumatic_Fpwv_output);
                TextView pneumatic_dP1_horiz_output = (TextView) findViewById(R.id.pneumatic_dP1_horiz_output);
                TextView pneumatic_dP2_horiz_output = (TextView) findViewById(R.id.pneumatic_dP2_horiz_output);
                TextView pneumatic_dP3_horiz_output = (TextView) findViewById(R.id.pneumatic_dP3_horiz_output);
                TextView pneumatic_dP4_horiz_output = (TextView) findViewById(R.id.pneumatic_dP4_horiz_output);
                TextView pneumatic_dP5_horiz_output = (TextView) findViewById(R.id.pneumatic_dP5_horiz_output);
                TextView pneumatic_dP6_horiz_output = (TextView) findViewById(R.id.pneumatic_dP6_horiz_output);
                TextView pneumatic_dPt_horiz_output = (TextView) findViewById(R.id.pneumatic_dPt_horiz_output);
                TextView pneumatic_dP1_vert_output = (TextView) findViewById(R.id.pneumatic_dP1_vert_output);
                TextView pneumatic_dP2_vert_output = (TextView) findViewById(R.id.pneumatic_dP2_vert_output);
                TextView pneumatic_dP3_vert_output = (TextView) findViewById(R.id.pneumatic_dP3_vert_output);
                TextView pneumatic_dP4_vert_output = (TextView) findViewById(R.id.pneumatic_dP4_vert_output);
                TextView pneumatic_dP5_vert_output = (TextView) findViewById(R.id.pneumatic_dP5_vert_output);
                TextView pneumatic_dP6_vert_output = (TextView) findViewById(R.id.pneumatic_dP6_vert_output);
                TextView pneumatic_dPt_vert_output = (TextView) findViewById(R.id.pneumatic_dPt_vert_output);
                TextView pneumatic_dPb_output = (TextView) findViewById(R.id.pneumatic_dPb_output);
                TextView pneumatic_dPtotal_output = (TextView) findViewById(R.id.pneumatic_dPtotal_output);

                Double D = -1.0, e = -1.0, rhop = -1.0, rhof = -1.0, Mp = -1.0, x = -1.0,
                        mu = -1.0, Lh = -1.0, Lv = -1.0, margin = -1.0, eps = 1e-7;
                Double g = 9.80665;
                int bends = -1;
                int unfilled = 0, benderror = 0;

                if (pneumatic_D_input.getText().length() > 0) {
                    D = Double.parseDouble(pneumatic_D_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (pneumatic_e_input.getText().length() > 0) {
                    e = Double.parseDouble(pneumatic_e_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (pneumatic_rhop_input.getText().length() > 0) {
                    rhop = Double.parseDouble(pneumatic_rhop_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (pneumatic_rhof_input.getText().length() > 0) {
                    rhof = Double.parseDouble(pneumatic_rhof_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (pneumatic_Mp_input.getText().length() > 0) {
                    Mp = Double.parseDouble(pneumatic_Mp_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (pneumatic_x_input.getText().length() > 0) {
                    x = Double.parseDouble(pneumatic_x_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (pneumatic_mu_input.getText().length() > 0) {
                    mu = Double.parseDouble(pneumatic_mu_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (pneumatic_Lh_input.getText().length() > 0) {
                    Lh = Double.parseDouble(pneumatic_Lh_input.getText().toString());
                } else {
                    pneumatic_Lh_input.setText("0");
                    Lh = 0.0; // set zero horizontal distance
                }
                if (pneumatic_Lv_input.getText().length() > 0) {
                    Lv = Double.parseDouble(pneumatic_Lv_input.getText().toString());
                } else {
                    pneumatic_Lv_input.setText("0");
                    Lv = 0.0; // set zero vertical distance
                }
                if (pneumatic_bends_input.getText().length() > 0) {
                    bends = Integer.parseInt(pneumatic_bends_input.getText().toString());
                    if (bends == 0 && Lh > 0.0 && Lv > 0.0) {
                        Toast.makeText(Pneumatic.this, "There has to be at least one bend\nif there are horizontal and vertical segments!", Toast.LENGTH_SHORT).show();
                        benderror = 1;
                    }
                    else if (bends > 0 && (Lh < eps || Lv < eps)) {
                        Toast.makeText(Pneumatic.this, "There cannot be any bends\nif there is only piping in one direction!", Toast.LENGTH_SHORT).show();
                        benderror = 1;
                    }
                } else {
                    if (Lh > 0.0 && Lv > 0.0) {
                        Toast.makeText(Pneumatic.this, "There has to be at least one bend\nif there are horizontal and vertical segments!", Toast.LENGTH_SHORT).show();
                        benderror = 1;
                    }
                    else {
                        pneumatic_bends_input.setText("0");
                        bends = 0; // set zero bends
                    }
                }
                if (pneumatic_margin_input.getText().length() > 0) {
                    margin = Double.parseDouble(pneumatic_margin_input.getText().toString());
                } else {
                    pneumatic_margin_input.setText("0");
                    margin = 0.0; // set zero safety margin
                }

                if (unfilled > 0) {
                    Toast.makeText(Pneumatic.this, "Please ensure all fields are filled!\n(Except for t which is only for puff model)", Toast.LENGTH_SHORT).show();
                }
                else if (benderror > 0){
                    //do nothing
                }
                else {
                    Double A, Gp, Usalt, voidCH0 = 1e-7, voidCH = 0.99, UCH, Ufs,
                            Uph, voidh, Ufh,
                            Rep, CD, fp, Fpwh,
                            Ref, ff, Ffwh,
                            UT, voidv, Ufv, Upv,
                            Ffwv, Fpwv;

                    A = Math.PI*Math.pow(D,2.0)/4.0;
                    Gp = Mp/A;
                    Usalt = Math.pow(
                            Mp*Math.pow(
                                    Math.sqrt(g*D),
                                    1100.0*x+2.5)/rhof/A*Math.pow(10.0,1440.0*x+1.96)
                            ,1.0/(1100.0*x+3.5));
                    Ufs = (1.0+margin/100.0)*Usalt;
                    Uph = Ufs*(1-0.0638*Math.pow(x,0.3)*Math.pow(rhop,0.5));
                    voidh = 1-Gp/(rhop*Uph);
                    Ufh = Ufs/voidh;
                    Rep = rhof*(Ufh-Uph)*x/mu;
                    CD = 24.0/Rep*(1.0+0.1806*Math.pow(Rep,0.6459))
                            +0.4251/(1.0+6880.95/Rep);
                    UT = Math.sqrt(4.0/3.0/CD*g*x*(rhop-rhof)/rhof);

                    int iter = 0;
                    Double fCH = 0.0, dfCH = 0.0;
                    while (Math.abs(voidCH - voidCH0) / voidCH0 > eps & iter < 1000) {
                        voidCH0 = voidCH;
                        fCH = (Math.pow(voidCH0,-4.7)-1.0)*Math.pow(1-voidCH0,2.0)
                                -Math.pow(rhof,0.77)/2250.0/D*Math.pow(Gp/rhop,2.0);
                        dfCH = -4.7*Math.pow(voidCH0,-5.7)
                                +7.4*Math.pow(voidCH0,-4.7)
                                -2.7*Math.pow(voidCH0,-3.7)
                                -2.0*voidCH0+2.0;
                        voidCH = voidCH0-fCH/dfCH;;
                        iter = iter + 1;
                    }
                    Toast.makeText(Pneumatic.this, "Iterative calculation for choking voidage stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();

                    UCH = voidCH*(UT+Gp/rhop/(1.0-voidCH));
                    // If somehow UCH is larger than Usalt, then Ufs has to be based on UCH instead of Usalt.
                    if (UCH > Usalt) {
                        Ufs = (1.0+margin/100.0)*UCH;
                        Uph = Ufs*(1-0.0638*Math.pow(x,0.3)*Math.pow(rhop,0.5));
                        voidh = 1.0-Gp/(rhop*Uph);
                        Ufh = Ufs/voidh;
                        Rep = rhof*(Ufh-Uph)*x/mu;
                        CD = 24.0/Rep*(1.0+0.1806*Math.pow(Rep,0.6459))
                                +0.4251/(1.0+6880.95/Rep);
                        UT = Math.sqrt(4.0/3.0/CD*g*x*(rhop-rhof)/rhof);
                    }

                    fp = 3.0*rhof*D/(8.0*rhop*x)*CD*Math.pow((Ufh-Uph)/Uph,2.0);
                    Fpwh = 2.0*fp*Gp*Uph/D;
                    Ref = rhof*Ufs*D/mu;
                    ff = Math.pow(-4.0*Math.log10(e/D/3.7065
                            -5.0452/Ref*Math.log10(
                            Math.pow(e/D,1.1098)/2.8257
                                    +Math.pow(7.149/Ref,0.8981))
                    ),-2.0);
                    Ffwh = 2*ff*rhof*Math.pow(Ufs,2.0)/D;

                    Double a=UT, b=-(UT+Ufs+Gp/rhop), c=Ufs;
                    Double voidv1 = (-b+Math.sqrt(b*b-4.0*a*c))/(2.0*a);
                    Double voidv2 = (-b-Math.sqrt(b*b-4.0*a*c))/(2.0*a);
                    voidv = Math.max(voidv1,voidv2);
                    if (0.0 < voidv1 && voidv1 < 1.0) {
                        voidv = voidv1;
                    }
                    else {
                        voidv = voidv2;
                    }
                    Ufv = Ufs/voidv;
                    Upv = Gp/rhop/(1-voidv);
                    Ffwv = Ffwh;
                    Fpwv = 0.057*Gp*Math.sqrt(g/D);

                    Double dP1h, dP2h, dP3h, dP4h, dP5h, dP6h, dPh,
                            dP1v, dP2v, dP3v, dP4v, dP5v, dP6v, dPv, dPb, dPtotal;
                    if (spinner_pneumatic_initaccel.getSelectedItemPosition()==0) {
                        //Initial horizontal acceleration
                        dP1h = 0.5*voidh*rhof*Ufh*Ufh;
                        dP2h = 0.5*(1-voidh)*rhop*Uph*Uph;
                        dP1v = 0.0;
                        dP2v = 0.0;
                    } else {
                        //Initial vertical acceleration
                        dP1h = 0.0;
                        dP2h = 0.0;
                        dP1v = 0.5*voidv*rhof*Ufv*Ufv;
                        dP2v = 0.5*(1-voidv)*rhop*Upv*Upv;
                    }
                    dP3h = Ffwh*Lh;
                    dP4h = Fpwh*Lh;
                    dP5h = 0.0; //since horizontal, no change in static head of particles
                    dP6h = 0.0; //since horizontal, no change in static head of gas
                    dP3v = Ffwv*Lv;
                    dP4v = Fpwv*Lv;
                    dP5v = rhop*Lv*(1-voidv)*g;
                    dP6v = rhof*Lv*voidv*g;
                    dPh = dP1h+dP2h+dP3h+dP4h+dP5h+dP6h;
                    dPv = dP1v+dP2v+dP3v+dP4v+dP5v+dP6v;
                    dPb = bends*7.5*dPv/Lv;
                    dPtotal = dPh+dPv+dPb;

                    pneumatic_Gp_output.setText(Gp.toString());
                    pneumatic_Usalt_output.setText(Usalt.toString());
                    pneumatic_UCH_output.setText(UCH.toString());
                    pneumatic_voidCH_output.setText(voidCH.toString());
                    pneumatic_Ufs_output.setText(Ufs.toString());
                    pneumatic_Uph_output.setText(Uph.toString());
                    pneumatic_voidh_output.setText(voidh.toString());
                    pneumatic_Ufh_output.setText(Ufh.toString());
                    pneumatic_Rep_output.setText(Rep.toString());
                    pneumatic_CD_output.setText(CD.toString());
                    pneumatic_fp_output.setText(fp.toString());
                    pneumatic_Fpwh_output.setText(Fpwh.toString());
                    pneumatic_Ref_output.setText(Ref.toString());
                    pneumatic_ff_output.setText(ff.toString());
                    pneumatic_Ffwh_output.setText(Ffwh.toString());
                    pneumatic_UT_output.setText(UT.toString());
                    pneumatic_voidv_output.setText(voidv.toString());
                    pneumatic_Ufv_output.setText(Ufv.toString());
                    pneumatic_Upv_output.setText(Upv.toString());
                    pneumatic_Ffwv_output.setText(Ffwv.toString());
                    pneumatic_Fpwv_output.setText(Fpwv.toString());
                    pneumatic_dP1_horiz_output.setText(dP1h.toString());
                    pneumatic_dP2_horiz_output.setText(dP2h.toString());
                    pneumatic_dP3_horiz_output.setText(dP3h.toString());
                    pneumatic_dP4_horiz_output.setText(dP4h.toString());
                    pneumatic_dP5_horiz_output.setText(dP5h.toString());
                    pneumatic_dP6_horiz_output.setText(dP6h.toString());
                    pneumatic_dPt_horiz_output.setText(dPh.toString());
                    pneumatic_dP1_vert_output.setText(dP1v.toString());
                    pneumatic_dP2_vert_output.setText(dP2v.toString());
                    pneumatic_dP3_vert_output.setText(dP3v.toString());
                    pneumatic_dP4_vert_output.setText(dP4v.toString());
                    pneumatic_dP5_vert_output.setText(dP5v.toString());
                    pneumatic_dP6_vert_output.setText(dP6v.toString());
                    pneumatic_dPt_vert_output.setText(dPv.toString());
                    pneumatic_dPb_output.setText(dPb.toString());
                    pneumatic_dPtotal_output.setText(dPtotal.toString());
                }
            }
        });

        TextView pneumatic_D = (TextView) findViewById(R.id.pneumatic_D);
        pneumatic_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Pipe internal diameter D (in m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_e = (TextView) findViewById(R.id.pneumatic_e);
        pneumatic_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Pipe internal roughness e (in m)\nUse 0 m for smooth pipes", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_rhop = (TextView) findViewById(R.id.pneumatic_rhop);
        pneumatic_rhop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Particle density \u03c1\u209a (in kg/m\u00b3)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_rhof = (TextView) findViewById(R.id.pneumatic_rhof);
        pneumatic_rhof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Fluid density \u03c1 (in kg/m\u00b3)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Mp = (TextView) findViewById(R.id.pneumatic_Mp);
        pneumatic_Mp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Desired particle mass flow rate M\u209a (in kg/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_x = (TextView) findViewById(R.id.pneumatic_x);
        pneumatic_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Particle diameter x (in m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_mu = (TextView) findViewById(R.id.pneumatic_mu);
        pneumatic_mu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Fluid viscosity \u03bc (in Pa s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Lh = (TextView) findViewById(R.id.pneumatic_Lh);
        pneumatic_Lh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Total horizontal pipe length L\u2095 (in m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Lv = (TextView) findViewById(R.id.pneumatic_Lv);
        pneumatic_Lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Total horizontal pipe length L\u1d65 (in m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_initaccel = (TextView) findViewById(R.id.pneumatic_initaccel);
        pneumatic_initaccel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Initial direction of acceleration in pipe\n(horizontal or vertical)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_bends = (TextView) findViewById(R.id.pneumatic_bends);
        pneumatic_bends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Number of 90\u00b0 bends", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_margin = (TextView) findViewById(R.id.pneumatic_margin);
        pneumatic_margin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Amount of safety margin for superficial fluid velocity Ufs (in %)" +
                        "\nUse 50% so that Ufs is 50% higher than the saltation/choking velocity required for safe operation.", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Gp = (TextView) findViewById(R.id.pneumatic_Gp);
        pneumatic_Gp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Particle mass flux (in kg/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Usalt = (TextView) findViewById(R.id.pneumatic_Usalt);
        pneumatic_Usalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Saltation velocity (in horizontal pipes) (in m/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_UCH = (TextView) findViewById(R.id.pneumatic_UCH);
        pneumatic_UCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Choking velocity (in vertical pipes) (in m/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_voidCH = (TextView) findViewById(R.id.pneumatic_voidCH);
        pneumatic_voidCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Choking voidage (dimensionless, 0 to 1)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Ufs = (TextView) findViewById(R.id.pneumatic_Ufs);
        pneumatic_Ufs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Minimum fluid superficial velocity (incl. safety margin) (in m/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_horiz_header = (TextView) findViewById(R.id.pneumatic_horiz_header);
        pneumatic_horiz_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Calculations for horizontal pipes", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Uph = (TextView) findViewById(R.id.pneumatic_Uph);
        pneumatic_Uph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Horizontal particle velocity (in m/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_voidh = (TextView) findViewById(R.id.pneumatic_voidh);
        pneumatic_voidh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Horizontal pipe voidage (dimensionless, 0 to 1)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Ufh = (TextView) findViewById(R.id.pneumatic_Ufh);
        pneumatic_Ufh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Horizontal fluid velocity (in m/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Rep = (TextView) findViewById(R.id.pneumatic_Rep);
        pneumatic_Rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Particle Reynolds number\nRe\u209a=\u03c1(Uf-Up)x/\u03bC (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_CD = (TextView) findViewById(R.id.pneumatic_CD);
        pneumatic_CD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Particle-fluid drag coefficient (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_fp = (TextView) findViewById(R.id.pneumatic_fp);
        pneumatic_fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Particle friction factor (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Fpwh = (TextView) findViewById(R.id.pneumatic_Fpwh);
        pneumatic_Fpwh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Horizontal particle-wall friction pressure drop per length (in Pa/m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Ref = (TextView) findViewById(R.id.pneumatic_Ref);
        pneumatic_Ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Fluid Reynolds number\nRe_f=\u03c1UfsD/\u03bC (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_ff = (TextView) findViewById(R.id.pneumatic_ff);
        pneumatic_ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Fluid friction factor (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Ffwh = (TextView) findViewById(R.id.pneumatic_Ffwh);
        pneumatic_Ffwh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Horizontal fluid-wall friction pressure drop per length (in Pa/m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_vert_header = (TextView) findViewById(R.id.pneumatic_vert_header);
        pneumatic_vert_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Calculations for vertical pipes", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_UT = (TextView) findViewById(R.id.pneumatic_UT);
        pneumatic_UT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Single-particle terminal velocity (in m/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_voidv = (TextView) findViewById(R.id.pneumatic_voidv);
        pneumatic_voidv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Vertical pipe voidage (dimensionless, 0 to 1)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Ufv = (TextView) findViewById(R.id.pneumatic_Ufv);
        pneumatic_Ufv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Vertical fluid velocity (in m/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Upv = (TextView) findViewById(R.id.pneumatic_Upv);
        pneumatic_Upv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Particle fluid velocity (in m/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Ffwv = (TextView) findViewById(R.id.pneumatic_Ffwv);
        pneumatic_Ffwv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Vertical fluid-wall friction pressure drop per length (in Pa/m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_Fpwv = (TextView) findViewById(R.id.pneumatic_Fpwv);
        pneumatic_Fpwv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Vertical particle-wall friction pressure drop per length (in Pa/m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_dP_header = (TextView) findViewById(R.id.pneumatic_dP_header);
        pneumatic_dP_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Pressure drop calculations", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_dP = (TextView) findViewById(R.id.pneumatic_dP);
        pneumatic_dP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Pressure drop from...", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_horiz = (TextView) findViewById(R.id.pneumatic_horiz);
        pneumatic_horiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "horizontal pipes", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_vert = (TextView) findViewById(R.id.pneumatic_vert);
        pneumatic_vert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "vertical pipes", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_dP1 = (TextView) findViewById(R.id.pneumatic_dP1);
        pneumatic_dP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Pressure drop from initial fluid acceleration\n(based on initial acceleration direction) (in Pa)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_dP2 = (TextView) findViewById(R.id.pneumatic_dP2);
        pneumatic_dP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Pressure drop from initial particle acceleration\n(based on initial acceleration direction) (in Pa)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_dP3 = (TextView) findViewById(R.id.pneumatic_dP3);
        pneumatic_dP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Pressure drop from gas-wall friction (in Pa)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_dP4 = (TextView) findViewById(R.id.pneumatic_dP4);
        pneumatic_dP4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Pressure drop from particle-wall friction (in Pa)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_dP5 = (TextView) findViewById(R.id.pneumatic_dP5);
        pneumatic_dP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Pressure drop from particle static head (in Pa)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_dP6 = (TextView) findViewById(R.id.pneumatic_dP6);
        pneumatic_dP6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Pressure drop from fluid static head (in Pa)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_dPt = (TextView) findViewById(R.id.pneumatic_dPt);
        pneumatic_dPt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Directional subtotal pressure drop (in Pa)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_dPb = (TextView) findViewById(R.id.pneumatic_dPb);
        pneumatic_dPb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Pressure drop from 90\u00b0 bends (in Pa)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pneumatic_dPtotal = (TextView) findViewById(R.id.pneumatic_dPtotal);
        pneumatic_dPtotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pneumatic.this, "Total allowable pressure drop across the pneumatic transport system (in Pa)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}