package Mine;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * The <code>AudioManager</code> class manages audio playback of <code>.wav</code> files.
 * It allows for a background music track to be designated, sound effects to be played, and
 * volume of each to be independently altered.
 */
public class AudioManager {
    private float SFXVolume = .75F;
    private float BGMVolume = .75F;
    private FloatControl fc;
    private final ArrayList<Clip> clips = new ArrayList<>();
    private Clip bgmClip;

    /**
     * Generates a <code>Clip</code> for use in audio playback.
     * @param soundURL <code>URL</code> of audio file
     * @return <code>Clip</code> made from audio
     */
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

    /**
     * Plays a designated audio file once all the way through.
     * @param fileLocation audio file location (represented by a <code>String</code>)
     */
    public void playSFX(String fileLocation) {
        URL soundURL = getClass().getResource(fileLocation);
        Clip clip = getClip(soundURL);

        clip.start();
        clips.add(clip);
    }

    /**
     * Sets the background music file.
     * @param fileLocation audio file location (represented by a <code>String</code>)
     */
    public void setBGM(String fileLocation) {
        URL bgmURL = getClass().getResource(fileLocation);
        bgmClip = getClip(bgmURL);
        updateBGMVolume();
    }
    /**
     * Starts audio playback of current background music.
     */
    public void playBGM() {
        bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    /**
     * Pauses audio playback of current background music.
     */
    public void pauseBGM() {
        bgmClip.stop();
    }
    /**
     * Sets audio playback of current background music to the start of the file.
     */
    public void resetBGM() {
        bgmClip.setMicrosecondPosition(0);
    }
    /**
     * Applies volume augmentations to background music playback.
     */
    private void updateBGMVolume() {
        FloatControl fc = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = fc.getMaximum() - fc.getMinimum();
        float gain = (range * BGMVolume) + fc.getMinimum();
        fc.setValue(gain);
    }
    /**
     * Indicates whether background music is currently playing.
     * @return <code>true</code> if playing; <code>false</code> if not
     */
    public boolean isBGMPlaying() {
        return bgmClip.isRunning();
    }

    /**
     * Sets and updates the volume of audio playback for current background music.
     * <p>
     *     Volume adjustment works as a multiplier, so a value of <code>.5</code>
     *     would be audio playback at 50% of the file's volume.
     * </p>
     * @param volume <code>float</code> multiplier value.
     *               <p>
     *                  Clamps to values within the range of <code>0</code> to <code>1</code>
     *               </p>
     */
    public void setBGMVolume(float volume) {
        if (volume < 0 || volume > 1) {
            throw new IllegalArgumentException("Volume value must be between 0 and 1 (inclusive).");
        }
        BGMVolume = volume;
        updateBGMVolume();
    }
    /**
     * Sets the volume of all future audio playback for sound effects.
     * <p>
     *     Volume adjustment works as a multiplier, so a value of <code>.5</code>
     *     would be audio playback at 50% of the file's volume.
     * </p>
     * @param volume <code>float</code> multiplier value.
     *               <p>
     *                  Clamps to values within the range of <code>0</code> to <code>1</code>
     *               </p>
     */
    public void setSFXVolume(float volume) {
        if (volume < 0 || volume > 1) {
            throw new IllegalArgumentException("Volume value must be between 0 and 1 (inclusive).");
        }
        SFXVolume = volume;
    }

    /**
     * Returns the current volume value for background music.
     * @return background music volume
     */
    public float getBGMVolume() {
        return BGMVolume;
    }
    /**
     * Returns the current volume value for all sound effects.
     * @return sound effects volume
     */
    public float getSFXVolume() {
        return SFXVolume;
    }

    /**
     * Empties <code>AudioManager</code> of background music and all sound effects.
     */
    public void clear() {
        clips.forEach(Line::close);
        clips.clear();

        pauseBGM();
        bgmClip.close();
        bgmClip = null;
    }
}
