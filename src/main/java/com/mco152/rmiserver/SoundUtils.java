package com.mco152.rmiserver;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * Tone generator based on Java sampled sound package.
 */
public class SoundUtils {

    public static float SAMPLE_RATE = 8000f;

    public static void tone(int hz, int msecs)
            throws LineUnavailableException {
        tone(hz, msecs, 1.0);
    }

    /**
     * Generate desired tone.
     *
     * @param hz
     * @param msecs
     * @param vol
     * @throws LineUnavailableException
     */
    public static void tone(int hz, int msecs, double vol)
            throws LineUnavailableException {
        byte[] buf = new byte[1];
        AudioFormat af =
                new AudioFormat(
                        SAMPLE_RATE, // sampleRate
                        8,           // sampleSizeInBits
                        1,           // channels
                        true,        // signed
                        false);      // bigEndian
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        for (int i = 0; i < msecs * 8; i++) {
            double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
            buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
            sdl.write(buf, 0, 1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }

    /**
     * for tests
     *
     * @param args
     */
    public static void main(String[] args) {
        play5Tones();
    }

    public static void play5Tones() {
        try {
            SoundUtils.tone(1000, 100);
            Thread.sleep(1000);
            SoundUtils.tone(3000, 2000);
            Thread.sleep(1000);
            SoundUtils.tone(2000, 1000);
            Thread.sleep(1000);
            SoundUtils.tone(400, 500);
            Thread.sleep(1000);
            SoundUtils.tone(400, 500, 0.2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}