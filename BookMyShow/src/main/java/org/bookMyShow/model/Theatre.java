package org.bookMyShow.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class Theatre {

    private final String id;
    private final String name;
    private final List<Screen> screens;

    public Theatre(@NonNull final String id, @NonNull final String name)
    {
        this.id = id;
        this.name = name;
        screens = new ArrayList<>();
    }


    public void addScreen(Screen screen) {
        screens.add(screen);
    }
}
