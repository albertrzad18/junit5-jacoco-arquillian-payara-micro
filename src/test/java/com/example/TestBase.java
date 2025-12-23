package com.example;

import java.io.File;
import java.io.IOException;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jacoco.core.tools.ExecDumpClient;
import org.jacoco.core.tools.ExecFileLoader;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;


@ArquillianSuiteDeployment
@ExtendWith(ArquillianExtension.class)
public abstract class TestBase {

	@Deployment
	public static WebArchive getArchive() {
		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war");
		war.addPackages(true,
				"com.example");

		war.addAsLibraries(Maven.resolver()
				.loadPomFromFile("pom.xml")
				.importDependencies(ScopeType.COMPILE, ScopeType.TEST, ScopeType.RUNTIME)
				.resolve()
				.withTransitivity()
				.asFile());

		return war;
	}

	@AfterAll
	static void captureCoverage() throws IOException {
		int port = 6300;

		ExecDumpClient jacocoClient = new ExecDumpClient();
		ExecFileLoader dump = jacocoClient.dump("localhost", port);
		dump.save(new File("./target/jacoco/jacoco-payara-it.exec"), true);
	}
}
