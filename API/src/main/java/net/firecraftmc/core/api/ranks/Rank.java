package net.firecraftmc.core.api.ranks;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class Rank implements Comparable<Rank> {
    private String name, baseColor, chatColor;
    private boolean hasPrefix;
    private int voteWeight;
    private double coinMultiplier, chatCooldown;
    private int weight;

    public String getPrefix() {
        if (hasPrefix) {
            return baseColor + "&l" + name.toUpperCase();
        } else {
            return baseColor;
        }
    }

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