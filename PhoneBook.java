// Формат сдачи: ссылка на подписанный git-проект.
// Задание:
// Реализуйте структуру телефонной книги с помощью HashMap. Программа также должна учитывать, 
// что в во входной структуре будут повторяющиеся имена с разными телефонами, их необходимо считать, 
// как одного человека с разными телефонами. Вывод должен быть отсортирован по убыванию числа телефонов.

package DZ.DZ_6;

import java.util.*;

public class PhoneBook {
    public static void main(String[] args) {
        // Создаем HashMap для хранения имен и списка телефонных номеров
        HashMap<String, List<String>> phoneBook = new HashMap<>();
        // Входные данные
        String[] input = {
                "Алена 123456",
                "Алена 789012",
                "Вася 789012",
                "Вася 758012",
                "Алена 789012",
                "Петя 789012",
                "Паша 893984"
        };

        // Заполняем HashMap на основе входных данных
        for (String entry : input) {
            String[] parts = entry.split(" ");
            String name = parts[0];
            String phoneNum = parts[1];

            // Если имя уже есть в HashMap, добавляем новый телефон в список
            // Если нет, создаем новую запись в HashMap
            if (phoneBook.containsKey(name)) {
                phoneBook.get(name).add(phoneNum);
            } else {
                List<String> phoneList = new ArrayList<>();
                phoneList.add(phoneNum);
                phoneBook.put(name, phoneList);
            }
        }

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
