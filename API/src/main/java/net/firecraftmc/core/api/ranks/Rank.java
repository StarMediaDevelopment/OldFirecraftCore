package net.firecraftmc.core.api.ranks;

import java.util.Objects;

public record Rank(String name, String baseColor, int weight) implements Comparable<Rank> {
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Rank rank = (Rank) o;
        return Objects.equals(name, rank.name);
    }

    public int hashCode() {
        return Objects.hash(name);
    }

    public int compareTo(Rank o) {
        return Integer.compare(this.weight, o.weight);
    }
}