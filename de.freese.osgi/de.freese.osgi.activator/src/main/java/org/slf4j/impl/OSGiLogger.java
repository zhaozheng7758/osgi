/**
 * Created: 26.09.2012
 */

package org.slf4j.impl;

import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

/**
 * SLF4J Logger für die OSGI Umgebung.
 * 
 * @author Thomas Freese
 */
public class OSGiLogger extends MarkerIgnoringBase
{
	/**
	 *
	 */
	private static final long serialVersionUID = -7260434629279477438L;

	/**
	 * Erstellt ein neues {@link OSGiLogger} Object.
	 */
	public OSGiLogger()
	{
		super();
	}

	/**
	 * @see org.slf4j.Logger#debug(java.lang.String)
	 */
	@Override
	public void debug(final String msg)
	{
		internalLog(LogService.LOG_DEBUG, msg, null);
	}

	/**
	 * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object)
	 */
	@Override
	public void debug(final String format, final Object arg)
	{
		String msgStr = MessageFormatter.format(format, arg).getMessage();
		internalLog(LogService.LOG_DEBUG, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void debug(final String format, final Object...argArray)
	{
		String msgStr = MessageFormatter.arrayFormat(format, argArray).getMessage();
		internalLog(LogService.LOG_DEBUG, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void debug(final String format, final Object arg1, final Object arg2)
	{
		String msgStr = MessageFormatter.format(format, arg1, arg2).getMessage();
		internalLog(LogService.LOG_DEBUG, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#debug(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void debug(final String msg, final Throwable t)
	{
		internalLog(LogService.LOG_DEBUG, msg, t);
	}

	/**
	 * @see org.slf4j.Logger#error(java.lang.String)
	 */
	@Override
	public void error(final String msg)
	{
		internalLog(LogService.LOG_ERROR, msg, null);
	}

	/**
	 * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object)
	 */
	@Override
	public void error(final String format, final Object arg)
	{
		String msgStr = MessageFormatter.format(format, arg).getMessage();
		internalLog(LogService.LOG_ERROR, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void error(final String format, final Object...argArray)
	{
		String msgStr = MessageFormatter.arrayFormat(format, argArray).getMessage();
		internalLog(LogService.LOG_ERROR, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#error(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void error(final String format, final Object arg1, final Object arg2)
	{
		String msgStr = MessageFormatter.format(format, arg1, arg2).getMessage();
		internalLog(LogService.LOG_ERROR, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#error(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void error(final String msg, final Throwable t)
	{
		internalLog(LogService.LOG_ERROR, msg, t);
	}

	/**
	 * @see org.slf4j.Logger#info(java.lang.String)
	 */
	@Override
	public void info(final String msg)
	{
		internalLog(LogService.LOG_INFO, msg, null);
	}

	/**
	 * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object)
	 */
	@Override
	public void info(final String format, final Object arg)
	{
		String msgStr = MessageFormatter.format(format, arg).getMessage();
		internalLog(LogService.LOG_INFO, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void info(final String format, final Object...argArray)
	{
		String msgStr = MessageFormatter.arrayFormat(format, argArray).getMessage();
		internalLog(LogService.LOG_INFO, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#info(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void info(final String format, final Object arg1, final Object arg2)
	{
		String msgStr = MessageFormatter.format(format, arg1, arg2).getMessage();
		internalLog(LogService.LOG_INFO, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#info(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void info(final String msg, final Throwable t)
	{
		internalLog(LogService.LOG_INFO, msg, t);
	}

	/**
	 * Check the availability of the OSGI logging service, and use it is available. Does nothing
	 * otherwise.
	 * 
	 * @param level int
	 * @param message Object
	 * @param t {@link Throwable}
	 */
	private final void internalLog(final int level, final Object message, final Throwable t)
	{
		LogService logservice = OSGILogFactory.getLogService();
		ServiceReference<?> serviceref = OSGILogFactory.getServiceReference();

		if (logservice != null)
		{
			try
			{
				if (t != null)
				{
					logservice.log(serviceref, level, message.toString(), t);
				}
				else
				{
					logservice.log(serviceref, level, message.toString());
				}
			}
			catch (Exception exc)
			{
				// Service may have become invalid, just ignore any error
				// until the log service reference is updated by the
				// log factory.
			}
		}
	}

	/**
	 * @see org.slf4j.Logger#isDebugEnabled()
	 */
	@Override
	public boolean isDebugEnabled()
	{
		return true;
	}

	/**
	 * @see org.slf4j.Logger#isErrorEnabled()
	 */
	@Override
	public boolean isErrorEnabled()
	{
		return true;
	}

	/**
	 * @see org.slf4j.Logger#isInfoEnabled()
	 */
	@Override
	public boolean isInfoEnabled()
	{
		return true;
	}

	/**
	 * @see org.slf4j.Logger#isTraceEnabled()
	 */
	@Override
	public boolean isTraceEnabled()
	{
		return true;
	}

	/**
	 * @see org.slf4j.Logger#isWarnEnabled()
	 */
	@Override
	public boolean isWarnEnabled()
	{
		return true;
	}

	/**
	 * @see org.slf4j.Logger#trace(java.lang.String)
	 */
	@Override
	public void trace(final String msg)
	{
		internalLog(LogService.LOG_DEBUG, msg, null);
	}

	/**
	 * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void trace(final String format, final Object...argArray)
	{
		String msgStr = MessageFormatter.arrayFormat(format, argArray).getMessage();
		internalLog(LogService.LOG_DEBUG, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object)
	 */
	@Override
	public void trace(final String format, final Object arg)
	{
		String msgStr = MessageFormatter.format(format, arg).getMessage();
		internalLog(LogService.LOG_DEBUG, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void trace(final String format, final Object arg1, final Object arg2)
	{
		String msgStr = MessageFormatter.format(format, arg1, arg2).getMessage();
		internalLog(LogService.LOG_DEBUG, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#trace(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void trace(final String msg, final Throwable t)
	{
		internalLog(LogService.LOG_DEBUG, msg, t);
	}

	/**
	 * @see org.slf4j.Logger#warn(java.lang.String)
	 */
	@Override
	public void warn(final String msg)
	{
		internalLog(LogService.LOG_WARNING, msg, null);
	}

	/**
	 * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object)
	 */
	@Override
	public void warn(final String format, final Object arg)
	{
		String msgStr = MessageFormatter.format(format, arg).getMessage();
		internalLog(LogService.LOG_WARNING, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void warn(final String format, final Object...argArray)
	{
		String msgStr = MessageFormatter.arrayFormat(format, argArray).getMessage();
		internalLog(LogService.LOG_WARNING, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void warn(final String format, final Object arg1, final Object arg2)
	{
		String msgStr = MessageFormatter.format(format, arg1, arg2).getMessage();
		internalLog(LogService.LOG_WARNING, msgStr, null);
	}

	/**
	 * @see org.slf4j.Logger#warn(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void warn(final String msg, final Throwable t)
	{
		internalLog(LogService.LOG_WARNING, msg, t);
	}
}
