package com.epct.cnx.Kinetic;

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

public class Single extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kinetic_single);

        Spinner spinner_single_pfr_n = (Spinner) findViewById(R.id.single_pfr_n_input);
        ArrayAdapter<CharSequence> adapter_single_pfr_n = ArrayAdapter.createFromResource(this,
                R.array.single_reactor_n, android.R.layout.simple_spinner_item);
        adapter_single_pfr_n.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_single_pfr_n.setAdapter(adapter_single_pfr_n);

        Spinner spinner_single_cstr_n = (Spinner) findViewById(R.id.single_cstr_n_input);
        ArrayAdapter<CharSequence> adapter_single_cstr_n = ArrayAdapter.createFromResource(this,
                R.array.single_reactor_n, android.R.layout.simple_spinner_item);
        adapter_single_cstr_n.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_single_cstr_n.setAdapter(adapter_single_cstr_n);

        Button single_pfr_solve = (Button) findViewById(R.id.single_pfr_solve);
        single_pfr_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner_single_pfr_n = (Spinner) findViewById(R.id.single_pfr_n_input);
                TextView single_pfr_ea_input = (TextView) findViewById(R.id.single_pfr_ea_input);
                TextView single_pfr_k_input = (TextView) findViewById(R.id.single_pfr_k_input);
                TextView single_pfr_cA0_input = (TextView) findViewById(R.id.single_pfr_cA0_input);
                TextView single_pfr_cA_input = (TextView) findViewById(R.id.single_pfr_cA_input);
                TextView single_pfr_X_input = (TextView) findViewById(R.id.single_pfr_X_input);
                TextView single_pfr_tau_input = (TextView) findViewById(R.id.single_pfr_tau_input);

                Boolean eafilled, kfilled, cA0filled, cAfilled, Xfilled, taufilled;
                Double ea=-1.0, k=-1.0, cA0=-1.0, cA=-1.0, X=-1.0, tau=-1.0;

                if (single_pfr_ea_input.getText().length() > 0) {
                    ea = Double.parseDouble(single_pfr_ea_input.getText().toString());
                    eafilled = true;
                } else {
                    ea = 0.0;
                    single_pfr_ea_input.setText("0.0");
                    eafilled = false;
                }
                if (single_pfr_k_input.getText().length() > 0) {
                    k = Double.parseDouble(single_pfr_k_input.getText().toString());
                    kfilled = true;
                } else {
                    kfilled = false;
                }
                if (single_pfr_cA_input.getText().length() > 0) {
                    cA = Double.parseDouble(single_pfr_cA_input.getText().toString());
                    cAfilled = true;
                } else {
                    cAfilled = false;
                }
                if (single_pfr_cA0_input.getText().length() > 0) {
                    cA0 = Double.parseDouble(single_pfr_cA0_input.getText().toString());
                    cA0filled = true;
                } else {
                    cA0filled = false;
                }
                if (single_pfr_X_input.getText().length() > 0) {
                    X = Double.parseDouble(single_pfr_X_input.getText().toString());
                    Xfilled = true;
                } else {
                    Xfilled = false;
                }
                if (single_pfr_tau_input.getText().length() > 0) {
                    tau = Double.parseDouble(single_pfr_tau_input.getText().toString());
                    taufilled = true;
                } else {
                    taufilled = false;
                }

                if (kfilled && cA0filled && (cAfilled ^ Xfilled) && !taufilled) {
                    if (cAfilled) {
                        // Use cA0 and cA to find t/tau and X
                        X = (cA0-cA)/cA0;
                        switch (spinner_single_pfr_n.getSelectedItemPosition()) {
                            case 0: //n=0
                                tau = X*cA0/k;
                                break;
                            case 1: //n=1
                                tau = ((1.0+ea)*Math.log(1.0/(1.0-X))-ea*X)/k;
                                break;
                            case 2: //n=2
                                tau = (2.0*ea*(1.0+ea)*Math.log(1.0-X)
                                        +ea*ea*X+Math.pow(1.0+ea,2.0)*X/(1.0-X))/(k*cA0);
                                break;
                        }
                        single_pfr_X_input.setText(X.toString());
                        single_pfr_tau_input.setText(tau.toString());
                    }
                    else {
                        // Use cA0 and X to find t/tau and cA
                        cA = (1.0-X)*cA0;
                        switch (spinner_single_pfr_n.getSelectedItemPosition()) {
                            case 0: //n=0
                                tau = X*cA0/k;
                                break;
                            case 1: //n=1
                                tau = ((1.0+ea)*Math.log(1.0/(1.0-X))-ea*X)/k;
                                break;
                            case 2: //n=2
                                tau = (2.0*ea*(1.0+ea)*Math.log(1.0-X)
                                        +ea*ea*X+Math.pow(1.0+ea,2.0)*X/(1.0-X))/(k*cA0);
                                break;
                        }
                        single_pfr_cA_input.setText(cA.toString());
                        single_pfr_tau_input.setText(tau.toString());
                    }
                }
                else if (kfilled && cA0filled && taufilled && !cAfilled && !Xfilled) {
                    Double ktauRHS, ktauCA0RHS, Xleft, Xright;
                    int iter;
                    // Use cA0 and t/tau to find cA and X
                    switch (spinner_single_pfr_n.getSelectedItemPosition()) {
                        case 0: //n=0
                            X = k*tau/cA0;
                            break;
                        case 1: //n=1
                            ktauRHS = -1.0; Xleft = 0.0; Xright = 1.0;
                            iter = 0;
                            while (Math.abs(Xright-Xleft)/Xleft>1e-9 && iter<1000) {
                                X = (Xleft+Xright)/2.0;
                                ktauRHS = (1.0+ea)*Math.log(1.0/(1.0-X))-ea*X;
                                if (ktauRHS<k*tau) {
                                    // if iterated k*tau is too low, increase X
                                    Xleft = X;
                                } else {
                                    // if iterated k*tau is too high, decrease X
                                    Xright = X;
                                }
                                iter = iter + 1;
                            }
                            Toast.makeText(Single.this, "Iterative calculation for X stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                            break;
                        case 2: //n=2
                            ktauCA0RHS = -1.0; Xleft = 0.0; Xright = 1.0;
                            iter = 0;
                            while (Math.abs(Xright-Xleft)/Xleft>1e-9 && iter<1000) {
                                X = (Xleft+Xright)/2.0;
                                ktauCA0RHS = 2.0*ea*(1.0+ea)*Math.log(1.0-X)
                                        +ea*ea*X+Math.pow(ea+1.0,2.0)*X/(1.0-X);
                                if (ktauCA0RHS<k*tau*cA0) {
                                    // if iterated k*tau is too low, increase X
                                    Xleft = X;
                                } else {
                                    // if iterated k*tau is too high, decrease X
                                    Xright = X;
                                }
                                iter = iter + 1;
                            }
                            Toast.makeText(Single.this, "Iterative calculation for X stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                            break;
                    }
                    cA = (1.0-X)*cA0;
                    single_pfr_X_input.setText(X.toString());
                    single_pfr_cA_input.setText(cA.toString());
                }
                else {
                    Toast.makeText(Single.this, "Invalid input - please check that you've only input:\n1. k, c\u2090\u2080 and (c\u2090 or X) - to find (t or \u03c4); or\n2. k, c\u2090\u2080 and (t or \u03c4) - to find (c\u2090 or X)\n\u03b5\u2090 is optional (if left blank, this is assumed zero)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button single_cstr_solve = (Button) findViewById(R.id.single_cstr_solve);
        single_cstr_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner_single_cstr_n = (Spinner) findViewById(R.id.single_cstr_n_input);
                TextView single_cstr_ea_input = (TextView) findViewById(R.id.single_cstr_ea_input);
                TextView single_cstr_k_input = (TextView) findViewById(R.id.single_cstr_k_input);
                TextView single_cstr_cA0_input = (TextView) findViewById(R.id.single_cstr_cA0_input);
                TextView single_cstr_cA_input = (TextView) findViewById(R.id.single_cstr_cA_input);
                TextView single_cstr_X_input = (TextView) findViewById(R.id.single_cstr_X_input);
                TextView single_cstr_tau_input = (TextView) findViewById(R.id.single_cstr_tau_input);

                Boolean eafilled, kfilled, cA0filled, cAfilled, Xfilled, taufilled;
                Double ea=-1.0, k=-1.0, cA0=-1.0, cA=-1.0, X=-1.0, tau=-1.0;

                if (single_cstr_ea_input.getText().length() > 0) {
                    ea = Double.parseDouble(single_cstr_ea_input.getText().toString());
                    eafilled = true;
                } else {
                    ea = 0.0;
                    single_cstr_ea_input.setText("0.0");
                    eafilled = false;
                }
                if (single_cstr_k_input.getText().length() > 0) {
                    k = Double.parseDouble(single_cstr_k_input.getText().toString());
                    kfilled = true;
                } else {
                    kfilled = false;
                }
                if (single_cstr_cA_input.getText().length() > 0) {
                    cA = Double.parseDouble(single_cstr_cA_input.getText().toString());
                    cAfilled = true;
                } else {
                    cAfilled = false;
                }
                if (single_cstr_cA0_input.getText().length() > 0) {
                    cA0 = Double.parseDouble(single_cstr_cA0_input.getText().toString());
                    cA0filled = true;
                } else {
                    cA0filled = false;
                }
                if (single_cstr_X_input.getText().length() > 0) {
                    X = Double.parseDouble(single_cstr_X_input.getText().toString());
                    Xfilled = true;
                } else {
                    Xfilled = false;
                }
                if (single_cstr_tau_input.getText().length() > 0) {
                    tau = Double.parseDouble(single_cstr_tau_input.getText().toString());
                    taufilled = true;
                } else {
                    taufilled = false;
                }

                if (kfilled && cA0filled && (cAfilled ^ Xfilled) && !taufilled) {
                    if (cAfilled) {
                        // Use cA0 and cA to find t/tau and X
                        X = (cA0-cA)/cA0;
                        switch (spinner_single_cstr_n.getSelectedItemPosition()) {
                            case 0: //n=0
                                tau = X*cA0/k;
                                break;
                            case 1: //n=1
                                tau = X*(1.0+ea*X)/(1.0-X)/k;
                                break;
                            case 2: //n=2
                                tau = X*Math.pow(1.0+ea*X,2.0)/Math.pow(1.0-X,2.0)/(k*cA0);
                                break;
                        }
                        single_cstr_X_input.setText(X.toString());
                        single_cstr_tau_input.setText(tau.toString());
                    }
                    else {
                        // Use cA0 and X to find t/tau and cA
                        cA = (1.0-X)*cA0;
                        switch (spinner_single_cstr_n.getSelectedItemPosition()) {
                            case 0: //n=0
                                tau = X*cA0/k;
                                break;
                            case 1: //n=1
                                tau = X*(1.0+ea*X)/(1.0-X)/k;
                                break;
                            case 2: //n=2
                                tau = X*Math.pow(1.0+ea*X,2.0)/Math.pow(1.0-X,2.0)/(k*cA0);
                                break;
                        }
                        single_cstr_cA_input.setText(cA.toString());
                        single_cstr_tau_input.setText(tau.toString());
                    }
                }
                else if (kfilled && cA0filled && taufilled && !cAfilled && !Xfilled) {
                    Double ktauRHS, ktauCA0RHS, Xleft, Xright;
                    int iter;
                    // Use cA0 and t/tau to find cA and X
                    switch (spinner_single_cstr_n.getSelectedItemPosition()) {
                        case 0: //n=0
                            X = k*tau/cA0;
                            break;
                        case 1: //n=1
                            ktauRHS = -1.0; Xleft = 0.0; Xright = 1.0;
                            iter = 0;
                            while (Math.abs(Xright-Xleft)/Xleft>1e-9 && iter<1000) {
                                X = (Xleft+Xright)/2.0;
                                ktauRHS = X*(1.0+ea*X)/(1.0-X);
                                if (ktauRHS<k*tau) {
                                    // if iterated k*tau is too low, increase X
                                    Xleft = X;
                                } else {
                                    // if iterated k*tau is too high, decrease X
                                    Xright = X;
                                }
                                iter = iter + 1;
                            }
                            Toast.makeText(Single.this, "Iterative calculation for X stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                            break;
                        case 2: //n=2
                            ktauCA0RHS = -1.0; Xleft = 0.0; Xright = 1.0;
                            iter = 0;
                            while (Math.abs(Xright-Xleft)/Xleft>1e-9 && iter<1000) {
                                X = (Xleft+Xright)/2.0;
                                ktauCA0RHS = X*Math.pow(1.0+ea*X,2.0)/Math.pow(1.0-X,2.0);
                                if (ktauCA0RHS<k*tau*cA0) {
                                    // if iterated k*tau is too low, increase X
                                    Xleft = X;
                                } else {
                                    // if iterated k*tau is too high, decrease X
                                    Xright = X;
                                }
                                iter = iter + 1;
                            }
                            Toast.makeText(Single.this, "Iterative calculation for X stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                            break;
                    }
                    cA = (1.0-X)*cA0;
                    single_cstr_X_input.setText(X.toString());
                    single_cstr_cA_input.setText(cA.toString());
                }
                else {
                    Toast.makeText(Single.this, "Invalid input - please check that you've only input:\n1. k, c\u2090\u2080 and (c\u2090 or X) - to find (t or \u03c4); or\n2. k, c\u2090\u2080 and (t or \u03c4) - to find (c\u2090 or X)\n\u03b5\u2090 is optional (if left blank, this is assumed zero)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView single_pfr_n = (TextView) findViewById(R.id.single_pfr_n);
        single_pfr_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Order of reaction (n=0,1,2)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView single_pfr_ea = (TextView) findViewById(R.id.single_pfr_ea);
        single_pfr_ea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Fractional change in volume of system.\n\u03b5\u2090 can be zero (constant density), negative or positive (variable density).", Toast.LENGTH_SHORT).show();
            }
        });
        TextView single_pfr_k = (TextView) findViewById(R.id.single_pfr_k);
        single_pfr_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Reaction rate constant k", Toast.LENGTH_SHORT).show();
            }
        });
        TextView single_pfr_cA0 = (TextView) findViewById(R.id.single_pfr_cA0);
        single_pfr_cA0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Inlet feed concentration c\u2090\u2080", Toast.LENGTH_SHORT).show();
            }
        });
        TextView single_pfr_cA = (TextView) findViewById(R.id.single_pfr_cA);
        single_pfr_cA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Outlet product concentration c\u2090", Toast.LENGTH_SHORT).show();
            }
        });
        TextView single_pfr_X = (TextView) findViewById(R.id.single_pfr_X);
        single_pfr_X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Reactor conversion X", Toast.LENGTH_SHORT).show();
            }
        });
        TextView single_pfr_tau = (TextView) findViewById(R.id.single_pfr_tau);
        single_pfr_tau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Reactor spacetime (t for batch, \u03c4 for PFR)", Toast.LENGTH_SHORT).show();
            }
        });
        
        TextView single_cstr_n = (TextView) findViewById(R.id.single_cstr_n);
        single_cstr_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Order of reaction (n=0,1,2)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView single_cstr_ea = (TextView) findViewById(R.id.single_cstr_ea);
        single_cstr_ea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Fractional change in volume of system.\n\u03b5\u2090 can be zero (constant density), negative or positive (variable density).", Toast.LENGTH_SHORT).show();
            }
        });
        TextView single_cstr_k = (TextView) findViewById(R.id.single_cstr_k);
        single_cstr_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Reaction rate constant k", Toast.LENGTH_SHORT).show();
            }
        });
        TextView single_cstr_cA0 = (TextView) findViewById(R.id.single_cstr_cA0);
        single_cstr_cA0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Inlet feed concentration c\u2090\u2080", Toast.LENGTH_SHORT).show();
            }
        });
        TextView single_cstr_cA = (TextView) findViewById(R.id.single_cstr_cA);
        single_cstr_cA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Outlet product concentration c\u2090", Toast.LENGTH_SHORT).show();
            }
        });
        TextView single_cstr_X = (TextView) findViewById(R.id.single_cstr_X);
        single_cstr_X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Reactor conversion X", Toast.LENGTH_SHORT).show();
            }
        });
        TextView single_cstr_tau = (TextView) findViewById(R.id.single_cstr_tau);
        single_cstr_tau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Single.this, "Reactor spacetime \u03c4", Toast.LENGTH_SHORT).show();
            }
        });
    }
}