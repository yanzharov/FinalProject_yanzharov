package by.tr.op.service.scrambler;

public class HashScrambler {
    private static final HashScrambler instance=new HashScrambler();
    
    private HashScrambler() {
    }
    
    public int encrypt(char[] input){
        int hash = 3;
        for(int i=0;i<input.length;i++){  
            hash = 47 * hash + input[i];
        }
        return hash;
    }

    public static HashScrambler getInstance() {
        return instance;
    }
    
}
