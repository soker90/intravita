# Intravita

[![GitHub license](https://img.shields.io/github/license/soker90/intravita.svg)](https://github.com/soker90/intravita/blob/master/LICENSE) [![Build Status](https://travis-ci.org/soker90/intravita.svg?branch=master)](https://travis-ci.org/soker90/intravita) [![GitHub forks](https://img.shields.io/github/forks/soker90/intravita.svg)](https://github.com/soker90/intravita/network)

Intravita es una red social similar a Facebook desarrollada con el framework Java Spring para la asignatura **Procesos de Ingeniería del Software**. La aplicación permite la gestión de publicaciones y usuarios, la gestión de amistades entre los usuarios, con sus debidos ajustes de privacidad, la republicación de las publicaciones de otros usuarios y la gestión de "Me gustas".

## Ejecución

La aplicación se puede usar online desde [https://intravita.herokuapp.com](https://intravita.herokuapp.com)

## Requisitos de instalación
- Maven
- Tomcat 8.5
- JDK 8
- MongoDB (En caso de usar una base de datos local)

Para instalar el resto de dependencias con maven:
```sh
maven install
```

## Tests
Para realizar los tests mediante maven:
```sh
maven test
```

## Licencia
GPLv3
