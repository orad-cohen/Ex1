package Ex1;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions {
    private ArrayList<function> functionsArray = new ArrayList<function>();
    public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE,
            Color.red, Color.GREEN, Color.PINK};



    @Override
    public void initFromFile(String file) throws IOException {
        ArrayList<function> fx = new ArrayList<>();
        functionsArray = fx;
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line;
        ComplexFunction compFunc = new ComplexFunction();
        while((line = reader.readLine())!=null){
            functionsArray.add(compFunc.initFromString(line));
        }
        fr.close();
        reader.close();

    }

    @Override
    public void saveToFile(String file) throws IOException {
        FileWriter save= new FileWriter(file);
        for(int i = 0; i<functionsArray.size();i++){
            save.write(functionsArray.get(i).toString()+"\n");

        }
        save.flush();
        save.close();

    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        int n = resolution;
        StdDraw.setCanvasSize(width, height);//starts the drawing.
        int size = functionsArray.size();
        double[] x = new double[n+1];
        double[][] yy = new double[size][n+1];
        double x_step = (rx.get_max()-rx.get_min())/n;
        double x0 = rx.get_min();
        //Create the array of values
        for (int i=0; i<=n; i++) {
            x[i] = x0;
            for(int a=0;a<size;a++) {

                yy[a][i] = functionsArray.get(a).f(x[i]);
            }
            x0+=x_step;
        }
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        //Draw grid and xy axis.
        int x1 = (int)rx.get_min();
        int y1 = (int)ry.get_min();
        Font font = new Font(StdDraw.getFont().toString(), Font.BOLD, 14 );
        StdDraw.setFont(font);
        for(int p = x1; p<rx.get_max();p++){
            StdDraw.setPenColor(Color.gray);
            StdDraw.line(p,ry.get_min(),p,ry.get_max());
            StdDraw.setPenColor(Color.black);
            if(p==0){continue;}
            StdDraw.text(p, 0.3, ""+p);
        }
        for(int p = y1; p<ry.get_max();p++){
            StdDraw.setPenColor(Color.gray);
            StdDraw.line(rx.get_min(), p,rx.get_max() ,p );
            StdDraw.setPenColor(Color.black);
            StdDraw.text(0.3, p+0.1, ""+p);
        }
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(0.004);
        StdDraw.line(0, ry.get_min(), 0,ry.get_max());
        StdDraw.line(rx.get_min(), 0, ry.get_max(), 0);
        StdDraw.setPenRadius();
        //Draw the functions.
        for(int a=0;a<size;a++) {
            int c = a%Colors.length;
            StdDraw.setPenColor(Colors[c]);

            System.out.println(a+") "+Colors[c]+"  f(x)= "+functionsArray.get(a));
            for (int i = 0; i < n; i++) {

                StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
            }
        }




    }

    @Override
    public void drawFunctions(String json_file)  {//reads from json file, if doesnt exist, read from default gui param.
        Object obj;
        try{
            JSONParser jp = new JSONParser();
            FileReader fr = new FileReader(json_file);
            obj = jp.parse(fr);
            JSONObject jo = (JSONObject) obj;
            JSONArray ry = (JSONArray) jo.get("Range_Y");
            JSONArray rx = (JSONArray) jo.get("Range_X");
            int width = Integer.parseInt(jo.get("Width").toString());
            int height = Integer.parseInt(jo.get("Height").toString());
            int res = Integer.parseInt(jo.get("Resolution").toString());
            double y1 = Double.parseDouble(ry.get(0).toString());
            double y2 = Double.parseDouble(ry.get(1).toString());
            double x1 = Double.parseDouble(rx.get(0).toString());
            double x2 = Double.parseDouble(rx.get(1).toString());
            Range yy = new Range(y1, y2);
            Range xx = new Range(x1, x2);
            drawFunctions(width, height, xx, yy, res);

        }
        catch (Exception e){
            Range ry = new Range(-5,15);
            Range rx = new Range(-10,10);
            drawFunctions(1000,600,rx,ry,200);
        }

    }

    public function get(int index){
        return functionsArray.get(index);
    }

    @Override
    public int size() {
        return functionsArray.size();
    }

    @Override
    public boolean isEmpty() {
        return functionsArray.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return functionsArray.contains(o);
    }

    @Override
    public Iterator<function> iterator() {
        return functionsArray.iterator();
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
            functionsArray.add(function);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean remove(Object o) {
        return functionsArray.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return functionsArray.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends function> collection) {
        return functionsArray.addAll(collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return functionsArray.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return functionsArray.retainAll(collection);
    }

    @Override
    public void clear() {
        functionsArray.clear();

    }
}
