package com.example.gujengapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.icu.number.NumberRangeFormatter;
import android.media.AudioManager;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_number);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word = words.get(position);
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
//                mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
//                mediaPlayer.start();
//                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });

        words.add(new Word("One", "Ek", R.drawable.number_one, R.raw.n1));
        words.add(new Word("Two", "Be", R.drawable.number_two, R.raw.n2));
        words.add(new Word("Three", "Tran", R.drawable.number_three, R.raw.n3));
        words.add(new Word("Four", "Char", R.drawable.number_four, R.raw.n4));
        words.add(new Word("Five", "Paach", R.drawable.number_five, R.raw.n5));
        words.add(new Word("Six", "Chh", R.drawable.number_six, R.raw.n6));
        words.add(new Word("Seven", "Saat", R.drawable.number_seven, R.raw.n7));
        words.add(new Word("Eight", "Aath", R.drawable.number_eight, R.raw.n8));
        words.add(new Word("Nine", "Nav", R.drawable.number_nine, R.raw.n9));
        words.add(new Word("Ten", "Das", R.drawable.number_ten, R.raw.n10));

  //      LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

//        for(int i=0; i<words.size(); i++)
//        {
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(i));
//            rootView.addView(wordView);
//        }
    }

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }
        }
    };

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private void releaseMediaPlayer()
    {
        if(mediaPlayer != null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    public void onStop()
    {
        super.onStop();
        releaseMediaPlayer();
    }
}
