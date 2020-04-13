package com.example.cs449_project;

class ScaleCreationAlgorithm {

    //an array of the available notes
    private String[] musicNotes = {"B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B",
            "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C"};

    //sets up the pattern for both the major and minor scales
    private String[] major = {"W", "W", "H", "W", "W", "W", "H"};
    private String[] minor = {"W", "H", "W", "W", "H", "W", "W"};

    String[] scaleCreator(String scale, String key){

        //this array will become the completed scale at the end
        String[] theScale = new String[7];

        boolean isMajor = false;

        if (scale.equals("Major")) {
            isMajor = true;
        }

        //gets the starting point
        int i = 0;
        while (!musicNotes[i].equals(key))
            ++i;


        int startingPoint = i;
        int nextNote = 0;

        //builds the scale according to the major or minor patterns
        for(int j = i; j < (startingPoint + 7); j++){
            theScale[nextNote] = musicNotes[i];

            if (isMajor){
                if (major[nextNote].equals("W"))
                    i += 2;
                else
                    i += 1;
            }

            else {
                if (minor[nextNote].equals("W"))
                    i += 2;
                else
                    i += 1;
            }

            ++nextNote;
        }

        return theScale;
    }
}
