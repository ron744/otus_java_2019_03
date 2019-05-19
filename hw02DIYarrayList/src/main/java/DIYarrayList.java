import java.util.*;

public class DIYarrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROW_SIZE = DEFAULT_CAPACITY * 2 + 1;
    private int size = 0;
    private T[] values;

    public DIYarrayList(){
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
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
                return currentIndex < size;
            }

            public T next() {

                /*if (currentIndex < size){
                    return values[currentIndex++];
                }*/
                if (currentIndex >= size){
                    throw new UnsupportedOperationException();
                }
                return  values[currentIndex++];

            }
        };
    }

    public Object[] toArray() {
        return Arrays.copyOf(values, size);
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    public boolean add(T t) {
        try{
            if (values.length <= size){
                T[] temp = values;
                values = (T[]) new Object[temp.length + GROW_SIZE];
                System.arraycopy(temp, 0, values, 0, temp.length);
            }
            values[size] = t;
            size++;
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
        /*if ((index >= size) && (index < 0)){
            throw new ArrayIndexOutOfBoundsException();
        }*/
        check(index);
        return values[index];
    }

    public T set(int index, T element) {
        /*if ((index >= size)&& (index < 0)){
            throw new ArrayIndexOutOfBoundsException();
        }*/
        check(index);
        return values[index] = element;
    }

    private void check(int index){
        if ((index >= size)&& (index < 0)){
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

                if (currentIndex < size){
                    return values[currentIndex++];
                }else{
                    throw new UnsupportedOperationException();
                }
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
