package com.houarizegai.calculator;

import java.awt.*;

public class Theme {

    private final Color applicationBackgroundColor;
    private final Color textColor;
    private final Color btnEqualTextColor;
    private final Color operatorBackgroundColor;
    private final Color numbersBackgroundColor;
    private final Color btnEqualBackgroundColor;

    public Theme(Color applicationBackgroundColor, Color textColor, Color btnEqualTextColor, Color operatorBackgroundColor, Color numbersBackgroundColor, Color btnEqualBackgroundColor) {
        this.applicationBackgroundColor = applicationBackgroundColor;
        this.textColor = textColor;
        this.btnEqualTextColor = btnEqualTextColor;
        this.operatorBackgroundColor = operatorBackgroundColor;
        this.numbersBackgroundColor = numbersBackgroundColor;
        this.btnEqualBackgroundColor = btnEqualBackgroundColor;
    }

    public Color getApplicationBackgroundColor() {
        return applicationBackgroundColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getBtnEqualTextColor() {
        return btnEqualTextColor;
    }

    public Color getOperatorBackgroundColor() {
        return operatorBackgroundColor;
    }

    public Color getNumbersBackgroundColor() {
        return numbersBackgroundColor;
    }

    public Color getBtnEqualBackgroundColor() {
        return btnEqualBackgroundColor;
    }

    public static ThemeBuilder builder() {
        return new ThemeBuilder();
    }

    public static class ThemeBuilder {

        private Color applicationBackgroundColor;
        private Color textColor;
        private Color btnEqualTextColor;
        private Color operatorBackgroundColor;
        private Color numbersBackgroundColor;
        private Color btnEqualBackgroundColor;
        
        public ThemeBuilder applicationBackgroundColor(Color applicationBackground) {
            this.applicationBackgroundColor = applicationBackground;
            return this;
        }

        public ThemeBuilder textColor(Color text) {
            this.textColor = text;
            return this;
        }

        public ThemeBuilder btnEqualTextColor(Color btnEqualText) {
            this.btnEqualTextColor = btnEqualText;
            return this;
        }

        public ThemeBuilder operatorBackgroundColor(Color operatorBackground) {
            this.operatorBackgroundColor = operatorBackground;
            return this;
        }

        public ThemeBuilder numbersBackgroundColor(Color numbersBackground) {
            this.numbersBackgroundColor = numbersBackground;
            return this;
        }

        public ThemeBuilder btnEqualBackgroundColor(Color btnEqualBackground) {
            this.btnEqualBackgroundColor = btnEqualBackground;
            return this;
        }
        
        public Theme build() {
            return new Theme(applicationBackgroundColor, textColor, btnEqualTextColor, operatorBackgroundColor, numbersBackgroundColor, btnEqualBackgroundColor);
        }
    }
}
