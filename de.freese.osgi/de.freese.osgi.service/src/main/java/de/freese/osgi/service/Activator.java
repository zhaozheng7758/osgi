package de.freese.osgi.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.freese.osgi.service.api.IMyService;
import de.freese.osgi.service.impl.MyServiceImpl;

/**
 * @author Thomas Freese
 */
public class Activator implements BundleActivator
{
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
		// Service registrieren.
		context.registerService(IMyService.class.getName(), new MyServiceImpl(), null);
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext context) throws Exception
	{
		// Empty
	}
}
