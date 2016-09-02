package org.simpumind.yourkindamovies.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SimpuMind on 9/1/16.
 */
public class ResultList {

    private List<Result> results = new ArrayList<Result>();

    /**
     *
     * @return
     * The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }
}
