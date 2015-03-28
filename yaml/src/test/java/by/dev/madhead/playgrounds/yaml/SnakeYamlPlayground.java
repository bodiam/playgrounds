package by.dev.madhead.playgrounds.yaml;

import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

@Test
public class SnakeYamlPlayground {
	@Test
	public void basic() {
		final Yaml yaml = new Yaml();
		final Object loaded = yaml.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("basic" +
				".yml"));

		System.out.println(loaded);
		System.out.println(loaded.getClass());
		System.out.println(((Map<String, Object>) loaded).get("systemLog"));
		System.out.println(((Map<String, Object>) loaded).get("systemLog").getClass());
	}

	@Test
	public void multipleDocs() {
		final Yaml yaml = new Yaml();
		final Object loaded = yaml.loadAll(Thread.currentThread().getContextClassLoader().getResourceAsStream
				("multiple_docs.yml"));

		System.out.println(loaded);
		System.out.println(loaded.getClass());

		final Iterable<Object> iterable = (Iterable<Object>) loaded;

		for (Object o : iterable) {
			System.out.println(o);
		}
	}

	@Test
	public void multipleDocsWithDots() {
		final Yaml yaml = new Yaml();
		final Object loaded = yaml.loadAll(Thread.currentThread().getContextClassLoader().getResourceAsStream
				("multiple_docs_with_dots.yml"));

		System.out.println(loaded);
		System.out.println(loaded.getClass());

		final Iterable<Object> iterable = (Iterable<Object>) loaded;

		for (Object o : iterable) {
			System.out.println(o);
		}
	}

	@Test
	public void tagTyped() {
		final Yaml yaml = new Yaml();
		final Object loaded = yaml.load(Thread.currentThread().getContextClassLoader().getResourceAsStream
				("mongo_config.yml"));
		final MongoConfig typed = yaml.loadAs(Thread.currentThread().getContextClassLoader().getResourceAsStream
				("mongo_config.yml"), MongoConfig.class);
		final MongoConfig typed1 = yaml.loadAs(Thread.currentThread().getContextClassLoader().getResourceAsStream
				("basic.yml"), MongoConfig.class);

		System.out.println(loaded);
		System.out.println(loaded.getClass());
		System.out.println(typed);
		System.out.println(typed.getClass());
		System.out.println(typed1);
		System.out.println(typed1.getClass());
	}
}
