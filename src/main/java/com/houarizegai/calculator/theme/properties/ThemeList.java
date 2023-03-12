package com.houarizegai.calculator.theme.properties;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ThemeList {
    private List<Theme> themes;

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public Map<String, Theme> getThemesAsMap() {
        return themes.stream().collect(Collectors.toMap(Theme::getName, Function.identity()));
    }
}
