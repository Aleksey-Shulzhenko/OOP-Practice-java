# Завдання №3: Factory Method для збереження та відображення обчислень

## Опис завдання

Програма демонструє реалізацію шаблону проектування Factory Method для керування результатами обчислень (сума та добуток). Програма виконує наступні кроки:

1. Введення: Користувач вводить два числа, які використовуються для виконання двох типів обчислень: сума та добуток.

2. Фабричний метод: Програма використовує фабричний метод для створення об'єктів, які представляють результати обчислень. Результат може бути або сумою, або добутком двох чисел.

3. Інтерфейс CalculationResult: Програма визначає інтерфейс CalculationResult, який реалізується двома конкретними класами: SumResult і ProductResult. Ці класи інкапсулюють результати відповідних обчислень і надають методи для відображення результату та отримання його у вигляді рядка.

4. CalculationManager: Клас-менеджер, що зберігає результати обчислень у колекції. Він надає методи для додавання результатів, їх відображення та збереження у файл.

5. Обробка файлів: Програма зберігає відображені результати у файл під назвою results.txt, який знаходиться в директорії resources/ex3/.

6. Виведення: Програма виводить результати на консоль і записує їх у файл для подальшого використання.

---

## Структура проєкту
```plaintext
├── src/                  # Вхідний код програми
│   ├── ex3/              # Пакет із кодом
│   │   ├── Main.java     # Програма для визначення оператора мобільного зв'язку
│   ├── img ex3/          # Пакет з скріншотами
├── .gitignore            # Виключені файли
├── README.md             # Документація
````


## Скріншоти виконання програм
![img.png](img%20ex3/img.png)
---

## Опис програми

Ця програма демонструє використання шаблону проектування **Factory Method** для обчислення результатів (сума і добуток двох чисел) та їх відображення/збереження в файл.

1. **Інтерфейс `CalculationResult`**:
    - Визначає методи для відображення результатів (`display()`) та отримання результату у вигляді рядка (`getResultString()`).

2. **Конкретні класи результатів**:
    - `SumResult` — обчислює суму двох чисел.
    - `ProductResult` — обчислює добуток двох чисел.

3. **Фабричний метод**:
    - Абстрактний клас `CalculationFactory` має метод `createResult()`, який реалізується у конкретних фабриках:
        - `SumFactory` — створює об'єкти для суми.
        - `ProductFactory` — створює об'єкти для добутку.

4. **Менеджер результатів**:
    - Клас `CalculationManager` зберігає результати у колекції, відображає їх на екрані та зберігає у файл.

5. **Головний метод**:
    - Користувач вводить два числа.
    - Створюються об'єкти результатів суми та добутку.
    - Результати відображаються на екрані і зберігаються в файл.

#### Приклад роботи:
1. Вводяться числа, наприклад, 5 і 3.
2. Програма виводить:
   ```
   Результат суми: 8
   Результат добутку: 15
   ```
3. Результати зберігаються у файл `results.txt`.

---

## Посилання на файл

[Main.java](code/Main.java)


### Повернення на головну

[Головне меню](../../../../README.md)