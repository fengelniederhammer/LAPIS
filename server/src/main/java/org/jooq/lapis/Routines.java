/*
 * This file is generated by jOOQ.
 */
package org.jooq.lapis;


import org.jooq.Configuration;
import org.jooq.lapis.routines.YSwitchInStagingTables;


/**
 * Convenience access to all stored procedures and functions in the default 
 * schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Routines {

    /**
     * Call <code>y_switch_in_staging_tables</code>
     */
    public static void ySwitchInStagingTables(
          Configuration configuration
    ) {
        YSwitchInStagingTables p = new YSwitchInStagingTables();

        p.execute(configuration);
    }
}
