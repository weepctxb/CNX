package com.epct.cnx.Thermo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.epct.cnx.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class XVirial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thermo_xvirial);

        final List<String[]> compounds = new ArrayList<>();
        Runnable compounds_runnable = new Runnable() {
            @Override
            public void run() {
                String[] compounds_row;
                try {
                    InputStream compounds_inputStream = getAssets().open("Compounds.csv");
                    BufferedReader compounds_buffrd = new BufferedReader(new InputStreamReader(compounds_inputStream));
                    String compounds_line = "";
                    while((compounds_line = compounds_buffrd.readLine()) != null){
                        compounds_row = compounds_line.split(",");
                        compounds.add(compounds_row);
                    }
                    compounds_buffrd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(compounds_runnable).start();

        Spinner spinner_xvirial_preset1 = (Spinner) findViewById(R.id.xvirial_preset1_input);
        ArrayAdapter<CharSequence> adapter_xvirial_preset1 = ArrayAdapter.createFromResource(this,
                R.array.compound_presets, android.R.layout.simple_spinner_item);
        adapter_xvirial_preset1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_xvirial_preset1.setAdapter(adapter_xvirial_preset1);
        spinner_xvirial_preset1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    TextView xvirial_w11_input = (TextView) findViewById(R.id.xvirial_w11_input);
                    TextView xvirial_tc11_input = (TextView) findViewById(R.id.xvirial_tc11_input);
                    TextView xvirial_pc11_input = (TextView) findViewById(R.id.xvirial_pc11_input);
                    TextView xvirial_vc11_input = (TextView) findViewById(R.id.xvirial_vc11_input);
                    TextView xvirial_zc11_input = (TextView) findViewById(R.id.xvirial_zc11_input);

                    Double tc = Double.parseDouble(compounds.get(pos)[1]);
                    if (Math.abs(tc+999.0)>0.001) {
                        xvirial_tc11_input.setText(tc.toString());
                    }
                    else {
                        xvirial_tc11_input.setText("");
                    }
                    Double pc = Double.parseDouble(compounds.get(pos)[2]);
                    if (Math.abs(pc+999.0)>0.001) {
                        xvirial_pc11_input.setText(pc.toString());
                    }
                    else {
                        xvirial_pc11_input.setText("");
                    }
                    Double w = Double.parseDouble(compounds.get(pos)[3]);
                    if (Math.abs(w+999.0)>0.001) {
                        xvirial_w11_input.setText(w.toString());
                    }
                    else {
                        xvirial_w11_input.setText("");
                    }
                    Double vc = Double.parseDouble(compounds.get(pos)[4]);
                    if (Math.abs(vc+999.0)>0.001) {
                        xvirial_vc11_input.setText(vc.toString());
                    }
                    else {
                        xvirial_vc11_input.setText("");
                    }
                    Double zc = Double.parseDouble(compounds.get(pos)[5]);
                    if (Math.abs(zc+999.0)>0.001) {
                        xvirial_zc11_input.setText(zc.toString());
                    }
                    else {
                        xvirial_zc11_input.setText("");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Spinner spinner_xvirial_preset2 = (Spinner) findViewById(R.id.xvirial_preset2_input);
        ArrayAdapter<CharSequence> adapter_xvirial_preset2 = ArrayAdapter.createFromResource(this,
                R.array.compound_presets, android.R.layout.simple_spinner_item);
        adapter_xvirial_preset2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_xvirial_preset2.setAdapter(adapter_xvirial_preset2);
        spinner_xvirial_preset2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    TextView xvirial_w22_input = (TextView) findViewById(R.id.xvirial_w22_input);
                    TextView xvirial_tc22_input = (TextView) findViewById(R.id.xvirial_tc22_input);
                    TextView xvirial_pc22_input = (TextView) findViewById(R.id.xvirial_pc22_input);
                    TextView xvirial_vc22_input = (TextView) findViewById(R.id.xvirial_vc22_input);
                    TextView xvirial_zc22_input = (TextView) findViewById(R.id.xvirial_zc22_input);

                    Double tc = Double.parseDouble(compounds.get(pos)[1]);
                    if (Math.abs(tc+999.0)>0.001) {
                        xvirial_tc22_input.setText(tc.toString());
                    }
                    else {
                        xvirial_tc22_input.setText("");
                    }
                    Double pc = Double.parseDouble(compounds.get(pos)[2]);
                    if (Math.abs(pc+999.0)>0.001) {
                        xvirial_pc22_input.setText(pc.toString());
                    }
                    else {
                        xvirial_pc22_input.setText("");
                    }
                    Double w = Double.parseDouble(compounds.get(pos)[3]);
                    if (Math.abs(w+999.0)>0.001) {
                        xvirial_w22_input.setText(w.toString());
                    }
                    else {
                        xvirial_w22_input.setText("");
                    }
                    Double vc = Double.parseDouble(compounds.get(pos)[4]);
                    if (Math.abs(vc+999.0)>0.001) {
                        xvirial_vc22_input.setText(vc.toString());
                    }
                    else {
                        xvirial_vc22_input.setText("");
                    }
                    Double zc = Double.parseDouble(compounds.get(pos)[5]);
                    if (Math.abs(zc+999.0)>0.001) {
                        xvirial_zc22_input.setText(zc.toString());
                    }
                    else {
                        xvirial_zc22_input.setText("");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Button xvirial_solve = (Button) findViewById(R.id.xvirial_solve);
        xvirial_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView xvirial_t_input = (TextView) findViewById(R.id.xvirial_t_input);
                TextView xvirial_p_input = (TextView) findViewById(R.id.xvirial_p_input);
                TextView xvirial_y11_input = (TextView) findViewById(R.id.xvirial_y11_input);
                TextView xvirial_y22_input = (TextView) findViewById(R.id.xvirial_y22_input);
                TextView xvirial_w11_input = (TextView) findViewById(R.id.xvirial_w11_input);
                TextView xvirial_w22_input = (TextView) findViewById(R.id.xvirial_w22_input);
                TextView xvirial_w12_output = (TextView) findViewById(R.id.xvirial_w12_output);
                TextView xvirial_tc11_input = (TextView) findViewById(R.id.xvirial_tc11_input);
                TextView xvirial_tc22_input = (TextView) findViewById(R.id.xvirial_tc22_input);
                TextView xvirial_tc12_output = (TextView) findViewById(R.id.xvirial_tc12_output);
                TextView xvirial_pc11_input = (TextView) findViewById(R.id.xvirial_pc11_input);
                TextView xvirial_pc22_input = (TextView) findViewById(R.id.xvirial_pc22_input);
                TextView xvirial_pc12_output = (TextView) findViewById(R.id.xvirial_pc12_output);
                TextView xvirial_vc11_input = (TextView) findViewById(R.id.xvirial_vc11_input);
                TextView xvirial_vc22_input = (TextView) findViewById(R.id.xvirial_vc22_input);
                TextView xvirial_vc12_output = (TextView) findViewById(R.id.xvirial_vc12_output);
                TextView xvirial_zc11_input = (TextView) findViewById(R.id.xvirial_zc11_input);
                TextView xvirial_zc22_input = (TextView) findViewById(R.id.xvirial_zc22_input);
                TextView xvirial_zc12_output = (TextView) findViewById(R.id.xvirial_zc12_output);
                TextView xvirial_tr11_output = (TextView) findViewById(R.id.xvirial_tr11_output);
                TextView xvirial_tr22_output = (TextView) findViewById(R.id.xvirial_tr22_output);
                TextView xvirial_tr12_output = (TextView) findViewById(R.id.xvirial_tr12_output);
                TextView xvirial_pr11_output = (TextView) findViewById(R.id.xvirial_pr11_output);
                TextView xvirial_pr22_output = (TextView) findViewById(R.id.xvirial_pr22_output);
                TextView xvirial_pr12_output = (TextView) findViewById(R.id.xvirial_pr12_output);
                TextView xvirial_b011_output = (TextView) findViewById(R.id.xvirial_b011_output);
                TextView xvirial_b022_output = (TextView) findViewById(R.id.xvirial_b022_output);
                TextView xvirial_b012_output = (TextView) findViewById(R.id.xvirial_b012_output);
                TextView xvirial_b111_output = (TextView) findViewById(R.id.xvirial_b111_output);
                TextView xvirial_b122_output = (TextView) findViewById(R.id.xvirial_b122_output);
                TextView xvirial_b112_output = (TextView) findViewById(R.id.xvirial_b112_output);
                TextView xvirial_bhat11_output = (TextView) findViewById(R.id.xvirial_bhat11_output);
                TextView xvirial_bhat22_output = (TextView) findViewById(R.id.xvirial_bhat22_output);
                TextView xvirial_bhat12_output = (TextView) findViewById(R.id.xvirial_bhat12_output);
                TextView xvirial_b11_output = (TextView) findViewById(R.id.xvirial_b11_output);
                TextView xvirial_b22_output = (TextView) findViewById(R.id.xvirial_b22_output);
                TextView xvirial_b12_output = (TextView) findViewById(R.id.xvirial_b12_output);
                TextView xvirial_delta12_output = (TextView) findViewById(R.id.xvirial_delta12_output);
                TextView xvirial_phi11_output = (TextView) findViewById(R.id.xvirial_phi11_output);
                TextView xvirial_phi22_output = (TextView) findViewById(R.id.xvirial_phi22_output);
                TextView xvirial_f11_output = (TextView) findViewById(R.id.xvirial_f11_output);
                TextView xvirial_f22_output = (TextView) findViewById(R.id.xvirial_f22_output);
                TextView xvirial_bmix_output = (TextView) findViewById(R.id.xvirial_bmix_output);
                TextView xvirial_zmix_output = (TextView) findViewById(R.id.xvirial_zmix_output);

                if (xvirial_t_input.getText().length() > 0
                        & xvirial_p_input.getText().length() > 0
                        & xvirial_y11_input.getText().length() > 0
                        & xvirial_y22_input.getText().length() > 0
                        & xvirial_w11_input.getText().length() > 0
                        & xvirial_w22_input.getText().length() > 0
                        & xvirial_tc11_input.getText().length() > 0
                        & xvirial_tc22_input.getText().length() > 0
                        & xvirial_pc11_input.getText().length() > 0
                        & xvirial_pc22_input.getText().length() > 0
                        & xvirial_vc11_input.getText().length() > 0
                        & xvirial_vc22_input.getText().length() > 0
                        & xvirial_zc11_input.getText().length() > 0
                        & xvirial_zc22_input.getText().length() > 0) {
                    Double t = Double.parseDouble(xvirial_t_input.getText().toString());
                    Double p = Double.parseDouble(xvirial_p_input.getText().toString());
                    Double y11 = Double.parseDouble(xvirial_y11_input.getText().toString());
                    Double y22 = Double.parseDouble(xvirial_y22_input.getText().toString());
                    Double w11 = Double.parseDouble(xvirial_w11_input.getText().toString());
                    Double w22 = Double.parseDouble(xvirial_w22_input.getText().toString());
                    Double tc11 = Double.parseDouble(xvirial_tc11_input.getText().toString());
                    Double tc22 = Double.parseDouble(xvirial_tc22_input.getText().toString());
                    Double pc11 = Double.parseDouble(xvirial_pc11_input.getText().toString());
                    Double pc22 = Double.parseDouble(xvirial_pc22_input.getText().toString());
                    Double vc11 = Double.parseDouble(xvirial_vc11_input.getText().toString());
                    Double vc22 = Double.parseDouble(xvirial_vc22_input.getText().toString());
                    Double zc11 = Double.parseDouble(xvirial_zc11_input.getText().toString());
                    Double zc22 = Double.parseDouble(xvirial_zc22_input.getText().toString());

                    Double r = 8.3144598; //gas constant

                    Double w12 = 0.5*(w11+w22);
                    Double tc12 = Math.sqrt(tc11*tc22);
                    Double zc12 = 0.5*(zc11+zc22);
                    Double vc12 = Math.pow(0.5 * (Math.pow(vc11, 1.0 / 3.0) + Math.pow(vc22, 1.0 / 3.0)), 3.0);
                    Double pc12 = 10.0 * zc12*r*tc12/vc12; // pc in bar, vc in cm^3/mol
                    Double tr11 = t / tc11; Double tr22 = t / tc22; Double tr12 = t / tc12;
                    Double pr11 = p / pc11; Double pr22 = p / pc22; Double pr12 = p / pc12;
                    Double b011 = 0.083 - 0.422 / Math.pow(tr11, 1.6);
                    Double b111 = 0.139 - 0.172 / Math.pow(tr11, 4.2);
                    Double b022 = 0.083 - 0.422 / Math.pow(tr22, 1.6);
                    Double b122 = 0.139 - 0.172 / Math.pow(tr22, 4.2);
                    Double b012 = 0.083 - 0.422 / Math.pow(tr12, 1.6);
                    Double b112 = 0.139 - 0.172 / Math.pow(tr12, 4.2);
                    Double bhat11 = b011 + w11 * b111;
                    Double bhat22 = b022 + w22 * b122;
                    Double bhat12 = b012 + w12 * b112;
                    Double b11 = bhat11*r*tc11/pc11;
                    Double b22 = bhat22*r*tc22/pc22;
                    Double b12 = bhat12*r*tc12/pc12;
                    Double delta12 = 2.0*b12-b11-b22;
                    Double phi11 = Math.exp(p*(b11+Math.pow(y22,2.0)*delta12)/(r*t));
                    Double phi22 = Math.exp(p*(b22+Math.pow(y11,2.0)*delta12)/(r*t));
                    Double f11 = phi11*y11*p; Double f22 = phi22*y22*p;
                    Double bmix = Math.pow(y11,2.0)*b11+2.0*y11*y22*b12+Math.pow(y22,2.0)*b22;
                    Double zmix = 1.0+bmix*p/(r*t);

                    xvirial_w12_output.setText(w12.toString());
                    xvirial_tc12_output.setText(tc12.toString());
                    xvirial_pc12_output.setText(pc12.toString());
                    xvirial_vc12_output.setText(vc12.toString());
                    xvirial_zc12_output.setText(zc12.toString());
                    xvirial_tr11_output.setText(tr11.toString());
                    xvirial_tr22_output.setText(tr22.toString());
                    xvirial_tr12_output.setText(tr12.toString());
                    xvirial_pr11_output.setText(pr11.toString());
                    xvirial_pr22_output.setText(pr22.toString());
                    xvirial_pr12_output.setText(pr12.toString());
                    xvirial_b011_output.setText(b011.toString());
                    xvirial_b022_output.setText(b022.toString());
                    xvirial_b012_output.setText(b012.toString());
                    xvirial_b111_output.setText(b111.toString());
                    xvirial_b122_output.setText(b122.toString());
                    xvirial_b112_output.setText(b112.toString());
                    xvirial_bhat11_output.setText(bhat11.toString());
                    xvirial_bhat22_output.setText(bhat22.toString());
                    xvirial_bhat12_output.setText(bhat12.toString());
                    xvirial_b11_output.setText(b11.toString());
                    xvirial_b22_output.setText(b22.toString());
                    xvirial_b12_output.setText(b12.toString());
                    xvirial_delta12_output.setText(delta12.toString());
                    xvirial_phi11_output.setText(phi11.toString());
                    xvirial_phi22_output.setText(phi22.toString());
                    xvirial_f11_output.setText(f11.toString());
                    xvirial_f22_output.setText(f22.toString());
                    xvirial_bmix_output.setText(bmix.toString());
                    xvirial_zmix_output.setText(zmix.toString());
                } else {
                    Toast.makeText(XVirial.this, "Under-determined system!", Toast.LENGTH_SHORT).show();
                }
            }
        });

                TextView xvirial_t = (TextView) findViewById(R.id.xvirial_t);
        xvirial_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Temperature T (in K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_p = (TextView) findViewById(R.id.xvirial_p);
        xvirial_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Pressure P (in bar)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_11 = (TextView) findViewById(R.id.xvirial_11);
        xvirial_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Pure species 1", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_22 = (TextView) findViewById(R.id.xvirial_22);
        xvirial_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Pure species 2", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_12 = (TextView) findViewById(R.id.xvirial_12);
        xvirial_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Vapour mixture (1-2)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_y = (TextView) findViewById(R.id.xvirial_y);
        xvirial_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Vapour phase mass composition (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_w = (TextView) findViewById(R.id.xvirial_w);
        xvirial_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Acentric factor \u03C9 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_tc = (TextView) findViewById(R.id.xvirial_tc);
        xvirial_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Critical temperature Tc (in K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_pc = (TextView) findViewById(R.id.xvirial_pc);
        xvirial_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Critical pressure Pc (in bar)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_vc = (TextView) findViewById(R.id.xvirial_vc);
        xvirial_vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Critical molar volume Vc (in cm\u00B3 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_zc = (TextView) findViewById(R.id.xvirial_zc);
        xvirial_zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Critical compressibility factor Zc (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_tr = (TextView) findViewById(R.id.xvirial_tr);
        xvirial_tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Reduced temperature T\u1D63 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_pr = (TextView) findViewById(R.id.xvirial_pr);
        xvirial_pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Reduced pressure P\u1D63 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_b0 = (TextView) findViewById(R.id.xvirial_b0);
        xvirial_b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Pitzer B\u2070 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_b1 = (TextView) findViewById(R.id.xvirial_b1);
        xvirial_b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Pitzer B\u00B9 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_bhat = (TextView) findViewById(R.id.xvirial_bhat);
        xvirial_bhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Pitzer B^ (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_b = (TextView) findViewById(R.id.xvirial_b);
        xvirial_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Pitzer B (in cm\u00B3 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_delta = (TextView) findViewById(R.id.xvirial_delta);
        xvirial_delta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Cross interaction \u03B4\u2081\u2082=2B\u2081\u2082-B\u2081\u2081-B\u2082\u2082 (in cm\u00B3 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_phi = (TextView) findViewById(R.id.xvirial_phi);
        xvirial_phi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Partial fugacity coefficient \u03D5 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_f = (TextView) findViewById(R.id.xvirial_f);
        xvirial_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Partial fugacity f (in bar)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_bmix = (TextView) findViewById(R.id.xvirial_bmix);
        xvirial_bmix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Pitzer B of vapour mixture (in cm\u00B3 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView xvirial_zmix = (TextView) findViewById(R.id.xvirial_zmix);
        xvirial_zmix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XVirial.this, "Compressibility factor of vapour mixture (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}