
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "checkOutdated")
public class NPMUpdaterMojo extends AbstractMojo {

	@Parameter(property = "modules")
	private List<String> modules;

	@Parameter(property = "extraModules", defaultValue = "")
	private String modulesToSplit;

	// <ModuleName, Version>
	private HashMap<String, String> modulesAndVersions = new HashMap<String, String>();

	@Override
	public void execute() throws MojoExecutionException {
		List<String> modulesList = modules;
		modulesList.addAll(Arrays.asList(modulesToSplit.split(",")));

		//Get all the desired npm module versions
		for (String moduleWithVersion : modulesList) {
			List<String> moduleAndVersion = Arrays.asList(moduleWithVersion.split("@"));
			if (moduleAndVersion.size() == 2) {
				modulesAndVersions.put(moduleAndVersion.get(0).trim(), moduleAndVersion.get(1).trim());
			}
		}

		try {

			for (Entry<String, String> entry : modulesAndVersions.entrySet()) {
				Process p = new ProcessBuilder("npm", "view", entry.getKey(), "version")
						.directory(new File(System.getProperty("user.dir"))).start();
				p.waitFor();
				String latestVersion = convertInputStreamToString(p.getInputStream()).trim();
				if (!entry.getValue().equals(latestVersion)) {
					getLog().info(entry.getKey() + "is outdated!");
					getLog().info("current version: " + entry.getValue() + ", latest version: " + latestVersion);
				}

			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static String convertInputStreamToString(InputStream inputStream) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		return result.toString(StandardCharsets.UTF_8.name());
	}
}
