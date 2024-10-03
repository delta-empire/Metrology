package ru.sergeipavlov.metrology.kip;

public class ScaleSignalCalc {

    public double calcPhysicalValueLinearScale(double unifiedSignal, double startUnifiedSignal, double endUnifiedSignal,
                                             double endPhysicalValue, double startPhysicalValue) {
        double physicalValue;
        physicalValue = ((unifiedSignal - startUnifiedSignal) / (endUnifiedSignal - startUnifiedSignal)) * (endPhysicalValue - startPhysicalValue) + startPhysicalValue;
        return physicalValue;
    }

    public double calcPhysicalValueLinearDecreasingScale(double unifiedSignal, double startUnifiedSignal, double endUnifiedSignal,
                                                       double endPhysicalValue, double startPhysicalValue) {
        double physicalValue;
        physicalValue = ((unifiedSignal - endUnifiedSignal) / (startUnifiedSignal - endUnifiedSignal)) * (endPhysicalValue - startPhysicalValue) + startPhysicalValue;
        return physicalValue;
    }

    public double calcPhysicalValueQuadraticScale(double unifiedSignal, double startUnifiedSignal, double endUnifiedSignal,
                                                double endPhysicalValue, double startPhysicalValue) {
        double physicalValue;
        physicalValue = Math.sqrt(((unifiedSignal - startUnifiedSignal) / (endUnifiedSignal - startUnifiedSignal))) * (endPhysicalValue - startPhysicalValue) + startPhysicalValue;
        return physicalValue;
    }

    public double calcPhysicalValueQuadraticDecreasingScale(double unifiedSignal, double startUnifiedSignal, double endUnifiedSignal,
                                                          double endPhysicalValue, double startPhysicalValue) {
        double physicalValue;
        physicalValue = Math.sqrt(((unifiedSignal - endUnifiedSignal) / (startUnifiedSignal - endUnifiedSignal))) * (endPhysicalValue - startPhysicalValue) + startPhysicalValue;
        return physicalValue;
    }

    public double calcPhysicalValueRootextractingScale(double unifiedSignal, double startUnifiedSignal, double endUnifiedSignal,
                                                     double endPhysicalValue, double startPhysicalValue) {
        double physicalValue;
        physicalValue = Math.pow(((unifiedSignal - startUnifiedSignal) / (endUnifiedSignal - startUnifiedSignal)), 2.) * (endPhysicalValue - startPhysicalValue) + startPhysicalValue;
        return physicalValue;
    }

    public double calcPhysicalValueRootextractingDecreasingScale(double unifiedSignal, double startUnifiedSignal, double endUnifiedSignal,
                                                               double endPhysicalValue, double startPhysicalValue) {
        double physicalValue;
        physicalValue = Math.pow(((unifiedSignal - endUnifiedSignal) / (startUnifiedSignal - endUnifiedSignal)), 2.) * (endPhysicalValue - startPhysicalValue) + startPhysicalValue;
        return physicalValue;
    }

    public double calcUnifiedSignalRootextractingDecreasingScale(double physicalValue, double startPhysicalValue, double endPhysicalValue,
                                                               double endUnifiedSignal, double startUnifiedSignal) {
        double unifiedSignal;
        unifiedSignal = Math.sqrt(((physicalValue - startPhysicalValue)/(endPhysicalValue - startPhysicalValue)))
                * (startUnifiedSignal - endUnifiedSignal) + endUnifiedSignal;
        return  unifiedSignal;
    }

    public double calcUnifiedSignalRootextractingScale(double physicalValue, double startPhysicalValue, double endPhysicalValue,
                                                     double endUnifiedSignal, double startUnifiedSignal) {
        double unifiedSignal;
        unifiedSignal = Math.sqrt(((physicalValue - startPhysicalValue)/(endPhysicalValue - startPhysicalValue)))
                * (endUnifiedSignal - startUnifiedSignal) + startUnifiedSignal;
        return  unifiedSignal;
    }

    public double calcUnifiedSignalQuadraticDecreasingScale(double physicalValue, double startPhysicalValue, double endPhysicalValue,
                                                          double endUnifiedSignal, double startUnifiedSignal) {
        double unifiedSignal;
        unifiedSignal = Math.pow(((physicalValue - startPhysicalValue)/(endPhysicalValue - startPhysicalValue)), 2.)
                * (startUnifiedSignal - endUnifiedSignal) + endUnifiedSignal;
        return  unifiedSignal;
    }

    public double calcUnifiedSignalQuadraticScale(double physicalValue, double startPhysicalValue, double endPhysicalValue,
                                                double endUnifiedSignal, double startUnifiedSignal) {
        double unifiedSignal;
        unifiedSignal = Math.pow(((physicalValue - startPhysicalValue)/(endPhysicalValue - startPhysicalValue)), 2.)
                * (endUnifiedSignal - startUnifiedSignal) + startUnifiedSignal;
        return  unifiedSignal;
    }

    public double calcUnifiedSignalLinearDecreasingScale(double physicalValue, double startPhysicalValue, double endPhysicalValue,
                                                       double endUnifiedSignal, double startUnifiedSignal) {
        double unifiedSignal;
        unifiedSignal = ((physicalValue - startPhysicalValue) / (endPhysicalValue - startPhysicalValue))
                * (startUnifiedSignal - endUnifiedSignal) + endUnifiedSignal;
        return  unifiedSignal;
    }

    public double calcUnifiedSignalLinearScale(double physicalValue, double startPhysicalValue, double endPhysicalValue,
                                             double endUnifiedSignal, double startUnifiedSignal) {
        double unifiedSignal;
        unifiedSignal = ((physicalValue - startPhysicalValue) / (endPhysicalValue - startPhysicalValue))
                * (endUnifiedSignal - startUnifiedSignal) + startUnifiedSignal;
        return  unifiedSignal;
    }
}
