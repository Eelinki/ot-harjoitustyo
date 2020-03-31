# Stonks

Stonks is a Java application for tracking personal goals.

For the course **Ohjelmistotekniikka** in **University of Helsinki**.


## Documentation

[Software requirements specification](https://github.com/Eelinki/ot-harjoitustyo/blob/master/docs/software_requirements_specification.md)

[Timesheet](https://github.com/Eelinki/ot-harjoitustyo/blob/master/docs/timesheet.md)

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
mvn jacoco:report
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