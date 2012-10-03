package de.freese.osgi.slf4j;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.slf4j.impl.OSGILogFactory;

/**
 * @author Thomas Freese
 */
public class Activator implements BundleActivator
{
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

		ServiceReference<LogService> ref = context.getServiceReference(LogService.class);
		LogService logService = context.getService(ref);
		logService.log(LogService.LOG_INFO, "init logging");
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext bundleContext) throws Exception
	{
		Activator.context = null;
	}
}
