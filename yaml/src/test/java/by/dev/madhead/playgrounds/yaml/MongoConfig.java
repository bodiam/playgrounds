package by.dev.madhead.playgrounds.yaml;

import java.util.Map;

public class MongoConfig {
	private Map<String, Object> systemLog;
	private Map<String, Object> storage;
	private Map<String, Object> net;

	public Map<String, Object> getSystemLog() {
		return systemLog;
	}

	public void setSystemLog(Map<String, Object> systemLog) {
		this.systemLog = systemLog;
	}

	public Map<String, Object> getStorage() {
		return storage;
	}

	public void setStorage(Map<String, Object> storage) {
		this.storage = storage;
	}

	public Map<String, Object> getNet() {
		return net;
	}

	public void setNet(Map<String, Object> net) {
		this.net = net;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MongoConfig{");
		sb.append("systemLog=").append(systemLog);
		sb.append(", storage=").append(storage);
		sb.append(", net=").append(net);
		sb.append('}');
		return sb.toString();
	}
}
