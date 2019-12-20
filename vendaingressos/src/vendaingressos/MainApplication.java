package vendaingressos;


import java.util.HashSet;
import java.util.Set;

import javax.jws.HandlerChain;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import vendaingressos.controller.MainController;
import vendaingressos.controller.PublicController;

@ApplicationPath("/main")
public class MainApplication extends Application {

	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(MainController.class);
		classes.add(PublicController.class);
		return (classes);
	}
}
