package Ex1;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions {
    ArrayList<function> ff = new ArrayList<function>();
    public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE,
            Color.red, Color.GREEN, Color.PINK};

    public Functions_GUI(String s){
        Polynom p1 = new Polynom();
        ff.add(p1.initFromString(s));

    }
    public Functions_GUI(){

    }


    @Override
    public void initFromFile(String file) throws IOException {
        ComplexFunction compFunc = null;
        String[] lines = file.split("\n");
        for(int i = 0; i<lines.length;i++){
            ff.add(compFunc.initFromString(lines[i]));        }

    }

    @Override
    public void saveToFile(String file) throws IOException {
        FileWriter save= new FileWriter("output.txt");
        for(int i = 0; i<ff.size();i++){
            save.write(ff.get(i).toString()+"\n");

        }

    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        int n = resolution;
        StdDraw.setCanvasSize(width, height);
        int size = ff.size();
        double[] x = new double[n+1];
        double[][] yy = new double[size][n+1];
        double x_step = (rx.get_max()-rx.get_min())/n;
        double x0 = rx.get_min();
        for (int i=0; i<=n; i++) {
            x[i] = x0;
            for(int a=0;a<size;a++) {

                yy[a][i] = ff.get(a).f(x[i]);
            }
            x0+=x_step;
        }
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        for(int a=0;a<size;a++) {
            int c = a%Colors.length;
            StdDraw.setPenColor(Colors[c]);

            System.out.println(a+") "+Colors[a]+"  f(x)= "+ff.get(a));
            for (int i = 0; i < n; i++) {

                StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
            }
        }

    }

    @Override
    public void drawFunctions(String json_file)  {
        JSONParser obj = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) obj.parse(json_file);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(Object key: json.keySet()){
            switch (json.get(key).toString()){
                case "Range_Y":

                case "Range_X":

                case "Height":

                case "Width":

                case "Resolution":

            }
            System.out.println(key);
            System.out.println(json.get(key));

        }


    }
    public function get(int index){
        return ff.get(index);
    }

    @Override
    public int size() {
        return ff.size();
    }

    @Override
    public boolean isEmpty() {
        return ff.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return ff.contains(o);
    }

    @Override
    public Iterator<function> iterator() {
        return ff.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(function function) {
        try{
            ff.add(function);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends function> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }
}
