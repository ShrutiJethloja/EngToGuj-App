package com.example.gujengapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.print.PrintJob;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();

        WordAdapter itemAdapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word = words.get(position);
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
//                mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
//                mediaPlayer.start();
//                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });

        words.add(new Word("How are you?", "Tame kem cho?", R.raw.p1));
        words.add(new Word("What is your name?", "Tamaru naam su che?", R.raw.p2));
        words.add(new Word("My name is...", "Maru naam ... che", R.raw.p3));
        words.add(new Word("How are you feeling?", "Tamne kevu che?", R.raw.p4));
        words.add(new Word("I am feeling good", "Mane saru che", R.raw.p5));
        words.add(new Word("Are you coming?", "Tame aavo cho?", R.raw.p6));
        words.add(new Word("Yes, I am coming", "haa, hu aavu chu", R.raw.p7));
        words.add(new Word("No, I am not coming", "Nah hu nathi avti", R.raw.p8));
        words.add(new Word("What is your age?", "Tamari ummar su che?", R.raw.p9));
        words.add(new Word("Okay then Byye", "Saru chalo pachi madya", R.raw.p10));
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

    protected void onStop()
    {
        super.onStop();
        releaseMediaPlayer();
    }
}
