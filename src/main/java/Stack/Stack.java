package Stack;

public interface Stack<E> {
    // 获取栈的大小
    int getSize();
    // 判断栈是否为空
    boolean isEmpty();
    // 向栈中添加元素
    void push(E e);
    // 从栈顶取出元素
    E pop();
    // 查看栈顶的元素
    E peek();
}
