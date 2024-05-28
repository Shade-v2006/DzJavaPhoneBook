// Формат сдачи: ссылка на подписанный git-проект.
// Задание:
// Реализуйте структуру телефонной книги с помощью HashMap. Программа также должна учитывать, 
// что в во входной структуре будут повторяющиеся имена с разными телефонами, их необходимо считать, 
// как одного человека с разными телефонами. Вывод должен быть отсортирован по убыванию числа телефонов.

// НЕ ПРИНЕМАЕТ РУССКИЙ АЛФАВИТ С КОНСОЛИ!!!   
package DZ.DZ_6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PhoneBook_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, List<String>> phoneBook = new HashMap<>(); // Создаем хеш-таблицу для хранения контактов
        // Меню - телефонной книги
        while (true) {
            System.out.println();
            System.out.println("Меню:(на вход не принемает русские буквы)");
            System.out.println("1. Добавить контакт");
            System.out.println("2. Удалить контакт");
            System.out.println("3. Вывести телефонную книгу");
            System.out.println("4. Выйти");
            System.out.println();
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очищаем буфер после считывания числа
            // Обрабатываем меню
            switch (choice) {
                case 1:
                    System.out.print("Введите имя: ");
                    String name = scanner.nextLine(); // Считываем имя контакта
                    System.out.print("Введите номер телефона: ");
                    String phoneNum = scanner.nextLine(); // Считываем номер телефона контакта
                    phoneBook.computeIfAbsent(name, k -> new ArrayList<>()).add(phoneNum); // Добавляем контакт в
                                                                                           // телефонную книгу
                    break;
                case 2:
                    System.out.print("Введите имя для удаления: ");
                    String removeName = scanner.nextLine(); // Считываем имя контакта для удаления
                    phoneBook.remove(removeName); // Удаляем контакт из телефонной книги
                    break;
                case 3:
                    System.out.println("Телефонная книга:");
                    printPhoneBook(phoneBook); // Выводим содержимое телефонной книги
                    break;
                case 4:
                    System.out.println("--Выход--");
                    scanner.close(); // Закрываем Scanner перед выходом из программы
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    public static void printPhoneBook(HashMap<String, List<String>> phoneBook) {
        // Создаем список записей для сортировки
        List<Map.Entry<String, List<String>>> entries = new ArrayList<>(phoneBook.entrySet());

        // Сортируем записи по убыванию количества номеров телефонов
        for (int i = 0; i < entries.size(); i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                if (entries.get(j).getValue().size() > entries.get(i).getValue().size()) {
                    Map.Entry<String, List<String>> temp = entries.get(i);
                    entries.set(i, entries.get(j));
                    entries.set(j, temp);
                }
            }
        }

        // Выводим результат
        for (Map.Entry<String, List<String>> entry : entries) {
            System.out.print(entry.getKey() + " - ");
            List<String> phoneList = entry.getValue();
            for (int i = 0; i < phoneList.size(); i++) {
                System.out.print(phoneList.get(i));
                if (i < phoneList.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
