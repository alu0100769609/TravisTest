# Tutorial: Como configurar Codacy en un repositorio nuevo

![Codacy logo](https://avatars2.githubusercontent.com/u/19940114?s=460&v=4)

## ¿Qué es Codacy?

![Página oficial](https://www.codacy.com/)

Es una herramienta para la __automatización de la cálidad de código__. Funciona de manera similar a __Travis__: Por cada commit, comprobará el código frente a una serie de __linters__, y devolverá un reporte con todos los __fallos__ que ha podido encontrar. Puede ser tanto __fallos en el código__, como __errores de estilo__, __código que puede ser confuso__, __funciones poco seguras__, etc.

> Un __linter__ es una herramienta que se encarga de __análizar un código__ con el fin de detectar problemas. Pueden ser tanto __errores de compilación__ en el código, como __errores de estilo__ que no se ajusten a la guía de estilo elegida.

Es una herramienta muy recomendable para poder estar constantemente comprobando la __calidad del código__, sobretodo si se trabaja en un __equipo__

## Configurando Codacy

Lo primero que tendremos que hacer es __registrarnos con nuestra cuenta de Github__ desde la [página oficial](https://www.codacy.com/).

Después, nos saldrá una lista con repositorios que ya tengamos configurados. Para añadir uno nuevo, le damos al botón azúl que pone __Add project__.

![Codacy main menu](https://i.imgur.com/DEezO7F.png)

Nos saldrá una __lista de repositorios__. Lo único que tendremos que hacer es __elegir__ el que queramos revisar con Codacy.

![Codacy repos](https://i.imgur.com/4xqhmPo.png)

Y ya tendremos el repositorio __configurado__. Ahora, cada vez que hagamos un commit volverá a __comprobar la calidad del código__.

## Usando Codacy

Es recomendable __experimentar__ con la aplicación. Es muy __intuitiva__ y fácil de utilizar. Las ventanas más importantes son el __Dashboard__, que muestra un __estado general__ del proyecto (evolución de la calidad, problemas encontrados, commits...)

![Dashboard](https://i.imgur.com/2YQVald.png)

La ventana de __Issues__ muestra detalladamente una descripción de los __problemas__ encontrados con el código, una breve __descripción__ de en que consisten y consejos sobre __como solucionarlos__

![Issues](https://i.imgur.com/mjrg3Td.png)

Y la ventana de __code patterns__ muestra los __linters__ utilizados por el proyecto y su __configuración__.

![Config](https://i.imgur.com/wC3UOhR.png)

