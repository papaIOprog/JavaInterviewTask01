package com.papaio.interviews.task2.solver;

import com.papaio.interviews.task2.pojo.Ticket;

import java.util.ArrayList;

public interface TaskSolverStrategy {

    public Double solve(ArrayList<Ticket> tickets);
}