package com.epct.cnx.Maths;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.epct.cnx.R;

public class Dimen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_dimen);

        Spinner dimen_n_input = (Spinner) findViewById(R.id.dimen_n_input);
        ArrayAdapter<CharSequence> adapter_n = ArrayAdapter.createFromResource(this,
                R.array.dimen_n, android.R.layout.simple_spinner_item);
        adapter_n.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dimen_n_input.setAdapter(adapter_n);

        Spinner dimen_r_input = (Spinner) findViewById(R.id.dimen_r_input);
        ArrayAdapter<CharSequence> adapter_r = ArrayAdapter.createFromResource(this,
                R.array.dimen_r, android.R.layout.simple_spinner_item);
        adapter_r.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dimen_r_input.setAdapter(adapter_r);

        Spinner dimen_d1_input = (Spinner) findViewById(R.id.dimen_d1_input);
        ArrayAdapter<CharSequence> adapter_d1 = ArrayAdapter.createFromResource(this,
                R.array.dimen_base, android.R.layout.simple_spinner_item);
        adapter_d1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dimen_d1_input.setAdapter(adapter_d1);

        Spinner dimen_d2_input = (Spinner) findViewById(R.id.dimen_d2_input);
        ArrayAdapter<CharSequence> adapter_d2 = ArrayAdapter.createFromResource(this,
                R.array.dimen_base, android.R.layout.simple_spinner_item);
        adapter_d2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dimen_d2_input.setAdapter(adapter_d2);

        Spinner dimen_d3_input = (Spinner) findViewById(R.id.dimen_d3_input);
        ArrayAdapter<CharSequence> adapter_d3 = ArrayAdapter.createFromResource(this,
                R.array.dimen_base, android.R.layout.simple_spinner_item);
        adapter_d3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dimen_d3_input.setAdapter(adapter_d3);

        Spinner dimen_d4_input = (Spinner) findViewById(R.id.dimen_d4_input);
        ArrayAdapter<CharSequence> adapter_d4 = ArrayAdapter.createFromResource(this,
                R.array.dimen_base, android.R.layout.simple_spinner_item);
        adapter_d4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dimen_d4_input.setAdapter(adapter_d4);

        Button dimen_display = (Button) findViewById(R.id.dimen_display);
        dimen_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableLayout dimen_table = (TableLayout) findViewById(R.id.dimen_table);
                Spinner spinner_dimen_n = (Spinner) findViewById(R.id.dimen_n_input);
                Spinner spinner_dimen_r = (Spinner) findViewById(R.id.dimen_r_input);
                TableRow dimen_d3_row = (TableRow) findViewById(R.id.dimen_d3_row);
                TableRow dimen_d4_row = (TableRow) findViewById(R.id.dimen_d4_row);
                TextView dimen_p5 = (TextView) findViewById(R.id.dimen_p5);
                TextView dimen_p5_input = (TextView) findViewById(R.id.dimen_p5_input);
                TextView dimen_d1p5_input = (TextView) findViewById(R.id.dimen_d1p5_input);
                TextView dimen_d2p5_input = (TextView) findViewById(R.id.dimen_d2p5_input);
                TextView dimen_d3p5_input = (TextView) findViewById(R.id.dimen_d3p5_input);
                TextView dimen_d4p5_input = (TextView) findViewById(R.id.dimen_d4p5_input);
                TextView dimen_p6 = (TextView) findViewById(R.id.dimen_p6);
                TextView dimen_p6_input = (TextView) findViewById(R.id.dimen_p6_input);
                TextView dimen_d1p6_input = (TextView) findViewById(R.id.dimen_d1p6_input);
                TextView dimen_d2p6_input = (TextView) findViewById(R.id.dimen_d2p6_input);
                TextView dimen_d3p6_input = (TextView) findViewById(R.id.dimen_d3p6_input);
                TextView dimen_d4p6_input = (TextView) findViewById(R.id.dimen_d4p6_input);
                Button dimen_reset = (Button) findViewById(R.id.dimen_reset);
                Button dimen_display = (Button) findViewById(R.id.dimen_display);

                switch (spinner_dimen_r.getSelectedItemPosition()) {
                    case 1: // 2 dimens
                        dimen_d3_row.setVisibility(View.GONE);
                        dimen_d4_row.setVisibility(View.GONE);
                        switch (spinner_dimen_n.getSelectedItemPosition()) {
                            case 1: // 4 params
                                dimen_reset.setVisibility(View.VISIBLE);
                                dimen_display.setVisibility(View.GONE);
                                dimen_table.setVisibility(View.VISIBLE);
                                dimen_p5.setVisibility(View.GONE);
                                dimen_p5_input.setVisibility(View.GONE);
                                dimen_d1p5_input.setVisibility(View.GONE);
                                dimen_d2p5_input.setVisibility(View.GONE);
                                dimen_d3p5_input.setVisibility(View.GONE);
                                dimen_d4p5_input.setVisibility(View.GONE);
                                dimen_p6.setVisibility(View.GONE);
                                dimen_p6_input.setVisibility(View.GONE);
                                dimen_d1p6_input.setVisibility(View.GONE);
                                dimen_d2p6_input.setVisibility(View.GONE);
                                dimen_d3p6_input.setVisibility(View.GONE);
                                dimen_d4p6_input.setVisibility(View.GONE);
                                break;
                            case 2: // 5 params
                                dimen_reset.setVisibility(View.VISIBLE);
                                dimen_display.setVisibility(View.GONE);
                                dimen_table.setVisibility(View.VISIBLE);
                                dimen_p5.setVisibility(View.VISIBLE);
                                dimen_p5_input.setVisibility(View.VISIBLE);
                                dimen_d1p5_input.setVisibility(View.VISIBLE);
                                dimen_d2p5_input.setVisibility(View.VISIBLE);
                                dimen_d3p5_input.setVisibility(View.VISIBLE);
                                dimen_d4p5_input.setVisibility(View.VISIBLE);
                                dimen_p6.setVisibility(View.GONE);
                                dimen_p6_input.setVisibility(View.GONE);
                                dimen_d1p6_input.setVisibility(View.GONE);
                                dimen_d2p6_input.setVisibility(View.GONE);
                                dimen_d3p6_input.setVisibility(View.GONE);
                                dimen_d4p6_input.setVisibility(View.GONE);
                                break;
                            case 3: // 6 params
                                dimen_reset.setVisibility(View.VISIBLE);
                                dimen_display.setVisibility(View.GONE);
                                dimen_table.setVisibility(View.VISIBLE);
                                dimen_p5.setVisibility(View.VISIBLE);
                                dimen_p5_input.setVisibility(View.VISIBLE);
                                dimen_d1p5_input.setVisibility(View.VISIBLE);
                                dimen_d2p5_input.setVisibility(View.VISIBLE);
                                dimen_d3p5_input.setVisibility(View.VISIBLE);
                                dimen_d4p5_input.setVisibility(View.VISIBLE);
                                dimen_p6.setVisibility(View.VISIBLE);
                                dimen_p6_input.setVisibility(View.VISIBLE);
                                dimen_d1p6_input.setVisibility(View.VISIBLE);
                                dimen_d2p6_input.setVisibility(View.VISIBLE);
                                dimen_d3p6_input.setVisibility(View.VISIBLE);
                                dimen_d4p6_input.setVisibility(View.VISIBLE);
                                break;
                            default:
                                dimen_reset.setVisibility(View.GONE);
                                dimen_display.setVisibility(View.VISIBLE);
                                dimen_table.setVisibility(View.GONE);
                                dimen_p5.setVisibility(View.GONE);
                                dimen_p5_input.setVisibility(View.GONE);
                                dimen_d1p5_input.setVisibility(View.GONE);
                                dimen_d2p5_input.setVisibility(View.GONE);
                                dimen_d3p5_input.setVisibility(View.GONE);
                                dimen_d4p5_input.setVisibility(View.GONE);
                                dimen_p6.setVisibility(View.GONE);
                                dimen_p6_input.setVisibility(View.GONE);
                                dimen_d1p6_input.setVisibility(View.GONE);
                                dimen_d2p6_input.setVisibility(View.GONE);
                                dimen_d3p6_input.setVisibility(View.GONE);
                                dimen_d4p6_input.setVisibility(View.GONE);
                                Toast.makeText(Dimen.this, "Please select n!", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                    case 2: // 3 dimens
                        dimen_d3_row.setVisibility(View.VISIBLE);
                        dimen_d4_row.setVisibility(View.GONE);
                        switch (spinner_dimen_n.getSelectedItemPosition()) {
                            case 1: // 4 params
                                dimen_reset.setVisibility(View.VISIBLE);
                                dimen_display.setVisibility(View.GONE);
                                dimen_table.setVisibility(View.VISIBLE);
                                dimen_p5.setVisibility(View.GONE);
                                dimen_p5_input.setVisibility(View.GONE);
                                dimen_d1p5_input.setVisibility(View.GONE);
                                dimen_d2p5_input.setVisibility(View.GONE);
                                dimen_d3p5_input.setVisibility(View.GONE);
                                dimen_d4p5_input.setVisibility(View.GONE);
                                dimen_p6.setVisibility(View.GONE);
                                dimen_p6_input.setVisibility(View.GONE);
                                dimen_d1p6_input.setVisibility(View.GONE);
                                dimen_d2p6_input.setVisibility(View.GONE);
                                dimen_d3p6_input.setVisibility(View.GONE);
                                dimen_d4p6_input.setVisibility(View.GONE);
                                break;
                            case 2: // 5 params
                                dimen_reset.setVisibility(View.VISIBLE);
                                dimen_display.setVisibility(View.GONE);
                                dimen_table.setVisibility(View.VISIBLE);
                                dimen_p5.setVisibility(View.VISIBLE);
                                dimen_p5_input.setVisibility(View.VISIBLE);
                                dimen_d1p5_input.setVisibility(View.VISIBLE);
                                dimen_d2p5_input.setVisibility(View.VISIBLE);
                                dimen_d3p5_input.setVisibility(View.VISIBLE);
                                dimen_d4p5_input.setVisibility(View.VISIBLE);
                                dimen_p6.setVisibility(View.GONE);
                                dimen_p6_input.setVisibility(View.GONE);
                                dimen_d1p6_input.setVisibility(View.GONE);
                                dimen_d2p6_input.setVisibility(View.GONE);
                                dimen_d3p6_input.setVisibility(View.GONE);
                                dimen_d4p6_input.setVisibility(View.GONE);
                                break;
                            case 3: // 6 params
                                dimen_reset.setVisibility(View.VISIBLE);
                                dimen_display.setVisibility(View.GONE);
                                dimen_table.setVisibility(View.VISIBLE);
                                dimen_p5.setVisibility(View.VISIBLE);
                                dimen_p5_input.setVisibility(View.VISIBLE);
                                dimen_d1p5_input.setVisibility(View.VISIBLE);
                                dimen_d2p5_input.setVisibility(View.VISIBLE);
                                dimen_d3p5_input.setVisibility(View.VISIBLE);
                                dimen_d4p5_input.setVisibility(View.VISIBLE);
                                dimen_p6.setVisibility(View.VISIBLE);
                                dimen_p6_input.setVisibility(View.VISIBLE);
                                dimen_d1p6_input.setVisibility(View.VISIBLE);
                                dimen_d2p6_input.setVisibility(View.VISIBLE);
                                dimen_d3p6_input.setVisibility(View.VISIBLE);
                                dimen_d4p6_input.setVisibility(View.VISIBLE);
                                break;
                            default:
                                dimen_reset.setVisibility(View.GONE);
                                dimen_display.setVisibility(View.VISIBLE);
                                dimen_table.setVisibility(View.GONE);
                                dimen_p5.setVisibility(View.GONE);
                                dimen_p5_input.setVisibility(View.GONE);
                                dimen_d1p5_input.setVisibility(View.GONE);
                                dimen_d2p5_input.setVisibility(View.GONE);
                                dimen_d3p5_input.setVisibility(View.GONE);
                                dimen_d4p5_input.setVisibility(View.GONE);
                                dimen_p6.setVisibility(View.GONE);
                                dimen_p6_input.setVisibility(View.GONE);
                                dimen_d1p6_input.setVisibility(View.GONE);
                                dimen_d2p6_input.setVisibility(View.GONE);
                                dimen_d3p6_input.setVisibility(View.GONE);
                                dimen_d4p6_input.setVisibility(View.GONE);
                                Toast.makeText(Dimen.this, "Please select n!", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                    case 3: // 4 dimens
                        dimen_d3_row.setVisibility(View.VISIBLE);
                        dimen_d4_row.setVisibility(View.VISIBLE);
                        switch (spinner_dimen_n.getSelectedItemPosition()) {
                            case 1: // 4 params
                                dimen_reset.setVisibility(View.VISIBLE);
                                dimen_display.setVisibility(View.GONE);
                                dimen_table.setVisibility(View.VISIBLE);
                                dimen_p5.setVisibility(View.GONE);
                                dimen_p5_input.setVisibility(View.GONE);
                                dimen_d1p5_input.setVisibility(View.GONE);
                                dimen_d2p5_input.setVisibility(View.GONE);
                                dimen_d3p5_input.setVisibility(View.GONE);
                                dimen_d4p5_input.setVisibility(View.GONE);
                                dimen_p6.setVisibility(View.GONE);
                                dimen_p6_input.setVisibility(View.GONE);
                                dimen_d1p6_input.setVisibility(View.GONE);
                                dimen_d2p6_input.setVisibility(View.GONE);
                                dimen_d3p6_input.setVisibility(View.GONE);
                                dimen_d4p6_input.setVisibility(View.GONE);
                                break;
                            case 2: // 5 params
                                dimen_reset.setVisibility(View.VISIBLE);
                                dimen_display.setVisibility(View.GONE);
                                dimen_table.setVisibility(View.VISIBLE);
                                dimen_p5.setVisibility(View.VISIBLE);
                                dimen_p5_input.setVisibility(View.VISIBLE);
                                dimen_d1p5_input.setVisibility(View.VISIBLE);
                                dimen_d2p5_input.setVisibility(View.VISIBLE);
                                dimen_d3p5_input.setVisibility(View.VISIBLE);
                                dimen_d4p5_input.setVisibility(View.VISIBLE);
                                dimen_p6.setVisibility(View.GONE);
                                dimen_p6_input.setVisibility(View.GONE);
                                dimen_d1p6_input.setVisibility(View.GONE);
                                dimen_d2p6_input.setVisibility(View.GONE);
                                dimen_d3p6_input.setVisibility(View.GONE);
                                dimen_d4p6_input.setVisibility(View.GONE);
                                break;
                            case 3: // 6 params
                                dimen_reset.setVisibility(View.VISIBLE);
                                dimen_display.setVisibility(View.GONE);
                                dimen_table.setVisibility(View.VISIBLE);
                                dimen_p5.setVisibility(View.VISIBLE);
                                dimen_p5_input.setVisibility(View.VISIBLE);
                                dimen_d1p5_input.setVisibility(View.VISIBLE);
                                dimen_d2p5_input.setVisibility(View.VISIBLE);
                                dimen_d3p5_input.setVisibility(View.VISIBLE);
                                dimen_d4p5_input.setVisibility(View.VISIBLE);
                                dimen_p6.setVisibility(View.VISIBLE);
                                dimen_p6_input.setVisibility(View.VISIBLE);
                                dimen_d1p6_input.setVisibility(View.VISIBLE);
                                dimen_d2p6_input.setVisibility(View.VISIBLE);
                                dimen_d3p6_input.setVisibility(View.VISIBLE);
                                dimen_d4p6_input.setVisibility(View.VISIBLE);
                                break;
                            default:
                                dimen_reset.setVisibility(View.GONE);
                                dimen_display.setVisibility(View.VISIBLE);
                                dimen_table.setVisibility(View.GONE);
                                dimen_p5.setVisibility(View.GONE);
                                dimen_p5_input.setVisibility(View.GONE);
                                dimen_d1p5_input.setVisibility(View.GONE);
                                dimen_d2p5_input.setVisibility(View.GONE);
                                dimen_d3p5_input.setVisibility(View.GONE);
                                dimen_d4p5_input.setVisibility(View.GONE);
                                dimen_p6.setVisibility(View.GONE);
                                dimen_p6_input.setVisibility(View.GONE);
                                dimen_d1p6_input.setVisibility(View.GONE);
                                dimen_d2p6_input.setVisibility(View.GONE);
                                dimen_d3p6_input.setVisibility(View.GONE);
                                dimen_d4p6_input.setVisibility(View.GONE);
                                Toast.makeText(Dimen.this, "Please select n!", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                    default:
                        dimen_reset.setVisibility(View.GONE);
                        dimen_display.setVisibility(View.VISIBLE);
                        dimen_table.setVisibility(View.GONE);
                        Toast.makeText(Dimen.this, "Please select r!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        Button dimen_reset = (Button) findViewById(R.id.dimen_reset);
        dimen_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableLayout dimen_table = (TableLayout) findViewById(R.id.dimen_table);
                TextView dimen_output = (TextView) findViewById(R.id.dimen_output);
                Spinner spinner_dimen_n = (Spinner) findViewById(R.id.dimen_n_input);
                Spinner spinner_dimen_r = (Spinner) findViewById(R.id.dimen_r_input);
                Spinner spinner_dimen_d1 = (Spinner) findViewById(R.id.dimen_d1_input);
                Spinner spinner_dimen_d2 = (Spinner) findViewById(R.id.dimen_d2_input);
                Spinner spinner_dimen_d3 = (Spinner) findViewById(R.id.dimen_d3_input);
                Spinner spinner_dimen_d4 = (Spinner) findViewById(R.id.dimen_d4_input);
                Button dimen_reset = (Button) findViewById(R.id.dimen_reset);
                Button dimen_display = (Button) findViewById(R.id.dimen_display);

                dimen_reset.setVisibility(View.GONE);
                dimen_display.setVisibility(View.VISIBLE);
                spinner_dimen_n.setSelection(0);
                spinner_dimen_r.setSelection(0);
                spinner_dimen_d1.setSelection(0);
                spinner_dimen_d2.setSelection(0);
                spinner_dimen_d3.setSelection(0);
                spinner_dimen_d4.setSelection(0);
                dimen_output.setText("");
                for (int i = 0; i < dimen_table.getChildCount(); i++) {
                    TableRow row = (TableRow) dimen_table.getChildAt(i);
                    for (int j = 0; j < row.getChildCount(); j++) {
                        if (row.getChildAt(j) instanceof EditText) {
                            EditText field = (EditText) row.getChildAt(j);
                            field.setText("");
                        }
                    }
                }
                dimen_table.setVisibility(View.GONE);
            }
        });

        TextView dimen_n = (TextView) findViewById(R.id.dimen_n);
        dimen_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dimen.this, "No. of parameters (variables)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dimen_r = (TextView) findViewById(R.id.dimen_r);
        dimen_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dimen.this, "No. of dimensions (base units involved)", Toast.LENGTH_SHORT).show();
            }
        });

        TextView dimen_pname = (TextView) findViewById(R.id.dimen_pname);
        dimen_pname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dimen.this, "Please input name of variables here (letter symbol)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}