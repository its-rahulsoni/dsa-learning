package com.dsa.learning.preparation_2023.datastructures.graph.adjacencylist;

/**
 * The above definition of vertex just features a label, but this can represent any possible entity such as Person or City.
 */
public class Vertex {

    String label;

    public Vertex(String label){
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Vertex)) {
            return false;
        }
        Vertex other = (Vertex) o;
        return this.label.equals(other.label);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + label.hashCode();
        return result;
    }

    public String getLabel() {
        return label;
    }


    /**
 * In this example, the Person class has two instance variables - name and age. The equals() method compares the name and age fields of two Person objects to determine if they are equal. The hashCode() method generates a hash code based on the name and age fields, using the formula 31 * hash + field.hashCode().
 *
 * It's important to note that whenever you override equals(), you must also override hashCode(), as they are used together to determine object equality. In this example, the hashCode() method uses the same fields as equals() to calculate the hash code, ensuring that two Person objects that are equal will also have the same hash code.
 */

}
