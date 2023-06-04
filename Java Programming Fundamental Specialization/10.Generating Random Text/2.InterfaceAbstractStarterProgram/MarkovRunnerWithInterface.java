
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov(int seed) {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        MarkovZero mz = new MarkovZero();

        runModel(mz, st, size,seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size,seed);
        
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size,seed);
        
        MarkovFour mFour = new MarkovFour();
        mFour.setRandom(seed);
        runModel(mFour, st, size,seed);

    }

    public void testHashMap(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');

        EfficientMarkovModel emm = new EfficientMarkovModel(5);

        runModel(emm,st,50,531);
        
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        
        
        EfficientMarkovModel emm = new EfficientMarkovModel(2);
        MarkovModel mTwo = new MarkovModel(2);
        
        long startTime = System.nanoTime();
        runModel(emm, st, size,42);
        
        System.out.println("***********************************************");
        long runTime = System.nanoTime() - startTime;
        System.out.println("Run time is: " + runTime);
        System.out.println("***********************************************");
        
        runModel(mTwo, st, size,42);
        System.out.println("***********************************************");
        runTime = System.nanoTime() -startTime - runTime;
        System.out.println("Run time is: " + runTime);
    
    }
    
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
}
