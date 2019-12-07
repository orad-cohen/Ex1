package Ex1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions {
    ArrayList<function> ff = new ArrayList<function>();


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

    }

    @Override
    public void drawFunctions(String json_file) {

    }
    public function get(int index){
        return ff.get(index);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<function> iterator() {
        return null;
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
        return false;
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
