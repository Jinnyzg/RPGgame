import java.util.ArrayList;

/**
 * this Class generate the component that can be added to the background and displayed by LayoutManager
 */
public class ViewComponent {
    public static ArrayList<String> viewTitle(String title) {
        ArrayList<String> res = new ArrayList<>();
        res.add("{----" + title + "----}");
        return res;
    }

    public static ArrayList<String> hotKeyBlock() {
        String[] strings = new String[]{"W/w: move up",
                "A/a: move left",
                "S/s: move down",
                "D/d: move right",
                "Q/q: quit game",
                "I/i: show information"};
        return createListView(strings);
    }

    public static ArrayList<String> loggerBoard(ArrayList<String> info, int i) {
        ArrayList<String> res = new ArrayList<>();
        int w = 0;
        for (String s : info) {
            if (w < s.length()) {
                w = s.length();
            }
        }
        while(info.size() > i){
            info.remove(0);
        }
        while(info.size() < i){
            info.add(" ");
        }
        String boardx = "-".repeat(w + 1);
        res.add("+".repeat(w + 1));
        res.add("-> Logger");
        res.add(boardx);
        for (String s : info) {
            int blankNum = w - s.length()+1;
            res.add(s + " ".repeat(blankNum));
        }
        res.add(boardx);
        return res;
    }

    public static ArrayList<String> createListView(ArrayList<String> info) {
        ArrayList<String> res = new ArrayList<>();
        int w = 0;
        for (String s : info) {
            if (w < s.length()) {
                w = s.length();
            }
        }
        String boardx = "-".repeat(w + 4);
        res.add(boardx);
        int i = 1;
        for (String s : info) {
            int blankNum = w - s.length();
            res.add("|" + i + "->" + s + " ".repeat(blankNum) + "|");
            i++;
        }
        res.add(boardx);
        return res;
    }

    public static ArrayList<String> createListView(String[] info) {
        ArrayList<String> res = new ArrayList<>();
        int w = 0;
        for (String s : info) {
            if (w < s.length()) {
                w = s.length();
            }
        }
        String boardx = "-".repeat(w + 4);
        res.add(boardx);
        int i = 1;
        for (String s : info) {
            int blankNum = w - s.length();
            res.add("|" + i + "->" + s + " ".repeat(blankNum) + "|" );
            i++;
        }
        res.add(boardx);
        return res;
    }

    public static ArrayList<String> createSheetView(String title, String[] keys, String[] values) {
        ArrayList<String> res = new ArrayList<>();
        int wKey = 0;
        for (String s : keys) {
            if (wKey < s.length()) {
                wKey = s.length();
            }
        }
        int wValue = 0;
        for (String s : values) {
            if (wValue < s.length()) {
                wValue = s.length();
            }
        }
        int maxlen = Math.max(title.length(), wKey + wValue);
        String boardx = "--".repeat(maxlen - 1);
        res.add(title);
        res.add(boardx);
        for (int i = 0; i < keys.length; i++) {
            String line = "";
            int keyBlankNum = wKey - keys[i].length();
            line += "|" + keys[i] + " ".repeat(keyBlankNum) + "|";
            int valueBlankNum = maxlen - wKey - values[i].length();
            line += values[i] + " ".repeat(valueBlankNum) + "|";
            res.add(line);
        }
        res.add(boardx);
        return res;
    }

    public static ArrayList<String> createStringView(String info){
        ArrayList<String> res = new ArrayList<>();
        res.add("->" + info);
        return res;
    }

    public static ArrayList<String> createInfoBlock(String[] info){
        ArrayList<String> res = new ArrayList<>();
        int w = 0;
        for (String s : info) {
            if (w < s.length()) {
                w = s.length();
            }
        }
        String boardx = "-".repeat(w + 2);
        res.add(boardx);
        int i = 1;
        for (String s : info) {
            int blankNum = w - s.length();
            res.add("|" + s + " ".repeat(blankNum) + "|");
            i++;
        }
        res.add(boardx);
        return res;
    }
}
