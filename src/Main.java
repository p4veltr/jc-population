import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // несовершеннолетние
        System.out.println("Несовершеннолетних: "+persons.stream().filter(person -> person.getAge() < 18).count());

        // призывники (мужчины 18-27)
        List<Person> personGroup = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN
                        && person.getAge() >= 18
                        && person.getAge() < 27)
                .collect(Collectors.toList());
        System.out.println("Призывники:");
        personGroup.forEach(System.out::println);

        // работоспособные с высшим образованием (Ж 18-60, М 18-65), сортировка по фамилии
        List<Person> workerGroup = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getSex() == Sex.MAN && person.getAge() < 65) || (person.getSex() == Sex.WOMAN && person.getAge() < 60))
                .sorted(Comparator.comparing(Person::getName))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Работоспособные:");
        workerGroup.forEach(System.out::println);
    }
}