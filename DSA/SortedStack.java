import java.util.*;
public class SortedStack<T extends Comparable<T>> extends Stack<T>{

    public T push(T data){
        Stack<T> temp = new Stack<T>();
        while(!this.isEmpty()&& data.compareTo(this.peek())>0){
            temp.push(this.pop());
        }
        super.push(data);
        while(!temp.isEmpty()){
            super.push(temp.pop());
        }
        return this.peek();
    }
    public static void main(String[] args){
        SortedStack<Double> intStack = new SortedStack<>();
        intStack.push(5.0);intStack.push(10.42);intStack.push(1.23);
        SortedStack<String> strStack = new SortedStack<>();
        strStack.push("Aa");strStack.push("HK");strStack.push("Bharat");
        System.out.println(intStack.toString());
        System.out.println(strStack.toString());
    }
}