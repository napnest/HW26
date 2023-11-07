import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //Task1
        Set<Set<String>> sets = new HashSet<>();
        Set<String> strings = new HashSet<>();
        strings.add("раз");
        strings.add("два");
        strings.add("три");
        Set<String> strings2 = new HashSet<>();
        strings2.add("Первое");
        strings2.add("Второе");
        strings2.add("Семьдесят шестое");
        Set<String> strings3 = new HashSet<>();
        strings3.add("12");
        strings3.add("783");
        strings3.add("37288736");
        Set<String> strings4 = new HashSet<>();
        strings4.add("Проверка");
        strings4.add("Струн");
        strings4.add("Раз-Два");

        sets.add(strings);
        sets.add(strings2);
        sets.add(strings3);
        sets.add(strings4);

        //Найти сумму всех символов в коллекции
        int sum = sets.stream().flatMap(s->s.stream()).mapToInt(x->x.length()).sum();

        //Найти длину самого большого слова
        int size = sets.stream().flatMap(s->s.stream()).mapToInt(s->s.length()).max().getAsInt();
        System.out.println(sum);
        System.out.println(size);
        ////////////////////////////////////////////////////////
        //Task2
        List<Employee> employees = EmployeeFactory.createEmployee();
        //Найти работника с самым большим KPI
        int maxKPI = employees.stream().mapToInt(s->s.getKpi()).max().getAsInt();
        Employee employeeMaxKPI = employees.stream().max(Comparator.comparing(Employee::getKpi)).get();
        System.out.println(maxKPI);
        System.out.println(employeeMaxKPI);
        ////////////////////////////////////////////////////////
        //Найти работника больше 65 лет
        employees.stream()
                .filter(x->x.getAge()>65).
                forEach(e-> System.out.println(e.getName()+" "+e.getSurname()));
        ////////////////////////////////////////////////////////
        //Найти работника с самой большой зарплатой
        int maxSalary = employees.stream().mapToInt(x->x.getSalary()).max().getAsInt();
        employees.stream().filter(s->s.getSalary()==maxSalary).forEach(System.out::println);
        Employee employeeMaxSalary = employees.stream().max(Comparator.comparing(Employee::getSalary)).get();
        System.out.println(maxSalary);
        System.out.println(employeeMaxSalary);
        /////////////////////////////////////////////////////////
        //Найти иностранного работника
        employees.stream().filter(s->s.getName().matches("[A-z]+")).forEach(System.out::println);
        /////////////////////////////////////////////////////////
        //Найти работников с KPI выше среднего
        double averageKPI = employees.stream().mapToDouble(s->s.getKpi()).average().getAsDouble();
        System.out.println(averageKPI);
        employees.stream().filter(s->s.getKpi()>averageKPI).forEach(System.out::println);
        System.out.println("///////////////////////////////////////////////////");
        ///////////////////////////////////////////////////////////
        //Найти среднее KPI работников по условию (возраст<45, зарплата < 20000, не иностранцы)
        double avgKPI = employees.stream()
                .filter(s->s.getAge()<45 && s.getName().matches("[А-яЁё]+") && s.getSalary()<20000)
                .mapToDouble(s->s.getKpi())
                .average()
                .getAsDouble();
        System.out.println(avgKPI);
        System.out.println("////////////////////////////////////////////////////");
        //////////////////////////////////////////////////////////////
        // Используя стримы создайте из списка работников Map<String, Employee>,
        // где ключом будет Фамилия Имя работника.
        // В карту должны попасть только те работники,
        // у которых: возраст до 35 лет, зп больше 10000
        Map <String, Employee> employeeMap = employees.stream()
                .filter(e->e.getAge()<35 && e.getSalary()>10000)
                .collect(Collectors.toMap(e->e.getName()+" "+e.getSurname(),e->e));
        for(Map.Entry <String,Employee> entry: employeeMap.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        List <Employee> employeeList = new ArrayList<>();

    }
}