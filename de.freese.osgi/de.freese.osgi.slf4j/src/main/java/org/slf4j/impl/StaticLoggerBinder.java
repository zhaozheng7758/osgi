/**
 * Created: 26.09.2012
 */

package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * @author Thomas Freese
 */
public class StaticLoggerBinder implements LoggerFactoryBinder
{
	/**
	 * 
	 */
	private static OSGILogFactory FACTORY = new OSGILogFactory();

	/**
	 * Declare the version of the SLF4J API this implementation is compiled against. The value of
	 * this field is usually modified with each release.
	 */
	// to avoid constant folding by the compiler, this field must *not* be final
	public static String REQUESTED_API_VERSION = "1.7.7";  // !final

	/**
	 * The unique instance of this class.
	 */
	private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

	/**
	 * Return the singleton of this class.
	 * 
	 * @return the StaticLoggerBinder singleton
	 */
	public static final StaticLoggerBinder getSingleton()
	{
		return SINGLETON;
	}

	/**
	 * Erstellt ein neues {@link StaticLoggerBinder} Object.
	 */
	private StaticLoggerBinder()
	{
		super();
	}

	/**
	 * @see org.slf4j.spi.LoggerFactoryBinder#getLoggerFactory()
	 */
	@Override
	public ILoggerFactory getLoggerFactory()
	{
		return FACTORY;
	}

	/**
	 * @see org.slf4j.spi.LoggerFactoryBinder#getLoggerFactoryClassStr()
	 */
	@Override
	public String getLoggerFactoryClassStr()
	{
		return FACTORY.getClass().getName();
	}
}
