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

public class Steam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thermo_steam);

        final List<String[]> satT = new ArrayList<>();
        final List<String[]> satP = new ArrayList<>();
        final List<String[]> steamU = new ArrayList<>();
        final List<String[]> steamH = new ArrayList<>();
        final List<String[]> steamS = new ArrayList<>();
        final List<String[]> steamD = new ArrayList<>();

        Runnable sat_runnable = new Runnable() {
            @Override
            public void run() {
                //saturated steam by temperature
                String[] satT_row;
                try {
                    InputStream satT_inputStream = getAssets().open("SaturatedSteam_T.csv");
                    BufferedReader satT_buffrd = new BufferedReader(new InputStreamReader(satT_inputStream));
                    String satT_line = "";
                    while((satT_line = satT_buffrd.readLine()) != null){
                        satT_row = satT_line.split(",");
                        satT.add(satT_row);
                    }
                    satT_buffrd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //saturated steam by pressure
                String[] satP_row;
                try {
                    InputStream satP_inputStream = getAssets().open("SaturatedSteam_P.csv");
                    BufferedReader satP_buffrd = new BufferedReader(new InputStreamReader(satP_inputStream));
                    String satP_line = "";
                    while((satP_line = satP_buffrd.readLine()) != null){
                        satP_row = satP_line.split(",");
                        satP.add(satP_row);
                    }
                    satP_buffrd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(sat_runnable).start();

        Runnable superheat_runnable = new Runnable() {
            @Override
            public void run() {
                //superheated steam by internal energy
                String[] steamU_row;
                try {
                    InputStream steamU_inputStream = getAssets().open("Steam_U.csv");
                    BufferedReader steamU_buffrd = new BufferedReader(new InputStreamReader(steamU_inputStream));
                    String steamU_line = "";
                    while((steamU_line = steamU_buffrd.readLine()) != null){
                        steamU_row = steamU_line.split(",");
                        steamU.add(steamU_row);
                    }
                    steamU_buffrd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //superheated steam by enthalpy
                String[] steamH_row;
                try {
                    InputStream steamH_inputStream = getAssets().open("Steam_H.csv");
                    BufferedReader steamH_buffrd = new BufferedReader(new InputStreamReader(steamH_inputStream));
                    String steamH_line = "";
                    while((steamH_line = steamH_buffrd.readLine()) != null){
                        steamH_row = steamH_line.split(",");
                        steamH.add(steamH_row);
                    }
                    steamH_buffrd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //superheated steam by entropy
                String[] steamS_row;
                try {
                    InputStream steamS_inputStream = getAssets().open("Steam_S.csv");
                    BufferedReader steamS_buffrd = new BufferedReader(new InputStreamReader(steamS_inputStream));
                    String steamS_line = "";
                    while((steamS_line = steamS_buffrd.readLine()) != null){
                        steamS_row = steamS_line.split(",");
                        steamS.add(steamS_row);
                    }
                    steamS_buffrd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //superheated steam by density
                String[] steamD_row;
                try {
                    InputStream steamD_inputStream = getAssets().open("Steam_D.csv");
                    BufferedReader steamD_buffrd = new BufferedReader(new InputStreamReader(steamD_inputStream));
                    String steamD_line = "";
                    while((steamD_line = steamD_buffrd.readLine()) != null){
                        steamD_row = steamD_line.split(",");
                        steamD.add(steamD_row);
                    }
                    steamD_buffrd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(superheat_runnable).start();

        Spinner spinner_steam = (Spinner) findViewById(R.id.steam_system_input);
        ArrayAdapter<CharSequence> adapter_steam = ArrayAdapter.createFromResource(this,
                R.array.steam_system, android.R.layout.simple_spinner_item);
        adapter_steam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_steam.setAdapter(adapter_steam);
        spinner_steam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Spinner spinner_steam = (Spinner) findViewById(R.id.steam_system_input);
            TextView steam_x_input = (TextView) findViewById(R.id.steam_x_input);
            TextView steam_t_input = (TextView) findViewById(R.id.steam_t_input);
            TextView steam_p_input = (TextView) findViewById(R.id.steam_p_input);
            TextView steam_u_input = (TextView) findViewById(R.id.steam_u_input);
            TextView steam_h_input = (TextView) findViewById(R.id.steam_h_input);
            TextView steam_s_input = (TextView) findViewById(R.id.steam_s_input);
            TextView steam_v_input = (TextView) findViewById(R.id.steam_v_input);

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (spinner_steam.getSelectedItemPosition()) {
                    case 0:
                        steam_x_input.setEnabled(false);
                        steam_t_input.setEnabled(false);
                        steam_p_input.setEnabled(false);
                        steam_u_input.setEnabled(false);
                        steam_h_input.setEnabled(false);
                        steam_s_input.setEnabled(false);
                        steam_v_input.setEnabled(false);
                        break;
                    case 1:
                        steam_x_input.setEnabled(true);
                        steam_t_input.setEnabled(true);
                        steam_p_input.setEnabled(true);
                        steam_u_input.setEnabled(false);
                        steam_h_input.setEnabled(false);
                        steam_s_input.setEnabled(false);
                        steam_v_input.setEnabled(false);
                        break;
                    case 2:
                        steam_x_input.setEnabled(false);
                        steam_t_input.setEnabled(true);
                        steam_p_input.setEnabled(true);
                        steam_u_input.setEnabled(false);
                        steam_h_input.setEnabled(false);
                        steam_s_input.setEnabled(false);
                        steam_v_input.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                steam_x_input.setEnabled(false);
                steam_t_input.setEnabled(false);
                steam_p_input.setEnabled(false);
                steam_u_input.setEnabled(false);
                steam_h_input.setEnabled(false);
                steam_s_input.setEnabled(false);
                steam_v_input.setEnabled(false);
            }
        });

        Button steam_solve = (Button) findViewById(R.id.steam_solve);
        steam_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner_steam = (Spinner) findViewById(R.id.steam_system_input);
                TextView steam_x_input = (TextView) findViewById(R.id.steam_x_input);
                TextView steam_t_input = (TextView) findViewById(R.id.steam_t_input);
                TextView steam_p_input = (TextView) findViewById(R.id.steam_p_input);
                TextView steam_u_input = (TextView) findViewById(R.id.steam_u_input);
                TextView steam_h_input = (TextView) findViewById(R.id.steam_h_input);
                TextView steam_s_input = (TextView) findViewById(R.id.steam_s_input);
                TextView steam_v_input = (TextView) findViewById(R.id.steam_v_input);

                switch (spinner_steam.getSelectedItemPosition()) {
                    case 0:
                        Toast.makeText(Steam.this, "Please select system type!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //Via x & T
                        if (steam_x_input.getText().length() > 0 &
                                steam_t_input.getText().length() > 0 &
                                steam_p_input.getText().length() == 0) {
                            Double x = Double.parseDouble(steam_x_input.getText().toString());
                            Double T = Double.parseDouble(steam_t_input.getText().toString());
                            if (T>=0.01 & T<=373.946) { //check if T within range
                                int row=1;
                                while (Double.parseDouble(satT.get(row)[0])<T & row<satT.size()) {
                                    row++;
                                }
                                Double T1 = Double.parseDouble(satT.get(row-1)[0]);
                                Double T2 = Double.parseDouble(satT.get(row)[0]);
                                Double P1 = Double.parseDouble(satT.get(row-1)[1]);
                                Double P2 = Double.parseDouble(satT.get(row)[1]);
                                Double VL1 = Double.parseDouble(satT.get(row-1)[2]);
                                Double VL2 = Double.parseDouble(satT.get(row)[2]);
                                Double VV1 = Double.parseDouble(satT.get(row-1)[3]);
                                Double VV2 = Double.parseDouble(satT.get(row)[3]);
                                Double UL1 = Double.parseDouble(satT.get(row-1)[4]);
                                Double UL2 = Double.parseDouble(satT.get(row)[4]);
                                Double UV1 = Double.parseDouble(satT.get(row-1)[5]);
                                Double UV2 = Double.parseDouble(satT.get(row)[5]);
                                Double HL1 = Double.parseDouble(satT.get(row-1)[6]);
                                Double HL2 = Double.parseDouble(satT.get(row)[6]);
                                Double HV1 = Double.parseDouble(satT.get(row-1)[7]);
                                Double HV2 = Double.parseDouble(satT.get(row)[7]);
                                Double SL1 = Double.parseDouble(satT.get(row-1)[8]);
                                Double SL2 = Double.parseDouble(satT.get(row)[8]);
                                Double SV1 = Double.parseDouble(satT.get(row-1)[9]);
                                Double SV2 = Double.parseDouble(satT.get(row)[9]);
                                Double P = (P2-P1)/(T2-T1)*(T-T1)+P1;
                                Double VL = (VL2-VL1)/(T2-T1)*(T-T1)+VL1;
                                Double VV = (VV2-VV1)/(T2-T1)*(T-T1)+VV1;
                                Double UL = (UL2-UL1)/(T2-T1)*(T-T1)+UL1;
                                Double UV = (UV2-UV1)/(T2-T1)*(T-T1)+UV1;
                                Double HL = (HL2-HL1)/(T2-T1)*(T-T1)+HL1;
                                Double HV = (HV2-HV1)/(T2-T1)*(T-T1)+HV1;
                                Double SL = (SL2-SL1)/(T2-T1)*(T-T1)+SL1;
                                Double SV = (SV2-SV1)/(T2-T1)*(T-T1)+SV1;
                                Double V = x*VV+(1.0-x)*VL;
                                Double U = x*UV+(1.0-x)*UL;
                                Double H = x*HV+(1.0-x)*HL;
                                Double S = x*SV+(1.0-x)*SL;
                                steam_p_input.setText(P.toString());
                                steam_u_input.setText(U.toString());
                                steam_h_input.setText(H.toString());
                                steam_s_input.setText(S.toString());
                                steam_v_input.setText(V.toString());
                            }
                            else {
                                Toast.makeText(Steam.this, "T must be between 0.01\u00B0C and 373.946\u00B0C!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        //Via x & P
                        else if (steam_x_input.getText().length() > 0 &
                                steam_t_input.getText().length() == 0 &
                                steam_p_input.getText().length() > 0) {
                            Double x = Double.parseDouble(steam_x_input.getText().toString());
                            Double P = Double.parseDouble(steam_p_input.getText().toString());
                            if (P>=0.611657 & P<=22064) { //check if P within range
                                int row=1;
                                while (Double.parseDouble(satP.get(row)[0])<P & row<satP.size()) {
                                    row++;
                                }
                                Double T1 = Double.parseDouble(satP.get(row-1)[1]);
                                Double T2 = Double.parseDouble(satP.get(row)[1]);
                                Double P1 = Double.parseDouble(satP.get(row-1)[0]);
                                Double P2 = Double.parseDouble(satP.get(row)[0]);
                                Double VL1 = Double.parseDouble(satP.get(row-1)[2]);
                                Double VL2 = Double.parseDouble(satP.get(row)[2]);
                                Double VV1 = Double.parseDouble(satP.get(row-1)[3]);
                                Double VV2 = Double.parseDouble(satP.get(row)[3]);
                                Double UL1 = Double.parseDouble(satP.get(row-1)[4]);
                                Double UL2 = Double.parseDouble(satP.get(row)[4]);
                                Double UV1 = Double.parseDouble(satP.get(row-1)[5]);
                                Double UV2 = Double.parseDouble(satP.get(row)[5]);
                                Double HL1 = Double.parseDouble(satP.get(row-1)[6]);
                                Double HL2 = Double.parseDouble(satP.get(row)[6]);
                                Double HV1 = Double.parseDouble(satP.get(row-1)[7]);
                                Double HV2 = Double.parseDouble(satP.get(row)[7]);
                                Double SL1 = Double.parseDouble(satP.get(row-1)[8]);
                                Double SL2 = Double.parseDouble(satP.get(row)[8]);
                                Double SV1 = Double.parseDouble(satP.get(row-1)[9]);
                                Double SV2 = Double.parseDouble(satP.get(row)[9]);
                                Double T = (T2-T1)/(P2-P1)*(P-P1)+T1;
                                Double VL = (VL2-VL1)/(P2-P1)*(P-P1)+VL1;
                                Double VV = (VV2-VV1)/(P2-P1)*(P-P1)+VV1;
                                Double UL = (UL2-UL1)/(P2-P1)*(P-P1)+UL1;
                                Double UV = (UV2-UV1)/(P2-P1)*(P-P1)+UV1;
                                Double HL = (HL2-HL1)/(P2-P1)*(P-P1)+HL1;
                                Double HV = (HV2-HV1)/(P2-P1)*(P-P1)+HV1;
                                Double SL = (SL2-SL1)/(P2-P1)*(P-P1)+SL1;
                                Double SV = (SV2-SV1)/(P2-P1)*(P-P1)+SV1;
                                Double V = x*VV+(1.0-x)*VL;
                                Double U = x*UV+(1.0-x)*UL;
                                Double H = x*HV+(1.0-x)*HL;
                                Double S = x*SV+(1.0-x)*SL;
                                steam_t_input.setText(T.toString());
                                steam_u_input.setText(U.toString());
                                steam_h_input.setText(H.toString());
                                steam_s_input.setText(S.toString());
                                steam_v_input.setText(V.toString());
                            } else {
                                Toast.makeText(Steam.this, "P must be between 0.611657 kPa and 22064 kPa", Toast.LENGTH_SHORT).show();
                            }
                        } else if (steam_x_input.getText().length() > 0 &
                                steam_t_input.getText().length() > 0 &
                                steam_p_input.getText().length() > 0) {
                            Toast.makeText(Steam.this, "Over-determined system!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Steam.this, "Under-determined system!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (steam_t_input.getText().length() > 0 &
                                steam_p_input.getText().length() > 0) {
                            Double T = Double.parseDouble(steam_t_input.getText().toString());
                            Double P = Double.parseDouble(steam_p_input.getText().toString());
                            if (P>=0.611657 & P<=22064 & T>=50 & T<=650) { //check if P & T within range
                                //locate element indices
                                int row=1;
                                while (Double.parseDouble(steamU.get(row)[0])<P & row<steamU.size()) {
                                    row++;
                                }
                                int col=1;
                                while (Double.parseDouble(steamU.get(0)[col])<T & col<steamU.get(0).length) {
                                    col++;
                                }
                                Double P1 = Double.parseDouble(steamU.get(row-1)[0]);
                                Double P2 = Double.parseDouble(steamU.get(row)[0]);
                                Double T1 = Double.parseDouble(steamU.get(0)[col-1]);
                                Double T2 = Double.parseDouble(steamU.get(0)[col]);
                                Double U11 = Double.parseDouble(steamU.get(row-1)[col-1]);
                                Double U12 = Double.parseDouble(steamU.get(row-1)[col]);
                                Double U21 = Double.parseDouble(steamU.get(row)[col-1]);
                                Double U22 = Double.parseDouble(steamU.get(row)[col]);
                                if ((U11 < 2000.0 & U12 < 2000.0 & U21 < 2000.0 & U22 < 2000.0) |
                                        (U11 > 2000.0 & U12 > 2000.0 & U21 > 2000.0 & U22 > 2000.0)) {
                                    //If interpolation points not across saturation boundary
                                    if (U11 < 2000.0) {
                                        //subcooled liquid
                                        steam_x_input.setText("Subcooled liquid");
                                    }
                                    else {
                                        //superheated vapour
                                        steam_x_input.setText("Superheated vapour");
                                    }
                                    Double U = (P2-P)*(T2-T)/((P2-P1)*(T2-T1))*U11 +
                                            (P-P1)*(T2-T)/((P2-P1)*(T2-T1))*U12 +
                                            (P2-P)*(T-T1)/((P2-P1)*(T2-T1))*U21 +
                                            (P-P1)*(T-T1)/((P2-P1)*(T2-T1))*U22;
                                    Double H11 = Double.parseDouble(steamH.get(row-1)[col-1]);
                                    Double H12 = Double.parseDouble(steamH.get(row-1)[col]);
                                    Double H21 = Double.parseDouble(steamH.get(row)[col-1]);
                                    Double H22 = Double.parseDouble(steamH.get(row)[col]);
                                    Double H = (P2-P)*(T2-T)/((P2-P1)*(T2-T1))*H11 +
                                            (P-P1)*(T2-T)/((P2-P1)*(T2-T1))*H12 +
                                            (P2-P)*(T-T1)/((P2-P1)*(T2-T1))*H21 +
                                            (P-P1)*(T-T1)/((P2-P1)*(T2-T1))*H22;
                                    Double S11 = Double.parseDouble(steamS.get(row-1)[col-1]);
                                    Double S12 = Double.parseDouble(steamS.get(row-1)[col]);
                                    Double S21 = Double.parseDouble(steamS.get(row)[col-1]);
                                    Double S22 = Double.parseDouble(steamS.get(row)[col]);
                                    Double S = (P2-P)*(T2-T)/((P2-P1)*(T2-T1))*S11 +
                                            (P-P1)*(T2-T)/((P2-P1)*(T2-T1))*S12 +
                                            (P2-P)*(T-T1)/((P2-P1)*(T2-T1))*S21 +
                                            (P-P1)*(T-T1)/((P2-P1)*(T2-T1))*S22;
                                    Double D11 = Double.parseDouble(steamD.get(row-1)[col-1]);
                                    Double D12 = Double.parseDouble(steamD.get(row-1)[col]);
                                    Double D21 = Double.parseDouble(steamD.get(row)[col-1]);
                                    Double D22 = Double.parseDouble(steamD.get(row)[col]);
                                    Double D = (P2-P)*(T2-T)/((P2-P1)*(T2-T1))*D11 +
                                            (P-P1)*(T2-T)/((P2-P1)*(T2-T1))*D12 +
                                            (P2-P)*(T-T1)/((P2-P1)*(T2-T1))*D21 +
                                            (P-P1)*(T-T1)/((P2-P1)*(T2-T1))*D22;
                                    Double V = 1.0/D; //densities have a better linear fit compared to specific volumes
                                    steam_u_input.setText(U.toString());
                                    steam_h_input.setText(H.toString());
                                    steam_s_input.setText(S.toString());
                                    steam_v_input.setText(V.toString());
                                }
                                else {
                                    //If interpolation points cross saturation boundary
                                    //TODO accomodate linear interpolation near phase boundary
                                    if (U11<2000.0 & U12>2000.0 & U21<2000.0 & U22>2000.0) {
                                        ;
                                    } else if (U11<2000.0 & U12>2000.0 & U21<2000.0 & U22<2000.0) {
                                        ;
                                    } else if (U11>2000.0 & U12>2000.0 & U21<2000.0 & U22>2000.0) {
                                        ;
                                    } else if (U11>2000.0 & U12>2000.0 & U21<2000.0 & U22<2000.0) {
                                        ;
                                    }
                                    Toast.makeText(Steam.this, "T & P values too close to saturation data for interpolation to occur! Try rounding off T & P values!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Steam.this, "T must be between 50\u00B0C and 650\u00B0C!\nP must be between 0.611657 kPa and 22064 kPa!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Steam.this, "Under-determined system!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });

        TextView steam_x = (TextView) findViewById(R.id.steam_x);
        steam_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Steam.this, "Vapour quality (between 0 & 1)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView steam_t = (TextView) findViewById(R.id.steam_t);
        steam_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Steam.this, "Temperature (in \u00B0C)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView steam_p = (TextView) findViewById(R.id.steam_p);
        steam_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Steam.this, "Pressure (in kPa)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView steam_u = (TextView) findViewById(R.id.steam_u);
        steam_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Steam.this, "Specific internal energy (in kJ/kg)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView steam_h = (TextView) findViewById(R.id.steam_h);
        steam_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Steam.this, "Specific enthalpy (in kJ/kg)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView steam_s = (TextView) findViewById(R.id.steam_s);
        steam_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Steam.this, "Specific entropy (in kJ/(kg K))", Toast.LENGTH_SHORT).show();
            }
        });

        TextView steam_v = (TextView) findViewById(R.id.steam_v);
        steam_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Steam.this, "Specific volume (in m\u00B3/kg)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}