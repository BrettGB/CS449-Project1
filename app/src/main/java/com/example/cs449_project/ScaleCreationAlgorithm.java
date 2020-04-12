package com.example.cs449_project;

class ScaleCreationAlgorithm {

    private String[] musicNotes = {"B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B",
            "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C"};

    private String[] major = {"W", "W", "H", "W", "W", "W", "H"};

    private String[] minor = {"W", "H", "W", "W", "H", "W", "W"};

    String[] scaleCreator(String scale, String key){

        //want to iterate through musicNotes until you get the key
        //make a new array that starts at the key and goes until the key again

        String[] theScale = new String[7];

        boolean isMajor = false;

        if (scale.equals("Major")) {
            isMajor = true;
        }


        int i = 0;
        while (!musicNotes[i].equals(key))
            ++i;


        int startingPoint = i;
        int nextNote = 0;

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
