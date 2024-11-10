package ru.sergeipavlov.metrology.kip.transformation;

public class UnitTransformationCalc {

    double[] pascalToDerivatives(double pascal) {

        double[] results;

        double KgsFractionSm2 = pascal / 98066.5;
        double KgsFractionM2 = pascal / 9.80665;
        double MilliBar = pascal / 100;
        double Bar = pascal / 100000;
        double KiloPa = pascal / 1000;
        double MegaPascal = pascal / 1000000;
        double NutonFractionMeter2 = pascal;
        double KiloNutonFractionM2 = pascal / 1000;
        double MegaNutonFractionM2 = pascal / 1000000;
        double NutonFractionSm2 = pascal /10000;
        double NutomFractionMillimetr2 = pascal / 1000000;
        double Atmosphere = pascal / 101325;
        double TechnicalAtmosphere = pascal / 98066.5;
        double MilliMetrH20 = pascal / 9.80665;
        double SantimetrH2O = pascal  / 98.0665;
        double MetreH2O = pascal / 9806.65;
        double InH2O = pascal / 249.08891;
        double FtH2O = pascal / 2989.006692;
        double MillimetreHG = pascal / 133.3223684;
        double SantimetreHG = pascal / 1333.223684;
        double InHG = pascal / 3386.38815789474;
        double Ksi = pascal / 6894.7572931783;
        double Psi = pascal / 6894.7572931783;
        double Psf = pascal / 47.8802589803;
        double TsiUSA = pascal / 13789514.58633672267344;
        double TsfUSA = pascal / 95760.51796067168523226;
        double TsiUK = pascal / 15444256.3366971;
        double TsfUK = pascal / 107251.780115952;

        return results = new double[]{KgsFractionSm2, KgsFractionM2, MilliBar, Bar, KiloPa, MegaPascal,
                NutonFractionMeter2, KiloNutonFractionM2, MegaNutonFractionM2, NutonFractionSm2,
                NutomFractionMillimetr2, Atmosphere, TechnicalAtmosphere, MilliMetrH20, SantimetrH2O,
                MetreH2O, InH2O, FtH2O, MillimetreHG, SantimetreHG, InHG, Ksi, Psi, Psf, TsiUSA, TsfUSA,
                TsiUK, TsfUK};
    }

    double[] celsiumToAnother(double celsium) {
        double[] results;

        double degreeCelsium = celsium;
        double degreeKelvin = celsium + 273.15;
        double degreeFahrengeit = 9 / 5 * celsium + 32;
        double degreeRankin = 0;
        double degreeReomur = 0;
        return results = new double[]{degreeKelvin, degreeFahrengeit, degreeRankin, degreeReomur};
    }
}


