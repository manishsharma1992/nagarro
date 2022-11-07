# Nagarro Java Task


## Pre-requiste

- Java 11+
- Spring Boot 2.7.X
- Node 18.X
- Maven 3.8.6

## Installation

Install the dependencies and devDependencies and start the Spring Boot server.
- Clone the project from the repository

```sh
$ git clone https://github.com/manishsharma1992/nagarro.git
```
- Navigate to directory after cloning

```sh
$ cd nagarro
```
- Build the server project (includes validating Unit Test Case), this will also generate coverage report

```sh
$ mvnw clean install
```
- Run Spring Boot Server

```sh
$ mvnw -pl nagarro-server -am spring-boot:run
```
- Open another terminal/cmd
- Navigate to UI module

```sh
$ cd nagarro/nagarro-web
```
- Install all UI dependencies and build using below command

```sh
$ npm install
```
- Run the UI

```sh
$ npm start
```
- Once UI is started
  Verify the deployment by navigating to your server address in
  your preferred browser.

```sh
http://localhost:4200
```

## License

MIT
**Free Software, Hell Yeah!**
