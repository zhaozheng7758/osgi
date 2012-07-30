/**
 * Created: 06.07.2012
 */

package de.freese.osgi.service.impl;

import java.util.Date;

import de.freese.osgi.service.api.IMyService;

/**
 * @author Thomas Freese
 */
public class MyServiceImpl implements IMyService
{
	/**
	 * Erstellt ein neues {@link MyServiceImpl} Object.
	 */
	public MyServiceImpl()
	{
		super();
	}

	/**
	 * @see de.freese.osgi.service.api.IMyService#getInfo()
	 */
	@Override
	public String getInfo()
	{
		return getClass().getSimpleName() + ": " + new Date().toString();
	}
}
