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

import org.apache.commons.math3.special.Erf;

public class Probit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oshe_probit);

        Spinner spinner_probit_injury = (Spinner) findViewById(R.id.probit_injury_input);
        ArrayAdapter<CharSequence> adapter_probit_injury = ArrayAdapter.createFromResource(this,
                R.array.probit_injury, android.R.layout.simple_spinner_item);
        adapter_probit_injury.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_probit_injury.setAdapter(adapter_probit_injury);
        spinner_probit_injury.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos > 0) {
                    TextView probit_k1_input = (TextView) findViewById(R.id.probit_k1_input);
                    TextView probit_k2_input = (TextView) findViewById(R.id.probit_k2_input);
                    TextView probit_desc = (TextView) findViewById(R.id.probit_desc);
                    Double k1 = 0.0, k2 = 0.0;
                    String var = "";
                    String desc = "Notes: Units of causative variable are dependent on user input, unless preset is chosen.";
                    String unit_te = "t\u2091 = effective time duration (s)",
                            unit_Ie = "I\u2091 = effective radiation intensity (W/m\u00b2)",
                            unit_t = "t = time duration of pool burning (s)",
                            unit_I = "I = radiation intensity from pool burning (W/m\u00b2)",
                            unit_p0 = "p\u2070 = peak overpressure (Pa)",
                            unit_J = "J = impulse (Pa s)",
                            unit_C = "C = concentration (ppm)",
                            unit_T = "T = time interval (min)";
                    switch (pos) {
                        case 1:
                            k1 = -14.9;
                            k2 = 2.56;
                            var = "t\u2091I\u2091^(4/3)/10\u2074";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_te + "\n" + unit_Ie);
                            break;
                        case 2:
                            k1 = -14.9;
                            k2 = 2.56;
                            var = "tI^(4/3)/10\u2074";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_t + "\n" + unit_I);
                            break;
                        case 3:
                            k1 = -77.1;
                            k2 = 6.91;
                            var = "p\u2070";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_p0);
                            break;
                        case 4:
                            k1 = -15.6;
                            k2 = 1.93;
                            var = "p\u2070";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_p0);
                            break;
                        case 5:
                            k1 = -46.1;
                            k2 = 4.82;
                            var = "J";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_J);
                            break;
                        case 6:
                            k1 = -39.1;
                            k2 = 4.45;
                            var = "J";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_J);
                            break;
                        case 7:
                            k1 = -27.1;
                            k2 = 4.26;
                            var = "J";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_J);
                            break;
                        case 8:
                            k1 = -23.8;
                            k2 = 2.92;
                            var = "p\u2070";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_p0);
                            break;
                        case 9:
                            k1 = -18.1;
                            k2 = 2.79;
                            var = "p\u2070";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_p0);
                            break;
                        case 10:
                            k1 = -35.9;
                            k2 = 1.85;
                            var = "\u03a3C\u00b2T";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_C + "\n" + unit_T);
                            break;
                        case 11:
                            k1 = -37.98;
                            k2 = 3.7;
                            var = "\u03a3CT";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_C + "\n" + unit_T);
                            break;
                        case 12:
                            k1 = -8.29;
                            k2 = 0.92;
                            var = "\u03a3C\u00b2T";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_C + "\n" + unit_T);
                            break;
                        case 13:
                            k1 = -6.19;
                            k2 = 1.0;
                            var = "\u03a3CT";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_C + "\n" + unit_T);
                            break;
                        case 14:
                            k1 = -16.85;
                            k2 = 2.0;
                            var = "\u03a3CT";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_C + "\n" + unit_T);
                            break;
                        case 15:
                            k1 = -13.79;
                            k2 = 1.4;
                            var = "\u03a3C\u00b2T";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_C + "\n" + unit_T);
                            break;
                        case 16:
                            k1 = -19.27;
                            k2 = 3.69;
                            var = "\u03a3CT";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_C + "\n" + unit_T);
                            break;
                        case 17:
                            k1 = -7.42;
                            k2 = 0.51;
                            var = "\u03a3C\u00b2T";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_C + "\n" + unit_T);
                            break;
                        case 18:
                            k1 = -15.67;
                            k2 = 1.0;
                            var = "\u03a3CT";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_C + "\n" + unit_T);
                            break;
                        case 19:
                            k1 = -6.79;
                            k2 = 0.41;
                            var = "\u03a3C^(2.5)T";
                            probit_desc.setText(desc + "\nV = " + var + "\n" + unit_C + "\n" + unit_T);
                            break;
                        default:
                            probit_desc.setText(desc);
                            break;
                    }
                    probit_k1_input.setText(k1.toString());
                    probit_k2_input.setText(k2.toString());
                } else {
                    TextView probit_k1_input = (TextView) findViewById(R.id.probit_k1_input);
                    TextView probit_k2_input = (TextView) findViewById(R.id.probit_k2_input);
                    TextView probit_desc = (TextView) findViewById(R.id.probit_desc);
                    probit_k1_input.setText("");
                    probit_k2_input.setText("");
                    probit_desc.setText("Notes: Units of causative variable are dependent on user input, unless preset is chosen.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });

        Button probit_solve = (Button) findViewById(R.id.probit_solve);
        probit_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView probit_k1_input = (TextView) findViewById(R.id.probit_k1_input);
                TextView probit_k2_input = (TextView) findViewById(R.id.probit_k2_input);
                TextView probit_V_input = (TextView) findViewById(R.id.probit_V_input);
                TextView probit_Y_input = (TextView) findViewById(R.id.probit_Y_input);
                TextView probit_P_input = (TextView) findViewById(R.id.probit_P_input);

                Double k1=0.0, k2=0.0, V=0.0, Y=0.0, P=0.0;
                int kunfilled = 0, Vunfilled = 0, YPunfilled = 0;
                if (probit_k1_input.getText().length() > 0) {
                    k1 = Double.parseDouble(probit_k1_input.getText().toString());
                } else {
                    kunfilled = kunfilled + 1;
                }
                if (probit_k2_input.getText().length() > 0) {
                    k2 = Double.parseDouble(probit_k2_input.getText().toString());
                } else {
                    kunfilled = kunfilled + 1;
                }
                if (probit_V_input.getText().length() > 0) {
                    V = Double.parseDouble(probit_V_input.getText().toString());
                } else {
                    Vunfilled = Vunfilled + 1;
                }
                if (probit_Y_input.getText().length() > 0) {
                    Y = Double.parseDouble(probit_Y_input.getText().toString());
                } else {
                    YPunfilled = YPunfilled + 1;
                }
                if (probit_P_input.getText().length() > 0) {
                    P = Double.parseDouble(probit_P_input.getText().toString());
                } else {
                    YPunfilled = YPunfilled + 1;
                }
                if (kunfilled > 0) {
                    Toast.makeText(Probit.this, "Please ensure k1 and k2 are filled!", Toast.LENGTH_SHORT).show();
                } else if (Vunfilled==0 && YPunfilled == 0) {
                    Toast.makeText(Probit.this, "Overdetermined system!", Toast.LENGTH_SHORT).show();
                } else if (Vunfilled==0 && YPunfilled == 1) {
                    Toast.makeText(Probit.this, "Overdetermined system!", Toast.LENGTH_SHORT).show();
                } else if (Vunfilled==1 && YPunfilled == 2) {
                    Toast.makeText(Probit.this, "Underdetermined system!", Toast.LENGTH_SHORT).show();
                } else if (Vunfilled==1 && YPunfilled == 0) {
                    Toast.makeText(Probit.this, "Please fill in only either P or Y!", Toast.LENGTH_SHORT).show();
                } else if (Vunfilled==1 && YPunfilled == 1) {
                    // Either Y or P given
                    if (probit_Y_input.getText().length() > 0) {
                        // if Y is given
                        P = 0.5*(Erf.erf((Y-5)/Math.sqrt(2))+1)*100.0;
                        V = Math.exp(Y-k1)/k2;
                        probit_P_input.setText(P.toString());
                        probit_V_input.setText(V.toString());
                    }
                    else {
                        // if P is given
                        Y = Math.sqrt(2.0)*Erf.erfInv(2.0*P/100.0-1.0)+5;
                        V = Math.exp(Y-k1)/k2;
                        probit_Y_input.setText(Y.toString());
                        probit_V_input.setText(V.toString());
                    }
                } else if (Vunfilled==0 && YPunfilled == 2) {
                    //solve for Y & P
                    Y = k1+k2*Math.log(V);
                    P = 0.5*(Erf.erf((Y-5)/Math.sqrt(2))+1)*100.0;
                    probit_P_input.setText(P.toString());
                    probit_Y_input.setText(Y.toString());
                }
            }
        });

        TextView probit_k1 = (TextView) findViewById(R.id.probit_k1);
        probit_k1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Probit.this, "Probit parameter k\u2081", Toast.LENGTH_SHORT).show();
            }
        });
        TextView probit_k2 = (TextView) findViewById(R.id.probit_k2);
        probit_k2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Probit.this, "Probit parameter k\u2082", Toast.LENGTH_SHORT).show();
            }
        });
        TextView probit_injury = (TextView) findViewById(R.id.probit_injury);
        probit_injury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Probit.this, "Injury preset (select to autofill injury parameters)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView probit_V = (TextView) findViewById(R.id.probit_V);
        probit_V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Probit.this, "Causative variable V (see Notes below for units)", Toast.LENGTH_SHORT).show();
            }
        });
        TextView probit_Y = (TextView) findViewById(R.id.probit_Y);
        probit_Y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Probit.this, "Probit variable Y (dimensionless)\nFill in either P or Y only!", Toast.LENGTH_SHORT).show();
            }
        });
        TextView probit_P = (TextView) findViewById(R.id.probit_P);
        probit_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Probit.this, "Percentage probability P (in %)\nFill in either P or Y only!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}