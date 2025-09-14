package processapi;

public class ProcessapiPrototype {
			public void process(Processapi process) {
				ProcessResponse response  = process.run(new RunDataStream());
		}
}
