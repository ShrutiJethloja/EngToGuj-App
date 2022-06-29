package com.example.gujengapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    private void releaseMediaPlayer()
    {
        if(mediaPlayer != null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    protected void onStop()
    {
        super.onStop();
        releaseMediaPlayer();
    }

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<>();

        WordAdapter itemAdapter = new WordAdapter(this,words, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word = words.get(position);
                mediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });

        words.add(new Word("Grandfather", "Dada", R.drawable.family_grandfather, R.raw.f1));
        words.add(new Word("Grandmother", "Baa / Dadi", R.drawable.family_grandmother, R.raw.f2));
        words.add(new Word("Mother / Mom", "Maa / Mummy", R.drawable.family_mother, R.raw.f3));
        words.add(new Word("Father / Daddy", "Papa", R.drawable.family_father, R.raw.f4));
        words.add(new Word("Older brother", "Bhailo", R.drawable.family_older_brother, R.raw.f5));
        words.add(new Word("Older sister", "Didi", R.drawable.family_older_sister, R.raw.f6));
        words.add(new Word("Younger brother", "nano bhai", R.drawable.family_younger_brother, R.raw.f7));
        words.add(new Word("Younger sister", "nani behen", R.drawable.family_younger_sister, R.raw.f8));
        words.add(new Word("Son", "Dikaro", R.drawable.family_son, R.raw.f9));
        words.add(new Word("Daughter", "Dikari", R.drawable.family_daughter, R.raw.f10));
    }
}

