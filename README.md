# Rick_and_Morty_wiki
![ic_launcher_foreground](https://user-images.githubusercontent.com/86518548/156780596-af1db0c0-4bce-4e16-899f-fc87eed8c8c5.png)


**Last version 0.2.4**
```
Перевод модели приложения на "single activity + fragments"
```

___
```
Доработки
- при возвращении к списку нет сохранения предыдущей позиции списка
- нет горизонтальной разметки
- реализовать поиск
- нет кнопки назад на детальном экране
- добавить скелетную разметку для загрузки данных на детальном экране
```
___

**version 0.2.4**
```
Перевод модели приложения на "single activity + fragments"
```

**version 0.2.3**
```
Реализация di - dagger
```

**version 0.2.2**
```
Добавлениие пагинации (paging 3)
```

**version 0.2.1**
```
Переход на чистую архитектуру (layers, usecases, repository)
```

**version 0.2**
```
Замена livedata на stateflow
Корутины
Рефакторинг кода
```

**version 0.1**
```
Первая реализация
```

___
Использованные библиотеки:
- retrofit
- gson
- picasso

___
Задача:
Реализовать простое приложение, которое будет содержать два экрана.
Использовать общедоступный API https://rickandmortyapi.com/documentation/#rest

1 экран - отображение списка персонажей Рик и Морти, используя соответствующий метод API. Будет плюсом, если сможете реализовать постраничную загрузку персонажей в списке.
Для каждого персонажа в ячейке списка должно отображаться имя, раса, пол, аватарка.

По тапу на ячейку списка должен открываться экран детальной информации о персонаже (2 экран). При открытии этого экрана должна происходить загрузка детальной информации о выбранном персонаже, используя соответствующий метод API.
На экране детальной информации о персонаже должно отображаться имя, раса, пол, статус, аватарка, последнее известное местоположение, кол-во эпизодов, в которых упоминался данный персонаж.
___
Приложение из двух экранов:
- список персонажей

![Screenshot_20220418-001307_Rick   Morty Characters wiki](https://user-images.githubusercontent.com/86518548/163729115-6e1579c1-16bb-490f-a775-a91e0e53d006.jpg)

- детальная информация о персонаже

![Screenshot_20220418-001116_Rick   Morty Characters wiki](https://user-images.githubusercontent.com/86518548/163729097-5f597c9c-f168-474f-8f9d-1df3869c0363.jpg)

___
