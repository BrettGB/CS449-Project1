package com.example.cs449_project;

class ScaleCreationAlgorithm {

    //an array of the available notes
    private String[] musicNotes = {"B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B",
            "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C"};

    //sets up the pattern for both the major and minor scales
    private final String[] major = {"W", "W", "H", "W", "W", "W", "H"};
    private final String[] minor = {"W", "H", "W", "W", "H", "W", "W"};
    private final String[] harmonic = {"W", "H", "W", "W", "H", "D", "H"};
    private final String[] melodic = {"W", "H", "W", "W", "W", "W", "H"};

    //this array will become the completed scale at the end
    private String[] theScale = new String[7];

    private int i;

    //decides which scale's creator to run
    String[] scaleCreator(String scale, String key){

        //this gets the starting position of the soon to be scale
        i = 0;
        while (!musicNotes[i].equals(key))
            ++i;

        switch (scale) {
            case "Minor":
                return (minorCreator(scale, key));
            case "Harmonic Minor":
                return (harmonicCreator(scale, key));
            case "Melodic Minor":
                return (melodicCreator(scale, key));
            default:
                return (majorCreator(scale, key));
        }
    }

    private String[] majorCreator(String scale, String key){
        int startingPoint = i;
        int nextNote = 0;

        //builds the scale according to the major or minor patterns
        for(int j = i; j < (startingPoint + 7); j++){
            theScale[nextNote] = musicNotes[i];

            if (major[nextNote].equals("W"))
                i += 2;
            else
                i += 1;

            ++nextNote;
        }

        return theScale;
    }

    private String[] minorCreator(String scale, String key){
        int startingPoint = i;
        int nextNote = 0;

        //builds the scale according to the major or minor patterns
        for(int j = i; j < (startingPoint + 7); j++){
            theScale[nextNote] = musicNotes[i];

            if (minor[nextNote].equals("W"))
                i += 2;
            else
                i += 1;

            ++nextNote;
        }

        return theScale;
    }

    private String[] harmonicCreator(String scale, String key){
        int startingPoint = i;
        int nextNote = 0;

        //builds the scale according to the major or minor patterns
        for(int j = i; j < (startingPoint + 7); j++){
            theScale[nextNote] = musicNotes[i];

            if (harmonic[nextNote].equals("W"))
                i += 2;
            else if (harmonic[nextNote].equals("D"))
                i += 3;
            else
                i += 1;

            ++nextNote;
        }

        return theScale;
    }

    private String[] melodicCreator(String scale, String key){
        int startingPoint = i;
        int nextNote = 0;

        //builds the scale according to the major or minor patterns
        for(int j = i; j < (startingPoint + 7); j++){
            theScale[nextNote] = musicNotes[i];

            if (melodic[nextNote].equals("W"))
                i += 2;
            else
                i += 1;

            ++nextNote;
        }

        return theScale;
    }
}
