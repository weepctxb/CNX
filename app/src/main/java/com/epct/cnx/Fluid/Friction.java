package com.epct.cnx.Fluid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epct.cnx.R;

public class Friction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fluid_friction);

        Button friction_laminar_solve = (Button) findViewById(R.id.friction_laminar_solve);
        friction_laminar_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView friction_laminar_phi_input = (TextView) findViewById(R.id.friction_laminar_phi_input);
                TextView friction_laminar_Re_input = (TextView) findViewById(R.id.friction_laminar_Re_input);

                Double phi=0.0, Re=0.0;
                boolean phifilled = false, Refilled = false;

                if (friction_laminar_phi_input.getText().length() > 0) {
                    phi = Double.parseDouble(friction_laminar_phi_input.getText().toString());
                    phifilled = true;
                }
                if (friction_laminar_Re_input.getText().length() > 0) {
                    Re = Double.parseDouble(friction_laminar_Re_input.getText().toString());
                    Refilled = true;
                }

                if (phifilled && Refilled) {
                    Toast.makeText(Friction.this, "Overdetermined system!", Toast.LENGTH_SHORT).show();
                }
                else if ((!phifilled) && (!Refilled)) {
                    Toast.makeText(Friction.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                }
                else if (phifilled && (!Refilled)) {
                    // Find Re from phi
                    Re = 16.0/phi;
                    friction_laminar_Re_input.setText(Re.toString());
                }
                else if ((!phifilled) && Refilled) {
                    // Find phi from Re
                    phi = 16.0/Re;
                    friction_laminar_phi_input.setText(phi.toString());
                }
            }
        });

        Button friction_smooth_solve = (Button) findViewById(R.id.friction_smooth_solve);
        friction_smooth_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView friction_smooth_phi_input = (TextView) findViewById(R.id.friction_smooth_phi_input);
                TextView friction_smooth_Re_input = (TextView) findViewById(R.id.friction_smooth_Re_input);

                Double phi=0.0, Re=0.0;
                boolean phifilled = false, Refilled = false;

                if (friction_smooth_phi_input.getText().length() > 0) {
                    phi = Double.parseDouble(friction_smooth_phi_input.getText().toString());
                    phifilled = true;
                }
                if (friction_smooth_Re_input.getText().length() > 0) {
                    Re = Double.parseDouble(friction_smooth_Re_input.getText().toString());
                    Refilled = true;
                }

                if (phifilled && Refilled) {
                    Toast.makeText(Friction.this, "Overdetermined system!", Toast.LENGTH_SHORT).show();
                }
                else if ((!phifilled) && (!Refilled)) {
                    Toast.makeText(Friction.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                }
                else if (phifilled && (!Refilled)) {
                    // Find Re from phi
                    Double phiinv = Math.pow(phi,-0.50);
                    Re = 1.33067*Math.exp(0.571361*phiinv)*phiinv;
                    friction_smooth_Re_input.setText(Re.toString());
                }
                else if ((!phifilled) && Refilled) {
                    // Find phi from Re
                    phi = 1e-3;
                    Double phiinv = Math.pow(phi,-0.50);
                    Double phiinv0 = 0.5;
                    int iter = 0;
                    while (Math.abs(phiinv-phiinv0)/phiinv0>1e-9 & iter<1000) {
                        phiinv0 = phiinv;
                        phiinv = 4.03*Math.log10(Re/phiinv)-0.50;
                        iter = iter+1;
                    }
                    phi = Math.pow(phiinv,-2.0);
                    Toast.makeText(Friction.this, "Iterative calculation for friction factor stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                    friction_smooth_phi_input.setText(phi.toString());
                }
            }
        });

        Button friction_blasius_solve = (Button) findViewById(R.id.friction_blasius_solve);
        friction_blasius_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView friction_blasius_phi_input = (TextView) findViewById(R.id.friction_blasius_phi_input);
                TextView friction_blasius_Re_input = (TextView) findViewById(R.id.friction_blasius_Re_input);

                Double phi=0.0, Re=0.0;
                boolean phifilled = false, Refilled = false;

                if (friction_blasius_phi_input.getText().length() > 0) {
                    phi = Double.parseDouble(friction_blasius_phi_input.getText().toString());
                    phifilled = true;
                }
                if (friction_blasius_Re_input.getText().length() > 0) {
                    Re = Double.parseDouble(friction_blasius_Re_input.getText().toString());
                    Refilled = true;
                }

                if (phifilled && Refilled) {
                    Toast.makeText(Friction.this, "Overdetermined system!", Toast.LENGTH_SHORT).show();
                }
                else if ((!phifilled) && (!Refilled)) {
                    Toast.makeText(Friction.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                }
                else if (phifilled && (!Refilled)) {
                    // Find Re from phi
                    Re = Math.pow(0.079/phi,4.0);
                    friction_blasius_Re_input.setText(Re.toString());
                }
                else if ((!phifilled) && Refilled) {
                    // Find phi from Re
                    phi = 0.079*Math.pow(Re,-0.25);
                    friction_blasius_phi_input.setText(phi.toString());
                }
            }
        });

        Button friction_rough_solve = (Button) findViewById(R.id.friction_rough_solve);
        friction_rough_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView friction_rough_phi_input = (TextView) findViewById(R.id.friction_rough_phi_input);
                TextView friction_rough_eD_input = (TextView) findViewById(R.id.friction_rough_eD_input);

                Double phi=0.0, eD=0.0;
                boolean phifilled = false, eDfilled = false;

                if (friction_rough_phi_input.getText().length() > 0) {
                    phi = Double.parseDouble(friction_rough_phi_input.getText().toString());
                    phifilled = true;
                }
                if (friction_rough_eD_input.getText().length() > 0) {
                    eD = Double.parseDouble(friction_rough_eD_input.getText().toString());
                    eDfilled = true;
                }

                if (phifilled && eDfilled) {
                    Toast.makeText(Friction.this, "Overdetermined system!", Toast.LENGTH_SHORT).show();
                }
                else if ((!phifilled) && (!eDfilled)) {
                    Toast.makeText(Friction.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                }
                else if (phifilled && (!eDfilled)) {
                    // Find e/D from phi
                    Double phiinv = Math.pow(phi,-0.50);
                    eD = Math.pow(10.0,0.569125-0.25*phiinv);
                    friction_rough_eD_input.setText(eD.toString());
                }
                else if ((!phifilled) && eDfilled) {
                    // Find phi from e/D
                    Double phiinv = 4.0*Math.log10(1.0/eD)+2.2765;
                    phi = Math.pow(phiinv,-2.0);
                    friction_rough_phi_input.setText(phi.toString());
                }
            }
        });

        Button friction_colebrook_solve = (Button) findViewById(R.id.friction_colebrook_solve);
        friction_colebrook_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView friction_colebrook_phi_input = (TextView) findViewById(R.id.friction_colebrook_phi_input);
                TextView friction_colebrook_Re_input = (TextView) findViewById(R.id.friction_colebrook_Re_input);
                TextView friction_colebrook_eD_input = (TextView) findViewById(R.id.friction_colebrook_eD_input);

                Double phi=0.0, Re=0.0, eD=0.0;
                boolean phifilled = false, Refilled = false, eDfilled = false;
                int filled = 0;

                if (friction_colebrook_phi_input.getText().length() > 0) {
                    phi = Double.parseDouble(friction_colebrook_phi_input.getText().toString());
                    phifilled = true;
                    filled = filled + 1;
                }
                if (friction_colebrook_Re_input.getText().length() > 0) {
                    Re = Double.parseDouble(friction_colebrook_Re_input.getText().toString());
                    Refilled = true;
                    filled = filled + 1;
                }
                if (friction_colebrook_eD_input.getText().length() > 0) {
                    eD = Double.parseDouble(friction_colebrook_eD_input.getText().toString());
                    eDfilled = true;
                    filled = filled + 1;
                }

                if (filled == 3) {
                    Toast.makeText(Friction.this, "Overdetermined system!", Toast.LENGTH_SHORT).show();
                }
                else if (filled < 2) {
                    Toast.makeText(Friction.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Refilled && eDfilled) {
                        // Find phi from Re and e/D
                        phi = 1e-3;
                        Double phiinv = Math.pow(phi,-0.50);
                        Double phiinv0 = 0.5;
                        int iter = 0;
                        while (Math.abs(phiinv-phiinv0)/phiinv0>1e-9 & iter<1000) {
                            phiinv0 = phiinv;
                            phiinv = -4.0*Math.log10(eD/3.715+1.255*phiinv/Re);
                            iter = iter+1;
                        }
                        phi = Math.pow(phiinv,-2.0);
                        Toast.makeText(Friction.this, "Iterative calculation for friction factor stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                        friction_colebrook_phi_input.setText(phi.toString());
                    }
                    else if (Refilled && phifilled) {
                        // Find e/D from Re and phi
                        Double phiinv = Math.pow(phi,-0.50);
                        eD = -0.018575*Math.exp(-0.57565*phiinv)*(251.0*Math.pow(10.0,0.25*phiinv)*phiinv-200.0*Re)/Re;
                        friction_colebrook_eD_input.setText(eD.toString());
                    }
                    else if (eDfilled && phifilled) {
                        // Find Re from phi and e/D
                        Double phiinv = Math.pow(phi,-0.50);
                        Re = (-186493.0*Math.pow(2.0,phiinv/4.0-3.0)*Math.pow(5.0,phiinv/4.0-2.0)*phiinv) / (Math.pow(2.0,phiinv/4.0+3.0)*Math.pow(5.0,phiinv/4.0+2.0)*eD-743.0);
                        friction_colebrook_Re_input.setText(Re.toString());
                    }
                }
            }
        });

        Button friction_chen_solve = (Button) findViewById(R.id.friction_chen_solve);
        friction_chen_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView friction_chen_phi_input = (TextView) findViewById(R.id.friction_chen_phi_input);
                TextView friction_chen_Re_input = (TextView) findViewById(R.id.friction_chen_Re_input);
                TextView friction_chen_eD_input = (TextView) findViewById(R.id.friction_chen_eD_input);

                Double phi=0.0, Re=0.0, eD=0.0;
                boolean phifilled = false, Refilled = false, eDfilled = false;
                int filled = 0;

                if (friction_chen_phi_input.getText().length() > 0) {
                    phi = Double.parseDouble(friction_chen_phi_input.getText().toString());
                    phifilled = true;
                    filled = filled + 1;
                }
                if (friction_chen_Re_input.getText().length() > 0) {
                    Re = Double.parseDouble(friction_chen_Re_input.getText().toString());
                    Refilled = true;
                    filled = filled + 1;
                }
                if (friction_chen_eD_input.getText().length() > 0) {
                    eD = Double.parseDouble(friction_chen_eD_input.getText().toString());
                    eDfilled = true;
                    filled = filled + 1;
                }

                if (filled == 3) {
                    Toast.makeText(Friction.this, "Overdetermined system!", Toast.LENGTH_SHORT).show();
                }
                else if (filled < 2) {
                    Toast.makeText(Friction.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (Refilled && eDfilled) {
                        // Find phi from Re and e/D
                        Double A = eD/3.7065,
                                B = 5.0452/Re,
                                C = Math.pow(eD,1.1098)/2.8257,
                                D = Math.pow(7.149/Re,0.8981);
                        Double phiinv = -4.0*Math.log10(A-B*Math.log10(C+D));
                        phi = Math.pow(phiinv, -2.0);
                        friction_chen_phi_input.setText(phi.toString());
                    }
                    else if (Refilled && phifilled) {
                        // Find e/D from Re and phi
                        Double phiinv = Math.pow(phi, -0.5);
                        Double eDleft = 1e-9, eDright = 1e-1;
                        int iter = 0;
                        while (Math.abs(eDright-eDleft)/eDleft>1e-9 && iter<1000) {
                            eD = (eDleft+eDright)/2.0;
                            Double A = eD/3.7065,
                                    B = 5.0452/Re,
                                    C = Math.pow(eD,1.1098)/2.8257,
                                    D = Math.pow(7.149/Re,0.8981);
                            Double phiinvtest = -4.0*Math.log10(A-B*Math.log10(C+D));
                            if (phiinvtest<phiinv) {
                                // if iterated phi is too high, reduce e/D
                                eDright = eD;
                            } else {
                                // if iterated phi is too low, increase e/D
                                eDleft = eD;
                            }
                            iter = iter + 1;
                        }
                        Toast.makeText(Friction.this, "Iterative calculation for relative roughness stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                        friction_chen_eD_input.setText(eD.toString());
                    }
                    else if (eDfilled && phifilled) {
                        // Find Re from phi and e/D
                        Double phiinv = Math.pow(phi, -0.5);
                        Double Releft = 1e3, Reright = 1e10;
                        int iter = 0;
                        while (Math.abs(Reright-Releft)/Releft>1e-9 && iter<1000) {
                            Re = (Releft+Reright)/2.0;
                            Double A = eD/3.7065,
                                    B = 5.0452/Re,
                                    C = Math.pow(eD,1.1098)/2.8257,
                                    D = Math.pow(7.149/Re,0.8981);
                            Double phiinvtest = -4.0*Math.log10(A-B*Math.log10(C+D));
                            if (phiinvtest>phiinv) {
                                // if iterated phi is too low, reduce Re
                                Reright = Re;
                            } else {
                                // if iterated phi is too high, increase Re
                                Releft = Re;
                            }
                            iter = iter + 1;
                        }
                        Toast.makeText(Friction.this, "Iterative calculation for Reynolds number stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                        friction_chen_Re_input.setText(Re.toString());
                    }
                }
            }
        });

        TextView friction_laminar_phi = (TextView) findViewById(R.id.friction_laminar_phi);
        friction_laminar_phi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Fanning friction factor \u03C6", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_laminar_Re = (TextView) findViewById(R.id.friction_laminar_Re);
        friction_laminar_Re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Fluid Reynolds number Re", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_smooth_phi = (TextView) findViewById(R.id.friction_smooth_phi);
        friction_smooth_phi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Fanning friction factor \u03C6", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_smooth_Re = (TextView) findViewById(R.id.friction_smooth_Re);
        friction_smooth_Re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Fluid Reynolds number Re", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_blasius_phi = (TextView) findViewById(R.id.friction_blasius_phi);
        friction_blasius_phi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Fanning friction factor \u03C6", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_blasius_Re = (TextView) findViewById(R.id.friction_blasius_Re);
        friction_blasius_Re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Fluid Reynolds number Re", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_rough_phi = (TextView) findViewById(R.id.friction_rough_phi);
        friction_rough_phi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Fanning friction factor \u03C6", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_rough_eD = (TextView) findViewById(R.id.friction_rough_eD);
        friction_rough_eD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Pipe relative roughness e/D", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_colebrook_phi = (TextView) findViewById(R.id.friction_colebrook_phi);
        friction_colebrook_phi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Fanning friction factor \u03C6", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_colebrook_Re = (TextView) findViewById(R.id.friction_colebrook_Re);
        friction_colebrook_Re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Fluid Reynolds number Re", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_colebrook_eD = (TextView) findViewById(R.id.friction_colebrook_eD);
        friction_colebrook_eD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Pipe relative roughness e/D", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_chen_phi = (TextView) findViewById(R.id.friction_chen_phi);
        friction_chen_phi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Fanning friction factor \u03C6", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_chen_Re = (TextView) findViewById(R.id.friction_chen_Re);
        friction_chen_Re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Fluid Reynolds number Re", Toast.LENGTH_SHORT).show();
            }
        });

        TextView friction_chen_eD = (TextView) findViewById(R.id.friction_chen_eD);
        friction_chen_eD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Friction.this, "Pipe relative roughness e/D", Toast.LENGTH_SHORT).show();
            }
        });
    }
}