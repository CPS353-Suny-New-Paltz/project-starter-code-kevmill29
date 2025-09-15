package processapi;

import project.annotations.ProcessAPIPrototype;

public class ProcessapiPrototype {
	@ProcessAPIPrototype
			public void process(Processapi process) {
				DefaultProcessResponse response  = process.run(new RunDataStream());
				
			if(response.success()) {
				return;
			}
		}
}
