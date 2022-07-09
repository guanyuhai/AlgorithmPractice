package HashCode;

public class Student
{

    int grade;
    int cls;
    String firstName;
    String lastName;

    Student(int grade, int cls, String firstName, String lastName){
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     如果我们想使得Student类能够哈希，我们必须实现hashCode()方法
     如果Student不实现hashCode()方法，依然可以运行程序，这是因为Object默认有一个hashCode()实现，
     这个hashCode()方法是根据地质来计算哈希值。
     */
    @Override
    public int hashCode(){
        int B = 31;//31，就是第二节所说的进制。这里素数M还没有取到，我们先不计算M（M代表哈希表数组的大小）
        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.toLowerCase().hashCode();//加toLowerCase()用于忽略大小写，大小写表示同一个人
        hash = hash * B + lastName.toLowerCase().hashCode();

        return hash;
    }

    /**
     如果不同的“键”映射到同一个哈希值，产生哈希冲突，这时就要真正的比较2个“键”的值，
     因此我们还需要实现equals()方法
     */
    @Override
    public boolean equals(Object o)
    {
        if(o == this)
            return true;//2个对象的引用相同，同一个Student

        if(o == null)
            return false;//空对象，直接返回false

        if(this.getClass() != o.getClass())
            return false;//2个对象的类对象不同，肯定不是同一个对象，返回false

        //以上判断都通过，那么就根据2个对象的各个属性是否相同来判断他们是不是同一个对象
        Student another = (Student)o;
        //这里，基本数据类型用“==” 边角是否相等，引用数据类型用 equals() 方法比较是否相等
        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());

    }
}


