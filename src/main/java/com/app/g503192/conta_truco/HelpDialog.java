package com.app.g503192.conta_truco;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Thiago on 30/07/2017.
 */

public class HelpDialog {
    int mTela = 0;

    public void showDialog(final Activity activity) {


        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Light);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.help_dialog_layout);

        final RelativeLayout relativeLayout = (RelativeLayout) dialog.findViewById(R.id.hlpBckgrd);
        final LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.hlpBottomBtns);
        final ImageView imageView = (ImageView) dialog.findViewById(R.id.dialogImg);
        final TextView dialogTxtTitle = (TextView) dialog.findViewById(R.id.dialogTxtTitle);
        final TextView dialogTxt1 = (TextView) dialog.findViewById(R.id.dialogTxt1);
        final TextView dialogTxt2 = (TextView) dialog.findViewById(R.id.dialogTxt2);
        final TextView dialogTxt3 = (TextView) dialog.findViewById(R.id.dialogTxt3);

        final TextView next = (TextView) dialog.findViewById(R.id.hlpnext1);
        final TextView skip = (TextView) dialog.findViewById(R.id.hlpskip1);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTela++;

                switch (mTela) {
                    case 1:
                        relativeLayout.setBackgroundResource(R.color.bg_screen2);
                        imageView.setImageResource(R.drawable.ic_replay_black_24dp);
                        dialogTxtTitle.setText(R.string.slide_2_title);
                        dialogTxt1.setText(R.string.slide_2_desc);
                        dialogTxt2.setText(R.string.slide_2_desc2);
                        dialogTxt3.setText("");
                        return;

                    case 2:
                        relativeLayout.setBackgroundResource(R.color.bg_screen3);
                        imageView.setImageResource(R.drawable.ic_history_black_24dp);
                        dialogTxtTitle.setText(R.string.slide_3_title);
                        dialogTxt1.setText(R.string.slide_3_desc);
                        dialogTxt2.setText(R.string.slide_3_desc2);
                        dialogTxt3.setText("");
                        return;

                    case 3:
                        relativeLayout.setBackgroundResource(R.color.bg_screen4);
                        imageView.setImageResource(R.drawable.ic_settings_black_24dp);
                        dialogTxtTitle.setText(R.string.slide_4_title);
                        dialogTxt1.setText(R.string.slide_4_desc);
                        dialogTxt2.setText(R.string.slide_4_desc2);
                        dialogTxt3.setText("Bom Jogo!");
                        linearLayout.removeView(next);
                        return;

                    default:
                        dialog.dismiss();
                        return;
                }


            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}