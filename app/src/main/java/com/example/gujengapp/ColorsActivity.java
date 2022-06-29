package com.example.gujengapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();

        WordAdapter itemAdapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word = words.get(position);
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                    audioManager.abandonAudioFocus(onAudioFocusChangeListener);
                }
//                mediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());
//                mediaPlayer.start();
//                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });

        words.add(new Word("Red", "Laal", R.drawable.color_red, R.raw.c1));
        words.add(new Word("Mustard Yellow", "Sarasav pilo", R.drawable.color_mustard_yellow, R.raw.c2));
        words.add(new Word("Dusty Yellow", "pilo", R.drawable.color_dusty_yellow, R.raw.c3));
        words.add(new Word("Green", "Lilo", R.drawable.color_green, R.raw.c4));
        words.add(new Word("Brown", "Bhuro", R.drawable.color_brown, R.raw.c5));
        words.add(new Word("Gray", "Bhukhara", R.drawable.color_gray, R.raw.c6));
        words.add(new Word("Black", "Kaado", R.drawable.color_black, R.raw.c7));
        words.add(new Word("White", "Safeed", R.drawable.color_white, R.raw.c8));
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
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private void releaseMediaPlayer(){
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    protected void onStop()
    {
        super.onStop();
        releaseMediaPlayer();
    }
}
