package hu.gdf.oop.fogadoiroda.model;

public class SequenceGenerator {

    private static int SEQ = 0;

    public static synchronized Integer next() {
        SEQ++;
        return new Integer(SEQ);
    }
}
