package de.freese.osgi.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.OSGILogFactory;

import de.freese.osgi.service.api.IMyService;

/**
 * @author Thomas Freese
 */
public class Activator implements BundleActivator
{
	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 */
	private static BundleContext context;

	/**
	 * @return {@link BundleContext}
	 */
	static BundleContext getContext()
	{
		return context;
	}

	/**
	 *
	 */
	private ServiceTracker<IMyService, IMyService> serviceTracker = null;

	/**
	 *
	 */
	private ServiceTracker<LogService, LogService> logServiceTracker = null;

	/**
	 * Erstellt ein neues {@link Activator} Object.
	 */
	public Activator()
	{
		super();
	}

	/**
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(final BundleContext context) throws Exception
	{
		OSGILogFactory.initOSGI(context);

		Activator.context = context;
		this.logServiceTracker = new ServiceTracker<>(context, LogService.class, null);
		this.logServiceTracker.open();
		// LogService logService = this.logServiceTracker.getService();

		// // Service registrieren.
		// context.registerService(IMyService.class.getName(), new MyServiceImpl(), null);

		// logService.log(LogService.LOG_ERROR, "Activator.start()");
		this.logger.info("Activator.start()");

		this.serviceTracker = new ServiceTracker<>(context, IMyService.class, null);
		this.serviceTracker.open();
		IMyService service = this.serviceTracker.getService();

		if (service != null)
		{
			// logService.log(LogService.LOG_ERROR, service.getInfo());
			// System.err.println("Activator.start(): " + service.getInfo());
			this.logger.info("Activator.start(): {}", service.getInfo());
		}
		else
		{
			// logService.log(LogService.LOG_ERROR, "No Service !");
			// System.err.println("Activator.start(): No Service !");
			this.logger.error("Activator.start(): No Service !");
		}
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext bundleContext) throws Exception
	{
		Activator.context = null;

		LogService logService = this.logServiceTracker.getService();
		logService.log(LogService.LOG_ERROR, "Activator.stop()");
		System.err.println("Activator.stop()");

		this.logServiceTracker.close();
		this.logServiceTracker = null;

		this.serviceTracker.close();
		this.serviceTracker = null;
	}
}
