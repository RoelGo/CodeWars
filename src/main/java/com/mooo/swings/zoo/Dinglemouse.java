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

        List<String> strings = initializeStringsWithZoo();
        foodFest(strings);
        addFinalZooState(strings);

        return listToStringArray(strings);
    }

    private ArrayList<String> initializeStringsWithZoo() {
        return new ArrayList<>(singletonList(zoo));
    }

    private String[] listToStringArray(List<String> strings) {
        Object[] objects = strings.toArray();
        return Arrays.copyOf(objects, objects.length, String[].class);
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
        if (!whoGotEaten.equals(new Nothing())) {
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
            leftFood = new Nothing();
        }
        try {
            rightFood = foods.get(foodIndex + 1);
        } catch (IndexOutOfBoundsException e) {
            rightFood = new Nothing();
        }
        return new Food[]{leftFood, rightFood};
    }

    private Food whoWasEaten(String result) {
        return Food.FOOD_REPOSITORY.get(result.split(" eats ")[1]);
    }

    private List<Food> zooToFoodList(String zoo) {
        String[] foodStrings = zoo.split(",");
        List<Food> foods = new ArrayList<>();
        for (String foodString : foodStrings) {
            Food food = Food.FOOD_REPOSITORY.get(foodString);
            if (food == null) {
                food = new Unknown(foodString);
            }
            foods.add(food);
        }
        return foods;
    }
}

abstract class Food {

    public final static Map<String, Food> FOOD_REPOSITORY = setupRepo();
    private String name;
    private List<Food> diet;

    Food(String name, Food... diet) {
        this.name = name;
        this.diet = Arrays.asList(diet);
    }

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
}

class Unknown extends Food {
    Unknown(String name) {
        super(name);
    }
}
