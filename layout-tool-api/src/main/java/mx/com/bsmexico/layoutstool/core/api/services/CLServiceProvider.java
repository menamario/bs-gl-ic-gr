package mx.com.bsmexico.layoutstool.core.api.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

import mx.com.bsmexico.layoutstool.core.api.ComponentLayout;

public class CLServiceProvider{

    private static CLServiceProvider service;
    private ServiceLoader<ComponentLayout> loader;

    private CLServiceProvider() {
        loader = ServiceLoader.load(ComponentLayout.class);
    }

    public static synchronized CLServiceProvider getInstance() {
        if (service == null) {
            service = new CLServiceProvider();
        }
        return service;
    }


    public List<ComponentLayout> getComponents() {
    	List<ComponentLayout> components = new ArrayList<>();

        try {
            Iterator<ComponentLayout> cls = loader.iterator();
            while (cls.hasNext()) {
            	ComponentLayout cl = cls.next();
                components.add(cl);
            }
        } catch (ServiceConfigurationError serviceError) {            
            serviceError.printStackTrace();

        }
        return components;
    }
}
