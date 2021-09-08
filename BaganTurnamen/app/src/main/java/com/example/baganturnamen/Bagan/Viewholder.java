package com.example.baganturnamen.Bagan;

import android.view.View;
import android.widget.TextView;

import com.example.baganturnamen.R;

public class Viewholder {

    TextView textView;

    Viewholder(View view) {
        textView = view.findViewById(R.id.idTvnode);
    }
}
