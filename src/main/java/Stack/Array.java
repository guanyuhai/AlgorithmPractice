package Stack;

public class Array<E> {
    private E[] data;
    // 数组中有效元素的大小
    private int size;

    /**
     * 构造函数，传入数组的容量capacity构造Array
     * @param capacity
     */
    public Array(int capacity){
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参的构造函数，默认数组容量capacity为10
     */
    public Array(){
        this(10);
    }

    /**
     * 有参构造函数，传入一个静态数组
     * @param arr
     */
    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        //data = arr.clone();
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    /**
     * 获取数组中的元素个数
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 数组是否为空
     * @return
     */
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * 向所有元素后添加一个元素
     * @param e
     */
    public void addLast(E e) {
        // 检查数组是否还有剩余容量添加元素
        /*if (size==data.length)
            throw new IllegalArgumentException("AddLast failed. stack.Array is full.");
        data[size] = e;
        size++;*/

        // addLast可以复用add方法
        add(size,e);
    }

    /**
     * 在所有的元素前添加一个元素
     * @param e
     */
    public void addFirst(E e) {
        add(0,e);
    }

    /**
     * 在index的位置插入一个新元素e
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        /*if (size==data.length)
            throw new IllegalArgumentException("AddLast failed. stack.Array is full.");*/
        if (index < 0 || index > size)
            throw new IllegalArgumentException("AddLast failed. Require index >= 0 and index =< size.");
        if (size==data.length)
            resize(2 * data.length);
        // 将index及之后的元素向后移动
        for (int i = size -1 ; i >= index ; i--) {
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegl.");
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    /**
     * 获取index索引位置的元素
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Require index >= 0 and index < size.");
        return data[index];
    }

    /**
     * x修改index索引位置的元素为e
     * @param index
     * @param e
     */
    public void set(int index,E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Set failed. Require index >= 0 and index < size.");
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找元素e在数组中的索引位置，如果不存在元素e，则返回-1
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public String findAll(E e) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                str.append(i);
                str.append(", ");
            }
        }
        if (str.length()!=0) return str.toString();
        return "-1";
    }

    /**
     * 删除指定index索引位置的元素，返回删除的元素
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Remove failed. Require index >= 0 and index < size.");
        E ret = data[index];

        for (int i = index+1; i < size; i++) {
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;

        // 缩减容量，只有减少到原来的1/4才缩容，避免复杂度震荡的情况
        if (size == data.length/4 && data.length/2 != 0)
            resize(data.length/2);
        return ret;
    }

    /**
     * 从数组中删除掉第一个元素，返回删除的元素
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 从数组中删除掉最后一个元素，返回删除的元素
     * @return
     */
    public E removeLast() {
        return remove(size-1);
    }

    /**
     * 从数组中删除元素e，如果存在多个相同元素e，只删除第一个
     * @param e
     */
    public boolean removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 删除数组中所有的元素e
     * @param e
     */
    public void removeAllElement(E e) {
        do {
            int index = find(e);
            if (index != -1)
                remove(index);
        } while (find(e) != -1);
    }

    public E getLast() {
        return get(size-1);
    }

    public E getFirst() {
        return get(0);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("stack.Array: size = %d, capacity = %d\n",size,data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size-1)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
