package Graph.chapter11;

/**
 * @description: 带权图的边
 * @author: wtx
 * @createDate: 2020/6/6
 */
public class WeightedEdge implements Comparable<WeightedEdge>{

    private Integer v, w;
    private Integer weight;

    public Integer getV() {
        return v;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getW() {
        return w;
    }

    public WeightedEdge(Integer v, Integer w, Integer weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "WeightedEdge {" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(WeightedEdge another) {
        return this.weight - another.weight;
    }
}
