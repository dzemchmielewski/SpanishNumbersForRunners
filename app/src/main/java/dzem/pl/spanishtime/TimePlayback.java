package dzem.pl.spanishtime;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static android.content.Context.AUDIO_SERVICE;

public class TimePlayback {

    SoundPool soundPool;
    AudioManager audioManager;
    Context context;

    float actVolume, maxVolume, volume;

    Map<String, Sound> sounds = new HashMap();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TimePlayback(Context context) {
        this.context = context;
        audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;

        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();


        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
        sounds.put("bump", new Sound(R.raw.bump));

        sounds.put("pl_1", new Sound(R.raw.pl_1));
        sounds.put("pl_2", new Sound(R.raw.pl_2));
        sounds.put("pl_3", new Sound(R.raw.pl_3));
        sounds.put("pl_4", new Sound(R.raw.pl_4));
        sounds.put("pl_5", new Sound(R.raw.pl_5));
        sounds.put("pl_6", new Sound(R.raw.pl_6));
        sounds.put("pl_7", new Sound(R.raw.pl_7));
        sounds.put("pl_8", new Sound(R.raw.pl_8));
        sounds.put("pl_9", new Sound(R.raw.pl_9));
        sounds.put("pl_10", new Sound(R.raw.pl_10));
        sounds.put("pl_11", new Sound(R.raw.pl_11));
        sounds.put("pl_12", new Sound(R.raw.pl_12));
        sounds.put("pl_13", new Sound(R.raw.pl_13));
        sounds.put("pl_14", new Sound(R.raw.pl_14));
        sounds.put("pl_15", new Sound(R.raw.pl_15));
        sounds.put("pl_16", new Sound(R.raw.pl_16));
        sounds.put("pl_17", new Sound(R.raw.pl_17));
        sounds.put("pl_18", new Sound(R.raw.pl_18));
        sounds.put("pl_19", new Sound(R.raw.pl_19));
        sounds.put("pl_20", new Sound(R.raw.pl_20));
        sounds.put("pl_30", new Sound(R.raw.pl_30));
        sounds.put("pl_40", new Sound(R.raw.pl_40));
        sounds.put("pl_50", new Sound(R.raw.pl_50));
        sounds.put("pl_60", new Sound(R.raw.pl_60));
        sounds.put("pl_70", new Sound(R.raw.pl_70));
        sounds.put("pl_80", new Sound(R.raw.pl_80));
        sounds.put("pl_90", new Sound(R.raw.pl_90));

        sounds.put("pl_100", new Sound(R.raw.pl_100));
        sounds.put("pl_200", new Sound(R.raw.pl_200));
        sounds.put("pl_300", new Sound(R.raw.pl_300));
        sounds.put("pl_400", new Sound(R.raw.pl_400));
        sounds.put("pl_500", new Sound(R.raw.pl_500));
        sounds.put("pl_600", new Sound(R.raw.pl_600));
        sounds.put("pl_700", new Sound(R.raw.pl_700));
        sounds.put("pl_800", new Sound(R.raw.pl_800));
        sounds.put("pl_900", new Sound(R.raw.pl_900));
        sounds.put("pl_1000", new Sound(R.raw.pl_1000));
        sounds.put("pl_1000000", new Sound(R.raw.pl_1000000));

        sounds.put("pl_numeral_1", new Sound(R.raw.pl_numeral_1));
        sounds.put("pl_numeral_2", new Sound(R.raw.pl_numeral_2));
        sounds.put("pl_numeral_3", new Sound(R.raw.pl_numeral_3));
        sounds.put("pl_numeral_4", new Sound(R.raw.pl_numeral_4));
        sounds.put("pl_numeral_5", new Sound(R.raw.pl_numeral_5));
        sounds.put("pl_numeral_6", new Sound(R.raw.pl_numeral_6));
        sounds.put("pl_numeral_7", new Sound(R.raw.pl_numeral_7));
        sounds.put("pl_numeral_8", new Sound(R.raw.pl_numeral_8));
        sounds.put("pl_numeral_9", new Sound(R.raw.pl_numeral_9));
        sounds.put("pl_numeral_10", new Sound(R.raw.pl_numeral_10));
        sounds.put("pl_numeral_11", new Sound(R.raw.pl_numeral_11));
        sounds.put("pl_numeral_12", new Sound(R.raw.pl_numeral_12));
        sounds.put("pl_dopelniacz_1", new Sound(R.raw.pl_dopelniacz_1));
        sounds.put("pl_dopelniacz_2", new Sound(R.raw.pl_dopelniacz_2));
        sounds.put("pl_dopelniacz_3", new Sound(R.raw.pl_dopelniacz_3));
        sounds.put("pl_dopelniacz_4", new Sound(R.raw.pl_dopelniacz_4));
        sounds.put("pl_dopelniacz_5", new Sound(R.raw.pl_dopelniacz_5));
        sounds.put("pl_dopelniacz_6", new Sound(R.raw.pl_dopelniacz_6));
        sounds.put("pl_dopelniacz_7", new Sound(R.raw.pl_dopelniacz_7));
        sounds.put("pl_dopelniacz_8", new Sound(R.raw.pl_dopelniacz_8));
        sounds.put("pl_dopelniacz_9", new Sound(R.raw.pl_dopelniacz_9));
        sounds.put("pl_dopelniacz_10", new Sound(R.raw.pl_dopelniacz_10));
        sounds.put("pl_dopelniacz_11", new Sound(R.raw.pl_dopelniacz_11));
        sounds.put("pl_dopelniacz_12", new Sound(R.raw.pl_dopelniacz_12));
        sounds.put("pl_pol_do", new Sound(R.raw.pl_pol_do));
        sounds.put("pl_za", new Sound(R.raw.pl_za));
        sounds.put("pl_po", new Sound(R.raw.pl_po));
        sounds.put("pl_kwadrans", new Sound(R.raw.pl_kwadrans));
        sounds.put("pl_za_kwadrans", new Sound(R.raw.pl_za_kwadrans));
        sounds.put("pl_kwadrans_po", new Sound(R.raw.pl_kwadrans_po));


        sounds.put("es_una", new Sound(R.raw.es_una));
        sounds.put("es_1", new Sound(R.raw.es_1));
        sounds.put("es_2", new Sound(R.raw.es_2));
        sounds.put("es_3", new Sound(R.raw.es_3));
        sounds.put("es_4", new Sound(R.raw.es_4));
        sounds.put("es_5", new Sound(R.raw.es_5));
        sounds.put("es_6", new Sound(R.raw.es_6));
        sounds.put("es_7", new Sound(R.raw.es_7));
        sounds.put("es_8", new Sound(R.raw.es_8));
        sounds.put("es_9", new Sound(R.raw.es_9));
        sounds.put("es_10",new Sound(R.raw.es_10));
        sounds.put("es_11",new Sound(R.raw.es_11));
        sounds.put("es_12",new Sound(R.raw.es_12));
        sounds.put("es_13",new Sound(R.raw.es_13));
        sounds.put("es_14",new Sound(R.raw.es_14));
        sounds.put("es_15",new Sound(R.raw.es_15));
        sounds.put("es_16",new Sound(R.raw.es_16));
        sounds.put("es_17",new Sound(R.raw.es_17));
        sounds.put("es_18",new Sound(R.raw.es_18));
        sounds.put("es_19",new Sound(R.raw.es_19));
        sounds.put("es_20",new Sound(R.raw.es_20));
        sounds.put("es_21",new Sound(R.raw.es_21));
        sounds.put("es_22",new Sound(R.raw.es_22));
        sounds.put("es_23",new Sound(R.raw.es_23));
        sounds.put("es_24",new Sound(R.raw.es_24));
        sounds.put("es_25",new Sound(R.raw.es_25));
        sounds.put("es_26",new Sound(R.raw.es_26));
        sounds.put("es_27",new Sound(R.raw.es_27));
        sounds.put("es_28",new Sound(R.raw.es_28));
        sounds.put("es_29",new Sound(R.raw.es_29));
        sounds.put("es_30",new Sound(R.raw.es_30));
        sounds.put("es_40",new Sound(R.raw.es_40));
        sounds.put("es_50",new Sound(R.raw.es_50));
        sounds.put("es_60",new Sound(R.raw.es_60));
        sounds.put("es_70",new Sound(R.raw.es_70));
        sounds.put("es_80",new Sound(R.raw.es_80));
        sounds.put("es_90",new Sound(R.raw.es_90));
        sounds.put("es_100",new Sound(R.raw.es_100));
        sounds.put("es_100to",new Sound(R.raw.es_100to));
        sounds.put("es_200",new Sound(R.raw.es_200));
        sounds.put("es_300",new Sound(R.raw.es_300));
        sounds.put("es_400",new Sound(R.raw.es_400));
        sounds.put("es_500",new Sound(R.raw.es_500));
        sounds.put("es_600",new Sound(R.raw.es_600));
        sounds.put("es_700",new Sound(R.raw.es_700));
        sounds.put("es_800",new Sound(R.raw.es_800));
        sounds.put("es_900",new Sound(R.raw.es_900));
        sounds.put("es_1000",new Sound(R.raw.es_1000));
        sounds.put("es_1000000",new Sound(R.raw.es_1000000));

        sounds.put("es_es_la",new Sound(R.raw.es_es_la));
        sounds.put("es_son_las",new Sound(R.raw.es_son_las));
        sounds.put("es_y",new Sound(R.raw.es_y));
        sounds.put("es_menos",new Sound(R.raw.es_menos));
        sounds.put("es_media",new Sound(R.raw.es_media));
        sounds.put("es_cuarto",new Sound(R.raw.es_cuarto));


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int playSounds(List<String> toPlay) {
        Log.d("SpanishTime", "Playing: " + toPlay.stream().collect(Collectors.joining(",")));
        TimePlayback.SoundPoolTask soundPoolTask = new TimePlayback.SoundPoolTask();
        soundPoolTask.execute(toPlay.toArray(new String[0]));
        return toPlay.stream().map(s -> sounds.get(s).duration).collect(Collectors.summingInt(Integer::intValue));
    }


    private class SoundPoolTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... soundKeys) {
            try {
                for (String key : soundKeys) {
                    soundPool.play(sounds.get(key).sourceID, volume, volume, 1, 0, 1f);
                    Thread.sleep(sounds.get(key).duration);
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {}

        @Override
        protected void onPostExecute(String result) {}
    }

    class Sound {
        int sourceID;
        int duration;

        public Sound(int resID) {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            AssetFileDescriptor afd = null;
            try {
                afd = context.getResources().openRawResourceFd(resID);
                mmr.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            } finally {
                try {
                    if (afd != null) {
                        afd.close();
                    }
                } catch(IOException ioEx) {
                }
            }
            String durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            duration = Integer.parseInt(durationStr);
            sourceID = soundPool.load(context, resID, 1);
        }
    }
}
