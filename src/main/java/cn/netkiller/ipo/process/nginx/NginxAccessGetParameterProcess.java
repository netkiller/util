package cn.netkiller.ipo.process.nginx;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.netkiller.ipo.process.ProcessInterface;

public class NginxAccessGetParameterProcess extends NginxAccessProcess implements ProcessInterface {

	private final static Logger logger = LoggerFactory.getLogger(NginxAccessGetParameterProcess.class);
	private Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
	// private String regex;

	public NginxAccessGetParameterProcess() {
		super();
	}

	public NginxAccessGetParameterProcess(String regex) {
		super(regex);
	}

	@Override
	public String run(String line) {
		logger.info("Process line {}", line);
		String newLine = super.run(line);
		@SuppressWarnings("unchecked")
		Map<String, String> map = gson.fromJson(newLine, LinkedHashMap.class);
		String request = map.get("request");
		// throw new UnsupportedOperationException("Not supported yet.");
		// Map<String, String> map = new HashMap<String, String>();
		// Pattern pattern = Pattern.compile(this.regex);

		// 现在创建 matcher 对象
		// Matcher matcher = pattern.matcher(line);
		// if (matcher.find()) {

		if (request != null && request.contains("?")) {
			// System.out.println("Found value: " + matcher.group(3));
			String[] param = request.split("\\?");
			String[] variables = param[1].split("&");
			for (String variable : variables) {
				String[] var = variable.split("=");
				if (var.length == 2) {
					// System.out.println(var[0] + "=" + var[1]);
					map.put(var[0], var[1]);
				} else {
					// System.out.println(var[0] + "=");
					map.put(var[0], "");
				}

			}

		}
		// }
		logger.debug("Process map ", map.toString());
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();

		return gson.toJson(map);
	}

}