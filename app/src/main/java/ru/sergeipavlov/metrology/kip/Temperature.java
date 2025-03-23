package ru.sergeipavlov.metrology.kip;

public class Temperature {
    //Международная система СИ
    //градусы Цельсия
    private double celcium;
    //градусы Кельвина
    private double kelvin;
    //США и Британия
    //градусы Фаренгейта
    private double fahrenheit;
    //Редкоиспользуемые
    //градусы Ранкина
    private double rankina;
    //градусы Реомюра
    private double reaumur;

    public double getCelcium() {
        return celcium;
    }

    public void setCelcium(double celcium) {
        this.celcium = celcium;
    }

    public double getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public double getKelvin() {
        return kelvin;
    }

    public void setKelvin(double kelvin) {
        this.kelvin = kelvin;
    }

    public double getRankina() {
        return rankina;
    }

    public void setRankina(double rankina) {
        this.rankina = rankina;
    }

    public double getReaumur() {
        return reaumur;
    }

    public void setReaumur(double reaumur) {
        this.reaumur = reaumur;
    }

    private void celciumToAnother() {
        double currentTemp;
        currentTemp = getCelcium();
        kelvin = currentTemp - 273.15;
        fahrenheit = currentTemp * 9 / 5 + 32;
        rankina = (currentTemp + 273.15) * 9 / 5;
        reaumur = currentTemp * 4 / 5;
    }

    private void kelvinToAnother() {
        double currentTemp;
        currentTemp = getKelvin();
    }

    private void fahrenheitToAnother() {
        double currentTemp;
        currentTemp = getFahrenheit();
    }

    private void rankinaToAnother() {
        double currentTemp;
        currentTemp = getRankina();
    }

    private void reaumurToAnother() {
        double currentTemp;
        currentTemp = getReaumur();
    }

}
