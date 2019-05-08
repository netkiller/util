package cn.netkiller.ipo;

import java.util.ArrayList;
import java.util.List;

public class InputProcessOutputGroup {
	private final List<InputProcessOutput> inputProcessOutputGroups = new ArrayList<InputProcessOutput>();

	public InputProcessOutputGroup(InputProcessOutput inputProcessOutputGroup) {
		this.inputProcessOutputGroups.add(inputProcessOutputGroup);
	}

	public InputProcessOutputGroup add(InputProcessOutput inputProcessOutputGroup) {
		this.inputProcessOutputGroups.add(inputProcessOutputGroup);
		return this;
	}

	public void launch() {
		for (InputProcessOutput ipo : this.inputProcessOutputGroups) {

			new Thread(new Runnable() {
				@Override
				public void run() {
					ipo.launch();

				}
			}).start();
			;
		}

	}

}