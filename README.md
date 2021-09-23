# JavaInterviewTask01
Hello. It's interview task for Idea Platform.

## Build
1. `mvn install`
2. `mvn package`

## Usage
Available options:
```
usage: Flights
 -file <arg>     Input filename
 -help           Outputs help
 -timezonemode   Calculate average flight time include timezones
                 (Departure from Vladivostok is GMT+10, arrival to Tel
                 Aviv is GMT+3)
```
Following command calculates average flight time from Vladivostok to Tel Aviv without according to timezones (it means, that `departure_time` has same timezone as `arrival_time`) with built-in JSON file:
* `java -jar ./target/Flight-1.0-SNAPSHOT-jar-with-dependencies.jar`

```
Average flight is 7,53 hours.
```

To specify your own file:
`java -jar ./target/Flight-1.0-SNAPSHOT-jar-with-dependencies.jar -file <Path-to-file>`

To enable calculation mode that accords on timezones (it means, that `departure_time` and `arrival_time` have different timezones) use parameter `-timezonemode`:
`java -jar ./target/Flight-1.0-SNAPSHOT-jar-with-dependencies.jar -timezonemode`

```
Average flight is 14,53 hours.
```
