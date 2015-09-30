package com.xiongyingqi.jdbc.driver;

import com.xiongyingqi.util.Assert;
import com.xiongyingqi.util.ClassLookupHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author <a href="http://xiongyingqi.com">qi</a>
 * @version 2015-09-29 18:22
 */
public class DriverAdapterManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverAdapterManager.class);
    private static Set<DriverAdapter> driverAdapters = new HashSet<DriverAdapter>();

    static {
        Set<Class<?>> classes = ClassLookupHelper.getClasses(DriverAdapterManager.class.getPackage().getName(), true, new ClassLookupHelper.ClassFileFilter() {
            @Override
            public boolean accept(String klassName, File file, ClassLoader loader) {
                try {
                    Class<?> aClass = Class.forName(klassName);
                    Class<?>[] interfaces = aClass.getInterfaces();
                    for (Class<?> anInterface : interfaces) {
                        if (anInterface.equals(DriverAdapter.class)) {
                            return true;
                        }
                    }
                } catch (ClassNotFoundException e) {
                    return false;
                }
                return false;
            }

            @Override
            public boolean accept(String klassName, JarFile jar, JarEntry entry, ClassLoader loader) {
                return false;
            }
        });
        if (logger.isDebugEnabled()) {
            logger.debug("found classes of driver adapter! classes: {}", classes);
        }
        for (Class<?> aClass : classes) {
            try {
                Object o = aClass.newInstance();
                if (o instanceof DriverAdapter) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("new instance a driver adapter! instance: {}", o);
                    }
                    driverAdapters.add((DriverAdapter) o);
                }
            } catch (InstantiationException e) {
                logger.error("", e);
            } catch (IllegalAccessException e) {
                logger.error("", e);
            }
        }
    }

    public static DriverAdapter getDriverAdapter(String name) {
        DriverAdapter driverAdapter = _getDriverAdapter(name);
        if (logger.isDebugEnabled()) {
            logger.debug("getDriverAdapter... name: {}, return driverAdapter: {}", name, driverAdapter);
        }
        return driverAdapter;
    }

    private static DriverAdapter _getDriverAdapter(String name) {
        Assert.hasText(name);
        for (DriverAdapter driverAdapter : driverAdapters) {
            if (name.equals(driverAdapter.supportName())) {
                return driverAdapter;
            }
        }
        return null;
    }

}
