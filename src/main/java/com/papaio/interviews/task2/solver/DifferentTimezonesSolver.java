package com.papaio.interviews.task2.solver;

import com.papaio.interviews.task2.pojo.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.stream.LongStream;

public class DifferentTimezonesSolver implements TaskSolverStrategy {

    public DifferentTimezonesSolver () {
    }

    public Double solve(ArrayList<Ticket> tickets) {
        LongStream longStream = tickets.stream()
                .mapToLong(ticket ->
                        countHours(ticket.getDepartureDate(), ticket.getDepartureTime(),
                                ticket.getArrivalDate(), ticket.getArrivalTime()));
        OptionalDouble optionalDouble = longStream.average();
        if(optionalDouble.isPresent()) return optionalDouble.getAsDouble();
        else return -1.0;
    }

    private long countHours (String departureDate, String departureTime, String arrivalDate, String arrivalTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd.MM.yy/HH:mm][dd.MM.yy/H:mm]");
        LocalDateTime vladivostok = LocalDateTime.parse(String.format("%s/%s", departureDate,departureTime), formatter);
        LocalDateTime telAviv = LocalDateTime.parse(String.format("%s/%s", arrivalDate, arrivalTime), formatter);
        ZoneOffset vladivostokOffset = ZoneOffset.of("+10:00");
        ZoneOffset telAvivOffset = ZoneOffset.of("+03:00");
        OffsetDateTime start = vladivostok.atOffset( vladivostokOffset );
        OffsetDateTime stop = telAviv.atOffset( telAvivOffset ) ;
        Duration duration = Duration.between( start , stop );
        return duration.toMillis();
    }
}