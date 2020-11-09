package com.ankur.trendmovies.models;

import java.util.List;

/**
 * @author ankur
 */
public class CreditResponse {
    List<Cast> cast;
    List<Crew> crew;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }
}
