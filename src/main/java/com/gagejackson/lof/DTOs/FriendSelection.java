package com.gagejackson.lof.DTOs;

public class FriendSelection {
    private long id;

    private String puuId;

    private String name;

    private int icon;

    private int summonerLevel;

    private boolean isSelected;

    public FriendSelection() {
    }

    public FriendSelection(long id, String puuId, String name, int icon, int summonerLevel, boolean isSelected) {
        this.id = id;
        this.puuId = puuId;
        this.name = name;
        this.icon = icon;
        this.summonerLevel = summonerLevel;
        this.isSelected = isSelected;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPuuId() {
        return puuId;
    }

    public void setPuuId(String puuId) {
        this.puuId = puuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
