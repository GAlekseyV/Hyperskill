import java.util.Scanner;

import java.util.ArrayList;

class SelectionContext {

    private PersonSelectionAlgorithm algorithm;

    public void setAlgorithm(PersonSelectionAlgorithm algorithm) {
        // write your code here
        this.algorithm = algorithm;
    }

    public Person[] selectPersons(Person[] persons) {
        // write your code here
        return algorithm.select(persons);
    }
}

interface PersonSelectionAlgorithm {

    Person[] select(Person[] persons);
}

class TakePersonsWithStepAlgorithm implements PersonSelectionAlgorithm {

    private int step;

    public TakePersonsWithStepAlgorithm(int step) {
        // write your code here
        this.step = step;
    }

    @Override
    public Person[] select(Person[] persons) {
        // write your code here
        ArrayList<Person> selPersons = new ArrayList<>();
        for(int i = 0; i < persons.length; i += this.step){
            selPersons.add(persons[i]);
        }
        Person[] selectedPersons = new Person[selPersons.size()];
        for(int i = 0; i < selectedPersons.length; i++){
            selectedPersons[i] = selPersons.get(i);
        }
        return selectedPersons;
    }
}

class TakeLastPersonsAlgorithm implements PersonSelectionAlgorithm {

    private int count;

    public TakeLastPersonsAlgorithm(int count) {
        // write your code here
        this.count = count;
    }

    @Override
    public Person[] select(Person[] persons) {
        // write your code here
        Person[] lastPersons = new Person[count];
        int startIndex = persons.length - count;
        for(int i = startIndex; i < persons.length; i++){
            lastPersons[i - startIndex] = persons[i];
        }
        return lastPersons;
    }
}

class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }
}


/* Do not change code below */
public class Main {

    public static void main(String args[]) {
        final Scanner scanner = new Scanner(System.in);

        final int count = Integer.parseInt(scanner.nextLine());
        final Person[] persons = new Person[count];

        for (int i = 0; i < count; i++) {
            persons[i] = new Person(scanner.nextLine());
        }

        final String[] configs = scanner.nextLine().split("\\s+");

        final PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
        SelectionContext ctx = new SelectionContext();
        ctx.setAlgorithm(alg);

        final Person[] selected = ctx.selectPersons(persons);
        for (Person p : selected) {
            System.out.println(p.name);
        }
    }

    public static PersonSelectionAlgorithm create(String algType, int param) {
        switch (algType) {
            case "STEP": {
                return new TakePersonsWithStepAlgorithm(param);
            }
            case "LAST": {
                return new TakeLastPersonsAlgorithm(param);
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algType);
            }
        }
    }
}