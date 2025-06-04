package com.smartqueue.strategy;

public class StrategySelector {

    public static JobProcessorStrategy getStrategy(String jobType) {
        switch (jobType.toLowerCase()) {
            case "image":
                return new ImageProcessingStrategy();
            case "scrape":
                return new DataScrapingStrategy();
            case "convert":
                return new FileConversionStrategy();
            default:
                throw new IllegalArgumentException("Unknown job type: " + jobType);
        }
    }
}
