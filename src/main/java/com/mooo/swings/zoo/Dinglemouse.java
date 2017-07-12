package com.mooo.swings.zoo;

import java.util.*;

import static java.util.Collections.singletonList;

public class Dinglemouse {

    public static String[] whoEatsWho(final String zooString) {
        Zoo zoo = new Zoo(zooString);
        return zoo.startFoodFest();
    }

}

class Zoo {

    private final String zoo;
    private List<Food> foods;

    Zoo(String zoo) {
        this.zoo = zoo;
        this.foods = zooToFoodList(zoo);
    }

    String[] startFoodFest() {
        List<String> output = initializeStringsWithZoo();
        foodFest(output);
        addFinalZooState(output);
        return output.toArray(new String[0]);
    }

    private ArrayList<String> initializeStringsWithZoo() {
        return new ArrayList<>(singletonList(zoo));
    }

    private void foodFest(List<String> strings) {
        for (int foodIndex = 0; foodIndex < foods.size(); foodIndex++) {
            Food[] foodToEat = getFoodToEat(foodIndex);
            Food eater = foods.get(foodIndex);
            String result = eater.eat(foodToEat[0], foodToEat[1]);
            if (removeEatenFood(result, foodToEat, foodIndex)) {
                foodIndex = -1;
                strings.add(result);
            }
        }
    }

    private void addFinalZooState(List<String> strings) {
        StringBuilder result = new StringBuilder();
        for (Food food : foods) {
            result.append(food.getName()).append(",");
        }
        result.deleteCharAt(result.length() - 1);
        strings.add(result.toString());
    }

    private boolean removeEatenFood(String result, Object[] foodToEat, int foodIndex) {
        boolean somethingGotEaten = false;
        Food whoGotEaten = whoWasEaten(result);
        if (!whoGotEaten.equals(new Food("nothing"))) {
            if (whoGotEaten.equals(foodToEat[0])) {
                foods.remove(foodIndex - 1);
            } else {
                foods.remove(foodIndex + 1);
            }
            somethingGotEaten = true;
        }
        return somethingGotEaten;
    }

    private Food[] getFoodToEat(int foodIndex) {
        Food leftFood;
        Food rightFood;
        try {
            leftFood = foods.get(foodIndex - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            leftFood = new Food("nothing");
        }
        try {
            rightFood = foods.get(foodIndex + 1);
        } catch (IndexOutOfBoundsException e) {
            rightFood = new Food("nothing");
        }
        return new Food[]{leftFood, rightFood};
    }

    private Food whoWasEaten(String result) {
        return getFoodFromRepo(result.split(" eats ")[1]);
    }

    private List<Food> zooToFoodList(String zoo) {
        String[] foodStrings = zoo.split(",");
        List<Food> foods = new ArrayList<>();
        for (String foodString : foodStrings) {
            foods.add(getFoodFromRepo(foodString));
        }
        return foods;
    }

    private Food getFoodFromRepo(String foodString) {
        Food food = Food.FOOD_REPOSITORY.get(foodString);
        if (food == null){
            return new Food(foodString);
        }
        return food;
    }
}

class Food {

    public final static Map<String, Food> FOOD_REPOSITORY = setupRepo();
    private String name;
    private List<String> diet;

    Food(String name, String... diet) {
        this.name = name;
        this.diet = Arrays.asList(diet);
    }

    private static Map<String, Food> setupRepo() {
        HashMap<String, Food> stringFoodHashMap = new HashMap<>();
        stringFoodHashMap.put("bear", new Food("bear", "big-fish", "bug", "chicken", "cow", "leaves", "sheep"));
        stringFoodHashMap.put("bug", new Food("bug", "leaves"));
        stringFoodHashMap.put("chicken", new Food("chicken", "bug"));
        stringFoodHashMap.put("fox", new Food("fox", "chicken", "sheep"));
        stringFoodHashMap.put("giraffe", new Food("giraffe", "leaves"));
        stringFoodHashMap.put("antelope", new Food("antelope", "grass"));
        stringFoodHashMap.put("lion", new Food("lion", "antelope", "cow"));
        stringFoodHashMap.put("cow", new Food("cow", "grass"));
        stringFoodHashMap.put("panda", new Food("panda", "leaves"));
        stringFoodHashMap.put("leaves", new Food("leaves"));
        stringFoodHashMap.put("sheep", new Food("sheep", "grass"));
        stringFoodHashMap.put("grass", new Food("grass"));
        stringFoodHashMap.put("little-fish", new Food("little-fish"));
        stringFoodHashMap.put("big-fish", new Food("big-fish", "little-fish"));
        return stringFoodHashMap;
    }

    private boolean eats(Food food) {
        return diet.contains(food.name);
    }

    String eat(Food leftFood, Food rightFood) {
        if (this.eats(leftFood)) {
            return this.name + " eats " + leftFood.name;
        }
        if (this.eats(rightFood)) {
            return this.name + " eats " + rightFood.name;
        }
        return this.name + " eats nothing";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        return name != null ? name.equals(food.name) : food.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public String getName() {
        return name;
    }
}