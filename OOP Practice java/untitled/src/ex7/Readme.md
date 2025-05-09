# Завдання №7: Графічний інтерфейс (Swing)

## Структура проєкту
```plaintext
├── src/                  # Вхідний код програми
│   ├── ex7/              # Пакет із кодом
│   │   ├── code          # Пакет з кодом
│   ├── img ex6/          # Пакет з скріншотами
├── .gitignore            # Виключені файли
├── README.md             # Документація
````

## Скріншоти виконання програм
## Завдання №1
![ex1.png](img%20ex7/ex1.png)
## Завдання №2 (1)
![ex2(1).png](img%20ex7/ex2%281%29.png)
## Завдання №2 (2)
![ex2 (2).png](img%20ex7/ex2%20%282%29.png)
## Завдання №2 (3)
![ex2 (3).png](img%20ex7/ex2%20%283%29.png)
## Завдання №2 (4)
![ex2 (4).png](img%20ex7/ex2%20%284%29.png)
## Завдання №3
![ex3.png](img%20ex7/ex3.png)
## Завдання №4
![ex4.png](img%20ex7/ex4.png)
## Завдання №5
![ex5.png](img%20ex7/ex5.png)
## Завдання №6
![ex6.png](img%20ex7/ex6.png)
---

### Огляд графічного інтерфейсу

Графічний інтерфейс (GUI) є важливою частиною програмного забезпечення, що дозволяє користувачам взаємодіяти із системою через візуальні елементи. У сучасних додатках GUI використовується для підвищення зручності роботи, покращення взаємодії з користувачем і автоматизації процесів введення та виводу даних.

### Основні компоненти GUI

1. **Вікна (Windows)**  
   Головний контейнер, у якому розміщуються всі інші елементи. Вікно може містити меню, кнопки, текстові поля та інші інтерактивні об'єкти.

2. **Кнопки (Buttons)**  
   Елементи, що реагують на натискання користувачем. Використовуються для виконання команд, відкриття нових вікон або обробки введених даних.

3. **Текстові поля (Text Fields)**  
   Поля для введення інформації. Можуть містити підказки для користувача та використовуються для обробки введених даних.

4. **Мітки (Labels)**  
   Текстові написи, що пояснюють призначення елементів інтерфейсу або відображають результати обчислень.

5. **Таблиці та списки (Tables & Lists)**  
   Відображають дані в структурованому вигляді, полегшуючи перегляд та аналіз інформації.

6. **Області виводу (Text Areas)**  
   Використовуються для відображення великих обсягів тексту, наприклад, результатів обчислень або повідомлень користувачеві.

7. **Обробка подій (Event Handling)**  
   GUI реагує на дії користувача (натискання кнопок, введення тексту) через систему обробки подій. Це дозволяє автоматизувати роботу інтерфейсу та забезпечити динамічний зв'язок між компонентами.

### Можливості:
- Інтерактивний графічний інтерфейс для роботи з даними.
- Введення числових значень через текстове поле.
- Автоматичний розрахунок мінімального, максимального значення та середнього арифметичного.
- Відбір парних чисел із введеного списку.
- Відображення результатів у спеціальному полі виводу.
- Підтримка багатопоточності для швидкої обробки даних без зависання інтерфейсу.
- Збереження результатів у файл.

### Вимоги:
- **Операційна система:** Windows, macOS або Linux.
- **JDK:** Версія 8 або новіша.
- **Бібліотеки:** Стандартні бібліотеки Java (`javax.swing`, `java.util`, `java.util.concurrent`).
- **Місце на диску:** Мінімум 10 МБ для запуску програми та збереження результатів.
- **Оперативна пам’ять:** Мінімум 512 МБ для стабільної роботи.


### Використання багатопоточності у GUI

Щоб уникнути блокування інтерфейсу під час виконання тривалих операцій, у сучасних додатках використовується багатопоточність. Потоки дозволяють виконувати складні обчислення у фоновому режимі без затримок у відображенні інтерфейсу.

### Висновок

Графічний інтерфейс відіграє ключову роль у забезпеченні взаємодії між користувачем і програмою. Використання структурованих елементів GUI та обробки подій дозволяє створювати зручні та ефективні програми, що автоматизують робочі процеси.


## Посилання на файл
[Завдання №1](code/ex1/Main.java)

[Завдання №2 (1)](code/ex2/Exercises1.java)

[Завдання №2 (2)](code/ex2/Exercises2.java)

[Завдання №2 (3)](code/ex2/Exercises3.java)

[Завдання №2 (4)](code/ex2/Exercises4.java)

[Завдання №3](code/ex3/Main.java)

[Завдання №4](code/ex4/Main.java)

[Завдання №5](code/ex5/Main.java)

[Завдання №6](code/ex6/NumberProcessorGUI.java)

### Повернення на головну

[Головне меню](../../../../README.md)