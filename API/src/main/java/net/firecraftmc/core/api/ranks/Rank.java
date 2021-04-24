package net.firecraftmc.core.api.ranks;

import java.util.Objects;

public class Rank implements Comparable<Rank> {
    private String name, baseColor, chatColor;
    private boolean hasPrefix;
    private int voteWeight;
    private double coinMultiplier, chatCooldown;
    private int weight;

    public Rank(String name, String baseColor, String chatColor, boolean hasPrefix, int voteWeight, double coinMultiplier, double chatCooldown, int weight) {
        this.name = name;
        this.baseColor = baseColor;
        this.chatColor = chatColor;
        this.hasPrefix = hasPrefix;
        this.voteWeight = voteWeight;
        this.coinMultiplier = coinMultiplier;
        this.chatCooldown = chatCooldown;
        this.weight = weight;
    }

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