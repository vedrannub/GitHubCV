import java.util.*;


/*Vedran_Krstevski_152030 */


public class LZW_Algoritam {
    /**  Компресирање на текст во листа на симболи */
    public static List<Integer> kompresiraj(String nekompresiranTekst) {
        // Градење на речникот
        int goleminaNaRecnik = 256;
        Map<String,Integer> recnik = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++)
            recnik.put("" + (char)i, i);

        String w = "";
        List<Integer> rezultat = new ArrayList<Integer>();
        for (char c : nekompresiranTekst.toCharArray()) {
            String wc = w + c;
            if (recnik.containsKey(wc))
                w = wc;
            else {
                rezultat.add(recnik.get(w));
                // Додавање на wc во речникот
                recnik.put(wc, goleminaNaRecnik++);
                w = "" + c;
            }
        }

        // Output the code for w.
        if (!w.equals(""))
            rezultat.add(recnik.get(w));
        return rezultat;
    }

    /** Декомпресирање на листа на излез ks во текст */
    public static String dekompresiraj(List<Integer> kompresiranTekst) {
        // Градење на речникот
        int goleminaNaRecnik = 256;
        Map<Integer,String> recnik = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++)
            recnik.put(i, "" + (char)i);

        String w = "" + (char)(int)kompresiranTekst.remove(0);
        StringBuffer rezultat = new StringBuffer(w);
        for (int k : kompresiranTekst) {
            String entry;
            if (recnik.containsKey(k))
                entry = recnik.get(k);
            else if (k == goleminaNaRecnik)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Лошо компресиран k: " + k);

            rezultat.append(entry);

            // Додавање на w + влезот[0] во речникот

            recnik.put(goleminaNaRecnik++, w + entry.charAt(0));
            w = entry;
        }
        return rezultat.toString();
    }

    public static void main(String[] args) {

        List<Integer> kompresirano = kompresiraj("LABARATORISKALZWALGORITAM");  /*Внесете го текстот кој сакаде да се компресира */
        System.out.println(kompresirano);
        String dekompresirano = dekompresiraj(kompresirano);
        System.out.println(dekompresirano);
    }
}