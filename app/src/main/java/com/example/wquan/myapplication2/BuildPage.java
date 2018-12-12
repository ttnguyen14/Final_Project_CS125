package com.example.wquan.myapplication2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;


public class BuildPage extends AppCompatActivity {
    private static final String TAG = BuildPage.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_page);
        //Trinket.
        ImageView trinket = findViewById(R.id.trinket);
        int randomTrinket = (int)(Math.random()*3);
        trinket.setImageDrawable(loadImages("img/item/Trinket" + randomTrinket + ".png"));
        //Item Selection.
        ImageView item1 = findViewById(R.id.item1);
        ImageView item2 = findViewById(R.id.item2);
        ImageView boots = findViewById(R.id.boots);
        ImageView item4 = findViewById(R.id.item4);
        ImageView item5 = findViewById(R.id.item5);
        ImageView item6 = findViewById(R.id.item6);
        int randomItem1 = (int)(Math.random()*70);
        int randomItem2 = (int)(Math.random()*70);
        int randomBoots = (int)(Math.random()*7);
        int randomItem4 = (int)(Math.random()*70);
        int randomItem5 = (int)(Math.random()*70);
        int randomItem6 = (int)(Math.random()*70);
        item1.setImageDrawable(loadImages("img/item/" + randomItem1 + ".png"));
        item2.setImageDrawable(loadImages("img/item/" + randomItem2 + ".png"));
        item4.setImageDrawable(loadImages("img/item/" + randomItem4 + ".png"));
        item5.setImageDrawable(loadImages("img/item/" + randomItem5 + ".png"));
        item6.setImageDrawable(loadImages("img/item/" + randomItem6 + ".png"));
        boots.setImageDrawable(loadImages("img/item/Boots" + randomBoots + ".png"));
        //Summoner Spells Selection.
        ImageView sum1 = findViewById(R.id.sum1);
        ImageView sum2 = findViewById(R.id.sum2);
        int randomSum1 = (int)(Math.random()*8);
        int randomSum2 = (int)(Math.random()*8);
        while (randomSum2 == randomSum1) {
            randomSum2 = (int)(Math.random()*8);
        }
        sum1.setImageDrawable(loadImages("img/spell/SS_" + randomSum1 + ".png"));
        sum2.setImageDrawable(loadImages("img/spell/SS_" + randomSum2 + ".png"));
        //Random Champ Pulled.
        int randomChampId = (int)(Math.random()*7);
        //Skill Order Selection.
        ImageView skill1 = findViewById(R.id.skill1);
        ImageView skill2 = findViewById(R.id.skill2);
        ImageView skill3 = findViewById(R.id.skill3);
        int randomSkill1 = (int)(Math.random()*3);
        int randomSkill2 = (int)(Math.random()*3);
        while (randomSkill2 == randomSkill1) {
            randomSkill2 = (int)(Math.random()*3);
        }
        int randomSkill3 = (int)(Math.random()*3);
        while (randomSkill3 == randomSkill1 || randomSkill3 == randomSkill2) {
            randomSkill3 = (int)(Math.random()*3);
        }
        skill1.setImageDrawable(loadImages("img/spell/" + randomChampId + "_" + randomSkill1 + ".png"));
        skill2.setImageDrawable(loadImages("img/spell/" + randomChampId + "_" + randomSkill2 + ".png"));
        skill3.setImageDrawable(loadImages("img/spell/" + randomChampId + "_" + randomSkill3 + ".png"));
        //Profile Picture.
        ImageView championPic = findViewById(R.id.championpic);
        championPic.setImageDrawable(loadImages("img/champion/" + randomChampId + ".png"));
        //Profile Name.
        TextView champName = findViewById(R.id.championname);
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("shortTestData.json"));
            JSONObject champs = obj.getJSONObject("data");
            Iterator x = champs.keys();
            JSONArray champsArr = new JSONArray();
            while(x.hasNext()) {
                String key = (String) x.next();
                champsArr.put(champs.get(key));
            }
            String[] champNames = new String[champs.length()];
            for (int i = 0; i < champsArr.length(); i++) {
                champNames[i] =  (String)champsArr.getJSONObject(i).get("name");
            }
            String[] champTitles = new String[champs.length()];
            for (int i = 0; i < champsArr.length(); i++) {
                champTitles[i] =  (String)champsArr.getJSONObject(i).get("title");
            }
            champName.setText(champNames[randomChampId] + ", " + champTitles[randomChampId]);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Button refreshButton = (Button) findViewById(R.id.refreshButton);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "button pushed");
                startActivity(new Intent(BuildPage.this, BuildPage.class));
            }
        });

    }
    public String loadJSONFromAsset(final String f) {
        String json = null;
        try {
            InputStream is = getAssets().open(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public Drawable loadImages(final String f) {
        try {
            InputStream ims = getAssets().open(f);
            Drawable d = Drawable.createFromStream(ims, null);
            return d;
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
