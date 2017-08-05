package com.app.g503192.conta_truco;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    SettingsOptions mCallback;

    private String mTeam1Name = "Nós";
    private String mTeam2Name = "Eles";
    private int mMaxRoundsOpt = 0;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.settings_fragment, container, false);

        if (savedInstanceState != null){
            mTeam1Name = savedInstanceState.getString("Team1Name");
            mTeam2Name = savedInstanceState.getString("Team2Name");
            mMaxRoundsOpt = savedInstanceState.getInt("MaxRoundsOpt");
        }

        final TextView textNameTeam1 = (TextView) rootView.findViewById(R.id.txtNomeEquipe1);
        final TextView textNameTeam2 = (TextView) rootView.findViewById(R.id.txtNomeEquipe2);

        textNameTeam1.setText(mTeam1Name);
        textNameTeam2.setText(mTeam2Name);


        Button button = (Button) rootView.findViewById(R.id.defaultButton);

        final Switch switchScreenState = (Switch) rootView.findViewById(R.id.swithScreen);


       LinearLayout viewNomeEquipe1 = (LinearLayout) rootView.findViewById(R.id.nomeEquipe1) ;
       LinearLayout viewNomeEquipe2 = (LinearLayout) rootView.findViewById(R.id.nomeEquipe2) ;
       LinearLayout viewMaxRounds = (LinearLayout) rootView.findViewById(R.id.maxRounds);

        viewNomeEquipe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Nome da Equipe 1");

                // Set up the input
                final EditText input = new EditText(getContext());

                input.setHint("Nós");
                input.setHintTextColor(getResources().getColor(R.color.secondary_text));

                // Specify Max Length of Edit Text View
                int maxLength = 20;
                input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                builder.setView(input);


                input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(s.length() > 19)
                            Toast.makeText(getContext(), "Máximo de 20 caracteres permitidos",Toast.LENGTH_SHORT).show();
                    }
                });


                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTeam1Name = input.getText().toString();

                        if (mTeam1Name.equals("")) {
                            mTeam1Name = "Nós";
                        }

                        TextView textView = (TextView) rootView.findViewById(R.id.txtNomeEquipe1);
                        textView.setText(mTeam1Name);

                        ((SettingsOptions) getActivity()).setNameTeam1(mTeam1Name);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();


            }
        });


        viewNomeEquipe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Nome da Equipe 2");

                // Set up the input
                final EditText input = new EditText(getContext());

                input.setHint("Eles");
                input.setHintTextColor(getResources().getColor(R.color.secondary_text));

                // Specify Max Length of Edit Text View
                int maxLength = 20;
                input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                builder.setView(input);

                input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(s.length() > 19)
                            Toast.makeText(getContext(), "Máximo de 20 caracteres permitidos",Toast.LENGTH_SHORT).show();
                    }
                });


                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTeam2Name = input.getText().toString();

                        if (mTeam2Name.equals("")) {
                            mTeam2Name = "Eles";
                        }

                        TextView textView = (TextView) rootView.findViewById(R.id.txtNomeEquipe2);
                        textView.setText(mTeam2Name);

                        ((SettingsOptions) getActivity()).setNameTeam2(mTeam2Name);


                      }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();


            }
        });


        viewMaxRounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String[] items = {"Livre", "Melhor de 3", "Melhor de 5", "Melhor de 7"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);

                builder.setTitle("Máximo de Rodadas")
                        .setSingleChoiceItems(items, mMaxRoundsOpt, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {

                                ListView lw = ((AlertDialog)dialog).getListView();
                                lw.setTag(Integer.valueOf(item));

                                mMaxRoundsOpt = (Integer)lw.getTag();

                                ((SettingsOptions) getActivity()).setMaxRounds(mMaxRoundsOpt);

                                Toast.makeText(getContext(), "Configuração aplicada no jogo atual", Toast.LENGTH_SHORT).show();

                                dialog.dismiss();
                            }
                        });

                builder.show();

            }
        });

        switchScreenState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }

                else {
                    getActivity().getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTeam1Name = "Nós";
                mTeam2Name = "Eles";

                textNameTeam1.setText(mTeam1Name);
                textNameTeam2.setText(mTeam2Name);
                mMaxRoundsOpt = 0;

                ((SettingsOptions) getActivity()).setNameTeam1(mTeam1Name);
                ((SettingsOptions) getActivity()).setNameTeam2(mTeam2Name);
                ((SettingsOptions) getActivity()).setMaxRounds(mMaxRoundsOpt);

                switchScreenState.setChecked(false);

                Toast.makeText(getContext(), "Configuração padrão restaurada", Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SettingsOptions) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataCommunication");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("MaxRoundsOpt", mMaxRoundsOpt);
        outState.putString("Team1Name", mTeam1Name);
        outState.putString("Team2Name", mTeam2Name);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null){
            mTeam1Name = savedInstanceState.getString("Team1Name");
            mTeam2Name = savedInstanceState.getString("Team2Name");
            mMaxRoundsOpt = savedInstanceState.getInt("MaxRoundsOpt");
        }

    }
}
