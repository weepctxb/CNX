package com.epct.cnx.Maths;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.epct.cnx.R;

public class Interpol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_interpol);

        Button interpol1d_solve = (Button) findViewById(R.id.interpol1d_solve);
        interpol1d_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView interpol1d_xlwr_input = (TextView) findViewById(R.id.interpol1d_xlwr_input);
                TextView interpol1d_x_input = (TextView) findViewById(R.id.interpol1d_x_input);
                TextView interpol1d_xupp_input = (TextView) findViewById(R.id.interpol1d_xupp_input);
                TextView interpol1d_ylwr_input = (TextView) findViewById(R.id.interpol1d_ylwr_input);
                TextView interpol1d_y_input = (TextView) findViewById(R.id.interpol1d_y_input);
                TextView interpol1d_yupp_input = (TextView) findViewById(R.id.interpol1d_yupp_input);

                if (interpol1d_x_input.getText().length() > 0
                        & interpol1d_y_input.getText().length() == 0
                        & interpol1d_xlwr_input.getText().length() > 0
                        & interpol1d_xupp_input.getText().length() > 0
                        & interpol1d_ylwr_input.getText().length() > 0
                        & interpol1d_yupp_input.getText().length() > 0) {
                    Double x = Double.parseDouble(interpol1d_x_input.getText().toString());
                    Double x1 = Double.parseDouble(interpol1d_xlwr_input.getText().toString());
                    Double x2 = Double.parseDouble(interpol1d_xupp_input.getText().toString());
                    Double y1 = Double.parseDouble(interpol1d_ylwr_input.getText().toString());
                    Double y2 = Double.parseDouble(interpol1d_yupp_input.getText().toString());
                    Double y = (y2 - y1) / (x2 - x1) * (x - x1) + y1;
                    interpol1d_y_input.setText(y.toString());
                } else if (interpol1d_x_input.getText().length() == 0
                        & interpol1d_y_input.getText().length() > 0
                        & interpol1d_xlwr_input.getText().length() > 0
                        & interpol1d_xupp_input.getText().length() > 0
                        & interpol1d_ylwr_input.getText().length() > 0
                        & interpol1d_yupp_input.getText().length() > 0) {
                    Double y = Double.parseDouble(interpol1d_y_input.getText().toString());
                    Double x1 = Double.parseDouble(interpol1d_xlwr_input.getText().toString());
                    Double x2 = Double.parseDouble(interpol1d_xupp_input.getText().toString());
                    Double y1 = Double.parseDouble(interpol1d_ylwr_input.getText().toString());
                    Double y2 = Double.parseDouble(interpol1d_yupp_input.getText().toString());
                    Double x = (x2 - x1) / (y2 - y1) * (y - y1) + x1;
                    interpol1d_x_input.setText(x.toString());
                } else if (interpol1d_x_input.getText().length() > 0
                        & interpol1d_y_input.getText().length() > 0
                        & interpol1d_xlwr_input.getText().length() > 0
                        & interpol1d_xupp_input.getText().length() > 0
                        & interpol1d_ylwr_input.getText().length() > 0
                        & interpol1d_yupp_input.getText().length() > 0) {
                    Toast.makeText(Interpol.this, "Over-determined system!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Interpol.this, "Under-determined system!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button interpol2d_solve = (Button) findViewById(R.id.interpol2d_solve);
        interpol2d_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView interpol2d_xlwr_input = (TextView) findViewById(R.id.interpol2d_xlwr_input);
                TextView interpol2d_x_input = (TextView) findViewById(R.id.interpol2d_x_input);
                TextView interpol2d_xupp_input = (TextView) findViewById(R.id.interpol2d_xupp_input);
                TextView interpol2d_ylwr_input = (TextView) findViewById(R.id.interpol2d_ylwr_input);
                TextView interpol2d_y_input = (TextView) findViewById(R.id.interpol2d_y_input);
                TextView interpol2d_yupp_input = (TextView) findViewById(R.id.interpol2d_yupp_input);
                TextView interpol2d_zll_input = (TextView) findViewById(R.id.interpol2d_zll_input);
                TextView interpol2d_zlu_input = (TextView) findViewById(R.id.interpol2d_zlu_input);
                TextView interpol2d_zul_input = (TextView) findViewById(R.id.interpol2d_zul_input);
                TextView interpol2d_zuu_input = (TextView) findViewById(R.id.interpol2d_zuu_input);
                TextView interpol2d_z_input = (TextView) findViewById(R.id.interpol2d_z_input);

                if (interpol2d_x_input.getText().length() > 0
                        & interpol2d_y_input.getText().length() > 0
                        & interpol2d_xlwr_input.getText().length() > 0
                        & interpol2d_xupp_input.getText().length() > 0
                        & interpol2d_ylwr_input.getText().length() > 0
                        & interpol2d_yupp_input.getText().length() > 0
                        & interpol2d_zll_input.getText().length() > 0
                        & interpol2d_zlu_input.getText().length() > 0
                        & interpol2d_zul_input.getText().length() > 0
                        & interpol2d_zuu_input.getText().length() > 0
                        & interpol2d_z_input.getText().length() == 0) {
                    Double x = Double.parseDouble(interpol2d_x_input.getText().toString());
                    Double y = Double.parseDouble(interpol2d_y_input.getText().toString());
                    Double x1 = Double.parseDouble(interpol2d_xlwr_input.getText().toString());
                    Double x2 = Double.parseDouble(interpol2d_xupp_input.getText().toString());
                    Double y1 = Double.parseDouble(interpol2d_ylwr_input.getText().toString());
                    Double y2 = Double.parseDouble(interpol2d_yupp_input.getText().toString());
                    Double zll = Double.parseDouble(interpol2d_zll_input.getText().toString());
                    Double zlu = Double.parseDouble(interpol2d_zlu_input.getText().toString());
                    Double zul = Double.parseDouble(interpol2d_zul_input.getText().toString());
                    Double zuu = Double.parseDouble(interpol2d_zuu_input.getText().toString());
                    Double z = (x2-x)*(y2-y)/((x2-x1)*(y2-y1))*zll +
                            (x-x1)*(y2-y)/((x2-x1)*(y2-y1))*zlu +
                            (x2-x)*(y-y1)/((x2-x1)*(y2-y1))*zul +
                            (x-x1)*(y-y1)/((x2-x1)*(y2-y1))*zuu;
                    interpol2d_z_input.setText(z.toString());
                }
                else if (interpol2d_x_input.getText().length() > 0
                        & interpol2d_y_input.getText().length() > 0
                        & interpol2d_xlwr_input.getText().length() > 0
                        & interpol2d_xupp_input.getText().length() > 0
                        & interpol2d_ylwr_input.getText().length() > 0
                        & interpol2d_yupp_input.getText().length() > 0
                        & interpol2d_zll_input.getText().length() > 0
                        & interpol2d_zlu_input.getText().length() > 0
                        & interpol2d_zul_input.getText().length() > 0
                        & interpol2d_zuu_input.getText().length() > 0
                        & interpol2d_z_input.getText().length() > 0) {
                    Toast.makeText(Interpol.this, "Over-determined system!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Interpol.this, "Under-determined system!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button interpol1d_reset = (Button) findViewById(R.id.interpol1d_reset);
        interpol1d_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView interpol1d_xlwr_input = (TextView) findViewById(R.id.interpol1d_xlwr_input);
                TextView interpol1d_x_input = (TextView) findViewById(R.id.interpol1d_x_input);
                TextView interpol1d_xupp_input = (TextView) findViewById(R.id.interpol1d_xupp_input);
                TextView interpol1d_ylwr_input = (TextView) findViewById(R.id.interpol1d_ylwr_input);
                TextView interpol1d_y_input = (TextView) findViewById(R.id.interpol1d_y_input);
                TextView interpol1d_yupp_input = (TextView) findViewById(R.id.interpol1d_yupp_input);
                interpol1d_xlwr_input.setText("");
                interpol1d_x_input.setText("");
                interpol1d_xupp_input.setText("");
                interpol1d_ylwr_input.setText("");
                interpol1d_y_input.setText("");
                interpol1d_yupp_input.setText("");
            }
        });

        Button interpol2d_reset = (Button) findViewById(R.id.interpol2d_reset);
        interpol2d_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView interpol2d_xlwr_input = (TextView) findViewById(R.id.interpol2d_xlwr_input);
                TextView interpol2d_x_input = (TextView) findViewById(R.id.interpol2d_x_input);
                TextView interpol2d_xupp_input = (TextView) findViewById(R.id.interpol2d_xupp_input);
                TextView interpol2d_ylwr_input = (TextView) findViewById(R.id.interpol2d_ylwr_input);
                TextView interpol2d_y_input = (TextView) findViewById(R.id.interpol2d_y_input);
                TextView interpol2d_yupp_input = (TextView) findViewById(R.id.interpol2d_yupp_input);
                TextView interpol2d_zll_input = (TextView) findViewById(R.id.interpol2d_zll_input);
                TextView interpol2d_zlu_input = (TextView) findViewById(R.id.interpol2d_zlu_input);
                TextView interpol2d_zul_input = (TextView) findViewById(R.id.interpol2d_zul_input);
                TextView interpol2d_zuu_input = (TextView) findViewById(R.id.interpol2d_zuu_input);
                TextView interpol2d_z_input = (TextView) findViewById(R.id.interpol2d_z_input);
                interpol2d_xlwr_input.setText("");
                interpol2d_x_input.setText("");
                interpol2d_xupp_input.setText("");
                interpol2d_ylwr_input.setText("");
                interpol2d_y_input.setText("");
                interpol2d_yupp_input.setText("");
                interpol2d_zll_input.setText("");
                interpol2d_zlu_input.setText("");
                interpol2d_zul_input.setText("");
                interpol2d_zuu_input.setText("");
                interpol2d_z_input.setText("");
            }
        });
    }
}