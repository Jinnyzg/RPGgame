import java.util.ArrayList;

/**
 * this layoutManager class manages to put multiple view component in a background and display the background
 */

public class LayoutManager {
    private ArrayList<String> background;

    public LayoutManager() {
        setBackground(createBackground(120,20));
    }

    public ArrayList<String> getBackground() {
        return background;
    }

    public void setBackground(ArrayList<String> background) {
        this.background = background;
    }

    public ArrayList<String> createBackground(int w, int h) {
        ArrayList<String> res = new ArrayList<>();
        String boardx =  "+ ".repeat(w / 2 - 1);
        String boardy = "|" + "  ".repeat(w / 2 - 2)+ "|";
        res.add(boardx);
        for (int i = 0; i < h; i++) {
            res.add(boardy);
        }
        res.add(boardx);
        return res;
    }

    public void display(String color){
        String output = "";
        for(String s: this.getBackground() ){
            output += color + s + color +"\n";
        }
        System.out.println(output);
    }

    public static void display(ArrayList<String> infos, String color){
        String output = "";
        for(String s:infos ){
            output += color + s + color +"\n";
        }
        System.out.println(output);
    }

    public void addComponent(int startx,int starty, ArrayList<String> component){
        int len = component.size();
        for(int i = 0; i < len;i++){
            String curLine = component.get(i);
            String s = this.background.get(startx+i);
            String newString = s.substring(0,starty) + curLine + s.substring(starty+ curLine.length());
            getBackground().set(startx+i, newString);
        }
    }

    public void addViewTitle(ArrayList<String> component){
        addComponent(1,2,component);
    }

}
