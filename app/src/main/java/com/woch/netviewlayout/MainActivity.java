package com.woch.netviewlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String loctionString = "[{\"X\":7.17,\"Y\":10.83,\"W\":100,\"H\":200,\"D\":\"V\"},{\"X\":50.5,\"Y\":18.06,\"W\":200,\"H\":80,\"D\":\"H\"},{\"X\":71.33,\"Y\":65.22,\"W\":60,\"H\":30,\"D\":\"H\"}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ViewLoctionEntity>>(){}.getType();
        ArrayList list = gson.fromJson(loctionString, type);

        UncertainPositionView mUPV = (UncertainPositionView) findViewById(R.id.mUPV);
        mUPV.setList(list);

    }
}
