package Lesson3;

import java.util.ArrayList;


public class Lesson3 {
    public static void main(String[] args) {
        Apple apple = new Apple();
        Orange orange = new Orange();

        Box<Apple> boxApple = new Box<Apple>();
        Box<Orange> boxOrange = new Box<Orange>();
        boxApple.addFruit(apple);
        boxApple.addFruit(apple);
        boxApple.addFruit(apple);
        boxOrange.addFruit(orange);
        boxOrange.addFruit(orange);
        boxOrange.addFruit(orange);
        boxOrange.addFruit(orange);
        boxOrange.addFruit(orange);

        boxOrange.getWeight();
        boxApple.compare(boxOrange);
        boxApple.moveTo(new Box<Apple>());
    }
}

class Apple extends Fruit{
    public Apple() {
        super.weight = 1f;
    }

}

class Orange extends Fruit{
    public Orange() {
        super.weight = 1.5f;
    }
}

class Fruit {
    float weight;
}

class Box<T>{
    float maxWeight = 3.5f;
    float currentWeight = 0;
    ArrayList<Fruit> arrayList = new ArrayList<>();

    void addFruit(Fruit fruit) {
        if (currentWeight + fruit.weight <= maxWeight) {
            arrayList.add(fruit);
            currentWeight += fruit.weight;
        } else
            System.out.println("The box is full");
    }

    float getWeight() {
        return arrayList.size() * arrayList.get(0).weight;
    }

    public boolean compare(Box box) {
        if (getWeight() == box.getWeight())
            return true;
        return false;
    }

    public Box<T> moveTo(Box<T> a) {
        for(Fruit item : arrayList) {
            a.arrayList.add(item);
        }

        arrayList.clear();
        currentWeight = 0;

        return a;
    }
}