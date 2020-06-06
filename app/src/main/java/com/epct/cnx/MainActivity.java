package com.epct.cnx;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.epct.cnx.FluSol.PackedBed;
import com.epct.cnx.FluSol.Pneumatic;
import com.epct.cnx.FluSol.SingPart;
import com.epct.cnx.Fluid.Atm;
import com.epct.cnx.Fluid.Friction;
import com.epct.cnx.HeatMass.ConvHeat;
import com.epct.cnx.HeatMass.ConvMass;
import com.epct.cnx.HeatMass.HeatExc;
import com.epct.cnx.HeatMass.MassDiff;
import com.epct.cnx.HeatMass.TransHeat;
import com.epct.cnx.Help.About;
import com.epct.cnx.Help.OpenSource;
import com.epct.cnx.Help.Settings;
import com.epct.cnx.Kinetic.KinData;
import com.epct.cnx.Kinetic.NCstr;
import com.epct.cnx.Kinetic.Single;
import com.epct.cnx.Maths.Calculus;
import com.epct.cnx.Maths.Dimen;
import com.epct.cnx.Maths.Interpol;
import com.epct.cnx.Oshe.Flamm;
import com.epct.cnx.Oshe.PasqGiff;
import com.epct.cnx.Oshe.Probit;
import com.epct.cnx.Oshe.SourceTerm;
import com.epct.cnx.Proc.Laplace;
import com.epct.cnx.Proc.Sodt;
import com.epct.cnx.Thermo.Antoine;
import com.epct.cnx.Thermo.DpBbp;
import com.epct.cnx.Thermo.Fugacity;
import com.epct.cnx.Thermo.HeatCap;
import com.epct.cnx.Thermo.Steam;
import com.epct.cnx.Thermo.VdwEOS;
import com.epct.cnx.Thermo.VirialEOS;
import com.epct.cnx.Thermo.XVirial;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class MainActivity extends AppCompatActivity {

    ExpandableRelativeLayout expandableLayout_thermo, expandableLayout_fluid,
            expandableLayout_kinetic, expandableLayout_heatmass, expandableLayout_flusol,
            expandableLayout_proc, expandableLayout_oshe,
            expandableLayout_maths, expandableLayout_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        expandableLayout_thermo = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_thermo);
        expandableLayout_fluid = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_fluid);
        expandableLayout_kinetic = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_kinetic);
        expandableLayout_heatmass = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_heatmass);
        expandableLayout_flusol = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_flusol);
        expandableLayout_proc = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_proc);
        expandableLayout_oshe = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_oshe);
        expandableLayout_maths = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_maths);
        expandableLayout_help = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_help);

        expandableLayout_thermo.collapse();
        expandableLayout_fluid.collapse();
        expandableLayout_proc.collapse();
        expandableLayout_maths.collapse();
        expandableLayout_help.collapse();

        GridView menugridview_thermo = (GridView) findViewById(R.id.menugridview_thermo);
        menugridview_thermo.setAdapter(new AdapterThermo(this));
        menugridview_thermo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (position) {
                    case 0: //heatcap
                        Intent heatcap = new Intent(MainActivity.this, HeatCap.class);
                        startActivity(heatcap);
                        break;
                    case 1: //antoine
                        Intent antoine = new Intent(MainActivity.this, Antoine.class);
                        startActivity(antoine);
                        break;
                    case 2: //virialeos
                        Intent virialeos = new Intent(MainActivity.this, VirialEOS.class);
                        startActivity(virialeos);
                        break;
                    case 3: //vdweos
                        Intent vdweos = new Intent(MainActivity.this, VdwEOS.class);
                        startActivity(vdweos);
                        break;
                    case 4: //fugacity
                        Intent fugacity = new Intent(MainActivity.this, Fugacity.class);
                        startActivity(fugacity);
                        break;
                    case 5: //xvirial
                        Intent xvirial = new Intent(MainActivity.this, XVirial.class);
                        startActivity(xvirial);
                        break;
                    case 6: //dpbbp
                        Intent dpbbp = new Intent(MainActivity.this, DpBbp.class);
                        startActivity(dpbbp);
                        break;
                    case 7: //steam
                        Intent steam = new Intent(MainActivity.this, Steam.class);
                        startActivity(steam);
                        break;
                }
            }
        });
        GridView menugridview_fluid = (GridView) findViewById(R.id.menugridview_fluid);
        menugridview_fluid.setAdapter(new AdapterFluid(this));
        menugridview_fluid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (position) {
                    case 0: //atm
                        Intent atm = new Intent(MainActivity.this, Atm.class);
                        startActivity(atm);
                        break;
                    case 1: //friction
                        Intent friction = new Intent(MainActivity.this, Friction.class);
                        startActivity(friction);
                        break;
                }
            }
        });
        GridView menugridview_kinetic = (GridView) findViewById(R.id.menugridview_kinetic);
        menugridview_kinetic.setAdapter(new AdapterKinetic(this));
        menugridview_kinetic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (position) {
                    case 0: //kindata
                        Intent kindata = new Intent(MainActivity.this, KinData.class);
                        startActivity(kindata);
                        break;
                    case 1: //single
                        Intent single = new Intent(MainActivity.this, Single.class);
                        startActivity(single);
                        break;
                    case 2: //ncstr
                        Intent ncstr = new Intent(MainActivity.this, NCstr.class);
                        startActivity(ncstr);
                        break;
                }
            }
        });
        GridView menugridview_heatmass = (GridView) findViewById(R.id.menugridview_heatmass);
        menugridview_heatmass.setAdapter(new AdapterHeatMass(this));
        menugridview_heatmass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (position) {
                    case 0: //convheat
                        Intent convheat = new Intent(MainActivity.this, ConvHeat.class);
                        startActivity(convheat);
                        break;
                    case 1: //transheat
                        Intent transheat = new Intent(MainActivity.this, TransHeat.class);
                        startActivity(transheat);
                        break;
                    case 2: //heatexc
                        Intent heatexc = new Intent(MainActivity.this, HeatExc.class);
                        startActivity(heatexc);
                        break;
                    case 3: //massdiff
                        Intent massdiff = new Intent(MainActivity.this, MassDiff.class);
                        startActivity(massdiff);
                        break;
                    case 4: //convmass
                        Intent convmass = new Intent(MainActivity.this, ConvMass.class);
                        startActivity(convmass);
                        break;
                }
            }
        });
        GridView menugridview_flusol = (GridView) findViewById(R.id.menugridview_flusol);
        menugridview_flusol.setAdapter(new AdapterFluSol(this));
        menugridview_flusol.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (position) {
                    case 0: //singpart
                        Intent singpart = new Intent(MainActivity.this, SingPart.class);
                        startActivity(singpart);
                        break;
                    case 1: //packedbed
                        Intent packedbed = new Intent(MainActivity.this, PackedBed.class);
                        startActivity(packedbed);
                        break;
                    case 2: //pneumatic
                        Intent pneumatic = new Intent(MainActivity.this, Pneumatic.class);
                        startActivity(pneumatic);
                        break;
                }
            }
        });
        GridView menugridview_proc = (GridView) findViewById(R.id.menugridview_proc);
        menugridview_proc.setAdapter(new AdapterProc(this));
        menugridview_proc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (position) {
                    case 0: //laplace
                        Intent laplace = new Intent(MainActivity.this, Laplace.class);
                        startActivity(laplace);
                        break;
                    case 1: //sodt
                        Intent sodt = new Intent(MainActivity.this, Sodt.class);
                        startActivity(sodt);
                        break;
                }
            }
        });
        GridView menugridview_oshe = (GridView) findViewById(R.id.menugridview_oshe);
        menugridview_oshe.setAdapter(new AdapterOshe(this));
        menugridview_oshe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (position) {
                    case 0: //probit
                        Intent probit = new Intent(MainActivity.this, Probit.class);
                        startActivity(probit);
                        break;
                    case 1: //flamm
                        Intent flamm = new Intent(MainActivity.this, Flamm.class);
                        startActivity(flamm);
                        break;
                    case 2: //sourceterm
                        Intent sourceterm = new Intent(MainActivity.this, SourceTerm.class);
                        startActivity(sourceterm);
                        break;
                    case 3: //pasqgiff
                        Intent pasqgiff = new Intent(MainActivity.this, PasqGiff.class);
                        startActivity(pasqgiff);
                        break;
                }
            }
        });
        GridView menugridview_maths = (GridView) findViewById(R.id.menugridview_maths);
        menugridview_maths.setAdapter(new AdapterMaths(this));
        menugridview_maths.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (position) {
                    case 0: //interpol
                        Intent interpol = new Intent(MainActivity.this, Interpol.class);
                        startActivity(interpol);
                        break;
                    case 1: //calculus
                        Intent calculus = new Intent(MainActivity.this, Calculus.class);
                        startActivity(calculus);
                        break;
                    case 2: //dimen
                        Intent dimen = new Intent(MainActivity.this, Dimen.class);
                        startActivity(dimen);
                        break;
                }
            }
        });
        GridView menugridview_help = (GridView) findViewById(R.id.menugridview_help);
        menugridview_help.setAdapter(new AdapterHelp(this));
        menugridview_help.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (position) {
                    case 0: //settings
                        Intent settings = new Intent(MainActivity.this, Settings.class);
                        startActivity(settings);
                        break;
                    case 1: //about
                        Intent about = new Intent(MainActivity.this, About.class);
                        startActivity(about);
                        break;
                    case 2: //opensource
                        Intent opensource = new Intent(MainActivity.this, OpenSource.class);
                        startActivity(opensource);
                        break;
                }
            }
        });
    }

    public void expandableButton_thermo(View view) {
        expandableLayout_thermo = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_thermo);
        expandableLayout_thermo.toggle(); // toggle expand and collapse
    }

    public void expandableButton_fluid(View view) {
        expandableLayout_fluid = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_fluid);
        expandableLayout_fluid.toggle(); // toggle expand and collapse
    }

    public void expandableButton_kinetic(View view) {
        expandableLayout_kinetic = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_kinetic);
        expandableLayout_kinetic.toggle(); // toggle expand and collapse
    }

    public void expandableButton_heatmass(View view) {
        expandableLayout_heatmass = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_heatmass);
        expandableLayout_heatmass.toggle(); // toggle expand and collapse
    }

    public void expandableButton_flusol(View view) {
        expandableLayout_flusol = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_flusol);
        expandableLayout_flusol.toggle(); // toggle expand and collapse
    }

    public void expandableButton_proc(View view) {
        expandableLayout_proc = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_proc);
        expandableLayout_proc.toggle(); // toggle expand and collapse
    }

    public void expandableButton_oshe(View view) {
        expandableLayout_oshe = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_oshe);
        expandableLayout_oshe.toggle(); // toggle expand and collapse
    }

    public void expandableButton_maths(View view) {
        expandableLayout_maths = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_maths);
        expandableLayout_maths.toggle(); // toggle expand and collapse
    }

    public void expandableButton_help(View view) {
        expandableLayout_help = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout_help);
        expandableLayout_help.toggle(); // toggle expand and collapse
    }
}
