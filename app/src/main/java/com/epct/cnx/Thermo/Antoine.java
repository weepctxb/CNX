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

public class Antoine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thermo_antoine);

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

        Spinner spinner_antoine_log = (Spinner) findViewById(R.id.antoine_log_input);
        ArrayAdapter<CharSequence> adapter_antoine_log = ArrayAdapter.createFromResource(this,
                R.array.antoine_log, android.R.layout.simple_spinner_item);
        adapter_antoine_log.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_antoine_log.setAdapter(adapter_antoine_log);

        Spinner spinner_antoine_presets = (Spinner) findViewById(R.id.antoine_presets_input);
        ArrayAdapter<CharSequence> adapter_antoine_presets = ArrayAdapter.createFromResource(this,
                R.array.compound_presets, android.R.layout.simple_spinner_item);
        adapter_antoine_presets.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_antoine_presets.setAdapter(adapter_antoine_presets);
        spinner_antoine_presets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Spinner spinner_antoine_log = (Spinner) findViewById(R.id.antoine_log_input);
                spinner_antoine_log.setSelection(0);

                if (pos > 0) {
                    TextView antoine_a_input = (TextView) findViewById(R.id.antoine_a_input);
                    TextView antoine_b_input = (TextView) findViewById(R.id.antoine_b_input);
                    TextView antoine_c_input = (TextView) findViewById(R.id.antoine_c_input);

                    Double a = Double.parseDouble(compounds.get(pos)[6]);
                    if (Math.abs(a+999.0)>0.001) {
                        antoine_a_input.setText(a.toString());
                    }
                    else {
                        antoine_a_input.setText("");
                    }
                    Double b = Double.parseDouble(compounds.get(pos)[7]);
                    if (Math.abs(b+999.0)>0.001) {
                        antoine_b_input.setText(b.toString());
                    }
                    else {
                        antoine_b_input.setText("");
                    }
                    Double c = Double.parseDouble(compounds.get(pos)[8]);
                    if (Math.abs(c+999.0)>0.001) {
                        antoine_c_input.setText(c.toString());
                    }
                    else {
                        antoine_c_input.setText("");
                    }
                }

                Toast.makeText(Antoine.this, "Natural logarithm is set for presets.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Button antoine_solve = (Button) findViewById(R.id.antoine_solve);
        antoine_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView antoine_temp_input = (TextView) findViewById(R.id.antoine_temp_input);
                TextView antoine_a_input = (TextView) findViewById(R.id.antoine_a_input);
                TextView antoine_b_input = (TextView) findViewById(R.id.antoine_b_input);
                TextView antoine_c_input = (TextView) findViewById(R.id.antoine_c_input);
                TextView antoine_pressure_output = (TextView) findViewById(R.id.antoine_pressure_output);
                Spinner spinner_antoine_log = (Spinner) findViewById(R.id.antoine_log_input);

                switch (spinner_antoine_log.getSelectedItemPosition()) {
                    case 0:
                        if (antoine_pressure_output.getText().length() == 0
                                & antoine_temp_input.getText().length() > 0
                                & antoine_a_input.getText().length() > 0
                                & antoine_b_input.getText().length() > 0
                                & antoine_c_input.getText().length() > 0) {
                            Double T = Double.parseDouble(antoine_temp_input.getText().toString());
                            Double A = Double.parseDouble(antoine_a_input.getText().toString());
                            Double B = Double.parseDouble(antoine_b_input.getText().toString());
                            Double C = Double.parseDouble(antoine_c_input.getText().toString());

                            Double P = Math.exp(A - B / (C + T));
                            antoine_pressure_output.setText(P.toString());
                        } else if (antoine_pressure_output.getText().length() > 0
                                & antoine_temp_input.getText().length() == 0
                                & antoine_a_input.getText().length() > 0
                                & antoine_b_input.getText().length() > 0
                                & antoine_c_input.getText().length() > 0) {
                            Double P = Double.parseDouble(antoine_pressure_output.getText().toString());
                            Double A = Double.parseDouble(antoine_a_input.getText().toString());
                            Double B = Double.parseDouble(antoine_b_input.getText().toString());
                            Double C = Double.parseDouble(antoine_c_input.getText().toString());

                            Double T = B / (A - Math.log(P)) - C;
                            antoine_temp_input.setText(T.toString());
                        } else if (antoine_pressure_output.getText().length() > 0
                                & antoine_temp_input.getText().length() > 0
                                & antoine_a_input.getText().length() == 0
                                & antoine_b_input.getText().length() > 0
                                & antoine_c_input.getText().length() > 0) {
                            Double P = Double.parseDouble(antoine_pressure_output.getText().toString());
                            Double T = Double.parseDouble(antoine_temp_input.getText().toString());
                            Double B = Double.parseDouble(antoine_b_input.getText().toString());
                            Double C = Double.parseDouble(antoine_c_input.getText().toString());

                            Double A = Math.log(P) + B / (T + C);
                            antoine_a_input.setText(A.toString());
                        } else if (antoine_pressure_output.getText().length() > 0
                                & antoine_temp_input.getText().length() > 0
                                & antoine_a_input.getText().length() > 0
                                & antoine_b_input.getText().length() == 0
                                & antoine_c_input.getText().length() > 0) {
                            Double P = Double.parseDouble(antoine_pressure_output.getText().toString());
                            Double T = Double.parseDouble(antoine_temp_input.getText().toString());
                            Double A = Double.parseDouble(antoine_a_input.getText().toString());
                            Double C = Double.parseDouble(antoine_c_input.getText().toString());

                            Double B = (A - Math.log(P)) * (T + C);
                            antoine_b_input.setText(B.toString());
                        } else if (antoine_pressure_output.getText().length() > 0
                                & antoine_temp_input.getText().length() > 0
                                & antoine_a_input.getText().length() > 0
                                & antoine_b_input.getText().length() > 0
                                & antoine_c_input.getText().length() == 0) {
                            Double P = Double.parseDouble(antoine_pressure_output.getText().toString());
                            Double T = Double.parseDouble(antoine_temp_input.getText().toString());
                            Double A = Double.parseDouble(antoine_a_input.getText().toString());
                            Double B = Double.parseDouble(antoine_b_input.getText().toString());

                            Double C = B / (A - Math.log(P)) - T;
                            antoine_c_input.setText(C.toString());
                        } else if (antoine_pressure_output.getText().length() > 0
                                & antoine_temp_input.getText().length() > 0
                                & antoine_a_input.getText().length() > 0
                                & antoine_b_input.getText().length() > 0
                                & antoine_c_input.getText().length() > 0) {
                            Toast.makeText(Antoine.this, "Over-determined system!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Antoine.this, "Under-determined system!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        if (antoine_pressure_output.getText().length() == 0
                                & antoine_temp_input.getText().length() > 0
                                & antoine_a_input.getText().length() > 0
                                & antoine_b_input.getText().length() > 0
                                & antoine_c_input.getText().length() > 0) {
                            Double T = Double.parseDouble(antoine_temp_input.getText().toString());
                            Double A = Double.parseDouble(antoine_a_input.getText().toString());
                            Double B = Double.parseDouble(antoine_b_input.getText().toString());
                            Double C = Double.parseDouble(antoine_c_input.getText().toString());

                            Double P = Math.pow(10.0,A - B / (C + T));
                            antoine_pressure_output.setText(P.toString());
                        } else if (antoine_pressure_output.getText().length() > 0
                                & antoine_temp_input.getText().length() == 0
                                & antoine_a_input.getText().length() > 0
                                & antoine_b_input.getText().length() > 0
                                & antoine_c_input.getText().length() > 0) {
                            Double P = Double.parseDouble(antoine_pressure_output.getText().toString());
                            Double A = Double.parseDouble(antoine_a_input.getText().toString());
                            Double B = Double.parseDouble(antoine_b_input.getText().toString());
                            Double C = Double.parseDouble(antoine_c_input.getText().toString());

                            Double T = B / (A - Math.log10(P)) - C;
                            antoine_temp_input.setText(T.toString());
                        } else if (antoine_pressure_output.getText().length() > 0
                                & antoine_temp_input.getText().length() > 0
                                & antoine_a_input.getText().length() == 0
                                & antoine_b_input.getText().length() > 0
                                & antoine_c_input.getText().length() > 0) {
                            Double P = Double.parseDouble(antoine_pressure_output.getText().toString());
                            Double T = Double.parseDouble(antoine_temp_input.getText().toString());
                            Double B = Double.parseDouble(antoine_b_input.getText().toString());
                            Double C = Double.parseDouble(antoine_c_input.getText().toString());

                            Double A = Math.log10(P) + B / (T + C);
                            antoine_a_input.setText(A.toString());
                        } else if (antoine_pressure_output.getText().length() > 0
                                & antoine_temp_input.getText().length() > 0
                                & antoine_a_input.getText().length() > 0
                                & antoine_b_input.getText().length() == 0
                                & antoine_c_input.getText().length() > 0) {
                            Double P = Double.parseDouble(antoine_pressure_output.getText().toString());
                            Double T = Double.parseDouble(antoine_temp_input.getText().toString());
                            Double A = Double.parseDouble(antoine_a_input.getText().toString());
                            Double C = Double.parseDouble(antoine_c_input.getText().toString());

                            Double B = (A - Math.log10(P)) * (T + C);
                            antoine_b_input.setText(B.toString());
                        } else if (antoine_pressure_output.getText().length() > 0
                                & antoine_temp_input.getText().length() > 0
                                & antoine_a_input.getText().length() > 0
                                & antoine_b_input.getText().length() > 0
                                & antoine_c_input.getText().length() == 0) {
                            Double P = Double.parseDouble(antoine_pressure_output.getText().toString());
                            Double T = Double.parseDouble(antoine_temp_input.getText().toString());
                            Double A = Double.parseDouble(antoine_a_input.getText().toString());
                            Double B = Double.parseDouble(antoine_b_input.getText().toString());

                            Double C = B / (A - Math.log10(P)) - T;
                            antoine_c_input.setText(C.toString());
                        } else if (antoine_pressure_output.getText().length() > 0
                                & antoine_temp_input.getText().length() > 0
                                & antoine_a_input.getText().length() > 0
                                & antoine_b_input.getText().length() > 0
                                & antoine_c_input.getText().length() > 0) {
                            Toast.makeText(Antoine.this, "Over-determined system!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Antoine.this, "Under-determined system!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });

        TextView antoine_temp = (TextView) findViewById(R.id.antoine_temp);
        antoine_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Antoine.this, "Temperature T (in \u00B0C)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView antoine_a = (TextView) findViewById(R.id.antoine_a);
        antoine_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Antoine.this, "Antoine parameter A (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView antoine_b = (TextView) findViewById(R.id.antoine_b);
        antoine_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Antoine.this, "Antoine parameter B (follows units of T)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView antoine_c = (TextView) findViewById(R.id.antoine_c);
        antoine_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Antoine.this, "Antoine parameter C (follows units of T)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView antoine_pressure = (TextView) findViewById(R.id.antoine_pressure);
        antoine_pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Antoine.this, "Vapour Pressure P (in kPa)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}