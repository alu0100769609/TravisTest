# Tutorial: Como configurar Travis CI en un repositorio nuevo

[![Build Status](https://travis-ci.org/alu0100769609/TravisTest.svg?branch=travis)](https://travis-ci.org/alu0100769609/TravisTest)


## Habilitando Travis

Primero hay que entrar en ![Travis CI (org)](https://travis-ci.org/dashboard).

Ahi, escogeremos el repositorio en el que queremos activar __Travis__. Podemos ver la lista de repositorios haciendo click en el perfil, y _Settings_.

![Settings](https://i.imgur.com/3k14t5m.png)

En este menú, tendremos la lista de __organizaciones__ a las que pertenece la cuenta, y la lista de __repositorios__ que tenemeos en cada uno.

> Nota: Para poder habilitar Travis la primera vez es necesario ser propietario del repositorio. En el caso de las organizaciones, también es necesario tener permisos en la organización.

![Repos](https://i.imgur.com/l4QRD6Z.png)

Para habilitar Travis en un repositorio, basta con __marcar la casilla__ al lado de su nombre

![Marcar](https://i.imgur.com/91ik1cy.png)

Hecho esto, podremos ver como ahora el repositorio aparece en nuestra __página principal__ de ![Travis CI](https://travis-ci.org/dashboard).

![Dashboard](https://i.imgur.com/PUDPuqf.png)


## Configurando el repositorio con `.travis.yml`

> Para más información, el ![tutorial oficial en la página de Travis CI](https://docs.travis-ci.com/user/languages/java/) indica como configurar un proyecto de Java con Travis, además de información sobre los errores más comunes.

Travis por si solo __no sabe__ como realizar la __integración__ del proyecto. Es necesario añadir al proyecto un fichero, llamado `.travis.yml`, donde indicarle los comandos que debe realizar para __clonar, compilar y ejecutar los tests__ del proyecto.

Además, Travis solo puede utilizarse con proyectos en Java que utilizen __Ant, Maven o Gradle__ como herramientas para construir los proyectos. (No ofrece soporte para Eclipse por defecto)

En este tutorial, explicaremos como integrar __Travis__ con __Gradle__

#### Añadiendo Gradle al proyecto

Primer paso, ![instalar Gradle](https://gradle.org/install/). Es recomendable hacer la instalación mediante el gestor de paquetes `brew`. En ![este enlace](https://gradle.org/install/) se explica como realizar el proceso de instalación.

Una vez instalado, nos situaremos en nuestro repositorio y ejecutaremos el comando `gradle init`.

Primero, nos preguntará que __tipo de proyecto__ queremos generar. Seleccionaremos `java-application` (aplicación Java). Para el resto de opciones, podemos dejar los __valores por defecto__.

```console
Select type of project to generate:
  1: basic
  2: cpp-application
  3: cpp-library
  4: groovy-application
  5: groovy-library
  6: java-application
  7: java-library
  8: kotlin-application
  9: kotlin-library
  10: scala-library
Enter selection (default: basic) [1..10] 6

Select build script DSL:
  1: groovy
  2: kotlin
Enter selection (default: groovy) [1..2]  

Select test framework:
  1: junit
  2: testng
  3: spock
Enter selection (default: junit) [1..3] 

Project name (default: TravisTest): 
Source package (default: TravisTest): 

BUILD SUCCESSFUL in 27s
2 actionable tasks: 2 executed
```

Veremos que nos ha __generado automáticamente__ nuevos archivos (`gradle`, `gradlew`, `build.gradle`...). El archivo más importante es `build.gradle`. En el está toda la configuración que utilizara __Gradle__ a la hora de integrar nuestro proyecto.

El objetivo de este tutorial no es aprender a utilizar Gradle, y la página oficial tiene ![buenos tutoriales](https://guides.gradle.org/creating-new-gradle-builds/). Sin embargo, vamos a explicar ligeramente el contenido del archivo `build.gradle`

```groovy
// Extensiones que utiliza `gradle`. En este caso, 'java', ya que
// estamos generando un proyecto en Java, y 'application', que nos
// permite ejecutar el `main` del proyecto.
plugins {
    id 'java'
    id 'application'
}

// Repositorio del cual descargar los paquetes (plugins y dependencias).
// Similar a `apt` en Ubuntu, o `npm` en Javascript
repositories {
    jcenter()
}

// Dependencias que utiliza el proyecto (librerías y paquetes externos).
// En este caso, por ejemplo, una de ellas es `JUnit` para la execución
// de tests
dependencies {
    implementation 'com.google.guava:guava:27.0.1-jre'
    testImplementation 'junit:junit:4.12'
}

// Ruta a la clase con el método `main` en nuestro proyecto. Utilizada
// por application para ejecutar el proyecto.
mainClassName = 'TravisTest.App'
```

No tendremos que cambiar mucho la configuración por defecto. A continuación, pondremos el fichero `build.gradle` utilizado por este proyecto. En el, simplemente hemos cambiado la versión de __JUnit__ a __JUnit 5__, y la ruta de `mainClassName` a la ruta al __main__ en nuestro proyecto:

```groovy
plugins {
    id 'java'
    id 'application'
}

repositories {
  mavenCentral()
}

dependencies {
  testCompile("org.assertj:assertj-core:3.11.1")
  testImplementation('org.junit.jupiter:junit-jupiter-api:5.4.0')
  testRuntimeOnly('org.junit.jupiter:junit-jupiter-engine:5.4.0')
}

test {
    useJUnitPlatform()

    testLogging {
        events = ["passed", "failed", "skipped"]
        showStandardStreams = true
    }
}

mainClassName = 'main.java.pract3.App'
```

Podemos utilizar algunos comandos de __Gradle__ para comprobar que lo hemos configurado correctamente.
* Para __compilar__ el proyecto: `./gradlew build`
* Para __ejecutar los tests__: `./gradlew test`
* Para __ejecutar el proyecto__: `./gradlew run`

Ahora que ya tenemos __Gradle__ configurado, podemos continuar configurando __Travis CI__.

### Creando el fichero `travis.yml`

El fichero es tan sencillo, que la mejor forma de entenderlo es verlo directamente. Crea en la __raiz del repositorio__ el fichero `.travis.yml` con el siguiente contenido:

```yml
language: java

jdk:
- oraclejdk11
```

* `language`: __Lenguaje de programación__ del proyecto. En este caso, __Java__
* `jdk`: __Versión de Java__ a utilizar. En este caso, __Java 11__

Y ya está. Travis reconocerá __automáticamente__ que el proyecto está configurado con __Gradle__ y utilizará el comando `./gradlew check (equivalente a ./gradlew test)` para ejecutar los tests y actualizar el estado del repositorio.

## Utilizando Travis CI

Hay que recordar que __Travis__ no es una herramienta que se utilice directamente, sino que se ejecuta __cada vez que hacemos un push__ al repositorio.

Por tanto, podemos hacer un __push__ con la configuración que acabamos de añadir para comprobar si Travis está funcionando correctamente.

Tras hacer el push, volvemos a ![Travis CI](https://travis-ci.org/)
