public class CoreCount {
    /**
     * Counts the number of cores in the Processor.
     * Might be useful for deciding
     * what number of threads should be used
     */
    public void countNumberOfCores() {
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println(coreCount);

    }
}
