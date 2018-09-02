package dzem.pl.spanishtime;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import dzem.pl.spanishtime.core.Configuration;
import dzem.pl.spanishtime.core.TheTime;
import dzem.pl.spanishtime.core.Time;

public class MainActivity extends AppCompatActivity {

    MyThread thread;
    Configuration configuration = new Configuration();
    TheTime theTime = new TheTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final TextView welcome = findViewById(R.id.welcome_id);
        //welcome.setText(welcome.getText() + " " + new Date().toString());

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        final Button startButton = findViewById(R.id.start_button_id);
        final Button stopButton = findViewById(R.id.stop_button_id);
        final TextView textView = findViewById(R.id.time_text_id);

        final TimePlayback timePlayback = new TimePlayback(this);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setVisibility(View.INVISIBLE);
                stopButton.setVisibility(View.VISIBLE);

                thread = new MyThread(textView, timePlayback);
                thread.start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setVisibility(View.VISIBLE);
                stopButton.setVisibility(View.INVISIBLE);

                thread.interrupt();
                thread.doStop();
            }
        });

    }

    class MyThread extends Thread {

        public MyThread(TextView textView, TimePlayback timePlayback) {
            super();
            this.textView = textView;
            this.timePlayback = timePlayback;
        }

        private TextView textView;
        private TimePlayback timePlayback;
        private boolean stopped = false;
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void run() {
            while (!thread.isInterrupted() & !stopped) {
                final Time time = theTime.randomTime(configuration.getAccuracy());
                Log.d("SpanishTime", time.toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(time.toString());
                    }
                });

                play(time, configuration.getFirstLang());
                sleep(configuration.getWaitBeforeAnswer());
                play(time, configuration.getSecondLang());
                sleep(configuration.getWaitBeforeNextQuestion());
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void play(Time time, Configuration.Lang lang) {
            List<String> sounds = theTime.soundList(time, lang, configuration.getAnswerMethod());
            int playbackMillis = timePlayback.playSounds(sounds);
            sleep(playbackMillis);
        }

        private void sleep(int miliseconds) {
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
                //do nothing
            }
        }

        public void doStop() {
            stopped = true;
        }
    }
}
