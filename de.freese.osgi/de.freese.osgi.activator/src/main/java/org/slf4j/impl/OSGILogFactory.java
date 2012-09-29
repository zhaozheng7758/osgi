/**
 * Created: 26.09.2012
 */

package org.slf4j.impl;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * @author Thomas Freese
 */
public class OSGILogFactory implements ILoggerFactory
{
	/**
	 * 
	 */
	private static BundleContext CONTEXT = null;

	/**
	 * 
	 */
	private static LogService LOG_SERVICE = null;

	/**
	 * 
	 */
	private static OSGiLogger LOGGER = new OSGiLogger();

	/**
	 * 
	 */
	private static ServiceListener s_servlistener = new ServiceListener()
	{
		/**
		 * @see org.osgi.framework.ServiceListener#serviceChanged(org.osgi.framework.ServiceEvent)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void serviceChanged(final ServiceEvent event)
		{
			LogService ls = (LogService) CONTEXT.getService(event.getServiceReference());

			if (ls != null)
			{
				if (event.getType() == ServiceEvent.REGISTERED)
				{
					OSGILogFactory.setLogService(ls);

				}
				else if (event.getType() == ServiceEvent.UNREGISTERING)
				{
					if (ls.equals(LOG_SERVICE))
					{
						OSGILogFactory.setLogService(null);

						// Try to find another log service as a replacement for our loss
						ServiceReference<LogService> ref =
								(ServiceReference<LogService>) CONTEXT
										.getServiceReference(LogService.class.getName());

						if (ref != null)
						{
							LOG_SERVICE = CONTEXT.getService(ref);
						}
					}
				}
			}
		}
	};

	/**
	 * 
	 */
	private static ServiceReference<?> SERVICE_REFERENCE = null;

	/**
	 * @return {@link LogService}
	 */
	public static LogService getLogService()
	{
		return LOG_SERVICE;
	}

	/**
	 * @return {@link ServiceReference}
	 */
	public static ServiceReference<?> getServiceReference()
	{
		return SERVICE_REFERENCE;
	}

	/**
	 * @param context {@link BundleContext}
	 */
	public static void initOSGI(final BundleContext context)
	{
		initOSGI(context, null);
	}

	/**
	 * @param context {@link BundleContext}
	 * @param servref {@link ServiceReference}
	 */
	@SuppressWarnings("unchecked")
	public static void initOSGI(final BundleContext context, final ServiceReference<?> servref)
	{
		CONTEXT = context;
		SERVICE_REFERENCE = servref;

		try
		{
			String filter = "(objectclass=" + LogService.class.getName() + ")";
			context.addServiceListener(s_servlistener, filter);
		}
		catch (InvalidSyntaxException ex)
		{
			LOGGER.error(null, ex);
		}

		ServiceReference<LogService> ref =
				(ServiceReference<LogService>) context.getServiceReference(LogService.class
						.getName());

		if (ref != null)
		{
			LOG_SERVICE = context.getService(ref);
		}
	}

	/**
	 * @param logservice {@link LogService}
	 */
	public static void setLogService(final LogService logservice)
	{
		LOG_SERVICE = logservice;
	}

	/**
	 * @param ref {@link ServiceReference}
	 */
	public static void setServiceReference(final ServiceReference<?> ref)
	{
		SERVICE_REFERENCE = ref;
	}

	/**
	 * Erstellt ein neues {@link OSGILogFactory} Object.
	 */
	public OSGILogFactory()
	{
		super();
	}

	/**
	 * @see org.slf4j.ILoggerFactory#getLogger(java.lang.String)
	 */
	@Override
	public Logger getLogger(final String name)
	{
		return LOGGER;
	}
}
