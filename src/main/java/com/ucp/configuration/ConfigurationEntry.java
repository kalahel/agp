package com.ucp.configuration;

/**
 * Configuration paths
 * FIXME TRY TO MAKE THE PATHS RELATIVE
 */
public class ConfigurationEntry {
    private final static String RELATIVE_PATH = PathParser.parsePath();
    /**
     * Path to the description of touristic sites.
     */
//    public final static String RELATIVE_TEXT_PATH = "src/main/resources/descriptions";
//    public final static String RELATIVE_TEXT_PATH = "D:\\Bureau\\agp\\src\\main\\resources\\descriptions";
//    public final static String RELATIVE_TEXT_PATH = ConfigurationEntry.class.getClassLoader().getResource("descriptions").getPath().substring(1);
    public final static String RELATIVE_TEXT_PATH = RELATIVE_PATH + "/descriptions";

    /**
     * Path to the result of the Lucene analysis.
     */
//    public final static String RELATIVE_INDEX_PATH = "src/main/resources/index";
//    public final static String RELATIVE_INDEX_PATH = "D:\\Bureau\\agp\\src\\main\\resources\\index";
    public final static String RELATIVE_INDEX_PATH = RELATIVE_PATH + "/index";
//    public final static String RELATIVE_INDEX_PATH = ConfigurationEntry.class.getClassLoader().getResource("index").getPath().substring(1);
}
