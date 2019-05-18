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

public class Fugacity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thermo_fugacity);

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

        Spinner spinner_fugacity_presets = (Spinner) findViewById(R.id.fugacity_presets_input);
        ArrayAdapter<CharSequence> adapter_fugacity_presets = ArrayAdapter.createFromResource(this,
                R.array.compound_presets, android.R.layout.simple_spinner_item);
        adapter_fugacity_presets.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_fugacity_presets.setAdapter(adapter_fugacity_presets);
        spinner_fugacity_presets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    TextView fugacity_tc_input = (TextView) findViewById(R.id.fugacity_tc_input);
                    TextView fugacity_pc_input = (TextView) findViewById(R.id.fugacity_pc_input);
                    TextView fugacity_omega_input = (TextView) findViewById(R.id.fugacity_omega_input);
                    TextView fugacity_vc_input = (TextView) findViewById(R.id.fugacity_vc_input);
                    TextView fugacity_zc_input = (TextView) findViewById(R.id.fugacity_zc_input);

                    Double tc = Double.parseDouble(compounds.get(pos)[1]);
                    if (Math.abs(tc+999.0)>0.001) {
                        fugacity_tc_input.setText(tc.toString());
                    }
                    else {
                        fugacity_tc_input.setText("");
                    }
                    Double pc = Double.parseDouble(compounds.get(pos)[2]);
                    if (Math.abs(pc+999.0)>0.001) {
                        fugacity_pc_input.setText(pc.toString());
                    }
                    else {
                        fugacity_pc_input.setText("");
                    }
                    Double w = Double.parseDouble(compounds.get(pos)[3]);
                    if (Math.abs(w+999.0)>0.001) {
                        fugacity_omega_input.setText(w.toString());
                    }
                    else {
                        fugacity_omega_input.setText("");
                    }
                    Double vc = Double.parseDouble(compounds.get(pos)[4]);
                    if (Math.abs(vc+999.0)>0.001) {
                        fugacity_vc_input.setText(vc.toString());
                    }
                    else {
                        fugacity_vc_input.setText("");
                    }
                    Double zc = Double.parseDouble(compounds.get(pos)[5]);
                    if (Math.abs(zc+999.0)>0.001) {
                        fugacity_zc_input.setText(zc.toString());
                    }
                    else {
                        fugacity_zc_input.setText("");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Button fugacity_solve = (Button) findViewById(R.id.fugacity_solve);
        fugacity_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView fugacity_t_input = (TextView) findViewById(R.id.fugacity_t_input);
                TextView fugacity_tc_input = (TextView) findViewById(R.id.fugacity_tc_input);
                TextView fugacity_p_input = (TextView) findViewById(R.id.fugacity_p_input);
                TextView fugacity_pc_input = (TextView) findViewById(R.id.fugacity_pc_input);
                TextView fugacity_psat_input = (TextView) findViewById(R.id.fugacity_psat_input);
                TextView fugacity_omega_input = (TextView) findViewById(R.id.fugacity_omega_input);
                TextView fugacity_vc_input = (TextView) findViewById(R.id.fugacity_vc_input);
                TextView fugacity_zc_input = (TextView) findViewById(R.id.fugacity_zc_input);

                TextView fugacity_state_output = (TextView) findViewById(R.id.fugacity_state_output);
                TextView fugacity_tr_output = (TextView) findViewById(R.id.fugacity_tr_output);
                TextView fugacity_pr_output = (TextView) findViewById(R.id.fugacity_pr_output);
                TextView fugacity_b0_output = (TextView) findViewById(R.id.fugacity_b0_output);
                TextView fugacity_b1_output = (TextView) findViewById(R.id.fugacity_b1_output);
                TextView fugacity_bhat_output = (TextView) findViewById(R.id.fugacity_bhat_output);
                TextView fugacity_vsat_output = (TextView) findViewById(R.id.fugacity_vsat_output);
                TextView fugacity_pcr_output = (TextView) findViewById(R.id.fugacity_pcr_output);
                TextView fugacity_phi_output = (TextView) findViewById(R.id.fugacity_phi_output);
                TextView fugacity_phisat_output = (TextView) findViewById(R.id.fugacity_phisat_output);
                TextView fugacity_f_output = (TextView) findViewById(R.id.fugacity_f_output);

                Double r = 8.3144598; //gas constant

                if (fugacity_t_input.getText().length() > 0
                        & fugacity_tc_input.getText().length() > 0
                        & fugacity_p_input.getText().length() > 0
                        & fugacity_pc_input.getText().length() > 0
                        & fugacity_psat_input.getText().length() > 0
                        & fugacity_omega_input.getText().length() > 0
                        & fugacity_vc_input.getText().length() > 0
                        & fugacity_zc_input.getText().length() > 0) {
                    Double t = Double.parseDouble(fugacity_t_input.getText().toString());
                    Double tc = Double.parseDouble(fugacity_tc_input.getText().toString());
                    Double p = Double.parseDouble(fugacity_p_input.getText().toString());
                    Double pc = Double.parseDouble(fugacity_pc_input.getText().toString());
                    Double psat = Double.parseDouble(fugacity_psat_input.getText().toString());
                    Double w = Double.parseDouble(fugacity_omega_input.getText().toString());
                    Double vc = Double.parseDouble(fugacity_vc_input.getText().toString());
                    Double zc = Double.parseDouble(fugacity_zc_input.getText().toString());

                    Double tr = t / tc;
                    Double pr = p / pc;
                    Double b0 = 0.083 - 0.422 / Math.pow(tr, 1.6);
                    Double b1 = 0.139 - 0.172 / Math.pow(tr, 4.2);
                    Double bhat = b0 + w * b1;
                    Double vsat = vc * Math.pow(Math.pow(zc, 1.0 - tr), 2.0 / 7.0);
                    Double phi = Math.exp(bhat * pr / tr);
                    Double phisat = Math.exp(bhat * (psat / pc) / tr);
                    Double eps = 0.000000000001;
                    Double f, pcr;

                    if (psat - p > eps) {
                        fugacity_state_output.setText("vapour");
                        fugacity_pcr_output.setText("N/A");
                        f = p * phi;
                    } else if (p - psat > eps) {
                        fugacity_state_output.setText("liquid");
                        pcr = Math.exp((vsat * 0.000001) * ((p - psat) * 100000) / (r * t));
                        fugacity_pcr_output.setText(pcr.toString());
                        f = psat * phisat * pcr;
                    } else {
                        fugacity_state_output.setText("vapour-liquid mixture");
                        fugacity_pcr_output.setText("0.0");
                        f = p * phi;
                    }

                    fugacity_tr_output.setText(tr.toString());
                    fugacity_pr_output.setText(pr.toString());
                    fugacity_b0_output.setText(b0.toString());
                    fugacity_b1_output.setText(b1.toString());
                    fugacity_bhat_output.setText(bhat.toString());
                    fugacity_vsat_output.setText(vsat.toString());
                    fugacity_phi_output.setText(phi.toString());
                    fugacity_phisat_output.setText(phisat.toString());
                    fugacity_f_output.setText(f.toString());
                } else {
                    Toast.makeText(Fugacity.this, "Under-determined system!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView fugacity_t = (TextView) findViewById(R.id.fugacity_t);
        fugacity_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Temperature T (in K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_tc = (TextView) findViewById(R.id.fugacity_tc);
        fugacity_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Critical temperature Tc (in K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_p = (TextView) findViewById(R.id.fugacity_p);
        fugacity_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Pressure P (in bar)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_pc = (TextView) findViewById(R.id.fugacity_pc);
        fugacity_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Critical pressure Pc (in bar)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_psat = (TextView) findViewById(R.id.fugacity_psat);
        fugacity_psat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Vapour pressure P\u209B\u2090\u209C at T (in bar)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_omega = (TextView) findViewById(R.id.fugacity_omega);
        fugacity_omega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Acentric factor \u03C9 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_vc = (TextView) findViewById(R.id.fugacity_vc);
        fugacity_vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Critical molar volume Vc (in cm\u00B3 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_zc = (TextView) findViewById(R.id.fugacity_zc);
        fugacity_zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Critical compressibility factor Zc (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_tr = (TextView) findViewById(R.id.fugacity_tr);
        fugacity_tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Reduced temperature T\u1D63 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_pr = (TextView) findViewById(R.id.fugacity_pr);
        fugacity_pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Reduced pressure P\u1D63 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_b0 = (TextView) findViewById(R.id.fugacity_b0);
        fugacity_b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Pitzer B\u2070 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_b1 = (TextView) findViewById(R.id.fugacity_b1);
        fugacity_b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Pitzer B\u00B9 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_bhat = (TextView) findViewById(R.id.fugacity_bhat);
        fugacity_bhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Pitzer B^ (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_vsat = (TextView) findViewById(R.id.fugacity_vsat);
        fugacity_vsat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Saturation liquid molar volume V\u209B\u2090\u209C at P\u209B\u2090\u209C and T (in cm\u00B3 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_pcr = (TextView) findViewById(R.id.fugacity_pcr);
        fugacity_pcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Poynting correction factor PCR (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_phi = (TextView) findViewById(R.id.fugacity_phi);
        fugacity_phi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Fugacity coefficient \u03D5 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_phisat = (TextView) findViewById(R.id.fugacity_phisat);
        fugacity_phisat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Fugacity coefficient at P\u209B\u2090\u209C at T (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView fugacity_f = (TextView) findViewById(R.id.fugacity_f);
        fugacity_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fugacity.this, "Fugacity f (in bar)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}