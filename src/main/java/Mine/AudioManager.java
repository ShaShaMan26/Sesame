package Mine;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class AudioManager {
    private float SFXVolume = .75F;
    private float BGMVolume = .75F;
    private FloatControl fc;
    private final ArrayList<Clip> clips = new ArrayList<>();
    private Clip bgmClip;

    public AudioManager() {

    }

    private Clip getClip(URL soundURL) {
        try {
            assert soundURL != null;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            float range = fc.getMaximum() - fc.getMinimum();
            float gain = (range * SFXVolume) + fc.getMinimum();
            fc.setValue(gain);

            audioInputStream.close();

            return clip;
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void playSFX(String fileLocation) {
        URL soundURL = getClass().getResource(fileLocation);
        Clip clip = getClip(soundURL);

        clip.start();
        clips.add(clip);
    }

    public void setBGM(String fileLocation) {
        URL bgmURL = getClass().getResource(fileLocation);
        bgmClip = getClip(bgmURL);
        updateBGMVolume();
    }
    public void playBGM() {
        bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void pauseBGM() {
        bgmClip.stop();
    }
    public void resetBGM() {
        bgmClip.setMicrosecondPosition(0);
    }
    private void updateBGMVolume() {
        FloatControl fc = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = fc.getMaximum() - fc.getMinimum();
        float gain = (range * BGMVolume) + fc.getMinimum();
        fc.setValue(gain);
    }
    public boolean isBGMPlaying() {
        return bgmClip.isRunning();
    }

    public void setBGMVolume(float volume) {
        if (volume < 0 || volume > 1) {
            throw new IllegalArgumentException("Volume value must be between 0 and 1 (inclusive).");
        }
        BGMVolume = volume;
        updateBGMVolume();
    }
    public void setSFXVolume(float volume) {
        if (volume < 0 || volume > 1) {
            throw new IllegalArgumentException("Volume value must be between 0 and 1 (inclusive).");
        }
        SFXVolume = volume;
    }

    public float getBGMVolume() {
        return BGMVolume;
    }
    public float getSFXVolume() {
        return SFXVolume;
    }

    public void clear() {
        clips.forEach(Line::close);
        clips.clear();

        pauseBGM();
        bgmClip.close();
    }
}