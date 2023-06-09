# Nested resources

Часто одни ресурсы являются дочерними по отношению к другим. В этом задании мы будем работать с постом и его комментариями. Чтобы создать видимость иерархии, мы будем использовать вложенный путь, например `/posts/10/comments/5`. Так мы покажем, что комментарии принадлежат посту.

## Ссылки

* [Получение запроса из имени метода репозитория](https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html)
* [Аннотации Java persistance для добавления связей между сущностями](https://docs.oracle.com/javaee/7/api/javax/persistence/package-summary.html)
* [Аннотация @JsonIgnore - позволяет проигнорировать поле класса при сериализации](https://fasterxml.github.io/jackson-annotations/javadoc/2.5/com/fasterxml/jackson/annotation/JsonIgnore.html)

## src/main/java/exercise/model/Comment.java

## Задачи

* Допишите модель комментария. Создайте в классе следующие поля:
  * `id` – уникальный идентификатор поста. Первичный ключ, должен быть автогенерируемым
  * `content` – содержимое комментария, количество символов в комментарии не ограничено
  * `post` – пост, которому принадлежит комментарий. Укажите, что комментарий связан с постом связью "многие к одному". В одном посте может быть множество комментариев, но отдельный комментарий всегда принадлежит только одному посту

## src/main/java/exercise/repository/CommentRepository.java

## Задачи

* Добавьте в репозиторий метод, который позволит получить все комментарии для определенного поста по его id. Метод должен вернуть список `Iterable` комментариев

* Добавьте в репозиторий метод, который позволит получить комментарий по его id и id  поста, которому принадлежит комментарий.

## src/main/java/exercise/controller/CommentController.java

* Создайте полный CRUD для комментариев поста. Добавьте в контроллер методы, которые будут обрабатывать следующие запросы:

  * *GET /posts/{postId}/comments* - вывод всех комментариев для конкретного поста. Должны выводится только комментарии, принадлежащие посту.
  * *GET /posts/{postId}/comments/{commentId}* - вывод конкретного комментария для  поста. Должны выводится только комментарий, принадлежащие посту. Если такой комментарий не существует, должен вернуться ответ с кодом 404.
  * *POST /posts/{postId}/comments* - создание нового комментария для поста. Должны выводится только комментарий, принадлежащие посту.
  * *PATCH /posts/{postId}/comments/{commentId}* - редактирование конкретного комментария поста. Если такой комментарий не существует, должен вернуться ответ с кодом 404.
  * *DELETE /posts/{postId}/comments/{commentId}* - удаление конкретного комментария у  поста. Если такой комментарий не существует, должен вернуться ответ с кодом 404.

## Подсказки

* Чтобы проигнорировать определённое поле класса модели при его сериализации, отметьте его аннотацией `@JsonIgnore`. При преобразовании класса в JSON, отмеченное поле не будет сериализовано.

* Используйте [правила именования методов репозитория](https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.named-queries), чтобы автоматически получить нужный запрос из имени метода.

* Для дополнительной информации о работе контроллера для комментария изучите тесты в файле *src/test/java/exercise/CommentTest.java*

* Чтобы вернуть ответ с кодом 404, нужно выбросить исключение `ResourceNotFoundException`
