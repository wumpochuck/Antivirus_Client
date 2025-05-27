<h1 align=center>Java Antivirus Client</h1>

## Project Structure

- `antivirus`
    - `src`
        - `main`
            - `java/ru/mtuci`    
                -`antivirus`
                    - `controllers`    # Контроллеры JavaFX (логика UI)
                    - `animations`     # Анимации для интерфейса
                    - `utils`          # Вспомогательные классы и утилиты
            - `resources`
         

## Getting Started

1) Заходим в IntelliJ IDEA Ultimate, открываем проект
2) Дожидаемся пока подгрузится Maven
3) Открываем слева панель Maven, делаем чистку "clean"
![1](./screenshots/1.png)
4) После чистки делаем сборку "javafx:jlink"
![2](./screenshots/2.png)
5) Появилась папка target, внутри переходим по app->bin и запускаем файл app.bat
![3](./screenshots/3.png)
   - Программа поддерживает флаг **--no-tray** для запуска окна и трея, без необходимости разворачивать окно


## Technologies 

- Java 17+ 
- JavaFX 
- Maven 
- ControlsFX 
- JUnit 
- Git 

> В проекте используется клиентская часть на JavaFX. Серверная часть (Spring Boot, Spring Security, Spring Data JPA, REST API, базы данных) не входит в этот репозиторий, но может использоваться на сервере.


## Contributors

<table>
    <tbody>
        <tr>
            <td>
                <img width=50 src="https://avatars.githubusercontent.com/u/130181963"/>
            </td>
            <td>
                <a href = "t.me/wumpochuck"><b>wumpochuck</b></a>
                <br>
            </td>
            <td>
                <img width=50 src="https://avatars.githubusercontent.com/u/85567113?v=4"/>
            </td>
            <td>
                <a href = "https://github.com/yokkochka"><b>yokkochka</b></a>
                <br>
            </td>
            <td>
                <img width=50 src="https://avatars.githubusercontent.com/u/153612706?v=4"/>
            </td>
            <td>
                <a href = "https://github.com/Na-Nd"><b>Na-Nd</b></a>
                <br>
            </td>
        </tr>
    </tbody>
</table>
