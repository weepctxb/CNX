package com.epct.cnx.Thermo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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

public class DpBbp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thermo_dpbbp);

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

        Spinner spinner_dpbbp_system = (Spinner) findViewById(R.id.dpbbp_system_input);
        ArrayAdapter<CharSequence> adapter_dpbbp_system = ArrayAdapter.createFromResource(this,
                R.array.dpbbp_system, android.R.layout.simple_spinner_item);
        adapter_dpbbp_system.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dpbbp_system.setAdapter(adapter_dpbbp_system);

        Spinner spinner_dpbbp_mode = (Spinner) findViewById(R.id.dpbbp_mode_input);
        ArrayAdapter<CharSequence> adapter_dpbbp_mode = ArrayAdapter.createFromResource(this,
                R.array.dpbbp_mode, android.R.layout.simple_spinner_item);
        adapter_dpbbp_mode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dpbbp_mode.setAdapter(adapter_dpbbp_mode);

        Spinner spinner_dpbbp_components = (Spinner) findViewById(R.id.dpbbp_components_input);
        ArrayAdapter<CharSequence> adapter_dpbbp_components = ArrayAdapter.createFromResource(this,
                R.array.dpbbp_components, android.R.layout.simple_spinner_item);
        adapter_dpbbp_components.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dpbbp_components.setAdapter(adapter_dpbbp_components);

        Spinner spinner_dpbbp_preset1 = (Spinner) findViewById(R.id.dpbbp_preset1_input);
        ArrayAdapter<CharSequence> adapter_dpbbp_preset1 = ArrayAdapter.createFromResource(this,
                R.array.compound_presets, android.R.layout.simple_spinner_item);
        adapter_dpbbp_preset1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dpbbp_preset1.setAdapter(adapter_dpbbp_preset1);
        spinner_dpbbp_preset1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    TextView dpbbp_a1_input = (TextView) findViewById(R.id.dpbbp_a1_input);
                    TextView dpbbp_b1_input = (TextView) findViewById(R.id.dpbbp_b1_input);
                    TextView dpbbp_c1_input = (TextView) findViewById(R.id.dpbbp_c1_input);
                    TextView dpbbp_w1_input = (TextView) findViewById(R.id.dpbbp_w1_input);
                    TextView dpbbp_tc1_input = (TextView) findViewById(R.id.dpbbp_tc1_input);
                    TextView dpbbp_pc1_input = (TextView) findViewById(R.id.dpbbp_pc1_input);
                    TextView dpbbp_vc1_input = (TextView) findViewById(R.id.dpbbp_vc1_input);
                    TextView dpbbp_zc1_input = (TextView) findViewById(R.id.dpbbp_zc1_input);

                    Double a = Double.parseDouble(compounds.get(pos)[6]);
                    if (Math.abs(a+999.0)>0.001) {
                        dpbbp_a1_input.setText(a.toString());
                    }
                    else {
                        dpbbp_a1_input.setText("");
                    }
                    Double b = Double.parseDouble(compounds.get(pos)[7]);
                    if (Math.abs(b+999.0)>0.001) {
                        dpbbp_b1_input.setText(b.toString());
                    }
                    else {
                        dpbbp_b1_input.setText("");
                    }
                    Double c = Double.parseDouble(compounds.get(pos)[8]);
                    if (Math.abs(c+999.0)>0.001) {
                        dpbbp_c1_input.setText(c.toString());
                    }
                    else {
                        dpbbp_c1_input.setText("");
                    }
                    Double w = Double.parseDouble(compounds.get(pos)[3]);
                    if (Math.abs(w+999.0)>0.001) {
                        dpbbp_w1_input.setText(w.toString());
                    }
                    else {
                        dpbbp_w1_input.setText("");
                    }
                    Double tc = Double.parseDouble(compounds.get(pos)[1]);
                    if (Math.abs(tc+999.0)>0.001) {
                        dpbbp_tc1_input.setText(tc.toString());
                    }
                    else {
                        dpbbp_tc1_input.setText("");
                    }
                    Double pc = Double.parseDouble(compounds.get(pos)[2]);
                    if (Math.abs(pc+999.0)>0.001) {
                        dpbbp_pc1_input.setText(pc.toString());
                    }
                    else {
                        dpbbp_pc1_input.setText("");
                    }
                    Double vc = Double.parseDouble(compounds.get(pos)[4]);
                    if (Math.abs(vc+999.0)>0.001) {
                        dpbbp_vc1_input.setText(vc.toString());
                    }
                    else {
                        dpbbp_vc1_input.setText("");
                    }
                    Double zc = Double.parseDouble(compounds.get(pos)[5]);
                    if (Math.abs(zc+999.0)>0.001) {
                        dpbbp_zc1_input.setText(zc.toString());
                    }
                    else {
                        dpbbp_zc1_input.setText("");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Spinner spinner_dpbbp_preset2 = (Spinner) findViewById(R.id.dpbbp_preset2_input);
        ArrayAdapter<CharSequence> adapter_dpbbp_preset2 = ArrayAdapter.createFromResource(this,
                R.array.compound_presets, android.R.layout.simple_spinner_item);
        adapter_dpbbp_preset2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dpbbp_preset2.setAdapter(adapter_dpbbp_preset2);
        spinner_dpbbp_preset2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    TextView dpbbp_a2_input = (TextView) findViewById(R.id.dpbbp_a2_input);
                    TextView dpbbp_b2_input = (TextView) findViewById(R.id.dpbbp_b2_input);
                    TextView dpbbp_c2_input = (TextView) findViewById(R.id.dpbbp_c2_input);
                    TextView dpbbp_w2_input = (TextView) findViewById(R.id.dpbbp_w2_input);
                    TextView dpbbp_tc2_input = (TextView) findViewById(R.id.dpbbp_tc2_input);
                    TextView dpbbp_pc2_input = (TextView) findViewById(R.id.dpbbp_pc2_input);
                    TextView dpbbp_vc2_input = (TextView) findViewById(R.id.dpbbp_vc2_input);
                    TextView dpbbp_zc2_input = (TextView) findViewById(R.id.dpbbp_zc2_input);

                    Double a = Double.parseDouble(compounds.get(pos)[6]);
                    if (Math.abs(a+999.0)>0.001) {
                        dpbbp_a2_input.setText(a.toString());
                    }
                    else {
                        dpbbp_a2_input.setText("");
                    }
                    Double b = Double.parseDouble(compounds.get(pos)[7]);
                    if (Math.abs(b+999.0)>0.001) {
                        dpbbp_b2_input.setText(b.toString());
                    }
                    else {
                        dpbbp_b2_input.setText("");
                    }
                    Double c = Double.parseDouble(compounds.get(pos)[8]);
                    if (Math.abs(c+999.0)>0.001) {
                        dpbbp_c2_input.setText(c.toString());
                    }
                    else {
                        dpbbp_c2_input.setText("");
                    }
                    Double w = Double.parseDouble(compounds.get(pos)[3]);
                    if (Math.abs(w+999.0)>0.001) {
                        dpbbp_w2_input.setText(w.toString());
                    }
                    else {
                        dpbbp_w2_input.setText("");
                    }
                    Double tc = Double.parseDouble(compounds.get(pos)[1]);
                    if (Math.abs(tc+999.0)>0.001) {
                        dpbbp_tc2_input.setText(tc.toString());
                    }
                    else {
                        dpbbp_tc2_input.setText("");
                    }
                    Double pc = Double.parseDouble(compounds.get(pos)[2]);
                    if (Math.abs(pc+999.0)>0.001) {
                        dpbbp_pc2_input.setText(pc.toString());
                    }
                    else {
                        dpbbp_pc2_input.setText("");
                    }
                    Double vc = Double.parseDouble(compounds.get(pos)[4]);
                    if (Math.abs(vc+999.0)>0.001) {
                        dpbbp_vc2_input.setText(vc.toString());
                    }
                    else {
                        dpbbp_vc2_input.setText("");
                    }
                    Double zc = Double.parseDouble(compounds.get(pos)[5]);
                    if (Math.abs(zc+999.0)>0.001) {
                        dpbbp_zc2_input.setText(zc.toString());
                    }
                    else {
                        dpbbp_zc2_input.setText("");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Spinner spinner_dpbbp_preset3 = (Spinner) findViewById(R.id.dpbbp_preset3_input);
        ArrayAdapter<CharSequence> adapter_dpbbp_preset3 = ArrayAdapter.createFromResource(this,
                R.array.compound_presets, android.R.layout.simple_spinner_item);
        adapter_dpbbp_preset3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dpbbp_preset3.setAdapter(adapter_dpbbp_preset3);
        spinner_dpbbp_preset3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    TextView dpbbp_a3_input = (TextView) findViewById(R.id.dpbbp_a3_input);
                    TextView dpbbp_b3_input = (TextView) findViewById(R.id.dpbbp_b3_input);
                    TextView dpbbp_c3_input = (TextView) findViewById(R.id.dpbbp_c3_input);

                    Double a = Double.parseDouble(compounds.get(pos)[6]);
                    if (Math.abs(a+999.0)>0.001) {
                        dpbbp_a3_input.setText(a.toString());
                    }
                    else {
                        dpbbp_a3_input.setText("");
                    }
                    Double b = Double.parseDouble(compounds.get(pos)[7]);
                    if (Math.abs(b+999.0)>0.001) {
                        dpbbp_b3_input.setText(b.toString());
                    }
                    else {
                        dpbbp_b3_input.setText("");
                    }
                    Double c = Double.parseDouble(compounds.get(pos)[8]);
                    if (Math.abs(c+999.0)>0.001) {
                        dpbbp_c3_input.setText(c.toString());
                    }
                    else {
                        dpbbp_c3_input.setText("");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Spinner spinner_dpbbp_preset4 = (Spinner) findViewById(R.id.dpbbp_preset4_input);
        ArrayAdapter<CharSequence> adapter_dpbbp_preset4 = ArrayAdapter.createFromResource(this,
                R.array.compound_presets, android.R.layout.simple_spinner_item);
        adapter_dpbbp_preset4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dpbbp_preset4.setAdapter(adapter_dpbbp_preset4);
        spinner_dpbbp_preset4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    TextView dpbbp_a4_input = (TextView) findViewById(R.id.dpbbp_a4_input);
                    TextView dpbbp_b4_input = (TextView) findViewById(R.id.dpbbp_b4_input);
                    TextView dpbbp_c4_input = (TextView) findViewById(R.id.dpbbp_c4_input);

                    Double a = Double.parseDouble(compounds.get(pos)[6]);
                    if (Math.abs(a+999.0)>0.001) {
                        dpbbp_a4_input.setText(a.toString());
                    }
                    else {
                        dpbbp_a4_input.setText("");
                    }
                    Double b = Double.parseDouble(compounds.get(pos)[7]);
                    if (Math.abs(b+999.0)>0.001) {
                        dpbbp_b4_input.setText(b.toString());
                    }
                    else {
                        dpbbp_b4_input.setText("");
                    }
                    Double c = Double.parseDouble(compounds.get(pos)[8]);
                    if (Math.abs(c+999.0)>0.001) {
                        dpbbp_c4_input.setText(c.toString());
                    }
                    else {
                        dpbbp_c4_input.setText("");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Button dpbbp_display = (Button) findViewById(R.id.dpbbp_display);
        dpbbp_display.setOnClickListener(new View.OnClickListener() {
            Spinner spinner_dpbbp_system = (Spinner) findViewById(R.id.dpbbp_system_input);
            Spinner spinner_dpbbp_components = (Spinner) findViewById(R.id.dpbbp_components_input);
            Spinner spinner_dpbbp_mode = (Spinner) findViewById(R.id.dpbbp_mode_input);
            LinearLayout dpbbp_activities_row = (LinearLayout) findViewById(R.id.dpbbp_activities_row);
            LinearLayout dpbbp_activities_row2 = (LinearLayout) findViewById(R.id.dpbbp_activities_row2);
            LinearLayout dpbbp_activities_row3 = (LinearLayout) findViewById(R.id.dpbbp_activities_row3);
            LinearLayout dpbbp_preset1_row = (LinearLayout) findViewById(R.id.dpbbp_preset1_row);
            LinearLayout dpbbp_preset2_row = (LinearLayout) findViewById(R.id.dpbbp_preset2_row);
            LinearLayout dpbbp_preset3_row = (LinearLayout) findViewById(R.id.dpbbp_preset3_row);
            LinearLayout dpbbp_preset4_row = (LinearLayout) findViewById(R.id.dpbbp_preset4_row);
            LinearLayout dpbbp_main = (LinearLayout) findViewById(R.id.dpbbp_main);
            TextView dpbbp_t_input = (TextView) findViewById(R.id.dpbbp_t_input);
            TextView dpbbp_p_input = (TextView) findViewById(R.id.dpbbp_p_input);
            Button dpbbp_display = (Button) findViewById(R.id.dpbbp_display);
            Button dpbbp_reset = (Button) findViewById(R.id.dpbbp_reset);
            Button dpbbp_igid_solve = (Button) findViewById(R.id.dpbbp_igid_solve);
            Button dpbbp_ignid_solve = (Button) findViewById(R.id.dpbbp_ignid_solve);
            Button dpbbp_nignid_solve = (Button) findViewById(R.id.dpbbp_nignid_solve);
            LinearLayout dpbbp_z_row = (LinearLayout) findViewById(R.id.dpbbp_z_row);
            LinearLayout dpbbp_w_row = (LinearLayout) findViewById(R.id.dpbbp_w_row);
            LinearLayout dpbbp_tc_row = (LinearLayout) findViewById(R.id.dpbbp_tc_row);
            LinearLayout dpbbp_pc_row = (LinearLayout) findViewById(R.id.dpbbp_pc_row);
            LinearLayout dpbbp_vc_row = (LinearLayout) findViewById(R.id.dpbbp_vc_row);
            LinearLayout dpbbp_zc_row = (LinearLayout) findViewById(R.id.dpbbp_zc_row);
            LinearLayout dpbbp_gamma_row = (LinearLayout) findViewById(R.id.dpbbp_gamma_row);
            LinearLayout dpbbp_phi_row = (LinearLayout) findViewById(R.id.dpbbp_phi_row);
            LinearLayout dpbbp_phisat_row = (LinearLayout) findViewById(R.id.dpbbp_phisat_row);
            LinearLayout dpbbp_pcr_row = (LinearLayout) findViewById(R.id.dpbbp_pcr_row);
            TextView dpbbp_3 = (TextView) findViewById(R.id.dpbbp_3);
            TextView dpbbp_4 = (TextView) findViewById(R.id.dpbbp_4);
            TextView dpbbp_x1_input = (TextView) findViewById(R.id.dpbbp_x1_input);
            TextView dpbbp_x2_input = (TextView) findViewById(R.id.dpbbp_x2_input);
            TextView dpbbp_x3_input = (TextView) findViewById(R.id.dpbbp_x3_input);
            TextView dpbbp_x4_input = (TextView) findViewById(R.id.dpbbp_x4_input);
            TextView dpbbp_y1_input = (TextView) findViewById(R.id.dpbbp_y1_input);
            TextView dpbbp_y2_input = (TextView) findViewById(R.id.dpbbp_y2_input);
            TextView dpbbp_y3_input = (TextView) findViewById(R.id.dpbbp_y3_input);
            TextView dpbbp_y4_input = (TextView) findViewById(R.id.dpbbp_y4_input);
            TextView dpbbp_z1_input = (TextView) findViewById(R.id.dpbbp_z1_input);
            TextView dpbbp_z2_input = (TextView) findViewById(R.id.dpbbp_z2_input);
            TextView dpbbp_z3_input = (TextView) findViewById(R.id.dpbbp_z3_input);
            TextView dpbbp_z4_input = (TextView) findViewById(R.id.dpbbp_z4_input);
            TextView dpbbp_a1_input = (TextView) findViewById(R.id.dpbbp_a1_input);
            TextView dpbbp_a2_input = (TextView) findViewById(R.id.dpbbp_a2_input);
            TextView dpbbp_a3_input = (TextView) findViewById(R.id.dpbbp_a3_input);
            TextView dpbbp_a4_input = (TextView) findViewById(R.id.dpbbp_a4_input);
            TextView dpbbp_b1_input = (TextView) findViewById(R.id.dpbbp_b1_input);
            TextView dpbbp_b2_input = (TextView) findViewById(R.id.dpbbp_b2_input);
            TextView dpbbp_b3_input = (TextView) findViewById(R.id.dpbbp_b3_input);
            TextView dpbbp_b4_input = (TextView) findViewById(R.id.dpbbp_b4_input);
            TextView dpbbp_c1_input = (TextView) findViewById(R.id.dpbbp_c1_input);
            TextView dpbbp_c2_input = (TextView) findViewById(R.id.dpbbp_c2_input);
            TextView dpbbp_c3_input = (TextView) findViewById(R.id.dpbbp_c3_input);
            TextView dpbbp_c4_input = (TextView) findViewById(R.id.dpbbp_c4_input);
            View dpbbp_w3_placeholder = findViewById(R.id.dpbbp_w3_placeholder);
            View dpbbp_w4_placeholder = findViewById(R.id.dpbbp_w4_placeholder);
            View dpbbp_tc3_placeholder = findViewById(R.id.dpbbp_tc3_placeholder);
            View dpbbp_tc4_placeholder = findViewById(R.id.dpbbp_tc4_placeholder);
            View dpbbp_pc3_placeholder = findViewById(R.id.dpbbp_pc3_placeholder);
            View dpbbp_pc4_placeholder = findViewById(R.id.dpbbp_pc4_placeholder);
            View dpbbp_vc3_placeholder = findViewById(R.id.dpbbp_vc3_placeholder);
            View dpbbp_vc4_placeholder = findViewById(R.id.dpbbp_vc4_placeholder);
            View dpbbp_zc3_placeholder = findViewById(R.id.dpbbp_zc3_placeholder);
            View dpbbp_zc4_placeholder = findViewById(R.id.dpbbp_zc4_placeholder);
            TextView dpbbp_gamma1_input = (TextView) findViewById(R.id.dpbbp_gamma1_input);
            TextView dpbbp_gamma2_input = (TextView) findViewById(R.id.dpbbp_gamma2_input);
            TextView dpbbp_gamma3_input = (TextView) findViewById(R.id.dpbbp_gamma3_input);
            View dpbbp_gamma4_placeholder = findViewById(R.id.dpbbp_gamma4_placeholder);
            TextView dpbbp_phi1_input = (TextView) findViewById(R.id.dpbbp_phi1_input);
            TextView dpbbp_phi2_input = (TextView) findViewById(R.id.dpbbp_phi2_input);
            View dpbbp_phi3_placeholder = findViewById(R.id.dpbbp_phi3_placeholder);
            View dpbbp_phi4_placeholder = findViewById(R.id.dpbbp_phi4_placeholder);
            TextView dpbbp_phisat1_input = (TextView) findViewById(R.id.dpbbp_phisat1_input);
            TextView dpbbp_phisat2_input = (TextView) findViewById(R.id.dpbbp_phisat2_input);
            View dpbbp_phisat3_placeholder = findViewById(R.id.dpbbp_phisat3_placeholder);
            View dpbbp_phisat4_placeholder = findViewById(R.id.dpbbp_phisat4_placeholder);
            TextView dpbbp_pcr1_input = (TextView) findViewById(R.id.dpbbp_pcr1_input);
            TextView dpbbp_pcr2_input = (TextView) findViewById(R.id.dpbbp_pcr2_input);
            View dpbbp_pcr3_placeholder = findViewById(R.id.dpbbp_pcr3_placeholder);
            View dpbbp_pcr4_placeholder = findViewById(R.id.dpbbp_pcr4_placeholder);

            @Override
            public void onClick(View v) {
                switch (spinner_dpbbp_system.getSelectedItemPosition()) {
                    case 0:
                        //Ideal/non-ideal not selected
                        Toast.makeText(DpBbp.this, "Please select system type!", Toast.LENGTH_SHORT).show();
                        dpbbp_main.setVisibility(View.GONE);
                        dpbbp_igid_solve.setVisibility(View.GONE);
                        dpbbp_ignid_solve.setVisibility(View.GONE);
                        dpbbp_nignid_solve.setVisibility(View.GONE);
                        dpbbp_gamma_row.setVisibility(View.GONE);
                        dpbbp_phi_row.setVisibility(View.GONE);
                        dpbbp_phisat_row.setVisibility(View.GONE);
                        dpbbp_pcr_row.setVisibility(View.GONE);
                        dpbbp_activities_row.setVisibility(View.GONE);
                        dpbbp_activities_row2.setVisibility(View.GONE);
                        dpbbp_activities_row3.setVisibility(View.GONE);
                        break;
                    case 1:
                        //Ideal gas, ideal solution
                        dpbbp_main.setVisibility(View.VISIBLE);
                        dpbbp_igid_solve.setVisibility(View.VISIBLE);
                        dpbbp_ignid_solve.setVisibility(View.GONE);
                        dpbbp_nignid_solve.setVisibility(View.GONE);
                        dpbbp_gamma_row.setVisibility(View.GONE);
                        dpbbp_w_row.setVisibility(View.GONE);
                        dpbbp_tc_row.setVisibility(View.GONE);
                        dpbbp_pc_row.setVisibility(View.GONE);
                        dpbbp_vc_row.setVisibility(View.GONE);
                        dpbbp_zc_row.setVisibility(View.GONE);
                        dpbbp_phi_row.setVisibility(View.GONE);
                        dpbbp_phisat_row.setVisibility(View.GONE);
                        dpbbp_pcr_row.setVisibility(View.GONE);
                        dpbbp_activities_row.setVisibility(View.GONE);
                        dpbbp_activities_row2.setVisibility(View.GONE);
                        dpbbp_activities_row3.setVisibility(View.GONE);
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 0:
                                //IGID > No. of components not selected
                                Toast.makeText(DpBbp.this, "Please select number of components in mixture!", Toast.LENGTH_SHORT).show();
                                dpbbp_main.setVisibility(View.GONE);
                                dpbbp_igid_solve.setVisibility(View.GONE);
                                break;
                            case 1:
                                //IGID > 2 components
                                dpbbp_3.setVisibility(View.GONE);
                                dpbbp_4.setVisibility(View.GONE);
                                dpbbp_x3_input.setVisibility(View.GONE);
                                dpbbp_x4_input.setVisibility(View.GONE);
                                dpbbp_y3_input.setVisibility(View.GONE);
                                dpbbp_y4_input.setVisibility(View.GONE);
                                dpbbp_z3_input.setVisibility(View.GONE);
                                dpbbp_z4_input.setVisibility(View.GONE);
                                dpbbp_a3_input.setVisibility(View.GONE);
                                dpbbp_a4_input.setVisibility(View.GONE);
                                dpbbp_b3_input.setVisibility(View.GONE);
                                dpbbp_b4_input.setVisibility(View.GONE);
                                dpbbp_c3_input.setVisibility(View.GONE);
                                dpbbp_c4_input.setVisibility(View.GONE);
                                dpbbp_gamma3_input.setVisibility(View.GONE);
                                dpbbp_gamma4_placeholder.setVisibility(View.GONE);
                                dpbbp_phi3_placeholder.setVisibility(View.GONE);
                                dpbbp_phi4_placeholder.setVisibility(View.GONE);
                                dpbbp_phisat3_placeholder.setVisibility(View.GONE);
                                dpbbp_phisat4_placeholder.setVisibility(View.GONE);
                                dpbbp_pcr3_placeholder.setVisibility(View.GONE);
                                dpbbp_pcr4_placeholder.setVisibility(View.GONE);
                                spinner_dpbbp_system.setEnabled(false);
                                spinner_dpbbp_components.setEnabled(false);
                                spinner_dpbbp_mode.setEnabled(false);
                                dpbbp_display.setVisibility(View.GONE);
                                dpbbp_reset.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                //IGID > 3 components
                                dpbbp_3.setVisibility(View.VISIBLE);
                                dpbbp_4.setVisibility(View.GONE);
                                dpbbp_x3_input.setVisibility(View.VISIBLE);
                                dpbbp_x4_input.setVisibility(View.GONE);
                                dpbbp_y3_input.setVisibility(View.VISIBLE);
                                dpbbp_y4_input.setVisibility(View.GONE);
                                dpbbp_z3_input.setVisibility(View.VISIBLE);
                                dpbbp_z4_input.setVisibility(View.GONE);
                                dpbbp_a3_input.setVisibility(View.VISIBLE);
                                dpbbp_a4_input.setVisibility(View.GONE);
                                dpbbp_b3_input.setVisibility(View.VISIBLE);
                                dpbbp_b4_input.setVisibility(View.GONE);
                                dpbbp_c3_input.setVisibility(View.VISIBLE);
                                dpbbp_c4_input.setVisibility(View.GONE);
                                dpbbp_gamma3_input.setVisibility(View.VISIBLE);
                                dpbbp_gamma4_placeholder.setVisibility(View.GONE);
                                dpbbp_phi3_placeholder.setVisibility(View.VISIBLE);
                                dpbbp_phi4_placeholder.setVisibility(View.GONE);
                                dpbbp_phisat3_placeholder.setVisibility(View.VISIBLE);
                                dpbbp_phisat4_placeholder.setVisibility(View.GONE);
                                dpbbp_pcr3_placeholder.setVisibility(View.VISIBLE);
                                dpbbp_pcr4_placeholder.setVisibility(View.GONE);
                                spinner_dpbbp_system.setEnabled(false);
                                spinner_dpbbp_components.setEnabled(false);
                                spinner_dpbbp_mode.setEnabled(false);
                                dpbbp_display.setVisibility(View.GONE);
                                dpbbp_reset.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                //IGID > 4 components
                                dpbbp_3.setVisibility(View.VISIBLE);
                                dpbbp_4.setVisibility(View.VISIBLE);
                                dpbbp_x3_input.setVisibility(View.VISIBLE);
                                dpbbp_x4_input.setVisibility(View.VISIBLE);
                                dpbbp_y3_input.setVisibility(View.VISIBLE);
                                dpbbp_y4_input.setVisibility(View.VISIBLE);
                                dpbbp_z3_input.setVisibility(View.VISIBLE);
                                dpbbp_z4_input.setVisibility(View.VISIBLE);
                                dpbbp_a3_input.setVisibility(View.VISIBLE);
                                dpbbp_a4_input.setVisibility(View.VISIBLE);
                                dpbbp_b3_input.setVisibility(View.VISIBLE);
                                dpbbp_b4_input.setVisibility(View.VISIBLE);
                                dpbbp_c3_input.setVisibility(View.VISIBLE);
                                dpbbp_c4_input.setVisibility(View.VISIBLE);
                                dpbbp_gamma3_input.setVisibility(View.VISIBLE);
                                dpbbp_gamma4_placeholder.setVisibility(View.VISIBLE);
                                dpbbp_phi3_placeholder.setVisibility(View.VISIBLE);
                                dpbbp_phi4_placeholder.setVisibility(View.VISIBLE);
                                dpbbp_phisat3_placeholder.setVisibility(View.VISIBLE);
                                dpbbp_phisat4_placeholder.setVisibility(View.VISIBLE);
                                dpbbp_pcr3_placeholder.setVisibility(View.VISIBLE);
                                dpbbp_pcr4_placeholder.setVisibility(View.VISIBLE);
                                spinner_dpbbp_system.setEnabled(false);
                                spinner_dpbbp_components.setEnabled(false);
                                spinner_dpbbp_mode.setEnabled(false);
                                dpbbp_display.setVisibility(View.GONE);
                                dpbbp_reset.setVisibility(View.VISIBLE);
                                break;
                        }
                        break;
                    case 2:
                        //Ideal gas, non-ideal solution
                        dpbbp_main.setVisibility(View.VISIBLE);
                        dpbbp_igid_solve.setVisibility(View.GONE);
                        dpbbp_ignid_solve.setVisibility(View.VISIBLE);
                        dpbbp_nignid_solve.setVisibility(View.GONE);
                        dpbbp_gamma_row.setVisibility(View.VISIBLE);
                        dpbbp_w_row.setVisibility(View.GONE);
                        dpbbp_tc_row.setVisibility(View.GONE);
                        dpbbp_pc_row.setVisibility(View.GONE);
                        dpbbp_vc_row.setVisibility(View.GONE);
                        dpbbp_zc_row.setVisibility(View.GONE);
                        dpbbp_phi_row.setVisibility(View.GONE);
                        dpbbp_phisat_row.setVisibility(View.GONE);
                        dpbbp_pcr_row.setVisibility(View.GONE);
                        dpbbp_activities_row.setVisibility(View.VISIBLE);
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 0:
                                //IGNID > No. of components not selected
                                Toast.makeText(DpBbp.this, "Please select number of components in mixture!", Toast.LENGTH_SHORT).show();
                                dpbbp_main.setVisibility(View.GONE);
                                dpbbp_ignid_solve.setVisibility(View.GONE);
                                break;
                            case 1:
                                //IGNID > 2 components
                                dpbbp_3.setVisibility(View.GONE);
                                dpbbp_4.setVisibility(View.GONE);
                                dpbbp_x3_input.setVisibility(View.GONE);
                                dpbbp_x4_input.setVisibility(View.GONE);
                                dpbbp_y3_input.setVisibility(View.GONE);
                                dpbbp_y4_input.setVisibility(View.GONE);
                                dpbbp_z3_input.setVisibility(View.GONE);
                                dpbbp_z4_input.setVisibility(View.GONE);
                                dpbbp_a3_input.setVisibility(View.GONE);
                                dpbbp_a4_input.setVisibility(View.GONE);
                                dpbbp_b3_input.setVisibility(View.GONE);
                                dpbbp_b4_input.setVisibility(View.GONE);
                                dpbbp_c3_input.setVisibility(View.GONE);
                                dpbbp_c4_input.setVisibility(View.GONE);
                                dpbbp_gamma3_input.setVisibility(View.GONE);
                                dpbbp_gamma4_placeholder.setVisibility(View.GONE);
                                dpbbp_phi3_placeholder.setVisibility(View.GONE);
                                dpbbp_phi4_placeholder.setVisibility(View.GONE);
                                dpbbp_phisat3_placeholder.setVisibility(View.GONE);
                                dpbbp_phisat4_placeholder.setVisibility(View.GONE);
                                dpbbp_pcr3_placeholder.setVisibility(View.GONE);
                                dpbbp_pcr4_placeholder.setVisibility(View.GONE);
                                dpbbp_activities_row2.setVisibility(View.GONE);
                                dpbbp_activities_row3.setVisibility(View.GONE);
                                spinner_dpbbp_system.setEnabled(false);
                                spinner_dpbbp_components.setEnabled(false);
                                spinner_dpbbp_mode.setEnabled(false);
                                dpbbp_display.setVisibility(View.GONE);
                                dpbbp_reset.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                //IGNID > 3 components
                                dpbbp_3.setVisibility(View.VISIBLE);
                                dpbbp_4.setVisibility(View.GONE);
                                dpbbp_x3_input.setVisibility(View.VISIBLE);
                                dpbbp_x4_input.setVisibility(View.GONE);
                                dpbbp_y3_input.setVisibility(View.VISIBLE);
                                dpbbp_y4_input.setVisibility(View.GONE);
                                dpbbp_z3_input.setVisibility(View.VISIBLE);
                                dpbbp_z4_input.setVisibility(View.GONE);
                                dpbbp_a3_input.setVisibility(View.VISIBLE);
                                dpbbp_a4_input.setVisibility(View.GONE);
                                dpbbp_b3_input.setVisibility(View.VISIBLE);
                                dpbbp_b4_input.setVisibility(View.GONE);
                                dpbbp_c3_input.setVisibility(View.VISIBLE);
                                dpbbp_c4_input.setVisibility(View.GONE);
                                dpbbp_gamma3_input.setVisibility(View.VISIBLE);
                                dpbbp_gamma4_placeholder.setVisibility(View.GONE);
                                dpbbp_phi3_placeholder.setVisibility(View.VISIBLE);
                                dpbbp_phi4_placeholder.setVisibility(View.GONE);
                                dpbbp_phisat3_placeholder.setVisibility(View.VISIBLE);
                                dpbbp_phisat4_placeholder.setVisibility(View.GONE);
                                dpbbp_pcr3_placeholder.setVisibility(View.VISIBLE);
                                dpbbp_pcr4_placeholder.setVisibility(View.GONE);
                                dpbbp_activities_row2.setVisibility(View.VISIBLE);
                                dpbbp_activities_row3.setVisibility(View.VISIBLE);
                                spinner_dpbbp_system.setEnabled(false);
                                spinner_dpbbp_components.setEnabled(false);
                                spinner_dpbbp_mode.setEnabled(false);
                                dpbbp_display.setVisibility(View.GONE);
                                dpbbp_reset.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                //IGNID > 4 components
                                Toast.makeText(DpBbp.this, "Calculation for 4 components not supported for non-ideal solutions!", Toast.LENGTH_SHORT).show();
                                dpbbp_main.setVisibility(View.GONE);
                                dpbbp_ignid_solve.setVisibility(View.GONE);
                                break;
                        }
                        break;
                    case 3:
                        //Non-ideal gas, non-ideal solution
                        dpbbp_main.setVisibility(View.VISIBLE);
                        dpbbp_igid_solve.setVisibility(View.GONE);
                        dpbbp_ignid_solve.setVisibility(View.GONE);
                        dpbbp_nignid_solve.setVisibility(View.VISIBLE);
                        dpbbp_gamma_row.setVisibility(View.VISIBLE);
                        dpbbp_w_row.setVisibility(View.VISIBLE);
                        dpbbp_tc_row.setVisibility(View.VISIBLE);
                        dpbbp_pc_row.setVisibility(View.VISIBLE);
                        dpbbp_vc_row.setVisibility(View.VISIBLE);
                        dpbbp_zc_row.setVisibility(View.VISIBLE);
                        dpbbp_phi_row.setVisibility(View.VISIBLE);
                        dpbbp_phisat_row.setVisibility(View.VISIBLE);
                        dpbbp_pcr_row.setVisibility(View.VISIBLE);
                        dpbbp_activities_row.setVisibility(View.VISIBLE);
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 0:
                                //NIGNID > No. of components not selected
                                Toast.makeText(DpBbp.this, "Please select number of components in mixture!", Toast.LENGTH_SHORT).show();
                                dpbbp_main.setVisibility(View.GONE);
                                dpbbp_nignid_solve.setVisibility(View.GONE);
                                break;
                            case 1:
                                //NIGNID > 2 components
                                dpbbp_3.setVisibility(View.GONE);
                                dpbbp_4.setVisibility(View.GONE);
                                dpbbp_x3_input.setVisibility(View.GONE);
                                dpbbp_x4_input.setVisibility(View.GONE);
                                dpbbp_y3_input.setVisibility(View.GONE);
                                dpbbp_y4_input.setVisibility(View.GONE);
                                dpbbp_z3_input.setVisibility(View.GONE);
                                dpbbp_z4_input.setVisibility(View.GONE);
                                dpbbp_a3_input.setVisibility(View.GONE);
                                dpbbp_a4_input.setVisibility(View.GONE);
                                dpbbp_b3_input.setVisibility(View.GONE);
                                dpbbp_b4_input.setVisibility(View.GONE);
                                dpbbp_c3_input.setVisibility(View.GONE);
                                dpbbp_c4_input.setVisibility(View.GONE);
                                dpbbp_w3_placeholder.setVisibility(View.GONE);
                                dpbbp_w4_placeholder.setVisibility(View.GONE);
                                dpbbp_tc3_placeholder.setVisibility(View.GONE);
                                dpbbp_tc4_placeholder.setVisibility(View.GONE);
                                dpbbp_pc3_placeholder.setVisibility(View.GONE);
                                dpbbp_pc4_placeholder.setVisibility(View.GONE);
                                dpbbp_vc3_placeholder.setVisibility(View.GONE);
                                dpbbp_vc4_placeholder.setVisibility(View.GONE);
                                dpbbp_zc3_placeholder.setVisibility(View.GONE);
                                dpbbp_zc4_placeholder.setVisibility(View.GONE);
                                dpbbp_gamma3_input.setVisibility(View.GONE);
                                dpbbp_gamma4_placeholder.setVisibility(View.GONE);
                                dpbbp_phi3_placeholder.setVisibility(View.GONE);
                                dpbbp_phi4_placeholder.setVisibility(View.GONE);
                                dpbbp_phisat3_placeholder.setVisibility(View.GONE);
                                dpbbp_phisat4_placeholder.setVisibility(View.GONE);
                                dpbbp_pcr3_placeholder.setVisibility(View.GONE);
                                dpbbp_pcr4_placeholder.setVisibility(View.GONE);
                                dpbbp_activities_row2.setVisibility(View.GONE);
                                dpbbp_activities_row3.setVisibility(View.GONE);
                                spinner_dpbbp_system.setEnabled(false);
                                spinner_dpbbp_components.setEnabled(false);
                                spinner_dpbbp_mode.setEnabled(false);
                                dpbbp_display.setVisibility(View.GONE);
                                dpbbp_reset.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                //NIGNID > 3 components
                                Toast.makeText(DpBbp.this, "Calculation for 3 components not supported for non-ideal gases & solutions!", Toast.LENGTH_SHORT).show();
                                dpbbp_main.setVisibility(View.GONE);
                                dpbbp_nignid_solve.setVisibility(View.GONE);
                                break;
                            case 3:
                                //NIGNID > 4 components
                                Toast.makeText(DpBbp.this, "Calculation for 4 components not supported for non-ideal gases & solutions!", Toast.LENGTH_SHORT).show();
                                dpbbp_main.setVisibility(View.GONE);
                                dpbbp_nignid_solve.setVisibility(View.GONE);
                                break;
                        }
                        break;
                }
                switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                    case 1:
                        //Any system > 2 components
                        dpbbp_x1_input.setEms(6);
                        dpbbp_x2_input.setEms(6);
                        dpbbp_y1_input.setEms(6);
                        dpbbp_y2_input.setEms(6);
                        dpbbp_z1_input.setEms(6);
                        dpbbp_z2_input.setEms(6);
                        dpbbp_a1_input.setEms(6);
                        dpbbp_a2_input.setEms(6);
                        dpbbp_b1_input.setEms(6);
                        dpbbp_b2_input.setEms(6);
                        dpbbp_c1_input.setEms(6);
                        dpbbp_c2_input.setEms(6);
                        dpbbp_gamma1_input.setEms(6);
                        dpbbp_gamma2_input.setEms(6);
                        dpbbp_phi1_input.setEms(6);
                        dpbbp_phi2_input.setEms(6);
                        dpbbp_phisat1_input.setEms(6);
                        dpbbp_phisat2_input.setEms(6);
                        dpbbp_pcr1_input.setEms(6);
                        dpbbp_pcr2_input.setEms(6);
                        dpbbp_preset1_row.setVisibility(View.VISIBLE);
                        dpbbp_preset2_row.setVisibility(View.VISIBLE);
                        dpbbp_preset3_row.setVisibility(View.GONE);
                        dpbbp_preset4_row.setVisibility(View.GONE);
                        break;
                    case 2:
                        //Any system > 3 components
                        dpbbp_x1_input.setEms(4);
                        dpbbp_x2_input.setEms(4);
                        dpbbp_x3_input.setEms(4);
                        dpbbp_y1_input.setEms(4);
                        dpbbp_y2_input.setEms(4);
                        dpbbp_y3_input.setEms(4);
                        dpbbp_z1_input.setEms(4);
                        dpbbp_z2_input.setEms(4);
                        dpbbp_z3_input.setEms(4);
                        dpbbp_a1_input.setEms(4);
                        dpbbp_a2_input.setEms(4);
                        dpbbp_a3_input.setEms(4);
                        dpbbp_b1_input.setEms(4);
                        dpbbp_b2_input.setEms(4);
                        dpbbp_b3_input.setEms(4);
                        dpbbp_c1_input.setEms(4);
                        dpbbp_c2_input.setEms(4);
                        dpbbp_c3_input.setEms(4);
                        dpbbp_gamma1_input.setEms(4);
                        dpbbp_gamma2_input.setEms(4);
                        dpbbp_gamma3_input.setEms(4);
                        dpbbp_preset1_row.setVisibility(View.VISIBLE);
                        dpbbp_preset2_row.setVisibility(View.VISIBLE);
                        dpbbp_preset3_row.setVisibility(View.VISIBLE);
                        dpbbp_preset4_row.setVisibility(View.GONE);
                        break;
                    case 3:
                        //Any system > 4 components
                        dpbbp_x1_input.setEms(3);
                        dpbbp_x2_input.setEms(3);
                        dpbbp_x3_input.setEms(3);
                        dpbbp_x4_input.setEms(3);
                        dpbbp_y1_input.setEms(3);
                        dpbbp_y2_input.setEms(3);
                        dpbbp_y3_input.setEms(3);
                        dpbbp_y4_input.setEms(3);
                        dpbbp_z1_input.setEms(3);
                        dpbbp_z2_input.setEms(3);
                        dpbbp_z3_input.setEms(3);
                        dpbbp_z4_input.setEms(3);
                        dpbbp_a1_input.setEms(3);
                        dpbbp_a2_input.setEms(3);
                        dpbbp_a3_input.setEms(3);
                        dpbbp_a4_input.setEms(3);
                        dpbbp_b1_input.setEms(3);
                        dpbbp_b2_input.setEms(3);
                        dpbbp_b3_input.setEms(3);
                        dpbbp_b4_input.setEms(3);
                        dpbbp_c1_input.setEms(3);
                        dpbbp_c2_input.setEms(3);
                        dpbbp_c3_input.setEms(3);
                        dpbbp_c4_input.setEms(3);
                        dpbbp_preset1_row.setVisibility(View.VISIBLE);
                        dpbbp_preset2_row.setVisibility(View.VISIBLE);
                        dpbbp_preset3_row.setVisibility(View.VISIBLE);
                        dpbbp_preset4_row.setVisibility(View.VISIBLE);
                        break;
                }
                switch (spinner_dpbbp_mode.getSelectedItemPosition()) {
                    case 0:
                        //Any system > No mode selected
                        Toast.makeText(DpBbp.this, "Please select caclulation mode!", Toast.LENGTH_SHORT).show();
                        dpbbp_main.setVisibility(View.GONE);
                        dpbbp_igid_solve.setVisibility(View.GONE);
                        dpbbp_ignid_solve.setVisibility(View.GONE);
                        dpbbp_nignid_solve.setVisibility(View.GONE);
                        break;
                    case 1:
                        //Any system > Bubble point pressure
                        dpbbp_t_input.setEnabled(true);
                        dpbbp_p_input.setEnabled(false);
                        dpbbp_p_input.setText("");
                        dpbbp_x1_input.setEnabled(true);
                        dpbbp_x2_input.setEnabled(true);
                        dpbbp_x3_input.setEnabled(true);
                        dpbbp_x4_input.setEnabled(true);
                        dpbbp_y1_input.setEnabled(false);
                        dpbbp_y1_input.setText("");
                        dpbbp_y2_input.setEnabled(false);
                        dpbbp_y2_input.setText("");
                        dpbbp_y3_input.setEnabled(false);
                        dpbbp_y3_input.setText("");
                        dpbbp_y4_input.setEnabled(false);
                        dpbbp_y4_input.setText("");
                        dpbbp_z_row.setVisibility(View.GONE);
                        break;
                    case 2:
                        //Any system > Bubble point temperature
                        dpbbp_t_input.setEnabled(false);
                        dpbbp_t_input.setText("");
                        dpbbp_p_input.setEnabled(true);
                        dpbbp_x1_input.setEnabled(true);
                        dpbbp_x2_input.setEnabled(true);
                        dpbbp_x3_input.setEnabled(true);
                        dpbbp_x4_input.setEnabled(true);
                        dpbbp_y1_input.setEnabled(false);
                        dpbbp_y1_input.setText("");
                        dpbbp_y2_input.setEnabled(false);
                        dpbbp_y2_input.setText("");
                        dpbbp_y3_input.setEnabled(false);
                        dpbbp_y3_input.setText("");
                        dpbbp_y4_input.setEnabled(false);
                        dpbbp_y4_input.setText("");
                        dpbbp_z_row.setVisibility(View.GONE);
                        break;
                    case 3:
                        //Any system > Dew point temperature
                        dpbbp_t_input.setEnabled(false);
                        dpbbp_t_input.setText("");
                        dpbbp_p_input.setEnabled(true);
                        dpbbp_x1_input.setEnabled(false);
                        dpbbp_x1_input.setText("");
                        dpbbp_x2_input.setEnabled(false);
                        dpbbp_x2_input.setText("");
                        dpbbp_x3_input.setEnabled(false);
                        dpbbp_x3_input.setText("");
                        dpbbp_x4_input.setEnabled(false);
                        dpbbp_x4_input.setText("");
                        dpbbp_y1_input.setEnabled(true);
                        dpbbp_y2_input.setEnabled(true);
                        dpbbp_y3_input.setEnabled(true);
                        dpbbp_y4_input.setEnabled(true);
                        dpbbp_z_row.setVisibility(View.GONE);
                        break;
                    case 4:
                        //Any system > Dew point pressure
                        dpbbp_t_input.setEnabled(true);
                        dpbbp_p_input.setEnabled(false);
                        dpbbp_p_input.setText("");
                        dpbbp_x1_input.setEnabled(false);
                        dpbbp_x1_input.setText("");
                        dpbbp_x2_input.setEnabled(false);
                        dpbbp_x2_input.setText("");
                        dpbbp_x3_input.setEnabled(false);
                        dpbbp_x3_input.setText("");
                        dpbbp_x4_input.setEnabled(false);
                        dpbbp_x4_input.setText("");
                        dpbbp_y1_input.setEnabled(true);
                        dpbbp_y2_input.setEnabled(true);
                        dpbbp_y3_input.setEnabled(true);
                        dpbbp_y4_input.setEnabled(true);
                        dpbbp_z_row.setVisibility(View.GONE);
                        break;
                    case 5:
                        //Any system > Flash calculations
                        dpbbp_t_input.setEnabled(true);
                        dpbbp_p_input.setEnabled(true);
                        dpbbp_x1_input.setEnabled(false);
                        dpbbp_x1_input.setText("");
                        dpbbp_x2_input.setEnabled(false);
                        dpbbp_x2_input.setText("");
                        dpbbp_x3_input.setEnabled(false);
                        dpbbp_x3_input.setText("");
                        dpbbp_x4_input.setEnabled(false);
                        dpbbp_x4_input.setText("");
                        dpbbp_y1_input.setEnabled(false);
                        dpbbp_y1_input.setText("");
                        dpbbp_y2_input.setEnabled(false);
                        dpbbp_y2_input.setText("");
                        dpbbp_y3_input.setEnabled(false);
                        dpbbp_y3_input.setText("");
                        dpbbp_y4_input.setEnabled(false);
                        dpbbp_y4_input.setText("");
                        dpbbp_z_row.setVisibility(View.VISIBLE);
                        dpbbp_z1_input.setEnabled(true);
                        dpbbp_z2_input.setEnabled(true);
                        dpbbp_z3_input.setEnabled(true);
                        dpbbp_z4_input.setEnabled(true);
                        break;
                }
            }
        });

        Button dpbbp_reset = (Button) findViewById(R.id.dpbbp_reset);
        dpbbp_reset.setVisibility(View.GONE);
        dpbbp_reset.setOnClickListener(new View.OnClickListener() {
            Spinner spinner_dpbbp_system = (Spinner) findViewById(R.id.dpbbp_system_input);
            Spinner spinner_dpbbp_components = (Spinner) findViewById(R.id.dpbbp_components_input);
            Spinner spinner_dpbbp_mode = (Spinner) findViewById(R.id.dpbbp_mode_input);
            LinearLayout dpbbp_main = (LinearLayout) findViewById(R.id.dpbbp_main);
            Button dpbbp_display = (Button) findViewById(R.id.dpbbp_display);
            Button dpbbp_reset = (Button) findViewById(R.id.dpbbp_reset);
            Button dpbbp_igid_solve = (Button) findViewById(R.id.dpbbp_igid_solve);
            Button dpbbp_ignid_solve = (Button) findViewById(R.id.dpbbp_ignid_solve);
            Button dpbbp_nignid_solve = (Button) findViewById(R.id.dpbbp_nignid_solve);
            @Override
            public void onClick(View v) {
                spinner_dpbbp_system.setEnabled(true);
                spinner_dpbbp_components.setEnabled(true);
                spinner_dpbbp_mode.setEnabled(true);
                dpbbp_main.setVisibility(View.GONE);
                dpbbp_display.setVisibility(View.VISIBLE);
                dpbbp_igid_solve.setVisibility(View.GONE);
                dpbbp_ignid_solve.setVisibility(View.GONE);
                dpbbp_nignid_solve.setVisibility(View.GONE);
                dpbbp_reset.setVisibility(View.GONE);
            }
        });

        //Ideal gas, ideal solution
        Button dpbbp_igid_solve = (Button) findViewById(R.id.dpbbp_igid_solve);
        dpbbp_igid_solve.setOnClickListener(new View.OnClickListener() {
            Spinner spinner_dpbbp_system = (Spinner) findViewById(R.id.dpbbp_system_input);
            Spinner spinner_dpbbp_components = (Spinner) findViewById(R.id.dpbbp_components_input);
            Spinner spinner_dpbbp_mode = (Spinner) findViewById(R.id.dpbbp_mode_input);
            TextView dpbbp_t_input = (TextView) findViewById(R.id.dpbbp_t_input);
            TextView dpbbp_p_input = (TextView) findViewById(R.id.dpbbp_p_input);
            TextView dpbbp_x1_input = (TextView) findViewById(R.id.dpbbp_x1_input);
            TextView dpbbp_x2_input = (TextView) findViewById(R.id.dpbbp_x2_input);
            TextView dpbbp_x3_input = (TextView) findViewById(R.id.dpbbp_x3_input);
            TextView dpbbp_x4_input = (TextView) findViewById(R.id.dpbbp_x4_input);
            TextView dpbbp_y1_input = (TextView) findViewById(R.id.dpbbp_y1_input);
            TextView dpbbp_y2_input = (TextView) findViewById(R.id.dpbbp_y2_input);
            TextView dpbbp_y3_input = (TextView) findViewById(R.id.dpbbp_y3_input);
            TextView dpbbp_y4_input = (TextView) findViewById(R.id.dpbbp_y4_input);
            TextView dpbbp_z1_input = (TextView) findViewById(R.id.dpbbp_z1_input);
            TextView dpbbp_z2_input = (TextView) findViewById(R.id.dpbbp_z2_input);
            TextView dpbbp_z3_input = (TextView) findViewById(R.id.dpbbp_z3_input);
            TextView dpbbp_z4_input = (TextView) findViewById(R.id.dpbbp_z4_input);
            TextView dpbbp_a1_input = (TextView) findViewById(R.id.dpbbp_a1_input);
            TextView dpbbp_a2_input = (TextView) findViewById(R.id.dpbbp_a2_input);
            TextView dpbbp_a3_input = (TextView) findViewById(R.id.dpbbp_a3_input);
            TextView dpbbp_a4_input = (TextView) findViewById(R.id.dpbbp_a4_input);
            TextView dpbbp_b1_input = (TextView) findViewById(R.id.dpbbp_b1_input);
            TextView dpbbp_b2_input = (TextView) findViewById(R.id.dpbbp_b2_input);
            TextView dpbbp_b3_input = (TextView) findViewById(R.id.dpbbp_b3_input);
            TextView dpbbp_b4_input = (TextView) findViewById(R.id.dpbbp_b4_input);
            TextView dpbbp_c1_input = (TextView) findViewById(R.id.dpbbp_c1_input);
            TextView dpbbp_c2_input = (TextView) findViewById(R.id.dpbbp_c2_input);
            TextView dpbbp_c3_input = (TextView) findViewById(R.id.dpbbp_c3_input);
            TextView dpbbp_c4_input = (TextView) findViewById(R.id.dpbbp_c4_input);
            Button dpbbp_display = (Button) findViewById(R.id.dpbbp_display);
            Button dpbbp_reset = (Button) findViewById(R.id.dpbbp_reset);
            Button dpbbp_igid_solve = (Button) findViewById(R.id.dpbbp_igid_solve);

            @Override
            public void onClick(View v) {
                switch (spinner_dpbbp_mode.getSelectedItemPosition()) {
                    case 1:
                        //IGID > Bubble point pressure
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //IGID > Bubble point pressure > 2 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_x1_input.getText().length() == 0 |
                                        dpbbp_x2_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double x1 = Double.parseDouble(dpbbp_x1_input.getText().toString());
                                    Double x2 = Double.parseDouble(dpbbp_x2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p = x1 * p1sat + x2 * p2sat;
                                    Double y1 = x1 * p1sat / p;
                                    Double y2 = x2 * p2sat / p;
                                    if (Math.abs(x1+x2-1.0)>1e-3 || x1<0.0 || x2<0.0) {
                                        Toast.makeText(DpBbp.this, "Liquid phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    dpbbp_p_input.setText(p.toString());
                                    dpbbp_y1_input.setText(y1.toString());
                                    dpbbp_y2_input.setText(y2.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_igid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                            case 2:
                                //IGID > Bubble point pressure > 3 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_x1_input.getText().length() == 0 |
                                        dpbbp_x2_input.getText().length() == 0 | dpbbp_x3_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_a3_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_b3_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0 |
                                        dpbbp_c3_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double x1 = Double.parseDouble(dpbbp_x1_input.getText().toString());
                                    Double x2 = Double.parseDouble(dpbbp_x2_input.getText().toString());
                                    Double x3 = Double.parseDouble(dpbbp_x3_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double p = x1 * p1sat + x2 * p2sat + x3 * p3sat;
                                    Double y1 = x1 * p1sat / p;
                                    Double y2 = x2 * p2sat / p;
                                    Double y3 = x3 * p3sat / p;
                                    if (Math.abs(x1+x2+x3-1.0)>1e-3 || x1<0.0 || x2<0.0 || x3<0.0) {
                                        Toast.makeText(DpBbp.this, "Liquid phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    dpbbp_p_input.setText(p.toString());
                                    dpbbp_y1_input.setText(y1.toString());
                                    dpbbp_y2_input.setText(y2.toString());
                                    dpbbp_y3_input.setText(y3.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_igid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                            case 3:
                                //IGID > Bubble point pressure > 4 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_x1_input.getText().length() == 0 |
                                        dpbbp_x2_input.getText().length() == 0 | dpbbp_x3_input.getText().length() == 0 |
                                        dpbbp_x4_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_a3_input.getText().length() == 0 |
                                        dpbbp_a4_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_b3_input.getText().length() == 0 |
                                        dpbbp_b4_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0 | dpbbp_c3_input.getText().length() == 0 |
                                        dpbbp_c4_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double x1 = Double.parseDouble(dpbbp_x1_input.getText().toString());
                                    Double x2 = Double.parseDouble(dpbbp_x2_input.getText().toString());
                                    Double x3 = Double.parseDouble(dpbbp_x3_input.getText().toString());
                                    Double x4 = Double.parseDouble(dpbbp_x4_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double a4 = Double.parseDouble(dpbbp_a4_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double b4 = Double.parseDouble(dpbbp_b4_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double c4 = Double.parseDouble(dpbbp_c4_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double p4sat = Math.exp(a4 - b4 / (c4 + t));
                                    Double p = x1 * p1sat + x2 * p2sat + x3 * p3sat + x4 * p4sat;
                                    Double y1 = x1 * p1sat / p;
                                    Double y2 = x2 * p2sat / p;
                                    Double y3 = x3 * p3sat / p;
                                    Double y4 = x4 * p4sat / p;
                                    if (Math.abs(x1+x2+x3+x4-1.0)>1e-3 || x1<0.0 || x2<0.0 || x3<0.0 || x4<0.0) {
                                        Toast.makeText(DpBbp.this, "Liquid phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    dpbbp_p_input.setText(p.toString());
                                    dpbbp_y1_input.setText(y1.toString());
                                    dpbbp_y2_input.setText(y2.toString());
                                    dpbbp_y3_input.setText(y3.toString());
                                    dpbbp_y4_input.setText(y4.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_igid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                        }
                        break;
                    case 2:
                        //IGID > Bubble point temperature
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //IGID > Bubble point temperature > 2 components
                                if (dpbbp_p_input.getText().length() == 0 | dpbbp_x1_input.getText().length() == 0 |
                                        dpbbp_x2_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double x1 = Double.parseDouble(dpbbp_x1_input.getText().toString());
                                    Double x2 = Double.parseDouble(dpbbp_x2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double t1sat = b1 / (a1 - Math.log(p)) - c1;
                                    Double t2sat = b2 / (a2 - Math.log(p)) - c2;
                                    Double tleft = Math.min(t1sat, t2sat);
                                    Double tright = Math.max(t1sat, t2sat);
                                    Double t = (tleft + tright) / 2.0;
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double y1 = x1 * p1sat / p;
                                    Double y2 = x2 * p2sat / p;
                                    Double y = y1 + y2;
                                    Double tol = 0.000000001;
                                    int iter = 0;
                                    while (Math.abs(y - 1) > tol & iter < 1000) {
                                        if (y > 1) {
                                            tright = t;
                                        } else if (y < 1) {
                                            tleft = t;
                                        }
                                        t = (tleft + tright) / 2.0;
                                        p1sat = Math.exp(a1 - b1 / (c1 + t));
                                        p2sat = Math.exp(a2 - b2 / (c2 + t));
                                        y1 = x1 * p1sat / p;
                                        y2 = x2 * p2sat / p;
                                        y = y1 + y2;
                                        iter = iter + 1;
                                    }
                                    if (Math.abs(x1+x2-1.0)>1e-3 || x1<0.0 || x2<0.0) {
                                        Toast.makeText(DpBbp.this, "Liquid phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_t_input.setText(t.toString());
                                    dpbbp_y1_input.setText(y1.toString());
                                    dpbbp_y2_input.setText(y2.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_igid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                            case 2:
                                //IGID > Bubble point temperature > 3 components
                                if (dpbbp_p_input.getText().length() == 0 | dpbbp_x1_input.getText().length() == 0 |
                                        dpbbp_x2_input.getText().length() == 0 | dpbbp_x3_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_a3_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_b3_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0 |
                                        dpbbp_c3_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double x1 = Double.parseDouble(dpbbp_x1_input.getText().toString());
                                    Double x2 = Double.parseDouble(dpbbp_x2_input.getText().toString());
                                    Double x3 = Double.parseDouble(dpbbp_x3_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double t1sat = b1 / (a1 - Math.log(p)) - c1;
                                    Double t2sat = b2 / (a2 - Math.log(p)) - c2;
                                    Double t3sat = b3 / (a3 - Math.log(p)) - c3;
                                    Double tleft = Math.min(Math.min(t1sat, t2sat), t3sat);
                                    Double tright = Math.max(Math.max(t1sat, t2sat), t3sat);
                                    Double t = (tleft + tright) / 2.0;
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double y1 = x1 * p1sat / p;
                                    Double y2 = x2 * p2sat / p;
                                    Double y3 = x3 * p3sat / p;
                                    Double y = y1 + y2 + y3;
                                    Double tol = 0.000000001;
                                    int iter = 0;
                                    while (Math.abs(y - 1) > tol & iter < 1000) {
                                        if (y > 1) {
                                            tright = t;
                                        } else if (y < 1) {
                                            tleft = t;
                                        }
                                        t = (tleft + tright) / 2.0;
                                        p1sat = Math.exp(a1 - b1 / (c1 + t));
                                        p2sat = Math.exp(a2 - b2 / (c2 + t));
                                        p3sat = Math.exp(a3 - b3 / (c3 + t));
                                        y1 = x1 * p1sat / p;
                                        y2 = x2 * p2sat / p;
                                        y3 = x3 * p3sat / p;
                                        y = y1 + y2 + y3;
                                        iter = iter + 1;
                                    }
                                    if (Math.abs(x1+x2+x3-1.0)>1e-3 || x1<0.0 || x2<0.0 || x3<0.0) {
                                        Toast.makeText(DpBbp.this, "Liquid phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_t_input.setText(t.toString());
                                    dpbbp_y1_input.setText(y1.toString());
                                    dpbbp_y2_input.setText(y2.toString());
                                    dpbbp_y3_input.setText(y3.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_igid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                            case 3:
                                //IGID > Bubble point temperature > 4 components
                                if (dpbbp_p_input.getText().length() == 0 | dpbbp_x1_input.getText().length() == 0 |
                                        dpbbp_x2_input.getText().length() == 0 | dpbbp_x3_input.getText().length() == 0 |
                                        dpbbp_x4_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_a3_input.getText().length() == 0 |
                                        dpbbp_a4_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_b3_input.getText().length() == 0 |
                                        dpbbp_b4_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0 | dpbbp_c3_input.getText().length() == 0 |
                                        dpbbp_c4_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double x1 = Double.parseDouble(dpbbp_x1_input.getText().toString());
                                    Double x2 = Double.parseDouble(dpbbp_x2_input.getText().toString());
                                    Double x3 = Double.parseDouble(dpbbp_x3_input.getText().toString());
                                    Double x4 = Double.parseDouble(dpbbp_x4_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double a4 = Double.parseDouble(dpbbp_a4_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double b4 = Double.parseDouble(dpbbp_b4_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double c4 = Double.parseDouble(dpbbp_c4_input.getText().toString());
                                    Double t1sat = b1 / (a1 - Math.log(p)) - c1;
                                    Double t2sat = b2 / (a2 - Math.log(p)) - c2;
                                    Double t3sat = b3 / (a3 - Math.log(p)) - c3;
                                    Double t4sat = b4 / (a4 - Math.log(p)) - c4;
                                    Double tleft = Math.min(Math.min(t1sat, t2sat), Math.min(t3sat, t4sat));
                                    Double tright = Math.max(Math.max(t1sat, t2sat), Math.max(t3sat, t4sat));
                                    Double t = (tleft + tright) / 2.0;
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double p4sat = Math.exp(a4 - b4 / (c4 + t));
                                    Double y1 = x1 * p1sat / p;
                                    Double y2 = x2 * p2sat / p;
                                    Double y3 = x3 * p3sat / p;
                                    Double y4 = x4 * p4sat / p;
                                    Double y = y1 + y2 + y3 + y4;
                                    Double tol = 0.000000001;
                                    int iter = 0;
                                    while (Math.abs(y - 1) > tol & iter < 1000) {
                                        if (y > 1) {
                                            tright = t;
                                        } else if (y < 1) {
                                            tleft = t;
                                        }
                                        t = (tleft + tright) / 2.0;
                                        p1sat = Math.exp(a1 - b1 / (c1 + t));
                                        p2sat = Math.exp(a2 - b2 / (c2 + t));
                                        p3sat = Math.exp(a3 - b3 / (c3 + t));
                                        p4sat = Math.exp(a4 - b4 / (c4 + t));
                                        y1 = x1 * p1sat / p;
                                        y2 = x2 * p2sat / p;
                                        y3 = x3 * p3sat / p;
                                        y4 = x4 * p4sat / p;
                                        y = y1 + y2 + y3 + y4;
                                        iter = iter + 1;
                                    }
                                    if (Math.abs(x1+x2+x3+x4-1.0)>1e-3 || x1<0.0 || x2<0.0 || x3<0.0 || x4<0.0) {
                                        Toast.makeText(DpBbp.this, "Liquid phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_t_input.setText(t.toString());
                                    dpbbp_y1_input.setText(y1.toString());
                                    dpbbp_y2_input.setText(y2.toString());
                                    dpbbp_y3_input.setText(y3.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_igid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                        }
                        break;
                    case 3:
                        //IGID > Dew point temperature
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //IGID > Dew point temperature > 2 components
                                if (dpbbp_p_input.getText().length() == 0 | dpbbp_y1_input.getText().length() == 0 |
                                        dpbbp_y2_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double y1 = Double.parseDouble(dpbbp_y1_input.getText().toString());
                                    Double y2 = Double.parseDouble(dpbbp_y2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double t1sat = b1 / (a1 - Math.log(p)) - c1;
                                    Double t2sat = b2 / (a2 - Math.log(p)) - c2;
                                    Double tleft = Math.min(t1sat, t2sat);
                                    Double tright = Math.max(t1sat, t2sat);
                                    Double t = (tleft + tright) / 2.0;
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double x1 = y1 * p / p1sat;
                                    Double x2 = y2 * p / p2sat;
                                    Double x = x1 + x2;
                                    Double tol = 0.000000001;
                                    int iter = 0;
                                    while (Math.abs(x - 1) > tol & iter < 1000) {
                                        if (x > 1) {
                                            tleft = t;
                                        } else if (x < 1) {
                                            tright = t;
                                        }
                                        t = (tleft + tright) / 2.0;
                                        p1sat = Math.exp(a1 - b1 / (c1 + t));
                                        p2sat = Math.exp(a2 - b2 / (c2 + t));
                                        x1 = y1 * p / p1sat;
                                        x2 = y2 * p / p2sat;
                                        x = x1 + x2;
                                        iter = iter + 1;
                                    }
                                    if (Math.abs(y1+y2-1.0)>1e-3 || y1<0.0 || y2<0.0) {
                                        Toast.makeText(DpBbp.this, "Vapour phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_t_input.setText(t.toString());
                                    dpbbp_x1_input.setText(x1.toString());
                                    dpbbp_x2_input.setText(x2.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_igid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                            case 2:
                                //IGID > Dew point temperature > 3 components
                                if (dpbbp_p_input.getText().length() == 0 | dpbbp_y1_input.getText().length() == 0 |
                                        dpbbp_y2_input.getText().length() == 0 | dpbbp_y3_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_a3_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_b3_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0 |
                                        dpbbp_c3_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double y1 = Double.parseDouble(dpbbp_y1_input.getText().toString());
                                    Double y2 = Double.parseDouble(dpbbp_y2_input.getText().toString());
                                    Double y3 = Double.parseDouble(dpbbp_y3_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double t1sat = b1 / (a1 - Math.log(p)) - c1;
                                    Double t2sat = b2 / (a2 - Math.log(p)) - c2;
                                    Double t3sat = b3 / (a3 - Math.log(p)) - c3;
                                    Double tleft = Math.min(Math.min(t1sat, t2sat), t3sat);
                                    Double tright = Math.max(Math.max(t1sat, t2sat), t3sat);
                                    Double t = (tleft + tright) / 2.0;
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double x1 = y1 * p / p1sat;
                                    Double x2 = y2 * p / p2sat;
                                    Double x3 = y3 * p / p3sat;
                                    Double x = x1 + x2 + x3;
                                    Double tol = 0.000000001;
                                    int iter = 0;
                                    while (Math.abs(x - 1) > tol & iter < 1000) {
                                        if (x > 1) {
                                            tleft = t;
                                        } else if (x < 1) {
                                            tright = t;
                                        }
                                        t = (tleft + tright) / 2.0;
                                        p1sat = Math.exp(a1 - b1 / (c1 + t));
                                        p2sat = Math.exp(a2 - b2 / (c2 + t));
                                        p3sat = Math.exp(a3 - b3 / (c3 + t));
                                        x1 = y1 * p / p1sat;
                                        x2 = y2 * p / p2sat;
                                        x3 = y3 * p / p3sat;
                                        x = x1 + x2 + x3;
                                        iter = iter + 1;
                                    }
                                    if (Math.abs(y1+y2+y3-1.0)>1e-3 || y1<0.0 || y2<0.0 || y3<0.0) {
                                        Toast.makeText(DpBbp.this, "Vapour phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_t_input.setText(t.toString());
                                    dpbbp_x1_input.setText(x1.toString());
                                    dpbbp_x2_input.setText(x2.toString());
                                    dpbbp_x3_input.setText(x3.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_igid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                            case 3:
                                //IGID > Dew point temperature > 4 components
                                if (dpbbp_p_input.getText().length() == 0 | dpbbp_y1_input.getText().length() == 0 |
                                        dpbbp_y2_input.getText().length() == 0 | dpbbp_y3_input.getText().length() == 0 |
                                        dpbbp_y4_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_a3_input.getText().length() == 0 |
                                        dpbbp_a4_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_b3_input.getText().length() == 0 |
                                        dpbbp_b4_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0 | dpbbp_c3_input.getText().length() == 0 |
                                        dpbbp_c4_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double y1 = Double.parseDouble(dpbbp_y1_input.getText().toString());
                                    Double y2 = Double.parseDouble(dpbbp_y2_input.getText().toString());
                                    Double y3 = Double.parseDouble(dpbbp_y3_input.getText().toString());
                                    Double y4 = Double.parseDouble(dpbbp_y4_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double a4 = Double.parseDouble(dpbbp_a4_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double b4 = Double.parseDouble(dpbbp_b4_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double c4 = Double.parseDouble(dpbbp_c4_input.getText().toString());
                                    Double t1sat = b1 / (a1 - Math.log(p)) - c1;
                                    Double t2sat = b2 / (a2 - Math.log(p)) - c2;
                                    Double t3sat = b3 / (a3 - Math.log(p)) - c3;
                                    Double t4sat = b4 / (a4 - Math.log(p)) - c4;
                                    Double tleft = Math.min(Math.min(t1sat, t2sat), Math.min(t3sat, t4sat));
                                    Double tright = Math.max(Math.max(t1sat, t2sat), Math.max(t3sat, t4sat));
                                    Double t = (tleft + tright) / 2.0;
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double p4sat = Math.exp(a4 - b4 / (c4 + t));
                                    Double x1 = y1 * p / p1sat;
                                    Double x2 = y2 * p / p2sat;
                                    Double x3 = y3 * p / p3sat;
                                    Double x4 = y4 * p / p4sat;
                                    Double x = x1 + x2 + x3 + x4;
                                    Double tol = 0.000000001;
                                    int iter = 0;
                                    while (Math.abs(x - 1) > tol & iter < 1000) {
                                        if (x > 1) {
                                            tleft = t;
                                        } else if (x < 1) {
                                            tright = t;
                                        }
                                        t = (tleft + tright) / 2.0;
                                        p1sat = Math.exp(a1 - b1 / (c1 + t));
                                        p2sat = Math.exp(a2 - b2 / (c2 + t));
                                        p3sat = Math.exp(a3 - b3 / (c3 + t));
                                        p4sat = Math.exp(a4 - b4 / (c4 + t));
                                        x1 = y1 * p / p1sat;
                                        x2 = y2 * p / p2sat;
                                        x3 = y3 * p / p3sat;
                                        x4 = y4 * p / p4sat;
                                        x = x1 + x2 + x3 + x4;
                                        iter = iter + 1;
                                    }
                                    if (Math.abs(y1+y2+y3+y4-1.0)>1e-3 || y1<0.0 || y2<0.0 || y3<0.0 || y4<0.0) {
                                        Toast.makeText(DpBbp.this, "Vapour phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_t_input.setText(t.toString());
                                    dpbbp_x1_input.setText(x1.toString());
                                    dpbbp_x2_input.setText(x2.toString());
                                    dpbbp_x3_input.setText(x3.toString());
                                    dpbbp_x4_input.setText(x4.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_igid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                        }
                        break;
                    case 4:
                        //IGID > Dew point pressure
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //IGID > Dew point pressure > 2 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_y1_input.getText().length() == 0 |
                                        dpbbp_y2_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double y1 = Double.parseDouble(dpbbp_y1_input.getText().toString());
                                    Double y2 = Double.parseDouble(dpbbp_y2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p = 1.0 / (y1 / p1sat + y2 / p2sat);
                                    Double x1 = y1 * p / p1sat;
                                    Double x2 = y2 * p / p2sat;
                                    if (Math.abs(y1+y2-1.0)>1e-3 || y1<0.0 || y2<0.0) {
                                        Toast.makeText(DpBbp.this, "Vapour phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    dpbbp_p_input.setText(p.toString());
                                    dpbbp_x1_input.setText(x1.toString());
                                    dpbbp_x2_input.setText(x2.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_igid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                            case 2:
                                //IGID > Dew point pressure > 3 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_y1_input.getText().length() == 0 |
                                        dpbbp_y2_input.getText().length() == 0 | dpbbp_y3_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_a3_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_b3_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0 |
                                        dpbbp_c3_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double y1 = Double.parseDouble(dpbbp_y1_input.getText().toString());
                                    Double y2 = Double.parseDouble(dpbbp_y2_input.getText().toString());
                                    Double y3 = Double.parseDouble(dpbbp_y3_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double p = 1.0 / (y1 / p1sat + y2 / p2sat + y3 / p3sat);
                                    Double x1 = y1 * p / p1sat;
                                    Double x2 = y2 * p / p2sat;
                                    Double x3 = y3 * p / p3sat;
                                    if (Math.abs(y1+y2+y3-1.0)>1e-3 || y1<0.0 || y2<0.0 || y3<0.0) {
                                        Toast.makeText(DpBbp.this, "Vapour phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    dpbbp_p_input.setText(p.toString());
                                    dpbbp_x1_input.setText(x1.toString());
                                    dpbbp_x2_input.setText(x2.toString());
                                    dpbbp_x3_input.setText(x3.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_igid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                            case 3:
                                //IGID > Dew point pressure > 4 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_y1_input.getText().length() == 0 |
                                        dpbbp_y2_input.getText().length() == 0 | dpbbp_y3_input.getText().length() == 0 |
                                        dpbbp_y4_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_a3_input.getText().length() == 0 |
                                        dpbbp_a4_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_b3_input.getText().length() == 0 |
                                        dpbbp_b4_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0 | dpbbp_c3_input.getText().length() == 0 |
                                        dpbbp_c4_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double y1 = Double.parseDouble(dpbbp_y1_input.getText().toString());
                                    Double y2 = Double.parseDouble(dpbbp_y2_input.getText().toString());
                                    Double y3 = Double.parseDouble(dpbbp_y3_input.getText().toString());
                                    Double y4 = Double.parseDouble(dpbbp_y4_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double a4 = Double.parseDouble(dpbbp_a4_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double b4 = Double.parseDouble(dpbbp_b4_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double c4 = Double.parseDouble(dpbbp_c4_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double p4sat = Math.exp(a4 - b4 / (c4 + t));
                                    Double p = 1.0 / (y1 / p1sat + y2 / p2sat + y3 / p3sat + y4 / p4sat);
                                    Double x1 = y1 * p / p1sat;
                                    Double x2 = y2 * p / p2sat;
                                    Double x3 = y3 * p / p3sat;
                                    Double x4 = y4 * p / p4sat;
                                    if (Math.abs(y1+y2+y3+y4-1.0)>1e-3 || y1<0.0 || y2<0.0 || y3<0.0 || y4<0.0) {
                                        Toast.makeText(DpBbp.this, "Vapour phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    dpbbp_p_input.setText(p.toString());
                                    dpbbp_x1_input.setText(x1.toString());
                                    dpbbp_x2_input.setText(x2.toString());
                                    dpbbp_x3_input.setText(x3.toString());
                                    dpbbp_x4_input.setText(x4.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_igid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                        }
                        break;
                    case 5:
                        //IGID > Flash calculations
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //IGID > Flash calculations > 2 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_p_input.getText().length() == 0 |
                                        dpbbp_z1_input.getText().length() == 0 | dpbbp_z2_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double z1 = Double.parseDouble(dpbbp_z1_input.getText().toString());
                                    Double z2 = Double.parseDouble(dpbbp_z2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double pbbp = z1 * p1sat + z2 * p2sat;
                                    Double pdp = 1.0 / (z1 / p1sat + z2 / p2sat);
                                    if (pdp <= p & p <= pbbp) {
                                        //Flash is possible
                                        Double V1 = 0.5, V0 = 0.0;
                                        Double fV0, dfV0;
                                        Double tol = 0.000000001;
                                        int iter = 0;
                                        while (Math.abs(V1 - V0) > tol & iter < 1000) {
                                            V0 = V1;
                                            fV0 = z1 / ((p / p1sat) * (1.0 - V0) + V0)
                                                    + z2 / ((p / p2sat) * (1.0 - V0) + V0) - 1.0;
                                            dfV0 = z1 / Math.pow(((p / p1sat) * (1.0 - V0) + V0), 2) * (p / p1sat - 1.0)
                                                    + z2 / Math.pow(((p / p2sat) * (1.0 - V0) + V0), 2) * (p / p2sat - 1.0);
                                            V1 = V0 - fV0 / dfV0;
                                            iter = iter + 1;
                                        }
                                        Double y1 = z1 / ((p / p1sat) * (1.0 - V1) + V1);
                                        Double y2 = z2 / ((p / p2sat) * (1.0 - V1) + V1);
                                        Double L = 1.0 - V1;
                                        Double x1 = (z1 - y1 * V1) / L;
                                        Double x2 = (z2 - y2 * V1) / L;
                                        if (Math.abs(z1+z2-1.0)>1e-3 || z1<0.0 || z2<0.0) {
                                            Toast.makeText(DpBbp.this, "Overall phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isNaN(y1) || Double.isNaN(y2) ||
                                                Math.abs(x1+x2-1.0)>1e-3 || Math.abs(y1+y2-1.0)>1e-3 ||
                                                x1<0.0 || x2<0.0 || y1<0.0 || y2<0.0) {
                                            Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                        }
                                        dpbbp_x1_input.setText(x1.toString());
                                        dpbbp_x2_input.setText(x2.toString());
                                        dpbbp_y1_input.setText(y1.toString());
                                        dpbbp_y2_input.setText(y2.toString());
                                        spinner_dpbbp_system.setEnabled(true);
                                        spinner_dpbbp_components.setEnabled(true);
                                        spinner_dpbbp_mode.setEnabled(true);
                                        dpbbp_igid_solve.setVisibility(View.GONE);
                                        dpbbp_display.setVisibility(View.VISIBLE);
                                        dpbbp_reset.setVisibility(View.GONE);
                                        Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(DpBbp.this, "Flash process is not possible at given conditions! At T = "+Double.toString(t)+" K, P should be between "+Double.toString(pdp)+" kPa and "+Double.toString(pbbp)+" kPa!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                break;
                            case 2:
                                //IGID > Flash calculations > 3 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_p_input.getText().length() == 0 |
                                        dpbbp_z1_input.getText().length() == 0 | dpbbp_z2_input.getText().length() == 0 |
                                        dpbbp_z3_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_a3_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_b3_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0 |
                                        dpbbp_c3_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double z1 = Double.parseDouble(dpbbp_z1_input.getText().toString());
                                    Double z2 = Double.parseDouble(dpbbp_z2_input.getText().toString());
                                    Double z3 = Double.parseDouble(dpbbp_z3_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double pbbp = z1 * p1sat + z2 * p2sat + z3 * p3sat;
                                    Double pdp = 1.0 / (z1 / p1sat + z2 / p2sat + z3 / p3sat);
                                    if (pdp <= p & p <= pbbp) {
                                        //Flash is possible
                                        Double V1 = 0.5, V0 = 0.0;
                                        Double fV0, dfV0;
                                        Double tol = 0.000000001;
                                        int iter = 0;
                                        while (Math.abs(V1 - V0) > tol & iter < 1000) {
                                            V0 = V1;
                                            fV0 = z1 / ((p / p1sat) * (1.0 - V0) + V0)
                                                    + z2 / ((p / p2sat) * (1.0 - V0) + V0)
                                                    + z3 / ((p / p3sat) * (1.0 - V0) + V0) - 1.0;
                                            dfV0 = z1 / Math.pow(((p / p1sat) * (1.0 - V0) + V0), 2) * (p / p1sat - 1.0)
                                                    + z2 / Math.pow(((p / p2sat) * (1.0 - V0) + V0), 2) * (p / p2sat - 1.0)
                                                    + z3 / Math.pow(((p / p3sat) * (1.0 - V0) + V0), 2) * (p / p3sat - 1.0);
                                            V1 = V0 - fV0 / dfV0;
                                            iter = iter + 1;
                                        }
                                        Double y1 = z1 / ((p / p1sat) * (1.0 - V1) + V1);
                                        Double y2 = z2 / ((p / p2sat) * (1.0 - V1) + V1);
                                        Double y3 = z3 / ((p / p3sat) * (1.0 - V1) + V1);
                                        Double L = 1.0 - V1;
                                        Double x1 = (z1 - y1 * V1) / L;
                                        Double x2 = (z2 - y2 * V1) / L;
                                        Double x3 = (z3 - y3 * V1) / L;
                                        if (Math.abs(z1+z2+z3-1.0)>1e-3 || z1<0.0 || z2<0.0 || z3<0.0) {
                                            Toast.makeText(DpBbp.this, "Overall phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isNaN(x3) ||
                                                Double.isNaN(y1) || Double.isNaN(y2) || Double.isNaN(y3) ||
                                                Math.abs(x1+x2+x3-1.0)>1e-3 || Math.abs(y1+y2+y3-1.0)>1e-3 ||
                                                x1<0.0 || x2<0.0 || x3<0.0 || y1<0.0 || y2<0.0 || y3<0.0) {
                                            Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                        }
                                        dpbbp_x1_input.setText(x1.toString());
                                        dpbbp_x2_input.setText(x2.toString());
                                        dpbbp_x3_input.setText(x3.toString());
                                        dpbbp_y1_input.setText(y1.toString());
                                        dpbbp_y2_input.setText(y2.toString());
                                        dpbbp_y3_input.setText(y3.toString());
                                        spinner_dpbbp_system.setEnabled(true);
                                        spinner_dpbbp_components.setEnabled(true);
                                        spinner_dpbbp_mode.setEnabled(true);
                                        dpbbp_igid_solve.setVisibility(View.GONE);
                                        dpbbp_display.setVisibility(View.VISIBLE);
                                        dpbbp_reset.setVisibility(View.GONE);
                                        Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(DpBbp.this, "Flash process is not possible at given conditions! At T = "+Double.toString(t)+" K, P should be between "+Double.toString(pdp)+" kPa and "+Double.toString(pbbp)+" kPa!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                break;
                            case 3:
                                //IGID > Flash calculations > 4 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_p_input.getText().length() == 0 |
                                        dpbbp_z1_input.getText().length() == 0 | dpbbp_z2_input.getText().length() == 0 |
                                        dpbbp_z3_input.getText().length() == 0 | dpbbp_z4_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_a3_input.getText().length() == 0 | dpbbp_a4_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_b3_input.getText().length() == 0 | dpbbp_b4_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0 |
                                        dpbbp_c3_input.getText().length() == 0 | dpbbp_c4_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double z1 = Double.parseDouble(dpbbp_z1_input.getText().toString());
                                    Double z2 = Double.parseDouble(dpbbp_z2_input.getText().toString());
                                    Double z3 = Double.parseDouble(dpbbp_z3_input.getText().toString());
                                    Double z4 = Double.parseDouble(dpbbp_z4_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double a4 = Double.parseDouble(dpbbp_a4_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double b4 = Double.parseDouble(dpbbp_b4_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double c4 = Double.parseDouble(dpbbp_c4_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double p4sat = Math.exp(a4 - b4 / (c4 + t));
                                    Double pbbp = z1 * p1sat + z2 * p2sat + z3 * p3sat + z4 * p4sat;
                                    Double pdp = 1.0 / (z1 / p1sat + z2 / p2sat + z3 / p3sat + z4 / p4sat);
                                    if (pdp <= p & p <= pbbp) {
                                        //Flash is possible
                                        Double V1 = 0.5, V0 = 0.0;
                                        Double fV0, dfV0;
                                        Double tol = 0.000000001;
                                        int iter = 0;
                                        while (Math.abs(V1 - V0) > tol & iter < 1000) {
                                            V0 = V1;
                                            fV0 = z1 / ((p / p1sat) * (1.0 - V0) + V0)
                                                    + z2 / ((p / p2sat) * (1.0 - V0) + V0)
                                                    + z3 / ((p / p3sat) * (1.0 - V0) + V0)
                                                    + z4 / ((p / p4sat) * (1.0 - V0) + V0) - 1.0;
                                            dfV0 = z1 / Math.pow(((p / p1sat) * (1.0 - V0) + V0), 2) * (p / p1sat - 1.0)
                                                    + z2 / Math.pow(((p / p2sat) * (1.0 - V0) + V0), 2) * (p / p2sat - 1.0)
                                                    + z3 / Math.pow(((p / p3sat) * (1.0 - V0) + V0), 2) * (p / p3sat - 1.0)
                                                    + z4 / Math.pow(((p / p4sat) * (1.0 - V0) + V0), 2) * (p / p4sat - 1.0);
                                            V1 = V0 - fV0 / dfV0;
                                            iter = iter + 1;
                                        }
                                        Double y1 = z1 / ((p / p1sat) * (1.0 - V1) + V1);
                                        Double y2 = z2 / ((p / p2sat) * (1.0 - V1) + V1);
                                        Double y3 = z3 / ((p / p3sat) * (1.0 - V1) + V1);
                                        Double y4 = z4 / ((p / p4sat) * (1.0 - V1) + V1);
                                        Double L = 1.0 - V1;
                                        Double x1 = (z1 - y1 * V1) / L;
                                        Double x2 = (z2 - y2 * V1) / L;
                                        Double x3 = (z3 - y3 * V1) / L;
                                        Double x4 = (z4 - y4 * V1) / L;
                                        if (Math.abs(z1+z2+z3+z4-1.0)>1e-3 || z1<0.0 || z2<0.0 || z3<0.0 || z4<0.0) {
                                            Toast.makeText(DpBbp.this, "Overall phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isNaN(x3) ||  Double.isNaN(x4) ||
                                                Double.isNaN(y1) || Double.isNaN(y2) || Double.isNaN(y3) || Double.isNaN(y4) ||
                                                Math.abs(x1+x2+x3+x4-1.0)>1e-3 || Math.abs(y1+y2+y3+y4-1.0)>1e-3 ||
                                                x1<0.0 || x2<0.0 || x3<0.0 || x4<0.0 || y1<0.0 || y2<0.0 || y3<0.0 || y4<0.0) {
                                            Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                        }
                                        dpbbp_x1_input.setText(x1.toString());
                                        dpbbp_x2_input.setText(x2.toString());
                                        dpbbp_x3_input.setText(x3.toString());
                                        dpbbp_x4_input.setText(x4.toString());
                                        dpbbp_y1_input.setText(y1.toString());
                                        dpbbp_y2_input.setText(y2.toString());
                                        dpbbp_y3_input.setText(y3.toString());
                                        dpbbp_y4_input.setText(y4.toString());
                                        spinner_dpbbp_system.setEnabled(true);
                                        spinner_dpbbp_components.setEnabled(true);
                                        spinner_dpbbp_mode.setEnabled(true);
                                        dpbbp_igid_solve.setVisibility(View.GONE);
                                        dpbbp_display.setVisibility(View.VISIBLE);
                                        dpbbp_reset.setVisibility(View.GONE);
                                        Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(DpBbp.this, "Flash process is not possible at given conditions! At T = "+Double.toString(t)+" K, P should be between "+Double.toString(pdp)+" kPa and "+Double.toString(pbbp)+" kPa!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                break;
                        }
                        break;
                    case 0:
                        //IGID > Mode not selected
                        Toast.makeText(DpBbp.this, "Please select calculation mode!", Toast.LENGTH_SHORT).show();
                        spinner_dpbbp_system.setEnabled(true);
                        spinner_dpbbp_components.setEnabled(true);
                        spinner_dpbbp_mode.setEnabled(true);
                        dpbbp_igid_solve.setVisibility(View.GONE);
                        dpbbp_display.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        //Ideal gas, non-ideal solution
        Button dpbbp_ignid_solve = (Button) findViewById(R.id.dpbbp_ignid_solve);
        dpbbp_ignid_solve.setOnClickListener(new View.OnClickListener() {
            Spinner spinner_dpbbp_system = (Spinner) findViewById(R.id.dpbbp_system_input);
            Spinner spinner_dpbbp_components = (Spinner) findViewById(R.id.dpbbp_components_input);
            Spinner spinner_dpbbp_mode = (Spinner) findViewById(R.id.dpbbp_mode_input);
            TextView dpbbp_t_input = (TextView) findViewById(R.id.dpbbp_t_input);
            TextView dpbbp_p_input = (TextView) findViewById(R.id.dpbbp_p_input);
            TextView dpbbp_x1_input = (TextView) findViewById(R.id.dpbbp_x1_input);
            TextView dpbbp_x2_input = (TextView) findViewById(R.id.dpbbp_x2_input);
            TextView dpbbp_x3_input = (TextView) findViewById(R.id.dpbbp_x3_input);
            TextView dpbbp_y1_input = (TextView) findViewById(R.id.dpbbp_y1_input);
            TextView dpbbp_y2_input = (TextView) findViewById(R.id.dpbbp_y2_input);
            TextView dpbbp_y3_input = (TextView) findViewById(R.id.dpbbp_y3_input);
            TextView dpbbp_z1_input = (TextView) findViewById(R.id.dpbbp_z1_input);
            TextView dpbbp_z2_input = (TextView) findViewById(R.id.dpbbp_z2_input);
            TextView dpbbp_z3_input = (TextView) findViewById(R.id.dpbbp_z3_input);
            TextView dpbbp_a1_input = (TextView) findViewById(R.id.dpbbp_a1_input);
            TextView dpbbp_a2_input = (TextView) findViewById(R.id.dpbbp_a2_input);
            TextView dpbbp_a3_input = (TextView) findViewById(R.id.dpbbp_a3_input);
            TextView dpbbp_b1_input = (TextView) findViewById(R.id.dpbbp_b1_input);
            TextView dpbbp_b2_input = (TextView) findViewById(R.id.dpbbp_b2_input);
            TextView dpbbp_b3_input = (TextView) findViewById(R.id.dpbbp_b3_input);
            TextView dpbbp_c1_input = (TextView) findViewById(R.id.dpbbp_c1_input);
            TextView dpbbp_c2_input = (TextView) findViewById(R.id.dpbbp_c2_input);
            TextView dpbbp_c3_input = (TextView) findViewById(R.id.dpbbp_c3_input);
            TextView dpbbp_gamma1_input = (TextView) findViewById(R.id.dpbbp_gamma1_input);
            TextView dpbbp_gamma2_input = (TextView) findViewById(R.id.dpbbp_gamma2_input);
            TextView dpbbp_gamma3_input = (TextView) findViewById(R.id.dpbbp_gamma3_input);
            TextView dpbbp_A12_input = (TextView) findViewById(R.id.dpbbp_A12_input);
            TextView dpbbp_A21_input = (TextView) findViewById(R.id.dpbbp_A21_input);
            TextView dpbbp_A13_input = (TextView) findViewById(R.id.dpbbp_A13_input);
            TextView dpbbp_A31_input = (TextView) findViewById(R.id.dpbbp_A31_input);
            TextView dpbbp_A23_input = (TextView) findViewById(R.id.dpbbp_A23_input);
            TextView dpbbp_A32_input = (TextView) findViewById(R.id.dpbbp_A32_input);
            TextView dpbbp_C_input = (TextView) findViewById(R.id.dpbbp_C_input);
            Button dpbbp_display = (Button) findViewById(R.id.dpbbp_display);
            Button dpbbp_reset = (Button) findViewById(R.id.dpbbp_reset);
            Button dpbbp_ignid_solve = (Button) findViewById(R.id.dpbbp_ignid_solve);

            @Override
            public void onClick(View v) {
                switch (spinner_dpbbp_mode.getSelectedItemPosition()) {
                    case 1:
                        //IGNID > Bubble point pressure
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //IGNID > Bubble point pressure > 2 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_x1_input.getText().length() == 0 |
                                        dpbbp_x2_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double x1 = Double.parseDouble(dpbbp_x1_input.getText().toString());
                                    Double x2 = Double.parseDouble(dpbbp_x2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double gamma1 = Math.exp(Math.pow(x2, 2.0) * (A12 + 2.0 * (A21 - A12 - C) * x1 + 3.0 * C * Math.pow(x1, 2.0)));
                                    Double gamma2 = Math.exp(Math.pow(x1, 2.0) * (A21 + 2.0 * (A12 - A21 - C) * x2 + 3.0 * C * Math.pow(x2, 2.0)));
                                    Double p = x1 * gamma1 * p1sat + x2 * gamma2 * p2sat;
                                    Double y1 = x1 * gamma1 * p1sat / p;
                                    Double y2 = x2 * gamma2 * p2sat / p;
                                    if (Math.abs(x1+x2-1.0)>1e-3 || x1<0.0 || x2<0.0) {
                                        Toast.makeText(DpBbp.this, "Liquid phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    dpbbp_p_input.setText(p.toString());
                                    dpbbp_y1_input.setText(y1.toString());
                                    dpbbp_y2_input.setText(y2.toString());
                                    dpbbp_gamma1_input.setText(gamma1.toString());
                                    dpbbp_gamma2_input.setText(gamma2.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_ignid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                            case 2:
                                //IGNID > Bubble point pressure > 3 components
                                if (dpbbp_t_input.getText().length() == 0 |
                                        dpbbp_x1_input.getText().length() == 0 | dpbbp_x2_input.getText().length() == 0 |
                                        dpbbp_x3_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_a3_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_b3_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0 | dpbbp_c3_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A13_input.getText().length() > 0 & dpbbp_A31_input.getText().length() == 0) {
                                        dpbbp_A31_input.setText(dpbbp_A13_input.getText());
                                    }
                                    if (dpbbp_A31_input.getText().length() > 0 & dpbbp_A13_input.getText().length() == 0) {
                                        dpbbp_A13_input.setText(dpbbp_A31_input.getText());
                                    }
                                    if (dpbbp_A23_input.getText().length() > 0 & dpbbp_A32_input.getText().length() == 0) {
                                        dpbbp_A32_input.setText(dpbbp_A23_input.getText());
                                    }
                                    if (dpbbp_A32_input.getText().length() > 0 & dpbbp_A23_input.getText().length() == 0) {
                                        dpbbp_A23_input.setText(dpbbp_A32_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    if (dpbbp_A31_input.getText().length() == 0 & dpbbp_A13_input.getText().length() == 0) {
                                        dpbbp_A13_input.setText("0.0");
                                        dpbbp_A31_input.setText("0.0");
                                    }
                                    if (dpbbp_A32_input.getText().length() == 0 & dpbbp_A23_input.getText().length() == 0) {
                                        dpbbp_A23_input.setText("0.0");
                                        dpbbp_A32_input.setText("0.0");
                                    }
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double x1 = Double.parseDouble(dpbbp_x1_input.getText().toString());
                                    Double x2 = Double.parseDouble(dpbbp_x2_input.getText().toString());
                                    Double x3 = Double.parseDouble(dpbbp_x3_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double A13 = Double.parseDouble(dpbbp_A13_input.getText().toString());
                                    Double A31 = Double.parseDouble(dpbbp_A31_input.getText().toString());
                                    Double A23 = Double.parseDouble(dpbbp_A23_input.getText().toString());
                                    Double A32 = Double.parseDouble(dpbbp_A32_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double gamma1 = Math.exp(Math.pow(x2, 2.0) * (A12 + 2.0 * x1 * (A21 - A12))
                                                    + Math.pow(x3, 2.0) * (A13 + 2.0 * x1 * (A31 - A13))
                                                    + x2 * x3 * (
                                                    0.5 * (A21 + A12 + A31 + A13 - A23 - A32)
                                                            + x1 * (A21 - A12 + A31 - A13)
                                                            + (x2 - x3) * (A23 - A32)
                                                            - C * (1.0 - 2.0 * x1))
                                    );
                                    Double gamma2 = Math.exp(Math.pow(x3, 2.0) * (A23 + 2.0 * x2 * (A32 - A23))
                                                    + Math.pow(x1, 2.0) * (A21 + 2.0 * x2 * (A12 - A21))
                                                    + x3 * x1 * (
                                                    0.5 * (A32 + A23 + A12 + A21 - A31 - A13)
                                                            + x2 * (A32 - A23 + A12 - A21)
                                                            + (x3 - x1) * (A31 - A13)
                                                            - C * (1.0 - 2.0 * x2))
                                    );
                                    Double gamma3 = Math.exp(Math.pow(x1, 2.0) * (A31 + 2.0 * x3 * (A13 - A31))
                                                    + Math.pow(x2, 2.0) * (A32 + 2.0 * x3 * (A23 - A32))
                                                    + x1 * x2 * (
                                                    0.5 * (A13 + A31 + A23 + A32 - A12 - A21)
                                                            + x3 * (A13 - A31 + A23 - A32)
                                                            + (x1 - x2) * (A12 - A21)
                                                            - C * (1.0 - 2.0 * x3))
                                    );
                                    Double p = x1 * gamma1 * p1sat + x2 * gamma2 * p2sat + x3 * gamma3 * p3sat;
                                    Double y1 = x1 * gamma1 * p1sat / p;
                                    Double y2 = x2 * gamma2 * p2sat / p;
                                    Double y3 = x3 * gamma3 * p3sat / p;
                                    if (Math.abs(x1+x2+x3-1.0)>1e-3 || x1<0.0 || x2<0.0 || x3<0.0) {
                                        Toast.makeText(DpBbp.this, "Liquid phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    dpbbp_p_input.setText(p.toString());
                                    dpbbp_y1_input.setText(y1.toString());
                                    dpbbp_y2_input.setText(y2.toString());
                                    dpbbp_y3_input.setText(y3.toString());
                                    dpbbp_gamma1_input.setText(gamma1.toString());
                                    dpbbp_gamma2_input.setText(gamma2.toString());
                                    dpbbp_gamma3_input.setText(gamma3.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_ignid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                        }
                        break;
                    case 2:
                        //IGNID > Bubble point temperature
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //IGNID > Bubble point temperature > 2 components
                                if (dpbbp_p_input.getText().length() == 0 | dpbbp_x1_input.getText().length() == 0 |
                                        dpbbp_x2_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double x1 = Double.parseDouble(dpbbp_x1_input.getText().toString());
                                    Double x2 = Double.parseDouble(dpbbp_x2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double t1sat = b1 / (a1 - Math.log(p)) - c1;
                                    Double t2sat = b2 / (a2 - Math.log(p)) - c2;
                                    Double tnew = x1 * t1sat + x2 * t2sat, told = tnew / 2.0;
                                    Double p1sat, p2sat, gamma1 = 0.0, gamma2 = 0.0;
                                    Double y1 = 0.0, y2 = 0.0;
                                    Double tol = 0.000000001;
                                    int iter = 0;
                                    while (Math.abs(tnew - told) / told > tol & iter < 1000) {
                                        told = tnew;
                                        p1sat = Math.exp(a1 - b1 / (c1 + tnew));
                                        p2sat = Math.exp(a2 - b2 / (c2 + tnew));
                                        y1 = x1 * gamma1 * p1sat / p;
                                        y2 = x2 * gamma2 * p2sat / p;
                                        gamma1 = Math.exp(Math.pow(x2, 2.0) * (A12 + 2.0 * (A21 - A12 - C) * x1 + 3 * C * Math.pow(x1, 2.0)));
                                        gamma2 = Math.exp(Math.pow(x1, 2.0) * (A21 + 2.0 * (A12 - A21 - C) * x2 + 3 * C * Math.pow(x2, 2.0)));
                                        p2sat = p / (x1 * gamma1 * p1sat / p2sat + x2 * gamma2 * p2sat / p2sat);
                                        tnew = b2 / (a2 - Math.log(p2sat)) - c2;
                                        iter = iter + 1;
                                    }
                                    if (Math.abs(x1+x2-1.0)>1e-3 || x1<0.0 || x2<0.0) {
                                        Toast.makeText(DpBbp.this, "Liquid phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    if (Double.isNaN(y1) || Double.isNaN(y2) ||
                                            Math.abs(y1+y2-1.0)>1e-3 ||
                                            y1<0.0 || y2<0.0) {
                                        Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_t_input.setText(tnew.toString());
                                    dpbbp_y1_input.setText(y1.toString());
                                    dpbbp_y2_input.setText(y2.toString());
                                    dpbbp_gamma1_input.setText(gamma1.toString());
                                    dpbbp_gamma2_input.setText(gamma2.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_ignid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                            case 2:
                                //IGNID > Bubble point temperature > 3 components
                                if (dpbbp_p_input.getText().length() == 0 |
                                        dpbbp_x1_input.getText().length() == 0 | dpbbp_x2_input.getText().length() == 0 |
                                        dpbbp_x3_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_a3_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_b3_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0 | dpbbp_c3_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A13_input.getText().length() > 0 & dpbbp_A31_input.getText().length() == 0) {
                                        dpbbp_A31_input.setText(dpbbp_A13_input.getText());
                                    }
                                    if (dpbbp_A31_input.getText().length() > 0 & dpbbp_A13_input.getText().length() == 0) {
                                        dpbbp_A13_input.setText(dpbbp_A31_input.getText());
                                    }
                                    if (dpbbp_A23_input.getText().length() > 0 & dpbbp_A32_input.getText().length() == 0) {
                                        dpbbp_A32_input.setText(dpbbp_A23_input.getText());
                                    }
                                    if (dpbbp_A32_input.getText().length() > 0 & dpbbp_A23_input.getText().length() == 0) {
                                        dpbbp_A23_input.setText(dpbbp_A32_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    if (dpbbp_A31_input.getText().length() == 0 & dpbbp_A13_input.getText().length() == 0) {
                                        dpbbp_A13_input.setText("0.0");
                                        dpbbp_A31_input.setText("0.0");
                                    }
                                    if (dpbbp_A32_input.getText().length() == 0 & dpbbp_A23_input.getText().length() == 0) {
                                        dpbbp_A23_input.setText("0.0");
                                        dpbbp_A32_input.setText("0.0");
                                    }
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double x1 = Double.parseDouble(dpbbp_x1_input.getText().toString());
                                    Double x2 = Double.parseDouble(dpbbp_x2_input.getText().toString());
                                    Double x3 = Double.parseDouble(dpbbp_x3_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double A13 = Double.parseDouble(dpbbp_A13_input.getText().toString());
                                    Double A31 = Double.parseDouble(dpbbp_A31_input.getText().toString());
                                    Double A23 = Double.parseDouble(dpbbp_A23_input.getText().toString());
                                    Double A32 = Double.parseDouble(dpbbp_A32_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double t1sat = b1 / (a1 - Math.log(p)) - c1;
                                    Double t2sat = b2 / (a2 - Math.log(p)) - c2;
                                    Double t3sat = b3 / (a3 - Math.log(p)) - c3;
                                    Double tnew = x1 * t1sat + x2 * t2sat + x3 * t3sat, told = tnew / 3.0;
                                    Double p1sat, p2sat, p3sat, gamma1 = 0.0, gamma2 = 0.0, gamma3 = 0.0;
                                    Double y1 = 0.0, y2 = 0.0, y3 = 0.0;
                                    Double tol = 0.000000001;
                                    int iter = 0;
                                    while (Math.abs(tnew - told) / told > tol & iter < 1000) {
                                        told = tnew;
                                        p1sat = Math.exp(a1 - b1 / (c1 + tnew));
                                        p2sat = Math.exp(a2 - b2 / (c2 + tnew));
                                        p3sat = Math.exp(a3 - b3 / (c3 + tnew));
                                        y1 = x1 * gamma1 * p1sat / p;
                                        y2 = x2 * gamma2 * p2sat / p;
                                        y3 = x3 * gamma3 * p3sat / p;
                                        gamma1 = Math.exp(Math.pow(x2, 2.0) * (A12 + 2.0 * x1 * (A21 - A12))
                                                        + Math.pow(x3, 2.0) * (A13 + 2.0 * x1 * (A31 - A13))
                                                        + x2 * x3 * (
                                                        0.5 * (A21 + A12 + A31 + A13 - A23 - A32)
                                                                + x1 * (A21 - A12 + A31 - A13)
                                                                + (x2 - x3) * (A23 - A32)
                                                                - C * (1.0 - 2.0 * x1))
                                        );
                                        gamma2 = Math.exp(Math.pow(x3, 2.0) * (A23 + 2.0 * x2 * (A32 - A23))
                                                        + Math.pow(x1, 2.0) * (A21 + 2.0 * x2 * (A12 - A21))
                                                        + x3 * x1 * (
                                                        0.5 * (A32 + A23 + A12 + A21 - A31 - A13)
                                                                + x2 * (A32 - A23 + A12 - A21)
                                                                + (x3 - x1) * (A31 - A13)
                                                                - C * (1.0 - 2.0 * x2))
                                        );
                                        gamma3 = Math.exp(Math.pow(x1, 2.0) * (A31 + 2.0 * x3 * (A13 - A31))
                                                        + Math.pow(x2, 2.0) * (A32 + 2.0 * x3 * (A23 - A32))
                                                        + x1 * x2 * (
                                                        0.5 * (A13 + A31 + A23 + A32 - A12 - A21)
                                                                + x3 * (A13 - A31 + A23 - A32)
                                                                + (x1 - x2) * (A12 - A21)
                                                                - C * (1.0 - 2.0 * x3))
                                        );
                                        p2sat = p / (x1 * gamma1 * p1sat / p2sat + x2 * gamma2 * p2sat / p2sat + x3 * gamma3 * p3sat / p2sat);
                                        tnew = b2 / (a2 - Math.log(p2sat)) - c2;
                                        iter = iter + 1;
                                    }
                                    if (Math.abs(x1+x2+x3-1.0)>1e-3 || x1<0.0 || x2<0.0 || x3<0.0) {
                                        Toast.makeText(DpBbp.this, "Liquid phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    if (Double.isNaN(y1) || Double.isNaN(y2) || Double.isNaN(y3) ||
                                            Math.abs(y1+y2+y3-1.0)>1e-3 ||
                                            y1<0.0 || y2<0.0 || y3<0.0) {
                                        Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_t_input.setText(tnew.toString());
                                    dpbbp_y1_input.setText(y1.toString());
                                    dpbbp_y2_input.setText(y2.toString());
                                    dpbbp_y3_input.setText(y3.toString());
                                    dpbbp_gamma1_input.setText(gamma1.toString());
                                    dpbbp_gamma2_input.setText(gamma2.toString());
                                    dpbbp_gamma3_input.setText(gamma3.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_ignid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                        }
                        break;
                    case 3:
                        //IGNID > Dew point temperature
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //IGNID > Dew point temperature > 2 components
                                if (dpbbp_p_input.getText().length() == 0 | dpbbp_y1_input.getText().length() == 0 |
                                        dpbbp_y2_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double y1 = Double.parseDouble(dpbbp_y1_input.getText().toString());
                                    Double y2 = Double.parseDouble(dpbbp_y2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double gamma1new = 1.0, gamma1old = 0.0;
                                    Double gamma2new = 1.0, gamma2old = 0.0;
                                    Double t1sat = b1 / (a1 - Math.log(p)) - c1;
                                    Double t2sat = b2 / (a2 - Math.log(p)) - c2;
                                    Double tnew = y1 * t1sat + y2 * t2sat, told = tnew / 2.0;
                                    Double p1sat, p2sat;
                                    Double x1 = 1.0, x2 = 1.0, x;
                                    Double tol = 0.000000001;
                                    int iter = 0, iter1 = 0, iter0;
                                    while (Math.abs(tnew - told) / told > tol & iter < 1000) {
                                        told = tnew;
                                        p1sat = Math.exp(a1 - b1 / (c1 + tnew));
                                        p2sat = Math.exp(a2 - b2 / (c2 + tnew));
                                        p2sat = p * (y1 / gamma1new * p2sat / p1sat + y2 / gamma2new * p2sat / p2sat);
                                        tnew = b2 / (a2 - Math.log(p2sat)) - c2;
                                        p1sat = Math.exp(a1 - b1 / (c1 + tnew));
                                        p2sat = Math.exp(a2 - b2 / (c2 + tnew));
                                        iter0 = 0;
                                        while (Math.abs(gamma1new - gamma1old) > tol & Math.abs(gamma2new - gamma2old) > tol & iter0 < 1000) {
                                            gamma1old = gamma1new;
                                            gamma2old = gamma2new;
                                            x1 = y1 * p / (gamma1new * p1sat);
                                            x2 = y2 * p / (gamma2new * p2sat);
                                            x = x1 + x2;
                                            x1 = x1 / x;
                                            x2 = x2 / x;
                                            gamma1new = Math.exp(Math.pow(x2, 2.0) * (A12 + 2.0 * (A21 - A12 - C) * x1 + 3 * C * Math.pow(x1, 2.0)));
                                            gamma2new = Math.exp(Math.pow(x1, 2.0) * (A21 + 2.0 * (A12 - A21 - C) * x2 + 3 * C * Math.pow(x2, 2.0)));
                                            iter0 = iter0 + 1;
                                            iter = iter + 1;
                                        }
                                        p2sat = p * (y1 / gamma1new * p2sat / p1sat + y2 / gamma2new * p2sat / p2sat);
                                        tnew = b2 / (a2 - Math.log(p2sat)) - c2;
                                        iter1 = iter1 + 1;
                                    }
                                    if (Math.abs(y1+y2-1.0)>1e-3 || y1<0.0 || y2<0.0) {
                                        Toast.makeText(DpBbp.this, "Vapour phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    if (Double.isNaN(x1) || Double.isNaN(x2) ||
                                            Math.abs(x1+x2-1.0)>1e-3 ||
                                            x1<0.0 || x2<0.0) {
                                        Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_t_input.setText(tnew.toString());
                                    dpbbp_x1_input.setText(x1.toString());
                                    dpbbp_x2_input.setText(x2.toString());
                                    dpbbp_gamma1_input.setText(gamma1new.toString());
                                    dpbbp_gamma2_input.setText(gamma2new.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_ignid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                            case 2:
                                //IGNID > Dew point temperature > 3 components
                                if (dpbbp_p_input.getText().length() == 0 |
                                        dpbbp_y1_input.getText().length() == 0 | dpbbp_y2_input.getText().length() == 0 |
                                        dpbbp_y3_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_a3_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_b3_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0 | dpbbp_c3_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A13_input.getText().length() > 0 & dpbbp_A31_input.getText().length() == 0) {
                                        dpbbp_A31_input.setText(dpbbp_A13_input.getText());
                                    }
                                    if (dpbbp_A31_input.getText().length() > 0 & dpbbp_A13_input.getText().length() == 0) {
                                        dpbbp_A13_input.setText(dpbbp_A31_input.getText());
                                    }
                                    if (dpbbp_A23_input.getText().length() > 0 & dpbbp_A32_input.getText().length() == 0) {
                                        dpbbp_A32_input.setText(dpbbp_A23_input.getText());
                                    }
                                    if (dpbbp_A32_input.getText().length() > 0 & dpbbp_A23_input.getText().length() == 0) {
                                        dpbbp_A23_input.setText(dpbbp_A32_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    if (dpbbp_A31_input.getText().length() == 0 & dpbbp_A13_input.getText().length() == 0) {
                                        dpbbp_A13_input.setText("0.0");
                                        dpbbp_A31_input.setText("0.0");
                                    }
                                    if (dpbbp_A32_input.getText().length() == 0 & dpbbp_A23_input.getText().length() == 0) {
                                        dpbbp_A23_input.setText("0.0");
                                        dpbbp_A32_input.setText("0.0");
                                    }
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double y1 = Double.parseDouble(dpbbp_y1_input.getText().toString());
                                    Double y2 = Double.parseDouble(dpbbp_y2_input.getText().toString());
                                    Double y3 = Double.parseDouble(dpbbp_y3_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double A13 = Double.parseDouble(dpbbp_A13_input.getText().toString());
                                    Double A31 = Double.parseDouble(dpbbp_A31_input.getText().toString());
                                    Double A23 = Double.parseDouble(dpbbp_A23_input.getText().toString());
                                    Double A32 = Double.parseDouble(dpbbp_A32_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double gamma1new = 1.0, gamma1old = 0.0;
                                    Double gamma2new = 1.0, gamma2old = 0.0;
                                    Double gamma3new = 1.0, gamma3old = 0.0;
                                    Double t1sat = b1 / (a1 - Math.log(p)) - c1;
                                    Double t2sat = b2 / (a2 - Math.log(p)) - c2;
                                    Double t3sat = b3 / (a3 - Math.log(p)) - c3;
                                    Double tnew = y1 * t1sat + y2 * t2sat + y3 * t3sat, told = tnew / 3.0;
                                    Double p1sat, p2sat, p3sat;
                                    Double x1 = 1.0, x2 = 1.0, x3 = 1.0, x;
                                    Double tol = 0.000000001;
                                    int iter = 0, iter1 = 0, iter0;
                                    while (Math.abs(tnew - told) / told > tol & iter < 1000) {
                                        told = tnew;
                                        p1sat = Math.exp(a1 - b1 / (c1 + tnew));
                                        p2sat = Math.exp(a2 - b2 / (c2 + tnew));
                                        p3sat = Math.exp(a3 - b3 / (c3 + tnew));
                                        p2sat = p * (y1 / gamma1new * p2sat / p1sat + y2 / gamma2new * p2sat / p2sat + y3 / gamma3new * p2sat / p3sat);
                                        tnew = b2 / (a2 - Math.log(p2sat)) - c2;
                                        p1sat = Math.exp(a1 - b1 / (c1 + tnew));
                                        p2sat = Math.exp(a2 - b2 / (c2 + tnew));
                                        p3sat = Math.exp(a3 - b3 / (c3 + tnew));
                                        iter0 = 0;
                                        while (Math.abs(gamma1new - gamma1old) > tol & Math.abs(gamma2new - gamma2old) > tol &
                                                Math.abs(gamma3new - gamma3old) > tol & iter0 < 1000) {
                                            gamma1old = gamma1new;
                                            gamma2old = gamma2new;
                                            gamma3old = gamma3new;
                                            x1 = y1 * p / (gamma1new * p1sat);
                                            x2 = y2 * p / (gamma2new * p2sat);
                                            x3 = y3 * p / (gamma3new * p3sat);
                                            x = x1 + x2 + x3;
                                            x1 = x1 / x;
                                            x2 = x2 / x;
                                            x3 = x3 / x;
                                            gamma1new = Math.exp(Math.pow(x2, 2.0) * (A12 + 2.0 * x1 * (A21 - A12))
                                                            + Math.pow(x3, 2.0) * (A13 + 2.0 * x1 * (A31 - A13))
                                                            + x2 * x3 * (
                                                            0.5 * (A21 + A12 + A31 + A13 - A23 - A32)
                                                                    + x1 * (A21 - A12 + A31 - A13)
                                                                    + (x2 - x3) * (A23 - A32)
                                                                    - C * (1.0 - 2.0 * x1))
                                            );
                                            gamma2new = Math.exp(Math.pow(x3, 2.0) * (A23 + 2.0 * x2 * (A32 - A23))
                                                            + Math.pow(x1, 2.0) * (A21 + 2.0 * x2 * (A12 - A21))
                                                            + x3 * x1 * (
                                                            0.5 * (A32 + A23 + A12 + A21 - A31 - A13)
                                                                    + x2 * (A32 - A23 + A12 - A21)
                                                                    + (x3 - x1) * (A31 - A13)
                                                                    - C * (1.0 - 2.0 * x2))
                                            );
                                            gamma3new = Math.exp(Math.pow(x1, 2.0) * (A31 + 2.0 * x3 * (A13 - A31))
                                                            + Math.pow(x2, 2.0) * (A32 + 2.0 * x3 * (A23 - A32))
                                                            + x1 * x2 * (
                                                            0.5 * (A13 + A31 + A23 + A32 - A12 - A21)
                                                                    + x3 * (A13 - A31 + A23 - A32)
                                                                    + (x1 - x2) * (A12 - A21)
                                                                    - C * (1.0 - 2.0 * x3))
                                            );
                                            iter0 = iter0 + 1;
                                            iter = iter + 1;
                                        }
                                        p2sat = p / (x1 * gamma1new * p1sat / p2sat + x2 * gamma2new * p2sat / p2sat + x3 * gamma3new * p3sat / p2sat);
                                        tnew = b2 / (a2 - Math.log(p2sat)) - c2;
                                        iter1 = iter1 + 1;
                                    }
                                    if (Math.abs(y1+y2+y3-1.0)>1e-3 || y1<0.0 || y2<0.0 || y3<0.0) {
                                        Toast.makeText(DpBbp.this, "Vapour phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isNaN(x3) ||
                                            Math.abs(x1+x2+x3-1.0)>1e-3 ||
                                            x1<0.0 || x2<0.0 || x3<0.0) {
                                        Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_t_input.setText(tnew.toString());
                                    dpbbp_x1_input.setText(x1.toString());
                                    dpbbp_x2_input.setText(x2.toString());
                                    dpbbp_x3_input.setText(x3.toString());
                                    dpbbp_gamma1_input.setText(gamma1new.toString());
                                    dpbbp_gamma2_input.setText(gamma2new.toString());
                                    dpbbp_gamma3_input.setText(gamma3new.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_ignid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                        }
                        break;
                    case 4:
                        //IGNID > Dew point pressure
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //IGNID > Dew point pressure > 2 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_y1_input.getText().length() == 0 |
                                        dpbbp_y2_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_b1_input.getText().length() == 0 |
                                        dpbbp_b2_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double y1 = Double.parseDouble(dpbbp_y1_input.getText().toString());
                                    Double y2 = Double.parseDouble(dpbbp_y2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double gamma1new = 1.0, gamma1old = 0.0;
                                    Double gamma2new = 1.0, gamma2old = 0.0;
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double pnew = 1.0 / (y1 / (gamma1new * p1sat) + y2 / (gamma2new * p2sat)), pold = pnew / 2.0;
                                    Double x1 = 1.0, x2 = 1.0, x;
                                    Double tol = 0.000000001;
                                    int iter = 0, iter1 = 0, iter0;
                                    while (Math.abs(pnew - pold) / pold > tol & iter1 < 1000) {
                                        pold = pnew;
                                        iter0 = 0;
                                        while (Math.abs(gamma1new - gamma1old) > tol & Math.abs(gamma2new - gamma2old) > tol & iter0 < 1000) {
                                            gamma1old = gamma1new;
                                            gamma2old = gamma2new;
                                            x1 = y1 * pnew / (gamma1new * p1sat);
                                            x2 = y2 * pnew / (gamma2new * p2sat);
                                            x = x1 + x2;
                                            x1 = x1 / x;
                                            x2 = x2 / x;
                                            gamma1new = Math.exp(Math.pow(x2, 2.0) * (A12 + 2.0 * (A21 - A12 - C) * x1 + 3 * C * Math.pow(x1, 2.0)));
                                            gamma2new = Math.exp(Math.pow(x1, 2.0) * (A21 + 2.0 * (A12 - A21 - C) * x2 + 3 * C * Math.pow(x2, 2.0)));
                                            iter0 = iter0 + 1;
                                            iter = iter + 1;
                                        }
                                        pnew = 1.0 / (y1 / (gamma1new * p1sat) + y2 / (gamma2new * p2sat));
                                        iter1 = iter1 + 1;
                                    }
                                    if (Math.abs(y1+y2-1.0)>1e-3 || y1<0.0 || y2<0.0) {
                                        Toast.makeText(DpBbp.this, "Vapour phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    if (Double.isNaN(x1) || Double.isNaN(x2) ||
                                            Math.abs(x1+x2-1.0)>1e-3 ||
                                            x1<0.0 || x2<0.0) {
                                        Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_p_input.setText(pnew.toString());
                                    dpbbp_x1_input.setText(x1.toString());
                                    dpbbp_x2_input.setText(x2.toString());
                                    dpbbp_gamma1_input.setText(gamma1new.toString());
                                    dpbbp_gamma2_input.setText(gamma2new.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_ignid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                            case 2:
                                //IGNID > Dew point pressure > 3 components
                                if (dpbbp_t_input.getText().length() == 0 |
                                        dpbbp_y1_input.getText().length() == 0 | dpbbp_y2_input.getText().length() == 0 |
                                        dpbbp_y3_input.getText().length() == 0 | dpbbp_a1_input.getText().length() == 0 |
                                        dpbbp_a2_input.getText().length() == 0 | dpbbp_a3_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_b3_input.getText().length() == 0 | dpbbp_c1_input.getText().length() == 0 |
                                        dpbbp_c2_input.getText().length() == 0 | dpbbp_c3_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A13_input.getText().length() > 0 & dpbbp_A31_input.getText().length() == 0) {
                                        dpbbp_A31_input.setText(dpbbp_A13_input.getText());
                                    }
                                    if (dpbbp_A31_input.getText().length() > 0 & dpbbp_A13_input.getText().length() == 0) {
                                        dpbbp_A13_input.setText(dpbbp_A31_input.getText());
                                    }
                                    if (dpbbp_A23_input.getText().length() > 0 & dpbbp_A32_input.getText().length() == 0) {
                                        dpbbp_A32_input.setText(dpbbp_A23_input.getText());
                                    }
                                    if (dpbbp_A32_input.getText().length() > 0 & dpbbp_A23_input.getText().length() == 0) {
                                        dpbbp_A23_input.setText(dpbbp_A32_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    if (dpbbp_A31_input.getText().length() == 0 & dpbbp_A13_input.getText().length() == 0) {
                                        dpbbp_A13_input.setText("0.0");
                                        dpbbp_A31_input.setText("0.0");
                                    }
                                    if (dpbbp_A32_input.getText().length() == 0 & dpbbp_A23_input.getText().length() == 0) {
                                        dpbbp_A23_input.setText("0.0");
                                        dpbbp_A32_input.setText("0.0");
                                    }
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double y1 = Double.parseDouble(dpbbp_y1_input.getText().toString());
                                    Double y2 = Double.parseDouble(dpbbp_y2_input.getText().toString());
                                    Double y3 = Double.parseDouble(dpbbp_y3_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double A13 = Double.parseDouble(dpbbp_A13_input.getText().toString());
                                    Double A31 = Double.parseDouble(dpbbp_A31_input.getText().toString());
                                    Double A23 = Double.parseDouble(dpbbp_A23_input.getText().toString());
                                    Double A32 = Double.parseDouble(dpbbp_A32_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double gamma1new = 1.0, gamma1old = 0.0;
                                    Double gamma2new = 1.0, gamma2old = 0.0;
                                    Double gamma3new = 1.0, gamma3old = 0.0;
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double pnew = 1.0 / (y1 / (gamma1new * p1sat) + y2 / (gamma2new * p2sat) + y3 / (gamma3new * p3sat)), pold = pnew / 3.0;
                                    Double x1 = 1.0, x2 = 1.0, x3 = 1.0, x;
                                    Double tol = 0.000000001;
                                    int iter = 0, iter1 = 0, iter0;
                                    while (Math.abs(pnew - pold) / pold > tol & iter1 < 1000) {
                                        pold = pnew;
                                        iter0 = 0;
                                        while (Math.abs(gamma1new - gamma1old) > tol & Math.abs(gamma2new - gamma2old) > tol &
                                                Math.abs(gamma3new - gamma3old) > tol & iter0 < 1000) {
                                            gamma1old = gamma1new;
                                            gamma2old = gamma2new;
                                            gamma3old = gamma3new;
                                            x1 = y1 * pnew / (gamma1new * p1sat);
                                            x2 = y2 * pnew / (gamma2new * p2sat);
                                            x3 = y3 * pnew / (gamma3new * p3sat);
                                            x = x1 + x2 + x3;
                                            x1 = x1 / x;
                                            x2 = x2 / x;
                                            x3 = x3 / x;
                                            gamma1new = Math.exp(Math.pow(x2, 2.0) * (A12 + 2.0 * x1 * (A21 - A12))
                                                            + Math.pow(x3, 2.0) * (A13 + 2.0 * x1 * (A31 - A13))
                                                            + x2 * x3 * (
                                                            0.5 * (A21 + A12 + A31 + A13 - A23 - A32)
                                                                    + x1 * (A21 - A12 + A31 - A13)
                                                                    + (x2 - x3) * (A23 - A32)
                                                                    - C * (1.0 - 2.0 * x1))
                                            );
                                            gamma2new = Math.exp(Math.pow(x3, 2.0) * (A23 + 2.0 * x2 * (A32 - A23))
                                                            + Math.pow(x1, 2.0) * (A21 + 2.0 * x2 * (A12 - A21))
                                                            + x3 * x1 * (
                                                            0.5 * (A32 + A23 + A12 + A21 - A31 - A13)
                                                                    + x2 * (A32 - A23 + A12 - A21)
                                                                    + (x3 - x1) * (A31 - A13)
                                                                    - C * (1.0 - 2.0 * x2))
                                            );
                                            gamma3new = Math.exp(Math.pow(x1, 2.0) * (A31 + 2.0 * x3 * (A13 - A31))
                                                            + Math.pow(x2, 2.0) * (A32 + 2.0 * x3 * (A23 - A32))
                                                            + x1 * x2 * (
                                                            0.5 * (A13 + A31 + A23 + A32 - A12 - A21)
                                                                    + x3 * (A13 - A31 + A23 - A32)
                                                                    + (x1 - x2) * (A12 - A21)
                                                                    - C * (1.0 - 2.0 * x3))
                                            );
                                            iter0 = iter0 + 1;
                                            iter = iter + 1;
                                        }
                                        pnew = 1.0 / (y1 / (gamma1new * p1sat) + y2 / (gamma2new * p2sat) + y3 / (gamma3new * p3sat));
                                        iter1 = iter1 + 1;
                                    }
                                    if (Math.abs(y1+y2+y3-1.0)>1e-3 || y1<0.0 || y2<0.0 || y3<0.0) {
                                        Toast.makeText(DpBbp.this, "Vapour phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isNaN(x3) ||
                                            Math.abs(x1+x2+x3-1.0)>1e-3 ||
                                            x1<0.0 || x2<0.0 || x3<0.0) {
                                        Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_p_input.setText(pnew.toString());
                                    dpbbp_x1_input.setText(x1.toString());
                                    dpbbp_x2_input.setText(x2.toString());
                                    dpbbp_x3_input.setText(x3.toString());
                                    dpbbp_gamma1_input.setText(gamma1new.toString());
                                    dpbbp_gamma2_input.setText(gamma2new.toString());
                                    dpbbp_gamma3_input.setText(gamma3new.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_ignid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                        }
                        break;
                    case 5:
                        //IGNID > Flash calculations
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //IGNID > Flash calculations > 2 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_p_input.getText().length() == 0 |
                                        dpbbp_z1_input.getText().length() == 0 | dpbbp_z2_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double z1 = Double.parseDouble(dpbbp_z1_input.getText().toString());
                                    Double z2 = Double.parseDouble(dpbbp_z2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double pbbp = z1 * p1sat + z2 * p2sat;
                                    Double pdp = 1.0 / (z1 / p1sat + z2 / p2sat);
                                    Double gamma1 = 1.0, gamma2 = 1.0;
                                    Double y1new = 1.0, y2new = 1.0, y1old = 0.0, y2old = 0.0;
                                    Double x1new = 1.0, x2new = 1.0, x1old = 0.0, x2old = 0.0;
                                    Double L;
                                    if (pdp <= p & p <= pbbp) {
                                        //Flash is possible
                                        Double V1 = 0.5, V0 = 0.0;
                                        Double fV0, dfV0;
                                        Double tol = 0.000000001;
                                        int iter = 0;
                                        while (Math.abs(V1 - V0) > tol & iter < 1000 &
                                                Math.abs(y1new - y1old) > tol & Math.abs(y2new - y2old) > tol &
                                                Math.abs(x1new - x1old) > tol & Math.abs(x2new - x2old) > tol) {
                                            V0 = V1;
                                            y1old = y1new;
                                            y2old = y2new;
                                            x1old = x1new;
                                            x2old = x2new;
                                            fV0 = z1 / ((p / (gamma1 * p1sat)) * (1.0 - V0) + V0)
                                                    + z2 / ((p / (gamma2 * p2sat)) * (1.0 - V0) + V0) - 1.0;
                                            dfV0 = z1 / Math.pow(((p / (gamma1 * p1sat)) * (1.0 - V0) + V0), 2.0) * (p / (gamma1 * p1sat) - 1.0)
                                                    + z2 / Math.pow(((p / (gamma2 * p2sat)) * (1.0 - V0) + V0), 2.0) * (p / (gamma2 * p2sat) - 1.0);
                                            V1 = V0 - fV0 / dfV0;
                                            y1new = z1 / ((p / (gamma1 * p1sat)) * (1.0 - V1) + V1);
                                            y2new = z2 / ((p / (gamma2 * p2sat)) * (1.0 - V1) + V1);
                                            L = 1.0 - V1;
                                            x1new = (z1 - y1new * V1) / L;
                                            x2new = (z2 - y2new * V1) / L;
                                            gamma1 = Math.exp(Math.pow(x2new, 2.0) * (A12 + 2.0 * (A21 - A12 - C) * x1new + 3 * C * Math.pow(x1new, 2.0)));
                                            gamma2 = Math.exp(Math.pow(x1new, 2.0) * (A21 + 2.0 * (A12 - A21 - C) * x2new + 3 * C * Math.pow(x2new, 2.0)));
                                            iter = iter + 1;
                                        }
                                        if (Math.abs(z1+z2-1.0)>1e-3 || z1<0.0 || z2<0.0) {
                                            Toast.makeText(DpBbp.this, "Overall phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        if (Double.isNaN(x1new) || Double.isNaN(x2new) ||
                                                Double.isNaN(y1new) || Double.isNaN(y2new) ||
                                                Math.abs(x1new+x2new-1.0)>1e-3 || Math.abs(y1new+y2new-1.0)>1e-3 ||
                                                x1new<0.0 || x2new<0.0 || y1new<0.0 || y2new<0.0) {
                                            Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                        }
                                        dpbbp_x1_input.setText(x1new.toString());
                                        dpbbp_x2_input.setText(x2new.toString());
                                        dpbbp_y1_input.setText(y1new.toString());
                                        dpbbp_y2_input.setText(y2new.toString());
                                        dpbbp_gamma1_input.setText(gamma1.toString());
                                        dpbbp_gamma2_input.setText(gamma2.toString());
                                        spinner_dpbbp_system.setEnabled(true);
                                        spinner_dpbbp_components.setEnabled(true);
                                        spinner_dpbbp_mode.setEnabled(true);
                                        dpbbp_ignid_solve.setVisibility(View.GONE);
                                        dpbbp_display.setVisibility(View.VISIBLE);
                                        dpbbp_reset.setVisibility(View.GONE);
                                        Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(DpBbp.this, "Flash process is not possible at given conditions! At T = "+Double.toString(t)+" K, P should be between "+Double.toString(pdp)+" kPa and "+Double.toString(pbbp)+" kPa!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                break;
                            case 2:
                                //IGNID > Flash calculations > 3 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_p_input.getText().length() == 0 |
                                        dpbbp_z1_input.getText().length() == 0 | dpbbp_z2_input.getText().length() == 0 |
                                        dpbbp_z3_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_a3_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_b3_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0 |
                                        dpbbp_c3_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A13_input.getText().length() > 0 & dpbbp_A31_input.getText().length() == 0) {
                                        dpbbp_A31_input.setText(dpbbp_A13_input.getText());
                                    }
                                    if (dpbbp_A31_input.getText().length() > 0 & dpbbp_A13_input.getText().length() == 0) {
                                        dpbbp_A13_input.setText(dpbbp_A31_input.getText());
                                    }
                                    if (dpbbp_A23_input.getText().length() > 0 & dpbbp_A32_input.getText().length() == 0) {
                                        dpbbp_A32_input.setText(dpbbp_A23_input.getText());
                                    }
                                    if (dpbbp_A32_input.getText().length() > 0 & dpbbp_A23_input.getText().length() == 0) {
                                        dpbbp_A23_input.setText(dpbbp_A32_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    if (dpbbp_A31_input.getText().length() == 0 & dpbbp_A13_input.getText().length() == 0) {
                                        dpbbp_A13_input.setText("0.0");
                                        dpbbp_A31_input.setText("0.0");
                                    }
                                    if (dpbbp_A32_input.getText().length() == 0 & dpbbp_A23_input.getText().length() == 0) {
                                        dpbbp_A23_input.setText("0.0");
                                        dpbbp_A32_input.setText("0.0");
                                    }
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double z1 = Double.parseDouble(dpbbp_z1_input.getText().toString());
                                    Double z2 = Double.parseDouble(dpbbp_z2_input.getText().toString());
                                    Double z3 = Double.parseDouble(dpbbp_z3_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double a3 = Double.parseDouble(dpbbp_a3_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double b3 = Double.parseDouble(dpbbp_b3_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double c3 = Double.parseDouble(dpbbp_c3_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double A13 = Double.parseDouble(dpbbp_A13_input.getText().toString());
                                    Double A31 = Double.parseDouble(dpbbp_A31_input.getText().toString());
                                    Double A23 = Double.parseDouble(dpbbp_A23_input.getText().toString());
                                    Double A32 = Double.parseDouble(dpbbp_A32_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double p3sat = Math.exp(a3 - b3 / (c3 + t));
                                    Double pbbp = z1 * p1sat + z2 * p2sat + z3 * p3sat;
                                    Double pdp = 1.0 / (z1 / p1sat + z2 / p2sat + z3 / p3sat);
                                    Double gamma1 = 1.0, gamma2 = 1.0, gamma3 = 1.0;
                                    Double y1new = 1.0, y2new = 1.0, y3new = 1.0, y1old = 0.0, y2old = 0.0, y3old = 0.0;
                                    Double x1new = 1.0, x2new = 1.0, x3new = 1.0, x1old = 0.0, x2old = 0.0, x3old = 0.0;
                                    Double L;
                                    if (pdp <= p & p <= pbbp) {
                                        //Flash is possible
                                        Double V1 = 0.5, V0 = 0.0;
                                        Double fV0, dfV0;
                                        Double tol = 0.000000001;
                                        int iter = 0;
                                        while (Math.abs(V1 - V0) > tol & iter < 1000 &
                                                Math.abs(y1new - y1old) > tol & Math.abs(x1new - x1old) > tol &
                                                Math.abs(y2new - y2old) > tol & Math.abs(x2new - x2old) > tol &
                                                Math.abs(y3new - y3old) > tol & Math.abs(x3new - x3old) > tol) {
                                            V0 = V1;
                                            y1old = y1new;
                                            y2old = y2new;
                                            y3old = y3new;
                                            x1old = x1new;
                                            x2old = x2new;
                                            x3old = x3new;
                                            fV0 = z1 / ((p / (gamma1 * p1sat)) * (1.0 - V0) + V0)
                                                    + z2 / ((p / (gamma2 * p2sat)) * (1.0 - V0) + V0)
                                                    + z3 / ((p / (gamma3 * p3sat)) * (1.0 - V0) + V0) - 1.0;
                                            dfV0 = z1 / Math.pow(((p / (gamma1 * p1sat)) * (1.0 - V0) + V0), 2.0) * (p / (gamma1 * p1sat) - 1.0)
                                                    + z2 / Math.pow(((p / (gamma2 * p2sat)) * (1.0 - V0) + V0), 2.0) * (p / (gamma2 * p2sat) - 1.0)
                                                    + z3 / Math.pow(((p / (gamma3 * p3sat)) * (1.0 - V0) + V0), 2.0) * (p / (gamma3 * p3sat) - 1.0);
                                            V1 = V0 - fV0 / dfV0;
                                            y1new = z1 / ((p / (gamma1 * p1sat)) * (1.0 - V1) + V1);
                                            y2new = z2 / ((p / (gamma2 * p2sat)) * (1.0 - V1) + V1);
                                            y3new = z3 / ((p / (gamma3 * p3sat)) * (1.0 - V1) + V1);
                                            L = 1.0 - V1;
                                            x1new = (z1 - y1new * V1) / L;
                                            x2new = (z2 - y2new * V1) / L;
                                            x3new = (z3 - y3new * V1) / L;
                                            gamma1 = Math.exp(Math.pow(x2new, 2.0) * (A12 + 2.0 * x1new * (A21 - A12))
                                                            + Math.pow(x3new, 2.0) * (A13 + 2.0 * x1new * (A31 - A13))
                                                            + x2new * x3new * (
                                                            0.5 * (A21 + A12 + A31 + A13 - A23 - A32)
                                                                    + x1new * (A21 - A12 + A31 - A13)
                                                                    + (x2new - x3new) * (A23 - A32)
                                                                    - C * (1.0 - 2.0 * x1new))
                                            );
                                            gamma2 = Math.exp(Math.pow(x3new, 2.0) * (A23 + 2.0 * x2new * (A32 - A23))
                                                            + Math.pow(x1new, 2.0) * (A21 + 2.0 * x2new * (A12 - A21))
                                                            + x3new * x1new * (
                                                            0.5 * (A32 + A23 + A12 + A21 - A31 - A13)
                                                                    + x2new * (A32 - A23 + A12 - A21)
                                                                    + (x3new - x1new) * (A31 - A13)
                                                                    - C * (1.0 - 2.0 * x2new))
                                            );
                                            gamma3 = Math.exp(Math.pow(x1new, 2.0) * (A31 + 2.0 * x3new * (A13 - A31))
                                                            + Math.pow(x2new, 2.0) * (A32 + 2.0 * x3new * (A23 - A32))
                                                            + x1new * x2new * (
                                                            0.5 * (A13 + A31 + A23 + A32 - A12 - A21)
                                                                    + x3new * (A13 - A31 + A23 - A32)
                                                                    + (x1new - x2new) * (A12 - A21)
                                                                    - C * (1.0 - 2.0 * x3new))
                                            );
                                            iter = iter + 1;
                                        }
                                        if (Math.abs(z1+z2+z3-1.0)>1e-3  || z1<0.0 || z2<0.0 || z3<0.0) {
                                            Toast.makeText(DpBbp.this, "Overall phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        if (Double.isNaN(x1new) || Double.isNaN(x2new) || Double.isNaN(x3new) ||
                                                Double.isNaN(y1new) || Double.isNaN(y2new) || Double.isNaN(y3new) ||
                                                Math.abs(x1new+x2new+x3new-1.0)>1e-3 || Math.abs(y1new+y2new+y3new-1.0)>1e-3 ||
                                                x1new<0.0 || x2new<0.0 || x3new<0.0 || y1new<0.0 || y2new<0.0 || y3new<0.0) {
                                            Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                        }
                                        dpbbp_x1_input.setText(x1new.toString());
                                        dpbbp_x2_input.setText(x2new.toString());
                                        dpbbp_x3_input.setText(x3new.toString());
                                        dpbbp_y1_input.setText(y1new.toString());
                                        dpbbp_y2_input.setText(y2new.toString());
                                        dpbbp_y3_input.setText(y3new.toString());
                                        dpbbp_gamma1_input.setText(gamma1.toString());
                                        dpbbp_gamma2_input.setText(gamma2.toString());
                                        dpbbp_gamma3_input.setText(gamma3.toString());
                                        spinner_dpbbp_system.setEnabled(true);
                                        spinner_dpbbp_components.setEnabled(true);
                                        spinner_dpbbp_mode.setEnabled(true);
                                        dpbbp_ignid_solve.setVisibility(View.GONE);
                                        dpbbp_display.setVisibility(View.VISIBLE);
                                        dpbbp_reset.setVisibility(View.GONE);
                                        Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(DpBbp.this, "Flash process is not possible at given conditions! At T = "+Double.toString(t)+" K, P should be between "+Double.toString(pdp)+" kPa and "+Double.toString(pbbp)+" kPa!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                break;
                        }
                        break;
                    case 0:
                        //IGNID > Mode not selected
                        Toast.makeText(DpBbp.this, "Please select calculation mode!", Toast.LENGTH_SHORT).show();
                        spinner_dpbbp_system.setEnabled(true);
                        spinner_dpbbp_components.setEnabled(true);
                        spinner_dpbbp_mode.setEnabled(true);
                        dpbbp_ignid_solve.setVisibility(View.GONE);
                        dpbbp_display.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        //Non-ideal gas, non-ideal solution
        Button dpbbp_nignid_solve = (Button) findViewById(R.id.dpbbp_nignid_solve);
        dpbbp_nignid_solve.setOnClickListener(new View.OnClickListener() {
            Spinner spinner_dpbbp_system = (Spinner) findViewById(R.id.dpbbp_system_input);
            Spinner spinner_dpbbp_components = (Spinner) findViewById(R.id.dpbbp_components_input);
            Spinner spinner_dpbbp_mode = (Spinner) findViewById(R.id.dpbbp_mode_input);
            TextView dpbbp_t_input = (TextView) findViewById(R.id.dpbbp_t_input);
            TextView dpbbp_p_input = (TextView) findViewById(R.id.dpbbp_p_input);
            TextView dpbbp_x1_input = (TextView) findViewById(R.id.dpbbp_x1_input);
            TextView dpbbp_x2_input = (TextView) findViewById(R.id.dpbbp_x2_input);
            TextView dpbbp_y1_input = (TextView) findViewById(R.id.dpbbp_y1_input);
            TextView dpbbp_y2_input = (TextView) findViewById(R.id.dpbbp_y2_input);
            TextView dpbbp_z1_input = (TextView) findViewById(R.id.dpbbp_z1_input);
            TextView dpbbp_z2_input = (TextView) findViewById(R.id.dpbbp_z2_input);
            TextView dpbbp_a1_input = (TextView) findViewById(R.id.dpbbp_a1_input);
            TextView dpbbp_a2_input = (TextView) findViewById(R.id.dpbbp_a2_input);
            TextView dpbbp_b1_input = (TextView) findViewById(R.id.dpbbp_b1_input);
            TextView dpbbp_b2_input = (TextView) findViewById(R.id.dpbbp_b2_input);
            TextView dpbbp_c1_input = (TextView) findViewById(R.id.dpbbp_c1_input);
            TextView dpbbp_c2_input = (TextView) findViewById(R.id.dpbbp_c2_input);
            TextView dpbbp_w1_input = (TextView) findViewById(R.id.dpbbp_w1_input);
            TextView dpbbp_w2_input = (TextView) findViewById(R.id.dpbbp_w2_input);
            TextView dpbbp_tc1_input = (TextView) findViewById(R.id.dpbbp_tc1_input);
            TextView dpbbp_tc2_input = (TextView) findViewById(R.id.dpbbp_tc2_input);
            TextView dpbbp_pc1_input = (TextView) findViewById(R.id.dpbbp_pc1_input);
            TextView dpbbp_pc2_input = (TextView) findViewById(R.id.dpbbp_pc2_input);
            TextView dpbbp_vc1_input = (TextView) findViewById(R.id.dpbbp_vc1_input);
            TextView dpbbp_vc2_input = (TextView) findViewById(R.id.dpbbp_vc2_input);
            TextView dpbbp_zc1_input = (TextView) findViewById(R.id.dpbbp_zc1_input);
            TextView dpbbp_zc2_input = (TextView) findViewById(R.id.dpbbp_zc2_input);
            TextView dpbbp_phi1_input = (TextView) findViewById(R.id.dpbbp_phi1_input);
            TextView dpbbp_phisat1_input = (TextView) findViewById(R.id.dpbbp_phisat1_input);
            TextView dpbbp_pcr1_input = (TextView) findViewById(R.id.dpbbp_pcr1_input);
            TextView dpbbp_phi2_input = (TextView) findViewById(R.id.dpbbp_phi2_input);
            TextView dpbbp_phisat2_input = (TextView) findViewById(R.id.dpbbp_phisat2_input);
            TextView dpbbp_pcr2_input = (TextView) findViewById(R.id.dpbbp_pcr2_input);
            TextView dpbbp_gamma1_input = (TextView) findViewById(R.id.dpbbp_gamma1_input);
            TextView dpbbp_gamma2_input = (TextView) findViewById(R.id.dpbbp_gamma2_input);
            TextView dpbbp_A12_input = (TextView) findViewById(R.id.dpbbp_A12_input);
            TextView dpbbp_A21_input = (TextView) findViewById(R.id.dpbbp_A21_input);
            TextView dpbbp_C_input = (TextView) findViewById(R.id.dpbbp_C_input);
            Button dpbbp_display = (Button) findViewById(R.id.dpbbp_display);
            Button dpbbp_reset = (Button) findViewById(R.id.dpbbp_reset);
            Button dpbbp_nignid_solve = (Button) findViewById(R.id.dpbbp_nignid_solve);
            @Override
            public void onClick(View v) {
                switch (spinner_dpbbp_mode.getSelectedItemPosition()) {
                    case 1:
                        //NIGNID > Bubble point pressure
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //NIGNID > Bubble point pressure > 2 components
                                if (dpbbp_t_input.getText().length() == 0 |
                                        dpbbp_x1_input.getText().length() == 0 | dpbbp_x2_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0 |
                                        dpbbp_w1_input.getText().length() == 0 | dpbbp_w2_input.getText().length() == 0 |
                                        dpbbp_tc1_input.getText().length() == 0 | dpbbp_tc2_input.getText().length() == 0 |
                                        dpbbp_pc1_input.getText().length() == 0 | dpbbp_pc2_input.getText().length() == 0 |
                                        dpbbp_vc1_input.getText().length() == 0 | dpbbp_vc2_input.getText().length() == 0 |
                                        dpbbp_zc1_input.getText().length() == 0 | dpbbp_zc2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double x1 = Double.parseDouble(dpbbp_x1_input.getText().toString());
                                    Double x2 = Double.parseDouble(dpbbp_x2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double w1 = Double.parseDouble(dpbbp_w1_input.getText().toString());
                                    Double w2 = Double.parseDouble(dpbbp_w2_input.getText().toString());
                                    Double tc1 = Double.parseDouble(dpbbp_tc1_input.getText().toString());
                                    Double tc2 = Double.parseDouble(dpbbp_tc2_input.getText().toString());
                                    Double pc1 = Double.parseDouble(dpbbp_pc1_input.getText().toString());
                                    Double pc2 = Double.parseDouble(dpbbp_pc2_input.getText().toString());
                                    Double vc1 = Double.parseDouble(dpbbp_vc1_input.getText().toString());
                                    Double vc2 = Double.parseDouble(dpbbp_vc2_input.getText().toString());
                                    Double zc1 = Double.parseDouble(dpbbp_tc1_input.getText().toString());
                                    Double zc2 = Double.parseDouble(dpbbp_zc2_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double phi1 = 1.0, phi2 = 1.0;
                                    Double phisat1 = 1.0, phisat2 = 1.0;
                                    Double pcr1 = 1.0, pcr2 = 1.0;
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double gamma1 = Math.exp(Math.pow(x2,2.0)*(A12+2.0*(A21-A12-C)*x1+3.0*C*Math.pow(x1,2.0)));
                                    Double gamma2 = Math.exp(Math.pow(x1,2.0)*(A21+2.0*(A12-A21-C)*x2+3.0*C*Math.pow(x2,2.0)));
                                    Double pnew = x1*gamma1*p1sat*phisat1*pcr1/phi1+x2*gamma2*p2sat*phisat2*pcr2/phi2;
                                    Double pold = pnew/2.0;
                                    Double y1 = 1.0, y2 = 1.0;
                                    Double tol = 0.000000001;
                                    Double r = 8.3144598; //gas constant
                                    Double w12, tc12, zc12, vc12, pc12,
                                            tr11, tr22, tr12, pr11, pr22, pr12,
                                            b011, b111, b022, b122, b012, b112,
                                            bhat11, bhat22, bhat12, b11, b22, b12, delta12,
                                            vsat11, vsat22;
                                    int iter = 0;
                                    while (Math.abs(pnew-pold)/pold>tol & iter<1000) {
                                        //System values in deg C and kPa, critical values are in K and bar
                                        pold = pnew;
                                        y1 = x1*gamma1*p1sat*phisat1*pcr1/(pnew*phi1);
                                        y2 = x2*gamma2*p2sat*phisat2*pcr2/(pnew*phi2);
                                        w12 = 0.5*(w1+w2);
                                        tc12 = Math.sqrt(tc1*tc2);
                                        zc12 = 0.5*(zc1+zc2);
                                        vc12 = Math.pow(0.5 * (Math.pow(vc1, 1.0 / 3.0) + Math.pow(vc2, 1.0 / 3.0)), 3.0);
                                        pc12 = zc12*r*tc12/vc12;
                                        tr11 = (t+273.15)/tc1;
                                        tr22 = (t+273.15)/tc2;
                                        tr12 = (t+273.15)/tc12;
                                        pr11 = (pnew/100.0)/pc1;
                                        pr22 = (pnew/100.0)/pc2;
                                        pr12 = (pnew/100.0)/pc12;
                                        b011 = 0.083-0.422/Math.pow(tr11,1.6);
                                        b111 = 0.139-0.172/Math.pow(tr11,4.2);
                                        b022 = 0.083-0.422/Math.pow(tr22,1.6);
                                        b122 = 0.139-0.172/Math.pow(tr22,4.2);
                                        b012 = 0.083-0.422/Math.pow(tr12,1.6);
                                        b112 = 0.139-0.172/Math.pow(tr12,4.2);
                                        bhat11 = b011+w1*b111;
                                        bhat22 = b022+w2*b122;
                                        bhat12 = b012+w12*b112;
                                        b11 = bhat11*r*tc1/pc1;
                                        b22 = bhat22*r*tc2/pc2;
                                        b12 = bhat12*r*tc12/pc12;
                                        delta12 = 2.0*b12-b11-b22;
                                        phi1 = Math.exp((pnew/100.0)*(b11+Math.pow(y2,2.0)*delta12)/(r*(t+273.15)));
                                        phi2 = Math.exp((pnew/100.0)*(b22+Math.pow(y1,2.0)*delta12)/(r*(t+273.15)));
                                        p1sat = Math.exp(a1-b1/(c1+t));
                                        p2sat = Math.exp(a2-b2/(c2+t));
                                        phisat1 = Math.exp(bhat11*((p1sat/100.0)/pc1)/tr11);
                                        phisat2 = Math.exp(bhat22*((p2sat/100.0)/pc2)/tr22);
                                        vsat11 = vc1*Math.pow(Math.pow(zc1,1.0-tr11),2.0/7.0);
                                        vsat22 = vc2*Math.pow(Math.pow(zc2,1.0-tr22),2.0/7.0);
                                        pcr1 = Math.exp((vsat11*0.000001)*((pnew-p1sat)*1000.0)/(r*(t+273.15)));
                                        pcr2 = Math.exp((vsat22*0.000001)*((pnew-p2sat)*1000.0)/(r*(t+273.15)));
                                        pnew = x1*gamma1*p1sat*phisat1*pcr1/phi1+x2*gamma2*p2sat*phisat2*pcr2/phi2;
                                        iter = iter+1;
                                    }
                                    if (Math.abs(x1+x2-1.0)>1e-3  || x1<0.0 || x2<0.0) {
                                        Toast.makeText(DpBbp.this, "Liquid phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    if (Double.isNaN(y1) || Double.isNaN(y2) ||
                                            Math.abs(y1+y2-1.0)>1e-3 ||
                                            y1<0.0 || y2<0.0) {
                                        Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_p_input.setText(pnew.toString());
                                    dpbbp_y1_input.setText(y1.toString());
                                    dpbbp_y2_input.setText(y2.toString());
                                    dpbbp_gamma1_input.setText(gamma1.toString());
                                    dpbbp_gamma2_input.setText(gamma2.toString());
                                    dpbbp_phi1_input.setText(phi1.toString());
                                    dpbbp_phi2_input.setText(phi2.toString());
                                    dpbbp_phisat1_input.setText(phisat1.toString());
                                    dpbbp_phisat2_input.setText(phisat2.toString());
                                    dpbbp_pcr1_input.setText(pcr1.toString());
                                    dpbbp_pcr2_input.setText(pcr2.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_nignid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                        }
                        break;
                    case 2:
                        //NIGNID > Bubble point temperature
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //NIGNID > Bubble point temperature > 2 components
                                if (dpbbp_p_input.getText().length() == 0 |
                                        dpbbp_x1_input.getText().length() == 0 | dpbbp_x2_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0 |
                                        dpbbp_w1_input.getText().length() == 0 | dpbbp_w2_input.getText().length() == 0 |
                                        dpbbp_tc1_input.getText().length() == 0 | dpbbp_tc2_input.getText().length() == 0 |
                                        dpbbp_pc1_input.getText().length() == 0 | dpbbp_pc2_input.getText().length() == 0 |
                                        dpbbp_vc1_input.getText().length() == 0 | dpbbp_vc2_input.getText().length() == 0 |
                                        dpbbp_zc1_input.getText().length() == 0 | dpbbp_zc2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double x1 = Double.parseDouble(dpbbp_x1_input.getText().toString());
                                    Double x2 = Double.parseDouble(dpbbp_x2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double w1 = Double.parseDouble(dpbbp_w1_input.getText().toString());
                                    Double w2 = Double.parseDouble(dpbbp_w2_input.getText().toString());
                                    Double tc1 = Double.parseDouble(dpbbp_tc1_input.getText().toString());
                                    Double tc2 = Double.parseDouble(dpbbp_tc2_input.getText().toString());
                                    Double pc1 = Double.parseDouble(dpbbp_pc1_input.getText().toString());
                                    Double pc2 = Double.parseDouble(dpbbp_pc2_input.getText().toString());
                                    Double vc1 = Double.parseDouble(dpbbp_vc1_input.getText().toString());
                                    Double vc2 = Double.parseDouble(dpbbp_vc2_input.getText().toString());
                                    Double zc1 = Double.parseDouble(dpbbp_tc1_input.getText().toString());
                                    Double zc2 = Double.parseDouble(dpbbp_zc2_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double phi1 = 1.0, phi2 = 1.0;
                                    Double phisat1 = 1.0, phisat2 = 1.0;
                                    Double pcr1 = 1.0, pcr2 = 1.0;
                                    Double t1sat = b1 / (a1 - Math.log(p)) - c1;
                                    Double t2sat = b2 / (a2 - Math.log(p)) - c2;
                                    Double tnew = x1 * t1sat + x2 * t2sat, told = tnew / 2.0;
                                    Double p1sat, p2sat, gamma1 = 0.0, gamma2 = 0.0;
                                    Double y1 = 0.0, y2 = 0.0;
                                    Double tol = 0.000000001;
                                    Double r = 8.3144598; //gas constant
                                    Double w12, tc12, zc12, vc12, pc12,
                                            tr11, tr22, tr12, pr11, pr22, pr12,
                                            b011, b111, b022, b122, b012, b112,
                                            bhat11, bhat22, bhat12, b11, b22, b12, delta12,
                                            vsat11, vsat22;
                                    int iter = 0;
                                    while (Math.abs(tnew - told) / told > tol & iter < 1000) {
                                        told = tnew;
                                        p1sat = Math.exp(a1 - b1 / (c1 + tnew));
                                        p2sat = Math.exp(a2 - b2 / (c2 + tnew));
                                        w12 = 0.5*(w1+w2);
                                        tc12 = Math.sqrt(tc1*tc2);
                                        zc12 = 0.5*(zc1+zc2);
                                        vc12 = Math.pow(0.5 * (Math.pow(vc1, 1.0 / 3.0) + Math.pow(vc2, 1.0 / 3.0)), 3.0);
                                        pc12 = zc12*r*tc12/vc12;
                                        tr11 = (tnew+273.15)/tc1;
                                        tr22 = (tnew+273.15)/tc2;
                                        tr12 = (tnew+273.15)/tc12;
                                        pr11 = (p/100.0)/pc1;
                                        pr22 = (p/100.0)/pc2;
                                        pr12 = (p/100.0)/pc12;
                                        b011 = 0.083-0.422/Math.pow(tr11,1.6);
                                        b111 = 0.139-0.172/Math.pow(tr11,4.2);
                                        b022 = 0.083-0.422/Math.pow(tr22,1.6);
                                        b122 = 0.139-0.172/Math.pow(tr22,4.2);
                                        b012 = 0.083-0.422/Math.pow(tr12,1.6);
                                        b112 = 0.139-0.172/Math.pow(tr12,4.2);
                                        bhat11 = b011+w1*b111;
                                        bhat22 = b022+w2*b122;
                                        bhat12 = b012+w12*b112;
                                        b11 = bhat11*r*tc1/pc1;
                                        b22 = bhat22*r*tc2/pc2;
                                        b12 = bhat12*r*tc12/pc12;
                                        delta12 = 2.0*b12-b11-b22;
                                        phi1 = Math.exp((p/100.0)*(b11+Math.pow(y2,2.0)*delta12)/(r*(tnew+273.15)));
                                        phi2 = Math.exp((p/100.0)*(b22+Math.pow(y1,2.0)*delta12)/(r*(tnew+273.15)));
                                        phisat1 = Math.exp(bhat11*((p1sat/100.0)/pc1)/tr11);
                                        phisat2 = Math.exp(bhat22*((p2sat/100.0)/pc2)/tr22);
                                        vsat11 = vc1*Math.pow(Math.pow(zc1,1.0-tr11),2.0/7.0);
                                        vsat22 = vc2*Math.pow(Math.pow(zc2,1.0-tr22),2.0/7.0);
                                        pcr1 = Math.exp((vsat11*0.000001)*((p-p1sat)*1000.0)/(r*(tnew+273.15)));
                                        pcr2 = Math.exp((vsat22*0.000001)*((p-p2sat)*1000.0)/(r*(tnew+273.15)));
                                        y1 = x1*gamma1*p1sat*phisat1*pcr1/(p*phi1);
                                        y2 = x2*gamma2*p2sat*phisat2*pcr2/(p*phi2);
                                        gamma1 = Math.exp(Math.pow(x2, 2.0) * (A12 + 2.0 * (A21 - A12 - C) * x1 + 3.0 * C * Math.pow(x1, 2.0)));
                                        gamma2 = Math.exp(Math.pow(x1, 2.0) * (A21 + 2.0 * (A12 - A21 - C) * x2 + 3.0 * C * Math.pow(x2, 2.0)));
                                        p2sat = p/(x1*gamma1*phisat1*pcr1/phi1*p1sat/p2sat+x2*gamma2*phisat2*pcr2/phi2*p2sat/p2sat);
                                        tnew = b2 / (a2 - Math.log(p2sat)) - c2;
                                        iter = iter + 1;
                                    }
                                    if (Math.abs(x1+x2-1.0)>1e-3 || x1<0.0 || x2<0.0) {
                                        Toast.makeText(DpBbp.this, "Liquid phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    if (Double.isNaN(y1) || Double.isNaN(y2) ||
                                            Math.abs(y1+y2-1.0)>1e-3 ||
                                            y1<0.0 || y2<0.0) {
                                        Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_t_input.setText(tnew.toString());
                                    dpbbp_y1_input.setText(y1.toString());
                                    dpbbp_y2_input.setText(y2.toString());
                                    dpbbp_gamma1_input.setText(gamma1.toString());
                                    dpbbp_gamma2_input.setText(gamma2.toString());
                                    dpbbp_phi1_input.setText(phi1.toString());
                                    dpbbp_phi2_input.setText(phi2.toString());
                                    dpbbp_phisat1_input.setText(phisat1.toString());
                                    dpbbp_phisat2_input.setText(phisat2.toString());
                                    dpbbp_pcr1_input.setText(pcr1.toString());
                                    dpbbp_pcr2_input.setText(pcr2.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_nignid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                        }
                        break;
                    case 3:
                        //NIGNID > Dew point temperature
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //NIGNID > Dew point temperature > 2 components
                                if (dpbbp_p_input.getText().length() == 0 |
                                        dpbbp_y1_input.getText().length() == 0 | dpbbp_y2_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0 |
                                        dpbbp_w1_input.getText().length() == 0 | dpbbp_w2_input.getText().length() == 0 |
                                        dpbbp_tc1_input.getText().length() == 0 | dpbbp_tc2_input.getText().length() == 0 |
                                        dpbbp_pc1_input.getText().length() == 0 | dpbbp_pc2_input.getText().length() == 0 |
                                        dpbbp_vc1_input.getText().length() == 0 | dpbbp_vc2_input.getText().length() == 0 |
                                        dpbbp_zc1_input.getText().length() == 0 | dpbbp_zc2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double y1 = Double.parseDouble(dpbbp_y1_input.getText().toString());
                                    Double y2 = Double.parseDouble(dpbbp_y2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double w1 = Double.parseDouble(dpbbp_w1_input.getText().toString());
                                    Double w2 = Double.parseDouble(dpbbp_w2_input.getText().toString());
                                    Double tc1 = Double.parseDouble(dpbbp_tc1_input.getText().toString());
                                    Double tc2 = Double.parseDouble(dpbbp_tc2_input.getText().toString());
                                    Double pc1 = Double.parseDouble(dpbbp_pc1_input.getText().toString());
                                    Double pc2 = Double.parseDouble(dpbbp_pc2_input.getText().toString());
                                    Double vc1 = Double.parseDouble(dpbbp_vc1_input.getText().toString());
                                    Double vc2 = Double.parseDouble(dpbbp_vc2_input.getText().toString());
                                    Double zc1 = Double.parseDouble(dpbbp_tc1_input.getText().toString());
                                    Double zc2 = Double.parseDouble(dpbbp_zc2_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double phi1 = 1.0, phi2 = 1.0;
                                    Double phisat1 = 1.0, phisat2 = 1.0;
                                    Double pcr1 = 1.0, pcr2 = 1.0;
                                    Double gamma1new = 1.0, gamma1old = 0.0;
                                    Double gamma2new = 1.0, gamma2old = 0.0;
                                    Double t1sat = b1 / (a1 - Math.log(p)) - c1;
                                    Double t2sat = b2 / (a2 - Math.log(p)) - c2;
                                    Double tnew = y1 * t1sat + y2 * t2sat, told = tnew / 2.0;
                                    Double p1sat, p2sat;
                                    Double x1 = 1.0, x2 = 1.0, x;
                                    Double tol = 0.000000001;
                                    Double r = 8.3144598; //gas constant
                                    Double w12, tc12, zc12, vc12, pc12,
                                            tr11, tr22, tr12, pr11, pr22, pr12,
                                            b011, b111, b022, b122, b012, b112,
                                            bhat11, bhat22, bhat12, b11, b22, b12, delta12,
                                            vsat11, vsat22;
                                    int iter = 0, iter1 = 0, iter0;
                                    while (Math.abs(tnew - told) / told > tol & iter < 1000) {
                                        told = tnew;
                                        p1sat = Math.exp(a1 - b1 / (c1 + tnew));
                                        p2sat = Math.exp(a2 - b2 / (c2 + tnew));
                                        w12 = 0.5*(w1+w2);
                                        tc12 = Math.sqrt(tc1*tc2);
                                        zc12 = 0.5*(zc1+zc2);
                                        vc12 = Math.pow(0.5 * (Math.pow(vc1, 1.0 / 3.0) + Math.pow(vc2, 1.0 / 3.0)), 3.0);
                                        pc12 = zc12*r*tc12/vc12;
                                        tr11 = (tnew+273.15)/tc1;
                                        tr22 = (tnew+273.15)/tc2;
                                        tr12 = (tnew+273.15)/tc12;
                                        pr11 = (p/100.0)/pc1;
                                        pr22 = (p/100.0)/pc2;
                                        pr12 = (p/100.0)/pc12;
                                        b011 = 0.083-0.422/Math.pow(tr11,1.6);
                                        b111 = 0.139-0.172/Math.pow(tr11,4.2);
                                        b022 = 0.083-0.422/Math.pow(tr22,1.6);
                                        b122 = 0.139-0.172/Math.pow(tr22,4.2);
                                        b012 = 0.083-0.422/Math.pow(tr12,1.6);
                                        b112 = 0.139-0.172/Math.pow(tr12,4.2);
                                        bhat11 = b011+w1*b111;
                                        bhat22 = b022+w2*b122;
                                        bhat12 = b012+w12*b112;
                                        b11 = bhat11*r*tc1/pc1;
                                        b22 = bhat22*r*tc2/pc2;
                                        b12 = bhat12*r*tc12/pc12;
                                        delta12 = 2.0*b12-b11-b22;
                                        phi1 = Math.exp((p/100.0)*(b11+Math.pow(y2,2.0)*delta12)/(r*(tnew+273.15)));
                                        phi2 = Math.exp((p/100.0)*(b22+Math.pow(y1,2.0)*delta12)/(r*(tnew+273.15)));
                                        phisat1 = Math.exp(bhat11*((p1sat/100.0)/pc1)/tr11);
                                        phisat2 = Math.exp(bhat22*((p2sat/100.0)/pc2)/tr22);
                                        vsat11 = vc1*Math.pow(Math.pow(zc1,1.0-tr11),2.0/7.0);
                                        vsat22 = vc2*Math.pow(Math.pow(zc2,1.0-tr22),2.0/7.0);
                                        pcr1 = Math.exp((vsat11*0.000001)*((p-p1sat)*1000.0)/(r*(tnew+273.15)));
                                        pcr2 = Math.exp((vsat22*0.000001)*((p-p2sat)*1000.0)/(r*(tnew+273.15)));
                                        p2sat = p*(y1*phi1/(gamma1new*phisat1*pcr1)*p2sat/p1sat+y2*phi2/(gamma2new*phisat2*pcr2)*p2sat/p2sat);
                                        tnew = b2 / (a2 - Math.log(p2sat)) - c2;
                                        p1sat = Math.exp(a1 - b1 / (c1 + tnew));
                                        p2sat = Math.exp(a2 - b2 / (c2 + tnew));
                                        iter0 = 0;
                                        while (Math.abs(gamma1new - gamma1old) > tol & Math.abs(gamma2new - gamma2old) > tol & iter0 < 1000) {
                                            gamma1old = gamma1new;
                                            gamma2old = gamma2new;
                                            x1 = y1*p*phi1/(gamma1new*p1sat*phisat1*pcr1);
                                            x2 = y2*p*phi2/(gamma2new*p2sat*phisat2*pcr2);
                                            x = x1 + x2;
                                            x1 = x1 / x;
                                            x2 = x2 / x;
                                            gamma1new = Math.exp(Math.pow(x2, 2.0) * (A12 + 2.0 * (A21 - A12 - C) * x1 + 3.0 * C * Math.pow(x1, 2.0)));
                                            gamma2new = Math.exp(Math.pow(x1, 2.0) * (A21 + 2.0 * (A12 - A21 - C) * x2 + 3.0 * C * Math.pow(x2, 2.0)));
                                            iter0 = iter0 + 1;
                                            iter = iter + 1;
                                        }
                                        p2sat = p*(y1*phi1/(gamma1new*phisat1*pcr1)*p2sat/p1sat+y2*phi2/(gamma2new*phisat2*pcr2)*p2sat/p2sat);
                                        tnew = b2 / (a2 - Math.log(p2sat)) - c2;
                                        iter1 = iter1 + 1;
                                    }
                                    if (Math.abs(y1+y2-1.0)>1e-3 || y1<0.0 || y2<0.0) {
                                        Toast.makeText(DpBbp.this, "Vapour phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    if (Double.isNaN(x1) || Double.isNaN(x2) ||
                                            Math.abs(x1+x2-1.0)>1e-3 ||
                                            x1<0.0 || x2<0.0) {
                                        Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_t_input.setText(tnew.toString());
                                    dpbbp_x1_input.setText(x1.toString());
                                    dpbbp_x2_input.setText(x2.toString());
                                    dpbbp_gamma1_input.setText(gamma1new.toString());
                                    dpbbp_gamma2_input.setText(gamma2new.toString());
                                    dpbbp_phi1_input.setText(phi1.toString());
                                    dpbbp_phi2_input.setText(phi2.toString());
                                    dpbbp_phisat1_input.setText(phisat1.toString());
                                    dpbbp_phisat2_input.setText(phisat2.toString());
                                    dpbbp_pcr1_input.setText(pcr1.toString());
                                    dpbbp_pcr2_input.setText(pcr2.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_nignid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                        }
                        break;
                    case 4:
                        //NIGNID > Dew point pressure
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //NIGNID > Dew point pressure > 2 components
                                if (dpbbp_t_input.getText().length() == 0 |
                                        dpbbp_y1_input.getText().length() == 0 | dpbbp_y2_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0 |
                                        dpbbp_w1_input.getText().length() == 0 | dpbbp_w2_input.getText().length() == 0 |
                                        dpbbp_tc1_input.getText().length() == 0 | dpbbp_tc2_input.getText().length() == 0 |
                                        dpbbp_pc1_input.getText().length() == 0 | dpbbp_pc2_input.getText().length() == 0 |
                                        dpbbp_vc1_input.getText().length() == 0 | dpbbp_vc2_input.getText().length() == 0 |
                                        dpbbp_zc1_input.getText().length() == 0 | dpbbp_zc2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double y1 = Double.parseDouble(dpbbp_y1_input.getText().toString());
                                    Double y2 = Double.parseDouble(dpbbp_y2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double w1 = Double.parseDouble(dpbbp_w1_input.getText().toString());
                                    Double w2 = Double.parseDouble(dpbbp_w2_input.getText().toString());
                                    Double tc1 = Double.parseDouble(dpbbp_tc1_input.getText().toString());
                                    Double tc2 = Double.parseDouble(dpbbp_tc2_input.getText().toString());
                                    Double pc1 = Double.parseDouble(dpbbp_pc1_input.getText().toString());
                                    Double pc2 = Double.parseDouble(dpbbp_pc2_input.getText().toString());
                                    Double vc1 = Double.parseDouble(dpbbp_vc1_input.getText().toString());
                                    Double vc2 = Double.parseDouble(dpbbp_vc2_input.getText().toString());
                                    Double zc1 = Double.parseDouble(dpbbp_tc1_input.getText().toString());
                                    Double zc2 = Double.parseDouble(dpbbp_zc2_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double gamma1new = 1.0, gamma1old = 0.0;
                                    Double gamma2new = 1.0, gamma2old = 0.0;
                                    Double phi1 = 1.0, phi2 = 1.0;
                                    Double phisat1 = 1.0, phisat2 = 1.0;
                                    Double pcr1 = 1.0, pcr2 = 1.0;
                                    Double p1sat = Math.exp(a1-b1/(c1+t));
                                    Double p2sat = Math.exp(a2-b2/(c2+t));
                                    Double pnew = 1.0/((phi1*y1)/(gamma1new*p1sat*phisat1*pcr1)+(phi2*y2)/(gamma2new*p2sat*phisat2*pcr2));
                                    Double pold = pnew/2.0;
                                    Double x1 = 1.0, x2 = 1.0, x;
                                    Double tol = 0.000000001;
                                    Double r = 8.3144598; //gas constant
                                    Double w12, tc12, zc12, vc12, pc12,
                                            tr11, tr22, tr12, pr11, pr22, pr12,
                                            b011, b111, b022, b122, b012, b112,
                                            bhat11, bhat22, bhat12, b11, b22, b12, delta12,
                                            vsat11, vsat22;
                                    int iter = 0, iter1 = 0, iter0;
                                    while (Math.abs(pnew-pold)/pold>tol & iter1<1000) {
                                        //System values in deg C and kPa, critical values are in K and bar
                                        pold = pnew;
                                        iter0 = 0;
                                        while (Math.abs(gamma1new-gamma1old) > tol & Math.abs(gamma2new-gamma2old) > tol & iter0 < 1000) {
                                            gamma1old = gamma1new;
                                            gamma2old = gamma2new;
                                            w12 = 0.5*(w1+w2);
                                            tc12 = Math.sqrt(tc1 * tc2);
                                            zc12 = 0.5*(zc1+zc2);
                                            vc12 = Math.pow(0.5 * (Math.pow(vc1, 1.0 / 3.0) + Math.pow(vc2, 1.0 / 3.0)), 3.0);
                                            pc12 = zc12*r*tc12/vc12;
                                            tr11 = (t+273.15)/tc1;
                                            tr22 = (t+273.15)/tc2;
                                            tr12 = (t+273.15)/tc12;
                                            pr11 = (pnew/100.0)/pc1;
                                            pr22 = (pnew/100.0)/pc2;
                                            pr12 = (pnew/100.0)/pc12;
                                            b011 = 0.083-0.422/Math.pow(tr11,1.6);
                                            b111 = 0.139-0.172/Math.pow(tr11,4.2);
                                            b022 = 0.083-0.422/Math.pow(tr22,1.6);
                                            b122 = 0.139-0.172/Math.pow(tr22,4.2);
                                            b012 = 0.083-0.422/Math.pow(tr12,1.6);
                                            b112 = 0.139-0.172/Math.pow(tr12,4.2);
                                            bhat11 = b011+w1*b111;
                                            bhat22 = b022+w2*b122;
                                            bhat12 = b012+w12*b112;
                                            b11 = bhat11*r*tc1/pc1;
                                            b22 = bhat22*r*tc2/pc2;
                                            b12 = bhat12*r*tc12/pc12;
                                            delta12 = 2.0*b12-b11-b22;
                                            phi1 = Math.exp((pnew/100.0)*(b11+Math.pow(y2,2.0)*delta12)/(r*(t+273.15)));
                                            phi2 = Math.exp((pnew/100.0)*(b22+Math.pow(y1,2.0)*delta12)/(r*(t+273.15)));
                                            p1sat = Math.exp(a1-b1/(c1+t));
                                            p2sat = Math.exp(a2-b2/(c2+t));
                                            phisat1 = Math.exp(bhat11*((p1sat/100)/pc1)/tr11);
                                            phisat2 = Math.exp(bhat22*((p2sat/100)/pc2)/tr22);
                                            vsat11 = vc1*Math.pow(Math.pow(zc1,1.0-tr11),2.0/7.0);
                                            vsat22 = vc2*Math.pow(Math.pow(zc2,1.0-tr22),2.0/7.0);
                                            pcr1 = Math.exp((vsat11*0.000001)*((pnew-p1sat)*1000.0)/(r*(t+273.15)));
                                            pcr2 = Math.exp((vsat22*0.000001)*((pnew-p2sat)*1000.0)/(r*(t+273.15)));
                                            x1 = (y1*phi1*pnew)/(gamma1new*p1sat*phisat1*pcr1);
                                            x2 = (y2*phi2*pnew)/(gamma2new*p2sat*phisat2*pcr2);
                                            x = x1+x2; x1 = x1/x; x2 = x2/x;
                                            gamma1new = Math.exp(Math.pow(x2,2.0)*(A12+2.0*(A21-A12-C)*x1+3.0*C*Math.pow(x1,2.0)));
                                            gamma2new = Math.exp(Math.pow(x1,2.0)*(A21+2.0*(A12-A21-C)*x2+3.0*C*Math.pow(x2,2.0)));
                                            iter0 = iter0 + 1;
                                            iter = iter + 1;
                                        }
                                        pnew = 1.0/((phi1*y1)/(gamma1new*p1sat*phisat1*pcr1)+(phi2*y2)/(gamma2new*p2sat*phisat2*pcr2));
                                        iter1 = iter1 + 1;
                                    }
                                    if (Math.abs(y1+y2-1.0)>1e-3 || y1<0.0 || y2<0.0) {
                                        Toast.makeText(DpBbp.this, "Vapour phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                    if (Double.isNaN(x1) || Double.isNaN(x2) ||
                                            Math.abs(x1+x2-1.0)>1e-3 ||
                                            x1<0.0 || x2<0.0) {
                                        Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    dpbbp_p_input.setText(pnew.toString());
                                    dpbbp_x1_input.setText(x1.toString());
                                    dpbbp_x2_input.setText(x2.toString());
                                    dpbbp_gamma1_input.setText(gamma1new.toString());
                                    dpbbp_gamma2_input.setText(gamma2new.toString());
                                    dpbbp_phi1_input.setText(phi1.toString());
                                    dpbbp_phi2_input.setText(phi2.toString());
                                    dpbbp_phisat1_input.setText(phisat1.toString());
                                    dpbbp_phisat2_input.setText(phisat2.toString());
                                    dpbbp_pcr1_input.setText(phisat1.toString());
                                    dpbbp_pcr2_input.setText(phisat2.toString());
                                    spinner_dpbbp_system.setEnabled(true);
                                    spinner_dpbbp_components.setEnabled(true);
                                    spinner_dpbbp_mode.setEnabled(true);
                                    dpbbp_nignid_solve.setVisibility(View.GONE);
                                    dpbbp_display.setVisibility(View.VISIBLE);
                                    dpbbp_reset.setVisibility(View.GONE);
                                }
                                break;
                        }
                        break;
                    case 5:
                        //NIGNID > Flash calculations
                        switch (spinner_dpbbp_components.getSelectedItemPosition()) {
                            case 1:
                                //NIGNID > Flash calculations > 2 components
                                if (dpbbp_t_input.getText().length() == 0 | dpbbp_p_input.getText().length() == 0 |
                                        dpbbp_z1_input.getText().length() == 0 | dpbbp_z2_input.getText().length() == 0 |
                                        dpbbp_a1_input.getText().length() == 0 | dpbbp_a2_input.getText().length() == 0 |
                                        dpbbp_b1_input.getText().length() == 0 | dpbbp_b2_input.getText().length() == 0 |
                                        dpbbp_c1_input.getText().length() == 0 | dpbbp_c2_input.getText().length() == 0 |
                                        dpbbp_w1_input.getText().length() == 0 | dpbbp_w2_input.getText().length() == 0 |
                                        dpbbp_tc1_input.getText().length() == 0 | dpbbp_tc2_input.getText().length() == 0 |
                                        dpbbp_pc1_input.getText().length() == 0 | dpbbp_pc2_input.getText().length() == 0 |
                                        dpbbp_vc1_input.getText().length() == 0 | dpbbp_vc2_input.getText().length() == 0 |
                                        dpbbp_zc1_input.getText().length() == 0 | dpbbp_zc2_input.getText().length() == 0) {
                                    Toast.makeText(DpBbp.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (dpbbp_C_input.getText().length() == 0) {
                                        dpbbp_C_input.setText("0.0");
                                    }
                                    if (dpbbp_A12_input.getText().length() > 0 & dpbbp_A21_input.getText().length() == 0) {
                                        dpbbp_A21_input.setText(dpbbp_A12_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() > 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText(dpbbp_A21_input.getText());
                                    }
                                    if (dpbbp_A21_input.getText().length() == 0 & dpbbp_A12_input.getText().length() == 0) {
                                        dpbbp_A12_input.setText("0.0");
                                        dpbbp_A21_input.setText("0.0");
                                    }
                                    Double t = Double.parseDouble(dpbbp_t_input.getText().toString());
                                    Double p = Double.parseDouble(dpbbp_p_input.getText().toString());
                                    Double z1 = Double.parseDouble(dpbbp_z1_input.getText().toString());
                                    Double z2 = Double.parseDouble(dpbbp_z2_input.getText().toString());
                                    Double a1 = Double.parseDouble(dpbbp_a1_input.getText().toString());
                                    Double a2 = Double.parseDouble(dpbbp_a2_input.getText().toString());
                                    Double b1 = Double.parseDouble(dpbbp_b1_input.getText().toString());
                                    Double b2 = Double.parseDouble(dpbbp_b2_input.getText().toString());
                                    Double c1 = Double.parseDouble(dpbbp_c1_input.getText().toString());
                                    Double c2 = Double.parseDouble(dpbbp_c2_input.getText().toString());
                                    Double w1 = Double.parseDouble(dpbbp_w1_input.getText().toString());
                                    Double w2 = Double.parseDouble(dpbbp_w2_input.getText().toString());
                                    Double tc1 = Double.parseDouble(dpbbp_tc1_input.getText().toString());
                                    Double tc2 = Double.parseDouble(dpbbp_tc2_input.getText().toString());
                                    Double pc1 = Double.parseDouble(dpbbp_pc1_input.getText().toString());
                                    Double pc2 = Double.parseDouble(dpbbp_pc2_input.getText().toString());
                                    Double vc1 = Double.parseDouble(dpbbp_vc1_input.getText().toString());
                                    Double vc2 = Double.parseDouble(dpbbp_vc2_input.getText().toString());
                                    Double zc1 = Double.parseDouble(dpbbp_tc1_input.getText().toString());
                                    Double zc2 = Double.parseDouble(dpbbp_zc2_input.getText().toString());
                                    Double A12 = Double.parseDouble(dpbbp_A12_input.getText().toString());
                                    Double A21 = Double.parseDouble(dpbbp_A21_input.getText().toString());
                                    Double C = Double.parseDouble(dpbbp_C_input.getText().toString());
                                    Double phi1 = 1.0, phi2 = 1.0;
                                    Double phisat1 = 1.0, phisat2 = 1.0;
                                    Double pcr1 = 1.0, pcr2 = 1.0;
                                    Double p1sat = Math.exp(a1 - b1 / (c1 + t));
                                    Double p2sat = Math.exp(a2 - b2 / (c2 + t));
                                    Double pbbp = z1 * p1sat + z2 * p2sat;
                                    Double pdp = 1.0 / (z1 / p1sat + z2 / p2sat);
                                    Double gamma1 = 1.0, gamma2 = 1.0;
                                    Double y1new = 1.0, y2new = 1.0, y1old = 0.0, y2old = 0.0;
                                    Double x1new = 1.0, x2new = 1.0, x1old = 0.0, x2old = 0.0;
                                    Double L;
                                    if (pdp <= p & p <= pbbp) {
                                        //Flash is possible
                                        Double V1 = 0.5, V0 = 0.0;
                                        Double fV0, dfV0;
                                        Double tol = 0.000000001;
                                        Double r = 8.3144598; //gas constant
                                        Double w12, tc12, zc12, vc12, pc12,
                                                tr11, tr22, tr12, pr11, pr22, pr12,
                                                b011, b111, b022, b122, b012, b112,
                                                bhat11, bhat22, bhat12, b11, b22, b12, delta12,
                                                vsat11, vsat22;
                                        int iter = 0;
                                        while (Math.abs(V1 - V0) > tol & iter < 1000 &
                                                Math.abs(y1new - y1old) > tol & Math.abs(y2new - y2old) > tol &
                                                Math.abs(x1new - x1old) > tol & Math.abs(x2new - x2old) > tol) {
                                            V0 = V1;
                                            y1old = y1new;
                                            y2old = y2new;
                                            x1old = x1new;
                                            x2old = x2new;
                                            w12 = 0.5*(w1+w2);
                                            tc12 = Math.sqrt(tc1 * tc2);
                                            zc12 = 0.5*(zc1+zc2);
                                            vc12 = Math.pow(0.5 * (Math.pow(vc1, 1.0 / 3.0) + Math.pow(vc2, 1.0 / 3.0)), 3.0);
                                            pc12 = zc12*r*tc12/vc12;
                                            tr11 = (t+273.15)/tc1;
                                            tr22 = (t+273.15)/tc2;
                                            tr12 = (t+273.15)/tc12;
                                            pr11 = (p/100.0)/pc1;
                                            pr22 = (p/100.0)/pc2;
                                            pr12 = (p/100.0)/pc12;
                                            b011 = 0.083-0.422/Math.pow(tr11,1.6);
                                            b111 = 0.139-0.172/Math.pow(tr11,4.2);
                                            b022 = 0.083-0.422/Math.pow(tr22,1.6);
                                            b122 = 0.139-0.172/Math.pow(tr22,4.2);
                                            b012 = 0.083-0.422/Math.pow(tr12,1.6);
                                            b112 = 0.139-0.172/Math.pow(tr12,4.2);
                                            bhat11 = b011+w1*b111;
                                            bhat22 = b022+w2*b122;
                                            bhat12 = b012+w12*b112;
                                            b11 = bhat11*r*tc1/pc1;
                                            b22 = bhat22*r*tc2/pc2;
                                            b12 = bhat12*r*tc12/pc12;
                                            delta12 = 2.0*b12-b11-b22;
                                            phi1 = Math.exp((p/100.0)*(b11+Math.pow(y2new,2.0)*delta12)/(r*(t+273.15)));
                                            phi2 = Math.exp((p/100.0)*(b22+Math.pow(y1new,2.0)*delta12)/(r*(t+273.15)));
                                            p1sat = Math.exp(a1-b1/(c1+t));
                                            p2sat = Math.exp(a2-b2/(c2+t));
                                            phisat1 = Math.exp(bhat11*((p1sat/100)/pc1)/tr11);
                                            phisat2 = Math.exp(bhat22*((p2sat/100)/pc2)/tr22);
                                            vsat11 = vc1*Math.pow(Math.pow(zc1,1.0-tr11),2.0/7.0);
                                            vsat22 = vc2*Math.pow(Math.pow(zc2,1.0-tr22),2.0/7.0);
                                            pcr1 = Math.exp((vsat11*0.000001)*((p-p1sat)*1000.0)/(r*(t+273.15)));
                                            pcr2 = Math.exp((vsat22*0.000001)*((p-p2sat)*1000.0)/(r*(t+273.15)));
                                            fV0 = z1/((p*phi1/(gamma1*p1sat*phisat1*pcr1))*(1.0-V0)+V0)
                                                    +z2/((p*phi2/(gamma2*p2sat*phisat2*pcr2))*(1.0-V0)+V0)-1.0;
                                            dfV0 = z1/Math.pow(((p*phi1/(gamma1*p1sat*phisat1*pcr1))*(1.0-V0)+V0),2.0)*((p*phi1/(gamma1*p1sat*phisat1*pcr1))-1.0)
                                                    +z2/Math.pow(((p*phi2/(gamma2*p2sat*phisat2*pcr2))*(1.0-V0)+V0),2.0)*((p*phi2/(gamma2*p2sat*phisat2*pcr2))-1.0);
                                            V1 = V0 - fV0 / dfV0;
                                            y1new = z1/((p*phi1/(gamma1*p1sat*phisat1*pcr1))*(1.0-V1)+V1);
                                            y2new = z2/((p*phi2/(gamma2*p2sat*phisat2*pcr2))*(1.0-V1)+V1);
                                            L = 1.0 - V1;
                                            x1new = (z1 - y1new * V1) / L;
                                            x2new = (z2 - y2new * V1) / L;
                                            gamma1 = Math.exp(Math.pow(x2new, 2.0) * (A12 + 2.0 * (A21 - A12 - C) * x1new + 3.0 * C * Math.pow(x1new, 2.0)));
                                            gamma2 = Math.exp(Math.pow(x1new, 2.0) * (A21 + 2.0 * (A12 - A21 - C) * x2new + 3.0 * C * Math.pow(x2new, 2.0)));
                                            iter = iter + 1;
                                        }
                                        if (Math.abs(z1+z2-1.0)>1e-3 || z1<0.0 || z2<0.0) {
                                            Toast.makeText(DpBbp.this, "Overall phase composition must be positive and sum to unity!", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        if (Double.isNaN(x1new) || Double.isNaN(x2new) ||
                                                Double.isNaN(y1new) || Double.isNaN(y2new) ||
                                                Math.abs(x1new+x2new-1.0)>1e-3 || Math.abs(y1new+y2new-1.0)>1e-3 ||
                                                x1new<0.0 || x2new<0.0 || y1new<0.0 || y2new<0.0) {
                                            Toast.makeText(DpBbp.this, "Solver unable to find appropriate solution!", Toast.LENGTH_SHORT).show();
                                        }
                                        dpbbp_x1_input.setText(x1new.toString());
                                        dpbbp_x2_input.setText(x2new.toString());
                                        dpbbp_y1_input.setText(y1new.toString());
                                        dpbbp_y2_input.setText(y2new.toString());
                                        dpbbp_gamma1_input.setText(gamma1.toString());
                                        dpbbp_gamma2_input.setText(gamma2.toString());
                                        dpbbp_phi1_input.setText(phi1.toString());
                                        dpbbp_phi2_input.setText(phi2.toString());
                                        dpbbp_phisat1_input.setText(phisat1.toString());
                                        dpbbp_phisat2_input.setText(phisat2.toString());
                                        dpbbp_pcr1_input.setText(phisat1.toString());
                                        dpbbp_pcr2_input.setText(phisat2.toString());
                                        spinner_dpbbp_system.setEnabled(true);
                                        spinner_dpbbp_components.setEnabled(true);
                                        spinner_dpbbp_mode.setEnabled(true);
                                        dpbbp_nignid_solve.setVisibility(View.GONE);
                                        dpbbp_display.setVisibility(View.VISIBLE);
                                        dpbbp_reset.setVisibility(View.GONE);
                                        Toast.makeText(DpBbp.this, "Iterative calculation stopped at iteration " + Integer.toString(iter), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(DpBbp.this, "Flash process is not possible at given conditions! At T = "+Double.toString(t)+" K, P should be between "+Double.toString(pdp)+" kPa and "+Double.toString(pbbp)+" kPa!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                break;
                        }
                        break;
                    case 0:
                        //NIGNID > Mode not selected
                        Toast.makeText(DpBbp.this, "Please select calculation mode!", Toast.LENGTH_SHORT).show();
                        spinner_dpbbp_system.setEnabled(true);
                        spinner_dpbbp_components.setEnabled(true);
                        spinner_dpbbp_mode.setEnabled(true);
                        dpbbp_nignid_solve.setVisibility(View.GONE);
                        dpbbp_display.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        TextView dpbbp_t = (TextView) findViewById(R.id.dpbbp_t);
        dpbbp_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Temperature T (in \u00B0C)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_p = (TextView) findViewById(R.id.dpbbp_p);
        dpbbp_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Pressure P (in kPa)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_x = (TextView) findViewById(R.id.dpbbp_x);
        dpbbp_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Liquid phase composition", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_y = (TextView) findViewById(R.id.dpbbp_y);
        dpbbp_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Vapour phase composition", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_z = (TextView) findViewById(R.id.dpbbp_z);
        dpbbp_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Overall (liquid+vapour) composition", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_a = (TextView) findViewById(R.id.dpbbp_a);
        dpbbp_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Antoine parameter a (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_b = (TextView) findViewById(R.id.dpbbp_b);
        dpbbp_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Antoine parameter b (follows units of T)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_c = (TextView) findViewById(R.id.dpbbp_c);
        dpbbp_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Antoine parameter c (follows units of T)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_gamma = (TextView) findViewById(R.id.dpbbp_gamma);
        dpbbp_gamma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Activity coefficient \u03B3", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_w = (TextView) findViewById(R.id.dpbbp_w);
        dpbbp_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Acentric factor \u03C9 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView dpbbp_tc = (TextView) findViewById(R.id.dpbbp_tc);
        dpbbp_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Critical temperature Tc (in K)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView dpbbp_pc = (TextView) findViewById(R.id.dpbbp_pc);
        dpbbp_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Critical pressure Pc (in bar)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_vc = (TextView) findViewById(R.id.dpbbp_vc);
        dpbbp_vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Critical molar volume Vc (in cm\u00B3 mol\u207B\u00B9)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_zc = (TextView) findViewById(R.id.dpbbp_zc);
        dpbbp_zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Critical compressibility factor Zc (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_phisat = (TextView) findViewById(R.id.dpbbp_phisat);
        dpbbp_phisat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Fugacity coefficient at P\u209B\u2090\u209C at T (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_pcr = (TextView) findViewById(R.id.dpbbp_pcr);
        dpbbp_pcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Poynting correction factor PCR (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_phi = (TextView) findViewById(R.id.dpbbp_phi);
        dpbbp_phi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Partial fugacity coefficient ^\u03D5\u1D62 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_A12 = (TextView) findViewById(R.id.dpbbp_A12);
        dpbbp_A12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Margules activity model 1st/2nd parameter A\u2081\u2082 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_A21 = (TextView) findViewById(R.id.dpbbp_A21);
        dpbbp_A21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Margules activity model 1st/2nd parameter A\u2082\u2081 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_A13 = (TextView) findViewById(R.id.dpbbp_A13);
        dpbbp_A13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Margules activity model 1st/2nd parameter A\u2081\u2083 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_A31 = (TextView) findViewById(R.id.dpbbp_A31);
        dpbbp_A13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Margules activity model 1st/2nd parameter A\u2083\u2081 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_A23 = (TextView) findViewById(R.id.dpbbp_A23);
        dpbbp_A23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Margules activity model 1st/2nd parameter A\u2082\u2083 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_A32 = (TextView) findViewById(R.id.dpbbp_A32);
        dpbbp_A32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Margules activity model 1st/2nd parameter A\u2083\u2082 (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dpbbp_C = (TextView) findViewById(R.id.dpbbp_C);
        dpbbp_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DpBbp.this, "Margules activity model 3rd parameter C (dimensionless)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}