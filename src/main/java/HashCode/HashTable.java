package HashCode;

import java.util.TreeMap;

/**
 注意，哈希表的键不需要具有比较性，但是他的键需要实现 hashCode() 与 equals() 方法，
 由于每一个键类型都会继承Object类，因此都继承Object的 hashCode() 与 equals() 方法，d
 当然，我们自己也可以重写自己需要的 hashCode() 与 equals() 方法。
 */
public class HashTable<K , V> {
    //首先，定义一个数组，数组内存储TreeMap类型的数据，然后TreeMap内存储的才是相应的键值数据
    private TreeMap<K,V>[] hashtable;
    private int M;//定义数组的长度，素数
    private int size;//数组内存储的键值对的个数

    //定义2个常量用于扩容操作，这两个常量表示每个位置的平均哈希冲突数量（即数组每个位置查找表的元素数量）
    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    //    private static final int initCapacity = 7;//初始化容量
    //我们使用一个素数表来定义哈希表数组的扩容与缩容，这样可以避免更多的哈希冲突
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private int capacityIndex = 0;//用于获取capacity数组的值，初始为0


    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];//初始化数组的长度
        //对于数组内的每一个元素，都是TreeMap对象，我们需要初始化这些对象
        for (int i = 0; i < M ; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

//    public HashTable()
//    {
//        this(initCapacity);//默认使用97作为素数
//    }

    public int getSize() {
        return size;
    }

    //辅助方法，使用这个哈希方法，将传入的 key值 转换为 hashtable 数组内的索引
    private int hash(K key)//注意，这个key可以是任意类型
    {
        //先利用key的哈希方法，将key转换为整数，再将整数转换为正数，随后除以M获得其在哈希数组内的索引
        return (key.hashCode() & 0x7fffffff) % M;
    }

    //添加方法
    public void add(K key , V value) {
        //先求得key在数组内的位置 hash(key)，并找到这个位置的TreeMap
        TreeMap<K, V> map = hashtable[hash(key)];
        /**
         如果这个位置的 TreeMap 已经原来已经存储了这个key，那么我们将这个key的value值直接覆盖原来key的value
         如果这个位置的 TreeMap 已经原来没有存储这个key，我们将这个键值对存储进去即可。
         如果有多个不同的 key 的哈希值都是这个位置的值，我们将这些哈希值相同的 key 存储到同一个位置的TreeMap中即可
         */
        if(map.containsKey(key)) {
            map.put(key , value);//覆盖value
        } else {
            map.put(key , value);//添加键值对
            size++;

            /** 当哈希表每个位置的元素数量（哈希冲突）大于 upperTol 的时候，扩容数组 */
            //if(size/M >= upperTol)
            //将乘法转换为除法，避免浮点型与整形的转换.
            //注意capacity数组不能越界
            if(size >= upperTol*M && capacityIndex+1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    //删除
    public V remove(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if(map.containsKey(key)) {
            ret = map.remove(key);
            size--;
            //控制缩容过程中哈希表数组的长度大于初始的容量
            if(size < lowerTol*M && capacityIndex-1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    //设置键的值
    public void set(K key , V  value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if(!map.containsKey(key))
            throw new IllegalArgumentException(key + " doesn't exist!");
        map.put(key , value);
    }

    //查询是否包含某个键
    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    //获取某个键的值
    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

    //扩容方法
    private void resize(int newM) {
        //重新创建一个 TreeMap类型的数组
        TreeMap<K,V>[] newHashTable = new TreeMap[newM];
        //初始化新的 TreeMap 数组每一个位置的TreeMap
        for (int i = 0; i < newM ; i++)
            newHashTable[i] = new TreeMap<>();

        /** 下面，将原来 TreeMap 数组中的元素存储到新的数组中
         注意，下面 newHashTable[hash(key)].put(key , map.get(key)) 中，hash(key) 方法中使用到M，而这里使用的M是我们类中
         定义的M，但是事实上，这里newHashTable应该使用新传入的 newM，使用旧的M会使得获取到新的哈希表数组的所有不均匀。
         那么我们应该事先更新类的M为newM.
         下面遍历中药使用到旧的M，因为将原来 hashtable 中的元素转换为新的 newHashTable中，
         原来 hashtable 的长度就是旧的M，因此需要将旧的M保存起来
         */
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM ; i++) {
            TreeMap<K, V> map = hashtable[i];//先取出老的 TreeMap 数组 hashtable 每一个位置的TreeMap
            //取出TreeMap中的元素，存储到新的TreeMap数组 newHashTable 相应的位置
            for (K key : map.keySet())
                newHashTable[hash(key)].put(key , map.get(key));
        }

        this.hashtable = newHashTable;//将原来的hashtable更新为新的newHashTable
    }

}


