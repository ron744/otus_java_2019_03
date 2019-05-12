import java.util.*;

public class DIYarrayList<T> implements List<T> {

    private T[] values;

    public DIYarrayList(){
        values = (T[]) new Object[10];
    }

    public int size() {
        return values.length;
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public boolean hasNext() {
                return currentIndex < values.length;
            }

            public T next() {
                try {
                    return values[currentIndex++];
                }catch(Exception e){
                    throw new ArrayIndexOutOfBoundsException();
                }
            }
        };
    }

    public Object[] toArray() {
        return Arrays.copyOf(values, values.length);
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    public boolean add(T t) {
        try{
            for(int i = 0; i < values.length; i++){
                if(values[i] == null){
                    values[i] = t;
                    return true;
                }
            }
                T[] temp = values;
                values = (T[]) new Object[temp.length + 10];
                System.arraycopy(temp, 0, values, 0, temp.length);
                values[values.length - 11] = t;
                return true;


        }catch(ClassCastException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public T get(int index) {
        try {
            return values[index];
        }catch(Exception e){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public T set(int index, T element) {
        try {
            return values[index] = element;
        }catch(Exception e){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return values[currentIndex++];
            }

            @Override
            public boolean hasPrevious() {
                throw new UnsupportedOperationException();
            }

            @Override
            public T previous() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(T t) {
                values[currentIndex - 1] = t;
            }

            @Override
            public void add(T t) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
