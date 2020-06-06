package com.epct.cnx.Thermo;

import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
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

public class VirialEOS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thermo_virialeos);

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

        Spinner spinner_virialeos_presets = (Spinner) findViewById(R.id.virialeos_presets_input);
        ArrayAdapter<CharSequence> adapter_virialeos_presets = ArrayAdapter.createFromResource(this,
                R.array.compound_presets, android.R.layout.simple_spinner_item);
        adapter_virialeos_presets.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_virialeos_presets.setAdapter(adapter_virialeos_presets);
        spinner_virialeos_presets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    TextView virialeos_tc_input = (TextView) findViewById(R.id.virialeos_tc_input);
                    TextView virialeos_pc_input = (TextView) findViewById(R.id.virialeos_pc_input);
                    TextView virialeos_omega_input = (TextView) findViewById(R.id.virialeos_omega_input);

                    Double tc = Double.parseDouble(compounds.get(pos)[1]);
                    if (Math.abs(tc+999.0)>0.001) {
                        virialeos_tc_input.setText(tc.toString());
                    }
                    else {
                        virialeos_tc_input.setText("");
                    }
                    Double pc = Double.parseDouble(compounds.get(pos)[2]);
                    if (Math.abs(pc+999.0)>0.001) {
                        virialeos_pc_input.setText(pc.toString());
                    }
                    else {
                        virialeos_pc_input.setText("");
                    }
                    Double w = Double.parseDouble(compounds.get(pos)[3]);
                    if (Math.abs(w+999.0)>0.001) {
                        virialeos_omega_input.setText(w.toString());
                    }
                    else {
                        virialeos_omega_input.setText("");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Button virialeos_solve = (Button) findViewById(R.id.virialeos_solve);
        virialeos_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView virialeos_t_input = (TextView) findViewById(R.id.virialeos_t_input);
                TextView virialeos_tc_input = (TextView) findViewById(R.id.virialeos_tc_input);
                TextView virialeos_p_input = (TextView) findViewById(R.id.virialeos_p_input);
                TextView virialeos_pc_input = (TextView) findViewById(R.id.virialeos_pc_input);
                TextView virialeos_omega_input = (TextView) findViewById(R.id.virialeos_omega_input);

                TextView virialeos_tr_output = (TextView) findViewById(R.id.virialeos_tr_output);
                TextView virialeos_pr_output = (TextView) findViewById(R.id.virialeos_pr_output);
                TextView virialeos_b0_output = (TextView) findViewById(R.id.virialeos_b0_output);
                TextView virialeos_b1_output = (TextView) findViewById(R.id.virialeos_b1_output);
                TextView virialeos_bhat_output = (TextView) findViewById(R.id.virialeos_bhat_output);
                TextView virialeos_b_output = (TextView) findViewById(R.id.virialeos_b_output);
                TextView virialeos_db0_output = (TextView) findViewById(R.id.virialeos_db0_output);
                TextView virialeos_db1_output = (TextView) findViewById(R.id.virialeos_db1_output);
                TextView virialeos_db_output = (TextView) findViewById(R.id.virialeos_db_output);
                TextView virialeos_hr_output = (TextView) findViewById(R.id.virialeos_hr_output);
                TextView virialeos_sr_output = (TextView) findViewById(R.id.virialeos_sr_output);
                TextView virialeos_v_output = (TextView) findViewById(R.id.virialeos_v_output);
                TextView virialeos_z_output = (TextView) findViewById(R.id.virialeos_z_output);

                Double r = 8.3144598; //gas constant

                if (virialeos_t_input.getText().length() > 0
                        & virialeos_tc_input.getText().length() > 0
                        & virialeos_p_input.getText().length() > 0
                        & virialeos_pc_input.getText().length() > 0
                        & virialeos_omega_input.getText().length() > 0) {
                    Double t = Double.parseDouble(virialeos_t_input.getText().toString());
                    Double tc = Double.parseDouble(virialeos_tc_input.getText().toString());
                    Double p = Double.parseDouble(virialeos_p_input.getText().toString());
                    Double pc = Double.parseDouble(virialeos_pc_input.getText().toString());
                    Double w = Double.parseDouble(virialeos_omega_input.getText().toString());

                    Double tr = t / tc;
                    Double pr = p / pc;
                    Double b0 = 0.083 - 0.422 / Math.pow(tr, 1.6);
                    Double b1 = 0.139 - 0.172 / Math.pow(tr, 4.2);
                    Double bhat = b0 + w * b1;
                    Double b = bhat * r * tc / pc * 10.0;
                    Double db0 = 0.675 / Math.pow(tr, 2.6);
                    Double db1 = 0.722 / Math.pow(tr, 5.2);
                    Double hr = r * tc * pr * (b0 - tr * db0 + w * (b1 - tr * db1));
                    Double sr = -r * pr * (db0 + w * db1);
                    Double db = -sr / p * 10.0;
                    Double z = 1.0 + bhat * pr / tr;
                    Double vol = z * r * t / p * 10.0;

                    virialeos_tr_output.setText(tr.toString());
                    virialeos_pr_output.setText(pr.toString());
                    virialeos_b0_output.setText(b0.toString());
                    virialeos_b1_output.setText(b1.toString());
                    virialeos_bhat_output.setText(bhat.toString());
                    virialeos_b_output.setText(b.toString());
                    virialeos_db0_output.setText(db0.toString());
                    virialeos_db1_output.setText(db1.toString());
                    virialeos_db_output.setText(db.toString());
                    virialeos_hr_output.setText(hr.toString());
                    virialeos_sr_output.setText(sr.toString());
                    virialeos_v_output.setText(vol.toString());
                    virialeos_z_output.setText(z.toString());
                } else {
                    Toast.makeText(VirialEOS.this, "Under-determined system!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView virialeos_t = (TextView) findViewById(R.id.virialeos_t);
        virialeos_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Temperature T (in K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_tc = (TextView) findViewById(R.id.virialeos_tc);
        virialeos_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Critical temperature Tc (in K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_p = (TextView) findViewById(R.id.virialeos_p);
        virialeos_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Pressure P (in bar)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_pc = (TextView) findViewById(R.id.virialeos_pc);
        virialeos_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Critical pressure Pc (in bar)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_omega = (TextView) findViewById(R.id.virialeos_omega);
        virialeos_omega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Acentric factor \u03C9 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_tr = (TextView) findViewById(R.id.virialeos_tr);
        virialeos_tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Reduced temperature T\u1D63 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_pr = (TextView) findViewById(R.id.virialeos_pr);
        virialeos_pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Reduced pressure P\u1D63 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_b0 = (TextView) findViewById(R.id.virialeos_b0);
        virialeos_b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Pitzer B\u2070 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_b1 = (TextView) findViewById(R.id.virialeos_b1);
        virialeos_b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Pitzer B\u00B9 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_bhat = (TextView) findViewById(R.id.virialeos_bhat);
        virialeos_bhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Pitzer B^ (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_b = (TextView) findViewById(R.id.virialeos_b);
        virialeos_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Pitzer B (in cm\u00B3 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_db0 = (TextView) findViewById(R.id.virialeos_db0);
        virialeos_db0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Pitzer derivative dB\u2070/dT\u1D63 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_db1 = (TextView) findViewById(R.id.virialeos_db1);
        virialeos_db1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Pitzer derivative dB\u00B9/dT\u1D63 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_db = (TextView) findViewById(R.id.virialeos_db);
        virialeos_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Pitzer derivative dB/dT\u1D63 (in cm\u00B3 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_hr = (TextView) findViewById(R.id.virialeos_hr);
        virialeos_hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Residual enthalpy H\u1D3F (in J mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_sr = (TextView) findViewById(R.id.virialeos_sr);
        virialeos_sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Residual entropy S\u1D3F (in J K\u207B\u00B9 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_v = (TextView) findViewById(R.id.virialeos_v);
        virialeos_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Molar volume V (in cm\u00B3 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView virialeos_z = (TextView) findViewById(R.id.virialeos_z);
        virialeos_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VirialEOS.this, "Compressibility factor Z (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
