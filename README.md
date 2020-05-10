# Stonks

Stonks is a Java application for tracking personal goals.

For the course **Ohjelmistotekniikka** in **University of Helsinki**.


## Documentation

[Software requirements specification](https://github.com/Eelinki/ot-harjoitustyo/blob/master/documentation/software_requirements_specification.md)

[Architecture](https://github.com/Eelinki/ot-harjoitustyo/blob/master/documentation/architecture.md)

[Timesheet](https://github.com/Eelinki/ot-harjoitustyo/blob/master/documentation/timesheet.md)

[User guide](https://github.com/Eelinki/ot-harjoitustyo/blob/master/documentation/user_guide.md)

[Testing document](https://github.com/Eelinki/ot-harjoitustyo/blob/master/documentation/testing.md)

## Releases

[Week 7 Release (final)](https://github.com/Eelinki/ot-harjoitustyo/releases/tag/week7)

[Week 6 Release](https://github.com/Eelinki/ot-harjoitustyo/releases/tag/week6)

[Week 5 Release](https://github.com/Eelinki/ot-harjoitustyo/releases/tag/week5)

## Running

```
mvn compile exec:java -Dexec.mainClass=stonks.Main
```

## Testing

Tests can be run with command

```
mvn test
```

To generate a code coverage report

```
mvn test jacoco:report
```

The report can be found in `target/site/jacoco/index.html`

## Generating an executable JAR file

```
mvn package
```

Generated JAR file can be found in `target/Stonks-1.0-SNAPSHOT.jar`

## JavaDoc

```
mvn javadoc:javadoc
```

Generated JavaDoc can be found in `target/site/apidocs/index.html`
