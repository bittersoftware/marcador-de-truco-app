package com.app.g503192.conta_truco;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

/**
 * Created by Thiago on 30/07/2017.
 */

public class AboutDialog {

    public void AboutDialog(final Activity activity) {


        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Light);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.about_dialog_layout);

        final RelativeLayout relativeLayout = (RelativeLayout) dialog.findViewById(R.id.about_parent_layout);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}