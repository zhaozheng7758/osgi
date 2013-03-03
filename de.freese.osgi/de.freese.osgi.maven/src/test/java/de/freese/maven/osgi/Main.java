/**
 * Created: 30.03.2012
 */

package de.freese.maven.osgi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import org.apache.felix.main.AutoProcessor;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

/**
 * Autostart: java -jar bin/felix.jar -b /path/to/dir<br>
 * : (org.apache.felix.main.Main)
 * 
 * @author Thomas Freese
 */
public class Main
{
	/**
	 * @return {@link List}
	 * @throws IOException ex
	 */
	@SuppressWarnings("unused")
	private static List<String> findBundles() throws IOException
	{
		List<String> bundles = new ArrayList<>();
		File bundleFolder = new File("bundles");

		if (!bundleFolder.exists())
		{
			return bundles;
		}

		for (String file : bundleFolder.list())
		{
			bundles.add(String.format("file:bundles/%s", file));
		}

		return bundles;
	}

	/**
	 * @param frameworkFactory {@link FrameworkFactory}
	 * @return {@link Framework}
	 * @throws BundleException Falls was schief geht.
	 */
	private static Framework getConfiguredFramework(final FrameworkFactory frameworkFactory)
		throws BundleException
	{
		Map<String, String> configMap = new HashMap<>();

		configMap.put(Constants.FRAMEWORK_STORAGE, "osgi-data");
		configMap.put(Constants.FRAMEWORK_STORAGE_CLEAN,
				Constants.FRAMEWORK_STORAGE_CLEAN_ONFIRSTINIT);

		configMap.put("felix.auto.deploy.dir", "bundles");
		configMap.put("felix.auto.deploy.action", "install,start");
		// // configMap.put("felix.auto.start.1", "file:bundles/maven-osgi-0.0.1-SNAPSHOT.jar");
		//
		// configMap.put("felix.startlevel.bundle", "4");
		configMap.put("felix.log.level", "4");

		// Provide the Java 1.5 execution environment
		// config.put(Constants.FRAMEWORK_EXECUTIONENVIRONMENT, "J2SE-1.5");

		Framework framework = frameworkFactory.newFramework(configMap);
		framework.init();
		AutoProcessor.process(configMap, framework.getBundleContext());

		return framework;
	}

	/**
	 * @return {@link FrameworkFactory}
	 */
	private static FrameworkFactory getFrameworkFactory()
	{
		// String[] equinoxArgs = {"-console","1234","-noExit"};
		// BundleContext context = EclipseStarter.startup(equinoxArgs,null);
		// Bundle bundle =
		// context.installBundle("http://www.eclipsezone.com/files/jsig/bundles/HelloWorld.jar");
		// bundle.start();

		ServiceLoader<FrameworkFactory> loader = ServiceLoader.load(FrameworkFactory.class);
		FrameworkFactory frameworkFactory = null;

		for (FrameworkFactory ff : loader)
		{
			frameworkFactory = ff;
			break;
		}

		if (frameworkFactory == null)
		{
			throw new NullPointerException("frameworkFactory");
		}

		return frameworkFactory;
	}

	/**
	 * @param args String[]
	 */
	public static void main(final String[] args)
	{
		try
		{
			FrameworkFactory frameworkFactory = getFrameworkFactory();
			Framework framework = getConfiguredFramework(frameworkFactory);
			framework.start();

			// BundleContext context = framework.getBundleContext();
			// List<Bundle> installedBundles = new LinkedList<>();
			//
			// // Bundles suchen.
			// List<String> bundleFiles = findBundles();
			//
			// // Bundles installieren.
			// for (String bundleFile : bundleFiles)
			// {
			// installedBundles.add(context.installBundle(bundleFile));
			// }
			//
			// // Bundles starten.
			// for (Bundle bundle : installedBundles)
			// {
			// System.out.println(bundle.getSymbolicName());
			//
			// if (bundle.getHeaders().get(Constants.FRAGMENT_HOST) == null)
			// {
			// bundle.start();
			// }
			// else
			// {
			// System.out.println("fragmented");
			// }
			// }

			framework.stop();
			framework.waitForStop(0);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			System.exit(0);
		}
	}
}
