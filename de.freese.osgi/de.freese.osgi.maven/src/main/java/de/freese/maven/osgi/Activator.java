package de.freese.maven.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

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
		System.out.println("Hello World!!");

		// Producer
		// context.registerService(
		// IHelloProvider.class.getName(),
		// new OsgiHelloProvider(), null);

		// Consumer
		// helloProviderTracker = new ServiceTracker(context,
		// IHelloProvider.class.getName(),
		// new ServiceTrackerCustomizer() {
		// public Object addingService(final ServiceReference reference) {
		// final IHelloProvider helloProvider = (IHelloProvider)
		// context.getService(reference);
		// System.out.println(helloProvider.sayHello());
		// return helloProvider;
		// }
		// public void modifiedService(final ServiceReference reference,
		// final Object service) { // Nothing to be done!
		// }
		// public void removedService(final ServiceReference reference,
		// final Object service) { // Nothing to be done!
		// }
		// });
		// helloProviderTracker.open();
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext context) throws Exception
	{
		System.out.println("Goodbye World!!");
		// if (helloProviderTracker != null) helloProviderTracker.close();
	}
}
