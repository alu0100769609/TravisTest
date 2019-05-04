# Tutorial: Como configurar Git hooks en un repositorio nuevo

![Git hooks logo](http://dev.agenciasisters.com.br/faciplac/vendor/larapack/hooks/resources/logo.png)

## ¿Que es un `Git hook`?

La [documentación oficial de Git](https://git-scm.com/docs/githooks) tiene información muy detallada, pero básicamente:

> Un `git hook` es un programa (normalmente un script `bash`) que se ejecuta tras realizar alguna acción en __Git__. Por ejemplo, podemos añadir __hooks__ que se ejecuten antes o después __hacer un commit, push, pull, merge, etc...__ 

Ya que su funcionamiento se basa en scripts en `bash`, __las posibilidades son prácticamente ilimitadas__

n este tutorial, veremos concretamente como añadir un hook que __ejecute los tests__ antes de realizar un __commit__, y solo continúe con el commit en caso de que todos los tests fallen.

### Creando el hook

El código es realmente simple:

```bash
#!/bin/sh

# run the tests with the gradle wrapper
./gradlew test --daemon

# store the last exit code in a variable
RESULT=$?

# return the './gradlew test' exit code
exit $RESULT
```

Lo único que debe devolver el script es __0__ en caso de que se haya ejecutado con éxito, o __cualquier otro valor__ en caso de error.
Con este `hook` estamos devolviendo el __código de retorno__ de la ejecución de los __tests con Gradle__.

* __IMPORTANTE__: Es necesario guardar el script con el nombre `pre-commit`. En este caso, para que se ejecute __antes de hacer un commit__

### Ejecutando el hook

Lo único necesario para que un `hook` se pueda ejecutar es colocarlo en la carpeta `.git/hooks/`.

Sin embargo, es recomendable __guardar todos los hooks__ en una carpeta del proyecto, y crear una __tarea__ de __Gradle__ para copiarlos automáticamente.

Para este proyecto, vamos a colocar el `hook` creado, `pre-commit` en la carpeta `gradle/` del proyecto. Además, añadiremos la siguiente tarea al `build.gradle`
```groovy
task installGitHooks() {
  "cp gradle/* .git/hooks/".execute()
}
```

Ahora, podremos ejecutar el comando `./gradlew installGitHooks` para __copiar automáticamente__ todos los hooks que tengamos.

Por último, podemos comprobar que, efectívamente, __funciona__
```console
dav@dav-GL552VW:~/TravisTest$ git add .
dav@dav-GL552VW:~/TravisTest$ git commit -m"Commit with test errors"

> Task :test FAILED

test.java.pract3.AppTest > test() PASSED

test.java.pract3.FechaTest > testSiguienteAnio() PASSED

test.java.pract3.FechaTest > testSetDiaAndGetDia() PASSED

test.java.pract3.FechaTest > testAnteriorDia() PASSED

test.java.pract3.FechaTest > testAnteriorMes() PASSED
...
```

En este caso, debido a que __los tests estaba fallando__, no se ha añadido el commit al historial. 
