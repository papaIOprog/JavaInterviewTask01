package com.papaio.interviews.task2;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.papaio.interviews.task2.pojo.Data;
import com.papaio.interviews.task2.solver.CommonTimezoneSolver;
import com.papaio.interviews.task2.solver.DifferentTimezonesSolver;
import com.papaio.interviews.task2.solver.TaskSolverStrategy;
import com.papaio.interviews.task2.util.JsonParser;
import org.apache.commons.cli.*;

import java.io.*;


public class Main {
    static private Options options;
    static private HelpFormatter formatter;
    public static void main(String[] args) {
        try {
            options = new Options();
            String timezoneOption = "timezonemode";
            String fileOption = "file";
            String helpOption = "help";
            options.addOption(timezoneOption, false, "Calculate average flight time include timezones (Departure from Vladivostok is GMT+10, arrival to Tel Aviv is GMT+3)");
            options.addOption(fileOption, true, "Input filename");
            options.addOption(helpOption, false, "Outputs help");
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            formatter = new HelpFormatter();

            if (cmd.hasOption(helpOption)) formatter.printHelp( "Flights", options );
            else {
                InputStream is;
                TaskSolverStrategy solver;

                String filePath = cmd.getOptionValue(fileOption);


                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                if(filePath != null) is = new FileInputStream(filePath);
                else is = classloader.getResourceAsStream("tickets.json");

                JsonParser<Data> dataParser = new JsonParser<>(new PropertyNamingStrategies.SnakeCaseStrategy());
                Data data = dataParser.parse(is, Data.class);

                if (cmd.hasOption(timezoneOption)) solver = new DifferentTimezonesSolver(data.getTickets());
                else solver = new CommonTimezoneSolver(data.getTickets());


                double solverAverageAnswer = solver.solveAverage();
                double averageAnswer = solverAverageAnswer / 1000.0 / 60.0 / 60.0;
                System.out.printf("Average flight is %.2f hours.%n", averageAnswer);

                double percentile = 90.0;
                long solverPercentileAnswer = solver.solvePercentile(percentile);
                double percentileAnswer = solverPercentileAnswer / 1000.0 / 60.0 / 60.0;
                System.out.printf("Percentile %.2f%% is %.2f hours.%n", percentile, percentileAnswer);
            }
        }
        catch (IOException e) {
            System.out.println("Probably, file wasn't found.");
        }
        catch (ParseException e) {
            formatter.printHelp( "Flights", options );
        }
    }
}
