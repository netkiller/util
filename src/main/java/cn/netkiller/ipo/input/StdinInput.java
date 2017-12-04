package cn.netkiller.ipo.input;

//import java.io.BufferedInputStream;
import java.io.BufferedReader;
//import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.netkiller.ipo.Input;

public class StdinInput implements InputInterface {
	private final static Logger logger = LoggerFactory.getLogger(Input.class);
	// DataInputStream stdin = new DataInputStream(new BufferedInputStream(System.in));
	BufferedReader stdin;
	private boolean nextLine = false;

	public StdinInput() {

	}

	@Override
	public Input open() {
		stdin = new BufferedReader(new InputStreamReader(System.in));
		return null;

	}

	@Override
	public String readLine() {
		try {
			return stdin.readLine();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;

	}

	public boolean hasNextLine() {
		// logger.debug(String.valueOf(this.nextLine));
		return this.nextLine;
	}

	public List<String> readLines() {
		List<String> lines = new ArrayList<String>();
		this.nextLine = false;

		String tmp = this.readLine();
		if (tmp != null && !tmp.equals("")) {
			lines.add(tmp);
			this.nextLine = true;
			// logger.debug(tmp);
		}

		return lines;
	}

	@Override
	public Input close() {
		try {
			this.stdin.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return null;
	}

}