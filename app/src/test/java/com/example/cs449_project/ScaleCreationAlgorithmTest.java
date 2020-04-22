package com.example.cs449_project;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ScaleCreationAlgorithmTest {
    private ScaleCreationAlgorithm theCreation;

    @Before
    public void setUp() throws Exception {
        theCreation = new ScaleCreationAlgorithm();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMajorScaleCreator() {
        String[] actual = theCreation.scaleCreator("Major", "C");

        String[] expected = {"C", "D", "E", "F", "G", "A", "B"};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMinorScaleCreator() {
        String[] actual = theCreation.scaleCreator("Minor", "D");

        String[] expected = {"D", "E", "F", "G", "A", "A#", "C"};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testHarmonicMinorScaleCreator() {
        String[] actual = theCreation.scaleCreator("Harmonic Minor", "E");

        String[] expected = {"E", "F#", "G", "A", "B", "C", "D#"};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMelodicMinorScaleCreator() {
        String[] actual = theCreation.scaleCreator("Melodic Minor", "F");

        String[] expected = {"F", "G", "G#", "A#", "C", "D", "E"};

        assertArrayEquals(expected, actual);
    }
}