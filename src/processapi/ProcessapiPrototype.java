package processapi;

import project.annotations.ProcessAPIPrototype;

public class ProcessapiPrototype {
	@ProcessAPIPrototype
			public void process(Processapi process) {
				ProcessResponse response  = process.run(new RunDataStream());
				
			if(response.getResult().success()) {
				return;
			}else {
				System.out.println("Error! Process not started!");
			}
		}
}
