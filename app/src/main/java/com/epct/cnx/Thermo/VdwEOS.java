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

public class VdwEOS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thermo_vdweos);

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

        Spinner spinner_vdweos_presets = (Spinner) findViewById(R.id.vdweos_presets_input);
        ArrayAdapter<CharSequence> adapter_vdweos_presets = ArrayAdapter.createFromResource(this,
                R.array.compound_presets, android.R.layout.simple_spinner_item);
        adapter_vdweos_presets.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_vdweos_presets.setAdapter(adapter_vdweos_presets);
        spinner_vdweos_presets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    TextView vdweos_tc_input = (TextView) findViewById(R.id.vdweos_tc_input);
                    TextView vdweos_pc_input = (TextView) findViewById(R.id.vdweos_pc_input);

                    Double tc = Double.parseDouble(compounds.get(pos)[1]);
                    if (Math.abs(tc + 999.0) > 0.001) {
                        vdweos_tc_input.setText(tc.toString());
                    } else {
                        vdweos_tc_input.setText("");
                    }
                    Double pc = Double.parseDouble(compounds.get(pos)[2]);
                    if (Math.abs(pc + 999.0) > 0.001) {
                        vdweos_pc_input.setText(pc.toString());
                    } else {
                        vdweos_pc_input.setText("");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Button vdweos_solve = (Button) findViewById(R.id.vdweos_solve);
        vdweos_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView vdweos_t_input = (TextView) findViewById(R.id.vdweos_t_input);
                TextView vdweos_tc_input = (TextView) findViewById(R.id.vdweos_tc_input);
                TextView vdweos_p_input = (TextView) findViewById(R.id.vdweos_p_input);
                TextView vdweos_pc_input = (TextView) findViewById(R.id.vdweos_pc_input);
                TextView vdweos_tr_output = (TextView) findViewById(R.id.vdweos_tr_output);
                TextView vdweos_pr_output = (TextView) findViewById(R.id.vdweos_pr_output);
                TextView vdweos_a_output = (TextView) findViewById(R.id.vdweos_a_output);
                TextView vdweos_b_output = (TextView) findViewById(R.id.vdweos_b_output);
                TextView vdweos_hr_output = (TextView) findViewById(R.id.vdweos_hr_output);
                TextView vdweos_sr_output = (TextView) findViewById(R.id.vdweos_sr_output);
                TextView vdweos_z_output = (TextView) findViewById(R.id.vdweos_z_output);
                TextView vdweos_v_output = (TextView) findViewById(R.id.vdweos_v_output);

                Double r = 8.3144598; //gas constant

                if (vdweos_t_input.getText().length() > 0
                        & vdweos_tc_input.getText().length() > 0
                        & vdweos_p_input.getText().length() > 0
                        & vdweos_pc_input.getText().length() > 0) {
                    Double t = Double.parseDouble(vdweos_t_input.getText().toString());
                    Double tc = Double.parseDouble(vdweos_tc_input.getText().toString());
                    Double p = Double.parseDouble(vdweos_p_input.getText().toString());
                    Double pc = Double.parseDouble(vdweos_pc_input.getText().toString());

                    p = p * 100000.0;
                    pc = pc * 100000.0;
                    Double tr = t / tc;
                    Double pr = p / pc;
                    Double a = 27.0 * Math.pow(r, 2.0) * Math.pow(tc, 2.0) / (64 * pc);
                    Double b = r * tc / (8 * pc);

                    Double B = -b - r * t / p;
                    Double C = a / p;
                    Double D = -a * b / p;
                    Double v0 = r * t / p, v1 = 2.0 * v0, fv = 0.0, dfv = 0.0;
                    int iter = 0;
                    while (Math.abs(v1 - v0) / v0 > 0.0000001 & iter < 1000) {
                        v0 = v1;
                        fv = Math.pow(v0, 3.0) + B * Math.pow(v0, 2.0) + C * v0 + D;
                        dfv = 3.0 * Math.pow(v0, 2.0) + 2.0 * B * v0 + C;
                        v1 = v0 - fv / dfv;
                        iter = iter + 1;
                    }
                    Toast.makeText(VdwEOS.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                    Double d = 1.0 / v1;
                    Double hr = -r * t * (1.0 - 1.0 / (1.0 - d * b)) - 2.0 * d * a;
                    Double sr = r * Math.log(1.0 - d * a / (r * t) + a * b * Math.pow(d, 2) / (r * t));
                    Double z = 1.0 / (1.0 - d * b) - d * a / (r * t);

                    vdweos_tr_output.setText(tr.toString());
                    vdweos_pr_output.setText(pr.toString());
                    vdweos_a_output.setText(a.toString());
                    vdweos_b_output.setText(b.toString());
                    vdweos_hr_output.setText(hr.toString());
                    vdweos_sr_output.setText(sr.toString());
                    vdweos_z_output.setText(z.toString());
                    vdweos_v_output.setText(v1.toString());
                } else {
                    Toast.makeText(VdwEOS.this, "Under-determined system!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView vdweos_t = (TextView) findViewById(R.id.vdweos_t);
        vdweos_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VdwEOS.this, "Temperature T (in K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView vdweos_tc = (TextView) findViewById(R.id.vdweos_tc);
        vdweos_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VdwEOS.this, "Critical temperature Tc (in K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView vdweos_p = (TextView) findViewById(R.id.vdweos_p);
        vdweos_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VdwEOS.this, "Pressure P (in bar)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView vdweos_pc = (TextView) findViewById(R.id.vdweos_pc);
        vdweos_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VdwEOS.this, "Critical pressure Pc (in bar)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView vdweos_tr = (TextView) findViewById(R.id.vdweos_tr);
        vdweos_tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VdwEOS.this, "Reduced temperature T\u1D63 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView vdweos_pr = (TextView) findViewById(R.id.vdweos_pr);
        vdweos_pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VdwEOS.this, "Reduced pressure P\u1D63 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView vdweos_a = (TextView) findViewById(R.id.vdweos_a);
        vdweos_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VdwEOS.this, "van der Waals parameter a (in Pa m\u2076 mol\u207B\u00B2)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView vdweos_b = (TextView) findViewById(R.id.vdweos_b);
        vdweos_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VdwEOS.this, "van der Waals parameter b (in m\u00B3 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView vdweos_hr = (TextView) findViewById(R.id.vdweos_hr);
        vdweos_hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VdwEOS.this, "Residual enthalpy H\u1D3F (in J mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView vdweos_sr = (TextView) findViewById(R.id.vdweos_sr);
        vdweos_sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VdwEOS.this, "Residual entropy S\u1D3F (in J K\u207B\u00B9 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView vdweos_z = (TextView) findViewById(R.id.vdweos_z);
        vdweos_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VdwEOS.this, "Compressibility factor Z (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView vdweos_v = (TextView) findViewById(R.id.vdweos_v);
        vdweos_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VdwEOS.this, "Volume V (in m\u00B3 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
