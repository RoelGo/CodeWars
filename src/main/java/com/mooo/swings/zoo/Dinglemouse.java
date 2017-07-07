package com.mooo.swings.zoo;

import java.util.*;

public class Dinglemouse {

    public static String[] whoEatsWho(final String zoo) {
        String[] foodStrings = zoo.split(",");
        List<Food> foods = foodStringsToList(foodStrings);
        return startFoodFest(zoo,foods);
    }

    private static String[] startFoodFest(String zoo, List<Food> foods) {
        List<String> strings = new ArrayList<>();
        strings.add(zoo);
        for (int foodIndex = 0; foodIndex < foods.size(); foodIndex++) {
            Food leftFood;

            Food rightFood;
            try{
                leftFood = foods.get(foodIndex - 1);
            } catch (ArrayIndexOutOfBoundsException e){
                leftFood = new Nothing();
            }
            try{
                rightFood = foods.get(foodIndex + 1);
            } catch (IndexOutOfBoundsException e){
                rightFood = new Nothing();
            }
            String result = foods.get(foodIndex).eat(leftFood, rightFood);
            Food whoGotEaten = whoWasEaten(result);
            if (!whoGotEaten.equals(new Nothing())) {

                if (whoGotEaten.equals(leftFood)){
                    foods.remove(foodIndex -1 );
                } else {
                    foods.remove(foodIndex + 1 );
                }

                strings.add(result);
                foodIndex = -1;
            }
        }
        StringBuilder result = new StringBuilder();
        for (Food food : foods) {
            result.append(food.getName()).append(",");
        }
        result.deleteCharAt(result.length()-1);
        strings.add(result.toString());
        Object[] objects = strings.toArray();
        return Arrays.copyOf(objects, objects.length, String[].class);
    }

    private static Food whoWasEaten(String result) {
        return Food.FOOD_REPOSITORY.get(result.split(" eats ")[1]);
    }

    private static List<Food> foodStringsToList(String... foodStrings) {
        List<Food> foods = new ArrayList<>();
        for (String foodString : foodStrings) {
            Food food = Food.FOOD_REPOSITORY.get(foodString);
            if (food == null){
                food = new Unknown(foodString);
            }
            foods.add(food);
        }
        return foods;
    }

}

abstract class Food {

    public final static Map<String, Food> FOOD_REPOSITORY = setupRepo();

    private static Map<String, Food> setupRepo() {
        HashMap<String, Food> stringFoodHashMap = new HashMap<>();
        stringFoodHashMap.put("bear", new Bear());
        stringFoodHashMap.put("bug", new Bug());
        stringFoodHashMap.put("chicken", new Chicken());
        stringFoodHashMap.put("fox", new Fox());
        stringFoodHashMap.put("giraffe", new Giraffe());
        stringFoodHashMap.put("antelope", new Antelope());
        stringFoodHashMap.put("lion", new Lion());
        stringFoodHashMap.put("cow", new Cow());
        stringFoodHashMap.put("panda", new Panda());
        stringFoodHashMap.put("leaves", new Leaves());
        stringFoodHashMap.put("sheep", new Sheep());
        stringFoodHashMap.put("grass", new Grass());
        stringFoodHashMap.put("little-fish", new LittleFish());
        stringFoodHashMap.put("big-fish", new BigFish());
        stringFoodHashMap.put("nothing", new Nothing());
        return stringFoodHashMap;
    }

    private String name;
    private List<Food> diet;

    Food(String name, Food... diet) {
        this.name = name;
        this.diet = Arrays.asList(diet);
    }

    private boolean eats(Food food) {
        return diet.contains(food);
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

class Bear extends Food {
    Bear() {
        super("bear", new BigFish(),
                new Bug(),
                new Chicken(),
                new Cow(),
                new Leaves(),
                new Sheep());
    }
}

class Bug extends Food {
    Bug() {
        super("bug",
                new Leaves());
    }
}

class Chicken extends Food {
    Chicken() {
        super("chicken",
                new Bug());
    }
}

class Fox extends Food {
    Fox() {
        super("fox",
                new Chicken(),
                new Sheep());
    }
}

class Giraffe extends Food {
    Giraffe() {
        super("giraffe",
                new Leaves());
    }
}

class Antelope extends Food {
    Antelope() {
        super("antelope",
                new Grass());
    }
}

class Lion extends Food {
    Lion() {
        super("lion",
                new Antelope(),
                new Cow());
    }
}

class Cow extends Food {
    Cow() {
        super("cow",
                new Grass());
    }
}

class Panda extends Food {
    Panda() {
        super("panda",
                new Leaves());
    }
}

class Leaves extends Food {
    Leaves() {
        super("leaves");
    }
}

class Sheep extends Food {
    Sheep() {
        super("sheep",
                new Grass());
    }
}

class Grass extends Food {
    Grass() {
        super("grass");
    }
}

class LittleFish extends Food {
    LittleFish() {
        super("little-fish");
    }
}

class BigFish extends Food {
    BigFish() {
        super("big-fish",
                new LittleFish());
    }
}
class Nothing extends Food {
    Nothing() {
        super("nothing");
    }
}class Unknown extends Food {
    Unknown(String name) {
        super(name);
    }
}
