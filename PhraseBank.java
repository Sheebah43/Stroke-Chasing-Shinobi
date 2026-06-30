package com.shinobi;

public class PhraseBank {

    public static final String[] PHRASES = {
        "shadow stealth infiltration",
        "silent moon rising",
        "hidden leaf village",
        "swift as the wind",
        "move unseen strike quickly",
        "master the hidden blade",
        "become one with darkness",
        "the scroll awaits",
        "climb without hesitation",
        "shinobi never retreat"
    };

    /*with static
        write PhraseBank.randomPhrase();
        instead of PhraseBank bank = new PhraseBank();
                    bank.randomPhrase();
     */
    public static String randomPhrase() {
        int index = (int) (Math.random() * PHRASES.length);
        /* Math.random() gives random variable between 0 to 1 and 
        we multiply it with the length to get actual random values instead of just 0.sthsth getting typecasted to integer which will then return only 0
        when we typecast it the decimal value becomes integer and 
        gives us a whole number between 0 to 9 because the total number of strings is 10    `
         */
        return PHRASES[index];
    }
}

