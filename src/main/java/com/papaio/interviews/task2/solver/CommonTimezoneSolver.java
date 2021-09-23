package com.papaio.interviews.task2.solver;

import com.papaio.interviews.task2.pojo.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.stream.LongStream;

public class CommonTimezoneSolver implements TaskSolverStrategy {

    public CommonTimezoneSolver () {
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
        LocalDateTime start = LocalDateTime.parse(String.format("%s/%s", departureDate,departureTime), formatter);
        LocalDateTime stop = LocalDateTime.parse(String.format("%s/%s", arrivalDate, arrivalTime), formatter);
        Duration duration = Duration.between( start , stop );
        return duration.toMillis();
    }
}