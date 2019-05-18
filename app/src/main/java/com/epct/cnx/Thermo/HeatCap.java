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

public class HeatCap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thermo_heatcap);

        final List<String[]> compounds = new ArrayList<>();
        Runnable compounds_runnable = new Runnable() {
            @Override
            public void run() {
                //saturated steam by temperature
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

        Spinner spinner_heatcap_cp = (Spinner) findViewById(R.id.heatcap_cp);
        ArrayAdapter<CharSequence> adapter_heatcap_cp = ArrayAdapter.createFromResource(this,
                R.array.heatcap_eqntypes, android.R.layout.simple_spinner_item);
        adapter_heatcap_cp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_heatcap_cp.setAdapter(adapter_heatcap_cp);

        Spinner spinner_heatcap_presets = (Spinner) findViewById(R.id.heatcap_presets_input);
        ArrayAdapter<CharSequence> adapter_heatcap_presets = ArrayAdapter.createFromResource(this,
                R.array.compound_presets, android.R.layout.simple_spinner_item);
        adapter_heatcap_presets.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_heatcap_presets.setAdapter(adapter_heatcap_presets);
        spinner_heatcap_presets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    TextView heatcap_a_input = (TextView) findViewById(R.id.heatcap_a_input);
                    TextView heatcap_b_input = (TextView) findViewById(R.id.heatcap_b_input);
                    TextView heatcap_b_order_input = (TextView) findViewById(R.id.heatcap_b_order_input);
                    TextView heatcap_c_input = (TextView) findViewById(R.id.heatcap_c_input);
                    TextView heatcap_c_order_input = (TextView) findViewById(R.id.heatcap_c_order_input);
                    TextView heatcap_d_input = (TextView) findViewById(R.id.heatcap_d_input);
                    TextView heatcap_d_order_input = (TextView) findViewById(R.id.heatcap_d_order_input);
                    TextView heatcap_e_input = (TextView) findViewById(R.id.heatcap_e_input);
                    TextView heatcap_e_order_input = (TextView) findViewById(R.id.heatcap_e_order_input);
                    Spinner spinner_heatcap_cp = (Spinner) findViewById(R.id.heatcap_cp);

                    Double a = Double.parseDouble(compounds.get(pos)[9]);
                    if (Math.abs(a+999.0)>0.001) {
                        heatcap_a_input.setText(a.toString());
                    }
                    else {
                        heatcap_a_input.setText("");
                    }
                    Double b = Double.parseDouble(compounds.get(pos)[10]);
                    if (Math.abs(b+999.0)>0.001) {
                        heatcap_b_input.setText(b.toString());
                        heatcap_b_order_input.setText("-3");
                    }
                    else {
                        heatcap_b_input.setText("");
                        heatcap_b_order_input.setText("");
                    }
                    Double c = Double.parseDouble(compounds.get(pos)[11]);
                    if (Math.abs(c+999.0)>0.001) {
                        heatcap_c_input.setText(c.toString());
                        heatcap_c_order_input.setText("-6");
                    }
                    else {
                        heatcap_c_input.setText("");
                        heatcap_c_order_input.setText("");
                    }
                    heatcap_d_input.setText("0.0");
                    heatcap_d_order_input.setText("0");
                    Double e = Double.parseDouble(compounds.get(pos)[12]);
                    if (Math.abs(e+999.0)>0.001) {
                        heatcap_e_input.setText(e.toString());
                        heatcap_e_order_input.setText("5");
                    }
                    else {
                        heatcap_e_input.setText("");
                        heatcap_e_order_input.setText("");
                    }
                    spinner_heatcap_cp.setSelection(2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Button heatcap_solve = (Button) findViewById(R.id.heatcap_solve);
        heatcap_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner_heatcap_cp = (Spinner) findViewById(R.id.heatcap_cp);
                TextView heatcap_a_input = (TextView) findViewById(R.id.heatcap_a_input);
                TextView heatcap_b_input = (TextView) findViewById(R.id.heatcap_b_input);
                TextView heatcap_b_order_input = (TextView) findViewById(R.id.heatcap_b_order_input);
                TextView heatcap_c_input = (TextView) findViewById(R.id.heatcap_c_input);
                TextView heatcap_c_order_input = (TextView) findViewById(R.id.heatcap_c_order_input);
                TextView heatcap_d_input = (TextView) findViewById(R.id.heatcap_d_input);
                TextView heatcap_d_order_input = (TextView) findViewById(R.id.heatcap_d_order_input);
                TextView heatcap_e_input = (TextView) findViewById(R.id.heatcap_e_input);
                TextView heatcap_e_order_input = (TextView) findViewById(R.id.heatcap_e_order_input);
                TextView heatcap_t1_input = (TextView) findViewById(R.id.heatcap_t1_input);
                TextView heatcap_t2_input = (TextView) findViewById(R.id.heatcap_t2_input);

                TextView heatcap_h_output = (TextView) findViewById(R.id.heatcap_h_output);
                TextView heatcap_u_output = (TextView) findViewById(R.id.heatcap_u_output);
                TextView heatcap_s_output = (TextView) findViewById(R.id.heatcap_s_output);

                Double a, b, c, d, e, aO, bO, cO, dO, eO;

                if (heatcap_a_input.getText().length() == 0) {
                    a = 0.0;
                } else {
                    a = Double.parseDouble(heatcap_a_input.getText().toString());
                }
                if (heatcap_b_input.getText().length() == 0) {
                    b = 0.0;
                } else {
                    b = Double.parseDouble(heatcap_b_input.getText().toString());
                }
                if (heatcap_c_input.getText().length() == 0) {
                    c = 0.0;
                } else {
                    c = Double.parseDouble(heatcap_c_input.getText().toString());
                }
                if (heatcap_d_input.getText().length() == 0) {
                    d = 0.0;
                } else {
                    d = Double.parseDouble(heatcap_d_input.getText().toString());
                }
                if (heatcap_e_input.getText().length() == 0) {
                    e = 0.0;
                } else {
                    e = Double.parseDouble(heatcap_e_input.getText().toString());
                }
                if (heatcap_b_order_input.getText().length() == 0) {
                    bO = 0.0;
                } else {
                    bO = Double.parseDouble(heatcap_b_order_input.getText().toString());
                }
                if (heatcap_c_order_input.getText().length() == 0) {
                    cO = 0.0;
                } else {
                    cO = Double.parseDouble(heatcap_c_order_input.getText().toString());
                }
                if (heatcap_d_order_input.getText().length() == 0) {
                    dO = 0.0;
                } else {
                    dO = Double.parseDouble(heatcap_d_order_input.getText().toString());
                }
                if (heatcap_e_order_input.getText().length() == 0) {
                    eO = 0.0;
                } else {
                    eO = Double.parseDouble(heatcap_e_order_input.getText().toString());
                }

                Double r = 8.3144598; //gas constant

                if (heatcap_t1_input.getText().length() == 0
                        | heatcap_t2_input.getText().length() == 0
                        | (heatcap_a_input.getText().length() == 0
                        & heatcap_b_input.getText().length() == 0
                        & heatcap_c_input.getText().length() == 0
                        & heatcap_d_input.getText().length() == 0
                        & heatcap_e_input.getText().length() == 0)) {
                    Toast.makeText(HeatCap.this, "Under-determined system!", Toast.LENGTH_SHORT).show();
                } else {
                    b = b * Math.pow(10.0, bO);
                    c = c * Math.pow(10.0, cO);
                    d = d * Math.pow(10.0, dO);
                    e = e * Math.pow(10.0, eO);
                    Double dH = 0.0;
                    Double dU = 0.0;
                    Double dS = 0.0;
                    Double t1 = Double.parseDouble(heatcap_t1_input.getText().toString());
                    Double t2 = Double.parseDouble(heatcap_t2_input.getText().toString());
                    if (spinner_heatcap_cp.getSelectedItem().toString().equals("c\u209A")) {
                        dH = (a * t2 + b * Math.pow(t2, 2.0) / 2 + c * Math.pow(t2, 3.0) / 3 + d * Math.pow(t2, 4.0) / 4 - e / t2)
                                - (a * t1 + b * Math.pow(t1, 2.0) / 2 + c * Math.pow(t1, 3.0) / 3 + d * Math.pow(t1, 4.0) / 4 - e / t1);
                        dU = dH - r * (t2 - t1);
                        dS = (a * Math.log(t2) + b * t2 + c * Math.pow(t2, 2.0) / 2 + d * Math.pow(t2, 3.0) / 3 - e * Math.pow(t2, -2.0) / 2)
                                - (a * Math.log(t1) + b * t1 + c * Math.pow(t1, 2.0) / 2 + d * Math.pow(t1, 3.0) / 3 - e * Math.pow(t1, -2.0) / 2);
                    } else if (spinner_heatcap_cp.getSelectedItem().toString().equals("c\u209A/R")) {
                        dH = r * (a * t2 + b * Math.pow(t2, 2.0) / 2 + c * Math.pow(t2, 3.0) / 3 + d * Math.pow(t2, 4.0) / 4 - e / t2)
                                - r * (a * t1 + b * Math.pow(t1, 2.0) / 2 + c * Math.pow(t1, 3.0) / 3 + d * Math.pow(t1, 4.0) / 4 - e / t1);
                        dU = dH - r * (t2 - t1);
                        dS = r * (a * Math.log(t2) + b * t2 + c * Math.pow(t2, 2.0) / 2 + d * Math.pow(t2, 3.0) / 3 - e * Math.pow(t2, -2.0) / 2)
                                - r * (a * Math.log(t1) + b * t1 + c * Math.pow(t1, 2.0) / 2 + d * Math.pow(t1, 3.0) / 3 - e * Math.pow(t1, -2.0) / 2);
                    } else {
                        Toast.makeText(HeatCap.this, "Please select type of c\u209A equation!", Toast.LENGTH_SHORT).show();
                    }
                    heatcap_h_output.setText(dH.toString());
                    heatcap_u_output.setText(dU.toString());
                    heatcap_s_output.setText(dS.toString());
                }
            }
        });

        TextView heatcap_a = (TextView) findViewById(R.id.heatcap_a);
        heatcap_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatCap.this, "Parameter A", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatcap_b = (TextView) findViewById(R.id.heatcap_b);
        heatcap_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatCap.this, "Parameter B", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatcap_c = (TextView) findViewById(R.id.heatcap_c);
        heatcap_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatCap.this, "Parameter C", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatcap_d = (TextView) findViewById(R.id.heatcap_d);
        heatcap_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatCap.this, "Parameter D", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatcap_e = (TextView) findViewById(R.id.heatcap_e);
        heatcap_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatCap.this, "Parameter E", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatcap_t1 = (TextView) findViewById(R.id.heatcap_t1);
        heatcap_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatCap.this, "Initial temperature T1 (presets use K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatcap_t2 = (TextView) findViewById(R.id.heatcap_t2);
        heatcap_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatCap.this, "Final temperature T2 (presets use K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatcap_h = (TextView) findViewById(R.id.heatcap_h);
        heatcap_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatCap.this, "Enthalpy \u0394H (presets use J mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatcap_u = (TextView) findViewById(R.id.heatcap_u);
        heatcap_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatCap.this, "Internal energy \u0394U (presets use J mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView heatcap_s = (TextView) findViewById(R.id.heatcap_s);
        heatcap_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HeatCap.this, "Isobaric entropy \u0394S (presets use J mol\u207B\u00B9 K\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
