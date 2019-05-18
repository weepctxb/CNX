package com.epct.cnx.Oshe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.epct.cnx.R;

public class PasqGiff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oshe_pasqgiff);

        Spinner spinner_pasqgiff_model = (Spinner) findViewById(R.id.pasqgiff_model_input);
        ArrayAdapter<CharSequence> adapter_pasqgiff_model = ArrayAdapter.createFromResource(this,
                R.array.pasqgiff_model, android.R.layout.simple_spinner_item);
        adapter_pasqgiff_model.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pasqgiff_model.setAdapter(adapter_pasqgiff_model);

        Spinner spinner_pasqgiff_stability = (Spinner) findViewById(R.id.pasqgiff_stability_input);
        ArrayAdapter<CharSequence> adapter_pasqgiff_stability = ArrayAdapter.createFromResource(this,
                R.array.pasqgiff_stability, android.R.layout.simple_spinner_item);
        adapter_pasqgiff_stability.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pasqgiff_stability.setAdapter(adapter_pasqgiff_stability);

        Spinner spinner_pasqgiff_terrain = (Spinner) findViewById(R.id.pasqgiff_terrain_input);
        ArrayAdapter<CharSequence> adapter_pasqgiff_terrain = ArrayAdapter.createFromResource(this,
                R.array.pasqgiff_terrain, android.R.layout.simple_spinner_item);
        adapter_pasqgiff_terrain.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pasqgiff_terrain.setAdapter(adapter_pasqgiff_terrain);

        Button pasqgiff_solve = (Button) findViewById(R.id.pasqgiff_solve);
        pasqgiff_solve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Spinner spinner_pasqgiff_model = (Spinner) findViewById(R.id.pasqgiff_model_input);
                TextView pasqgiff_qm_input = (TextView) findViewById(R.id.pasqgiff_qm_input);
                TextView pasqgiff_t_input = (TextView) findViewById(R.id.pasqgiff_t_input);
                TextView pasqgiff_ux_input = (TextView) findViewById(R.id.pasqgiff_ux_input);
                Spinner spinner_pasqgiff_stability = (Spinner) findViewById(R.id.pasqgiff_stability_input);
                Spinner spinner_pasqgiff_terrain = (Spinner) findViewById(R.id.pasqgiff_terrain_input);
                TextView pasqgiff_x_input = (TextView) findViewById(R.id.pasqgiff_x_input);
                TextView pasqgiff_y_input = (TextView) findViewById(R.id.pasqgiff_y_input);
                TextView pasqgiff_z_input = (TextView) findViewById(R.id.pasqgiff_z_input);
                TextView pasqgiff_dxdy_output = (TextView) findViewById(R.id.pasqgiff_dxdy_output);
                TextView pasqgiff_dz_output = (TextView) findViewById(R.id.pasqgiff_dz_output);
                TextView pasqgiff_conc_output = (TextView) findViewById(R.id.pasqgiff_conc_output);
                TextView pasqgiff_maxconc_output = (TextView) findViewById(R.id.pasqgiff_maxconc_output);
                TextView pasqgiff_centrelineconc_output = (TextView) findViewById(R.id.pasqgiff_centrelineconc_output);

                Double qm = -1.0, t = -1.0, ux = -1.0, x = -1.0, y = -1.0, z = -1.0,
                        dxdy = -1.0, dz = -1.0, dxdym = -1.0, dzm = -1.0,
                        conc = -1.0, maxconc = -1.0, centrelineconc = -1.0;
                int unfilled = 0;

                Boolean puff = false, plume = false;
                int stability = -1;
                Boolean rural = false, urban = false;

                switch (spinner_pasqgiff_model.getSelectedItemPosition()) {
                    case 0: //puff
                        puff = true;
                        plume = false;
                        break;
                    case 1: //plume
                        plume = false;
                        plume = true;
                        break;
                }

                stability = 1 + spinner_pasqgiff_stability.getSelectedItemPosition();

                switch (spinner_pasqgiff_terrain.getSelectedItemPosition()) {
                    case 0: //rural
                        rural = true;
                        urban = false;
                        break;
                    case 1: //urban
                        rural = false;
                        urban = true;
                        break;
                }

                if (pasqgiff_qm_input.getText().length() > 0) {
                    qm = Double.parseDouble(pasqgiff_qm_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (puff) { //t only for puff model
                    if (pasqgiff_t_input.getText().length() > 0) {
                        t = Double.parseDouble(pasqgiff_t_input.getText().toString());
                    } else {
                        unfilled = unfilled + 1;
                    }
                }
                if (pasqgiff_ux_input.getText().length() > 0) {
                    ux = Double.parseDouble(pasqgiff_ux_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (pasqgiff_x_input.getText().length() > 0) {
                    x = Double.parseDouble(pasqgiff_x_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (pasqgiff_y_input.getText().length() > 0) {
                    y = Double.parseDouble(pasqgiff_y_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }
                if (pasqgiff_z_input.getText().length() > 0) {
                    z = Double.parseDouble(pasqgiff_z_input.getText().toString());
                } else {
                    unfilled = unfilled + 1;
                }

                if (unfilled > 0) {
                    Toast.makeText(PasqGiff.this, "Please ensure all fields are filled!\n(Except for t which is only for puff model)", Toast.LENGTH_SHORT).show();
                }
                else if (x<100.0) {
                    Toast.makeText(PasqGiff.this, "x coordinate is too near to release point (<100 m, near field)\nfor Pasquill-Gifford to be valid!", Toast.LENGTH_SHORT).show();
                }
                else if (x>10000.0) {
                    Toast.makeText(PasqGiff.this, "x coordinate is too far from release point (>10 km\nfor Pasquill-Gifford to be valid!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (puff) {
                        switch (stability) {
                            case 1:
                                dxdy = 0.18 * Math.pow(x, 0.92);
                                dz = 0.60 * Math.pow(x, 0.75);
                                dxdym = 0.18 * Math.pow(ux * t, 0.92);
                                dzm = 0.60 * Math.pow(ux * t, 0.75);
                                break;
                            case 2:
                                dxdy = 0.14 * Math.pow(x, 0.92);
                                dz = 0.53 * Math.pow(x, 0.73);
                                dxdym = 0.14 * Math.pow(ux * t, 0.92);
                                dzm = 0.53 * Math.pow(ux * t, 0.73);
                                break;
                            case 3:
                                dxdy = 0.10 * Math.pow(x, 0.92);
                                dz = 0.34 * Math.pow(x, 0.71);
                                dxdym = 0.10 * Math.pow(ux * t, 0.92);
                                dzm = 0.34 * Math.pow(ux * t, 0.71);
                                break;
                            case 4:
                                dxdy = 0.06 * Math.pow(x, 0.92);
                                dz = 0.15 * Math.pow(x, 0.70);
                                dxdym = 0.06 * Math.pow(ux * t, 0.92);
                                dzm = 0.15 * Math.pow(ux * t, 0.70);
                                break;
                            case 5:
                                dxdy = 0.04 * Math.pow(x, 0.92);
                                dz = 0.10 * Math.pow(x, 0.65);
                                dxdym = 0.04 * Math.pow(ux * t, 0.92);
                                dzm = 0.10 * Math.pow(ux * t, 0.65);
                                break;
                            case 6:
                                dxdy = 0.02 * Math.pow(x, 0.89);
                                dz = 0.05 * Math.pow(x, 0.61);
                                dxdym = 0.02 * Math.pow(ux * t, 0.89);
                                dzm = 0.05 * Math.pow(ux * t, 0.61);
                                break;
                        }
                    } else if (plume && rural) {
                        switch (stability) {
                            case 1:
                                dxdy = 0.22 * x * Math.pow(1 + 0.0001 * x, -0.5);
                                dz = 0.20 * x;
                                break;
                            case 2:
                                dxdy = 0.16 * x * Math.pow(1 + 0.0001 * x, -0.5);
                                dz = 0.12 * x;
                                break;
                            case 3:
                                dxdy = 0.11 * x * Math.pow(1 + 0.0001 * x, -0.5);
                                dz = 0.08 * x * Math.pow(1 + 0.0002 * x, -0.5);
                                break;
                            case 4:
                                dxdy = 0.08 * x * Math.pow(1 + 0.0001 * x, -0.5);
                                dz = 0.06 * x * Math.pow(1 + 0.0015 * x, -0.5);
                                break;
                            case 5:
                                dxdy = 0.06 * x * Math.pow(1 + 0.0001 * x, -0.5);
                                dz = 0.03 * x * Math.pow(1 + 0.0003 * x, -1.0);
                                break;
                            case 6:
                                dxdy = 0.04 * x * Math.pow(1 + 0.0001 * x, -0.5);
                                dz = 0.016 * x * Math.pow(1 + 0.0003 * x, -1.0);
                                break;
                        }
                    } else if (plume && urban) {
                        switch (stability) {
                            case 1:
                                dxdy = 0.32 * x * Math.pow(1 + 0.0004 * x, -0.5);
                                dz = 0.24 * x * Math.pow(1 + 0.001 * x, 0.5);
                                break;
                            case 2:
                                dxdy = 0.32 * x * Math.pow(1 + 0.0004 * x, -0.5);
                                dz = 0.24 * x * Math.pow(1 + 0.001 * x, 0.5);
                                break;
                            case 3:
                                dxdy = 0.22 * x * Math.pow(1 + 0.0004 * x, -0.5);
                                dz = 0.20 * x;
                                break;
                            case 4:
                                dxdy = 0.16 * x * Math.pow(1 + 0.0004 * x, -0.5);
                                dz = 0.14 * x * Math.pow(1 + 0.0003 * x, -0.5);
                                break;
                            case 5:
                                dxdy = 0.11 * x * Math.pow(1 + 0.0004 * x, -0.5);
                                dz = 0.08 * x * Math.pow(1 + 0.0015 * x, -0.5);
                                break;
                            case 6:
                                dxdy = 0.11 * x * Math.pow(1 + 0.0004 * x, -0.5);
                                dz = 0.08 * x * Math.pow(1 + 0.0015 * x, -0.5);
                                break;
                        }
                    }

                    if (puff) {
                        conc = qm / (Math.sqrt(2) * Math.pow(Math.PI, 1.5) * dxdy * dxdy * dz)
                                * Math.exp(-0.5 * (
                                        Math.pow((x - ux * t) / dxdy, 2.0)
                                                + Math.pow(y / dxdy, 2.0)
                                                + Math.pow(z / dz, 2.0))
                        );
                        maxconc = qm / (Math.sqrt(2) * Math.pow(Math.PI, 1.5) * dxdym * dxdym * dzm);
                        centrelineconc = qm / (Math.sqrt(2) * Math.pow(Math.PI, 1.5) * dxdy * dxdy * dz)
                                * Math.exp(-0.5 * (Math.pow((x - ux * t) / dxdy, 2.0))
                        );

                        pasqgiff_dxdy_output.setText(dxdy.toString());
                        pasqgiff_dz_output.setText(dz.toString());
                        pasqgiff_conc_output.setText(conc.toString());
                        pasqgiff_maxconc_output.setText(maxconc.toString());
                        pasqgiff_centrelineconc_output.setText(centrelineconc.toString());
                    } else if (plume) {
                        conc = qm / (Math.PI * dxdy * dz * ux) * Math.exp(-0.5 * (
                                Math.pow(y / dxdy, 2.0) + Math.pow(z / dz, 2.0)
                        ));
                        centrelineconc = qm / (Math.PI * dxdy * dz);

                        pasqgiff_dxdy_output.setText(dxdy.toString());
                        pasqgiff_dz_output.setText(dz.toString());
                        pasqgiff_conc_output.setText(conc.toString());
                        pasqgiff_maxconc_output.setText("At release point, but conc. is undefined.");
                        pasqgiff_centrelineconc_output.setText(centrelineconc.toString());
                    }
                }

            }
        });

        TextView pasqgiff_model = (TextView) findViewById(R.id.pasqgiff_model);
        pasqgiff_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "Puff (instantaneous release, time-variant)\nor Plume (steady-state continuous release)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_qm = (TextView) findViewById(R.id.pasqgiff_qm);
        pasqgiff_qm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "Release mass (in kg) for puff\nRelease mass rate (in kg/s) for plume", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_t = (TextView) findViewById(R.id.pasqgiff_t);
        pasqgiff_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "Release duration (in s). For puff only.\nFor plume leave this blank (since steady-state).", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_ux = (TextView) findViewById(R.id.pasqgiff_ux);
        pasqgiff_ux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "Wind speed in x-direction (in m/s)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_stability = (TextView) findViewById(R.id.pasqgiff_stability);
        pasqgiff_stability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "Pasquill stability class (A to F)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_terrain = (TextView) findViewById(R.id.pasqgiff_terrain);
        pasqgiff_terrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "Terrain (rural or urban)\nThis doesn't matter for puff model.\nUse rural for larger dispersion estimate.", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_x = (TextView) findViewById(R.id.pasqgiff_x);
        pasqgiff_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "x coordinate (in m)\nPasquill-Gifford only valid for 100 m < x < 10 km", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_y = (TextView) findViewById(R.id.pasqgiff_y);
        pasqgiff_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "y coordinate (in m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_z = (TextView) findViewById(R.id.pasqgiff_z);
        pasqgiff_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "z coordinate (in m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_dxdy = (TextView) findViewById(R.id.pasqgiff_dxdy);
        pasqgiff_dxdy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "Dispersion coefficient along the x/y-direction (in m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_dz = (TextView) findViewById(R.id.pasqgiff_dz);
        pasqgiff_dz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "Dispersion coefficient along the z-direction (in m)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_conc = (TextView) findViewById(R.id.pasqgiff_conc);
        pasqgiff_conc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "Mass concentration at (x,y,z) (in kg/m\u00b3)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_maxconc = (TextView) findViewById(R.id.pasqgiff_maxconc);
        pasqgiff_maxconc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "Maximum concentration (in kg/m³)\nFor puff: At puff location (u\u2093t,0,0)\nFor plume: At release point (0,0,0) but this is undefined", Toast.LENGTH_SHORT).show();
            }
        });
        TextView pasqgiff_centrelineconc = (TextView) findViewById(R.id.pasqgiff_centrelineconc);
        pasqgiff_centrelineconc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PasqGiff.this, "Centreline concentration (in kg/m³)\nAt (x,0,0)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}